package com.RestMan.dao;

import com.RestMan.model.Customer6;
import com.RestMan.model.Manager6;
import com.RestMan.model.Member6;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class AuthenticationDAO extends DAO {
    public record AuthenticationResult(String role, Member6 account) {}

    public AuthenticationResult authenticate(String username, String password) {
        String sql = """
            SELECT m.id, m.username, m.password, m.name, m.dob, m.address, m.phone, m.email,
                s.position, mg.tblStaffid AS manager_id, c.tblMemberid AS customer_id
            FROM tblMember6 m
            LEFT JOIN tblStaff6 s ON s.tblMemberid = m.id
            LEFT JOIN tblManager6 mg ON mg.tblStaffid = s.tblMemberid
            LEFT JOIN tblCustomer6 c ON c.tblMemberid = m.id
            WHERE m.username = ?
            """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next() || !matchesPassword(password, rs.getString("password"))) {
                    return null;
                }

                if (rs.getObject("manager_id") != null) {
                    Manager6 manager = new Manager6();
                    setMemberFields(manager, rs);
                    manager.setPosition(rs.getString("position"));
                    return new AuthenticationResult("manager", manager);
                }

                if (rs.getObject("customer_id") != null) {
                    Customer6 customer = new Customer6();
                    setMemberFields(customer, rs);
                    return new AuthenticationResult("customer", customer);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Không thể xác thực tài khoản", e);
        }
    }

    private static void setMemberFields(Member6 member, ResultSet rs) throws SQLException {
        member.setId(rs.getInt("id"));
        member.setUsername(rs.getString("username"));
        member.setFullName(rs.getString("name"));
        member.setDob(rs.getDate("dob"));
        member.setAddress(rs.getString("address"));
        member.setPhone(rs.getString("phone"));
        member.setEmail(rs.getString("email"));
    }

    private static boolean matchesPassword(String password, String storedHash) {
        if (password == null || storedHash == null) {
            return false;
        }
        String[] parts = storedHash.split("\\$", -1);
        if (parts.length != 4 || !"pbkdf2_sha256".equals(parts[0])) {
            return false;
        }
        try {
            int iterations = Integer.parseInt(parts[1]);
            byte[] salt = Base64.getDecoder().decode(parts[2]);
            byte[] expected = Base64.getDecoder().decode(parts[3]);
            byte[] actual = deriveKey(password, salt, iterations);
            return MessageDigest.isEqual(actual, expected);
        } catch (IllegalArgumentException | GeneralSecurityException e) {
            return false;
        }
    }

    private static byte[] deriveKey(String password, byte[] salt, int iterations)
            throws GeneralSecurityException {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, 256);
        try {
            SecretKeyFactory factory =
                    SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return factory.generateSecret(spec).getEncoded();
        } finally {
            spec.clearPassword();
        }
    }
}

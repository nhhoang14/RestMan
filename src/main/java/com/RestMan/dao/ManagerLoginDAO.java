package com.RestMan.dao;

import com.RestMan.model.Manager6;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class ManagerLoginDAO extends DAO {
    public Manager6 authenticate(String username, String password) {
        String sql = """
            SELECT m.id, m.username, m.password, m.name, m.dob, m.address, m.phone, m.email,
                s.position
            FROM tblMember6 m
            JOIN tblStaff6 s ON s.tblMemberid = m.id
            JOIN tblManager6 mg ON mg.tblStaffid = s.tblMemberid
            WHERE m.username = ?
            """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                if (!matchesPassword(password, rs.getString("password"))) {
                    return null;
                }
                Manager6 manager = new Manager6();
                manager.setId(rs.getInt("id"));
                manager.setUsername(rs.getString("username"));
                manager.setFullName(rs.getString("name"));
                manager.setDob(rs.getDate("dob"));
                manager.setAddress(rs.getString("address"));
                manager.setPhone(rs.getString("phone"));
                manager.setEmail(rs.getString("email"));
                manager.setPosition(rs.getString("position"));
                return manager;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Không thể xác thực tài khoản quản lý", e);
        }
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
        PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(), salt, iterations, 256);
        try {
            SecretKeyFactory factory =
                    SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return factory.generateSecret(spec).getEncoded();
        } finally {
            spec.clearPassword();
        }
    }

}

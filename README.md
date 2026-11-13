<div align="center">

# RestMan

**Hệ thống quản lý nhà hàng**

Bài tập lớn môn Phân tích và Thiết kế hệ thống thông tin - PTIT

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Tomcat](https://img.shields.io/badge/Tomcat_10-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=black)
![MySQL](https://img.shields.io/badge/MySQL_9-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

</div>

---

## ✨ Chức năng

| Vai trò | Chức năng |
|---|---|
| 👤 Khách hàng | Tìm kiếm thông tin món ăn |
| 🧑‍💼 Nhân viên quản lý | Thống kê khách hàng theo doanh thu |

## 🛠️ Công nghệ

| Thành phần | Công nghệ |
|---|---|
| Giao diện | JSP, JSTL, CSS |
| Điều khiển | Jakarta Servlet |
| Truy cập dữ liệu | JDBC |
| Cơ sở dữ liệu | MySQL 9 |
| Máy chủ | Apache Tomcat 10 |
| Build | Maven |

## 📁 Cấu trúc

```
src/main
├── java/com/RestMan
│   ├── model/      # lớp thực thể
│   ├── dao/        # truy cập dữ liệu
│   └── servlet/    # điều khiển
└── webapp/         # giao diện JSP (customer, manager) và css
```

Luồng xử lý: `JSP → Servlet → DAO → Model`

## 🚀 Chạy dự án

1. Tạo CSDL MySQL `restman` theo thiết kế CSDL trong tài liệu.
2. Sửa thông tin kết nối trong `src/main/java/com/RestMan/dao/DAO.java`.
3. Build bằng `mvn package`, rồi deploy file WAR trong `target/` lên Tomcat 10.
4. Truy cập `http://localhost:8080/restaurant/`.

## 📄 Tài liệu

Báo cáo phân tích và thiết kế hệ thống: [Google Docs](https://docs.google.com/document/d/1-b8c-kPM9dgEHFcUrYrplAzTF3X5wi1z13pECZUmVm0)

## 👨‍💻 Tác giả

**Nguyễn Huy Hoàng** - B23DCCN336 - Nhóm CQ08

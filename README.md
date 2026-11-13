# RestMan

Hệ thống quản lý nhà hàng, xây dựng cho bài tập lớn môn Phân tích và Thiết kế hệ thống thông tin (PTIT).

## Chức năng

- Tìm kiếm thông tin món ăn (khách hàng)
- Thống kê khách hàng theo doanh thu (nhân viên quản lý)

## Công nghệ

Java · Jakarta Servlet/JSP · Apache Tomcat 10 · JDBC · MySQL 9 · Maven

## Cấu trúc

```
src/main
├── java/com/RestMan
│   ├── model/      # lớp thực thể
│   ├── dao/        # truy cập dữ liệu
│   └── servlet/    # điều khiển
└── webapp/         # giao diện JSP (customer, manager) và css
```

## Chạy dự án

1. Tạo CSDL MySQL `restman` theo thiết kế CSDL trong tài liệu.
2. Sửa thông tin kết nối trong `src/main/java/com/RestMan/dao/DAO.java`.
3. Build bằng `mvn package`, rồi deploy file WAR trong `target/` lên Tomcat 10.
4. Truy cập `http://localhost:8080/RestMan/`.

## Tài liệu

Báo cáo phân tích và thiết kế hệ thống: [Google Docs](https://docs.google.com/document/d/1-b8c-kPM9dgEHFcUrYrplAzTF3X5wi1z13pECZUmVm0)

## Tác giả

Nguyễn Huy Hoàng - B23DCCN336 - Nhóm CQ08

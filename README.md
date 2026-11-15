# RestMan

Hệ thống quản lý nhà hàng, xây dựng cho bài tập lớn môn Phân tích và Thiết kế hệ thống thông tin (PTIT).

## Chức năng

- Tìm kiếm thông tin món ăn (khách hàng)
- Thống kê khách hàng theo doanh thu (nhân viên quản lý)
- Giao diện đăng nhập và đăng xuất khu vực quản lý

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

### 1. Chuẩn bị CSDL MySQL
Nhập dữ liệu mẫu vào MySQL (chỉ cần chạy lần đầu):
```bash
mysql -u root -p < database.sql
```

Mật khẩu trong `database.sql` được lưu dưới dạng hash PBKDF2-SHA256 có salt,
không lưu plaintext. Nếu database đã được tạo trước thay đổi này, cần chạy lại
lệnh trên để cập nhật dữ liệu mẫu. Tài khoản mẫu vẫn là `manager1` với mật khẩu
`123456`.

### 2. Lệnh Build, Deploy và Chạy Web (Copy toàn bộ dán vào Terminal)
Thay `YOUR_MYSQL_PASSWORD` bằng mật khẩu MySQL của bạn rồi copy cả khối lệnh dưới đây dán vào Terminal:

```bash
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-25.jdk/Contents/Home
export PATH="$JAVA_HOME/bin:$PATH"
export CATALINA_HOME=/opt/homebrew/opt/tomcat/libexec
export RESTMAN_DB_URL='jdbc:mysql://localhost:3306/restmandb?serverTimezone=Asia/Ho_Chi_Minh'
export RESTMAN_DB_USER='root'
export RESTMAN_DB_PASSWORD='YOUR_MYSQL_PASSWORD'

# Tắt bản cũ nếu đang chạy
$CATALINA_HOME/bin/shutdown.sh 5 -force 2>/dev/null

# Đóng gói và khởi động bản mới ngầm trên Tomcat
./mvnw clean package && \
rm -rf $CATALINA_HOME/webapps/RestMan $CATALINA_HOME/webapps/RestMan.war && \
cp target/RestMan.war $CATALINA_HOME/webapps/ && \
$CATALINA_HOME/bin/startup.sh
```

> **Lưu ý:** Vì Tomcat chạy ở chế độ ngầm (`startup.sh`), sau khi chạy lệnh xong bạn có thể **tắt hẳn Terminal mà trang web vẫn chạy bình thường**.

* Mở trình duyệt xem trang web: [http://localhost:8080/RestMan/](http://localhost:8080/RestMan/)
* Trang tìm kiếm món ăn: [http://localhost:8080/RestMan/menuitem?keyword=](http://localhost:8080/RestMan/menuitem?keyword=)

### 3. Lệnh dừng Tomcat khi không dùng nữa
```bash
/opt/homebrew/opt/tomcat/libexec/bin/shutdown.sh 5 -force
```

Các luồng servlet chính:

- `/menuitem?keyword=...`: tìm món; `/menuitem?action=detail&id=...`: xem chi tiết.
- `/customer-revenue?action=report&startDate=yyyy-MM-dd&endDate=yyyy-MM-dd`: báo cáo doanh thu.
- `/customer-revenue?action=history&customerId=...`, `action=order&orderId=...`,
  `action=bill&orderId=...`: lịch sử, chi tiết đơn và hóa đơn.

## Tài liệu

Báo cáo phân tích và thiết kế hệ thống: [Google Docs](https://docs.google.com/document/d/1-b8c-kPM9dgEHFcUrYrplAzTF3X5wi1z13pECZUmVm0)

## Tác giả

Nguyễn Huy Hoàng - B23DCCN336 - Nhóm CQ08

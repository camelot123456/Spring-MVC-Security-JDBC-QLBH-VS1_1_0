# Mô tả dự án: SpringQLBH

## Giới thiệu
Dự án SpringQLBH là một ứng dụng web quản lý bán hàng được xây dựng dựa trên nền tảng Java, sử dụng Spring Framework (Spring MVC, Spring Security, Spring JDBC) và triển khai theo mô hình MVC. Ứng dụng hỗ trợ các chức năng quản lý người dùng, sản phẩm, đơn hàng, phân quyền truy cập và bảo mật.

## Công nghệ sử dụng
- **Ngôn ngữ:** Java 8
- **Framework:** Spring 4 (MVC, Security, JDBC)
- **Web:** JSP, JSTL, Sitemesh (layout)
- **Cơ sở dữ liệu:** SQL Server (thông qua JDBC)
- **Quản lý dự án:** Maven
- **Xử lý dữ liệu:** Jackson (JSON/XML), Jakarta XML Bind
- **Máy chủ:** Triển khai dạng WAR, phù hợp với các máy chủ ứng dụng Java (Tomcat, v.v.)

## Chức năng chính
- Đăng nhập, phân quyền người dùng với Spring Security
- Quản lý sản phẩm, khách hàng, đơn hàng
- Giao diện web động với JSP, JSTL, Sitemesh
- Kết nối và thao tác dữ liệu với SQL Server qua Spring JDBC

## Cấu trúc thư mục
- `src/main/java`: Mã nguồn Java (controller, service, dao, model, ...)
- `src/main/resources`: Cấu hình, properties
- `src/main/webapp`: Giao diện web (JSP, template, layout)
- `WEB-INF`: Cấu hình web, views, thư viện
- `pom.xml`: Quản lý phụ thuộc Maven

## Đối tượng sử dụng
- Doanh nghiệp vừa và nhỏ cần quản lý bán hàng
- Sinh viên, lập trình viên học tập về Spring MVC, bảo mật, JDBC

---
Bạn có thể mở rộng mô tả này hoặc yêu cầu thêm chi tiết về từng module nếu cần.

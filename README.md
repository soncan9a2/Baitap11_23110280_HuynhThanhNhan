## Huỳnh Thanh Nhân - 23110280

## Spring Security 6 Demos (1, 2, 3, 4)

### Chi tiết
#### Demo 1: Cài đặt, Cấu hình, Phân quyền trong Spring Security (link 1 bài giảng utex)

#### Demo 2: Sử dụng database để lưu và lấy dữ liệu cho việc phân quyền trong Spring Security (link 1 bài giảng utex)

#### Demo 3: Spring security với Thymeleaf (link 1 bài giảng utex)

#### Demo 4: Demo JWT với Spring Boot 3 – Security 6 (link 2 bài giảng utex)


### Demo 1 – Cài đặt, Cấu hình, Phân quyền trong Spring Security
- Public: `GET /hello`
- Cần đăng nhập: `GET /customer/**`
- Quyền:
  - `/customer/all` → `ROLE_ADMIN`
  - `/customer/{id}` → `ROLE_USER`

Tạo user (dùng API Demo 2), ví dụ (PowerShell):
```powershell
curl -Method POST http://localhost:8080/user/new `
  -Headers @{ "Content-Type" = "application/json" } `
  -Body '{"name":"admin","email":"admin@example.com","password":"123456","roles":"ROLE_ADMIN,ROLE_USER"}'
```

---

### Demo 2 – Sử dụng database để lưu và lấy dữ liệu cho việc phân quyền trong Spring Security
- API tạo user: `POST /user/new` (public)
- Mật khẩu được mã hóa trước khi lưu
- Roles dùng prefix `ROLE_...`

Ví dụ (CMD):
```bash
curl -X POST http://localhost:8080/user/new ^
  -H "Content-Type: application/json" ^
  -d "{\"name\":\"admin\",\"email\":\"admin@example.com\",\"password\":\"123456\",\"roles\":\"ROLE_ADMIN,ROLE_USER\"}"
```

---

### Demo 3 – Spring security với Thymeleaf
- Trang chủ: `GET /` (public) – danh sách sản phẩm đơn giản
- Form login tùy chỉnh: `GET /login`
- Trang lỗi quyền: `GET /403`
- Quyền cho thao tác:
  - `GET/POST /new` → `ROLE_ADMIN` hoặc `ROLE_CREATOR`
  - `GET/POST /edit/{idx}` → `ROLE_ADMIN` hoặc `ROLE_EDITOR`
  - `POST /delete/{idx}` → `ROLE_ADMIN`

Tạo user đầy đủ quyền UI (Git Bash/Linux/macOS):
```bash
curl -X POST http://localhost:8080/user/new \
  -H "Content-Type: application/json" \
  -d '{"name":"adminfull","email":"adminfull@example.com","password":"123","roles":"ROLE_ADMIN,ROLE_USER,ROLE_EDITOR,ROLE_CREATOR"}'
```

Đăng nhập tại `http://localhost:8080/` và thử tạo/sửa/xóa. Nếu thiếu quyền, sẽ chuyển tới `/403`.

---

### Mã nguồn chính
- Cấu hình bảo mật: `src/main/java/vn/iotstar/config/SecurityConfig.java`
- Cấu hình MVC: `src/main/java/vn/iotstar/config/MvcConfig.java`
- API Customer (Demo 1): `src/main/java/vn/iotstar/controller/CustomerController.java`
- API tạo user (Demo 2): `src/main/java/vn/iotstar/controller/UserController.java`
- UI & CRUD mẫu (Demo 3): `src/main/java/vn/iotstar/controller/ProductController.java`
- Thymeleaf templates: `src/main/resources/templates/`

---

### Demo 4 – JWT (JSON Web Token)
- Đăng nhập nhận token: `POST /auth/login` (JSON body: `{ "username", "password" }`)
- Gọi API kèm Bearer token: `GET /users/me` với header `Authorization: Bearer <token>`

Ví dụ:
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"adminfull","password":"123"}'

curl http://localhost:8080/users/me \
  -H "Authorization: Bearer <token>"
```

Test nhanh (PowerShell):
```powershell
# 1) (tuỳ chọn) Tạo user
curl -Method POST http://localhost:8080/user/new `
  -Headers @{ "Content-Type" = "application/json" } `
  -Body '{"name":"adminfull","email":"adminfull@example.com","password":"123","roles":"ROLE_ADMIN,ROLE_USER,ROLE_EDITOR,ROLE_CREATOR"}'

# 2) Đăng nhập lấy token
$login = curl -Method POST http://localhost:8080/auth/login `
  -Headers @{ "Content-Type" = "application/json" } `
  -Body '{"username":"adminfull","password":"123"}'
$token = ($login.Content | ConvertFrom-Json).token

# 3) Gọi API có bảo vệ
curl http://localhost:8080/users/me -Headers @{ "Authorization" = "Bearer $token" }
```



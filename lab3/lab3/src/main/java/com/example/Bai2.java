package com.example;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Bai2 {
    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.err.println("Hệ thống không hỗ trợ bảng mã UTF-8");
        }
        // Cấu hình kết nối SQL Server đến CSDL LAB3
        String jdbcUrl = "jdbc:sqlserver://localhost:1433;databaseName=LAB3;encrypt=true;trustServerCertificate=true;";
        String dbUser = "java_user";
        String dbPass = "mat_khau_cua_ban"; // Thay bằng mật khẩu SQL Server của bạn

        EmployeeDAO dao = new EmployeeDAO(jdbcUrl, dbUser, dbPass);
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n=== HỆ THỐNG QUẢN LÝ NHÂN VIÊN (BÀI 2) ===");
                System.out.println("1. Thêm nhân viên mới (Create)");
                System.out.println("2. Hiển thị danh sách & Tìm kiếm (Read List)");
                System.out.println("3. Xem chi tiết nhân viên (Read Detail)");
                System.out.println("4. Cập nhật thông tin nhân viên (Update)");
                System.out.println("5. Xóa nhân viên (Delete)");
                System.out.println("6. Thoát");
                System.out.print("Chọn chức năng (1-6): ");
                
                String choice = sc.nextLine().trim();

                switch (choice) {
                    case "1":
                        System.out.println("\n--- THÊM NHÂN VIÊN MỚI ---");
                        Employee newEmp = inputEmployee(sc, true); // true: Yêu cầu nhập mới Mã NV
                        if (newEmp != null) {
                            if (dao.insert(newEmp)) {
                                System.out.println("=> Thêm nhân viên thành công!");
                            } else {
                                System.out.println("=> Thêm thất bại (Có thể bị trùng mã EMP_CODE hoặc EMAIL).");
                            }
                        }
                        break;

                    case "2":
                        System.out.println("\n--- DANH SÁCH NHÂN VIÊN ---");
                        System.out.print("Nhập từ khóa tìm kiếm (Mã hoặc Tên) - Để trống để hiển thị tất cả: ");
                        String searchKey = sc.nextLine().trim();
                        dao.list(searchKey);
                        break;

                    case "3":
                        System.out.println("\n--- XEM CHI TIẾT NHÂN VIÊN ---");
                        System.out.print("Nhập Mã nhân viên (EMP_CODE): ");
                        String detailCode = sc.nextLine().trim();
                        if (detailCode.isEmpty()) {
                            System.out.println("=> Mã nhân viên không được để trống.");
                        } else {
                            dao.detail(detailCode);
                        }
                        break;

                    case "4":
                        System.out.println("\n--- CẬP NHẬT THÔNG TIN NHÂN VIÊN ---");
                        System.out.print("Nhập Mã nhân viên cần cập nhật (EMP_CODE): ");
                        String updateCode = sc.nextLine().trim();
                        if (updateCode.isEmpty()) {
                            System.out.println("=> Mã nhân viên không được để trống.");
                            break;
                        }
                        
                        System.out.println("Nhập các thông tin mới thay thế:");
                        Employee updateEmp = inputEmployee(sc, false); // false: Không nhập lại Mã NV
                        if (updateEmp != null) {
                            updateEmp.setEmpCode(updateCode); // Giữ nguyên mã cũ để làm điều kiện WHERE
                            if (dao.update(updateEmp)) {
                                System.out.println("=> Cập nhật thông tin thành công!");
                            } else {
                                System.out.println("=> Cập nhật thất bại. Không tìm thấy nhân viên có mã: " + updateCode);
                            }
                        }
                        break;

                    case "5":
                        System.out.println("\n--- XÓA NHÂN VIÊN ---");
                        System.out.print("Nhập Mã nhân viên cần xóa (EMP_CODE): ");
                        String deleteCode = sc.nextLine().trim();
                        if (deleteCode.isEmpty()) {
                            System.out.println("=> Mã nhân viên không được để trống.");
                            break;
                        }

                        // Bước xác nhận trước khi xóa theo yêu cầu đề bài
                        System.out.print("Bạn có chắc chắn muốn xóa nhân viên [" + deleteCode + "] không? (Y/N): ");
                        String confirm = sc.nextLine().trim();
                        if (confirm.equalsIgnoreCase("Y")) {
                            if (dao.delete(deleteCode)) {
                                System.out.println("=> Đã xóa nhân viên thành công!");
                            } else {
                                System.out.println("=> Xóa thất bại. Không tìm thấy nhân viên có mã: " + deleteCode);
                            }
                        } else {
                            System.out.println("=> Thao tác xóa đã bị hủy.");
                        }
                        break;

                    case "6":
                        System.out.println("Đã đóng chương trình quản lý.");
                        System.exit(0);

                    default:
                        System.out.println("=> Lựa chọn không hợp lệ. Vui lòng chọn từ 1 đến 6.");
                }
            } catch (Exception ex) {
                System.err.println("Đã xảy ra lỗi hệ thống: " + ex.getMessage());
            }
        }
    }

    /**
     * Hàm hỗ trợ nhập và kiểm tra tính hợp lệ của dữ liệu (AI Validation)
     */
    private static Employee inputEmployee(Scanner sc, boolean isInsert) {
        Employee e = new Employee();
        try {
            // Kiểm tra Mã NV (chỉ yêu cầu khi Thêm mới, khi Cập nhật thì lấy theo mã cũ)
            if (isInsert) {
                System.out.print("Mã NV (Bắt buộc): ");
                String code = sc.nextLine().trim();
                if (code.isEmpty()) {
                    throw new IllegalArgumentException("Mã nhân viên không được để trống.");
                }
                e.setEmpCode(code);
            }

            // Kiểm tra Họ tên
            System.out.print("Họ và tên (Bắt buộc): ");
            String name = sc.nextLine().trim();
            if (name.isEmpty()) {
                throw new IllegalArgumentException("Họ và tên không được để trống.");
            }
            e.setFullName(name);

            // Kiểm tra định dạng Email bằng Regular Expression
            System.out.print("Email (Bắt buộc): ");
            String email = sc.nextLine().trim();
            String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
            if (!email.matches(emailRegex)) {
                throw new IllegalArgumentException("Định dạng email không hợp lệ (Ví dụ chuẩn: abc@gmail.com).");
            }
            e.setEmail(email);

            // Số điện thoại
            System.out.print("Số điện thoại: ");
            e.setPhone(sc.nextLine().trim());

            // Giới tính
            System.out.print("Giới tính (Male / Female / Other): ");
            String gender = sc.nextLine().trim();
            e.setGender(gender.isEmpty() ? "Other" : gender);

            // Kiểm tra định dạng Ngày sinh
            System.out.print("Ngày sinh (Định dạng YYYY-MM-DD): ");
            String bdStr = sc.nextLine().trim();
            try {
                e.setBirthDate(LocalDate.parse(bdStr));
            } catch (DateTimeParseException ex) {
                throw new IllegalArgumentException("Ngày sinh sai định dạng. Vui lòng nhập đúng YYYY-MM-DD (Ví dụ: 2000-05-15).");
            }

            // Kiểm tra Mã phòng ban phải là số nguyên
            System.out.print("Mã Phòng Ban (1: Đào Tạo, 2: Tuyển Sinh, 3: CTSV): ");
            try {
                int deptId = Integer.parseInt(sc.nextLine().trim());
                e.setDeptId(deptId);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("Mã phòng ban bắt buộc phải là một số nguyên cụ thể.");
            }

            // Chức vụ
            System.out.print("Chức vụ (Position): ");
            e.setPosition(sc.nextLine().trim());

            // Kiểm tra Tiền lương không âm và phải là số hợp lệ
            System.out.print("Mức lương: ");
            try {
                BigDecimal salary = new BigDecimal(sc.nextLine().trim());
                if (salary.compareTo(BigDecimal.ZERO) < 0) {
                    throw new IllegalArgumentException("Mức lương không được là một số âm.");
                }
                e.setSalary(salary);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("Lương không hợp lệ. Phải nhập chữ số (Ví dụ: 12500000).");
            }

            return e; // Trả về đối tượng Employee hợp lệ sau khi qua hết bộ lọc

        } catch (IllegalArgumentException ex) {
            // Khi có bất cứ dữ liệu nào sai quy định, ném lỗi ra màn hình và dừng luồng nhập hiện tại
            System.err.println(" LỖI NHẬP LIỆU: " + ex.getMessage());
            System.out.println("=> Vui lòng thực hiện lại thao tác.");
            return null;
        }
    }
}
package presentation;

import business.AdminService;
import business.impl.AdminServiceImpl;

import java.util.Scanner;

public class LoginMenu {
    private final AdminService adminService = new AdminServiceImpl();

    public void login() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("========== ĐĂNG NHẬP QUẢN TRỊ ==========");
            System.out.print("Tài khoản: ");
            String username = sc.nextLine();
            System.out.print("Mật khẩu: ");
            String password = sc.nextLine();
            if (adminService.login(username, password)) {
                System.out.println("Đăng nhập thành công vào quản lý nào!!");
                new MainMenu().show();
                return;
            } else {
                System.err.println("Sai mật khẩu rồi! Vui lòng nhập lại\n");
            }
        } while (true);

    }
}

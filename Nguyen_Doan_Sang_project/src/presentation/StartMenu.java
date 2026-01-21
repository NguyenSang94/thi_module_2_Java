package presentation;

import java.util.Scanner;

public class StartMenu {
    public void show() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("====== HỆ THỐNG QUẢN LÝ CỬA HÀNG ======");
            System.out.println("1. Đăng nhập Admin");
            System.out.println("2. Thoát");
            System.out.println("=======================================");
            System.out.print("Nhập lựa chọn: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    new LoginMenu().login();
                    break;
                case "2":
                    System.out.println("Thoát");
                    System.exit(2);
                default:
                    System.out.println("Lựa chọn của bạn không hợp lệ !!");
            }
        } while (true);
    }


}

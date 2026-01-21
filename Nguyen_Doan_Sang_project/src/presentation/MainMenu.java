package presentation;

import java.util.Scanner;

public class MainMenu {
    public void show() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("========== MENU CHÍNH ==========");
            System.out.println("1. Quản lý sản phẩm điện thoại");
            System.out.println("2. Quản lý khách hàng");
            System.out.println("3. Quản lý hóa đơn");
            System.out.println("0. Đăng xuất");
            System.out.print("Nhập lựa chọn: ");
            String choice = sc.nextLine();
            switch (choice){
                case "1":
                    new ProductMenu().show();
                    break;
                case "2":
                    new CustomerMenu().show();
                    break;
                case "3":
                    System.out.println("Chức năng này chưa làm sẽ làm bù ạ");
                    break;
                case "0":
                    System.out.println("Đăng xuất");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (true);
    }
}

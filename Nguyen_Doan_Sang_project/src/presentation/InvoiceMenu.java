package presentation;

import business.InvoiceService;
import business.impl.InvoiceServiceImpl;
import model.Invoice;

import java.util.List;
import java.util.Scanner;

public class InvoiceMenu {
    Scanner sc = new Scanner(System.in);
    private final InvoiceService invoiceService = new InvoiceServiceImpl();

    public void show() {
        do {
            System.out.println("=========QUẢN LÝ HÓA ĐƠN========");
            System.out.println("1. Hiển thị danh sách hóa đơn");
            System.out.println("2. Thêm mới hóa đơn");
            System.out.println("3. Tìm kiếm hóa đơn");
            System.out.println("0. Quay lại menu chính");
            System.out.println("Mời bạn chọn: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    showAll();
                    break;
                case "2":
                    add();
                    break;
                case "3":
                    break;
                case "0":
                    break;
                default:
                    System.err.println("Lựa chọn không hợp lệ! Vui lòng chọn lai: ");
                    break;
            }

        } while (true);
    }

    public void showAll() {
        System.out.println("DANH SÁCH HÓA ĐƠN");
        List<Invoice> invoices = invoiceService.findById();
        invoices.forEach(System.out::println);
    }

    public void add() {

        System.out.print("Nhập ID khách hàng: ");
        int customerId = Integer.parseInt(sc.nextLine());

        System.out.print("Nhập tổng tiền hóa đơn: ");
        double totalAmount = Double.parseDouble(sc.nextLine());
        invoiceService.insert(customerId, totalAmount);
        System.out.println("Thêm thành công");
    }

}

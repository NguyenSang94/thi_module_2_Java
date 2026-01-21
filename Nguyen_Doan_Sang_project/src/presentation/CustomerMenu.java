package presentation;

import business.CustomerService;
import business.impl.CustomerServiceImpl;
import model.Customer;

import java.util.List;
import java.util.Scanner;

public class CustomerMenu {
    Scanner sc = new Scanner(System.in);
    private final CustomerService customerService = new CustomerServiceImpl();

    public void show() {

        do {
            System.out.println("\n========== QUẢN LÝ KHÁCH HÀNG ==========");
            System.out.println("1. Hiển thị danh sách khách hàng");
            System.out.println("2. Thêm khách hàng");
            System.out.println("3. Cập nhật khách hàng");
            System.out.println("4. Xóa khách hàng");
            System.out.println("0. Quay về menu chính");
            System.out.print(" Mời bạn Chọn: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    showAll();
                    break;
                case "2":
                    addCustomer();
                    break;
                case "3":
                    updateCustomer();
                    break;
                case "4":
                    deleteCustomer();
                    break;
                case "0":
                    return;
                default:
                    System.err.println("Lựa chọn không hợp lệ!");
            }
        } while (true);

    }

    public void showAll() {
        System.out.println("======DANH SÁCH KHÁCH HÀNG======");
        List<Customer> list = customerService.findAll();
        list.forEach(System.out::println);
    }

    public void addCustomer() {
        Customer c = new Customer();

        System.out.print("Tên: ");
        c.setName(sc.nextLine());

        System.out.print("Phone: ");
        c.setPhone(sc.nextLine());

        System.out.print("Email: ");
        c.setEmail(sc.nextLine());

        System.out.print("Địa chỉ: ");
        c.setAddress(sc.nextLine());

        customerService.insert(c);
        System.out.println("Thêm khách hàng thành công!");
    }

    public void updateCustomer() {
        Customer customer = null;
        do {
            System.out.println("Nhập vào ID cần cập nhật: ");
            int id = Integer.parseInt(sc.nextLine());
            customer = customerService.findById(id);
            if (customer == null) {
                System.err.println("Mã ID không tồn tại! Vui lòng nhập lại:");
            } else {
                break;
            }
        } while (true);


        System.out.println("\n Đây là thông tin hiện tại: ");
        System.out.println("Tên:  " + customer.getName());
        System.out.println("Phone:  " + customer.getPhone());
        System.out.println("Email:  " + customer.getEmail());
        System.out.println("Địa chỉ:  " + customer.getAddress());

        System.err.println("Nếu không muốn thay đổi thì ấn next\n");

        System.out.print("Nhập tên mới: ");
        String name = sc.nextLine();
        if (!name.isEmpty()) customer.setName(name);
        System.out.print("Nhập phone mới: ");
        String phone = sc.nextLine();
        if (!phone.isEmpty()) customer.setPhone(phone);
        System.out.print("Nhập email mới: ");
        String email = sc.nextLine();
        if (!email.isEmpty()) customer.setEmail(email);
        System.out.print("Nhập địa chỉ mới: ");
        String address = sc.nextLine();
        if (!address.isEmpty()) customer.setAddress(address);

        customerService.update(customer);
        System.out.println("Cập nhật thành công!");
    }

    public void deleteCustomer() {

        Customer cs = null;
        do {
            System.out.print("Nhập vào ID cần xóa: ");
            int id = Integer.parseInt(sc.nextLine());
            cs = customerService.findById(id);
            if (cs == null) {
                System.err.println("ID cần xóa không tồn tại! Vui lòng nhập lại:  ");
            } else {
                customerService.delete(id);
                System.out.println("Xóa thành công");
                break;
            }

        } while (true);
    }
}

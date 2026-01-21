package presentation;

import business.ProductService;
import business.impl.ProductServiceImpl;
import model.Product;

import java.util.List;
import java.util.Scanner;

public class ProductMenu {
    private final ProductService productService = new ProductServiceImpl();
    Scanner sc = new Scanner(System.in);

    public void show() {

        do {
            System.out.println("===== QUẢN LÝ SẢN PHẨM =====");
            System.out.println("1. Hiển thị danh sách");
            System.out.println("2. Thêm sản phẩm");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Tìm kiếm theo tên/hãng");
            System.out.println("6. Tìm kiếm theo khoảng giá");
            System.out.println("7. Tìm kiếm theo tên + tồn kho");
            System.out.println("0. Quay về menu chính");
            System.out.print("Chọn: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    showAll();
                    break;
                case "2":
                    add();
                    break;
                case "3":
                    update();
                    break;
                case "4":
                    delete();
                    break;
                case "5":
                    search_brand();
                    break;
                case "6":
                    searchByPrice();
                    break;
                case "7":
                    searchByNameAndStock();
                    break;

                case "0":
                    return;
                default:
                    System.err.println("Nhập sai, vui lòng nhập lại!");
            }
        } while (true);

    }

    public void showAll() {
        System.out.println("=========DANH SÁCH SẢN PHẨM========");
        List<Product> list = new ProductServiceImpl().findAll();
        list.forEach(System.out::println);
    }

    public void add() {
        Product p = new Product();
        System.out.print("Tên: ");
        p.setName(sc.nextLine());
        System.out.print("Hãng: ");
        p.setBrand(sc.nextLine());
        System.out.print("Giá: ");
        p.setPrice(Double.parseDouble(sc.nextLine()));
        System.out.print("Số lượng: ");
        p.setStock(Integer.parseInt(sc.nextLine()));
        productService.insert(p);
        System.out.println("Thêm thành công hãy kiểm tra dữ liệu mới nhập");
    }

    public void update() {
        Product product = null;
        do {
            System.out.println("Nhập vào ID cần cập nhật:");
            int id = Integer.parseInt(sc.nextLine());
            product = productService.findById(id);
            if (product == null) {
                System.err.println("Mã ID không tồn tại! Vui lòng nhập lại:");
            } else {
                break;
            }
        } while (true);


        System.out.println("\n Đây là thông tin hiện tại: ");
        System.out.println("Tên:  " + product.getName());
        System.out.println("Brand:  " + product.getBrand());
        System.out.println("Price:  " + product.getPrice());
        System.out.println("Stock:  " + product.getStock());

        System.out.print("Nhập name mới: ");
        String name = sc.nextLine();
        if (!name.isEmpty()) product.setName(name);

        System.out.print("Nhập brand mới: ");
        String brand = sc.nextLine();
        if (!brand.isEmpty()) product.setBrand(brand);

        System.out.print("Nhập price mới: ");
        String price = sc.nextLine();
        if (!price.isEmpty()) product.setPrice(Double.parseDouble(price));
        System.out.print("Nhập stock mới: ");
        String stock = sc.nextLine();
        if (!stock.isEmpty()) product.setStock(Integer.parseInt(stock));

        productService.update(product);
        System.out.println("Cập nhật thành công!");
    }

    public void delete() {
        Product pr = null;
        do {
            System.out.print("Nhập vào ID cần xóa:");
            int id = Integer.parseInt(sc.nextLine());
            pr = productService.findById(id);
            if (pr == null) {
                System.err.println("\nID cần xóa không tồn tại! Vui lòng nhập lại: ");
            } else {
                productService.delete(id);
                System.out.println("Đã xóa thành công");
                break;
            }

        } while (true);
    }

    public void search_brand() {
        List<Product> products;
        do {
            System.out.print("Nhập từ khóa để tìm kiếm sản phẩm: ");
            String key = sc.nextLine();
            products = productService.searchByBrand(key);
            if (products.isEmpty()) {
                System.err.println("Từ khóa không tồn tại trong cửa hàng! Vui lòng nhập lại:");
            } else {
                System.out.println("Danh sách sản phẩm tìm được");
                products.forEach(System.out::println);
                break;
            }
        } while (true);
    }

    public void searchByPrice() {
        List<Product> listProduct;
        do {
            System.out.print("Nhập giá thấp nhất: ");
            double min = Double.parseDouble(sc.nextLine());
            System.out.print("Nhập giá cao nhất: ");
            double max = Double.parseDouble(sc.nextLine());
            listProduct = productService.searchByPriceRange(min, max);
            if (listProduct.isEmpty()) {
                System.err.println("Khoảng giá bạn vừa nhập không nằm trong cửa hàng! Vui lòng nhập lại: ");
            } else {
                System.out.println("Danh sách sản phẩm theo khoảng giá bạn chọn");
                listProduct.forEach(System.out::println);
                break;
            }
        } while (true);
    }

    public void searchByNameAndStock() {
        List<Product> productslist;
        do {
            System.out.print("Nhập tên sản phẩm cần tìm: ");
            String name = sc.nextLine();
            productslist = productService.searchByNameAvailable(name);
            if (productslist.isEmpty()) {
                System.err.println("Không có tên sản phẩm nào phù hợp! Vui lòng nhập lại: ");
            } else {
                System.out.println("Danh sách sản phẩm cần tìm");
                productslist.forEach(System.out::println);
                break;
            }
        } while (true);
    }
}


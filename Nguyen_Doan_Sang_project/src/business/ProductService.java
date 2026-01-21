package business;

import model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(int id);
    List<Product> searchByBrand(String keyword);
    List<Product> searchByPriceRange(double min, double  max);
    List<Product> searchByNameAvailable(String key);
    void insert(Product product);
    void update(Product product);
    void delete(int id);
}

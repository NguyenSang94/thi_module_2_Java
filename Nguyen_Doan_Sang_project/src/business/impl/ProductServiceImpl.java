package business.impl;

import business.ProductService;
import dao.ProductDAO;
import dao.impl.ProductDAOImpl;
import model.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findById(int id) {
        return productDAO.findById(id);
    }

    @Override
    public List<Product> searchByBrand(String keyword) {
        return productDAO.searchByBrand(keyword);
    }

    @Override
    public List<Product> searchByPriceRange(double min, double max) {
        return productDAO.searchByPriceRange(min, max);
    }

    @Override
    public List<Product> searchByNameAvailable(String key) {
        return productDAO.searchByNameAvailable(key);
    }

    @Override
    public void insert(Product product) {
        productDAO.insert(product);
    }

    @Override
    public void update(Product product) {
        productDAO.update(product);
    }

    @Override
    public void delete(int id) {
        productDAO.delete(id);
    }
}

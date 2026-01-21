package dao;

import model.Customer;
import model.Product;

import java.util.List;

public interface CustomerDAO {
    List<Customer> findAll();
     Customer findById(int id);
    void insert(Customer cs);
    void update(Customer cs);
    void delete(int id);
}

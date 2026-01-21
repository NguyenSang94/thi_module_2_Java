package business;

import model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(int id);
    void insert(Customer cs);
    void update(Customer cs);
    void delete(int id);
}

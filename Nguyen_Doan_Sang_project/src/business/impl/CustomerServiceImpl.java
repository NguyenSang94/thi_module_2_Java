package business.impl;

import business.CustomerService;
import dao.CustomerDAO;
import dao.impl.CustomerDAOImpl;
import model.Customer;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

    @Override
    public Customer findById(int id) {
        return customerDAO.findById(id);
    }

    @Override
    public void insert(Customer cs) {
        customerDAO.insert(cs);
    }

    @Override
    public void update(Customer cs) {
        customerDAO.update(cs);
    }

    @Override
    public void delete(int id) {
        customerDAO.delete(id);
    }
}

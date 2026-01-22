package dao;

import model.Invoice;

import java.util.List;

public interface InvoiceDAO {
    List<Invoice> findAll();
    void insert(int customerId, double totalAmount);
}

package business;

import model.Invoice;

import java.util.List;

public interface InvoiceService {
    List<Invoice> findById();
    void insert(int customerId, double totalAmount);
}

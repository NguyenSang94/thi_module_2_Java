package business.impl;

import business.InvoiceService;
import dao.InvoiceDAO;
import dao.impl.InvoiceDAOImpl;
import model.Invoice;

import java.util.List;

public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceDAO invoiceDAO = new InvoiceDAOImpl();

    @Override
    public List<Invoice> findById() {
        return invoiceDAO.findAll();
    }

    @Override
    public void insert(int customerId, double totalAmount) {
        invoiceDAO.insert(customerId, totalAmount);
    }
}

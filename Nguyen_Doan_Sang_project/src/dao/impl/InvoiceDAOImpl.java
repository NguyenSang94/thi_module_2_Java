package dao.impl;

import dao.InvoiceDAO;
import model.Invoice;
import utils.DBUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAOImpl implements InvoiceDAO {

    @Override
    public List<Invoice> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Invoice> listInvoice = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{ call get_all_invoice()}");
            boolean hasData = callSt.execute();
            if (hasData) {
                ResultSet rs = callSt.getResultSet();
                listInvoice = new ArrayList<>();
                while (rs.next()) {
                    Invoice invoice = new Invoice();
                    invoice.setId(rs.getInt("id"));
                    invoice.setCustomerId(rs.getInt("customer_id"));
                    invoice.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
                    invoice.setTotalAmount(rs.getDouble("total_amount"));
                    listInvoice.add(invoice);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return listInvoice;
    }

    @Override
    public void insert(int customerId, double totalAmount) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("call add_invoice(?, ?)");
            callSt.setInt(1, customerId);
            callSt.setDouble(2, totalAmount);
            callSt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
    }
}




package dao.impl;

import dao.CustomerDAO;
import model.Customer;
import utils.DBUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public List<Customer> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Customer> listCustomer = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{call get_all_customer()}");
            boolean hasData = callSt.execute();
            if (hasData) {
                listCustomer = new ArrayList<>();
                ResultSet rs = callSt.getResultSet(); // trả 1 tập dữ liệu của bảng
                while (rs.next()) { // kiểm tra xem còn dữ liệu hay không
                    Customer cs = new Customer();
                    cs.setId(rs.getInt("id"));
                    cs.setName(rs.getString("name"));
                    cs.setPhone(rs.getString("phone"));
                    cs.setEmail(rs.getString("email"));
                    cs.setAddress(rs.getString("address"));
                    listCustomer.add(cs);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return listCustomer;
    }

    @Override
    public Customer findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Customer cu = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{call find_customer_by_id(?)}");
            // set giá trị cho tham số truyển vào
            callSt.setInt(1, id);
            boolean hasData = callSt.execute();
            if (hasData) {
                ResultSet rs = callSt.getResultSet();
                if (rs.next()) {
                    cu = new Customer();
                    cu.setId(rs.getInt("id"));
                    cu.setName(rs.getString("name"));
                    cu.setPhone(rs.getString("phone"));
                    cu.setEmail(rs.getString("email"));
                    cu.setAddress(rs.getString("address"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return cu;
    }

    @Override
    public void insert(Customer cs) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("call add_customer(?, ?, ?, ?)");
            callSt.setString(1, cs.getName());
            callSt.setString(2, cs.getPhone());
            callSt.setString(3, cs.getEmail());
            callSt.setString(4, cs.getAddress());
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
    }

    @Override
    public void update(Customer cs) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("call update_customer(?, ?, ?, ?, ?)");
            callSt.setInt(1, cs.getId());
            callSt.setString(2, cs.getName());
            callSt.setString(3, cs.getPhone());
            callSt.setString(4, cs.getEmail());
            callSt.setString(5, cs.getAddress());
            callSt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
    }

    @Override
    public void delete(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("call delete_customer(?)");
            callSt.setInt(1, id);
            callSt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
    }


}

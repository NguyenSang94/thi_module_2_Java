package dao.impl;

import dao.ProductDAO;
import model.Product;
import utils.DBUtil;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public List<Product> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> listProduct = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{call get_all_product()}");
            boolean hasData = callSt.execute();
            if (hasData) {
                listProduct = new ArrayList<>();
                ResultSet rs = callSt.getResultSet();
                while (rs.next()) {
                    Product pr = new Product();
                    pr.setId(rs.getInt("id"));
                    pr.setName(rs.getString("name"));
                    pr.setBrand(rs.getString("brand"));
                    pr.setPrice(rs.getDouble("price"));
                    pr.setStock(rs.getInt("stock"));

                    listProduct.add(pr);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listProduct;
    }

    @Override
    public Product findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Product pr = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{call find_product_by_id(?)}");
            callSt.setInt(1, id);
            boolean hasData = callSt.execute();
            if (hasData) {
                ResultSet rs = callSt.getResultSet();
                if (rs.next()) {
                    pr = new Product();
                    pr.setId(rs.getInt("id"));
                    pr.setName(rs.getString("name"));
                    pr.setBrand(rs.getString("brand"));
                    pr.setPrice(rs.getDouble("price"));
                    pr.setStock(rs.getInt("stock"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return pr;
    }

    @Override
    public List<Product> searchByBrand(String keyword) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> products = new ArrayList<>();
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{call search_product_by_brand(?)}");
            callSt.setString(1, keyword);
            boolean hasData = callSt.execute();
            if (hasData) {
                ResultSet rs = callSt.getResultSet();
                while (rs.next()) {
                    Product pr = new Product();
                    pr.setId(rs.getInt("id"));
                    pr.setName(rs.getString("name"));
                    pr.setBrand(rs.getString("brand"));
                    pr.setPrice(rs.getDouble("price"));
                    pr.setId(rs.getInt("stock"));
                    products.add(pr);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return products;


    }

    @Override
    public List<Product> searchByPriceRange(double min, double max) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> listProduct = new ArrayList<>();
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{call search_product_by_price_range(?, ?)}");
            callSt.setBigDecimal(1, BigDecimal.valueOf(min));
            callSt.setBigDecimal(2, BigDecimal.valueOf(max));
            boolean hasData = callSt.execute();
            if (hasData) {
                ResultSet rs = callSt.getResultSet();
                while (rs.next()) {
                    Product p = new Product();
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setBrand(rs.getString("brand"));
                    p.setPrice(rs.getDouble("price"));
                    p.setStock(rs.getInt("stock"));
                    listProduct.add(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return listProduct;
    }

    @Override
    public List<Product> searchByNameAvailable(String key) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> listProducts = new ArrayList<>();
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{ call search_product_by_name_and_stock(?)}");
            callSt.setString(1, key);
            boolean hasData = callSt.execute();
            if (hasData) {
                ResultSet rs = callSt.getResultSet();
                while (rs.next()) {
                    Product pr = new Product();
                    pr.setId(rs.getInt("id"));
                    pr.setName(rs.getString("name"));
                    pr.setBrand(rs.getString("brand"));
                    pr.setPrice(rs.getDouble("price"));
                    pr.setStock(rs.getInt("stock"));
                    listProducts.add(pr);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return listProducts;
    }

    @Override
    public void insert(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("call add_product(?, ?, ?, ?)");
            // set giá trị cho tham số truyền vào
            callSt.setString(1, product.getName());
            callSt.setString(2, product.getBrand());
            callSt.setBigDecimal(3, BigDecimal.valueOf(product.getPrice()));
            callSt.setInt(4, product.getStock());
            callSt.executeUpdate();// tham số truyển vào

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
    }

    @Override
    public void update(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("call update_product(?, ?, ?, ?, ?)");
            callSt.setInt(1, product.getId());
            callSt.setString(2, product.getName());
            callSt.setString(3, product.getBrand());
            callSt.setDouble(4, product.getPrice());
            callSt.setInt(5, product.getId());
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
            callSt = conn.prepareCall("call delete_product(?)");
            callSt.setInt(1, id);
            callSt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
    }
}

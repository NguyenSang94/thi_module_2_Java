package business.impl;

import business.AdminService;
import dao.AdminDAO;
import dao.impl.AdminDAOImpl;

public class AdminServiceImpl implements AdminService {
    private final AdminDAO adminDAO = new AdminDAOImpl();

    @Override
    public boolean login(String username, String password) {
        return adminDAO.login(username, password);
    }
}

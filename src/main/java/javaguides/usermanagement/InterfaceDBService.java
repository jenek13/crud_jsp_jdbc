package javaguides.usermanagement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface InterfaceDBService {

    void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException;
    void showEditForm(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException;
    void updateUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException;
    void deleteUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException;
    void listUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException;
}

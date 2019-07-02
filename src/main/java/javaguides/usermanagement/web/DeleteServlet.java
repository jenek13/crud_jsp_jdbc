package javaguides.usermanagement.web;

import javaguides.usermanagement.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    private final DBService dbService;

    public DeleteServlet(DBService dbService) {
        this.dbService = dbService;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)//в инсерте срабоатывает
            throws ServletException, IOException {
                try {
                    dbService.deleteUser(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                response.sendRedirect("list");//вернуть на страницу со списком юзеров сразу после удаления
    }

}

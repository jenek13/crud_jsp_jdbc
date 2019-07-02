package javaguides.usermanagement.web;

import javaguides.usermanagement.DBService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/list")
public class ReadServlet extends HttpServlet {
    private final DBService dbService;

    public ReadServlet(DBService dbService) {
        this.dbService = dbService;
    }


        public void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
            try {
                dbService.listUser(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");//его задача принять какой то юри в данном случае user-list.jsp
            dispatcher.forward(request, response);
        }
}

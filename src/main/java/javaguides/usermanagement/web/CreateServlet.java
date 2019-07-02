package javaguides.usermanagement.web;

import javaguides.usermanagement.DBService;
import javaguides.usermanagement.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/insert")
public class CreateServlet extends HttpServlet {

    private final DBService dbService;

    public CreateServlet(DBService dbService) {
        this.dbService = dbService;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)//в инсерте срабоатывает
            throws ServletException, IOException {
                RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");//сразу пернаправляет  на эту джспи при вызове этого мтода
                dispatcher.forward(request, response);
                try {
                    dbService.insertUser(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                response.sendRedirect("list");//после того ка кзполнил поля тебя перкинет на эту джспи.
    }


}

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

@WebServlet("/edit")
public class UpdateServlet extends HttpServlet {
    private final DBService dbService;

    public UpdateServlet(DBService dbService) {
        this.dbService = dbService;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)//в инсерте срабоатывает
            throws ServletException, IOException {
                RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");//перенапралем на джспи
                //request.setAttribute("user", existingUser);//void setAttribute(java.lang.String name, java.lang.Object o)
                //1-ый парметр имя атрибута, творой сами данные которые хотим поменять . т е если хотим поменять на Света то тут Света и писать
                //но тут мы ничего нового не пишем т к тот мтеод всего лишь показываем форму где будешь вводить изменения
                dispatcher.forward(request, response);
                try {
                    dbService.updateUser(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                response.sendRedirect("list");
    }


}

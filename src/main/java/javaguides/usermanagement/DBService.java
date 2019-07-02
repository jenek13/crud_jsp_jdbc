package javaguides.usermanagement;

import javaguides.usermanagement.dao.UserDAO;
import javaguides.usermanagement.model.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBService implements InterfaceDBService{

    public final UserDAO userDAO;

    public DBService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }



    /*public void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");//сразу пернаправляет  на эту джспи при вызове этого мтода
        dispatcher.forward(request, response);
        //этот мтеод чисто направляет на форму едита.не знаю в каком рид или апдтейт сервлете он должен вызываться
    }*/

    public void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        User newUser = new User(name, age);
        userDAO.insertDAO(newUser);
        //response.sendRedirect("list");//после того ка кзполнил поля тебя перкинет на эту джспи.
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));//запомнаем айди пришедший в реквесте от клиента
        User existingUser = userDAO.selectUser(id);//вызов метода в дао, ищем его запросом через метод дао
        //RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");//перенапралем на джспи
        request.setAttribute("user", existingUser);//void setAttribute(java.lang.String name, java.lang.Object o)
        //1-ый парметр имя атрибута, творой сами данные которые хотим поменять . т е если хотим поменять на Света то тут Света и писать
        //но тут мы ничего нового не пишем т к тот мтеод всего лишь показываем форму где будешь вводить изменения
        //dispatcher.forward(request, response);
//этот мтеод чисто направляет на форму едита.не знаю в каком рид или апдтейт сервлете он должен вызываться
    }

    public void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        User user = new User(id, name, age);
        userDAO.updateModel(user);//вызов метода в дао
        //response.sendRedirect("list");
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteModel(id);//вызов метода в дао
        //response.sendRedirect("list");//вернуть на страницу со списком юзеров сразу после удаления
    }

    public void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDAO.selectAllUsers();//вызов метода в дао,сналча достаем данные из базы и пишем их в List
        request.setAttribute("listUser", listUser);
    }
        //RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");//его задача принять какой то юри в данном случае user-list.jsp
        //dispatcher.forward(request, response);//с помощью мтеода forward перенаправить запрос с метода listUser сервлета в джспи user-list.jsp
        //т е мы обратились к сервлету к мтеоду листюзер а фактически нас сразу пернправили на джспи user-list jsp


}

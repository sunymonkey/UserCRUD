package pl.coderslab.user;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserEdit", value = "/user/edit")
public class UserEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String id = request.getParameter("id");

        try {
            int idUser = Integer.parseInt(id);
            UserDao userDao = new UserDao();
            User user = userDao.read(idUser);

            request.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/users/edit.jsp").forward(request, response);
        } catch (NumberFormatException e){
            response.getWriter().append("Błąd wartości !");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String id = request.getParameter("id");
        String userName = request.getParameter("name");
        String userMail = request.getParameter("email");
        String userPassword = request.getParameter("password");

        try {
            int userId = Integer.parseInt(id);
            User user = new User(userId, userMail, userName, userPassword);

            UserDao userDao = new UserDao();
            userDao.update(user);
            response.sendRedirect(request.getContextPath() + "/user/list");
        } catch (NumberFormatException e) {
        }
    }
}

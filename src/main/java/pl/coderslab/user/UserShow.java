package pl.coderslab.user;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserShow", value = "/user/show")
public class UserShow extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String userId = request.getParameter("id");

        try {
            int id = Integer.parseInt(userId);
            UserDao userDao = new UserDao();
            User user = userDao.read(id);
            request.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/users/show.jsp").forward(request, response);
        } catch (NumberFormatException e) {

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

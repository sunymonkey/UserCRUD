package pl.coderslab.user;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserAdd", value = "/user/add")
public class UserAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/users/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(name == null || email == null || password == null ||
        name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            response.getWriter().append("404 error wrong date ");
        } else {
            UserDao userDao = new UserDao();
            userDao.create(new User(name, email, password));

            response.sendRedirect(request.getContextPath() + "/user/list");
        }

    }
}

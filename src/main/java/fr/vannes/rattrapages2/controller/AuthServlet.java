package fr.vannes.rattrapages2.controller;

import fr.vannes.rattrapages2.dao.DAOFactory;
import fr.vannes.rattrapages2.dao.UserDAOImpl;
import fr.vannes.rattrapages2.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet({"/auth"})
public class AuthServlet extends HttpServlet {
    private DAOFactory daoFactory;
    private UserDAOImpl userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        daoFactory = DAOFactory.getInstance();
        try {
            userDAO = daoFactory.getUserDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logout = request.getParameter("logout");
        System.out.print("logout:" + logout);

        if (logout != null && logout.equals("true")) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            RequestDispatcher rd = request.getRequestDispatcher("/auth.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/auth.jsp");
            rd.forward(request, response);
        }
    }

    // Only handle POST request for authentication
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String login = request.getParameter("inputLog");
        String password = request.getParameter("inputPwd");

        // Vérifiez si l'utilisateur existe
        User user = userDAO.read(login);

        if (user != null && userDAO.authentification(login, password)) {
            int role = user.getRole();
            System.out.println("login-pwd:" + login + " [password not logged for security]");
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
            session.setAttribute("role", role); // Enregistrez le rôle dans la session
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        } else {
            System.out.println("Authentication failed");
            request.setAttribute("errorMessage", "Login ou Mot de passe incorrect");
            RequestDispatcher rd = request.getRequestDispatcher("/auth.jsp");
            rd.forward(request, response);
        }
    }
}

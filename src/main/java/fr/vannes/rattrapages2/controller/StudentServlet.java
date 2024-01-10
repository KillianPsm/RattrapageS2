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
import java.sql.SQLException;
import java.util.List;

@WebServlet({"/student"})
public class StudentServlet extends HttpServlet {
    private UserDAOImpl userDAO;

    // Initialisez la servlet
    @Override
    public void init() throws ServletException {
        super.init();
        DAOFactory daoFactory = DAOFactory.getInstance();

        try {
            userDAO = daoFactory.getUserDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int role = (int) session.getAttribute("role");

        if (role == 2) { // Vérifiez si l'utilisateur est un professeur
            String action = request.getParameter("action");

            if ("listCourse".equals(action)) {
                // Logique pour afficher la liste des étudiants
                List<User> students = userDAO.listAllStudents();
                request.setAttribute("students", students);
                RequestDispatcher rd = request.getRequestDispatcher("/listCourse.jsp");
                rd.forward(request, response);
            }
        }
    }
}

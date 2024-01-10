package fr.vannes.rattrapages2.controller;

import fr.vannes.rattrapages2.dao.CourseDAOImpl;
import fr.vannes.rattrapages2.dao.DAOFactory;
import fr.vannes.rattrapages2.dao.UserDAOImpl;
import fr.vannes.rattrapages2.model.Course;
import fr.vannes.rattrapages2.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {
    private UserDAOImpl userDAO;
    private CourseDAOImpl courseDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        DAOFactory daoFactory = DAOFactory.getInstance();

        try {
            userDAO = daoFactory.getUserDAO();
            courseDAO = daoFactory.getCourseDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int role = (int) session.getAttribute("role");

        // Verification du role de l'utilisateur
        if (role == 1) {
            String action = request.getParameter("action");

            if ("listStudent".equals(action)) {
                // Affichage de la liste des etudiants
                List<User> students = userDAO.listAllStudents();
                System.out.println("Number of students: " + students.size());
                request.setAttribute("students", students);
                RequestDispatcher rd = request.getRequestDispatcher("/listStudent.jsp");
                rd.forward(request, response);

            } else if ("listCourse".equals(action)) {
                // Affichage de la liste des cours
                List<Course> courses = courseDAO.listAllCourses();
                System.out.println("Number of courses: " + courses.size());
                request.setAttribute("courses", courses);
                RequestDispatcher rd = request.getRequestDispatcher("/listCourse.jsp");
                rd.forward(request, response);

            } else if ("addStudent".equals(action)) {
                // Affichage de la page d'ajout d'etudiant
                RequestDispatcher rd = request.getRequestDispatcher("/addStudent.jsp");
                rd.forward(request, response);

            } else if ("addCourse".equals(action)) {
                // Affichage de la page d'ajout de cours
                RequestDispatcher rd = request.getRequestDispatcher("/addCourse.jsp");
                rd.forward(request, response);

            } else if ("deleteStudent".equals(action)) {
                // Recuperation de l'ID de l'etudiant a supprimer
                int studentId = Integer.parseInt(request.getParameter("id"));
                // Suppression de l'etudiant de la base de donnees
                userDAO.delete(studentId);
                // Redirection vers la liste des etudiants apres la suppression
                response.sendRedirect(request.getContextPath() + "/teacher?action=listStudent");

            } else if ("deleteCourse".equals(action)) {
                // Recuperation de l'ID du cours a supprimer
                int courseId = Integer.parseInt(request.getParameter("id"));
                // Suppression du cours de la base de donnees
                courseDAO.delete(courseId);
                // Redirection vers la liste des cours apres la suppression
                response.sendRedirect(request.getContextPath() + "/teacher?action=listCourse");

            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("addStudent".equals(action)) {
            // Recuperation des parametres du formulaire
            String lastName = request.getParameter("inputLastName");
            String firstName = request.getParameter("inputFirstName");
            Date birthdate = Date.valueOf(request.getParameter("inputBirthdate"));
            String className = request.getParameter("inputClassName");
            String pwd = request.getParameter("inputPwd");

            // Creation d'un nouveau User avec les informations de l'etudiant
            User newStudent = new User(lastName, firstName, birthdate, className, pwd);

            // Attribution du role "eleve"
            newStudent.setRole(2);

            // Ajout de l'etudiant a la bdd
            userDAO.create(newStudent);

            // Redirection vers la liste des etudiants apres l'ajout
            response.sendRedirect(request.getContextPath() + "/teacher?action=listStudent");

        } else if ("addCourse".equals(action)) {
            // Recuperation des parametres du formulaire
            String code = request.getParameter("inputCode");
            String label = request.getParameter("inputLabel");

            // Creation d'un nouveau Course
            Course newCourse = new Course(code, label);

            // Ajout du cours a la bdd
            courseDAO.create(newCourse);

            // Redirection vers la liste des cours apres l'ajout
            response.sendRedirect(request.getContextPath() + "/teacher?action=listCourse");

        }
    }
}

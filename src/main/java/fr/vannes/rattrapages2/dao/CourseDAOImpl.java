package fr.vannes.rattrapages2.dao;

import fr.vannes.rattrapages2.model.Course;
import fr.vannes.rattrapages2.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    private final String TABLE = "course";
    private final String ID = "id";
    private final String CODE = "code";
    private final String LABEL = "label";
    protected Connection conn;

    public CourseDAOImpl(DAOFactory daoFactory) {
        try {
            this.conn = daoFactory.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Connexion à la bdd impossible !");
        }
    }

    @Override
    public void create(Course course) {
        try {
            String q = "INSERT INTO " + TABLE + " (" + CODE + ", " + LABEL + ") VALUES (?,?)";
            PreparedStatement pst = this.conn.prepareStatement(q);
            pst.setString(1, course.getCode());
            pst.setString(2, course.getLabel());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course read(int id) {
        try {
            String query = "SELECT * FROM " + TABLE + " WHERE " + ID + "=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();

            Course course = new Course();
            if (resultSet.next()) {
                course.setId(resultSet.getInt(ID));
                course.setCode(resultSet.getString(CODE));
                course.setLabel(resultSet.getString(LABEL));
            }

            resultSet.close();
            pst.close();
            return course;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Course> listAllCourses() {
        List<Course> courses = new ArrayList<>();

        try {
            String query = "SELECT * FROM " + TABLE;
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getInt(ID));
                course.setCode(resultSet.getString(CODE));
                course.setLabel(resultSet.getString(LABEL));

                courses.add(course);
            }

            resultSet.close();
            pst.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return courses;
    }

    @Override
    public void update(Course course) {
        try {
            String query = "UPDATE " + TABLE + " SET " +
                    CODE + "=?," +
                    LABEL + "=?," +
                    "WHERE " + ID + "=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, course.getCode());
            pst.setString(2, course.getLabel());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            String query = "DELETE FROM " + TABLE + " WHERE " + ID + "=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

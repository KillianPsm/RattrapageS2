package fr.vannes.rattrapages2.dao;

import fr.vannes.rattrapages2.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private final String TABLE = "user";
    private final String ID = "id";
    private final String LASTNAME = "lastName";
    private final String FIRSTNAME = "firstName";
    private final String BIRTHDATE = "birthDate";
    private final String CLASSNAME = "className";
    private final String PASSWORD = "password";
    private final String ROLE = "role_id";
    protected Connection conn;

    public UserDAOImpl(DAOFactory daoFactory) {
        try {
            this.conn = daoFactory.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Connexion à la bdd impossible !");
        }
    }

    public boolean authentification(String log, String pwd) {
        try {
            String query = "SELECT * FROM " + TABLE + " WHERE " + LASTNAME + "=? AND " + PASSWORD + "=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, log);
            pst.setString(2, pwd);

            ResultSet resultSet = pst.executeQuery();
            boolean auth = resultSet.next();

            resultSet.close();
            pst.close();
            return auth;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void create(User user) {
        try {
            String q = "INSERT INTO " + TABLE + " (" + FIRSTNAME + ", " + LASTNAME + ", " + BIRTHDATE + ", " + CLASSNAME + ", " + PASSWORD + ", " + ROLE + ") VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = this.conn.prepareStatement(q);
            pst.setString(1, user.getLastname());
            pst.setString(2, user.getFirstname());
            pst.setDate(3, user.getBirthdate());
            pst.setString(4, user.getClassName());
            pst.setString(5, user.getPwd());
            pst.setInt(6, user.getRole());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User read(String log) {
        try {
            String query = "SELECT * FROM " + TABLE + " WHERE " + LASTNAME + "=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, log);
            ResultSet resultSet = pst.executeQuery();

            User user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(ID));
                user.setLastname(resultSet.getString(LASTNAME));
                user.setFirstname(resultSet.getString(FIRSTNAME));
                user.setBirthdate(resultSet.getDate(BIRTHDATE));
                user.setClassName(resultSet.getString(CLASSNAME));
                user.setPwd(resultSet.getString(PASSWORD));
                user.setRole(resultSet.getInt(ROLE));
            }

            resultSet.close();
            pst.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> listAllStudents() {
        List<User> students = new ArrayList<>();

        try {
            String query = "SELECT * FROM " + TABLE + " WHERE " + ROLE + "=2";
            PreparedStatement pst = conn.prepareStatement(query);

            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                User student = new User();
                student.setId(resultSet.getInt(ID));
                student.setLastname(resultSet.getString(LASTNAME));
                student.setFirstname(resultSet.getString(FIRSTNAME));
                student.setBirthdate(resultSet.getDate(BIRTHDATE));
                student.setClassName(resultSet.getString(CLASSNAME));
                student.setPwd(resultSet.getString(PASSWORD));
                student.setRole(resultSet.getInt(ROLE));

                students.add(student);
            }

            resultSet.close();
            pst.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public void update(User user) {
        try {
            String query = "UPDATE " + TABLE + " SET " +
                    LASTNAME + "=?," +
                    FIRSTNAME + "=?," +
                    BIRTHDATE + "=?," +
                    CLASSNAME + "=? " +
                    "WHERE " + ID + "=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, user.getLastname());
            pst.setString(2, user.getFirstname());
            pst.setDate(3, user.getBirthdate());
            pst.setString(4, user.getClassName());
            pst.setInt(5, user.getId());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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

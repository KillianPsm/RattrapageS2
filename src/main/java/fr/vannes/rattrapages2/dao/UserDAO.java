package fr.vannes.rattrapages2.dao;

import fr.vannes.rattrapages2.model.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserDAO {
    boolean authentification(String log, String pwd) throws NoSuchAlgorithmException;

    void create(User user);

    User read(int id);

    User readByName(String log);

    List<User> listAllStudents();

    void update(User user);

    void delete(int id);
}

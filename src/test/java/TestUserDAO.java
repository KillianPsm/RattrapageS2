import fr.vannes.rattrapages2.dao.DAOFactory;
import fr.vannes.rattrapages2.dao.UserDAOImpl;
import fr.vannes.rattrapages2.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserDAO {
    private Connection conn;
    private static UserDAOImpl userDAO;

    @BeforeAll
    public static void setUp() throws SQLException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        try {
            userDAO = daoFactory.getUserDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCreate() {
        User user = new User();
        user.setLastname("Carrey");
        user.setFirstname("Jim");
        user.setBirthdate(Date.valueOf("2000-01-01"));
        user.setClassName("Premiere");
        user.setPwd("1234");
        user.setRole(2);

        userDAO.create(user);

        User user2 = userDAO.readByName(user.getLastname());
        assertEquals(user.getLastname(), user2.getLastname());
        assertEquals(user.getFirstname(), user2.getFirstname());
        assertEquals(user.getBirthdate(), user2.getBirthdate());
        assertEquals(user.getClassName(), user2.getClassName());
        assertEquals(user.getPwd(), user2.getPwd());
    }

}

import fr.vannes.rattrapages2.dao.DAOFactory;
import fr.vannes.rattrapages2.dao.UserDAOImpl;
import fr.vannes.rattrapages2.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class TestUserDAO {
    private static UserDAOImpl userDAO;
    private static User testUser;

    @BeforeEach
    public void setUp() throws SQLException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        try {
            userDAO = daoFactory.getUserDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    public static void methodeAppeleeApresChaqueTest() {
        User u3 = userDAO.readByName("LastNameTest");
        userDAO.delete(u3.getId());
        u3 = null;
    }

    @BeforeAll
    public static void methodeAppeleeAvantTousLesTests() throws NoSuchAlgorithmException {
        testUser = new User("LastNameTest", "FirstNameTest", Date.valueOf("2001-01-12"), "ClasseTest", "MotDePasseTest");
        testUser.setRole(2);
    }

    @Test
    public void testCreate() {
        // Création d'un nouvel utilisateur
        userDAO.create(testUser);

        User testUser2 = userDAO.readByName("LastNameTest");
        assertEquals(testUser.getLastname(), testUser2.getLastname());
        assertEquals(testUser.getFirstname(), testUser2.getFirstname());
        assertEquals(testUser.getBirthdate(), testUser2.getBirthdate());
        assertEquals(testUser.getClassName(), testUser2.getClassName());
        assertEquals(testUser.getPwd(), testUser2.getPwd());
        assertEquals(testUser.getRole(), testUser2.getRole());

        System.out.println("User créé: " + testUser2.toString());
        System.out.println("====================================");
    }

    @Test
    public void testReadByName() {
        User testUser2 = userDAO.readByName("LastNameTest");

        assertEquals(testUser.getLastname(), testUser2.getLastname());
        assertEquals(testUser.getFirstname(), testUser2.getFirstname());
        assertEquals(testUser.getBirthdate(), testUser2.getBirthdate());
        assertEquals(testUser.getClassName(), testUser2.getClassName());
        assertEquals(testUser.getPwd(), testUser2.getPwd());
        assertEquals(testUser.getRole(), testUser2.getRole());

        System.out.println("User lu: " + testUser2.toString());
        System.out.println("====================================");
    }

    @Test
    public void testUpdate() {
        // Créez un utilisateur pour la mise à jour
        userDAO.create(testUser);

        System.out.println("User créé pour mise à jour: " + testUser.toString());

        // Récupérez l'ID de l'utilisateur créé
        int userId = userDAO.readByName("LastNameTest").getId();

        // Modifiez les détails de l'utilisateur
        testUser.setId(userId);
        testUser.setFirstname("UpdatedFirstName");
        testUser.setBirthdate(Date.valueOf("1985-03-15"));
        testUser.setClassName("UpdatedClass");
        testUser.setPwd("UpdatedPWD");
        testUser.setRole(1);

        // Mise à jour de l'utilisateur dans la base de données
        userDAO.update(testUser);

        // Lisez l'utilisateur mis à jour depuis la base de données
        User updatedUser = userDAO.read(userId);

        // Vérifiez si l'utilisateur mis à jour existe
        assertNotNull(updatedUser);

        // Assertions pour vérifier que les détails ont été mis à jour correctement
        assertEquals(testUser.getId(), updatedUser.getId());
        assertEquals(testUser.getLastname(), updatedUser.getLastname());
        assertEquals(testUser.getFirstname(), updatedUser.getFirstname());
        assertEquals(testUser.getBirthdate(), updatedUser.getBirthdate());
        assertEquals(testUser.getClassName(), updatedUser.getClassName());
        assertEquals(testUser.getPwd(), updatedUser.getPwd());
        assertEquals(testUser.getRole(), updatedUser.getRole());

        System.out.println("User mis à jour: " + updatedUser.toString());
    }

    @Test
    public void testDelete() {
        // Création d'un nouvel utilisateur
        userDAO.create(testUser);

        System.out.println("User créé pour suppression: " + testUser.toString());

        // Récupération de l'ID de l'utilisateur créé
        int userId = userDAO.readByName("LastNameTest").getId();

        // Suppression de l'utilisateur
        userDAO.delete(userId);

        // Tentative de lecture de l'utilisateur après suppression
        User deletedUser = userDAO.read(userId);

        // Vérification que l'utilisateur n'existe plus
        assertNull(deletedUser, "L'utilisateur devrait être supprimé");

        System.out.println("User supprimé: " + deletedUser);
        System.out.println("====================================");
    }
}

import fr.vannes.rattrapages2.dao.CourseDAOImpl;
import fr.vannes.rattrapages2.dao.DAOFactory;
import fr.vannes.rattrapages2.model.Course;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestCourseDAO {
    private static CourseDAOImpl courseDAO;
    private static Course testCourse;

    @BeforeEach
    public void setUp() throws SQLException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        try {
            courseDAO = daoFactory.getCourseDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    public static void methodeAppeleeApresChaqueTest() {
        Course c3 = courseDAO.readByCode("CodeTest");
        courseDAO.delete(c3.getId());
        c3 = null;
    }

    @BeforeAll
    public static void methodeAppeleeAvantTousLesTests() throws NoSuchAlgorithmException {
        testCourse = new Course("CodeTest", "LabelTest");
    }

    @Test
    public void testCreate() {
        // Création d'un nouvel utilisateur
        courseDAO.create(testCourse);

        Course testCourse2 = courseDAO.readByCode("CodeTest");
        assertEquals(testCourse.getCode(), testCourse2.getCode());
        assertEquals(testCourse.getLabel(), testCourse2.getLabel());

        System.out.println("Cours créé: " + testCourse2.toString());
        System.out.println("====================================");
    }

    @Test
    public void testReadByCode() {
        Course testCourse2 = courseDAO.readByCode("CodeTest");

        assertEquals(testCourse.getCode(), testCourse2.getCode());
        assertEquals(testCourse.getLabel(), testCourse2.getLabel());

        System.out.println("Cours lu: " + testCourse2.toString());
        System.out.println("====================================");
    }

    @Test
    public void testDelete() {
        // Création d'un nouvel utilisateur
        courseDAO.create(testCourse);

        System.out.println("Cours créé pour suppression: " + testCourse.toString());

        // Récupération de l'ID de l'utilisateur créé
        int courseId = courseDAO.readByCode("CodeTest").getId();

        // Suppression de l'utilisateur
        courseDAO.delete(courseId);

        // Tentative de lecture de l'utilisateur après suppression
        Course deletedCourse = courseDAO.read(courseId);

        // Vérification que l'utilisateur n'existe plus
        assertNull(deletedCourse, "Le cours devrait être supprimé");

        System.out.println("Cours supprimé: " + deletedCourse);
        System.out.println("====================================");
    }
}

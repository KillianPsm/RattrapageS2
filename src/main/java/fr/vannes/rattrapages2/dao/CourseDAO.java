package fr.vannes.rattrapages2.dao;

import fr.vannes.rattrapages2.model.Course;

import java.util.List;

public interface CourseDAO {
    void create(Course course);

    Course read(int id);

    Course readByCode(String code);

    List<Course> listAllCourses();

    void update(Course course);

    void delete(int id);
}

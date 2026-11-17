package dao.interfaces;

import model.SchemeCourse;
import datastructures.linear.ArrayList;
import model.Course;
import model.CourseScheme;

/**
 * I'm still not sure if we'd need the DAO for this associative entity.
 * @author ShaheerZK
 */
public interface SchemeCourseDAO 
{
    /**
     * Why'd you need to look at the documentation to determine what this method does?!
     * @param schemeCourse 
     */
    public void insertSchemeCourse(SchemeCourse schemeCourse);
    
    /**
     * Should return all the courses belonging to a particular scheme.
     * @param courseScheme
     * @return 
     */
    public ArrayList<Course> getAllCoursesInScheme(CourseScheme courseScheme);
    
    /**
     * It should be returning all the schemes offering the provided course.
     * @param course
     * @return 
     */
    public ArrayList<CourseScheme> getAllSchemesWithCourse(Course course);
}

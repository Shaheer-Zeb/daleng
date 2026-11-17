package dao.interfaces;

import datastructures.linear.ArrayList;
import model.Course;

/**
 *
 * @author ShaheerZK
 */
public interface CourseDAO 
{
    /**
     * Yoink.
     * @param course 
     */
    public void insertCourse(Course course);
    
    /**
     * Searches the database with the id, if found returns the object, null otherwise.
     * @param id
     * @return 
     */
    public Course getCourseById(int id);
    
    /**
     * Takes in the course code (e.g. CSC699) and returns its object if found in the DB, null otherwise.
     * @param code
     * @return 
     */
    public Course getCourseByCode(String code);
    
    /**
     * Searches the DB with the provided course name (e.g. OOP) and returns the object if found.
     * @param name
     * @return 
     */
    public Course getCourseByName(String name);
    
    /**
     * Returns all the courses in the DB.
     * @return 
     */
    public ArrayList<Course> getAllCourses();
    
    /**
     * Returns all the courses having equaling the provided credit hours.
     * @param creditHours
     * @return 
     */
    public ArrayList<Course> getCoursesByCreditHours(double creditHours);
}

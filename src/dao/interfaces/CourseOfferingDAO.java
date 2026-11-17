package dao.interfaces;

import datastructures.linear.ArrayList;
import model.AcademicTerm;
import model.Course;
import model.CourseOffering;
import model.Teacher;

/**
 *
 * @author ShaheerZK
 */
public interface CourseOfferingDAO
{
    /**
     * Inserts a new offering for a course.
     * @param offering The CourseOffering object.
     */
    public void insertCourseOffering(CourseOffering offering);
    
    /**
     * Returns the CourseOffering by id if found, null otherwise.
     * @param id
     * @return 
     */
    public CourseOffering getCourseOfferingById(int id);
    
    /**
     * Returns all course offerings of a particular course.
     * @param course
     * @return 
     */
    public ArrayList<CourseOffering> getCourseOfferingsByCourse(Course course);
    
    /**
     * Returns all the CourseOfferings taught by a particular teacher.
     * @param teacher
     * @return 
     */
    public ArrayList<CourseOffering> getCourseOfferingsByTeacher(Teacher teacher);
    
    /**
     * Return all the CourseOfferings in a belonging to the given academic term.
     * @param term
     * @return 
     */
    public ArrayList<CourseOffering> getAllCoursesByTerm(AcademicTerm term);
    
    /**
     * Returns all the course offerings.
     * @return 
     */
    public ArrayList<CourseOffering> getAllCourseOfferings();
}

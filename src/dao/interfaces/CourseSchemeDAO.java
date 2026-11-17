package dao.interfaces;

import datastructures.linear.ArrayList;
import model.AcademicTerm;
import model.CourseScheme;
import model.Program;

/**
 *
 * @author ShaheerZK
 */
public interface CourseSchemeDAO 
{
    /**
     * Inserts a new CourseScheme in the table of course.
     * @param courseScheme 
     */
    public void insertCourseScheme(CourseScheme courseScheme);
    
    /**
     * Isn't it avowed by name what it does?
     * @param scheme_id
     * @return Returns the CourseScheme object if found, null otherwise.
     */
    public CourseScheme getCourseSchemeById(int scheme_id);
    
    /**
     * Returns the course scheme by id, duh.
     * @param name
     * @return Returns the CourseScheme object if found, null otherwise.
     */
    public CourseScheme getCourseSchemeByName(String name);
    
    /**
     * Gets all the CourseSchemes that have belong to the given academic term.
     * @param academicTerm
     * @return 
     */
    public ArrayList<CourseScheme> getCourseSchemesByTerm(AcademicTerm academicTerm);
    
    /**
     * Returns all the course schemes for a particular program.
     * @param program
     * @return 
     */
    public ArrayList<CourseScheme> getAllCourseSchemesByProgram(Program program);
    
    /**
     * Returns all the course schemes.
     * @return 
     */
    public ArrayList<CourseScheme> getAllCourseSchemes();
}

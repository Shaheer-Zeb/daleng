package dao.interfaces;

import datastructures.linear.ArrayList;
import model.Course;
import model.SelectedCourse;
import model.StudentSelection;

/**
 *
 * @author ShaheerZK
 */
public interface SelectedCourseDAO 
{
    /**
     * Inserts a new course selection.
     * @param selectedCourse 
     */
    public void insertSelectedCourse(SelectedCourse selectedCourse);
    
    /**
     * Yoink.
     * @param id
     * @return 
     */
    public SelectedCourse getSelectedCourseById(int id);
    
    /**
     * Returns all the courses selected by a student in a StudentSelection.
     * @param selection
     * @return 
     */
    public ArrayList<SelectedCourse> getAllSelectedCourseByStudentSelection(StudentSelection selection);
    
    /**
     * Returns all the SelectedCourses of a particular course. It essentially means that how many times, a course was selected and returns them.
     * @param course
     * @return 
     */
    public ArrayList<SelectedCourse> getAllSelectedCoursesByCourse(Course course);
    
    /**
     * Returns all the SelectedCourses.
     * @return 
     */
    public ArrayList<SelectedCourse> getAllSelectedCourses();
    
}

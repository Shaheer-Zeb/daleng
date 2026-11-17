package dao.interfaces;

import datastructures.linear.ArrayList;
import model.AcademicTerm;
import model.Student;
import model.StudentSelection;

/**
 *
 * @author ShaheerZK
 */
public interface StudentSelectionDAO 
{
    /**
     * Inserts a new selection of a student.
     * @param selction 
     */     
    public void insertStudentSelection(StudentSelection selection);
    
    /**
     * Returns a selection based on its primary key. 
     * @param id
     * @return 
     */
    public StudentSelection getSelectionById(int id);
    
    /**
     * Returns all the selections of a student across all the terms.
     * @param student
     * @return 
     */
    public ArrayList<StudentSelection> getAllSelectionsOfStudent(Student student);
    
    /**
     * Gets all the student selections that the student has made in the provided term.
     * @param student
     * @param term
     * @return 
     */
    public ArrayList<StudentSelection> getAllSelectionsOfStudentInTerm(Student student, AcademicTerm term);
    
    /**
     * Returns all the selections in the given term.
     * @param term
     * @return 
     */
    public ArrayList<StudentSelection> getAllSelectionsInTerm(AcademicTerm term);
    
    /**
     * Returns all the selections in the database.
     * @return 
     */
    public ArrayList<StudentSelection> getAllSelections();
}

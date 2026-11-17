package dao.interfaces;

import datastructures.linear.ArrayList;
import model.Program;
import model.Student;

/**
 *
 * @author ShaheerZK
 */
public interface StudentDAO 
{
    /**
     * It does what it says.
     * @param student 
     */
    public void insertStudent(Student student);
    
    /**
     * Returns the student based on the id, null if id wasn't found.
     * @param id
     * @return 
     */
    public Student getStudentById(int id);
    
    /**
     * Returns the student based on the registration number, null if id wasn't found.
     * @param regoNo
     * @return 
     */
    public Student getStudentByRegNo(String regoNo);
    
    /**
     * Returns the all students with the provided name.
     * @param name
     * @return 
     */
    public ArrayList<Student> getStudentsByName(String name);
    
    /**
     * Returns all the students in the given program.
     * @param program
     * @return 
     */
    public ArrayList<Student> getAllStudentsInProgram(Program program);
    
    /**
     * Returns all the students belonging to a particular cohort. Cohort is the term in which they were enrolled in the University. For example, I FA25-BCS-019 was enrolled in FA25 cohort. 
     * @param name
     * @return 
     */
    public ArrayList<Student> getAllStudentsOfCohort(String name);
    
    /**
     * Returns every student in the DB.
     * @return 
     */
    public ArrayList<Student> getAllStudents();
}

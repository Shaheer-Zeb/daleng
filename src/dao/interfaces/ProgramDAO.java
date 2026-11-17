package dao.interfaces;

import model.Program;
import model.Department;
import datastructures.linear.ArrayList;

/**
 *
 * @author ShaheerZK
 */
public interface ProgramDAO 
{
    /**
     * Inserts the program object.
     * @param program 
     */
    public void insertProgram(Program program);
    
    /**
     * Returns the object if found, null otherwise.
     * @param programId
     * @return 
     */
    public Program getProgramById(int programId);
    
    /**
     * Returns all the programs belonging to the provided department. 
     * @param department
     * @return 
     */
    public ArrayList<Program> getProgramsByDepartment(Department department);
    
    /**
     * Gets the program by name (e.g BS Computer Science).
     * @param name
     * @return 
     */
    public Program getProgramByName(String name);
    
    /**
     * Returns all the programs.
     * @return 
     */
    public ArrayList<Program> getAllPrograms();
}

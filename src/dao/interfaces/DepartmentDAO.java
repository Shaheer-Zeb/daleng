package dao.interfaces;

import datastructures.linear.ArrayList;
import model.Department;

/**
 * @author ShaheerZK
 */

public interface DepartmentDAO
{
    /**
     * ...
     * @param department 
     */
    public void insertDepartment(Department department);
    
    /**
     * Gets the department name e.g. Computer Science, and returns the Department object if found, null otherwise.
     * @param name
     * @return 
     */
    public Department getDepartmentByName(String name);
    
    /**
     * Returns the Department having the provided id value.
     * @param id
     * @return 
     */
    public Department getDepartmentById(int id);
    
    /**
     * Gives all the departments.
     * @return 
     */
    public ArrayList<Department> getAllDepartments();
}
package dao;

import dao.interfaces.DepartmentDAO;
import database.DBConnection;
import datastructures.linear.ArrayList;
import model.Department;
import java.sql.*;

/**
 *
 * @author ShaheerZK
 */
public class DepartmentDAOImpl implements DepartmentDAO
{

    @Override
    public void insertDepartment(Department department)
    {
        String insertQuery = "INSERT INTO department (department_name) VALUES (?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(insertQuery))
        {
            ps.setString(1, department.getDepartmentName());
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    @Override
    public Department getDepartmentByName(String name) 
    {
        String selectQuery = "SELECT * FROM department WHERE department_name = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                return new Department(rs.getInt("department_id"), rs.getString("department_name"));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;    
    }
    @Override
    public Department getDepartmentById(int id) 
    {
        String selectQuery = "SELECT * FROM department WHERE department_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                return new Department(rs.getInt("department_id"), rs.getString("department_name"));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null; 
    }
    @Override
    public ArrayList<Department> getAllDepartments() 
    {
        ArrayList<Department> departments = new ArrayList<>();
        String selectAllQuery = "SELECT * FROM department";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectAllQuery))
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Department program = new Department(rs.getInt("department_id"), rs.getString("department_name"));
                departments.add(program);
            }
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return departments;
    }
    
}

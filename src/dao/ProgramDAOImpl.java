package dao;

import dao.interfaces.ProgramDAO;
import datastructures.linear.ArrayList;
import model.Department;
import model.Program;
import database.DBConnection;
import java.sql.*;

/**
 *
 * @author ShaheerZK
 */
public class ProgramDAOImpl implements ProgramDAO
{
    
    @Override
    public void insertProgram(Program program) 
    {
        String insertQuery = "INSERT INTO program (department_id, program_name) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(insertQuery))
        {   
            ps.setInt(1, program.getDepartmentId());
            ps.setString(2, program.getProgramName());
            
            ps.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }
    @Override
    public Program getProgramById(int programId) 
    {
        String selectQuery = "SELECT * FROM program WHERE program_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, programId);
            ResultSet rs = ps.executeQuery();
            Program program = null;
            while (rs.next())
            {
                program = new Program(rs.getInt("program_id"), rs.getInt("department_id"), rs.getString("program_name"));
            }
            return program;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    @Override
    public ArrayList<Program> getProgramsByDepartment(Department department) 
    {
        ArrayList<Program> programs = new ArrayList<>();
        String selectQuery = "SELECT * FROM program WHERE department_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, department.getDepartmentId());
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Program program = new Program(rs.getInt("program_id"), rs.getInt("department_id"), rs.getString("program_name"));
                programs.add(program);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return programs;
    }
    @Override
    public Program getProgramByName(String name) 
    {
        String selectQuery = "SELECT * FROM program WHERE program_name = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                return new Program(rs.getInt("program_id"), rs.getInt("department_id"), rs.getString("program_name"));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    @Override
    public ArrayList<Program> getAllPrograms()
    {
        ArrayList<Program> programs = new ArrayList<>();
        String selectAllQuery = "SELECT * FROM program";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectAllQuery))
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Program program = new Program(rs.getInt("program_id"), rs.getInt("department_id"), rs.getString("program_name"));
                programs.add(program);
            }
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return programs;
    }
    
}

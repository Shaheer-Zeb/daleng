package dao;

import dao.interfaces.CourseSchemeDAO;
import database.DBConnection;
import datastructures.linear.ArrayList;
import model.AcademicTerm;
import model.CourseScheme;
import java.sql.*;
import model.Program;

/**
 *
 * @author ShaheerZK
 */
public class CourseSchemeDAOImpl implements CourseSchemeDAO
{
    @Override
    public void insertCourseScheme(CourseScheme courseScheme) 
    {
        String insertQuery = "INSERT INTO course_scheme (program_id, term_id, scheme_name) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareCall(insertQuery))
        {
            ps.setInt(1, courseScheme.getProgramId());
            ps.setInt(2, courseScheme.getTermId());
            ps.setString(3, courseScheme.getSchemeName());
            
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    @Override
    public CourseScheme getCourseSchemeById(int schemeId) 
    {
        String selectQuery = "SELECT * FROM course_scheme WHERE scheme_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareCall(selectQuery))
        {
            ps.setInt(1, schemeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return new CourseScheme(rs.getInt("scheme_id"), rs.getInt("program_id"), rs.getInt("term_id"), rs.getString("scheme_name"));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    @Override
    public CourseScheme getCourseSchemeByName(String name)
    {
        String selectQuery = "SELECT * FROM course_scheme WHERE scheme_name = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareCall(selectQuery))
        {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return new CourseScheme(rs.getInt("scheme_id"), rs.getInt("program_id"), rs.getInt("term_id"), rs.getString("scheme_name"));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    @Override
    public ArrayList<CourseScheme> getCourseSchemesByTerm(AcademicTerm academicTerm) 
    {
        ArrayList<CourseScheme> schemes = new ArrayList<>();
        String selectQuery = "SELECT * FROM course_scheme WHERE term_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareCall(selectQuery))
        {
            ps.setInt(1, academicTerm.getTermId());
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                schemes.add(new CourseScheme(rs.getInt("scheme_id"), rs.getInt("program_id"), rs.getInt("term_id"), rs.getString("scheme_name")));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    @Override
    public ArrayList<CourseScheme> getAllCourseSchemes()
    {
        ArrayList<CourseScheme> schemes = new ArrayList<>();
        String selectQuery = "SELECT * FROM course_scheme";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareCall(selectQuery))
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                schemes.add(new CourseScheme(rs.getInt("scheme_id"), rs.getInt("program_id"), rs.getInt("term_id"), rs.getString("scheme_name")));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<CourseScheme> getAllCourseSchemesByProgram(Program program) 
    {
        ArrayList<CourseScheme> schemes = new ArrayList<>();
        String selectQuery = "SELECT * FROM course_scheme WHERE program_id = (?)";
        
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, program.getProgramId());
            ResultSet rs = ps.executeQuery();
            
            while (rs.next())
            {
                schemes.add(new CourseScheme(rs.getInt("scheme_id"), rs.getInt("program_id"), rs.getInt("term_id"), rs.getString("scheme_name")));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return schemes;
    }
}

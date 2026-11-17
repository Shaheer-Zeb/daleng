package dao;

import dao.interfaces.CourseOfferingDAO;
import database.DBConnection;
import datastructures.linear.ArrayList;
import model.AcademicTerm;
import model.Course;
import model.CourseOffering;
import model.Teacher;
import java.sql.*;

/**
 *
 * @author ShaheerZK
 */
public class CourseOfferingDAOImpl implements CourseOfferingDAO
{
    
    @Override
    public void insertCourseOffering(CourseOffering offering) 
    {
        String insertQuery = "INSERT INTO course_offering (course_id, term_id, teacher_id, section_name) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(insertQuery))
        {
            ps.setInt(1, offering.getCourseId());
            ps.setInt(2, offering.getTermId());
            ps.setInt(3, offering.getTeacherId());
            ps.setString(4, offering.getSectionName());
            
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public CourseOffering getCourseOfferingById(int id)
    {
        String selectQuery = "SELECT * FROM course_offering WHERE offering_id = (?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                return new CourseOffering(rs.getInt("offering_id"), rs.getInt("course_id"), rs.getInt("term_id"), rs.getInt("teacher_id"), rs.getString("section_name"));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<CourseOffering> getCourseOfferingsByCourse(Course course) 
    {
        ArrayList<CourseOffering> offerings = new ArrayList<>();
        String selectQuery = "SELECT * FROM course_offering WHERE course_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, course.getCourseId());
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                offerings.add(new CourseOffering(rs.getInt("offering_id"), rs.getInt("course_id"), rs.getInt("term_id"), rs.getInt("teacher_id"), rs.getString("section_name")));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return offerings;
    }
    @Override
    public ArrayList<CourseOffering> getCourseOfferingsByTeacher(Teacher teacher)
    {
        ArrayList<CourseOffering> offerings = new ArrayList<>();
        String selectQuery = "SELECT * FROM course_offering WHERE teacher_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, teacher.getTeacherId());
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                offerings.add(new CourseOffering(rs.getInt("offering_id"), rs.getInt("course_id"), rs.getInt("term_id"), rs.getInt("teacher_id"), rs.getString("section_name")));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return offerings;
    }
    @Override
    public ArrayList<CourseOffering> getAllCoursesByTerm(AcademicTerm term) 
    {
        ArrayList<CourseOffering> offerings = new ArrayList<>();
        String selectQuery = "SELECT * FROM course_offering WHERE term_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, term.getTermId());
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                offerings.add(new CourseOffering(rs.getInt("offering_id"), rs.getInt("course_id"), rs.getInt("term_id"), rs.getInt("teacher_id"), rs.getString("section_name")));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return offerings;
    }

    @Override
    public ArrayList<CourseOffering> getAllCourseOfferings() 
    {
        ArrayList<CourseOffering> offerings = new ArrayList<>();
        String selectQuery = "SELECT * FROM course_offering";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                offerings.add(new CourseOffering(rs.getInt("offering_id"), rs.getInt("course_id"), rs.getInt("term_id"), rs.getInt("teacher_id"), rs.getString("section_name")));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return offerings;
    }
}

package dao;

import dao.interfaces.CourseDAO;
import database.DBConnection;
import datastructures.linear.ArrayList;
import model.Course;
import java.sql.*;

/**
 *
 * @author ShaheerZK
 */
public class CourseDAOImpl implements CourseDAO
{

    @Override
    public void insertCourse(Course course)
    {
        String insertQuery = "INSERT INTO course (course_code, course_name, credit_hours) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareCall(insertQuery))
        {
            ps.setString(1, course.getCourseCode());
            ps.setString(2, course.getCourseName());
            ps.setDouble(3, course.getCreditHours());
            
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public Course getCourseById(int id) 
    {
        String selectQuery = "SELECT * FROM course WHERE course_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return new Course(rs.getInt("course_id"), rs.getString("course_code"), rs.getString("course_name"), rs.getDouble("credit_hours"));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Course getCourseByCode(String code)
    {
        String selectQuery = "SELECT * FROM course WHERE course_code = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return new Course(rs.getInt("course_id"), rs.getString("course_code"), rs.getString("course_name"), rs.getDouble("credit_hours"));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Course getCourseByName(String name)
    {
        String selectQuery = "SELECT * FROM course WHERE course_name = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return new Course(rs.getInt("course_id"), rs.getString("course_code"), rs.getString("course_name"), rs.getDouble("credit_hours"));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Course> getAllCourses() 
    {
        ArrayList<Course> courses = new ArrayList<>();
        String selectQuery = "SELECT * FROM course";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                courses.add(new Course(rs.getInt("course_id"), rs.getString("course_code"), rs.getString("course_name"), rs.getDouble("credit_hours")));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return courses;
    }
    @Override
    public ArrayList<Course> getCoursesByCreditHours(double creditHours)
    {
        ArrayList<Course> courses = new ArrayList<>();
        String selectQuery = "SELECT * FROM course WHERE credit_hours = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setDouble(1, creditHours);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                courses.add(new Course(rs.getInt("course_id"), rs.getString("course_code"), rs.getString("course_name"), rs.getDouble("credit_hours")));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return courses;
    } 
}

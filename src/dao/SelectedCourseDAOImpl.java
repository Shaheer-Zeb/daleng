package dao;

import dao.interfaces.SelectedCourseDAO;
import database.DBConnection;
import datastructures.linear.ArrayList;
import model.Course;
import model.SelectedCourse;
import model.StudentSelection;
import java.sql.*;

/**
 *
 * @author ShaheerZK
 */
public class SelectedCourseDAOImpl implements SelectedCourseDAO
{

    @Override
    public void insertSelectedCourse(SelectedCourse selectedCourse) 
    {
        String insertQuery = "INSERT INTO selected_course (selection_id, course_id) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(insertQuery))
        {
            ps.setInt(1, selectedCourse.getSelectionId());
            ps.setInt(2, selectedCourse.getCourseId());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public SelectedCourse getSelectedCourseById(int id) 
    {
        String selectQuery = "SELECT * FROM selected_course WHERE selected_course_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return new SelectedCourse(rs.getInt("selected_course_id"), rs.getInt("selection_id"), rs.getInt("course_id"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<SelectedCourse> getAllSelectedCourseByStudentSelection(StudentSelection selection) 
    {
        String selectQuery = "SELECT * FROM selected_course WHERE selected_course_id = ?";
        ArrayList<SelectedCourse> selectedCourses = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, selection.getSelectionId());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                selectedCourses.add(new SelectedCourse(rs.getInt("selected_course_id"), rs.getInt("selection_id"), rs.getInt("course_id")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return selectedCourses;
    }

    @Override
    public ArrayList<SelectedCourse> getAllSelectedCoursesByCourse(Course course) 
    {
        String selectQuery = "SELECT * FROM selected_course WHERE course_id = ?";
        ArrayList<SelectedCourse> selectedCourses = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, course.getCourseId());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                selectedCourses.add(new SelectedCourse(rs.getInt("selected_course_id"), rs.getInt("selection_id"), rs.getInt("course_id")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return selectedCourses;
    }

    @Override
    public ArrayList<SelectedCourse> getAllSelectedCourses() 
    {
        String selectQuery = "SELECT * FROM selected_course";
        ArrayList<SelectedCourse> selectedCourses = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                selectedCourses.add(new SelectedCourse(rs.getInt("selected_course_id"), rs.getInt("selection_id"), rs.getInt("course_id")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return selectedCourses;
    }
}

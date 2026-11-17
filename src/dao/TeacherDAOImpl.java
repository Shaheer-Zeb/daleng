package dao;

import dao.interfaces.TeacherDAO;
import database.DBConnection;
import datastructures.linear.ArrayList;
import model.Teacher;
import java.sql.*;

/**
 *
 * @author ShaheerZK
 */
public class TeacherDAOImpl implements TeacherDAO
{

    @Override
    public void insertTeacher(Teacher teacher) 
    {
        String insertQuery = "INSERT INTO TEACHER (teacher_name) VALUES (?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(insertQuery))
        {
            ps.setString(1, teacher.getTeacherName());
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public Teacher getTeacherById(int id)
    {
        String selectQuery = "SELECT * FROM teacher WHERE teacher_id = (?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return new Teacher(rs.getInt("teacher_id"), rs.getString("teacher_name"));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Teacher getTeacherByName(String name) 
    {
        String selectQuery = "SELECT * FROM teacher WHERE teacher_name = (?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return new Teacher(rs.getInt("teacher_id"), rs.getString("teacher_name"));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Teacher> getAllTeachers() 
    {
        ArrayList<Teacher> teachers = new ArrayList<>();
        String selectQuery = "SELECT * FROM teacher";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                teachers.add(new Teacher(rs.getInt("teacher_id"), rs.getString("teacher_name")));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return teachers;
    }
    
}

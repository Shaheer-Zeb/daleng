package dao;

import dao.interfaces.StudentSelectionDAO;
import database.DBConnection;
import datastructures.linear.ArrayList;
import model.AcademicTerm;
import model.Student;
import model.StudentSelection;
import java.sql.*;

/**
 *
 * @author ShaheerZK
 */
public class StudentSelectionDAOImpl implements StudentSelectionDAO
{

    @Override
    public void insertStudentSelection(StudentSelection selection) 
    {
        String insertQuery = "INSERT INTO student_selection (student_id, term_id) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(insertQuery))
        {
            ps.setInt(1, selection.getSelectionId());
            ps.setInt(2, selection.getTermId());
            
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public StudentSelection getSelectionById(int id) 
    {
        String selectQuery = "SELECT * FROM student_selection WHERE selection_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next())
                return new StudentSelection(rs.getInt("selection_id"), rs.getInt("student_id"), rs.getInt("term_id"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<StudentSelection> getAllSelectionsOfStudent(Student student) 
    {
        String selectQuery = "SELECT * FROM student_selection WHERE student_id = ?";
        ArrayList<StudentSelection> selections = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, student.getStudentId());
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next())
                selections.add(new StudentSelection(rs.getInt("selection_id"), rs.getInt("student_id"), rs.getInt("term_id")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return selections;
    }

    @Override
    public ArrayList<StudentSelection> getAllSelectionsOfStudentInTerm(Student student, AcademicTerm term) 
    {
        String selectQuery = "SELECT * FROM student_selection WHERE student_id = ? AND term_id = ?";
        ArrayList<StudentSelection> selections = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, student.getStudentId());
            ps.setInt(2, term.getTermId());
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next())
                selections.add(new StudentSelection(rs.getInt("selection_id"), rs.getInt("student_id"), rs.getInt("term_id")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return selections;
    }

    @Override
    public ArrayList<StudentSelection> getAllSelectionsInTerm(AcademicTerm term)
    {
        String selectQuery = "SELECT * FROM student_selection WHERE term_id = ?";
        ArrayList<StudentSelection> selections = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, term.getTermId());
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next())
                selections.add(new StudentSelection(rs.getInt("selection_id"), rs.getInt("student_id"), rs.getInt("term_id")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return selections;
    }

    @Override
    public ArrayList<StudentSelection> getAllSelections() 
    {
        String selectQuery = "SELECT * FROM student_selection";
        ArrayList<StudentSelection> selections = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {   
            ResultSet rs = ps.executeQuery();
            
            while (rs.next())
                selections.add(new StudentSelection(rs.getInt("selection_id"), rs.getInt("student_id"), rs.getInt("term_id")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return selections;
    } 
}

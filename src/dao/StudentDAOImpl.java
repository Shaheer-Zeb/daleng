package dao;

import dao.interfaces.StudentDAO;
import database.DBConnection;
import datastructures.linear.ArrayList;
import model.Program;
import model.Student;
import java.sql.*;

/**
 *
 * @author ShaheerZK
 */
public class StudentDAOImpl implements StudentDAO
{

    @Override
    public void insertStudent(Student student) 
    {
        String insertQuery = "INSERT INTO student (reg_no, student_name, program_id, cohort_code, email, password_hash) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(insertQuery))
        {
            ps.setString(1, student.getRegNo());
            ps.setString(2, student.getStudentName());
            ps.setInt(3, student.getProgramId());
            ps.setString(4, student.getCohortCode());
            ps.setString(5, student.getEmail());
            ps.setString(6, student.getPasswordHash());
            
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudentById(int id) 
    {
        String selectQuery = "SELECT * FROM student WHERE student_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next())
                return new Student(rs.getInt("student_id"), rs.getString("reg_no"), rs.getString("student_name"), rs.getInt("program_id"), rs.getString("cohort_code"), rs.getString("email"), rs.getString("password_hash"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Student getStudentByRegNo(String regoNo) 
    {
        String selectQuery = "SELECT * FROM student WHERE reg_no = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setString(1, regoNo);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next())
                return new Student(rs.getInt("student_id"), rs.getString("reg_no"), rs.getString("student_name"), rs.getInt("program_id"), rs.getString("cohort_code"), rs.getString("email"), rs.getString("password_hash"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Student> getStudentsByName(String name) 
    {
        String selectQuery = "SELECT * FROM student WHERE student_name = ?";
        ArrayList<Student> students = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setString(1, name);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next())
                students.add(new Student(rs.getInt("student_id"), rs.getString("reg_no"), rs.getString("student_name"), rs.getInt("program_id"), rs.getString("cohort_code"), rs.getString("email"), rs.getString("password_hash")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public ArrayList<Student> getAllStudentsInProgram(Program program) 
    {
        String selectQuery = "SELECT * FROM student WHERE program_id = ?";
        ArrayList<Student> students = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, program.getProgramId());
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next())
                students.add(new Student(rs.getInt("student_id"), rs.getString("reg_no"), rs.getString("student_name"), rs.getInt("program_id"), rs.getString("cohort_code"), rs.getString("email"), rs.getString("password_hash")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public ArrayList<Student> getAllStudentsOfCohort(String name) 
    {
        String selectQuery = "SELECT * FROM student WHERE cohort_code = ?";
        ArrayList<Student> students = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setString(1, name);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next())
                students.add(new Student(rs.getInt("student_id"), rs.getString("reg_no"), rs.getString("student_name"), rs.getInt("program_id"), rs.getString("cohort_code"), rs.getString("email"), rs.getString("password_hash")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public ArrayList<Student> getAllStudents() 
    {
        String selectQuery = "SELECT * FROM student";
        ArrayList<Student> students = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next())
                students.add(new Student(rs.getInt("student_id"), rs.getString("reg_no"), rs.getString("student_name"), rs.getInt("program_id"), rs.getString("cohort_code"), rs.getString("email"), rs.getString("password_hash")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return students;
    }
}

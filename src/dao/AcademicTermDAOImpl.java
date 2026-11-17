package dao;

import dao.interfaces.AcademicTermDAO;
import database.DBConnection;
import datastructures.linear.ArrayList;
import model.AcademicTerm;
import java.time.LocalDate;
import java.sql.*;

/**
 *
 * @author ShaheerZK
 */
public class AcademicTermDAOImpl implements AcademicTermDAO
{

    @Override
    public void insertAcademicTerm(AcademicTerm academicTerm) 
    {
        String insertQuery = "INSERT INTO academic_term (term_code,start_date, end_date) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(insertQuery))
        {
            ps.setString(1, academicTerm.getTermCode());
            ps.setObject(2, academicTerm.getStartDate());
            ps.setObject(3, academicTerm.getEndDate());
            
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    @Override
    public AcademicTerm getAcademicTermById(int id) 
    {
        String selectQuery = "SELECT * FROM academic_term WHERE term_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return new AcademicTerm(rs.getInt("term_id"), rs.getString("term_code"), rs.getObject("start_date", LocalDate.class), rs.getObject("end_date", LocalDate.class));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    @Override
    public AcademicTerm getAcademicTermByCode(String code) 
    {
        String selectQuery = "SELECT * FROM academic_term WHERE term_code = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return new AcademicTerm(rs.getInt("term_id"), rs.getString("term_code"), rs.getObject("start_date", LocalDate.class), rs.getObject("end_date", LocalDate.class));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    @Override
    public ArrayList<AcademicTerm> getAllAcademicTerms() 
    {
        ArrayList<AcademicTerm> academicTerms = new ArrayList<>();
        String selectQuery = "SELECT * FROM academic_term";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                academicTerms.add(new AcademicTerm(rs.getInt("term_id"), rs.getString("term_code"), rs.getObject("start_date", LocalDate.class), rs.getObject("end_date", LocalDate.class)));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return academicTerms;
    }  
}

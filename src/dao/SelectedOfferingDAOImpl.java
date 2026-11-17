package dao;

import dao.interfaces.SelectedOfferingDAO;
import database.DBConnection;
import datastructures.linear.ArrayList;
import model.SelectedOffering;
import model.StudentSelection;
import java.sql.*;

/**
 *
 * @author ShaheerZK
 */
public class SelectedOfferingDAOImpl implements SelectedOfferingDAO
{

    @Override
    public void insertSelectedOffering(SelectedOffering selectedOffering) 
    {
        String insertQuery = "INSERT INTO selected_offering (selected_course_id, course_id, offering_id, selection_source VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(insertQuery))
        {
            ps.setInt(1, selectedOffering.getSelectedCourseId());
            ps.setInt(2, selectedOffering.getCourseId());
            ps.setInt(3, selectedOffering.getOfferingId());
            ps.setString(4, selectedOffering.getSelectionSource());
            
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public SelectedOffering getSelectedOfferingById(int id) 
    {
        String selectQuery = "SELECT * FROM selected_offering WHERE selected_offering_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next())
            return new SelectedOffering(rs.getInt("selected_offering_id"), rs.getInt("selected_course_id"), rs.getInt("course_id"), rs.getInt("offering_id"), rs.getString("selection_source"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<SelectedOffering> getAllSelectedOfferingsByStudentSelection(StudentSelection selection) 
    {
        String selectQuery = "SELECT * FROM selected_offering JOIN selected_course ON selected_course.selected_course_id = selected_offering.selected_course_id WHERE selected_course.selection_id = ?";
        ArrayList<SelectedOffering> selectedOfferings = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, selection.getSelectionId());
            
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                selectedOfferings.add(new SelectedOffering(rs.getInt("selected_offering_id"), rs.getInt("selected_course_id"), rs.getInt("course_id"), rs.getInt("offering_id"), rs.getString("selection_source")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return selectedOfferings;
    }

    @Override
    public void updateSelectedOffering(int selectedOfferingId, int offeringId, int courseId, String selectionSource) 
    {
        String updateQuery = "UPDATE selected_offering SET offering_id = ?, course_id = ?, selection_source = ? WHERE selected_offering_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(updateQuery))
        {
            ps.setInt(1, offeringId);
            ps.setInt(2, courseId);
            ps.setString(3, selectionSource);
            ps.setInt(4, selectedOfferingId);
            
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSelectedOffering(int selectedOfferingId) 
    {
        String deleteQuery = "DELETE FROM selected_offering WHERE selected_offering_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(deleteQuery))
        {
            ps.setInt(1, selectedOfferingId);
            
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }  
}

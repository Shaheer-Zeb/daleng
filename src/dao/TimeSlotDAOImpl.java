package dao;

import dao.interfaces.TimeSlotDAO;
import database.DBConnection;
import datastructures.linear.ArrayList;
import model.TimeSlot;
import java.sql.*;
import java.time.LocalTime;

/**
 *
 * @author ShaheerZK
 */
public class TimeSlotDAOImpl implements TimeSlotDAO
{

    @Override
    public void insertTimeSlot(TimeSlot timeSlot) 
    {
        String insertSql = "INSERT INTO time_slot (start_time, end_time) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(insertSql))
        {
            ps.setObject(1, timeSlot.getStartTime());
            ps.setObject(2, timeSlot.getEndTime());
            
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    @Override
    public TimeSlot getTimeSlotById(int id) 
    {
        String selectQuery = "SELECT * FROM time_slot WHERE time_slot_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return new TimeSlot(rs.getInt("time_slot_id"), rs.getObject("start_time", LocalTime.class), rs.getObject("end_time", LocalTime.class));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<TimeSlot> getAllTimeSlots() 
    {
        ArrayList<TimeSlot> timeSlots = new ArrayList<>();
        String selectQuery = "SELECT * FROM time_slot";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                timeSlots.add(new TimeSlot(rs.getInt("time_slot_id"), rs.getObject("start_time", LocalTime.class), rs.getObject("end_time", LocalTime.class)));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return timeSlots;
    }
    
}

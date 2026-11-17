package dao;

import dao.interfaces.CourseOfferingDAO;
import dao.interfaces.OfferingScheduleDAO;
import database.DBConnection;
import datastructures.linear.ArrayList;
import model.Course;
import model.CourseOffering;
import model.OfferingSchedule;
import java.sql.*;

/**
 *
 * @author ShaheerZK
 */
public class OfferingScheduleDAOImpl implements OfferingScheduleDAO
{

    @Override
    public void insertOfferingSchedule(OfferingSchedule schedule)
    {
        String insertQuery = "INSERT INTO offering_schedule (offering_id, time_slot_id, day_of_week) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(insertQuery))
        {
            ps.setInt(1, schedule.getOfferingId());
            ps.setInt(2, schedule.getTimeSlotId());
            ps.setString(3, schedule.getDay());
            
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public OfferingSchedule getOfferingScheduleById(int id)
    {
        String selectQuery = "SELECT * FROM offering_schedule WHERE schedule_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return new OfferingSchedule(rs.getInt("schedule_id"), rs.getInt("offering_id"), rs.getInt("time_slot_id"), rs.getString("day_of_week"));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<OfferingSchedule> getAllOfferingSchedulesByOffering(CourseOffering offering)
    {
        ArrayList<OfferingSchedule> schedules = new ArrayList<>();
        String selectQuery = "SELECT * FROM offering_schedule WHERE offering_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery))
        {
            ps.setInt(1, offering.getOfferingId());
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                schedules.add(new OfferingSchedule(rs.getInt("schedule_id"), rs.getInt("offering_id"), rs.getInt("time_slot_id"), rs.getString("day_of_week")));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return schedules;
    }

    /**
     * I think this implementation also has an N+1 query problem.
     * @param course
     * @return 
     */
    @Override
    public ArrayList<OfferingSchedule> getAllOfferingSchedulesByCourse(Course course) 
    {
        ArrayList<OfferingSchedule> schedules = new ArrayList<>();
        CourseOfferingDAO courseOfferingDao = new CourseOfferingDAOImpl();
        for (CourseOffering offering : courseOfferingDao.getCourseOfferingsByCourse(course))
        {
            for (OfferingSchedule schedule : getAllOfferingSchedulesByOffering(offering))
                schedules.add(schedule);
        }
        return schedules;
    }
}

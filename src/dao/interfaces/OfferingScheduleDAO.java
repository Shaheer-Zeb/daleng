package dao.interfaces;

import datastructures.linear.ArrayList;
import model.Course;
import model.CourseOffering;
import model.OfferingSchedule;

/**
 *
 * @author ShaheerZK
 */
public interface OfferingScheduleDAO 
{
    /**
     * Inserts the offering schedule in the DB.
     * @param schedule 
     */
    public void insertOfferingSchedule(OfferingSchedule schedule);
    
    /**
     * Returns the offering schedule by its primary key (schedule_id).
     * @return
     * @param id 
     */
    public OfferingSchedule getOfferingScheduleById(int id);
    
    /**
     * Returns all the offering schedules of a particular course offering.
     * @param offering
     * @return 
     */
    public ArrayList<OfferingSchedule> getAllOfferingSchedulesByOffering(CourseOffering offering); 
    
    
    /**
     * Returns all the schedules of all the course offerings of the provided course.
     * @param course
     * @return 
     */
    public ArrayList<OfferingSchedule> getAllOfferingSchedulesByCourse(Course course);
}

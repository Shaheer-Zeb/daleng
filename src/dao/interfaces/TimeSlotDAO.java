package dao.interfaces;

import datastructures.linear.ArrayList;
import java.time.LocalTime;
import model.TimeSlot;

/**
 *
 * @author ShaheerZK
 */
public interface TimeSlotDAO
{
    /**
     * Inserts a new time slot, if it already doesn't exist.
     * @param timeSlot 
     */
    public void insertTimeSlot(TimeSlot timeSlot);
    
    /**
     * Returns a time slot POJO depending on the id (the slot number for our University essentially). Returns null if the id is invalid.
     * @param id
     * @return 
     */
    public TimeSlot getTimeSlotById(int id);
    
    /**
     * Gives all the time slots in the DB.
     * @return 
     */
    public ArrayList<TimeSlot> getAllTimeSlots();
    
    /**
     * Returns all the time slots which have the same starting time.
     * @param time
     * @return 
     */
    //public ArrayList<TimeSlot> getTimeSlotsByStartTime(LocalTime time);
    
    /**
     * Returns all the time slots having the same end times.
     * @param time
     * @return 
     */
    //public ArrayList<TimeSlot> getTimeSlotsByEndTime(LocalTime time);
}

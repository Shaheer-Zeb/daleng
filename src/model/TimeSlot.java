package model;

import java.time.LocalTime;

/**
 *
 * @author ShaheerZK
 */
public class TimeSlot 
{
    private int timeSlotId;
    private LocalTime startTime;
    private LocalTime endTime;
    
    public TimeSlot(int timeSlotId, LocalTime startTime, LocalTime endTime)
    {
        this.timeSlotId = timeSlotId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public TimeSlot(LocalTime startTime, LocalTime endTime)
    {
        this(0, startTime, endTime);
    }

    public int getTimeSlotId()
    {
        return timeSlotId;
    }

    public void setTimeSlotId(int timeSlotId)
    {
        this.timeSlotId = timeSlotId;
    }

    public LocalTime getStartTime() 
    {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) 
    {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() 
    {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) 
    {
        this.endTime = endTime;
    }
    
}

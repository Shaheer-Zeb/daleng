package model;

/**
 *
 * @author ShaheerZK
 */
public class OfferingSchedule
{
    private int scheduleId;
    private int offeringId;
    private int timeSlotId;
    private String day;
    
    public OfferingSchedule (int scheduleId, int offeringId, int timeSlotId, String day)
    {
        this.scheduleId = scheduleId;
        this.offeringId = offeringId;
        this.timeSlotId = timeSlotId;
        this.day = day;  
    }
    public OfferingSchedule (int offeringId, int timeSlotId, String day)
    {
        this(0, offeringId, timeSlotId, day);
    }

    public int getScheduleId() 
    {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) 
    {
        this.scheduleId = scheduleId;
    }

    public int getOfferingId() 
    {
        return offeringId;
    }

    public void setOfferingId(int offeringId) 
    {
        this.offeringId = offeringId;
    }

    public int getTimeSlotId()
    {
        return timeSlotId;
    }

    public void setTimeSlotId(int timeSlotId) 
    {
        this.timeSlotId = timeSlotId;
    }

    public String getDay() 
    {
        return day;
    }

    public void setDay(String day) 
    {
        this.day = day;
    }
    
}

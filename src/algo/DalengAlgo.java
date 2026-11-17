package algo;

import dao.CourseOfferingDAOImpl;
import dao.OfferingScheduleDAOImpl;
import dao.TimeSlotDAOImpl;
import dao.interfaces.CourseOfferingDAO;
import dao.interfaces.OfferingScheduleDAO;
import dao.interfaces.TimeSlotDAO;
import datastructures.linear.ArrayList;
import datastructures.linear.HashMap;
import model.Course;
import model.CourseOffering;
import model.OfferingSchedule;
import model.TimeSlot;

/**
 * A class that has the actual offering recommendation algo and a few utility methods.
 * @author ShaheerZK
 */
public class DalengAlgo 
{
    public ConflictGraph conflictGraph;
    private ArrayList<Course> selectedCourses;
    private ArrayList<ArrayList<CourseOffering>> availableOfferings;
    private HashMap<CourseOffering, ArrayList<OfferingSchedule>> offeringScheduleMap;
    
    // Algorithm specific stuff
    private ArrayList<CourseOffering> currentSelection;
    private ArrayList<CourseOffering> bestSelection;
    private int conflictCount = 0;
    private int lowestConflictCount = Integer.MAX_VALUE;
    
    public DalengAlgo(ArrayList<Course> selectedCourses)
    {
        this.selectedCourses = selectedCourses;
        initAvailableOfferings();
        initOfferingScheduleMap();
        conflictGraph = new ConflictGraph(availableOfferings, offeringScheduleMap);
    }
    /**
     * The actual method that should be called while invoking the Daleng Algorithm.
     * @return 
     */
    public Recommendation getRecommendation()
    {
        return null;
    }
    /**
     * Uses the DAO layer to get all the available offerings for the current selected courses.
     */
    private void initAvailableOfferings()
    {
        CourseOfferingDAO dao = new CourseOfferingDAOImpl();
        availableOfferings = new ArrayList<>();
        for (Course c : selectedCourses)
            availableOfferings.add(dao.getCourseOfferingsByCourse(c));
    }
    /**
     * Uses the DAO layer to map every available offering for the selected courses to their offering schedules.
     */
    private void initOfferingScheduleMap()
    {
        OfferingScheduleDAO dao = new OfferingScheduleDAOImpl();
        offeringScheduleMap = new HashMap<>();
        for (ArrayList<CourseOffering> courseOfferings : availableOfferings)
        {
            for (CourseOffering offering : courseOfferings)
            {
                offeringScheduleMap.put(offering, dao.getAllOfferingSchedulesByOffering(offering));
            }
        }
    }
    /**
     * Returns whether two OfferingSchedules have a conflict or not. Well, currently this method is awfully slow, since it creates a new DAO everytime and does two DB queries eachtime. I'd definitely need to solve this.
     * @param first
     * @param second
     * @return 
     */
    public static boolean twoSchedulesConflict(OfferingSchedule first, OfferingSchedule second)
    {
        if (!first.getDay().equals(second.getDay()))
            return false;
        TimeSlotDAO timeSlotDao = new TimeSlotDAOImpl();
        
        TimeSlot firstTimeSlot = timeSlotDao.getTimeSlotById(first.getTimeSlotId());
        TimeSlot secondTimeSlot = timeSlotDao.getTimeSlotById(second.getTimeSlotId());
        
        return firstTimeSlot.getStartTime().isBefore(secondTimeSlot.getEndTime()) && secondTimeSlot.getStartTime().isBefore(firstTimeSlot.getEndTime());
    }
    /**
     * Returns whether the two offering schedules of two offerings have any conflict throughout the week.
     * @param first
     * @param second
     * @return 
     */
    public static boolean hasConflictInWeek(ArrayList<OfferingSchedule> first, ArrayList<OfferingSchedule> second)
    {
        for (OfferingSchedule a : first)
        {
            for (OfferingSchedule b : second)
            {
                if (twoSchedulesConflict(a, b))
                    return true;
            }
        }
        return false;
    }
}

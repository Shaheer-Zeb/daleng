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
    private static HashMap<Integer, TimeSlot> timeSlotMap;
    
    // Algorithm specific stuff
    private ArrayList<CourseOffering> currentSelection = new ArrayList<>();
    private ArrayList<CourseOffering> bestSelection = new ArrayList<>();
    private int conflictCount = 0;
    private int lowestConflictCount = Integer.MAX_VALUE;
    
    public DalengAlgo(ArrayList<Course> selectedCourses)
    {
        this.selectedCourses = selectedCourses;
        initAvailableOfferings();
        initOfferingScheduleMap();
        initTimeslotMap();
        conflictGraph = new ConflictGraph(availableOfferings, offeringScheduleMap);
    }
    /**
     * The actual method that should be called while invoking the Daleng Algorithm.
     * @return 
     */
    public Recommendation getRecommendation()
    {
        backtrack(0);
        return new Recommendation(bestSelection, conflictCount);
    }
    /**
     * A simple backtracking algo with bounding and pruning. It chooses one course offering for each selected course at a time and returns checks if the current conflicts are greater than best conflicts, if they are, it backtracks.
     * Calls itself recursively until all courses are assigned an offering. If all courses are assigned an offering, and have the least conflicts, it assigned as the bestSelection.
     * And if at any point an offering is selected for all the courses and the conflict count between them is 0, the method immediately the current selection as the best one and returns.
     * @param courseIndex 
     */
    private void backtrack(int courseIndex)
    {
        if (courseIndex == selectedCourses.size())
        {
            if (conflictCount < lowestConflictCount)
            {
                lowestConflictCount = conflictCount;
                bestSelection = copyCurrentSelection();
            }
            return;
        }
        ArrayList<CourseOffering> offeringsForCourse = availableOfferings.get(courseIndex);
        for (int i = 0; i < offeringsForCourse.size(); i++)
        {
            if (lowestConflictCount == 0)
                return;
            
            CourseOffering selectedOffering = offeringsForCourse.get(i);
            int newConficts = getNewConflictCount(selectedOffering);
            
            if (conflictCount + newConficts < lowestConflictCount)
            {
                currentSelection.add(selectedOffering);
                conflictCount += newConficts;
                
                backtrack(courseIndex + 1);
                
                conflictCount -= newConficts;
                currentSelection.remove(currentSelection.size() - 1);
            }
        }
    }
    /**
     * Creates a copy of currentSelection and returns it.
     */
    private ArrayList<CourseOffering> copyCurrentSelection()
    {
        ArrayList<CourseOffering> copy = new ArrayList<>();
        for (int i = 0; i < currentSelection.size(); i++)
            copy.add(currentSelection.get(i));
        return copy;
    }
    /**
     * Compares the provided CourseOffering with every offering in the currentSelection list, and returns the number of conflicts the list would have after adding the given offering to it.
     * It uses the already built conflict graph to find whether a conflict between two offerings exist or not. It doesn't start finding out conflict by itslef.
     * @param offering 
     */
    private int getNewConflictCount(CourseOffering offering)
    {
        int count = 0;
        for (int i = 0; i < currentSelection.size(); i++)
        {
            if (conflictGraph.hasConflict(offering, currentSelection.get(i)))
                count++;
        }
        return count;
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
     * Uses the DAO the retrieve all the Timeslots in the database, and assigns timeSlotId as the key and the Timeslot object as the value. Essentially, it'd save us the hassle of querying the DB everytime, like it did in the previous approach.
     */
    private void initTimeslotMap()
    {
        timeSlotMap = new HashMap<>();
        TimeSlotDAO dao = new TimeSlotDAOImpl();
        ArrayList<TimeSlot> slots = dao.getAllTimeSlots();
        for (TimeSlot slot : slots)
            timeSlotMap.put(slot.getTimeSlotId(), slot);
    }
    /**
     * Returns whether two OfferingSchedules have a conflict or not. Unlike before, now it doesn't query the DB everytime for the corresponding timeslot of an OfferingSchedule.
     * @param first
     * @param second
     * @return 
     */
    private static boolean twoSchedulesConflict(OfferingSchedule first, OfferingSchedule second)
    {
        if (!first.getDay().equals(second.getDay()))
            return false;

        TimeSlot firstTimeSlot = timeSlotMap.get(first.getTimeSlotId());
        TimeSlot secondTimeSlot = timeSlotMap.get(second.getTimeSlotId());
        
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
        for (int i = 0; i < first.size(); i++)
        {
            for (int j = 0; j < second.size(); j++)
                if (twoSchedulesConflict(first.get(i), second.get(j)))
                    return true;
        }
        return false;
    }
}

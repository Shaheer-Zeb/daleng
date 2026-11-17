package algo;

import datastructures.linear.ArrayList;
import model.CourseOffering;

/**
 * A class that merely encapsulates the ArrayList containing the CourseOfferings recommended by the algo. 
 * @author ShaheerZK
 */
public class Recommendation 
{
    private ArrayList<CourseOffering> selectedOfferings;
    private int conflictCount = 0;
    public Recommendation(ArrayList<CourseOffering> selectedOfferings, int conflictCount)
    {
        this.selectedOfferings = selectedOfferings;
        this.conflictCount = conflictCount;
    }
    /**
     * Returns all the selected offerings in the generated recommendation.
     * @return 
     */
    public ArrayList<CourseOffering> getSelectedOfferings()
    {
        return selectedOfferings;
    }
    /**
     * Returns the number of conflicts in the recommendation.
     * @return 
     */
    public int getConflictCount()
    {
        return conflictCount;
    }
    /**
     * What do you think it does?
     * @return 
     */
    public boolean isConflictFree()
    {
        return conflictCount == 0;
    }
}

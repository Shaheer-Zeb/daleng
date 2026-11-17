package algo;

import datastructures.graph.UnweightedGraph;
import datastructures.linear.ArrayList;
import datastructures.linear.HashMap;
import datastructures.linear.HashSet;
import model.CourseOffering;
import model.OfferingSchedule;

/**
 * An undirected unweighted conflict graph with CourseOfferings as vertices and each edge shows a conflict between two offerings at any point throughout the week.
 * @author ShaheerZK
 */
public class ConflictGraph 
{
    private ArrayList<ArrayList<CourseOffering>> availableOfferings;
    private HashMap<CourseOffering, ArrayList<OfferingSchedule>> offeringScheuldeMap;
    private UnweightedGraph<CourseOffering> conflictGraph = new UnweightedGraph<>(false);
    private HashSet<Conflict> conflictSet = new HashSet();
    
    /**
     * A class to represent which two CourseOfferings have conflict. Its objects will be used in HashSet.
     */
    public static class Conflict
    {
        public CourseOffering firstOffering, secondOffering;

        public Conflict(CourseOffering firstOffering, CourseOffering secondOffering) 
        {
            this.firstOffering = firstOffering;
            this.secondOffering = secondOffering;
        }
        /**
         * Overriding the equals so that we can check if the same Conflict object is already placed in the HashSet or no. So, that we don't add offerings with multiple conflicts only once.
         * And also so that, in the hasConflict() method, we can create a new Conflict object from the two given Offerings and see if the Conflict object corresponding to them exist in the set.
         * @param obj
         * @return 
         */
        @Override
        public boolean equals(Object obj) 
        {
            if (this == obj)
                return true;
            if (obj instanceof Conflict c)
            {
                int id1 = this.firstOffering.getOfferingId();
                int id2 = this.secondOffering.getOfferingId();
                int otherId1 = c.firstOffering.getOfferingId();
                int otherId2 = c.secondOffering.getOfferingId();

                return (id1 == otherId1 && id2 == otherId2) || (id1 == otherId2 && id2 == otherId1);
            }
            return false;
        }
        @Override
        public int hashCode() 
        {
            return Integer.hashCode(firstOffering.getOfferingId()) + Integer.hashCode(secondOffering.getOfferingId());
        }
        
    }
    public ConflictGraph(ArrayList<ArrayList<CourseOffering>> availableOfferings, HashMap<CourseOffering, ArrayList<OfferingSchedule>> offeringScheuldeMap)
    {
        this.availableOfferings = availableOfferings;
        this.offeringScheuldeMap = offeringScheuldeMap;
        buildConflicts();
    }
    /**
     * Builds the conflict graph with the given available offerings and their schedules.
     */
    private void buildConflicts()
    {
        ArrayList<CourseOffering> flattenedOfferings = flattenToSingleArrayList(availableOfferings);
        for (CourseOffering offering : flattenedOfferings)
            addOffering(offering);
        for (int i = 0; i < flattenedOfferings.size(); i++)
        {
            for (int j = i + 1; j < flattenedOfferings.size(); j++)
            {
                CourseOffering firstOffering = flattenedOfferings.get(i);
                CourseOffering secondOffering = flattenedOfferings.get(j);
                
                ArrayList<OfferingSchedule> firstSchedule = offeringScheuldeMap.get(firstOffering);
                ArrayList<OfferingSchedule> secondSchedule = offeringScheuldeMap.get(secondOffering);
                
                if (DalengAlgo.hasConflictInWeek(firstSchedule, secondSchedule))
                    addConflict(firstOffering, secondOffering);
            }
        }
    }
    /**
     * Converts the ArrayList of ArrayLists (a 2D ArrayList basically) to a single ArrayList, so that the algorithm can easily build a conflict graph out of it.
     * @param offerings
     * @return 
     */
    private ArrayList<CourseOffering> flattenToSingleArrayList(ArrayList<ArrayList<CourseOffering>> offerings)
    {
        ArrayList<CourseOffering> flattenedOfferings = new ArrayList<>();
        
        // Won't be using a nested for-each loop for my ArrayList, perhaps the Iterator doesn't work as intended
//        for (ArrayList<CourseOffering> i : offerings)
//            for (CourseOffering offering : i)
//                flattenedOfferings.add(offering);

        for (int i = 0; i < offerings.size(); i++)
        {
            for (int j = 0; j < offerings.get(i).size(); j++)
            {
                flattenedOfferings.add(offerings.get(i).get(j));
            }
        }
        return flattenedOfferings;
    }
    /**
     * Adds an offering as a new vertex in the graph. It doesn't add a CourseOffering if it already exists in the graph.
     * @param offering 
     */
    private void addOffering(CourseOffering offering)
    {
        conflictGraph.addVertex(offering);
    }
    /**
     * Just adds a conflict edge between two vertices (CourseOfferings).
     * @param first
     * @param second 
     */
    private void addConflict(CourseOffering first, CourseOffering second)
    {
        Conflict conflict = new Conflict(first, second);
        if (conflictSet.contains(conflict))
            return;
        conflictSet.add(conflict);
        conflictGraph.addEdge(first, second);
    }
    /**
     * Returns whether the two provided offerings have any conflict or not. Works only on the offerings that currently are the vertices of the conflict graph.
     * @param firstOffering
     * @param secondOffering
     * @return 
     */
    public boolean hasConflict(CourseOffering firstOffering, CourseOffering secondOffering)
    {
        Conflict conflict = new Conflict(firstOffering, secondOffering);
        return conflictSet.contains(conflict);
    }
    /**
     * Returns the offerings that have conflicts.
     * @return 
     */
    public HashSet<Conflict> getConflictingOfferings()
    {
        return conflictSet;
    }
}

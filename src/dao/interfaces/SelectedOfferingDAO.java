package dao.interfaces;

import datastructures.linear.ArrayList;
import model.SelectedOffering;
import model.StudentSelection;

/**
 *
 * @author ShaheerZK
 */
public interface SelectedOfferingDAO 
{
    /**
     * Inserts a new SelectedOffering.
     * @param selectedOffering 
     */
    public void insertSelectedOffering(SelectedOffering selectedOffering);
    
    /**
     * Returns the SelectedOffering based on the selected_offering_id.
     * @param id
     * @return 
     */
    public SelectedOffering getSelectedOfferingById(int id);
    
    /**
     * Returns all the SelectedOfferings for a StudentSelection.
     * @param selection
     * @return 
     */
    public ArrayList<SelectedOffering> getAllSelectedOfferingsByStudentSelection(StudentSelection selection);
    
    /**
     * Updates a preexisting SelectionOffering.
     * @param selectedOfferingId
     * @param offeringId
     * @param courseId
     * @param selectionSource 
     */
    public void updateSelectedOffering(int selectedOfferingId, int offeringId, int courseId, String selectionSource);
    
    /**
     * Deletes a SelectedOffering.
     * @param selectedOfferingId 
     */
    public void deleteSelectedOffering(int selectedOfferingId);
}

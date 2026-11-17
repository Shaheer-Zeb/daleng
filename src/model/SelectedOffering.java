package model;

/**
 *
 * @author ShaheerZK
 */
public class SelectedOffering
{
    private int selectedOfferingId;
    private int selectedCourseId;
    private int courseId; // Now, I know that having courseId in both SelectedCourse and SelectedOffering may look redundant, but it's essential to ensure that SelectedCourse.course_id = SelectedOffering.course_id;
    private int offeringId;
    private String selectionSource;
    
    public SelectedOffering(int selectedOfferingId, int selectedCourseId, int courseId, int offeringId, String selectionSource)
    {
        this.selectedOfferingId = selectedOfferingId;
        this.selectedCourseId = selectedCourseId;
        this.courseId = courseId;
        this.offeringId = offeringId;
        this.selectionSource = selectionSource;
    }
    public SelectedOffering(int selectedCourseId, int courseId, int offeringId, String selectionSource)
    {
        this(0, selectedCourseId, courseId, offeringId, selectionSource);
    }

    public int getSelectedOfferingId() 
    {
        return selectedOfferingId;
    }

    public void setSelectedOfferingId(int selectedOfferingId) 
    {
        this.selectedOfferingId = selectedOfferingId;
    }

    public int getSelectedCourseId()
    {
        return selectedCourseId;
    }

    public void setSelectedCourseId(int selectedCourseId) 
    {
        this.selectedCourseId = selectedCourseId;
    }

    public int getCourseId()
    {
        return courseId;
    }

    public void setCourseId(int courseId)
    {
        this.courseId = courseId;
    }

    public int getOfferingId() 
    {
        return offeringId;
    }

    public void setOfferingId(int offeringId) 
    {
        this.offeringId = offeringId;
    }

    public String getSelectionSource()
    {
        return selectionSource;
    }

    public void setSelectionSource(String selectionSource) 
    {
        this.selectionSource = selectionSource;
    }    
}

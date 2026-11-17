package model;

/**
 * Again, a POJO representing a student's selected course.
 * @author ShaheerZK
 */
public class SelectedCourse 
{
    private int selectedCourseId;
    private int selectionId;
    private int courseId;
    
    public SelectedCourse(int selectedCourseId, int selectionId, int courseId)
    {
        this.selectedCourseId = selectedCourseId;
        this.selectionId = selectionId;
        this.courseId = courseId;
    }
    public SelectedCourse (int selectionId, int courseId)
    {
        this(0, selectionId, courseId);
    }

    public int getSelectedCourseId() 
    {
        return selectedCourseId;
    }

    public void setSelectedCourseId(int selectedCourseId)
    {
        this.selectedCourseId = selectedCourseId;
    }

    public int getSelectionId()
    {
        return selectionId;
    }

    public void setSelectionId(int selectionId) 
    {
        this.selectionId = selectionId;
    }

    public int getCourseId() 
    {
        return courseId;
    }

    public void setCourseId(int courseId) 
    {
        this.courseId = courseId;
    }  
}

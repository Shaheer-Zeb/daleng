package model;

/**
 * The POJO class for the associative entity between Course and CourseScheme.
 * @author ShaheerZK
 */
public class SchemeCourse 
{
    private int schemeId;
    private int courseId;
    
    public SchemeCourse(int schemeId, int courseId)
    {
        this.schemeId = schemeId;
        this.courseId = courseId;
    }

    public int getSchemeId() 
    {
        return schemeId;
    }

    public void setSchemeId(int schemeId) 
    {
        this.schemeId = schemeId;
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

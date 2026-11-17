package model;

/**
 * A POJO class.
 * @author ShaheerZK
 */
public class CourseOffering 
{
    private int offeringId;
    private int courseId;
    private int termId;
    private int teacherId;
    private String sectionName;

    public CourseOffering(int offeringId, int courseId, int termId, int teacherId, String sectionName)
    {
        this.offeringId = offeringId;
        this.courseId = courseId;
        this.termId = termId;
        this.teacherId = teacherId;
        this.sectionName = sectionName;
    }
    public CourseOffering(int courseId, int termId, int teacherId, String sectionName) 
    {
        this(0, courseId, termId, teacherId, sectionName);
    }
    
    public int getOfferingId() 
    {
        return offeringId;
    }

    public void setOfferingId(int offeringId)
    {
        this.offeringId = offeringId;
    }

    public int getCourseId() 
    {
        return courseId;
    }

    public void setCourseId(int courseId) 
    {
        this.courseId = courseId;
    }

    public int getTermId() 
    {
        return termId;
    }

    public void setTermId(int termId) 
    {
        this.termId = termId;
    }

    public int getTeacherId() 
    {
        return teacherId;
    }

    public void setTeacherId(int teacherId) 
    {
        this.teacherId = teacherId;
    }

    public String getSectionName() 
    {
        return sectionName;
    }

    public void setSectionName(String sectionName) 
    {
        this.sectionName = sectionName;
    }    
}

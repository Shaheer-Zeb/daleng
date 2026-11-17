package model;

/**
 * The POJO class that maps the Course entity.
 * @author ShaheerZK
 */
public class Course 
{
    private int courseId;
    private String courseCode;
    private String courseName;
    private double creditHours;
    
    public Course(int courseId, String courseCode, String courseName, double creditHours)
    {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHours = creditHours;
    }
    public Course(String courseCode, String courseName, double creditHours)
    {
        this(0, courseCode, courseName, creditHours);
    }

    public int getCourseId() 
    {
        return courseId;
    }

    public void setCourseId(int courseId) 
    {
        this.courseId = courseId;
    }

    public String getCourseCode() 
    {
        return courseCode;
    }

    public void setCourseCode(String courseCode)
    {
        this.courseCode = courseCode;
    }

    public String getCourseName() 
    {
        return courseName;
    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public double getCreditHours() 
    {
        return creditHours;
    }

    public void setCreditHours(double creditHours) 
    {
        this.creditHours = creditHours;
    }
    
}

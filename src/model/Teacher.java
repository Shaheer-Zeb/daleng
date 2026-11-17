package model;

/**
 * POJO for Teacher.
 * @author ShaheerZK
 */
public class Teacher 
{
    private int teacherId;
    private String teacherName;
    
    public Teacher(int teacherId, String teacherName)
    {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }
    public Teacher(String teacherName)
    {
        this(0, teacherName);
    }

    public int getTeacherId()
    {
        return teacherId;
    }

    public void setTeacherId(int teacherId) 
    {
        this.teacherId = teacherId;
    }

    public String getTeacherName() 
    {
        return teacherName;
    }

    public void setTeacherName(String teacherName) 
    {
        this.teacherName = teacherName;
    }
}

package model;

/**
 * The POJO class that maps the Program entity.
 * @author ShaheerZK
 */
public class Program
{
    private int programId;
    private int departmentId;
    private String programName;
    
    public Program(int programId, int departmentId, String programName)
    {
        this.programId = programId;
        this.departmentId = departmentId;
        this.programName = programName;
    }
    public Program (int departmentId, String programName)
    {
        this(0, departmentId, programName);
    }

    public int getProgramId() 
    {
        return programId;
    }

    public void setProgramId(int programId)
    {
        this.programId = programId;
    }

    public int getDepartmentId()
    {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) 
    {
        this.departmentId = departmentId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) 
    {
        this.programName = programName;
    }
    
}

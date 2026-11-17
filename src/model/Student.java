package model;

/**
 *
 * @author ShaheerZK
 */
public class Student 
{
    private int studentId;
    private String regNo;
    private String studentName;
    private int programId;
    private String cohortCode;
    private String email;
    private String passwordHash; // I'm thinking perhaps we should create a basic hashing algo of our own ;)
    
    public Student (int studentId, String regNo, String studentName, int programId, String cohortCode, String email, String passwordHash)
    {
        this.studentId = studentId;
        this.regNo = regNo;
        this.studentName = studentName;
        this.programId = programId;
        this.cohortCode = cohortCode;
        this.email = email;
        this.passwordHash = passwordHash;
    }
    public Student (String regNo, String studentName, int programId, String cohortCode, String email, String passwordHash)
    {
        this(0, regNo, studentName, programId, cohortCode, email, passwordHash);
    }

    public int getStudentId()
    {
        return studentId;
    }

    public void setStudentId(int studentId)
    {
        this.studentId = studentId;
    }

    public String getRegNo() 
    {
        return regNo;
    }

    public void setRegNo(String regNo) 
    {
        this.regNo = regNo;
    }

    public String getStudentName() 
    {
        return studentName;
    }

    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }

    public int getProgramId() 
    {
        return programId;
    }

    public void setProgramId(int programId) 
    {
        this.programId = programId;
    }

    public String getCohortCode()
    {
        return cohortCode;
    }

    public void setCohortCode(String cohortCode) 
    {
        this.cohortCode = cohortCode;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getPasswordHash() 
    {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash)
    {
        this.passwordHash = passwordHash;
    }
}

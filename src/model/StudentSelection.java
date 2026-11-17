package model;

/**
 * The POJO representation of database entity, StudentSelection.
 * @author ShaheerZK
 */
public class StudentSelection 
{
    private int selectionId;
    private int studentId;
    private int termId;
    
    public StudentSelection(int selectionid, int studentId, int termId)
    {
        this.selectionId = selectionid;
        this.studentId = studentId;
        this.termId = termId;
    }
    public StudentSelection(int studentid, int termId)
    {
        this(0, studentid, termId);
    }

    public int getSelectionId()
    {
        return selectionId;
    }

    public void setSelectionId(int selectionId) 
    {
        this.selectionId = selectionId;
    }

    public int getStudentId() 
    {
        return studentId;
    }

    public void setStudentId(int studentId) 
    {
        this.studentId = studentId;
    }

    public int getTermId() 
    {
        return termId;
    }

    public void setTermId(int termId) 
    {
        this.termId = termId;
    }
    
}

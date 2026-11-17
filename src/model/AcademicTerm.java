package model;

import java.time.LocalDate;
/**
 * The POJO class that maps the AcademicTerm entity.
 * @author ShaheerZK
 */
public class AcademicTerm 
{
    private int termId;
    private String termCode;
    private LocalDate startDate, endDate;
    
    public AcademicTerm(int termId, String termCode, LocalDate startDate, LocalDate endDate)
    {
        this.termId = termId;
        this.termCode = termCode;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public AcademicTerm (String termCode, LocalDate startDate, LocalDate endDate)
    {
        this(0, termCode, startDate, endDate);
    }

    public int getTermId() 
    {
        return termId;
    }

    public void setTermId(int termId) 
    {
        this.termId = termId;
    }

    public String getTermCode()
    {
        return termCode;
    }

    public void setTermCode(String termCode) 
    {
        this.termCode = termCode;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) 
    {
        this.startDate = startDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public void setEndDate(LocalDate endDate)
    {
        this.endDate = endDate;
    }
    
}

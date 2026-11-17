package model;

/**
 * The POJO class that maps the CourseScheme entity.
 * @author ShaheerZK
 */
public class CourseScheme 
{
    private int schemeId;
    private int programId;
    private int termId;
    private String schemeName;
    
    public CourseScheme(int schemeId, int programId, int termId, String schemeName)
    {
        this.schemeId = schemeId;
        this.programId = programId;
        this.termId = termId;
        this.schemeName = schemeName;
    }
    public CourseScheme(int programId, int termId, String schemeName)
    {
        this(0, programId, termId, schemeName);
    }

    public int getSchemeId() 
    {
        return schemeId;
    }

    public void setSchemeId(int schemeId) 
    {
        this.schemeId = schemeId;
    }

    public int getProgramId() 
    {
        return programId;
    }

    public void setProgramId(int programId)
    {
        this.programId = programId;
    }

    public int getTermId()
    {
        return termId;
    }

    public void setTermId(int termId) 
    {
        this.termId = termId;
    }

    public String getSchemeName() 
    {
        return schemeName;
    }

    public void setSchemeName(String schemeName) 
    {
        this.schemeName = schemeName;
    }
    
}

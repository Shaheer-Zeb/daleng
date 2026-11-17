package dao.interfaces;

import datastructures.linear.ArrayList;
import model.AcademicTerm;

/**
 *
 * @author ShaheerZK
 */
public interface AcademicTermDAO 
{
    /**
     * Why would this even require documentation?!
     * @param academicTerm 
     */
    public void insertAcademicTerm(AcademicTerm academicTerm);
    
    /**
     * Returns the AcademicTerm object if found, null otherwise.
     * @param id
     * @return 
     */
    public AcademicTerm getAcademicTermById(int id);
    
    /**
     * It returns the AcademicTerm essentially by the name of the term, e.g. FA25.
     * @param code
     * @return 
     */
    public AcademicTerm getAcademicTermByCode(String code);
    
    /**
     * Returns all the AcademicTerms in the database (I wouldn't use the word table because the underlying database could be non-relational as well).
     * @return 
     */
    public ArrayList<AcademicTerm> getAllAcademicTerms();
}

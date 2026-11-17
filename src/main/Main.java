package main;

import algo.ConflictGraph;
import algo.DalengAlgo;
import algo.PasswordHashing;
import dao.AcademicTermDAOImpl;
import dao.CourseDAOImpl;
import dao.CourseOfferingDAOImpl;
import dao.CourseSchemeDAOImpl;
import dao.DepartmentDAOImpl;
import dao.OfferingScheduleDAOImpl;
import dao.ProgramDAOImpl;
import dao.SchemeCourseDAOImpl;
import dao.StudentDAOImpl;
import dao.TeacherDAOImpl;
import dao.TimeSlotDAOImpl;
import dao.interfaces.AcademicTermDAO;
import dao.interfaces.CourseDAO;
import dao.interfaces.CourseOfferingDAO;
import dao.interfaces.CourseSchemeDAO;
import dao.interfaces.DepartmentDAO;
import dao.interfaces.OfferingScheduleDAO;
import dao.interfaces.ProgramDAO;
import dao.interfaces.SchemeCourseDAO;
import dao.interfaces.StudentDAO;
import dao.interfaces.TeacherDAO;
import dao.interfaces.TimeSlotDAO;
import datastructures.linear.ArrayList;
import java.time.LocalTime;
import model.Course;
import model.CourseOffering;
import model.CourseScheme;
import model.Department;
import model.OfferingSchedule;
import model.Program;
import model.Student;
import model.TimeSlot;

/**
 * @author ShaheerZK
 */

public class Main
{
    public static void main(String[] args) 
    {
        ArrayList<Course> selectedCourses = new ArrayList<>();
        CourseDAO dao = new CourseDAOImpl();
        
        selectedCourses.add(dao.getCourseById(1));
        selectedCourses.add(dao.getCourseById(2));
        selectedCourses.add(dao.getCourseById(3));
        
        DalengAlgo algo = new DalengAlgo(selectedCourses);
        ConflictGraph graph = algo.conflictGraph;
        System.out.println(graph.getConflictingOfferings().size());
    }
}
package dao;

import dao.interfaces.SchemeCourseDAO;
import database.DBConnection;
import datastructures.linear.ArrayList;
import model.Course;
import model.CourseScheme;
import model.SchemeCourse;
import java.sql.*;

/**
 *
 * @author ShaheerZK
 */
public class SchemeCourseDAOImpl implements SchemeCourseDAO
{

    @Override
    public void insertSchemeCourse(SchemeCourse schemeCourse)
    {
        String insertQuery = "INSERT INTO scheme_course (scheme_id, course_id) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(insertQuery))
        {
            ps.setInt(1, schemeCourse.getSchemeId());
            ps.setInt(2, schemeCourse.getCourseId());
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Course> getAllCoursesInScheme(CourseScheme courseScheme) 
    {
        // I'll let this commented implementation with N+1 query problem, just for learning purposes :)
//        ArrayList<Course> courses = new ArrayList<>();
//        String selectCourseSchemeQuery = "SELECT * FROM scheme_course WHERE scheme_id = (?)";
//        
//        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectCourseSchemeQuery))
//        {
//            ps.setInt(1, courseScheme.getSchemeId());
//            ResultSet courseSchemeRs = ps.executeQuery();
//            
//            while (courseSchemeRs.next())
//            {
//                int courseId = courseSchemeRs.getInt("course_id");
//                String selectCourseQuery = "SELECT * FROM course WHERE course_id = (?)";
//                PreparedStatement coursePs = con.prepareStatement(selectCourseQuery);
//                coursePs.setInt(1, courseId);
//                
//                ResultSet courseRs = coursePs.executeQuery();
//                while (courseRs.next())
//                    courses.add(new Course(courseRs.getInt("course_id"), courseRs.getString("course_code"), courseRs.getString("course_name"), courseRs.getDouble("credit_hours")));
//            }
//        }
//        catch (SQLException ex)
//        {
//            ex.printStackTrace();
//        }
//        return courses;
        ArrayList<Course> courses = new ArrayList<>();
        String selectJoinQuery = "SELECT * FROM course JOIN scheme_course ON course.course_id = scheme_course.course_id WHERE scheme_course.scheme_id = ?";
        
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectJoinQuery))
        {
            ps.setInt(1, courseScheme.getSchemeId());
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                courses.add(new Course(rs.getInt("course_id"), rs.getString("course_code"), rs.getString("course_name"), rs.getDouble("credit_hours")));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return courses;
    }

    @Override
    public ArrayList<CourseScheme> getAllSchemesWithCourse(Course course) 
    {
        // Again, the same for this one. I'll keep the naive implemnation with JOIN.
//        ArrayList<CourseScheme> schemes = new ArrayList<>();
//        String selectSchemeCourseQuery = "SELECT * FROM scheme_course WHERE course_id = (?)";
//        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectSchemeCourseQuery))
//        {
//            ps.setInt(1, course.getCourseId());
//            ResultSet schemeCourseRs = ps.executeQuery();
//            while (schemeCourseRs.next())
//            {
//                
//                int schemeId = schemeCourseRs.getInt("scheme_id");
//                String selectSchemeQuery = "SELECT * FROM course_scheme WHERE scheme_id = (?)";
//                PreparedStatement schemePs = con.prepareStatement(selectSchemeQuery);
//                schemePs.setInt(1, schemeId);
//                ResultSet schemeRs = schemePs.executeQuery();
//                while (schemeRs.next())
//                    schemes.add(new CourseScheme(schemeRs.getInt("scheme_id"), schemeRs.getInt("program_id"), schemeRs.getInt("term_id"), schemeRs.getString("scheme_name")));
//            }
//        }
//        catch (SQLException ex)
//        {
//            ex.printStackTrace();
//        }
//        return schemes;
        ArrayList<CourseScheme> schemes = new ArrayList<>();
        String selectJoinQuery = "SELECT * FROM course_scheme JOIN scheme_course ON course_scheme.scheme_id = scheme_course.scheme_id WHERE course_id = ?";
        
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(selectJoinQuery))
        {
            ps.setInt(1, course.getCourseId());
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                schemes.add(new CourseScheme(rs.getInt("scheme_id"), rs.getInt("program_id"), rs.getInt("term_id"), rs.getString("scheme_name")));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return schemes;
    }
}

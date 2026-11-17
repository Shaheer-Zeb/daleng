package dao.interfaces;

import datastructures.linear.ArrayList;
import model.Teacher;

/**
 * I ain't going to document what these methods do, their working is avowed by their name.
 * @author ShaheerZK
 */
public interface TeacherDAO
{
    public void insertTeacher(Teacher teacher);
    public Teacher getTeacherById(int id);
    public Teacher getTeacherByName(String name);
    public ArrayList<Teacher> getAllTeachers();
}

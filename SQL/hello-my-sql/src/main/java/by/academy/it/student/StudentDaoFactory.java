package by.academy.it.student;

import by.academy.it.mysql.StudentDAO;

import java.security.InvalidParameterException;
import java.sql.SQLException;

public class StudentDaoFactory {
    private static StudentDAO studentDAO;
    public static StudentDAO getStudentDao(String baseName) throws SQLException {
        if ("mysql".equals(baseName)){
            if (studentDAO == null) studentDAO = new StudentDAO();
            return studentDAO;
        }
       throw new InvalidParameterException("no such database");
    }
}

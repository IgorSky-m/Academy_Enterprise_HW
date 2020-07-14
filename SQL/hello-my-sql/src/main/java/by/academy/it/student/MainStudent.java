package by.academy.it.student;


import by.academy.it.interfaces.IStudentDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainStudent {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(MainStudent.class.getName());

        try {
            GenerateLists.initializeLists();
            IStudentDao studentDao = StudentDaoFactory.getStudentDao("mysql");
            for (int i = 0; i < 1000; i++) {
                StudentDTO student = GenerateLists.generateRandomStudent();
                studentDao.create(student);
            }
        } catch (SQLException | IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);

        }
    }

}

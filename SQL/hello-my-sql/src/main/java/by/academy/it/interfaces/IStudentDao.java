package by.academy.it.interfaces;

import by.academy.it.ClientDto;
import by.academy.it.student.StudentDTO;

import java.sql.SQLException;
import java.util.List;

public interface IStudentDao {

    int create(StudentDTO student) throws SQLException;

    ClientDto read (int id);

    List<ClientDto> readAll ();

    void update(StudentDTO student);

    boolean delete(StudentDTO student);

    boolean delete(int id);

    int getLastId();
}

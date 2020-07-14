package by.academy.it.mysql;

import by.academy.it.ClientDto;
import by.academy.it.interfaces.IStudentDao;
import by.academy.it.student.StudentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDAO implements IStudentDao {
    private final Connection connection;

    public StudentDAO() throws SQLException {
        connection = MySqlDataSource.getConnection();
    }






    @Override
    public int create(StudentDTO student) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO client.students VALUES (?,?,?,?,?,?,?)")) {
            int id = getLastId();
            preparedStatement.setInt(1,student.getId());
            preparedStatement.setString(2,student.getName());
            preparedStatement.setString(3,student.getSurname());
            preparedStatement.setInt(4,student.getAge());
            preparedStatement.setString(5,String.valueOf(student.getGender()));
            preparedStatement.setInt(6,student.getCourse());
            preparedStatement.setString(7,student.getSpecialization());

            boolean create = preparedStatement.execute();
            if (create) return id;
        }

        return 0;
    }



    @Override
    public ClientDto read(int id) {
        return null;
    }

    @Override
    public List<ClientDto> readAll() {
        return null;
    }

    @Override
    public void update(StudentDTO student) {

    }

    @Override
    public boolean delete(StudentDTO student) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public int getLastId() {
        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT MAX(id) FROM client.students")){
            while (resultSet.next()) {
                int i= resultSet.getInt(resultSet.getRow());
                if ( i > 0) return i;
            }
        } catch (SQLException th) {
            th.printStackTrace();
        }
        return 0;
    }


}


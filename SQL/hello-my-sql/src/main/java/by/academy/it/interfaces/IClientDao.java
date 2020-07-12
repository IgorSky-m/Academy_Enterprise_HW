package by.academy.it.interfaces;


import java.sql.SQLException;
import java.util.List;

public interface IClientDao {

    int create(IClientDto clientDto) throws SQLException;

    IClientDto read (Integer id) throws SQLException;

    List<IClientDto> readAll () throws SQLException;

    boolean update(IClientDto clientDto) throws SQLException;

    boolean delete(IClientDto clientDto) throws SQLException;

    boolean delete(Integer id) throws SQLException;

    int getLastId() throws SQLException;

}

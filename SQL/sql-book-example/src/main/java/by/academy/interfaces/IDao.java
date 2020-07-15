package by.academy.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IDao<E> {
    int create(E obj) throws SQLException;
    E read(Integer id) throws SQLException;
    List<E> readAll() throws SQLException;
    boolean update (E obj) throws SQLException;
    boolean delete (E obj) throws SQLException;
    boolean delete (Integer id) throws SQLException;
}

package by.academy.it;

import java.sql.SQLException;
import java.util.List;

public interface IClientDao {

    int create(ClientDto clientDto) throws SQLException;

    ClientDto read (int id);

    List<ClientDto> readAll ();

    void update(ClientDto clientDto);

    boolean delete(ClientDto clientDto);

    boolean delete(int id);

}

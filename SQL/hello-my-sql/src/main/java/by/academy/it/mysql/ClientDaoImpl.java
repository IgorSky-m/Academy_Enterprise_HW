package by.academy.it.mysql;

import by.academy.it.ClientDto;
import by.academy.it.IClientDao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDaoImpl implements IClientDao {
    private static final Logger logger = Logger.getLogger("ClientDaoImpl log");

    private final Connection connection;

    public ClientDaoImpl() throws SQLException {
        this.connection= MySqlDataSource.getConnection();
    }


    @Override
    public int create(ClientDto clientDto) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO client.clients values (?,?,?,?,?,?)"))
        {
            preparedStatement.setInt(1,clientDto.getId());
            preparedStatement.setString(2,clientDto.getName());
            preparedStatement.setString(3,clientDto.getSecondName());
            preparedStatement.setString(4,clientDto.getEmail());
            preparedStatement.setDate(5,clientDto.getDateOfBirth());
            preparedStatement.setString(6,Character.toString(clientDto.getGender()));

            boolean result = preparedStatement.execute();
            if(result) return clientDto.getId();

        }catch (SQLException e) {
         logger.log(Level.SEVERE,e.getMessage(),e);
        }
        return 0;
    }

    @Override
    public ClientDto read(int id) {
        ClientDto clientDto = new ClientDto();
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM client.clients WHERE id = ?")){
            preparedStatement.setInt(1,id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    clientDto.setId(resultSet.getInt(1));
                    clientDto.setName(resultSet.getString(2));
                    clientDto.setSecondName(resultSet.getString(3));
                    clientDto.setEmail(resultSet.getString(4));
                    clientDto.setDateOfBirth(resultSet.getDate(5));
                    clientDto.setGender(resultSet.getString(6).charAt(0));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE,e.getMessage(),e);
        }
        return clientDto;
    }

    @Override
    public List<ClientDto> readAll() {
        List<ClientDto> clientsList = new LinkedList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM client.clients")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ClientDto clientDto = new ClientDto();
                    clientDto.setId(resultSet.getInt(1));
                    clientDto.setName(resultSet.getString(2));
                    clientDto.setSecondName(resultSet.getString(3));
                    clientDto.setEmail(resultSet.getString(4));
                    clientDto.setDateOfBirth(resultSet.getDate(5));
                    clientDto.setGender(resultSet.getString(6).charAt(0));
                    clientsList.add(clientDto);
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE,e.getMessage(),e);
        }
        return clientsList;
    }
    @Override
    public void update(ClientDto clientDto) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE client.clients " +
                "set name = ?, second_name = ?, email = ?, date_of_birth = ?, gender = ? WHERE id = ?"))
        {
            preparedStatement.setString(1,clientDto.getName());
            preparedStatement.setString(2,clientDto.getSecondName());
            preparedStatement.setString(3,clientDto.getEmail());
            preparedStatement.setDate(4,clientDto.getDateOfBirth());
            preparedStatement.setString(5,Character.toString(clientDto.getGender()));
            preparedStatement.setInt(6,clientDto.getId());

            boolean result = preparedStatement.execute();
            if (result) logger.log(Level.SEVERE,clientDto.getId()+" can't updated");

        }catch (SQLException e) {
            logger.log(Level.SEVERE,e.getMessage(),e);
        }
    }

    @Override
    public boolean delete(ClientDto clientDto) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM client.clients WHERE id = ?"))
        {
            preparedStatement.setInt(1,clientDto.getId());

            boolean result = preparedStatement.execute();
            if (result) logger.log(Level.SEVERE,clientDto.getId()+" can't updated");

            return result;
        }catch (SQLException e) {
            logger.log(Level.SEVERE,e.getMessage(),e);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        ClientDto clientDto = read(id);
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM client.clients WHERE id = ?"))
        {
            preparedStatement.setInt(1,clientDto.getId());

            boolean result = preparedStatement.execute();
            if (result) logger.log(Level.SEVERE,clientDto.getId()+" can't deleted");
            return result;
        }catch (SQLException e) {
            logger.log(Level.SEVERE,e.getMessage(),e);
        }
        return false;
    }


    public int getLastId() {
        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT MAX(id) FROM client.clients")){
            while (resultSet.next()) {
                return resultSet.getInt(resultSet.getRow());
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE,e.getMessage(),e);
        }
        return 0;
    }
}

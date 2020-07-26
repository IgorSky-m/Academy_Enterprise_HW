package by.academy.it.mysql;

import by.academy.it.ClientDto;
import by.academy.it.exceptions.InvalidCreationException;
import by.academy.it.interfaces.IClientDao;
import by.academy.it.interfaces.IClientDto;
import by.academy.it.utill.Validator;


import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class ClientDaoImpl implements IClientDao {
    private static final Logger logger = Logger.getLogger(ClientDaoImpl.class.getName());
    private final boolean isTestInstance;
    private final Connection connection;
    private static final String WORK_CLIENTS_TABLE = "client.clients";
    private static final String CLIENT_TEST_CLIENTS = "client_test.clients";

    public ClientDaoImpl() throws SQLException {
        this.isTestInstance = false;
        this.connection= MySqlDataSource.getConnection();
    }

    public ClientDaoImpl(boolean isTestInstance) throws SQLException {
        this.isTestInstance = isTestInstance;
        if (isTestInstance) this.connection = MySqlDataSource.getTestConnection();
        else this.connection= MySqlDataSource.getConnection();
    }


   @Override
    public int create(IClientDto clientDto) throws SQLException {
        if (clientDto == null) throw new InvalidCreationException("creation is unsuccessful. client can't be null");
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " +
                getSqlTableName(this.isTestInstance) + " values (?,?,?,?,?,?)")) {
            Integer clientId = clientDto.getId();
            if (isIdNotExist(clientId)) {
                preparedStatement.setInt(1, clientId);
                preparedStatement.setString(2, clientDto.getName());
                preparedStatement.setString(3, clientDto.getSecondName());

                String email = clientDto.getEmail();
                if (Validator.validateEmail(email) || !(Validator.validateString(email))) {
                    preparedStatement.setString(4, clientDto.getEmail());
                } else throw new InvalidParameterException("incorrect email");

                preparedStatement.setDate(5, clientDto.getDateOfBirth());

                Character gender = clientDto.getGender();
                if (gender == null) preparedStatement.setString(6,null);
                else preparedStatement.setString(6,gender.toString());


                boolean result = preparedStatement.execute();
                if (!result) return clientDto.getId();
            }
        }
        return 0;
    }

    @Override
    public IClientDto read(Integer id) throws SQLException {
        if (isIdNotExist(id)) throw new InvalidParameterException("can't read. record does't exist");
        List<IClientDto> clientDtoList = new LinkedList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " +
                getSqlTableName(this.isTestInstance) + " WHERE id = ?")) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                clientDtoList.addAll(parseResultSet(resultSet));
                if (!(clientDtoList.isEmpty())) {
                    return clientDtoList.get(0);
                } else throw new InvalidParameterException("record is empty");

            }
        }
    }

    @Override
    public List<IClientDto> readAll() throws SQLException{
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM "+
                getSqlTableName(this.isTestInstance))) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return parseResultSet(resultSet);
                }
            }
        }

    @Override
    public boolean update(IClientDto clientDto) throws SQLException {
        if (isIdNotExist(clientDto.getId())) throw new InvalidParameterException("record does't exist");

        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("UPDATE " + getSqlTableName(this.isTestInstance)+
                " set name = ?, second_name = ?, email = ?, date_of_birth = ?, gender = ? WHERE id = ?")) {

            preparedStatement.setString(1,clientDto.getName());
            preparedStatement.setString(2,clientDto.getSecondName());

            String email = clientDto.getEmail();
            if (Validator.validateEmail(email) || !(Validator.validateString(email))) {
                preparedStatement.setString(3, clientDto.getEmail());
            } else throw new InvalidParameterException("incorrect email");

            preparedStatement.setDate(4,clientDto.getDateOfBirth());

            Character gender = clientDto.getGender();

            if (gender == null) preparedStatement.setString(5,null);
            else preparedStatement.setString(5,gender.toString());

            preparedStatement.setInt(6,clientDto.getId());

            boolean result = preparedStatement.execute();
            if (result) logger.severe("can't update");
            return !result;
        }
    }

    @Override
    public boolean delete(IClientDto clientDto) throws SQLException {
            return delete(clientDto.getId());
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        if (isIdNotExist(id)) throw new InvalidParameterException("can't delete. record does't exist");
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM "+
                getSqlTableName(this.isTestInstance)+" WHERE id = ?")) {

            preparedStatement.setInt(1,id);
            boolean result = preparedStatement.execute();
            if (result) logger.severe("can't delete");
            return !result;
        }
    }



    public int getLastId() throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(id) FROM "+
                getSqlTableName(this.isTestInstance))) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(resultSet.getRow());
                }
                return 0;
            }
        }
    }

    private List<IClientDto> parseResultSet (ResultSet resultSet) throws SQLException {
        List<IClientDto> clientsList = new ArrayList<>();
        while (resultSet.next()) {
            IClientDto clientDto = new ClientDto();
            clientDto.setId(resultSet.getInt(1));
            clientDto.setName(resultSet.getString(2));
            clientDto.setSecondName(resultSet.getString(3));
            clientDto.setEmail(resultSet.getString(4));
            clientDto.setDateOfBirth(resultSet.getDate(5));
            String gender = resultSet.getString(6);
            if (gender == null) clientDto.setGender(null);
            else clientDto.setGender(resultSet.getString(6).charAt(0));
            clientsList.add(clientDto);
        }
        return clientsList;
    }



    private boolean isIdNotExist(Integer id) throws SQLException {
        if (id == null) throw new InvalidParameterException("ID can't be null");
        if (id < 0 ) throw new InvalidParameterException("ID can't be negative");
        if (id == 0 ) throw new InvalidParameterException("ID can't be equals '0'");

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM "+
                getSqlTableName(this.isTestInstance) + " WHERE ID = ?")) {
            preparedStatement.setInt(1,id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                return !resultSet.next();
            }
        }
    }

    private String getSqlTableName(boolean isTestInstance) {
        return (isTestInstance ? CLIENT_TEST_CLIENTS : WORK_CLIENTS_TABLE);
    }


}

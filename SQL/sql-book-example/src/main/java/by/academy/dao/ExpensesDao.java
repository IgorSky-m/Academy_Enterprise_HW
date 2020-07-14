package by.academy.dao;

import by.academy.dto.ExpensesDto;
import by.academy.exceptions.NullDtoParameterException;
import by.academy.interfaces.IDao;
import by.academy.sql.MySQLDataSource;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ExpensesDao implements IDao<ExpensesDto> {
    private final boolean isTestInstance;
    private final Connection connection;
    private static final Map<Boolean,String> tableMap;
    static {
        tableMap = new HashMap<>();
        tableMap.put(false,"expenses_data.expenses");
        tableMap.put(true,"expenses_test_data.expenses");
    }

    public ExpensesDao() throws SQLException {
        this.isTestInstance = false;
        this.connection = MySQLDataSource.getConnection();

    }

    public ExpensesDao(boolean isTestInstance) throws SQLException {
        this.isTestInstance = isTestInstance;
        this.connection = isTestInstance ? MySQLDataSource.getTestConnection() : MySQLDataSource.getConnection();

    }
//
//    id INT NOT NULL,
//    paydate DATE NOT NULL,
//    receiver INT NOT NULL,
//    value DECIMAL(10,3) NOT NULL,

    @Override
    public int create(ExpensesDto obj) throws SQLException {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO "+ tableMap.get(isTestInstance)+ " VALUES (?,?,?,?)")) {
            Integer id = obj.getId();
            if (!isIdNotExist(id)) throw new InvalidParameterException("id already use in database");
            if ( obj.getDate() == null || obj.getReceiver() == null || obj.getValue() == null ) throw new NullDtoParameterException();
            preparedStatement.setInt(1,id);
            preparedStatement.setDate(2,obj.getDate());
            preparedStatement.setInt(3,obj.getReceiver());
            preparedStatement.setBigDecimal(4,obj.getValue());
            boolean res = preparedStatement.execute();
            if (!res) return id;
        }
        return 0;

    }

    @Override
    public ExpensesDto read(Integer id) throws SQLException {
        List<ExpensesDto> dtoList;
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM "+tableMap.get(isTestInstance)+" WHERE id = ?")) {
            if (isIdNotExist(id)) throw new InvalidParameterException("id " + id+" doesn't exist");
            preparedStatement.setInt(1,id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    dtoList = parseResultSet(resultSet);
                    if (!dtoList.isEmpty()) return dtoList.get(0);
                }
        }
        throw new InvalidParameterException("can't read");
    }

    @Override
    public List<ExpensesDto> readAll() throws SQLException {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM "+tableMap.get(isTestInstance))){
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return parseResultSet(resultSet);
            }
        }
    }

    @Override
    public boolean update(ExpensesDto obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(ExpensesDto obj) throws SQLException {
        return false;
    }

    @Override
    public List<ExpensesDto> readSqlParam(String sqlRequest) throws SQLException {
        return null;
    }

    private boolean isIdNotExist(Integer id) throws SQLException {
        if (id == null) throw new InvalidParameterException("ID can't be null");
        if (id < 0 ) throw new InvalidParameterException("ID can't be negative");
        if (id == 0 ) throw new InvalidParameterException("ID can't be equals '0'");

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM "+
                tableMap.get(isTestInstance) + " WHERE ID = ?")) {
            preparedStatement.setInt(1,id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                return !resultSet.next();
            }
        }
    }


    private List<ExpensesDto> parseResultSet(ResultSet resultSet) throws SQLException {
        List<ExpensesDto> dtoList = new LinkedList<>();
        while (resultSet.next()) {
            ExpensesDto expensesDto = new ExpensesDto();
            expensesDto.setId(resultSet.getInt(1));
            expensesDto.setDate(resultSet.getDate(2));
            expensesDto.setReceiver(resultSet.getInt(3));
            expensesDto.setValue(resultSet.getBigDecimal(4));
            dtoList.add(expensesDto);
        }
        return dtoList;
    }
}

package by.academy.dao;

import by.academy.interfaces.IDao;
import by.academy.sql.MySQLDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReceiversDao implements IDao<ReceiversDao> {
    private final boolean isTestInstance;
    private final Connection connection;
    private static final Map<Boolean,String> tableMap;
    static {
        tableMap = new LinkedHashMap<>();
        tableMap.put(true,"expenses_test_data.receivers");
        tableMap.put(false,"expenses_data.receivers");
    }

    public ReceiversDao() throws SQLException {
        this.isTestInstance = false;
        this.connection = MySQLDataSource.getConnection();
    }

    public ReceiversDao(boolean isTestInstance) throws SQLException {
        this.isTestInstance = isTestInstance;
        this.connection = isTestInstance ? MySQLDataSource.getTestConnection() : MySQLDataSource.getConnection();
    }


    @Override
    public int create(ReceiversDao obj) throws SQLException {
        return 0;
    }

    @Override
    public ReceiversDao read(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<ReceiversDao> readAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(ReceiversDao obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(ReceiversDao obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        return false;
    }

}

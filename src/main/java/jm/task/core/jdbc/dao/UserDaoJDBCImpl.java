package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.util.Util;
import java.sql.*;

public class UserDaoJDBCImpl implements UserDao {

    private static final Connection connection = Util.open();

    public void createUsersTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS usersJDBC (id BIGSERIAL PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "last_name VARCHAR(255), " +
                "age INT)");
    }

    public void dropUsersTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS usersJDBC");
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO usersJDBC (name, last_name, age) VALUES (?, ?, ?)");
        pstm.setString(1, name);
        pstm.setString(2, lastName);
        pstm.setByte(3, age);
        pstm.executeUpdate();
    }

    public void removeUserById(long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM usersJDBC WHERE id = ?");
        statement.setLong(1, id);
        statement.executeUpdate();
    }

    public ResultSet getAllUsers() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM usersJDBC");
        return resultSet;
    }

    public void cleanUsersTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("TRUNCATE TABLE usersJDBC");
    }
}

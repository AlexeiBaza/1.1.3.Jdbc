package jm.task.core.jdbc.service;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoJDBCImpl();
    public void createUsersTable() {
        try {
            userDao.createUsersTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Таблица создана");
    }
    public void dropUsersTable() {
        try {
            userDao.dropUsersTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Таблица удалена");
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            userDao.saveUser(name, lastName, age);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        try {
            userDao.removeUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("User с id – " + id + " удален из базы данных");
    }
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = userDao.getAllUsers();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public void cleanUsersTable() {
        try {
            userDao.cleanUsersTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Таблица очищена");
    }
}

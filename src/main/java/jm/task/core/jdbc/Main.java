package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("name1", "lastName1", (byte) 10);
        userService.saveUser("name2", "lastName2", (byte) 20);
        userService.saveUser("name3", "lastName3", (byte) 30);
        userService.saveUser("name4", "lastName4", (byte) 40);

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}

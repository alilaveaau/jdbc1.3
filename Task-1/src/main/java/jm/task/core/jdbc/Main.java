package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        userService.createUsersTable();

        userService.saveUser("Джон", "Сноу", (byte)27);
        userService.saveUser("Дейнерис", "Таргариен", (byte)25);
        userService.saveUser("Тирион", "Ланнистер", (byte)40);
        userService.saveUser("Арья", "Старк", (byte)20);

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}

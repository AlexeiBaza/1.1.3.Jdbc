package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection connection;

    private static final String PASSWORD_KEY = "db.password";
    private static final String USERNAME_KEY = "db.username";
    private static final String URL_KEY = "db.url";

    static {
        loadDriver();
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
            /*загрузка класса драйвера в память JVM (metaspace память). Таким образом не будет исключения у DriverManager
            даже если работаем до java 1.8 */
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public static Connection open() {
        try {
            connection = DriverManager.getConnection(
                 PropertiesUtil.get(URL_KEY),//получаем значения из Properties файла
                 PropertiesUtil.get(USERNAME_KEY),
                 PropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException();//пробрасываем исключение т.к если класса Driver не нашли то пробрасываем дальше и дальше
        }
        return connection;
    }
}

package jm.task.core.jdbc.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();//сюда загружаем все файылы из resources

    static {
        loadProperties();
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);//метод возвращающий properties по ключу, по сути value из ассоциативного массива
    }

    private static void loadProperties() {
        try (InputStream inputStream = PropertiesUtil.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);//чтобы приложение упало если не прочтет application.properties файл
        }
    }

    private PropertiesUtil() {
    }
}

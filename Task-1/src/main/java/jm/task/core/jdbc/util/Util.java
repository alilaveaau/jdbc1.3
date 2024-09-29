package jm.task.core.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static Connection connection = null;
    private static Util instance = null;

    private Util() {
        try {
            if (null == connection || connection.isClosed()) {
                Properties props = getProperties();
                connection = DriverManager
                        .getConnection(props.getProperty("db.url"), props.getProperty("db.username"), props.getProperty("db.password"));
            }
        }catch (SQLException | IOException e){
            e.printStackTrace();
        }
    }
    public static Util getInstance(){
        if (null == instance){
            instance = new Util();
        }
        return instance;
    }
    public Connection getConnection(){
        return connection;
    }

    private static Properties getProperties() throws IOException{
        Properties properties = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get(Util.class.getResource("/database.properties").toURI()))){
            properties.load(in);
            return properties;
        }catch (IOException | URISyntaxException e){
            throw new IOException("Конфигуратор базы данных не найден", e);
        }
    }

}

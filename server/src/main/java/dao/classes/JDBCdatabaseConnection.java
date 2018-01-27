package dao.classes;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static dao.constants.JdbcDataBaseConnectionConstants.*;

//TODO Kommentare auf englisch übersetzen
/**
 * Class to create JDBC database connection
 *
 */
public class JDBCdatabaseConnection {
    private Properties properties;

    /**
     * Hier wird die Properties Datei db_config.properties geoeffnet und eine Referenz dafuer angelegt
     */
    public JDBCdatabaseConnection() {
        properties = new Properties();
        InputStream propertiesReader;

        try {
            propertiesReader = JDBCdatabaseConnection.class.getClassLoader().getResourceAsStream(PROPERTIES_NAME);
            properties.load(propertiesReader);

            Class.forName(properties.getProperty(CLASS_NAME));
        } catch (ClassNotFoundException e) {
            System.err.println(ERR_MSG_DRIVER_NOT_FOUND);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println(ERR_MSG_PROPERTIES);
            e.printStackTrace();
        }
    }

    /**
     * Methode um ein Verbindungsobjekt, mit den Verbindungsdaten zur SQLite DB, zurueckzugeben
     *
     * @return Connection Connection Objekt, mit dem man Abfragen auf der DB machen kann
     */
    protected Connection getConnection() {
        Connection con = null;
        String url;

        try {
            url = properties.getProperty(URL);
            con = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.err.println(ERR_MSG_CONNECTION);
            e.printStackTrace();
        }

        return con;
    }
}

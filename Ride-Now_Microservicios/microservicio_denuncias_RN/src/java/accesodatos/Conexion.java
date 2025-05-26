
package accesodatos;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kevin
 */
public class Conexion {
    public static Connection getConexion() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Class.forName("io.github.cdimascio.dotenv.Dotenv");
        
        Dotenv dotEnv = Dotenv.load();
        String user = dotEnv.get("USER");
        String host = dotEnv.get("URL");
        String password = dotEnv.get("PASSWORD");
        return DriverManager.getConnection(host, user, password);
    }
} 
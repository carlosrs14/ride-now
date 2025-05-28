
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
    private static Conexion instancia;
    private Connection conexion;

    private Conexion() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Dotenv dotEnv = Dotenv.load();
        String user = dotEnv.get("USER");
        String host = dotEnv.get("URL");
        String password = dotEnv.get("PASSWORD");

        conexion = DriverManager.getConnection(host, user, password);
    }

    public static Conexion getInstancia() throws SQLException, ClassNotFoundException {
        if (instancia == null || instancia.getConexion().isClosed()) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }
} 
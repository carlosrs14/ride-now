
package models.DAOs;

import accesodatos.Conexion;
import com.ridenow.models.Cliente;
import com.ridenow.models.PrestadorDeServicio;
import models.Denuncia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class DenunciaDAO implements DAO<Denuncia> {

    public DenunciaDAO() {
    }

    @Override
    public Denuncia create(Denuncia denuncia) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO denuncias "
                + "(fecha, descripcion, estado, idcliente, idprestadordeservicio) "
                + "VALUES (?, ?, ?, ?, ?) RETURNING iddenuncia";
        Connection conexion = Conexion.getConexion();
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setDate(1, Date.valueOf(denuncia.getFecha()));
        statement.setString(2, denuncia.getDescripcion());
        statement.setString(3, denuncia.getEstado());
        statement.setInt(4, denuncia.getCliente().getId());
        statement.setInt(5, denuncia.getPrestadorDeServicio().getId());
        
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            denuncia.setId(result.getInt("iddenuncia"));
            return denuncia;
        } else {
            return null;
        }
    }

    @Override
    public Denuncia get(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM denuncias WHERE iddenuncia = ?;";
        Connection conexion = Conexion.getConexion();
        PreparedStatement statement = conexion.prepareStatement(sql);

        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            return new Denuncia(
                    result.getInt("iddenuncia"),
                    String.valueOf(result.getDate("fecha")),
                    result.getString("descripcion"),
                    result.getString("estado"),
                    new Cliente(result.getInt("idcliente")),
                    new PrestadorDeServicio(result.getInt("idprestadordeservicio"))
            );
        }
        return null;
    }

    @Override
    public List<Denuncia> getAll() throws SQLException, ClassNotFoundException {
        List<Denuncia> denuncias = new ArrayList<>();
        
        String sql = "SELECT * FROM denuncias;";
        Connection conexion = Conexion.getConexion();
        PreparedStatement statement = conexion.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            denuncias.add(
                new Denuncia(
                    result.getInt("iddenuncia"),
                    String.valueOf(result.getDate("fecha")),
                    result.getString("descripcion"),
                    result.getString("estado"),
                    new Cliente(result.getInt("idcliente")),
                    new PrestadorDeServicio(result.getInt("idprestadordeservicio"))
                )
            );
        }
        return denuncias;
    }

    @Override
    public boolean update(Denuncia denuncia) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE denuncias SET descripcion = ?, estado = ? WHERE iddenuncia = ?";
        Connection conexion = Conexion.getConexion();
        PreparedStatement statement = conexion.prepareStatement(sql);

        statement.setString(1, denuncia.getDescripcion());
        statement.setString(2, denuncia.getEstado());
        statement.setInt(3, denuncia.getId());

        int filasAfectadas = statement.executeUpdate();
        return filasAfectadas > 0;
    }

    @Override
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM denuncias WHERE iddenuncia = ?";
        Connection conexion = Conexion.getConexion();
        PreparedStatement statement = conexion.prepareStatement(sql);

        statement.setInt(1, id);
        return statement.executeUpdate() > 0;
    }

    @Override
    public List<Denuncia> filterByOwner(int idCliente) throws SQLException, ClassNotFoundException {
        List<Denuncia> denuncias = new ArrayList<>();
        
        String sql = "SELECT * FROM denuncias WHERE idcliente = ?;";
        Connection conexion = Conexion.getConexion();
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setInt(1, idCliente);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            denuncias.add(
                new Denuncia(
                    result.getInt("iddenuncia"),
                    String.valueOf(result.getDate("fecha")),
                    result.getString("descripcion"),
                    result.getString("estado"),
                    new Cliente(result.getInt("idcliente")),
                    new PrestadorDeServicio(result.getInt("idprestadordeservicio"))
                )
            );
        }
        return denuncias;
    }
} 
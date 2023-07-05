package org.proyecto.dao.Implementaciones;

import org.proyecto.Conexion;
import org.proyecto.dao.MetodoDePagoDao;
import org.proyecto.dto.MetodoDePago;

import java.sql.*;
import java.util.ArrayList;

public class MetodoDePagoDaoImple extends MetodoDePagoDao {
    @Override
    public int insert(MetodoDePago obj) throws Exception {
        int id = 0;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "INSERT INTO metodo_de_pago (tipo,descripcion) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, obj.getTipo());
            stmt.setString(2, obj.getDescripcion());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

            if (id == 0) {
                throw new Exception("El registro no pudo ser insertado");
            }

            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al insertar el metodo de pago en la base de datos");
        }

        return id;
    }

    @Override
    public void update(MetodoDePago obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "UPDATE metodo_de_pago SET  tipo=?, descripcion=? WHERE idMetodoDePago=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(3, obj.getIdMetodoDePago());
            stmt.setString(1, obj.getTipo());
            stmt.setString(2, obj.getDescripcion());

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar el metodo de pago en la base de datos");
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "DELETE FROM metodo_de_pago WHERE idMetodoDePago=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar el metodo de pago en la base de datos");
        }
    }

    @Override
    public MetodoDePago get(int id) throws Exception {
        MetodoDePago obj = null;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT idMetodoDePago, tipo, descripcion FROM metodo_de_pago WHERE idMetodoDePago=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                obj = new MetodoDePago();
                obj.setIdMetodoDePago(rs.getInt("idMetodoDePago"));
                obj.setTipo(rs.getString("tipo"));
                obj.setDescripcion(rs.getString("descripcion"));
            }

            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener el metodo de pago de la base de datos");
        }

        return obj;
    }

    @Override
    public ArrayList<MetodoDePago> getList() throws Exception {
        ArrayList<MetodoDePago> lista = new ArrayList<>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT idMetodoDePago, tipo, descripcion FROM metodo_de_pago";
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MetodoDePago obj = new MetodoDePago();
                obj.setIdMetodoDePago(rs.getInt("idMetodoDePago"));
                obj.setTipo(rs.getString("tipo"));
                obj.setDescripcion(rs.getString("descripcion"));
                lista.add(obj);
            }

            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de metodos de pago de la base de datos");
        }

        return lista;
    }
}

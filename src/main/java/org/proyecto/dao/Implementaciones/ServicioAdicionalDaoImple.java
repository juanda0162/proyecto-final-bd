package org.proyecto.dao.Implementaciones;

import org.proyecto.Conexion;
import org.proyecto.dao.ServicioAdicionalDao;
import org.proyecto.dto.ServicioAdicional;

import java.sql.*;
import java.util.ArrayList;

public class ServicioAdicionalDaoImple extends ServicioAdicionalDao {
    @Override
    public int insert(ServicioAdicional obj) throws Exception {
        int id = 0;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "INSERT INTO servicio_adicional (nombre,precio) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, obj.getNombre());
            stmt.setFloat(2, obj.getPrecio());
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
            throw new Exception("Error al insertar el servicio adicional en la base de datos");
        }

        return id;
    }

    @Override
    public void update(ServicioAdicional obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "UPDATE servicio_adicional SET  nombre=?, precio=? WHERE idServicioAdicional=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(3, obj.getIdServicioAdicional());
            stmt.setString(1, obj.getNombre());
            stmt.setFloat(2, obj.getPrecio());

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar el servicio adicional en la base de datos");
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "DELETE FROM servicio_adicional WHERE idServicioAdicional=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar el servicio adicional en la base de datos");
        }
    }

    @Override
    public ServicioAdicional get(int id) throws Exception {
        ServicioAdicional obj = null;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM servicio_adicional WHERE idServicioAdicional=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                obj = new ServicioAdicional();
                obj.setIdServicioAdicional(rs.getInt("idServicioAdicional"));
                obj.setNombre(rs.getString("nombre"));
                obj.setPrecio(rs.getFloat("precio"));
            }
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener el servicio adicional de la base de datos");
        }
        return obj;
    }

    @Override
    public ArrayList<ServicioAdicional> getList() throws Exception {
        ArrayList<ServicioAdicional> lista = new ArrayList<ServicioAdicional>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM servicio_adicional";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ServicioAdicional obj = new ServicioAdicional();
                obj.setIdServicioAdicional(rs.getInt("idServicioAdicional"));
                obj.setNombre(rs.getString("nombre"));
                obj.setPrecio(rs.getFloat("precio"));
                lista.add(obj);
            }
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de servicios adicionales de la base de datos");
        }
        return lista;
    }
}
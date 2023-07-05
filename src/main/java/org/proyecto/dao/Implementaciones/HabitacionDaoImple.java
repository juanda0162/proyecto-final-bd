package org.proyecto.dao.Implementaciones;

import org.proyecto.Conexion;
import org.proyecto.dao.HabitacionDao;
import org.proyecto.dto.Habitacion;
import org.proyecto.dto.Tipo;

import java.sql.*;
import java.util.ArrayList;

public class HabitacionDaoImple extends HabitacionDao {
    @Override
    public int insert(Habitacion obj) throws Exception {
        int id = 0;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "INSERT INTO habitacion (estado, idSucursal, idTipo) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, obj.getEstado());
            if (obj.getIdSucursal() <= 0) {
                stmt.setNull(2, Types.INTEGER);
            } else {
                stmt.setInt(2, obj.getIdSucursal());
            }
            if (obj.getIdTipo() <= 0) {
                stmt.setNull(3, Types.INTEGER);
            } else {
                stmt.setInt(3, obj.getIdTipo());
            }


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
            throw new Exception("Error al insertar la habitacion en la base de datos");
        }

        return id;
    }


    @Override
    public Habitacion update(Habitacion obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "UPDATE habitacion SET estado=?, idSucursal=?, idTipo=? WHERE idHabitacion=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(4, obj.getIdHabitacion());
            stmt.setString(1, obj.getEstado());
            if (obj.getIdSucursal() <= 0) {
                stmt.setNull(2, Types.INTEGER);
            } else {
                stmt.setInt(2, obj.getIdSucursal());
            }
            if (obj.getIdTipo() <= 0) {
                stmt.setNull(3, Types.INTEGER);
            } else {
                stmt.setInt(3, obj.getIdTipo());
            }

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar la habitacion en la base de datos");
        }
        return null;
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "DELETE FROM habitacion WHERE idHabitacion=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar la habitacion en la base de datos");
        }

    }

    @Override
    public Habitacion get(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM habitacion WHERE idHabitacion=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Habitacion habitaciones = new Habitacion();
                habitaciones.setIdHabitacion(rs.getInt("idHabitacion"));
                habitaciones.setEstado(rs.getString("estado"));
                habitaciones.setIdSucursal(rs.getInt("idSucursal"));
                habitaciones.setIdTipo(rs.getInt("idTipo"));
                rs.close();
                stmt.close();
                objConexion.desconectar();
                return habitaciones;
            } else {
                rs.close();
                stmt.close();
                objConexion.desconectar();
                throw new Exception("No se encontró la habitacion en la base de datos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la habitacion de la base de datos");
        }
    }

    public ArrayList<Habitacion> getListPorTipo(Tipo tipo) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM habitacion WHERE idTipo=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, tipo.getIdTipo());
            ResultSet rs = stmt.executeQuery();


            ArrayList<Habitacion> habitaciones = new ArrayList<>();
            while (rs.next()) {
                Habitacion habitacion = new Habitacion();
                habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
                habitacion.setEstado(rs.getString("estado"));
                habitacion.setIdSucursal(rs.getInt("idSucursal"));
                habitacion.setIdTipo(rs.getInt("idTipo"));
                habitaciones.add(habitacion);
            }

            rs.close();
            stmt.close();
            objConexion.desconectar();
            return habitaciones;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de habitaciones de la base de datos");
        }
    }

    @Override
    public ArrayList<Habitacion> getList() throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM habitacion";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Habitacion> habitaciones = new ArrayList<>();
            while (rs.next()) {
                Habitacion habitacion = new Habitacion();
                habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
                habitacion.setEstado(rs.getString("estado"));
                habitacion.setIdSucursal(rs.getInt("idSucursal"));
                habitacion.setIdTipo(rs.getInt("idTipo"));
                habitaciones.add(habitacion);
            }

            rs.close();
            stmt.close();
            objConexion.desconectar();
            return habitaciones;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de habitaciones de la base de datos");
        }
    }
}

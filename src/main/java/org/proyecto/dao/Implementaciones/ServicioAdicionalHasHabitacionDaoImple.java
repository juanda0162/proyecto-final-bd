package org.proyecto.dao.Implementaciones;

import org.proyecto.Conexion;
import org.proyecto.dao.ServicioAdicionalHasHabitacionDao;
import org.proyecto.dto.Habitacion;
import org.proyecto.dto.ServicioAdicional;
import org.proyecto.dto.ServicioAdicionalHasHabitacion;

import java.sql.*;
import java.util.ArrayList;

public class ServicioAdicionalHasHabitacionDaoImple extends ServicioAdicionalHasHabitacionDao {
    @Override
    public int insert(ServicioAdicionalHasHabitacion obj) throws Exception {
        int id = 0;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "INSERT INTO servicioadicional_has_habitacion (idServicioAdicional,idHabitacion) VALUES (?,?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            if (obj.getIdServicioAdicional() <= 0) {
                System.out.println("No puede haber una relacion ServicioAdicional_has_habitacion sin servicioAdicional");
            } else {
                stmt.setInt(1, obj.getIdServicioAdicional());
            }
            if (obj.getIdHabitacion() <= 0) {
                System.out.println("No puede haber una relacion ServicioAdicional_has_habitacion sin habitacion");
            } else {
                stmt.setInt(2, obj.getIdHabitacion());
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
            throw new Exception("Error al insertar el ServicioAdicional_has_habitacion en la base de datos");
        }

        return id;
    }

    @Override
    public void update(ServicioAdicionalHasHabitacion obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "UPDATE servicioadicional_has_habitacion SET idServicioAdicional=?, idHabitacion=? WHERE idServicioAdicionalHasHabitaciones=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            if (obj.getIdServicioAdicional() <= 0) {
                System.out.println("No puede haber una relacion ServicioAdicional_has_habitacion sin servicioAdicional");
            } else {
                stmt.setInt(1, obj.getIdServicioAdicional());
            }
            if (obj.getIdHabitacion() <= 0) {
                System.out.println("No puede haber una relacion ServicioAdicional_has_habitacion sin habitacion");
            } else {
                stmt.setInt(2, obj.getIdHabitacion());
            }

            stmt.setInt(3, obj.getIdServicioAdicional_has_Habitacion());
            stmt.executeUpdate();

            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar el ServicioAdicional_has_habitaciones en la base de datos");
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "DELETE FROM servicioadicional_has_habitacion WHERE idServicioAdicionalHasHabitaciones=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar el ServicioAdicional_has_habitaciones en la base de datos");
        }
    }

    @Override
    public ServicioAdicionalHasHabitacion get(int id) throws Exception {
        ServicioAdicionalHasHabitacion obj = null;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT idServicioAdicionalHasHabitaciones, idServicioAdicional, idHabitacion FROM servicioadicional_has_habitacion WHERE idServicioAdicionalHasHabitaciones=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                obj = new ServicioAdicionalHasHabitacion();
                obj.setIdServicioAdicional_has_Habitacion(rs.getInt("idServicioAdicionalHasHabitaciones"));
                obj.setIdServicioAdicional(rs.getInt("idServicioAdicional"));
                obj.setIdHabitacion(rs.getInt("idHabitacion"));
            }

            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener el ServicioAdicional_has_habitaciones de la base de datos");
        }

        return obj;
    }

    @Override
    public ArrayList<ServicioAdicionalHasHabitacion> getListPorServicioAdicional(ServicioAdicional servicioAdicional) throws Exception {
        ArrayList<ServicioAdicionalHasHabitacion> lista = new ArrayList<ServicioAdicionalHasHabitacion>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT idServicioAdicionalHasHabitaciones, idServicioAdicional, idHabitacion FROM servicioadicional_has_habitacion WHERE idServicioAdicional=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, servicioAdicional.getIdServicioAdicional());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ServicioAdicionalHasHabitacion obj = new ServicioAdicionalHasHabitacion();
                obj.setIdServicioAdicional_has_Habitacion(rs.getInt("idServicioAdicionalHasHabitaciones"));
                obj.setIdServicioAdicional(rs.getInt("idServicioAdicional"));
                obj.setIdHabitacion(rs.getInt("idHabitacion"));
                lista.add(obj);
            }

            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener el ServicioAdicional_has_habitaciones de la base de datos");
        }

        return lista;
    }

    @Override
    public ArrayList<ServicioAdicionalHasHabitacion> getListPorHabitacion(Habitacion habitacion) throws Exception {
        ArrayList<ServicioAdicionalHasHabitacion> lista = new ArrayList<ServicioAdicionalHasHabitacion>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT idServicioAdicionalHasHabitaciones, idServicioAdicional, idHabitacion FROM servicioadicional_has_habitacion WHERE idHabitacion=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, habitacion.getIdHabitacion());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ServicioAdicionalHasHabitacion obj = new ServicioAdicionalHasHabitacion();
                obj.setIdServicioAdicional_has_Habitacion(rs.getInt("idServicioAdicionalHasHabitaciones"));
                obj.setIdServicioAdicional(rs.getInt("idServicioAdicional"));
                obj.setIdHabitacion(rs.getInt("idHabitacion"));
                lista.add(obj);
            }

            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener el ServicioAdicional_has_habitaciones de la base de datos");
        }

        return lista;
    }

    @Override
    public ArrayList<ServicioAdicionalHasHabitacion> getList() throws Exception {
        ArrayList<ServicioAdicionalHasHabitacion> lista = new ArrayList<ServicioAdicionalHasHabitacion>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT idServicioAdicionalHasHabitaciones, idServicioAdicional, idHabitacion FROM servicioadicional_has_habitacion";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ServicioAdicionalHasHabitacion obj = new ServicioAdicionalHasHabitacion();
                obj.setIdServicioAdicional_has_Habitacion(rs.getInt("idServicioAdicionalHasHabitaciones"));
                obj.setIdServicioAdicional(rs.getInt("idServicioAdicional"));
                obj.setIdHabitacion(rs.getInt("idHabitacion"));
                lista.add(obj);
            }

            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de ServicioAdicional_has_habitaciones de la base de datos");
        }

        return lista;
    }
}

package org.proyecto.dao.Implementaciones;

import org.proyecto.Conexion;
import org.proyecto.dao.ReservaHasHabitacionDao;
import org.proyecto.dto.Habitacion;
import org.proyecto.dto.Reserva;
import org.proyecto.dto.ReservaHasHabitacion;

import java.sql.*;
import java.util.ArrayList;

public class ReservaHasHabitacionDaoImple extends ReservaHasHabitacionDao {
    @Override
    public int insert(ReservaHasHabitacion obj) throws Exception {
        int id = 0;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "INSERT INTO reserva_has_habitacion (idReserva,idHabitacion,precio) VALUES (?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            if(obj.getIdReserva()<=0){
                System.out.println("No puede haber una relacion Reserva_has_habitacion sin reserva");;}
            else{
                 stmt.setInt(1, obj.getIdReserva());}

            if(obj.getIdHabitacion()<=0){
                System.out.println("No puede haber una relacion Reserva_has_habitacion sin habitacion");;}
            else{
                 stmt.setInt(2, obj.getIdHabitacion());}

            if(obj.getPrecio()<=0){
                System.out.println("No puede haber una relacion Reserva_has_habitacion sin precio");;}
            else{
                 stmt.setFloat(3, obj.getPrecio());
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
            throw new Exception("Error al insertar la Reserva_has_habitacion en la base de datos");
        }

        return id;
    }

    @Override
    public void update(ReservaHasHabitacion obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "UPDATE reserva_has_habitacion SET idReserva=?, idHabitacion=?, precio=? WHERE idReserva_has_habitacion=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            if(obj.getIdReserva()<=0){
                System.out.println("No puede haber una relacion Reserva_has_habitacion sin reserva");;}
            else{
                 stmt.setInt(1, obj.getIdReserva());}

            if(obj.getIdHabitacion()<=0){
                System.out.println("No puede haber una relacion Reserva_has_habitacion sin habitacion");;}
            else{
                 stmt.setInt(2, obj.getIdHabitacion());}

            if(obj.getPrecio()<=0){
                System.out.println("No puede haber una relacion Reserva_has_habitacion sin precio");;}
            else{
                 stmt.setFloat(3, obj.getPrecio());
            }

            stmt.setInt(4, obj.getIdReserva_has_habitacion());
            stmt.executeUpdate();

            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar la Reserva_has_habitacion en la base de datos");
        }
    }



    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "DELETE FROM reserva_has_habitacion WHERE idReserva_has_habitacion=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar la Reserva_has_habitacion en la base de datos");
        }
    }

    @Override
    public ReservaHasHabitacion get(int id) throws Exception {
        ReservaHasHabitacion obj = null;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT idReserva_has_habitacion, idReserva, idHabitacion, precio FROM reserva_has_habitacion WHERE idReserva_has_habitacion=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                obj = new ReservaHasHabitacion();
                obj.setIdReserva_has_habitacion(rs.getInt("idReserva_has_habitacion"));
                obj.setIdReserva(rs.getInt("idReserva"));
                obj.setIdHabitacion(rs.getInt("idHabitacion"));
                obj.setPrecio(rs.getFloat("precio"));
            }
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la Reserva_has_habitacion de la base de datos");
        }
        return obj;
    }

    @Override
    public ArrayList<ReservaHasHabitacion> getListPorReserva(Reserva reserva) throws Exception {
        ArrayList<ReservaHasHabitacion> lista = new ArrayList<ReservaHasHabitacion>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT idReserva_has_habitacion, idReserva, idHabitacion, precio FROM reserva_has_habitacion WHERE idReserva=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, reserva.getIdReserva());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ReservaHasHabitacion obj = new ReservaHasHabitacion();
                obj.setIdReserva_has_habitacion(rs.getInt("idReserva_has_habitacion"));
                obj.setIdReserva(rs.getInt("idReserva"));
                obj.setIdHabitacion(rs.getInt("idHabitacion"));
                obj.setPrecio(rs.getFloat("precio"));
                lista.add(obj);
            }
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la Reserva_has_habitacion de la base de datos");
        }
        return lista;
    }

    @Override
    public ArrayList<ReservaHasHabitacion> getListPorHabitacion(Habitacion habitacion) throws Exception {
        ArrayList<ReservaHasHabitacion> lista = new ArrayList<ReservaHasHabitacion>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT idReserva_has_habitacion, idReserva, idHabitacion, precio FROM reserva_has_habitacion WHERE idHabitacion=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, habitacion.getIdHabitacion());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ReservaHasHabitacion obj = new ReservaHasHabitacion();
                obj.setIdReserva_has_habitacion(rs.getInt("idReserva_has_habitacion"));
                obj.setIdReserva(rs.getInt("idReserva"));
                obj.setIdHabitacion(rs.getInt("idHabitacion"));
                obj.setPrecio(rs.getFloat("precio"));
                lista.add(obj);
            }
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la Reserva_has_habitacion de la base de datos");
        }
        return lista;
    }

    @Override
    public ArrayList<ReservaHasHabitacion> getList() throws Exception {
        ArrayList<ReservaHasHabitacion> lista = new ArrayList<ReservaHasHabitacion>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT idReserva_has_habitacion, idReserva, idHabitacion, precio FROM reserva_has_habitacion";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ReservaHasHabitacion obj = new ReservaHasHabitacion();
                obj.setIdReserva_has_habitacion(rs.getInt("idReserva_has_habitacion"));
                obj.setIdReserva(rs.getInt("idReserva"));
                obj.setIdHabitacion(rs.getInt("idHabitacion"));
                obj.setPrecio(rs.getFloat("precio"));
                lista.add(obj);
            }
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la Reserva_has_habitacion de la base de datos");
        }
        return lista;
    }
}

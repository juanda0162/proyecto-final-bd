package org.proyecto.dao.Implementaciones;

import org.proyecto.Conexion;
import org.proyecto.dao.CheckinDao;
import org.proyecto.dto.Checkin;

import java.sql.*;
import java.util.ArrayList;

public class CheckinDaoImple extends CheckinDao {
    @Override
    public int insert(Checkin obj) throws Exception {
        int id = 0;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "INSERT INTO chekin (registroLlegada,registroSalida,idReservas) VALUES (?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setDate(1, obj.getRegistroLlegada());
            stmt.setDate(2, obj.getRegistroSalida());
            if(obj.getIdReservas() <= 0){
                throw new Exception("El id de la reserva no es valido");
            }else {
                stmt.setInt(3, obj.getIdReservas());
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
            throw new Exception("Error al insertar la cadena hotelera en la base de datos");
        }

        return id;
    }

    @Override
    public void update(Checkin obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "UPDATE chekin SET  registroLlegada=?, registroSalida=?, idReservas=? WHERE idCheckin=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(4, obj.getIdChekin());
            stmt.setDate(1, obj.getRegistroLlegada());
            stmt.setDate(2, obj.getRegistroSalida());
            if(obj.getIdReservas() <= 0){
                throw new Exception("El id de la reserva no es valido");
            }else {
                stmt.setInt(3, obj.getIdReservas());
            }

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar el checkin en la base de datos");
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "DELETE FROM chekin WHERE idCheckin=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar el checkin en la base de datos");
        }
    }

    @Override
    public Checkin get(int id) throws Exception {
        Checkin obj = null;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM chekin WHERE idCheckin=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                obj = new Checkin();
                obj.setIdChekin(rs.getInt("idCheckin"));
                obj.setRegistroLlegada(rs.getDate("registroLlegada"));
                obj.setRegistroSalida(rs.getDate("registroSalida"));
                obj.setIdReservas(rs.getInt("idReservas"));
            }

            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener el checkin de la base de datos");
        }

        return obj;
    }

    @Override
    public Checkin getPorReserva(int id) throws Exception {
        Checkin obj = null;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM chekin WHERE idReservas=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                obj = new Checkin();
                obj.setIdChekin(rs.getInt("idCheckin"));
                obj.setRegistroLlegada(rs.getDate("registroLlegada"));
                obj.setRegistroSalida(rs.getDate("registroSalida"));
                obj.setIdReservas(rs.getInt("idReservas"));
            }

            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener el checkin de la base de datos");
        }

        return obj;
    }

    @Override
    public ArrayList<Checkin> getList() throws Exception {
        ArrayList<Checkin> lista = new ArrayList<Checkin>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM chekin";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Checkin obj = new Checkin();
                obj.setIdChekin(rs.getInt("idCheckin"));
                obj.setRegistroLlegada(rs.getDate("registroLlegada"));
                obj.setRegistroSalida(rs.getDate("registroSalida"));
                obj.setIdReservas(rs.getInt("idReservas"));
                lista.add(obj);
            }
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener los checkin de la base de datos");
        }

        return lista;
    }
}
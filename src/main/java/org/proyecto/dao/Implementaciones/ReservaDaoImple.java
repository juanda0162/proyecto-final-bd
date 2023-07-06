package org.proyecto.dao.Implementaciones;

import org.proyecto.Conexion;
import org.proyecto.dao.ReservaDao;
import org.proyecto.dto.Huesped;
import org.proyecto.dto.MetodoDePago;
import org.proyecto.dto.Reserva;

import java.sql.*;
import java.util.ArrayList;

public class ReservaDaoImple extends ReservaDao {
    @Override
    public int insert(Reserva obj) throws Exception {
        int id = 0;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "INSERT INTO reserva (estado, fechaEntrada,fechaSalida,peticion,cantidadPersonas,idHuesped,idMetodoDePago) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, obj.getEstado());
            stmt.setDate(2, obj.getFechaEntrada());
            stmt.setDate(3, obj.getFechaSalida());
            stmt.setString(4, obj.getPeticion());
            if (obj.getCantidadPersonas() <= 0) {
                stmt.setNull(5, Types.INTEGER);
            } else {
                stmt.setInt(5, obj.getCantidadPersonas());
            }
            if (obj.getIdHuesped() <= 0) {
                System.out.println("No puede habler reserva sin idHuesped");
            } else {
                stmt.setInt(6, obj.getIdHuesped());
            }
            if (obj.getIdMetodoDePago() <= 0) {
                System.out.println("No puede habler reserva sin idMetodoDePago");
            } else {
                stmt.setInt(7, obj.getIdMetodoDePago());
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
    public Reserva update(Reserva obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "UPDATE reserva SET estado=?, fechaEntrada=?, fechaSalida=?, peticion=?, cantidadPersonas=?, idHuesped=?, idMetodoDePago=? WHERE idReserva=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, obj.getEstado());
            stmt.setDate(2, obj.getFechaEntrada());
            stmt.setDate(3, obj.getFechaSalida());
            stmt.setString(4, obj.getPeticion());
            if (obj.getCantidadPersonas() <= 0) {
                stmt.setNull(5, Types.INTEGER);
            } else {
                stmt.setInt(5, obj.getCantidadPersonas());
            }
            if (obj.getIdHuesped() <= 0) {
                System.out.println("No puede habler reserva sin idHuesped");
            } else {
                stmt.setInt(6, obj.getIdHuesped());
            }
            if (obj.getIdMetodoDePago() <= 0) {
                System.out.println("No puede habler reserva sin idMetodoDePago");
            } else {
                stmt.setInt(7, obj.getIdMetodoDePago());
            }
            stmt.setInt(8, obj.getIdReserva());

            stmt.executeUpdate();

            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar la reserva en la base de datos");
        }
        return null;
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "DELETE FROM reserva WHERE idReserva=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            stmt.executeUpdate();

            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar la reserva en la base de datos");
        }
    }

    @Override
    public Reserva get(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM reserva WHERE idReserva=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setIdReserva(rs.getInt("idReserva"));
                reserva.setEstado(rs.getString("estado"));
                reserva.setFechaEntrada(rs.getDate("fechaEntrada"));
                reserva.setFechaSalida(rs.getDate("fechaSalida"));
                reserva.setPeticion(rs.getString("peticion"));
                reserva.setCantidadPersonas(rs.getInt("cantidadPersonas"));
                reserva.setIdHuesped(rs.getInt("idHuesped"));
                reserva.setIdMetodoDePago(rs.getInt("idMetodoDePago"));
                rs.close();
                stmt.close();
                objConexion.desconectar();
                return reserva;

            } else {
                rs.close();
                stmt.close();
                objConexion.desconectar();
                throw new Exception("No se encontró la reserva en la base de datos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la reserva de la base de datos");
        }
    }

    @Override
    public ArrayList<Reserva> getListPorHuesped(Huesped huesped) throws Exception {
        ArrayList<Reserva> reservas = new ArrayList<>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM reserva WHERE idHuesped=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, huesped.getIdHuesped());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setIdReserva(rs.getInt("idReserva"));
                reserva.setEstado(rs.getString("estado"));
                reserva.setFechaEntrada(rs.getDate("fechaEntrada"));
                reserva.setFechaSalida(rs.getDate("fechaSalida"));
                reserva.setPeticion(rs.getString("peticion"));
                reserva.setCantidadPersonas(rs.getInt("cantidadPersonas"));
                reserva.setIdHuesped(rs.getInt("idHuesped"));
                reserva.setIdMetodoDePago(rs.getInt("idMetodoDePago"));
                reservas.add(reserva);
            }

            rs.close();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener las reservas de la base de datos");
        }

        return reservas;
    }

    @Override
    public ArrayList<Reserva> getListPorMetodoDePago(MetodoDePago metodoDePago) throws Exception {
        ArrayList<Reserva> reservas = new ArrayList<>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM reserva WHERE idMetodoDePago=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, metodoDePago.getIdMetodoDePago());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setIdReserva(rs.getInt("idReserva"));
                reserva.setEstado(rs.getString("estado"));
                reserva.setFechaEntrada(rs.getDate("fechaEntrada"));
                reserva.setFechaSalida(rs.getDate("fechaSalida"));
                reserva.setPeticion(rs.getString("peticion"));
                reserva.setCantidadPersonas(rs.getInt("cantidadPersonas"));
                reserva.setIdHuesped(rs.getInt("idHuesped"));
                reserva.setIdMetodoDePago(rs.getInt("idMetodoDePago"));
                reservas.add(reserva);
            }

            rs.close();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener las reservas de la base de datos");
        }

        return reservas;
    }


    @Override
    public ArrayList<Reserva> getList() throws Exception {
        ArrayList<Reserva> reservas = new ArrayList<>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM reserva";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setIdReserva(rs.getInt("idReserva"));
                reserva.setEstado(rs.getString("estado"));
                reserva.setFechaEntrada(rs.getDate("fechaEntrada"));
                reserva.setFechaSalida(rs.getDate("fechaSalida"));
                reserva.setPeticion(rs.getString("peticion"));
                reserva.setCantidadPersonas(rs.getInt("cantidadPersonas"));
                reserva.setIdHuesped(rs.getInt("idHuesped"));
                reserva.setIdMetodoDePago(rs.getInt("idMetodoDePago"));
                reservas.add(reserva);
            }

            rs.close();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener las reservas de la base de datos");
        }

        return reservas;
    }
}

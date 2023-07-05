package org.proyecto.dao.Implementaciones;

import org.proyecto.Conexion;
import org.proyecto.dao.DeudaDao;
import org.proyecto.dto.Deuda;
import org.proyecto.dto.Reserva;

import java.sql.*;
import java.util.ArrayList;

public class DeudaDaoImple extends DeudaDao {
    @Override
    public int insert(Deuda obj) throws Exception {
        int id = 0;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "INSERT INTO deuda (montoTotal,idReserva) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            if (obj.getMontoTotal() <= 0) {
                throw new Exception("El monto total no puede ser 0");
            } else {
                stmt.setFloat(1, obj.getMontoTotal());
            }
            if (obj.getIdReserva() <= 0) {
                throw new Exception("La reserva no puede ser 0");
            } else {
                stmt.setInt(2, obj.getIdReserva());
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
            throw new Exception("Error al insertar la deuda en la base de datos");
        }

        return id;
    }

    @Override
    public void update(Deuda obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "UPDATE deuda SET montoTotal=?, idReserva=? WHERE idDeuda=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            if (obj.getMontoTotal() <= 0) {
                throw new Exception("El monto total no puede ser 0");
            } else {
                stmt.setFloat(1, obj.getMontoTotal());
            }
            if (obj.getIdReserva() <= 0) {
                throw new Exception("La reserva no puede ser 0");
            } else {
                stmt.setInt(2, obj.getIdReserva());
            }
            if (obj.getIdDeuda() <= 0) {
                throw new Exception("La deuda no puede ser 0");
            } else {
                stmt.setInt(3, obj.getIdDeuda());
            }

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar la deuda en la base de datos");
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "DELETE FROM deuda WHERE idDeuda=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar la deuda de la base de datos");
        }
    }

    @Override
    public Deuda get(int id) throws Exception {
        Deuda obj = null;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM deuda WHERE idDeuda=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                obj = new Deuda();
                obj.setIdDeuda(rs.getInt("idDeuda"));
                obj.setMontoTotal(rs.getFloat("montoTotal"));
                obj.setIdReserva(rs.getInt("idReserva"));
            }
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la deuda de la base de datos");
        }
        return obj;
    }

    @Override
    public Deuda getListPorReserva(Reserva reserva) throws Exception {
        Deuda obj = null;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM deuda WHERE idReserva=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, reserva.getIdReserva());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                obj = new Deuda();
                obj.setIdDeuda(rs.getInt("idDeuda"));
                obj.setMontoTotal(rs.getFloat("montoTotal"));
                obj.setIdReserva(rs.getInt("idReserva"));
            }
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la deuda de la base de datos");
        }
        return obj;
    }

    @Override
    public ArrayList<Deuda> getList() throws Exception {
        ArrayList<Deuda> lista = new ArrayList<Deuda>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM deuda";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Deuda obj = new Deuda();
                obj.setIdDeuda(rs.getInt("idDeuda"));
                obj.setMontoTotal(rs.getFloat("montoTotal"));
                obj.setIdReserva(rs.getInt("idReserva"));
                lista.add(obj);
            }
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de deudas de la base de datos");
        }
        return lista;
    }
}

package org.proyecto.dao.Implementaciones;

import org.proyecto.Conexion;
import org.proyecto.dao.CadenaHoteleraDao;
import org.proyecto.dto.CadenaHotelera;

import java.sql.*;
import java.util.ArrayList;

public class CadenaHoteleraDaoImple extends CadenaHoteleraDao {
    @Override
    public int insert(CadenaHotelera obj) throws Exception {
        int id = 0;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "INSERT INTO cadena_hotelera (nombre) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, obj.getNombre());

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
    public void update(CadenaHotelera obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "UPDATE cadena_hotelera SET  nombre=? WHERE idCadenaHotelera=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(2, obj.getIdCadenaHotelera());
            stmt.setString(1, obj.getNombre());

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar la cadena hotelera en la base de datos");
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "DELETE FROM cadena_hotelera WHERE idCadenaHotelera=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar la cadena hotelera en la base de datos");
        }
    }

    @Override
    public CadenaHotelera get(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM cadena_hotelera WHERE idCadenaHotelera=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                CadenaHotelera cadenaHotelera = new CadenaHotelera();
                cadenaHotelera.setIdCadenaHotelera(rs.getInt("idCadenaHotelera"));
                cadenaHotelera.setNombre(rs.getString("nombre"));
                rs.close();
                stmt.close();
                objConexion.desconectar();
                return cadenaHotelera;
            } else {
                rs.close();
                stmt.close();
                objConexion.desconectar();
                throw new Exception("No se encontró la cadena hotelera en la base de datos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la cadena hotelera de la base de datos");
        }
    }

    @Override
    public ArrayList<CadenaHotelera> getList() throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM cadena_hotelera";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<CadenaHotelera> cadenaHoteleras = new ArrayList<>();
            while (rs.next()) {
                CadenaHotelera cadenaHotelera = new CadenaHotelera();
                cadenaHotelera.setIdCadenaHotelera(rs.getInt("idCadenaHotelera"));
                cadenaHotelera.setNombre(rs.getString("nombre"));
                cadenaHoteleras.add(cadenaHotelera);
            }

            rs.close();
            stmt.close();
            objConexion.desconectar();
            return cadenaHoteleras;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de agentes de la base de datos");
        }
    }
}

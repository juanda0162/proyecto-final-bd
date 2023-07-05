package org.proyecto.dao.Implementaciones;

import org.proyecto.Conexion;
import org.proyecto.dao.TipoDao;
import org.proyecto.dto.Tipo;

import java.sql.*;
import java.util.ArrayList;

public class TipoDaoImple extends TipoDao {
    @Override
    public int insert(Tipo obj) throws Exception {
        int id = 0;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "INSERT INTO tipo (nombre,comodidades,precio) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, obj.getNombre());
            stmt.setString(2, obj.getComodidades());
            if(obj.getPrecio() < 0.0){
                throw new Exception("El precio no puede ser negativo");}
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
            throw new Exception("Error al insertar el tipo en la base de datos");
        }

        return id;
    }

    @Override
    public Tipo update(Tipo obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "UPDATE tipo SET nombre=?, comodidades=?, precio=? WHERE idTipo=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(4, obj.getIdTipo());
            stmt.setString(1, obj.getNombre());
            stmt.setString(2, obj.getComodidades());
            if(obj.getPrecio() <= 0.0){
                stmt.setNull(3, Types.FLOAT);}
            else{
                stmt.setFloat(3, obj.getPrecio());
            }

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar el tipo en la base de datos");
        }
        return null;
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "DELETE FROM tipo WHERE idTipo=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar el tipo en la base de datos");
        }
    }

    @Override
    public Tipo get(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM tipo WHERE idTipo=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Tipo tipo = new Tipo();
                tipo.setIdTipo(rs.getInt("idTipo"));
                tipo.setNombre(rs.getString("nombre"));
                tipo.setComodidades(rs.getString("comodidades"));
                tipo.setPrecio(rs.getFloat("precio"));

                rs.close();
                stmt.close();
                objConexion.desconectar();
                return tipo;
            } else {
                rs.close();
                stmt.close();
                objConexion.desconectar();
                throw new Exception("No se encontró el tipo en la base de datos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener el tipo de la base de datos");
        }
    }

    @Override
    public ArrayList<Tipo> getList() throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM tipo";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Tipo> tipos = new ArrayList<>();
            while (rs.next()) {
                Tipo tipo = new Tipo();
                tipo.setIdTipo(rs.getInt("idTipo"));
                tipo.setNombre(rs.getString("nombre"));
                tipo.setComodidades(rs.getString("comodidades"));
                tipo.setPrecio(rs.getFloat("precio"));

                tipos.add(tipo);
            }

            rs.close();
            stmt.close();
            objConexion.desconectar();
            return tipos;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de tipos de la base de datos");
        }
    }
}

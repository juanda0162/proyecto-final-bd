package org.proyecto.dao.Implementaciones;

import org.proyecto.Conexion;
import org.proyecto.dao.ComentarioDao;
import org.proyecto.dto.Checkin;
import org.proyecto.dto.Comentario;

import java.sql.*;
import java.util.ArrayList;

public class ComentarioDaoImple extends ComentarioDao {
    @Override
    public int insert(Comentario obj) throws Exception {
        int id = 0;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "INSERT INTO comentario (descripcion,calificacion,fecha,idChekin) VALUES (?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, obj.getDescripcion());
            stmt.setString(2, obj.getCalificacion());
            stmt.setDate(3, (Date) obj.getFecha());
            if (obj.getIdChekin() > 0) {
                stmt.setInt(4, obj.getIdChekin());
            } else {
                System.out.println("No se ha asignado un checkin al comentario");
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
            throw new Exception("Error al insertar el comentario en la base de datos");
        }

        return id;
    }

    @Override
    public void update(Comentario obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "UPDATE comentario SET  descripcion=?, calificacion=?, fecha=?, idChekin=? WHERE idComentario=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(5, obj.getIdComentario());
            stmt.setString(1, obj.getDescripcion());
            stmt.setString(2, obj.getCalificacion());
            stmt.setDate(3, (Date) obj.getFecha());
            if (obj.getIdChekin() > 0) {
                stmt.setInt(4, obj.getIdChekin());
            } else {
                System.out.println("No se ha asignado un checkin al comentario");
            }

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar el comentario en la base de datos");
        }
    }



    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "DELETE FROM comentario WHERE idComentario=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar el comentario en la base de datos");
        }
    }

    @Override
    public Comentario get(int id) throws Exception {
        Comentario obj = null;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM comentario WHERE idComentario=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                obj = new Comentario();
                obj.setIdComentario(rs.getInt("idComentario"));
                obj.setDescripcion(rs.getString("descripcion"));
                obj.setCalificacion(rs.getString("calificacion"));
                obj.setFecha(rs.getDate("fecha"));
                obj.setIdChekin(rs.getInt("idChekin"));
            }

            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener el comentario de la base de datos");
        }

        return obj;
    }

    @Override
    public ArrayList<Comentario> getListPorChekin(Checkin checkin) throws Exception {
        ArrayList<Comentario> lista = new ArrayList<Comentario>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM comentario WHERE idChekin=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, checkin.getIdChekin());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Comentario obj = new Comentario();
                obj.setIdComentario(rs.getInt("idComentario"));
                obj.setDescripcion(rs.getString("descripcion"));
                obj.setCalificacion(rs.getString("calificacion"));
                obj.setFecha(rs.getDate("fecha"));
                obj.setIdChekin(rs.getInt("idChekin"));
                lista.add(obj);
            }
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de comentarios de la base de datos");
        }

        return lista;
    }

    @Override
    public ArrayList<Comentario> getList() throws Exception {
        ArrayList<Comentario> lista = new ArrayList<Comentario>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM comentario";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Comentario obj = new Comentario();
                obj.setIdComentario(rs.getInt("idComentario"));
                obj.setDescripcion(rs.getString("descripcion"));
                obj.setCalificacion(rs.getString("calificacion"));
                obj.setFecha(rs.getDate("fecha"));
                obj.setIdChekin(rs.getInt("idChekin"));
                lista.add(obj);
            }
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de comentarios de la base de datos");
        }

        return lista;
    }
}

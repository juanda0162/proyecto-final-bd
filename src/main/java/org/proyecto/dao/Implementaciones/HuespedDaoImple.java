package org.proyecto.dao.Implementaciones;

import org.proyecto.Conexion;
import org.proyecto.dao.HuespedDao;
import org.proyecto.dto.Huesped;

import java.sql.*;
import java.util.ArrayList;

public class HuespedDaoImple extends HuespedDao {
    @Override
    public int insert(Huesped obj) throws Exception {
        int id = 0;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "INSERT INTO huesped (ci,nombre,telefono) VALUES (?, ?,?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            if (obj.getCi() <= 0) {
                System.out.println("No puede haber un huesped sin ci");
            } else {
                stmt.setInt(1, obj.getCi());
            }
            stmt.setString(2, obj.getNombre());
            if (obj.getTelefono() <= 0) {
                stmt.setNull(3, Types.INTEGER);
            } else {
                stmt.setInt(3, obj.getTelefono());
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
            throw new Exception("Error al insertar el huesped en la base de datos");
        }

        return id;
    }

    @Override
    public void update(Huesped obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "UPDATE huesped SET ci=?, nombre=?, telefono=? WHERE idHuesped=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            if (obj.getCi() <= 0) {
                System.out.println("No puede haber un huesped sin ci");
            } else {
                stmt.setInt(1, obj.getCi());
            }
            stmt.setString(2, obj.getNombre());
            if (obj.getTelefono() <= 0) {
                stmt.setNull(3, Types.INTEGER);
            } else {
                stmt.setInt(3, obj.getTelefono());
            }
            stmt.setInt(4, obj.getIdHuesped());

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar el huesped en la base de datos");
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "DELETE FROM huesped WHERE idHuesped=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar el huesped en la base de datos");
        }
    }

    @Override
    public Huesped get(int id) throws Exception {
        Huesped obj = null;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM huesped WHERE idHuesped=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                obj = new Huesped();
                obj.setIdHuesped(rs.getInt("idHuesped"));
                obj.setCi(rs.getInt("ci"));
                obj.setNombre(rs.getString("nombre"));
                obj.setTelefono(rs.getInt("telefono"));
            }
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener el huesped de la base de datos");
        }
        return obj;
    }

    @Override
    public ArrayList<Huesped> getList() throws Exception {
        ArrayList<Huesped> lista = new ArrayList<Huesped>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM huesped";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Huesped obj = new Huesped();
                obj.setIdHuesped(rs.getInt("idHuesped"));
                obj.setCi(rs.getInt("ci"));
                obj.setNombre(rs.getString("nombre"));
                obj.setTelefono(rs.getInt("telefono"));
                lista.add(obj);
            }
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de huespedes de la base de datos");
        }
        return lista;
    }
}

package org.proyecto.dao.Implementaciones;

import org.proyecto.Conexion;
import org.proyecto.dao.SucursalDao;
import org.proyecto.dto.CadenaHotelera;
import org.proyecto.dto.Sucursal;

import java.sql.*;
import java.util.ArrayList;

public class SucursalDaoImple extends SucursalDao {
    @Override
    public int insert(Sucursal obj) throws Exception {
        int id = 0;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "INSERT INTO sucursal (nombre,direccion,clasificacion,telefono,idCadenaHotelera) VALUES (?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, obj.getNombre());
            stmt.setString(2, obj.getDireccion());
            stmt.setString(3, obj.getClasificacion());
            stmt.setInt(4, obj.getTelefono());


            if(obj.getIdCadenaHotelera() > 0){
                stmt.setInt(5, obj.getIdCadenaHotelera());
            }else {
                throw new Exception("Una sucursal no puede existir sin Cadena Hotelera");
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
    public Sucursal update(Sucursal obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "UPDATE sucursal SET nombre=?, direccion=?, clasificacion=?, telefono=?, idCadenaHotelera=? WHERE idSucursal=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, obj.getNombre());
            stmt.setString(2, obj.getDireccion());
            stmt.setString(3, obj.getClasificacion());
            stmt.setInt(4, obj.getTelefono());
            stmt.setInt(5, obj.getIdCadenaHotelera());
            stmt.setInt(6, obj.getIdSucursal());

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar la sucursal en la base de datos");
        }

        return null;
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "DELETE FROM sucursal WHERE idSucursal=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar la sucursal en la base de datos");
        }

    }

    @Override
    public Sucursal get(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM sucursal WHERE idSucursal=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Sucursal sucursal = new Sucursal();
                sucursal.setIdSucursal(rs.getInt("idSucursal"));
                sucursal.setNombre(rs.getString("nombre"));
                sucursal.setDireccion(rs.getString("direccion"));
                sucursal.setClasificacion(rs.getString("clasificacion"));
                sucursal.setTelefono(rs.getInt("telefono"));
                sucursal.setIdCadenaHotelera(rs.getInt("idCadenaHotelera"));

                rs.close();
                stmt.close();
                objConexion.desconectar();
                return sucursal;
            } else {
                rs.close();
                stmt.close();
                objConexion.desconectar();
                throw new Exception("No se encontró la sucursal en la base de datos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la sucursal de la base de datos");
        }
    }

    @Override
    public ArrayList<Sucursal> getListPorCadenaHotelera(CadenaHotelera cadenaHotelera) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM sucursal WHERE idCadenaHotelera=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, cadenaHotelera.getIdCadenaHotelera());
            ResultSet rs = stmt.executeQuery();


            ArrayList<Sucursal> sucursales = new ArrayList<>();
            while (rs.next()) {
                Sucursal sucursal = new Sucursal();
                sucursal.setIdSucursal(rs.getInt("idSucursal"));
                sucursal.setNombre(rs.getString("nombre"));
                sucursal.setDireccion(rs.getString("direccion"));
                sucursal.setClasificacion(rs.getString("clasificacion"));
                sucursal.setTelefono(rs.getInt("telefono"));
                sucursal.setIdCadenaHotelera(rs.getInt("idCadenaHotelera"));
                sucursales.add(sucursal);
            }

            rs.close();
            stmt.close();
            objConexion.desconectar();
            return sucursales;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de sucursales de la base de datos");
        }
    }

    @Override
    public ArrayList<Sucursal> getList() throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarMySQL();
            String query = "SELECT * FROM sucursal";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Sucursal> sucursales = new ArrayList<>();
            while (rs.next()) {
                Sucursal sucursal = new Sucursal();
                sucursal.setIdSucursal(rs.getInt("idSucursal"));
                sucursal.setNombre(rs.getString("nombre"));
                sucursal.setDireccion(rs.getString("direccion"));
                sucursal.setClasificacion(rs.getString("clasificacion"));
                sucursal.setTelefono(rs.getInt("telefono"));
                sucursal.setIdCadenaHotelera(rs.getInt("idCadenaHotelera"));
                sucursales.add(sucursal);
            }

            rs.close();
            stmt.close();
            objConexion.desconectar();
            return sucursales;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de sucursales de la base de datos");
        }
    }
}

package org.proyecto;

import org.proyecto.dao.SucursalDao;
import org.proyecto.dao.Implementaciones.SucursalDaoImple;
import org.proyecto.dto.Sucursal;
import org.proyecto.gui.FramePrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Conexion conexion = Conexion.getOrCreate();
        conexion.conectarMySQL();

        SucursalDao sucursalDao = new SucursalDaoImple();
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre("Sucursal 1");
        sucursal.setDireccion("Direccion 1");
        sucursal.setClasificacion("Clasificacion 1");
        sucursal.setTelefono(12345678);
        sucursal.setIdCadenaHotelera(2);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FramePrincipal();
            }
        });
        /*try {
            sucursalDao.insert(sucursal);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        /*try {
            System.out.println(sucursalDao.getList().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }
}
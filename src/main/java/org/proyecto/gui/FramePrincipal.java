package org.proyecto.gui;

import org.proyecto.dao.*;
import org.proyecto.dao.Implementaciones.*;
import org.proyecto.dto.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FramePrincipal extends JFrame {
    private DefaultListModel<String> listModel;
    private JList<String> listTabla;
    private JComboBox<String> tablaComboBox;
    private JButton ingresarButton;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private JTextArea datosTextArea;

    //instancias DAO
    private CadenaHoteleraDao cadenaHoteleraDao;
//    private CheckinDao checkinDao;
    private ComentarioDao comentarioDao;
    private DeudaDao deudaDao;
    private HabitacionDao habitacionDao;
    private HuespedDao huespedDao;
    private MetodoDePagoDao metodoDePagoDao;
    private ReservaDao reservaDao;
    private ReservaHasHabitacionDao reservaHasHabitacionDao;
//    private ServicioAdicionalDao servicioAdicionalDao;
    private ServicioAdicionalHasHabitacionDao servicioAdicionalHasHabitacionDao;
    private SucursalDao sucursalDao;
    private TipoDao tipoDao;



    public FramePrincipal() {

        //Inicializar DAO

        cadenaHoteleraDao = new CadenaHoteleraDaoImple();
//        checkinDao = new CheckinDaoImpl();
        comentarioDao = new ComentarioDaoImple();
        deudaDao = new DeudaDaoImple();
        habitacionDao = new HabitacionDaoImple();
        huespedDao = new HuespedDaoImple();
        metodoDePagoDao = new MetodoDePagoDaoImple();
        reservaDao = new ReservaDaoImple();
        reservaHasHabitacionDao = new ReservaHasHabitacionDaoImple();
//        servicioAdicionalDao = new ServicioAdicionalDaoImpl();
        servicioAdicionalHasHabitacionDao = new ServicioAdicionalHasHabitacionDaoImple();
        sucursalDao = new SucursalDaoImple();
        tipoDao = new TipoDaoImple();


        setTitle("Gestión de Tablas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);

//      configuracion de las listas
        listModel = new DefaultListModel<>();
        listTabla = new JList<>(listModel);
        listTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listTabla);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        add(scrollPane, BorderLayout.WEST);

        // Configurar el JComboBox
        tablaComboBox = new JComboBox<>();
        tablaComboBox.addItem("CADENA_HOTELERA");
        tablaComboBox.addItem("COMENTARIOS");
        tablaComboBox.addItem("DEUDA");
        tablaComboBox.addItem("HABITACION");
        tablaComboBox.addItem("HUESPED");
        tablaComboBox.addItem("METODO_DE_PAGO");
        tablaComboBox.addItem("RESERVA");
        tablaComboBox.addItem("RESERVA_HAS_HABITACION");
        tablaComboBox.addItem("SERVICIO_ADICIONAL");
        tablaComboBox.addItem("SERVICIO_ADICIONAL_HAS_HABITACION");
        tablaComboBox.addItem("SUCURSAL");
        tablaComboBox.addItem("TIPO");

        add(tablaComboBox, BorderLayout.NORTH);

        // Configurar los botones
        ingresarButton = new JButton("Ingresar");
        actualizarButton = new JButton("Actualizar");
        eliminarButton = new JButton("Eliminar");


        // Configurar el JTextArea para mostrar los datos
        datosTextArea = new JTextArea();
        datosTextArea.setEditable(false);
        datosTextArea.setFont(new Font("Arial", Font.PLAIN, 14));

        // Configurar el panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(ingresarButton);
        buttonPanel.add(actualizarButton);
        buttonPanel.add(eliminarButton);
        add(buttonPanel, BorderLayout.SOUTH);

        //ActionListener Combobox
        tablaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entidadSeleccionada = (String) tablaComboBox.getSelectedItem();
                try {
                    cargarTabla(entidadSeleccionada);
                } catch (Exception ex) {
                    System.out.println("Ocurrio un error en presion combobox");
                }
            }
        });


        //ActionListenerBotones
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    insertarRegistro();
                } catch (Exception ex) {
                    System.out.println("Ocurrio un error en presion ingresar boton");
                }
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actualizarRegistro();
                } catch (Exception ex) {
                    System.out.println("Ocurrio un error en presion ingresar boton");
                }
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    EliminarRegistro();
                } catch (Exception ex) {
                    System.out.println("Ocurrio un error al eliminar");
                }
            }
        });
    }

    //metodos
    private void cargarTabla(String entidad) throws Exception {
        listModel.clear();
        switch (entidad) {
            case "CADENA_HOTELERA":
                ArrayList<CadenaHotelera> cadenaHoteleras = cadenaHoteleraDao.getList();
                for (CadenaHotelera cadenaHotelera : cadenaHoteleras) {
                    listModel.addElement(cadenaHotelera.getIdCadenaHotelera() + ". " + cadenaHotelera.getNombre());
                }
                break;
            case "CHECKIN":
                // Agrega aquí el código para cargar la tabla CHECKIN
                break;
            case "COMENTARIO":
                ArrayList<Comentario> comentarios = comentarioDao.getList();
                for (Comentario comentario : comentarios) {
                    listModel.addElement(comentario.getIdComentario() + ". " + comentario.getIdChekin() + " - " + comentario.getDescripcion() + " - " + comentario.getCalificacion());
                }
                break;
            case "DEUDA":
                ArrayList<Deuda> deudas = deudaDao.getList();
                for (Deuda deuda : deudas) {
                    listModel.addElement(deuda.getIdDeuda() + ". " + deuda.getIdReserva() + " - " + deuda.getMontoTotal());
                }
                break;
            case "HABITACION":
                ArrayList<Habitacion> habitaciones = habitacionDao.getList();
                for (Habitacion habitacion : habitaciones) {
                    listModel.addElement(habitacion.getIdHabitacion() + ". " + habitacion.getIdSucursal() + " - " + habitacion.getIdTipo()
                            + " - " + habitacion.getEstado());
                }
                break;
            case "HUESPED":
                ArrayList<Huesped> huespedes = huespedDao.getList();
                for (Huesped huesped : huespedes) {
                    listModel.addElement(huesped.getIdHuesped() + ". " + huesped.getNombre() + " " +
                            huesped.getCi() + " - " + huesped.getTelefono());
                }
                break;
            case "METODO_DE_PAGO":
                ArrayList<MetodoDePago> metodosDePago = metodoDePagoDao.getList();
                for (MetodoDePago metodoDePago : metodosDePago) {
                    listModel.addElement(metodoDePago.getIdMetodoDePago() + ". " + metodoDePago.getDescripcion() + ". " + metodoDePago.getTipo());
                }
                break;
            case "RESERVA":
                ArrayList<Reserva> reservas = reservaDao.getList();
                for (Reserva reserva : reservas) {
                    listModel.addElement(reserva.getIdReserva() + ". " + reserva.getIdHuesped() + " - " + reserva.getCantidadPersonas()
                            + " - " + reserva.getIdMetodoDePago() + " - " + reserva.getFechaEntrada() + " - " + reserva.getFechaSalida() + " - " + reserva.getEstado() + " - " + reserva.getPeticion());
                }
                break;
            case "RESERVA_HAS_HABITACION":
                ArrayList<ReservaHasHabitacion> reservaHasHabitaciones = reservaHasHabitacionDao.getList();
                for (ReservaHasHabitacion reservaHasHabitacion : reservaHasHabitaciones) {
                    listModel.addElement(reservaHasHabitacion.getIdReserva() + ". " + reservaHasHabitacion.getIdHabitacion() + ". " + reservaHasHabitacion.getIdReserva_has_habitacion() + " - " + reservaHasHabitacion.getPrecio());
                }
                break;

            case "SERVICIO_ADICIONAL":
                // Agrega aquí el código para cargar la tabla SERVICIO_ADICIONAL
                break;
            case "SERVICIO_ADICIONAL_HAS_HABITACION":
                ArrayList<ServicioAdicionalHasHabitacion> servicioAdicionalHasHabitaciones = servicioAdicionalHasHabitacionDao.getList();
                for (ServicioAdicionalHasHabitacion servicioAdicionalHasHabitacion : servicioAdicionalHasHabitaciones) {
                    listModel.addElement(servicioAdicionalHasHabitacion.getIdServicioAdicional_has_Habitacion() + ". " +
                            servicioAdicionalHasHabitacion.getIdServicioAdicional() + " - " + servicioAdicionalHasHabitacion.getIdHabitacion());
                }
                break;

            case "SUCURSAL":
                ArrayList<Sucursal> sucursales = sucursalDao.getList();
                for (Sucursal sucursal : sucursales) {
                    listModel.addElement(sucursal.getIdSucursal() + ". " + sucursal.getNombre() + " - " + sucursal.getDireccion());
                }
                break;
            case "TIPO":
                ArrayList<Tipo> tipos = tipoDao.getList();
                for (Tipo tipo : tipos) {
                    listModel.addElement(tipo.getIdTipo() + ". " + tipo.getNombre());
                }
                break;
        }
    }

    private void insertarRegistro() throws Exception {
        String entidadSeleccionada = (String) tablaComboBox.getSelectedItem();
        switch (entidadSeleccionada) {
            case "CADENA_HOTELERA":
                //Implementar lógica para insertar una nueva cadena hotelera
                CadenaHoteleraFormDialog cadenaHoteleraFormDialog = new CadenaHoteleraFormDialog(this, cadenaHoteleraDao, null);
                cadenaHoteleraFormDialog.setVisible(true);
                cargarTabla("CADENA_HOTELERA");
                break;
            case "HUESPED":
                // Implementar lógica para insertar un nuevo huésped
                HuespedFormDialog huespedFormDialog = new HuespedFormDialog(this, huespedDao, null);
                huespedFormDialog.setVisible(true);
                cargarTabla("HUESPED");
                break;
            case "METODO_DE_PAGO":
                // Implementar lógica para insertar un nuevo método de pago
                MetodoDePagoFormDialog metodoDePagoFormDialog = new MetodoDePagoFormDialog(this, metodoDePagoDao, null);
                metodoDePagoFormDialog.setVisible(true);
                cargarTabla("METODO_DE_PAGO");
                break;
        }
    }


    private void actualizarRegistro() throws Exception {
        String entidadSeleccionada = (String) tablaComboBox.getSelectedItem();
        switch (entidadSeleccionada) {
            case "CADENA_HOTELERA":
                // Implementar lógica para actualizar una cadena hotelera
                int indexCadenaHotelera = listTabla.getSelectedIndex();
                if (indexCadenaHotelera != -1) {
                    String cadenaHoteleraItem = listModel.get(indexCadenaHotelera);
                    int idCadenaHotelera = getIdFromItem(cadenaHoteleraItem);
                    CadenaHotelera cadenaHotelera = cadenaHoteleraDao.get(idCadenaHotelera);
                    CadenaHoteleraFormDialog cadenaHoteleraFormDialog = new CadenaHoteleraFormDialog(this, cadenaHoteleraDao, cadenaHotelera);
                    cadenaHoteleraFormDialog.setVisible(true);
                    cargarTabla("Paciente");
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un paciente para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "HUESPED":
                int indexHuesped = listTabla.getSelectedIndex();
                if (indexHuesped != -1) {
                    String huespedItem = listModel.get(indexHuesped);
                    int idHuesped = getIdFromItem(huespedItem);
                    Huesped huesped = huespedDao.get(idHuesped);
                    HuespedFormDialog huespedFormDialog = new HuespedFormDialog(this, huespedDao, huesped);
                    huespedFormDialog.setVisible(true);
                    cargarTabla("HUESPED");
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un huésped para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "METODO_DE_PAGO":
                int indexMetodoDePago = listTabla.getSelectedIndex();
                if (indexMetodoDePago != -1) {
                    String metodoDePagoItem = listModel.get(indexMetodoDePago);
                    int idMetodoDePago = getIdFromItem(metodoDePagoItem);
                    MetodoDePago metodoDePago = metodoDePagoDao.get(idMetodoDePago);
                    MetodoDePagoFormDialog metodoDePagoFormDialog = new MetodoDePagoFormDialog(this, metodoDePagoDao, metodoDePago);
                    metodoDePagoFormDialog.setVisible(true);
                    cargarTabla("METODO_DE_PAGO");
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un método de pago para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

        }
    }
    //Eliminar
    private void EliminarRegistro() throws Exception {
        String entidadSeleccionada = (String) tablaComboBox.getSelectedItem();
        switch (entidadSeleccionada){
            case "CADENA_HOTELERA":
                int indexCadenaHotelera = listTabla.getSelectedIndex();
                if (indexCadenaHotelera != -1) {
                    int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar la cadena hotelera?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        String cadenaHoteleraItem = listModel.get(indexCadenaHotelera);
                        int idCadenaHotelera = getIdFromItem(cadenaHoteleraItem);
                        cadenaHoteleraDao.delete(idCadenaHotelera);
                        cargarTabla("CADENA_HOTELERA");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione una cadena hotelera para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "HUESPED":
                int indexHuesped = listTabla.getSelectedIndex();
                if (indexHuesped != -1) {
                    int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el huésped?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        String huespedItem = listModel.get(indexHuesped);
                        int idHuesped = getIdFromItem(huespedItem);
                        huespedDao.delete(idHuesped);
                        cargarTabla("HUESPED");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un huésped para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "METODO_DE_PAGO":
                int indexMetodoDePago = listTabla.getSelectedIndex();
                if (indexMetodoDePago != -1) {
                    int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el método de pago?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        String metodoDePagoItem = listModel.get(indexMetodoDePago);
                        int idMetodoDePago = getIdFromItem(metodoDePagoItem);
                        metodoDePagoDao.delete(idMetodoDePago);
                        cargarTabla("METODO_DE_PAGO");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un método de pago para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }


    private int getIdFromItem(String item) {
        int index = item.indexOf(".");
        return Integer.parseInt(item.substring(0, index));
    }

    private int getCiFromItem(String item) {
        int index = item.indexOf(".");
        return Integer.parseInt(item.substring(0, index));
    }
}


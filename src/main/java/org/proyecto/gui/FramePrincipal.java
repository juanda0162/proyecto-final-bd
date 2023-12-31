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
    private CheckinDao checkinDao;
    private ComentarioDao comentarioDao;
    private DeudaDao deudaDao;
    private HabitacionDao habitacionDao;
    private HuespedDao huespedDao;
    private MetodoDePagoDao metodoDePagoDao;
    private ReservaDao reservaDao;
    private ReservaHasHabitacionDao reservaHasHabitacionDao;
    private ServicioAdicionalDao servicioAdicionalDao;
    private ServicioAdicionalHasHabitacionDao servicioAdicionalHasHabitacionDao;
    private SucursalDao sucursalDao;
    private TipoDao tipoDao;



    public FramePrincipal() {

        //Inicializar DAO

        cadenaHoteleraDao = new CadenaHoteleraDaoImple();
        checkinDao = new CheckinDaoImple();
        comentarioDao = new ComentarioDaoImple();
        deudaDao = new DeudaDaoImple();
        habitacionDao = new HabitacionDaoImple();
        huespedDao = new HuespedDaoImple();
        metodoDePagoDao = new MetodoDePagoDaoImple();
        reservaDao = new ReservaDaoImple();
        reservaHasHabitacionDao = new ReservaHasHabitacionDaoImple();
        servicioAdicionalDao = new ServicioAdicionalDaoImple();
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
        tablaComboBox.addItem("CHECKIN");
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
                ArrayList<Checkin> checkins = checkinDao.getList();
                for (Checkin checkin : checkins) {
                    listModel.addElement(checkin.getIdChekin() + ". Registro Llegada: " + checkin.getRegistroLlegada() + " - Registro Salida: " + checkin.getRegistroSalida() + " - ID Reservas: " + checkin.getIdReservas());
                }
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
                    String reservaInfo = reserva.getIdReserva() + ". " +
                            "| Huesped: " + reserva.getIdHuesped() + " " +
                            "| Cantidad de Personas: " + reserva.getCantidadPersonas() + " |" + reserva.getIdMetodoDePago() + " " +
                            "| Fecha de Entrada: " + reserva.getFechaEntrada() + " " +
                            "| Fecha de Salida: " + reserva.getFechaSalida() + " " +
                            "| Estado: " + reserva.getEstado() + " " +
                            "| Petición: " + reserva.getPeticion();
                    listModel.addElement(reservaInfo);
                }
                break;
            case "RESERVA_HAS_HABITACION":
                ArrayList<ReservaHasHabitacion> reservaHasHabitaciones = reservaHasHabitacionDao.getList();
                for (ReservaHasHabitacion reservaHasHabitacion : reservaHasHabitaciones) {
                    listModel.addElement(reservaHasHabitacion.getIdReserva() + ". " + reservaHasHabitacion.getIdHabitacion() + ". " + reservaHasHabitacion.getIdReserva_has_habitacion() + " - " + reservaHasHabitacion.getPrecio());
                }
                break;

            case "SERVICIO_ADICIONAL":
                ArrayList<ServicioAdicional> serviciosAdicionales = servicioAdicionalDao.getList();
                for (ServicioAdicional servicioAdicional : serviciosAdicionales) {
                    listModel.addElement(servicioAdicional.getNombre() + " - Precio: " + servicioAdicional.getPrecio());
                }
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
            case "CHECKIN":
                // Implementar lógica para insertar un nuevo check-in
                CheckinFormDialog checkinFormDialog = new CheckinFormDialog(this, checkinDao, null);
                checkinFormDialog.setVisible(true);
                cargarTabla("CHECKIN");
                break;
            case "COMENTARIO":
                // Implementar lógica para insertar un nuevo comentario
                ComentarioFormDialog comentarioFormDialog = new ComentarioFormDialog(this, comentarioDao, null);
                comentarioFormDialog.setVisible(true);
                cargarTabla("COMENTARIO");
                break;
            case "DEUDA":
                // Implementar lógica para insertar una nueva deuda
                DeudaFormDialog deudaFormDialog = new DeudaFormDialog(this, deudaDao, null);
                deudaFormDialog.setVisible(true);
                cargarTabla("DEUDA");
                break;
            case "HABITACION":
                // Implementar lógica para insertar una nueva habitación
                HabitacionFormDialog habitacionFormDialog = new HabitacionFormDialog(this, habitacionDao, null);
                habitacionFormDialog.setVisible(true);
                cargarTabla("HABITACION");
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
            case "RESERVA":
                // Implementar lógica para insertar una nueva reserva
                ReservaFormDialog reservaFormDialog = new ReservaFormDialog(this, reservaDao, null);
                reservaFormDialog.setVisible(true);
                cargarTabla("RESERVA");
                break;
            case "RESERVA_HAS_HABITACION":
                // Implementar lógica para insertar una nueva reserva-habitación
                ReservaHasHabitacionFormDialog reservaHasHabitacionFormDialog = new ReservaHasHabitacionFormDialog(this, reservaHasHabitacionDao, null);
                reservaHasHabitacionFormDialog.setVisible(true);
                cargarTabla("RESERVA_HAS_HABITACION");
                break;
            case "SERVICIO_ADICIONAL":
                // Implementar lógica para insertar un nuevo servicio adicional
                ServicioAdicionalFormDialog servicioAdicionalFormDialog = new ServicioAdicionalFormDialog(this, servicioAdicionalDao, null);
                servicioAdicionalFormDialog.setVisible(true);
                cargarTabla("SERVICIO_ADICIONAL");
                break;
            case "SUCURSAL":
                // Implementar lógica para insertar una nueva sucursal
                SucursalFormDialog sucursalFormDialog = new SucursalFormDialog(this, sucursalDao, null);
                sucursalFormDialog.setVisible(true);
                cargarTabla("SUCURSAL");
                break;
            case "TIPO":
                // Implementar lógica para insertar un nuevo tipo
                TipoFormDialog tipoFormDialog = new TipoFormDialog(this, tipoDao, null);
                tipoFormDialog.setVisible(true);
                cargarTabla("TIPO");
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
            case "CHECKIN":
                // Implementar lógica para actualizar un check-in
                int indexCheckin = listTabla.getSelectedIndex();
                if (indexCheckin != -1) {
                    String checkinItem = listModel.get(indexCheckin);
                    int idCheckin = getIdFromItem(checkinItem);
                    Checkin checkin = checkinDao.get(idCheckin);
                    CheckinFormDialog checkinFormDialog = new CheckinFormDialog(this, checkinDao, checkin);
                    checkinFormDialog.setVisible(true);
                    cargarTabla("CHECKIN");
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un check-in para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "COMENTARIO":
                // Implementar lógica para actualizar un comentario
                int indexComentario = listTabla.getSelectedIndex();
                if (indexComentario != -1) {
                    String comentarioItem = listModel.get(indexComentario);
                    int idComentario = getIdFromItem(comentarioItem);
                    Comentario comentario = comentarioDao.get(idComentario);
                    ComentarioFormDialog comentarioFormDialog = new ComentarioFormDialog(this, comentarioDao, comentario);
                    comentarioFormDialog.setVisible(true);
                    cargarTabla("COMENTARIO");
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un comentario para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "DEUDA":
                int indexDeuda = listTabla.getSelectedIndex();
                if (indexDeuda != -1) {
                    String deudaItem = listModel.get(indexDeuda);
                    int idDeuda = getIdFromItem(deudaItem);
                    Deuda deuda = deudaDao.get(idDeuda);
                    DeudaFormDialog deudaFormDialog = new DeudaFormDialog(this, deudaDao, deuda);
                    deudaFormDialog.setVisible(true);
                    cargarTabla("DEUDA");
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione una deuda para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "HABITACION":
                // Implementar lógica para actualizar una habitación
                int indexHabitacion = listTabla.getSelectedIndex();
                if (indexHabitacion != -1) {
                    String habitacionItem = listModel.get(indexHabitacion);
                    int idHabitacion = getIdFromItem(habitacionItem);
                    Habitacion habitacion = habitacionDao.get(idHabitacion);
                    HabitacionFormDialog habitacionFormDialog = new HabitacionFormDialog(this, habitacionDao, habitacion);
                    habitacionFormDialog.setVisible(true);
                    cargarTabla("HABITACION");
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione una habitación para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
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
            case "RESERVA":
                // Implementar lógica para actualizar una reserva
                int indexReserva = listTabla.getSelectedIndex();
                if (indexReserva != -1) {
                    String reservaItem = listModel.get(indexReserva);
                    int idReserva = getIdFromItem(reservaItem);
                    Reserva reserva = reservaDao.get(idReserva);
                    ReservaFormDialog reservaFormDialog = new ReservaFormDialog(this, reservaDao, reserva);
                    reservaFormDialog.setVisible(true);
                    cargarTabla("RESERVA");
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione una reserva para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "RESERVA_HAS_HABITACION":
                // Implementar lógica para actualizar una reserva-habitación
                int indexReservaHabitacion = listTabla.getSelectedIndex();
                if (indexReservaHabitacion != -1) {
                    String reservaHabitacionItem = listModel.get(indexReservaHabitacion);
                    int idReservaHabitacion = getIdFromItem(reservaHabitacionItem);
                    ReservaHasHabitacion reservaHasHabitacion = reservaHasHabitacionDao.get(idReservaHabitacion);
                    ReservaHasHabitacionFormDialog reservaHasHabitacionFormDialog = new ReservaHasHabitacionFormDialog(this, reservaHasHabitacionDao, reservaHasHabitacion);
                    reservaHasHabitacionFormDialog.setVisible(true);
                    cargarTabla("RESERVA_HAS_HABITACION");
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione una reserva-habitación para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "SERVICIO_ADICIONAL":
                // Implementar lógica para actualizar un servicio adicional
                int indexServicioAdicional = listTabla.getSelectedIndex();
                if (indexServicioAdicional != -1) {
                    String servicioAdicionalItem = listModel.get(indexServicioAdicional);
                    int idServicioAdicional = getIdFromItem(servicioAdicionalItem);
                    ServicioAdicional servicioAdicional = servicioAdicionalDao.get(idServicioAdicional);
                    ServicioAdicionalFormDialog servicioAdicionalFormDialog = new ServicioAdicionalFormDialog(this, servicioAdicionalDao, servicioAdicional);
                    servicioAdicionalFormDialog.setVisible(true);
                    cargarTabla("SERVICIO_ADICIONAL");
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un servicio adicional para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "SUCURSAL":
                // Implementar lógica para actualizar una sucursal
                int indexSucursal = listTabla.getSelectedIndex();
                if (indexSucursal != -1) {
                    String sucursalItem = listModel.get(indexSucursal);
                    int idSucursal = getIdFromItem(sucursalItem);
                    Sucursal sucursal = sucursalDao.get(idSucursal);
                    SucursalFormDialog sucursalFormDialog = new SucursalFormDialog(this, sucursalDao, sucursal);
                    sucursalFormDialog.setVisible(true);
                    cargarTabla("SUCURSAL");
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione una sucursal para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "TIPO":
                // Implementar lógica para actualizar un tipo
                int indexTipo = listTabla.getSelectedIndex();
                if (indexTipo != -1) {
                    String tipoItem = listModel.get(indexTipo);
                    int idTipo = getIdFromItem(tipoItem);
                    Tipo tipo = tipoDao.get(idTipo);
                    TipoFormDialog tipoFormDialog = new TipoFormDialog(this, tipoDao, tipo);
                    tipoFormDialog.setVisible(true);
                    cargarTabla("TIPO");
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un tipo para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
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
            case "CHECKIN":
                int indexCheckin = listTabla.getSelectedIndex();
                if (indexCheckin != -1) {
                    int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el check-in?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        String checkinItem = listModel.get(indexCheckin);
                        int idCheckin = getIdFromItem(checkinItem);
                        checkinDao.delete(idCheckin);
                        cargarTabla("CHECKIN");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un check-in para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "COMENTARIO":
                int indexComentario = listTabla.getSelectedIndex();
                if (indexComentario != -1) {
                    int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el comentario?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        String comentarioItem = listModel.get(indexComentario);
                        int idComentario = getIdFromItem(comentarioItem);
                        comentarioDao.delete(idComentario);
                        cargarTabla("COMENTARIO");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un comentario para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "DEUDA":
                int indexDeuda = listTabla.getSelectedIndex();
                if (indexDeuda != -1) {
                    int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar la deuda?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        String deudaItem = listModel.get(indexDeuda);
                        int idDeuda = getIdFromItem(deudaItem);
                        deudaDao.delete(idDeuda);
                        cargarTabla("DEUDA");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione una deuda para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "HABITACION":
                int indexHabitacion = listTabla.getSelectedIndex();
                if (indexHabitacion != -1) {
                    int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar la habitación?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        String habitacionItem = listModel.get(indexHabitacion);
                        int idHabitacion = getIdFromItem(habitacionItem);
                        habitacionDao.delete(idHabitacion);
                        cargarTabla("HABITACION");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione una habitación para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
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
            case "RESERVA":
                int indexReserva = listTabla.getSelectedIndex();
                if (indexReserva != -1) {
                    int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar la reserva?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        String reservaItem = listModel.get(indexReserva);
                        int idReserva = getIdFromItem(reservaItem);
                        reservaDao.delete(idReserva);
                        cargarTabla("RESERVA");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione una reserva para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "RESERVA_HAS_HABITACION":
                int indexReservaHabitacion = listTabla.getSelectedIndex();
                if (indexReservaHabitacion != -1) {
                    int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar la reserva-habitación?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        String reservaHabitacionItem = listModel.get(indexReservaHabitacion);
                        int idReservaHabitacion = getIdFromItem(reservaHabitacionItem);
                        reservaHasHabitacionDao.delete(idReservaHabitacion);
                        cargarTabla("RESERVA_HAS_HABITACION");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione una reserva-habitación para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "SERVICIO_ADICIONAL":
                int indexServicioAdicional = listTabla.getSelectedIndex();
                if (indexServicioAdicional != -1) {
                    int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el servicio adicional?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        String servicioAdicionalItem = listModel.get(indexServicioAdicional);
                        int idServicioAdicional = getIdFromItem(servicioAdicionalItem);
                        servicioAdicionalDao.delete(idServicioAdicional);
                        cargarTabla("SERVICIO_ADICIONAL");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un servicio adicional para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "SUCURSAL":
                int indexSucursal = listTabla.getSelectedIndex();
                if (indexSucursal != -1) {
                    int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar la sucursal?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        String sucursalItem = listModel.get(indexSucursal);
                        int idSucursal = getIdFromItem(sucursalItem);
                        sucursalDao.delete(idSucursal);
                        cargarTabla("SUCURSAL");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione una sucursal para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "TIPO":
                int indexTipo = listTabla.getSelectedIndex();
                if (indexTipo != -1) {
                    int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el tipo?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        String tipoItem = listModel.get(indexTipo);
                        int idTipo = getIdFromItem(tipoItem);
                        tipoDao.delete(idTipo);
                        cargarTabla("TIPO");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un tipo para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
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


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
    private ComentarioDao comentarioDao;
    private DeudaDao deudaDao;
    private HabitacionDao habitacionDao;
    private HuespedDao huespedDao;
    private MetodoDePagoDao metodoDePagoDao;
    private ReservaDao reservaDao;
    private ServicioAdicionalHasHabitacionDao servicioAdicionalHasHabitacionDao;
    private SucursalDao sucursalDao;
    private TipoDao tipoDao;


    public FramePrincipal() {

        //Inicializar DAO

        cadenaHoteleraDao = new CadenaHoteleraDaoImple();
        comentarioDao = new ComentarioDaoImple();
        deudaDao = new DeudaDaoImple();
        habitacionDao = new HabitacionDaoImple();
        huespedDao = new HuespedDaoImple();
        metodoDePagoDao = new MetodoDePagoDaoImple();
        reservaDao = new ReservaDaoImple();
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
        tablaComboBox.addItem("HUESPED");
        tablaComboBox.addItem("SERVICIO_ADICIONAL");
        tablaComboBox.addItem("COMENTARIOS");
        tablaComboBox.addItem("DEUDA");
        tablaComboBox.addItem("HABITACION");
        tablaComboBox.addItem("METODO_DE_PAGO");
        tablaComboBox.addItem("RESERVA");
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
            case "HUESPED":
                ArrayList<Huesped> huespedes = huespedDao.getList();
                for (Huesped huesped : huespedes) {
                    listModel.addElement(huesped.getIdHuesped() + ". " + huesped.getNombre() + " " +
                            huesped.getCi() + " - " + huesped.getTelefono());
                }
                break;
            case "SERVICIO_ADICIONAL":
                ArrayList<ServicioAdicionalHasHabitacion> serviciosAdicionales = servicioAdicionalHasHabitacionDao.getList();
                for (ServicioAdicionalHasHabitacion servicioAdicional : serviciosAdicionales) {
                    listModel.addElement(servicioAdicional.getIdServicioAdicional() + ". " + servicioAdicional.getIdHabitacion()
                            + " - " + servicioAdicional.getIdServicioAdicional_has_Habitacion());
                }
                break;
            case "COMENTARIOS":
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
//                 Implementar lógica para insertar una nueva cadena hotelera
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


package org.proyecto.gui;

import org.proyecto.dao.ReservaDao;
import org.proyecto.dto.Reserva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class ReservaFormDialog extends JFrame {
    private JTextField estadoTextField;
    private JTextField fechaEntradaTextField;
    private JTextField fechaSalidaTextField;
    private JTextField peticionTextField;
    private JTextField cantidadPersonasTextField;
    private JTextField idHuespedTextField;
    private JTextField idMetodoDePagoTextField;
    private JButton guardarButton;

    private ReservaDao reservaDao;
    private Reserva reserva;

    public ReservaFormDialog(JFrame parent, ReservaDao reservaDao, Reserva reserva) {
        super();
        this.reservaDao = reservaDao;
        this.reserva = reserva;

        // Configurar la ventana emergente
        setTitle("Consulta de Reserva");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear componentes de la interfaz
        estadoTextField = new JTextField(10);
        fechaEntradaTextField = new JTextField(10);
        fechaSalidaTextField = new JTextField(10);
        peticionTextField = new JTextField(10);
        cantidadPersonasTextField = new JTextField(10);
        idHuespedTextField = new JTextField(10);
        idMetodoDePagoTextField = new JTextField(10);
        guardarButton = new JButton("Guardar");

        // Configurar el ActionListener para el botón "Guardar"
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    guardarReserva();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Configurar el diseño de la interfaz
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Agregar componentes al panel
        panel.add(new JLabel("Estado:"), gbc);
        gbc.gridx++;
        panel.add(estadoTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Fecha Entrada:"), gbc);
        gbc.gridx++;
        panel.add(fechaEntradaTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Fecha Salida:"), gbc);
        gbc.gridx++;
        panel.add(fechaSalidaTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Peticion:"), gbc);
        gbc.gridx++;
        panel.add(peticionTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Cantidad Personas:"), gbc);
        gbc.gridx++;
        panel.add(cantidadPersonasTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("ID Huesped:"), gbc);
        gbc.gridx++;
        panel.add(idHuespedTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("ID Método de Pago:"), gbc);
        gbc.gridx++;
        panel.add(idMetodoDePagoTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(guardarButton, gbc);

        // Agregar panel al centro de la ventana
        add(panel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    private void guardarReserva() throws Exception {
        // Obtener los datos ingresados por el usuario
        String estado = estadoTextField.getText();
        Date fechaEntrada = Date.valueOf(fechaEntradaTextField.getText());
        Date fechaSalida = Date.valueOf(fechaSalidaTextField.getText());
        String peticion = peticionTextField.getText();
        int cantidadPersonas = Integer.parseInt(cantidadPersonasTextField.getText());
        int idHuesped = Integer.parseInt(idHuespedTextField.getText());
        int idMetodoDePago = Integer.parseInt(idMetodoDePagoTextField.getText());

        // Crear un objeto Reserva con los datos ingresados
        Reserva nuevaReserva = new Reserva(estado, fechaEntrada, fechaSalida, peticion, cantidadPersonas, idHuesped, idMetodoDePago);

        // Guardar la reserva en la base de datos
        if (reserva != null) {
            // Actualizar la reserva existente
            nuevaReserva.setIdReserva(reserva.getIdReserva());
            reservaDao.update(nuevaReserva);
            JOptionPane.showMessageDialog(this, "Reserva actualizada exitosamente");
        } else {
            // Insertar una nueva reserva
            reservaDao.insert(nuevaReserva);
            JOptionPane.showMessageDialog(this, "Reserva actualizada exitosamente");
        }

        // Cerrar la ventana de consulta de reserva
        dispose();
    }
}

package org.proyecto.gui;

import org.proyecto.dao.ReservaHasHabitacionDao;
import org.proyecto.dto.ReservaHasHabitacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservaHasHabitacionFormDialog extends JFrame {
    private JTextField idReservaTextField;
    private JTextField idHabitacionTextField;
    private JTextField precioTextField;
    private JButton guardarButton;

    private ReservaHasHabitacionDao reservaHasHabitacionDao;
    private ReservaHasHabitacion reservaHasHabitacion;

    public ReservaHasHabitacionFormDialog(JFrame parent, ReservaHasHabitacionDao reservaHasHabitacionDao, ReservaHasHabitacion reservaHasHabitacion) {
        super();
        this.reservaHasHabitacionDao = reservaHasHabitacionDao;
        this.reservaHasHabitacion = reservaHasHabitacion;

        // Configurar la ventana emergente
        setTitle("Consulta de Reserva-Habitación");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear componentes de la interfaz
        idReservaTextField = new JTextField(10);
        idHabitacionTextField = new JTextField(10);
        precioTextField = new JTextField(10);
        guardarButton = new JButton("Guardar");

        // Configurar el ActionListener para el botón "Guardar"
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    guardarReservaHasHabitacion();
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
        panel.add(new JLabel("ID Reserva:"), gbc);
        gbc.gridx++;
        panel.add(idReservaTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("ID Habitación:"), gbc);
        gbc.gridx++;
        panel.add(idHabitacionTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Precio:"), gbc);
        gbc.gridx++;
        panel.add(precioTextField, gbc);

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

    private void guardarReservaHasHabitacion() throws Exception {
        int idReserva = Integer.parseInt(idReservaTextField.getText());
        int idHabitacion = Integer.parseInt(idHabitacionTextField.getText());
        float precio = Float.parseFloat(precioTextField.getText());

        ReservaHasHabitacion NuevaReservaHasHabitacion = new ReservaHasHabitacion(idReserva, idHabitacion, precio);

        if (reservaHasHabitacion != null) {
            // Actualizar la reserva existente
            NuevaReservaHasHabitacion.setIdReserva_has_habitacion(reservaHasHabitacion.getIdReserva());
            reservaHasHabitacionDao.update(NuevaReservaHasHabitacion);
            JOptionPane.showMessageDialog(this, "Reserva actualizada exitosamente");
        } else {
            // Insertar una nueva reserva
            reservaHasHabitacionDao.insert(NuevaReservaHasHabitacion);
            JOptionPane.showMessageDialog(this, "Reserva actualizada exitosamente");
        }

        // Cerrar la ventana de consulta de reserva
        dispose();

    }
}
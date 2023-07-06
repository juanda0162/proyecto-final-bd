package org.proyecto.gui;

import org.proyecto.dao.CheckinDao;
import org.proyecto.dto.Checkin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class CheckinFormDialog extends JFrame {
    private JTextField registroLlegadaTextField;
    private JTextField registroSalidaTextField;
    private JTextField idReservasTextField;
    private JButton guardarButton;

    private CheckinDao checkinDao;
    private Checkin checkin;

    public CheckinFormDialog(JFrame parent, CheckinDao checkinDao, Checkin checkin) {
        super();
        this.checkinDao = checkinDao;
        this.checkin = checkin;

        // Configurar la ventana emergente
        setTitle("Consulta de Checkin");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear componentes de la interfaz
        registroLlegadaTextField = new JTextField(10);
        registroSalidaTextField = new JTextField(10);
        idReservasTextField = new JTextField(10);
        guardarButton = new JButton("Guardar");

        // Configurar el ActionListener para el botón "Guardar"
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    guardarCheckin();
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

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Registro Llegada:"), gbc);
        gbc.gridx++;
        panel.add(registroLlegadaTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Registro Salida:"), gbc);
        gbc.gridx++;
        panel.add(registroSalidaTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("ID Reservas:"), gbc);
        gbc.gridx++;
        panel.add(idReservasTextField, gbc);

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

    private void guardarCheckin() throws Exception {
        Date registroLlegada = Date.valueOf(registroLlegadaTextField.getText());
        Date registroSalida = Date.valueOf(registroSalidaTextField.getText());
        int idReservas = Integer.parseInt(idReservasTextField.getText());

        Checkin nuevoCheckin = new Checkin(registroLlegada, registroSalida, idReservas);

        if (checkin != null) {
            // Actualizar el checkin existente
            nuevoCheckin.setIdChekin(checkin.getIdChekin());
            checkinDao.update(nuevoCheckin);
            JOptionPane.showMessageDialog(this, "checkin actualizado exitosamente");
        } else {
            // Insertar una nueva reserva
            checkinDao.insert(nuevoCheckin);
            JOptionPane.showMessageDialog(this, "checkin actualizado exitosamente");
        }
        // Cerrar la ventana de consulta de reserva
        dispose();
    }
}
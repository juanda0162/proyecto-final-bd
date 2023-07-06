package org.proyecto.gui;


import org.proyecto.dao.HabitacionDao;
import org.proyecto.dto.Habitacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HabitacionFormDialog extends JFrame {
    private JTextField estadoTextField;
    private JTextField idSucursalTextField;
    private JTextField idTipoTextField;
    private JButton guardarButton;

    private HabitacionDao habitacionDao;
    private Habitacion habitacion;

    public HabitacionFormDialog(JFrame parent, HabitacionDao habitacionDao, Habitacion habitacion) {
        super();
        this.habitacionDao = habitacionDao;
        this.habitacion = habitacion;

        // Configurar la ventana emergente
        setTitle("Consulta de Habitación");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear componentes de la interfaz
        estadoTextField = new JTextField(10);
        idSucursalTextField = new JTextField(10);
        idTipoTextField = new JTextField(10);
        guardarButton = new JButton("Guardar");

        // Configurar el ActionListener para el botón "Guardar"
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    guardarHabitacion();
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
        panel.add(new JLabel("ID Sucursal:"), gbc);
        gbc.gridx++;
        panel.add(idSucursalTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("ID Tipo:"), gbc);
        gbc.gridx++;
        panel.add(idTipoTextField, gbc);

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

    private void guardarHabitacion() throws Exception {
        // Obtener los datos ingresados por el usuario
        String estado = estadoTextField.getText();
        int idSucursal = Integer.parseInt(idSucursalTextField.getText());
        int idTipo = Integer.parseInt(idTipoTextField.getText());

        // Crear un objeto Habitacion con los datos ingresados
        Habitacion nuevaHabitacion = new Habitacion(estado, idSucursal, idTipo);

        // Guardar la habitacion en la base de datos
        if (habitacion != null) {
            // Actualizar la habitacion existente
            nuevaHabitacion.setIdHabitacion(habitacion.getIdHabitacion());
            habitacionDao.update(nuevaHabitacion);
            JOptionPane.showMessageDialog(this, "Habitacion actualizada exitosamente");
        } else {
            // Insertar una nueva habitacion
            habitacionDao.insert(nuevaHabitacion);
            JOptionPane.showMessageDialog(this, "Habitacion actualizada exitosamente");
        }

        // Cerrar la ventana de actualización de habitacion
        dispose();
    }
}
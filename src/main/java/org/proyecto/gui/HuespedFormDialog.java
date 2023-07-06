package org.proyecto.gui;

import org.proyecto.dao.HuespedDao;
import org.proyecto.dto.Huesped;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HuespedFormDialog extends JFrame {
    private JTextField ciTextField;
    private JTextField nombreTextField;
    private JTextField telefonoTextField;
    private JButton guardarButton;

    private HuespedDao huespedDao;
    private Huesped huesped;

    public HuespedFormDialog(JFrame parent, HuespedDao huespedDao, Huesped huesped) {
        super();
        this.huespedDao = huespedDao;
        this.huespedDao = huespedDao;

        // Configurar la ventana emergente
        setTitle("Consulta de Huésped");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear componentes de la interfaz
        ciTextField = new JTextField(10);
        nombreTextField = new JTextField(10);
        telefonoTextField = new JTextField(10);
        guardarButton = new JButton("Guardar");

        // Configurar el ActionListener para el botón "Guardar"
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    guardarHuesped();
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
        panel.add(new JLabel("CI del Huésped:"), gbc);
        gbc.gridx++;
        panel.add(ciTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx++;
        panel.add(nombreTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx++;
        panel.add(telefonoTextField, gbc);

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



    private void guardarHuesped() throws Exception {
        // Obtener los datos ingresados por el usuario
        int ci = Integer.parseInt(ciTextField.getText());
        String nombre = nombreTextField.getText();
        int telefono = Integer.parseInt(telefonoTextField.getText());

        // Crear un objeto Huesped con los datos ingresados
        Huesped NuevoHuesped = new Huesped(ci, nombre, telefono);

        // Guardar el huesped en la base de datos
        if (huesped != null) {
            // Actualizar el paciente existente
            NuevoHuesped.setIdHuesped(huesped.getIdHuesped());
            huespedDao.update(NuevoHuesped);
        } else {
            // Insertar un nuevo paciente
            huespedDao.insert(NuevoHuesped);
            JOptionPane.showMessageDialog(this, "Huesped actualizado exitosamente");

        }

        // Cerrar la ventana de consulta de huésped
        dispose();
    }
}

package org.proyecto.gui;

import org.proyecto.dao.ServicioAdicionalDao;
import org.proyecto.dto.ServicioAdicional;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServicioAdicionalFormDialog extends JFrame {
    private JTextField nombreTextField;
    private JTextField precioTextField;
    private JButton guardarButton;

    private ServicioAdicionalDao servicioAdicionalDao;
    private ServicioAdicional servicioAdicional;

    public ServicioAdicionalFormDialog(JFrame parent, ServicioAdicionalDao servicioAdicionalDao, ServicioAdicional servicioAdicional) {
        super();
        this.servicioAdicionalDao = servicioAdicionalDao;
        this.servicioAdicional = servicioAdicional;

        // Configurar la ventana emergente
        setTitle("Consulta de Servicio Adicional");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear componentes de la interfaz
        nombreTextField = new JTextField(10);
        precioTextField = new JTextField(10);
        guardarButton = new JButton("Guardar");

        // Configurar el ActionListener para el botón "Guardar"
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    guardarServicioAdicional();
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
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx++;
        panel.add(nombreTextField, gbc);

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

    private void guardarServicioAdicional() throws Exception {
        String nombre = nombreTextField.getText();
        float precio = Float.parseFloat(precioTextField.getText());

        ServicioAdicional nuevoServicioAdicional = new ServicioAdicional(nombre, precio);

        if (servicioAdicional != null) {
            // Actualizar el servicio adicional existente
            nuevoServicioAdicional.setIdServicioAdicional(servicioAdicional.getIdServicioAdicional());
            servicioAdicionalDao.update(nuevoServicioAdicional);
            JOptionPane.showMessageDialog(this, "Servicio adicional actualizado exitosamente");
        } else {
            // Insertar un nuevo servicio adicional
            servicioAdicionalDao.insert(nuevoServicioAdicional);
            JOptionPane.showMessageDialog(this, "Servicio adicional insertado exitosamente");
        }

        // Cerrar la ventana de consulta de servicio adicional
        dispose();
    }
}

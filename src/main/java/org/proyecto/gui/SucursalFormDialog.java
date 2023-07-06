package org.proyecto.gui;

import org.proyecto.dao.SucursalDao;
import org.proyecto.dto.Sucursal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SucursalFormDialog extends JFrame {
    private JTextField nombreTextField;
    private JTextField direccionTextField;
    private JTextField clasificacionTextField;
    private JTextField telefonoTextField;
    private JTextField idCadenaHoteleraTextField;
    private JButton guardarButton;

    private SucursalDao sucursalDao;
    private Sucursal sucursal;

    public SucursalFormDialog(JFrame parent, SucursalDao sucursalDao, Sucursal sucursal) {
        super();
        this.sucursalDao = sucursalDao;
        this.sucursal = sucursal;

        // Configurar la ventana emergente
        setTitle("Consulta de Sucursal");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear componentes de la interfaz
        nombreTextField = new JTextField(10);
        direccionTextField = new JTextField(10);
        clasificacionTextField = new JTextField(10);
        telefonoTextField = new JTextField(10);
        idCadenaHoteleraTextField = new JTextField(10);
        guardarButton = new JButton("Guardar");

        // Configurar el ActionListener para el botón "Guardar"
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    guardarSucursal();
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
        panel.add(new JLabel("Dirección:"), gbc);
        gbc.gridx++;
        panel.add(direccionTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Clasificación:"), gbc);
        gbc.gridx++;
        panel.add(clasificacionTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx++;
        panel.add(telefonoTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("ID Cadena Hotelera:"), gbc);
        gbc.gridx++;
        panel.add(idCadenaHoteleraTextField, gbc);

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

    private void guardarSucursal() throws Exception {
        // Obtener los datos ingresados por el usuario
        String nombre = nombreTextField.getText();
        String direccion = direccionTextField.getText();
        String clasificacion = clasificacionTextField.getText();
        int telefono = Integer.parseInt(telefonoTextField.getText());
        int idCadenaHotelera = Integer.parseInt(idCadenaHoteleraTextField.getText());

        // Crear un objeto Sucursal con los datos ingresados
        Sucursal nuevaSucursal = new Sucursal(nombre, direccion, clasificacion, telefono, idCadenaHotelera);

        // Guardar la sucursal en la base de datos
        if (sucursal != null) {
            // Actualizar la sucursal existente
            nuevaSucursal.setIdSucursal(sucursal.getIdSucursal());
            sucursalDao.update(nuevaSucursal);
        } else {
            // Insertar una nueva sucursal
            sucursalDao.insert(nuevaSucursal);
            JOptionPane.showMessageDialog(this, "Sucursal actualizada exitosamente");
        }

        // Cerrar la ventana de consulta de sucursal
        dispose();
    }
}

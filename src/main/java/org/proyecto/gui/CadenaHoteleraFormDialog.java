package org.proyecto.gui;


import org.proyecto.dao.CadenaHoteleraDao;
import org.proyecto.dto.CadenaHotelera;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadenaHoteleraFormDialog extends JFrame {
    private CadenaHotelera cadenaHotelera;
    private CadenaHoteleraDao cadenaHoteleraDao;
    private JTextField idTextField;
    private JTextField nombreTextField;
    private JButton guardarButton;


    public CadenaHoteleraFormDialog(JFrame parent, CadenaHoteleraDao cadenaHoteleraDao, CadenaHotelera cadenaHotelera) {
        super();
        this.cadenaHoteleraDao = cadenaHoteleraDao;
        this.cadenaHotelera = cadenaHotelera;

        // Configurar la ventana emergente
        setTitle("Actualización de Cadena Hotelera");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear componentes de la interfaz
        idTextField = new JTextField(10);
        nombreTextField = new JTextField(10);
        guardarButton = new JButton("Guardar");

        // Configurar el ActionListener para el botón "Guardar"
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    guardarCadenaHotelera();
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
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx++;
        panel.add(nombreTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(guardarButton, gbc);

        // Agregar panel al centro de la ventana
        add(panel, BorderLayout.CENTER);

        pack();
        setVisible(true);

        if (cadenaHotelera != null) {
            nombreTextField.setText(cadenaHotelera.getNombre());
        }

    }


    private void guardarCadenaHotelera() throws Exception {
        // Obtener los datos ingresados por el usuario
        String nombre = nombreTextField.getText();

        // Crear un objeto CadenaHotelera con los datos ingresados
        CadenaHotelera NuevaCadenaHotelera = new CadenaHotelera(nombre);

        // Actualizar la cadena hotelera en la base de datos
        if (cadenaHotelera != null) {
            // Actualizar el paciente existente
            NuevaCadenaHotelera.setIdCadenaHotelera(cadenaHotelera.getIdCadenaHotelera());
            cadenaHoteleraDao.update(NuevaCadenaHotelera);
        } else {
            // Insertar un nuevo paciente
            cadenaHoteleraDao.insert(NuevaCadenaHotelera);
            JOptionPane.showMessageDialog(this, "Cadena hotelera actualizada exitosamente");

        }


        // Cerrar la ventana de actualización de cadena hotelera
        dispose();
    }
}

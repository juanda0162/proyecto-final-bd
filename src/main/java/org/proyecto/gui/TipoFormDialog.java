package org.proyecto.gui;

import org.proyecto.dao.TipoDao;
import org.proyecto.dto.Tipo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TipoFormDialog extends JFrame {
    private JTextField nombreTextField;
    private JTextField comodidadesTextField;
    private JTextField precioTextField;
    private JButton guardarButton;

    private TipoDao tipoDao;
    private Tipo tipo;

    public TipoFormDialog(JFrame parent, TipoDao tipoDao, Tipo tipo) {
        super();
        this.tipoDao = tipoDao;
        this.tipo = tipo;

        // Configurar la ventana emergente
        setTitle("Consulta de Tipo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear componentes de la interfaz
        nombreTextField = new JTextField(10);
        comodidadesTextField = new JTextField(10);
        precioTextField = new JTextField(10);
        guardarButton = new JButton("Guardar");

        // Configurar el ActionListener para el botón "Guardar"
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    guardarTipo();
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
        panel.add(new JLabel("Comodidades:"), gbc);
        gbc.gridx++;
        panel.add(comodidadesTextField, gbc);

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

    private void guardarTipo() throws Exception {
        // Obtener los datos ingresados por el usuario
        String nombre = nombreTextField.getText();
        String comodidades = comodidadesTextField.getText();
        float precio = Float.parseFloat(precioTextField.getText());

        // Crear un objeto Tipo con los datos ingresados
        Tipo nuevoTipo = new Tipo(nombre, comodidades, precio);

        // Guardar el tipo en la base de datos
        if (tipo != null) {
            // Actualizar el tipo existente
            nuevoTipo.setIdTipo(tipo.getIdTipo());
            tipoDao.update(nuevoTipo);
        } else {
            // Insertar un nuevo tipo
            tipoDao.insert(nuevoTipo);
            JOptionPane.showMessageDialog(this, "Tipo actualizado exitosamente");
        }

        // Cerrar la ventana de consulta de tipo
        dispose();
    }
}

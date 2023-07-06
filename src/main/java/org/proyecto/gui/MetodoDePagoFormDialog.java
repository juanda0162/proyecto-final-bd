package org.proyecto.gui;

import org.proyecto.dao.MetodoDePagoDao;
import org.proyecto.dto.MetodoDePago;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MetodoDePagoFormDialog extends JFrame {
    private JTextField tipoTextField;
    private JTextField descripcionTextField;
    private JButton guardarButton;

    private MetodoDePagoDao metodoDePagoDao;
    private MetodoDePago metodoDePago;

    public MetodoDePagoFormDialog(JFrame parent, MetodoDePagoDao metodoDePagoDao, MetodoDePago metodoDePago) {
        super();
        this.metodoDePagoDao = metodoDePagoDao;
        this.metodoDePago = metodoDePago;

        // Configurar la ventana emergente
        setTitle("Consulta de Método de Pago");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear componentes de la interfaz
        tipoTextField = new JTextField(10);
        descripcionTextField = new JTextField(10);
        guardarButton = new JButton("Guardar");

        // Configurar el ActionListener para el botón "Guardar"
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    guardarMetodoDePago();
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
        panel.add(new JLabel("Tipo de Método de Pago:"), gbc);
        gbc.gridx++;
        panel.add(tipoTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Descripción:"), gbc);
        gbc.gridx++;
        panel.add(descripcionTextField, gbc);

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

    private void guardarMetodoDePago() throws Exception {
        // Obtener los datos ingresados por el usuario
        String tipo = tipoTextField.getText();
        String descripcion = descripcionTextField.getText();

        // Crear un objeto MetodoDePago con los datos ingresados
        MetodoDePago nuevoMetodoDePago = new MetodoDePago(tipo, descripcion);

        // Guardar el método de pago en la base de datos
        if (metodoDePago != null) {
            // Actualizar el método de pago existente
            nuevoMetodoDePago.setIdMetodoDePago(metodoDePago.getIdMetodoDePago());
            metodoDePagoDao.update(nuevoMetodoDePago);
        } else {
            // Insertar un nuevo método de pago
            metodoDePagoDao.insert(nuevoMetodoDePago);
            JOptionPane.showMessageDialog(this, "Método de pago actualizado exitosamente");
        }

        // Cerrar la ventana de consulta de método de pago
        dispose();
    }
}

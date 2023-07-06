package org.proyecto.gui;

import org.proyecto.dao.DeudaDao;
import org.proyecto.dto.Deuda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeudaFormDialog extends JFrame {
    private JTextField montoTextField;
    private JTextField idReservaTextField;
    private JButton guardarButton;

    private DeudaDao deudaDao;
    private Deuda deuda;

    public DeudaFormDialog(JFrame parent, DeudaDao deudaDao, Deuda deuda) {
        super();
        this.deudaDao = deudaDao;
        this.deuda = deuda;

        // Configurar la ventana emergente
        setTitle("Consulta de Deuda");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear componentes de la interfaz
        montoTextField = new JTextField(10);
        idReservaTextField = new JTextField(10);
        guardarButton = new JButton("Guardar");

        // Configurar el ActionListener para el botón "Guardar"
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    guardarDeuda();
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
        panel.add(new JLabel("Monto Total:"), gbc);
        gbc.gridx++;
        panel.add(montoTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("ID Reserva:"), gbc);
        gbc.gridx++;
        panel.add(idReservaTextField, gbc);

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

    private void guardarDeuda() throws Exception {
        // Obtener los datos ingresados por el usuario
        float montoTotal = Float.parseFloat(montoTextField.getText());
        int idReserva = Integer.parseInt(idReservaTextField.getText());

        // Crear un objeto Deuda con los datos ingresados
        Deuda nuevaDeuda = new Deuda(montoTotal, idReserva);

        // Actualizar la deuda en la base de datos
        if (deuda != null) {
            // Actualizar la deuda existente
            nuevaDeuda.setIdDeuda(deuda.getIdDeuda());
            deudaDao.update(nuevaDeuda);
            JOptionPane.showMessageDialog(this, "Deuda actualizada exitosamente");
        } else {
            // Insertar una nueva deuda
            deudaDao.insert(nuevaDeuda);
            JOptionPane.showMessageDialog(this, "Deuda actualizada exitosamente");
        }

        // Cerrar la ventana de actualización de deuda
        dispose();
    }
}


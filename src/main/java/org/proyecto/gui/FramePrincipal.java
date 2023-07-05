package org.proyecto.gui;

import org.proyecto.dao.HuespedDao;
import org.proyecto.dao.Implementaciones.HuespedDaoImple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FramePrincipal extends JFrame{
    private HuespedDao huespedDao;
    public FramePrincipal() {
        huespedDao = new HuespedDaoImple();
        // Configuración de la ventana JFrame
        setTitle("Hotel Management App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configurar el estilo de apariencia de Nimbus
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Crear los componentes de la interfaz
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel("Bienvenido a la aplicación de gestión hotelera");
        label.setFont(new Font("Arial", Font.BOLD, 24));

        JButton reservaButton = new JButton("Realizar Reserva");
        reservaButton.setPreferredSize(new Dimension(200, 50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(label, gbc);

        gbc.gridy = 1;
        panel.add(reservaButton, gbc);
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        // Configurar acciones de los botones
        reservaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HuespedFormDialog huespedFormDialog = new HuespedFormDialog(huespedDao);
                huespedFormDialog.setVisible(true);

            }
        });
        setVisible(true);
    }

}

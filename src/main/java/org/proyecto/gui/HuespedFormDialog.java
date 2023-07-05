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

    public HuespedFormDialog(HuespedDao huespedDao) {
        this.huespedDao = huespedDao;

        // Configurar la ventana emergente
        setTitle("Consulta de Huésped");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        // Crear componentes de la interfaz
        ciTextField = new JTextField(10);
        nombreTextField = new JTextField(10);
        telefonoTextField = new JTextField(10);
        guardarButton = new JButton("Guardar");

        // Agregar componentes a la ventana
        add(new JLabel("CI del Huésped:"));
        add(ciTextField);
        add(new JLabel("Nombre:"));
        add(nombreTextField);
        add(new JLabel("Teléfono:"));
        add(telefonoTextField);
        add(guardarButton);

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

        setVisible(true);
    }

    private void guardarHuesped() throws Exception {
        // Obtener los datos ingresados por el usuario
        int ci = Integer.parseInt(ciTextField.getText());
        String nombre = nombreTextField.getText();
        int telefono = Integer.parseInt(telefonoTextField.getText());

        // Crear un objeto Huesped con los datos ingresados
        Huesped huesped = new Huesped(ci, nombre, telefono);

        // Guardar el huesped en la base de datos
        huespedDao.insert(huesped);

        // Mostrar un mensaje de confirmación
        JOptionPane.showMessageDialog(this, "Huésped guardado exitosamente");

        // Cerrar la ventana de consulta de huésped
        dispose();
    }
}

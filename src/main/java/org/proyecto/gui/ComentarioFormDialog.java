package org.proyecto.gui;

import org.proyecto.dao.ComentarioDao;
import org.proyecto.dto.Comentario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class ComentarioFormDialog extends JFrame {
    private JTextField descripcionTextField;
    private JTextField calificacionTextField;
    private JTextField fechaTextField;
    private JTextField idChekinTextField;
    private JButton guardarButton;

    private ComentarioDao comentarioDao;
    private Comentario comentario;

    public ComentarioFormDialog(JFrame parent, ComentarioDao comentarioDao, Comentario comentario) {
        super();
        this.comentarioDao = comentarioDao;
        this.comentario = comentario;

        // Configurar la ventana emergente
        setTitle("Consulta de Comentario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear componentes de la interfaz
        descripcionTextField = new JTextField(10);
        calificacionTextField = new JTextField(10);
        fechaTextField = new JTextField(10);
        idChekinTextField = new JTextField(10);
        guardarButton = new JButton("Guardar");

        // Configurar el ActionListener para el botón "Guardar"
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    guardarComentario();
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
        panel.add(new JLabel("Descripción:"), gbc);
        gbc.gridx++;
        panel.add(descripcionTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Calificación:"), gbc);
        gbc.gridx++;
        panel.add(calificacionTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Fecha:"), gbc);
        gbc.gridx++;
        panel.add(fechaTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("ID Checkin:"), gbc);
        gbc.gridx++;
        panel.add(idChekinTextField, gbc);

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

    private void guardarComentario() throws Exception {
        String descripcion = descripcionTextField.getText();
        String calificacion = calificacionTextField.getText();
        Date fecha = new Date(fechaTextField.getText());
        int idCheckin = Integer.parseInt(idChekinTextField.getText());

        Comentario nuevoComentario = new Comentario(descripcion, calificacion, fecha, idCheckin);

        if (comentario != null) {
            // Actualizar el comentario existente
            nuevoComentario.setIdComentario(comentario.getIdComentario());
            comentarioDao.update(nuevoComentario);
            JOptionPane.showMessageDialog(this, "Comentario actualizado exitosamente");
        } else {
            // Insertar un nuevo comentario
            comentarioDao.update(nuevoComentario);
            JOptionPane.showMessageDialog(this, "Comentario actualizado exitosamente");
        }

        // Cerrar la ventana de consulta de servicio adicional
        dispose();
    }
}

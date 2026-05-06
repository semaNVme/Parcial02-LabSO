package UI;

import Factory.Factory;
import Factory.IMessage;
import Factory.Message;
import Decorador.*;
import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private JTextField txtId, txtRecipient, txtContent;
    private JComboBox<String> comboType;

    public ControlPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Panel de Control (Emisor)"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ID
        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("ID:"), gbc);
        txtId = new JTextField("MSG-" + System.currentTimeMillis() % 1000);
        gbc.gridx = 1; add(txtId, gbc);

        // Tipo
        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Canal:"), gbc);
        comboType = new JComboBox<>(new String[]{"email", "default", "push"});
        gbc.gridx = 1; add(comboType, gbc);

        // Destinatario
        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Destinatario:"), gbc);
        txtRecipient = new JTextField("usuario@ejemplo.com");
        gbc.gridx = 1; add(txtRecipient, gbc);

        // Contenido
        gbc.gridx = 0; gbc.gridy = 3; add(new JLabel("Mensaje:"), gbc);
        txtContent = new JTextField("Hola desde la UI!");
        gbc.gridx = 1; add(txtContent, gbc);

        // Botón Enviar
        JButton btnSend = new JButton("Enviar Mensaje");
        btnSend.setBackground(new Color(0, 150, 0));
        btnSend.setForeground(Color.WHITE);
        btnSend.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        add(btnSend, gbc);

        btnSend.addActionListener(e -> sendMessage());
    }

    private void sendMessage() {
        String id = txtId.getText();
        String typeKey = (String) comboType.getSelectedItem();
        String recipient = txtRecipient.getText();
        String content = txtContent.getText();
        String typeLabel = typeKey.equals("default") ? "SMS" : typeKey.toUpperCase();

        // 1. Obtener base de la Factory
        IMessage base = Factory.getInstance().getMessage(typeKey);

        // 2. Construir cadena de decoradores
        IMessage procesador = new ValidateMessage(
                                new LogMessage(
                                    new EncryptMessage(
                                        new NotificationDecorador(base)
                                    )
                                )
                              );

        // 3. Procesar
        Message msg = new Message(id, typeLabel, recipient, content);
        procesador.procesar(msg);

        // Limpiar para el siguiente
        txtId.setText("MSG-" + (int)(Math.random() * 1000));
        txtContent.setText("");
    }
}

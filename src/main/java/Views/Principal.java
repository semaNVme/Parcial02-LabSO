package Views;

import Factory.Factory;
import Factory.IMessage;
import Factory.Message;
import Decorador.*;
import Observer.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class Principal extends javax.swing.JFrame {

    // Componentes personalizados
    private JTextField txtId, txtRecipient, txtContent;
    private JComboBox<String> comboType;
    private JTextArea auditArea;
    private Map<String, JLabel> statLabels = new HashMap<>();
    private int totalCount = 0;
    private JLabel totalLabel;

    public Principal() {
        initComponents();
        setupControlPanel();
        setupAuditPanel();
        setupStatisticsPanel();
        
        // Registrar observadores
        NotificationManager.getInstance().addObserver(new UIObserver());
    }

    private void setupControlPanel() {
        panelControl.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; panelControl.add(new JLabel("ID:"), gbc);
        txtId = new JTextField("MSG-" + (int)(Math.random() * 1000));
        gbc.gridx = 1; panelControl.add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panelControl.add(new JLabel("Canal:"), gbc);
        comboType = new JComboBox<>(new String[]{"email", "default", "push"});
        gbc.gridx = 1; panelControl.add(comboType, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panelControl.add(new JLabel("Dest:"), gbc);
        txtRecipient = new JTextField("usuario@ejemplo.com");
        gbc.gridx = 1; panelControl.add(txtRecipient, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panelControl.add(new JLabel("Msg:"), gbc);
        txtContent = new JTextField("Hola!");
        gbc.gridx = 1; panelControl.add(txtContent, gbc);

        JButton btnSend = new JButton("Enviar");
        btnSend.setBackground(new Color(0, 153, 51));
        btnSend.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panelControl.add(btnSend, gbc);

        btnSend.addActionListener(e -> sendMessage());
    }

    private void setupAuditPanel() {
        panelAudit.setLayout(new BorderLayout());
        auditArea = new JTextArea();
        auditArea.setEditable(false);
        auditArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        panelAudit.add(new JScrollPane(auditArea), BorderLayout.CENTER);
    }

    private void setupStatisticsPanel() {
        panelStatistics.setLayout(new GridLayout(5, 1, 5, 5));
        totalLabel = new JLabel("Total: 0", SwingConstants.CENTER);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 12));
        panelStatistics.add(totalLabel);

        panelStatistics.add(createStatLabel("EMAIL"));
        panelStatistics.add(createStatLabel("SMS"));
        panelStatistics.add(createStatLabel("PUSH"));
    }

    private JPanel createStatLabel(String type) {
        JPanel p = new JPanel(new BorderLayout());
        JLabel label = new JLabel("0", SwingConstants.RIGHT);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(new JLabel(type + ": "), BorderLayout.WEST);
        p.add(label, BorderLayout.CENTER);
        statLabels.put(type, label);
        return p;
    }

    private void sendMessage() {
        String typeKey = (String) comboType.getSelectedItem();
        String typeLabel = typeKey.equals("default") ? "SMS" : typeKey.toUpperCase();

        IMessage base = Factory.getInstance().getMessage(typeKey);
        IMessage procesador = new ValidateMessage(new LogMessage(new EncryptMessage(new NotificationDecorador(base))));

        Message msg = new Message(txtId.getText(), typeLabel, txtRecipient.getText(), txtContent.getText());
        procesador.procesar(msg);
        
        txtId.setText("MSG-" + (int)(Math.random() * 1000));
        txtContent.setText("");
    }

    // Clase interna para manejar las actualizaciones del Observer
    private class UIObserver implements NotificationObserver {
        @Override
        public void update(NotificationEvent event) {
            SwingUtilities.invokeLater(() -> {
                // Actualizar Auditoría
                auditArea.append(String.format("[%s] %s -> %s\n", event.getType(), event.getRecipient(), event.getStatus()));
                
                // Actualizar Estadísticas
                totalCount++;
                totalLabel.setText("Total: " + totalCount);
                if (statLabels.containsKey(event.getType())) {
                    JLabel lbl = statLabels.get(event.getType());
                    lbl.setText(String.valueOf(Integer.parseInt(lbl.getText()) + 1));
                }
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelControl = new javax.swing.JPanel();
        panelAudit = new javax.swing.JPanel();
        panelStatistics = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard de Mensajería");

        panelControl.setBorder(javax.swing.BorderFactory.createTitledBorder("Envío"));

        javax.swing.GroupLayout panelControlLayout = new javax.swing.GroupLayout(panelControl);
        panelControl.setLayout(panelControlLayout);
        panelControlLayout.setHorizontalGroup(
            panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        panelControlLayout.setVerticalGroup(
            panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelAudit.setBorder(javax.swing.BorderFactory.createTitledBorder("Auditoría"));

        javax.swing.GroupLayout panelAuditLayout = new javax.swing.GroupLayout(panelAudit);
        panelAudit.setLayout(panelAuditLayout);
        panelAuditLayout.setHorizontalGroup(
            panelAuditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        panelAuditLayout.setVerticalGroup(
            panelAuditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelStatistics.setBorder(javax.swing.BorderFactory.createTitledBorder("Estadísticas"));

        javax.swing.GroupLayout panelStatisticsLayout = new javax.swing.GroupLayout(panelStatistics);
        panelStatistics.setLayout(panelStatisticsLayout);
        panelStatisticsLayout.setHorizontalGroup(
            panelStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        panelStatisticsLayout.setVerticalGroup(
            panelStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAudit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelStatistics, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelControl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelAudit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelStatistics, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

        java.awt.EventQueue.invokeLater(() -> {
            new Principal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelAudit;
    private javax.swing.JPanel panelControl;
    private javax.swing.JPanel panelStatistics;
    // End of variables declaration//GEN-END:variables
}

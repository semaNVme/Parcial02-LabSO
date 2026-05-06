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

public class Principal extends javax.swing.JFrame implements NotificationObserver {

    private Map<String, JLabel> statLabels = new HashMap<>();

    public Principal() {
        initComponents();
        initCustomLogic();
    }
    
    private void initCustomLogic() {
        // Inicializar mapeo de etiquetas para las estadísticas
        statLabels.put("EMAIL", totalEmail);
        statLabels.put("SMS", totalSMS);
        statLabels.put("PUSH", totalPush);
        
        // Inicializar etiquetas con 0
        totalSMS.setText("0");
        totalEmail.setText("0");
        totalPush.setText("0");
        
        // Registrar esta vista como observador
        NotificationManager.getInstance().addObserver(this);
        
        // Generar un ID inicial
        txtId.setText("MSG-" + (int)(Math.random() * 1000));
        setLocationRelativeTo(null);
    }
    
    private void sendMessage() {
        String typeKey = (String) comboType.getSelectedItem();
        // Mapeo de la llave del combo al label esperado por el observer
        String typeLabel = typeKey.equals("default") ? "SMS" : typeKey.toUpperCase();

        IMessage base = Factory.getInstance().getMessage(typeKey);
        // Cadena de decoradores: Validar -> Log -> Encriptar -> Notificar
        IMessage procesador = new ValidateMessage(
                                new LogMessage(
                                    new EncryptMessage(
                                        new NotificationDecorador(base)
                                    )
                                )
                              );

        Message msg = new Message(txtId.getText(), typeLabel, txtRecipient.getText(), txtContent.getText());
        procesador.procesar(msg);
        
        // Resetear campos
        txtId.setText("MSG-" + (int)(Math.random() * 1000));
        txtContent.setText("");
    }

    @Override
    public void update(NotificationEvent event) {
        SwingUtilities.invokeLater(() -> {
            String entry = String.format("[%s] [%s] %s -> %s\n", 
                    event.getFormattedTimestamp(), event.getType(), event.getRecipient(), event.getStatus());
            auditArea.append(entry);
            auditArea.setCaretPosition(auditArea.getDocument().getLength());
            
            if (statLabels.containsKey(event.getType())) {
                JLabel lbl = statLabels.get(event.getType());
                try {
                    String currentText = lbl.getText();
                    int current = Integer.parseInt(currentText.equals(".") || currentText.isEmpty() ? "0" : currentText);
                    lbl.setText(String.valueOf(current + 1));
                } catch (NumberFormatException e) {
                    lbl.setText("1");
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelControl = new javax.swing.JPanel();
        lblid = new javax.swing.JLabel();
        comboType = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtRecipient = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtContent = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        panelAudit = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        auditArea = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        panelStatistics = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        totalSMS = new javax.swing.JLabel();
        totalEmail = new javax.swing.JLabel();
        totalPush = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelControl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblid.setText("ID:");

        comboType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "email", "push", "default" }));

        jLabel1.setText("Destinatario:");

        jLabel2.setText("Canal:");

        jLabel3.setText("Msg:");

        btnSend.setText("Enviar");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelControlLayout = new javax.swing.GroupLayout(panelControl);
        panelControl.setLayout(panelControlLayout);
        panelControlLayout.setHorizontalGroup(
            panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelControlLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblid, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRecipient, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addComponent(txtContent))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelControlLayout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(btnSend)
                .addGap(50, 50, 50))
        );
        panelControlLayout.setVerticalGroup(
            panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelControlLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblid)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRecipient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtContent, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSend)
                .addGap(42, 42, 42))
        );

        panelAudit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        auditArea.setColumns(20);
        auditArea.setRows(5);
        jScrollPane1.setViewportView(auditArea);

        jLabel4.setText("Auditoria:");

        javax.swing.GroupLayout panelAuditLayout = new javax.swing.GroupLayout(panelAudit);
        panelAudit.setLayout(panelAuditLayout);
        panelAuditLayout.setHorizontalGroup(
            panelAuditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelAuditLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        panelAuditLayout.setVerticalGroup(
            panelAuditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuditLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        panelStatistics.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setText("SMS:");

        jLabel6.setText("Email:");

        jLabel7.setText("Push:");

        totalSMS.setText(".");

        totalEmail.setText(".");

        totalPush.setText(".");

        jLabel8.setText("Statistics:");

        javax.swing.GroupLayout panelStatisticsLayout = new javax.swing.GroupLayout(panelStatistics);
        panelStatistics.setLayout(panelStatisticsLayout);
        panelStatisticsLayout.setHorizontalGroup(
            panelStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStatisticsLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelStatisticsLayout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(60, 60, 60)
                            .addComponent(totalSMS, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelStatisticsLayout.createSequentialGroup()
                            .addGroup(panelStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(50, 50, 50)
                            .addGroup(panelStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(totalEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(totalPush, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        panelStatisticsLayout.setVerticalGroup(
            panelStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStatisticsLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel8)
                .addGap(30, 30, 30)
                .addGroup(panelStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(totalSMS))
                .addGap(38, 38, 38)
                .addGroup(panelStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(totalEmail))
                .addGap(39, 39, 39)
                .addGroup(panelStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(totalPush))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        sendMessage();
    }//GEN-LAST:event_btnSendActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea auditArea;
    private javax.swing.JButton btnSend;
    private javax.swing.JComboBox<String> comboType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblid;
    private javax.swing.JPanel panelAudit;
    private javax.swing.JPanel panelControl;
    private javax.swing.JPanel panelStatistics;
    private javax.swing.JLabel totalEmail;
    private javax.swing.JLabel totalPush;
    private javax.swing.JLabel totalSMS;
    private javax.swing.JTextField txtContent;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtRecipient;
    // End of variables declaration//GEN-END:variables
}

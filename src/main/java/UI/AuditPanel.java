package UI;

import Observer.NotificationEvent;
import Observer.NotificationObserver;
import javax.swing.*;
import java.awt.*;

public class AuditPanel extends JPanel implements NotificationObserver {
    private JTextArea auditArea;

    public AuditPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Módulo de Auditoría (Observador)"));

        auditArea = new JTextArea();
        auditArea.setEditable(false);
        auditArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scroll = new JScrollPane(auditArea);
        add(scroll, BorderLayout.CENTER);
    }

    @Override
    public void update(NotificationEvent event) {
        String logEntry = String.format("[%s] %s -> %s [%s]\n", 
                event.getFormattedTimestamp(), 
                event.getType(), 
                event.getRecipient(), 
                event.getStatus());
        
        // Asegurar actualización en el EDT de Swing
        SwingUtilities.invokeLater(() -> {
            auditArea.append(logEntry);
            auditArea.setCaretPosition(auditArea.getDocument().getLength());
        });
    }
}

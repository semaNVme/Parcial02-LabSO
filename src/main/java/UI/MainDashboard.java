package UI;

import Observer.NotificationManager;
import javax.swing.*;
import java.awt.*;

public class MainDashboard extends JFrame {

    public MainDashboard() {
        setTitle("Sistema de Mensajería - Demostración de Patrones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Paneles
        ControlPanel control = new ControlPanel();
        AuditPanel audit = new AuditPanel();
        StatisticsPanel stats = new StatisticsPanel();

        // Registrar los paneles como Observadores
        NotificationManager.getInstance().addObserver(audit);
        NotificationManager.getInstance().addObserver(stats);

        // Organizar Layout
        add(control, BorderLayout.WEST);
        
        JPanel rightPanel = new JPanel(new BorderLayout(5, 5));
        rightPanel.add(audit, BorderLayout.CENTER);
        rightPanel.add(stats, BorderLayout.EAST);
        
        stats.setPreferredSize(new Dimension(200, 0));
        add(rightPanel, BorderLayout.CENTER);

        // Estilo General
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainDashboard().setVisible(true);
        });
    }
}

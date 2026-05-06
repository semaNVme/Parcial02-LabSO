package UI;

import Observer.NotificationEvent;
import Observer.NotificationObserver;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class StatisticsPanel extends JPanel implements NotificationObserver {
    private Map<String, JLabel> labels = new HashMap<>();
    private int totalCount = 0;
    private JLabel totalLabel;

    public StatisticsPanel() {
        setLayout(new GridLayout(5, 1, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Módulo de Estadísticas (Observador)"));

        totalLabel = new JLabel("Total Mensajes: 0", SwingConstants.CENTER);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(totalLabel);

        add(createStatLabel("EMAIL"));
        add(createStatLabel("SMS"));
        add(createStatLabel("PUSH"));
    }

    private JPanel createStatLabel(String type) {
        JPanel p = new JPanel(new BorderLayout());
        JLabel label = new JLabel("0", SwingConstants.RIGHT);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.BLUE);
        
        p.add(new JLabel(type + ": "), BorderLayout.WEST);
        p.add(label, BorderLayout.CENTER);
        
        labels.put(type, label);
        return p;
    }

    @Override
    public void update(NotificationEvent event) {
        totalCount++;
        String type = event.getType();
        
        SwingUtilities.invokeLater(() -> {
            totalLabel.setText("Total Mensajes: " + totalCount);
            if (labels.containsKey(type)) {
                JLabel lbl = labels.get(type);
                int count = Integer.parseInt(lbl.getText()) + 1;
                lbl.setText(String.valueOf(count));
            }
        });
    }
}

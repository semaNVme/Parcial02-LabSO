package Observer;
 
import java.util.HashMap;
import java.util.Map;
 
/**
 * Módulo de Estadísticas
 *
 * Reacciona a cada cambio de estado actualizando contadores por tipo y estado.
 * Desacoplado del NotificationManager: solo implementa NotificationObserver.
 */
public class StatisticsModule implements NotificationObserver {
 
    private final Map<String, Integer> countByType   = new HashMap<>();
    private final Map<NotificationStatus, Integer> countByStatus = new HashMap<>();
    private int total = 0;
 
    @Override
    public void update(NotificationEvent event) {
        total++;
 
        // Conteo por tipo (EMAIL, SMS, PUSH)
        countByType.merge(event.getType(), 1, Integer::sum);
 
        // Conteo por estado (ENVIADO, FALLIDO, PENDIENTE)
        countByStatus.merge(event.getStatus(), 1, Integer::sum);
 
        System.out.printf("[StatisticsModule] Total=%d | Tipo %s=%d | Estado %s=%d%n",
                total,
                event.getType(), countByType.get(event.getType()),
                event.getStatus(), countByStatus.get(event.getStatus()));
    }
 
    public int getTotal()                              { return total; }
    public Map<String, Integer> getCountByType()       { return new HashMap<>(countByType); }
    public Map<NotificationStatus, Integer> getCountByStatus() {
        return new HashMap<>(countByStatus);
    }
 
    /** Resumen formateado para mostrar en la UI. */
    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Total eventos: ").append(total).append("\n\n");
        sb.append("── Por tipo ──────────────\n");
        countByType.forEach((k, v) -> sb.append("  ").append(k).append(": ").append(v).append("\n"));
        sb.append("\n── Por estado ────────────\n");
        countByStatus.forEach((k, v) -> sb.append("  ").append(k).append(": ").append(v).append("\n"));
        return sb.toString();
    }
}

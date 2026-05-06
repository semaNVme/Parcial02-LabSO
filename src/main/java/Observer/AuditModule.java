package Observer;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Módulo de Auditoría
 *
 * Reacciona a cada cambio de estado registrando una entrada de auditoría.
 * Principio de Responsabilidad Única (SRP): solo se ocupa de auditar.
 * Principio Abierto/Cerrado (OCP): se puede agregar o quitar del sistema
 * sin tocar el NotificationManager.
 */
public class AuditModule implements NotificationObserver {
 
    private final List<String> auditLog = new ArrayList<>();
 
    @Override
    public void update(NotificationEvent event) {
        String entry = String.format("AUDITORÍA | %s | Tipo: %-5s | Dest: %-20s | Estado: %s",
                event.getFormattedTimestamp(),
                event.getType(),
                event.getRecipient(),
                event.getStatus());
        auditLog.add(entry);
        System.out.println("[AuditModule] " + entry);
    }
 
    /** Devuelve copia inmutable del log de auditoría. */
    public List<String> getAuditLog() {
        return Collections.unmodifiableList(auditLog);
    }
}
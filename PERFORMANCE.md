# PERFORMANCE

## Prueba de Carga

### Objetivo
Verificar que el componente soporta **30 transacciones por segundo (TPS)** de forma estable.

### Enfoque
- Ejecutar la API en perfil `prod` y en contenedores Docker/Kubernetes.
- Aplicar una carga progresiva hasta alcanzar **30 TPS sostenidos**.
- Mantener la carga durante varios minutos para validar estabilidad.
- Medir latencia, tasa de errores y consumo de recursos (CPU y memoria).

### Herramientas
- **k6** o **Apache JMeter** para generar la carga.
- **Docker / Kubernetes** para simular el entorno productivo.
- **Logs y métricas de Quarkus** para verificar comportamiento y errores.

### Criterio de éxito
- ≥ 30 TPS sostenidos.
- Latencia aceptable (p95 < 500 ms).
- Sin errores significativos ni reinicios del servicio.

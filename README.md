# api-bancario prueba Sofka

Hola muy buenas noches, Señores de Sofka.

En este repositorio se encuentra el proyecto completo desarrollado en Java, utilizando el framework Quarkus y Maven.

Para esta prueba se emplea SQL Server, tal como fue indicado. El proyecto está basado en una transacción bancaria básica, diseñada para demostrar de forma clara la implementación de una Arquitectura Hexagonal (Ports & Adapters) orientada a microservicios.

Espero que el proyecto sea de su agrado y resulte claro desde el punto de vista técnico y arquitectónico.

Notas para iniciar el proyecto

Utilizar OpenJDK 21

Verificar que el puerto 8080 esté disponible

Tener correctamente configuradas las variables de entorno (JAVA_HOME, MAVEN_HOME)


SE INICIA DESDE CONSOLA CON mvnw quarkus:dev


Las credenciales de la base de datos son:

Usuario: bancotransaccion
Contraseña: PasswordSeguro123!

En el directorio del proyecto dejo el .bak que es SISTEMABANCARIO.bak



Respondiendo a las preguntas

¿Si el banco externo tarda 15 segundos en responder, cómo evitas que el servicio degrade su rendimiento para otros bancos?

El servicio evita la degradación del rendimiento aplicando timeouts configurables y aislamiento de llamadas externas. Cada invocación al banco externo se ejecuta con un tiempo máximo de espera definido (timeoutMs), por lo que si el banco tarda más de lo permitido, la operación se corta y el hilo se libera rápidamente. Esto impide que peticiones lentas bloqueen recursos del sistema y afecten el procesamiento de transacciones hacia otros bancos, manteniendo la capacidad de atención del servicio bajo alta latencia externa.


¿Cómo garantizamos la integridad de la transacción si el servicio se cae después de que el banco confirmó el pago, pero antes de guardar en la base de datos local?

La integridad se garantiza persistiendo primero la transacción en la base de datos con un estado inicial PENDIENTE antes de realizar la llamada al banco externo. De esta manera, si el servicio falla luego de que el banco confirma el pago, la transacción ya está registrada y no se pierde. Posteriormente, estas transacciones pendientes pueden ser conciliadas o reprocesadas para actualizar su estado final, asegurando consistencia eventual sin perder información ni generar duplicidades.

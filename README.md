# XatTextual

Este proyecto implementa un chat textual basado en un servidor centralizado y múltiples clientes. La comunicación se realiza mediante sockets, con el servidor retransmitiendo los mensajes de un cliente a los demás conectados.

Características principales

Funcionalidades implementadas

    Encapsulación de sockets:
        Clases MySocket y MyServerSocket:
            Son equivalentes a las clases estándar de Java Socket y ServerSocket.
            Incluyen manejo simplificado de excepciones y soporte para flujos de texto (BufferedReader y PrintWriter).
            Métodos añadidos para la lectura y escritura de tipos de datos básicos.

    Cliente multithreading:
        Implementado como dos hilos concurrentes:
            Hilo de entrada: Lee líneas desde el teclado y las envía al servidor.
            Hilo de salida: Lee líneas enviadas por otros clientes a través del servidor y las imprime en la consola.
        Manejo automático del cierre del cliente al finalizar la entrada.

    Servidor multithreading:
        Gestiona múltiples clientes de manera concurrente.
        Mantiene un diccionario sincronizado de pares (nick, socket) para identificar clientes conectados.
        Métodos de sincronización para garantizar la consistencia del diccionario:
          - Uso de ConcurrentHashMap

Ejecución

Ejemplo de uso

Servidor:

    Ejecuta el servidor en un terminal: "java ChatServer <Port>"
    
    - java ChatServer 12345

Cliente:

    En otro terminal, conecta un cliente: "java ChatClient <host> <port>"
    
    - java ChatClient localhost 12345

host: Dirección del servidor.

port: Puerto utilizado por el servidor.

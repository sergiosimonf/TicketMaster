# TicketMaster
Este repositorio contiene el código fuente de un proyecto Java que simula la venta de entradas para un concierto, similar al funcionamiento de TicketMaster. El proyecto implementa un sistema de concurrencia donde varios fans intentan comprar entradas mientras un promotor repone el stock en la web de venta de entradas.

## Características Principales:
### Roles y Funcionalidades:
- Fans: Representados como hilos, intentan comprar entradas. Cada fan puede comprar una entrada a la vez y puede comprar entradas para sus amigos mientras haya disponibilidad. Después de cada compra, el fan se "duerme" entre 1 y 3 segundos.
- Promotor: También representado como hilo, repone entradas en la web. Después de cada reposición, el promotor se "duerme" entre 3 y 8 segundos.
- Web de Compra de Entradas: Gestiona la compra y reposición de entradas. Tiene secciones críticas para garantizar la consistencia en las operaciones.
### Secciones Críticas y Espera/Notificación:
- Se han identificado las secciones críticas en el código para evitar problemas de concurrencia.
- Se utilizan técnicas de espera/notificación para coordinar la interacción entre fans y el promotor.
### Cierre de la Venta:
- Implementación del cierre de la venta cuando se alcanza el número total de entradas deseado por el promotor.
- Los fans son notificados cuando se cierra la venta y detienen su intento de compra.

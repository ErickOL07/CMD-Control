# Proyecto Redes - Aplicación de Detección, Realización y Mitigación de Ataques Informáticos

## Descripción

Esta aplicación de Android está diseñada para detectar, realizar y mitigar ataques informáticos. Ofrece funcionalidades para realizar ataques DoS (Denial of Service), simular la implementación de listas de control de acceso (ACL) para bloquear tráfico no deseado, y detectar intentos de ataques para mejorar la seguridad de la red.

## Funcionalidades

1. **Detectar Ataques**:
   - Monitorea el tráfico de red en busca de patrones sospechosos que podrían indicar un ataque.
   - Muestra alertas cuando se detectan posibles ataques y proporciona detalles sobre el tipo de ataque y la IP de origen.

2. **Realizar Ataques DoS**:
   - Permite al usuario realizar ataques DoS contra una URL específica.
   - Muestra una pantalla con detalles del ataque, incluyendo la URL de destino, el número de iteraciones, el tiempo de respuesta, etc.

3. **Implementar ACL**:
   - Permite al usuario simular la implementación de una ACL para bloquear una IP específica.
   - Muestra una pantalla con detalles de la ACL, incluyendo la IP de destino, la hora de bloqueo y detalles del gateway.

## Requisitos

- Android Studio
- Emulador de Android o dispositivo Android físico
- Conexión a Internet para probar los ataques DoS y ACL

## Instalación

1. Ingrese al siguiente enlace:
https://drive.google.com/file/d/1YvweMEtXvZe7RmmgEaJBsXmIft5g6Uu_/view
2. Dele a "Descargar".
3. En caso solicite, active la instalación de aplicaciones mediante fuentes externas.
4. Ejecute el archivo APK.
5. Culmine la instalación-

IMPORTANTE: Solo disponible para dispositivos Android.

## Uso

### Detectar Ataques

1. En la pantalla principal, navega a la sección "Detectar Ataques".
2. La aplicación comenzará a monitorear el tráfico de red automáticamente.
3. Si se detecta un ataque, se mostrará una alerta con detalles del ataque.

### Realizar Ataques DoS

1. En la pantalla principal, navega a la sección "Realizar Ataques".
2. Ingresa la URL de destino y el número de iteraciones.
3. Presiona el botón "Realizar DoS".
4. La aplicación mostrará una nueva pantalla con los detalles del ataque en curso.

### Implementar ACL

1. En la pantalla principal, navega a la sección "Mitigar Ataques".
2. Ingresa la IP de destino que deseas bloquear.
3. Presiona el botón "Implementar ACL".
4. La aplicación mostrará una nueva pantalla con los detalles de la ACL implementada.

## Archivos Principales

- `MainActivity.kt`: La actividad principal de la aplicación.
- `DetectarAtaqueActivity.kt`: La actividad para detectar ataques.
- `RealizarAtaqueActivity.kt`: La actividad para realizar ataques DoS.
- `MitigarAtaqueActivity.kt`: La actividad para implementar ACL.
- `Dos.kt`: Clase que maneja la lógica de los ataques DoS.
- `ACLImplementation.kt`: Clase que maneja la lógica de la implementación de ACL.
- `DeteccionAtaques.kt`: Clase que maneja la lógica de detección de ataques.
- `activity_detectar_ataque.xml`: Layout de la pantalla para detectar ataques.
- `activity_realizar_ataque.xml`: Layout de la pantalla para realizar ataques DoS.
- `activity_mitigar_ataque.xml`: Layout de la pantalla para implementar ACL.
- `activity_acl_details.xml`: Layout de la pantalla que muestra los detalles de la ACL.

## Licencia

Este proyecto está bajo la Licencia del grupo 2 de la sección 626 del curso Redes de Computadoras de la Universidad de Lima.

## Autores

- Héctor Arrasco
- Claudia Sipion
- Erick Obradovich
- Sergio Chaparro

## Contacto

Para cualquier duda o sugerencia, puedes contactarnos en [erickobradovichluna@gmail.com]

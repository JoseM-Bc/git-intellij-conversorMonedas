Conversor de monedas en Java que utiliza ExchangeRate-API para obtener tasas en tiempo real, con historial de conversiones y soporte para múltiples divisas.

📌 Características Principales
✅ Conversión en tiempo real usando ExchangeRate-API
✅ Historial completo de todas las conversiones realizadas
✅ Soporte para 7 monedas: USD, ARS, BRL, COP, EUR, MXN, CLP
✅ Marca de tiempo en cada operación (usando java.time)
✅ Interfaz intuitiva con menús interactivos
✅ Validación de entradas para evitar errores

🛠️ Tecnologías Utilizadas
Tecnología	Uso
Java 11	Lenguaje principal
Gson	Parseo de JSON desde la API
java.net.http	Solicitudes HTTP a ExchangeRate-API
java.time	Registro de fechas y horas

📦 Instalación y Ejecución
Requisitos
JDK 11 o superior

Conexión a Internet (para obtener tasas actualizadas)

Clave API de ExchangeRate-API (gratis)

Pasos para ejecutar
Clona el repositorio:

Abre el proyecto en tu IDE favorito (Eclipse, IntelliJ, VS Code).

Asegúrate de tener la biblioteca Gson incluida.

Ejecuta la clase principal: Conversion.java.

🎨 Interfaz y Funcionalidades
Menú Principal

<img width="1811" height="428" alt="image" src="https://github.com/user-attachments/assets/773fd70b-cb6e-4ba4-a1e3-a6bc5c95172a" />

Realizar conversión: Permite seleccionar monedas y cantidad.

Ver historial: Muestra todas las conversiones anteriores con fecha y hora.

Actualizar tasas: Obtiene las tasas más recientes desde la API.

Salir: Cierra la aplicación.

2. Menú de Conversión

<img width="1813" height="500" alt="image" src="https://github.com/user-attachments/assets/9c614945-eeae-45c6-ae1b-cb11620003c2" />


11 opciones de conversión (USD a ARS, EUR a USD, etc.).

Validación de entrada: Solo acepta números positivos.

3. Ejemplo de Conversión

<img width="1820" height="297" alt="image" src="https://github.com/user-attachments/assets/a824f37a-a2a6-4af3-ba4a-0bf145f367fc" />


Muestra el resultado con formato claro.

Registra automáticamente en el historial.

4. Historial de Conversiones

<img width="1817" height="611" alt="image" src="https://github.com/user-attachments/assets/9ce54df7-3530-49ad-a784-323526c52e62" />


Ordenado cronológicamente.

Muestra:

Fecha y hora exacta.

Monedas convertidas.

Cantidad y resultado.

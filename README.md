# 🧠 Evaluación Final Transversal – Desarrollo Orientado a Objetos I

## 👤 Autor del proyecto
- **Nombre completo:** [Ariel Gustavo Loncon Lefimil]
- **Sección:** [009A]
- **Carrera:** Desarrollo de aplicaciones
- **Sede:** [Online]


---
## 📘 Descripción general del sistema

Este sistema es una aplicación de escritorio desarrollada en **Java** utilizando la librería gráfica **Swing** para resolver las problemáticas de desorganización, automatización de reservas y trazabilidad de la agencia de viajes **"Llanquihue Tour"**. 
La aplicación permite gestionar de manera integral el catálogo de servicios turísticos, guías, proveedores y clientes, aplicando restricciones del negocio en tiempo real (como el control estricto de cupos por tour) y garantizando la persistencia de datos mediante archivos planos de texto (.txt).

---
## 🧱 Estructura general del proyecto
```plaintext
📁 Resources/
    ├── Cliente.txt                 # Archivo de texto que almacena los datos de los clientes
    ├── Guias.txt                   # Archivo de texto que almacena los datos de Guías
    ├── Proveedores.txt             # Archivo de texto que almacena los datos de los proveedores
    ├── Servicios.txt               # Archivo de texto que almacena los datos de los servicios turisticos
📁 src/
├── Data/
│   ├── GestorArchivotxt/
│   │   └── GestorArchivos.java     # Validador de archivos
│   │   ├── GestorCliente.java      # Carga, lectura y escritura de clientes en Cliente.txt
│   │   ├── GestorEliminar.java     # Lógica centralizada para borrar registros físicos de los .txt
│   │   └── GestorServicios.java    # Lectura polimórfica del catálogo de servicios turísticos
│   │   └── GestorGuias.java        # Lectura polimórfica del catálogo de Guias turisticos
│   │   └── GestorProveedor.java    # Lectura polimórfica del catálogo de Proveedores locales
│   └── GestorListas/
│       └── GestorEntidades.java    # Administración de la lista polimórfica global en memoria
├── Model/
│   ├── Tours/
│   │   ├── PaseoLacustre.java      # Subclase especializada de ServicioTuristico
│   │   ├── RutaCultural.java       # Subclase especializada de ServicioTuristico
│   │   ├── ServivioTuristico.java  # Superclase abstracta del catálogo de tours
│   │   └── TourGastronomico.java   # Subclase especializada de ServicioTuristico
│   ├── Cliente.java                # Subclase especializada de Persona
│   ├── Direccion.java              # Clase que gestiona direcciones
│   ├── Guia.java                   # Subclase especializada de Persona
│   ├── Persona.java                # Superclase abstracta
│   ├── Proveedor.java              # Subclase especializada de Persona
│   └── Registrable.java            # Interfaz que define el contrato de comportamiento común
├── Ui/
│   └── SwingUi/
│   │   ├── Agregar.java            # Interfaz de gráfica para vizualizar y agregar ver y borrar registros de guia, proveedores y servicios turisticos
│   │   ├── ControladorCupos.java   # Controlador logístico de UI (cupos máximos y siguiente OC)
│   │   ├── PanelPrincipal.java     # Interfaz gráfica que hace de menu para visualizar y acceder a las opciondes de las otras interfaces
│   │   ├── VentanaListaClientes.java   # Interfaz gráfica para visualizar y filtrar clientes en JTable
│   │   ├── VentanaReservaTour.java # Interfaz gráfica para visualizar y agregar clientes segun servicio turistico elejido
│   │   ├── VentanaToursDisponible.java # Interfaz gráfica para visualizar y flitrar Servicios turisticos en Jtable 
│   │   └── VerDatosJtable.java     # Jtable para visualizar y flitrar Guias, proveedores y Servicios turisticos en Jtable de "Agregar" 
│   └── Main.java                   # Interfaz que define el contrato de comportamiento común
└── Util/
    ├── ValidadorEmail.java         # Validación de formatos mediante expresiones regulares
    ├── ValidadorRut.java           # Validación estricta y lógica del dígito verificador del RUT
    └── ValidadorTelefono.java      # Validación de longitud y caracteres para números telefónicos
    └── GestorLineasTxt.java        # Validación de lineas, genera un salto para evitar corrupcionde archivo, (en caso de intervencion manual del archivo txt)
````
---

## ⚙️ Instrucciones para clonar y ejecutar el proyecto
1. Clona el repositorio desde GitHub: https://github.com/Ariel-Loncon/ExamenLlanquihueTour.git
2. Abre el proyecto en IntelliJ IDEA, Net Beans o similares.
3. Verifica que los archivos `.txt` estén correctamente ubicados, en caso de no estar se generaran unos en blanco.
4. Ejecuta el archivo `Main.java` desde el paquete `Ui` para desplegar la interfaz gráfica de usuario (GUI).
5. Utiliza el menú para navegar entre el ingreso de nuevos clientes, la visualización de reportes polimórficos de auditoría en la terminal, o el filtrado dinámico de contratos por tour específico en las tablas correspondientes.

#### Repositorio GitHub: \[https://github.com/Ariel-Loncon/ExamenLlanquihueTour.git]
#### Fecha de entrega: \[19/07/2026]
---
#### © Duoc UC | Escuela de Informática y Telecomunicaciones | Evaluación Final Transversal 
---

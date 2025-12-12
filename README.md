# Taller-4Poo -Ucn-Sebastian-Barrera
#Nombre sebastian Barrera- RUT 20.015.335-9-ITI
Sistema de Gestión Académica - Academicore
Este proyecto es la solución al desafío final del curso, donde se nos pidió desarrollar un sistema de gestión académica completo para una universidad. El objetivo principal fue crear una aplicación que permitiera administrar usuarios (estudiantes, coordinadores y administradores), gestionar cursos, notas y, lo más importante, manejar todo el proceso de inscripción y seguimiento de certificaciones (Minors).

El sistema permite a los estudiantes ver su malla curricular, inscribir certificaciones y revisar su progreso visualmente. Por otro lado, los coordinadores pueden gestionar la oferta académica y obtener reportes detallados sobre el rendimiento y las preferencias de los alumnos.

Tecnologías y Arquitectura
Para mantener el código ordenado y cumplir con las buenas prácticas de desarrollo, estructuré todo el proyecto utilizando el patrón de arquitectura Modelo-Vista-Controlador (MVC). Esto me permitió separar la lógica del negocio, los datos y la interfaz gráfica en paquetes distintos (modelo, vista, controlador), facilitando mucho el orden y la escalabilidad del código.

Interfaz Gráfica: Java Swing (Ventanas, Botones y Paneles).
nota: no use fx porque no me manejo mucho en frontend y necesitaba sacar el taller rapido, pero me hubiese gustado probar mas cosas.

Persistencia: Archivos de texto (.txt) para simular una base de datos.

Patrones de Diseño Aplicados
Para resolver problemas complejos de diseño y cumplir con los requisitos avanzados del taller, implementé cuatro patrones de diseño específicos. Aquí explico por qué usé cada uno:

1. Singleton
Lo utilicé en la clase Controlador. ¿Por qué? Necesitaba asegurarme de que existiera una única instancia del sistema gestionando toda la lógica y las listas de datos (usuarios, cursos, certificaciones) durante la ejecución. Si hubiera múltiples controladores, los datos estarían desincronizados. Con Singleton, todo el programa accede al mismo "cerebro" central.

2. Factory Method
Lo implementé en las clases FactoryUsuario y FactoryCertificaciones. ¿Por qué? Al cargar los datos desde los archivos de texto, el sistema no sabe de antemano si la línea que está leyendo corresponde a un Estudiante o un Cordinador, o qué tipo de certificación es. El Factory se encarga de recibir los datos "crudos" y decidir qué objeto específico crear, encapsulando esa lógica de creación y dejando el código de carga mucho más limpio.

3. Strategy
Lo apliqué para la generación de Reportes en el menú del Coordinador (AnalisiInscripcion y AnalisisAsignaturas). ¿Por qué? El sistema requieria generar reportes muy distintos (uno de popularidad de inscripciones y otro de asignaturas con alto riesgo de reprobación) que podían cambiar o aumentar en el futuro. En lugar de llenar el código de if-else, usé Strategy para que el comportamiento del reporte sea intercambiable dinámicamente según lo que elija el coordinador.

4. Visitor
Lo utilicé en el Dashboard de Progreso (DashBoard, Visitor). ¿Por qué? Necesitaba ejecutar acciones o mostrar mensajes personalizados dependiendo del tipo de certificación que tuviera el alumno (IA, Ciberseguridad o Software), pero sin modificar las clases de las certificaciones constantemente. El patrón Visitor me permitió separar el algoritmo (los mensajes y consejos del mentor virtual) de la estructura de objetos, respetando el principio de abierto/cerrado.

5 .Modelo Vista Controlador(mcv)
Este patron lo habia aprendiendo años atras y es simplemtente para poder organizar las clases dependiendo de su funcion en el programa, lo que facilita la implementacion de nuevas funcionalidades en el programa.

notas:
1.Hay un main con opciones por consola porque al principio entendi que solo la malla era por gui, despues entendi que no y preferi no borrar, quizas solo porque no queria perder mi avance  o que no fuera en vano.
2.hay una clase que obtiene un menor/mayor en base a lo trabajado en catedra el ultimo mes vi innecesario seguir haciendolo manual porque ya hace un mes que hasta los ordenamientos los hacemos directo(ejemplo ,sort)
3.Implemente dos inicios de sesion para admis y coordinador a traves de su usuario y estudiante con su rut.
4.En la peticion editar usuario, solo permito que se modifiquen 3 atributos ya que el pdf no indica cuentos y 3 atributos me pareciron coherentes para mi diseño de programa(factoryusuario)
5.Hace semanas venia viendo como hacer el sistema menos gigante, la forma que "solucione" eso fue haciendo una clase por funcion o requerimiento, la clase se referenciaba en el sistema y asi eviataba una clase gigante y que tuviera el poder de hacer todo, asi divido responsabilidades.
![image_alt](https://github.com/sebayocefry/Sebastian_Barrera_Taller4_POO_UCN/blob/main/umlTALLER4.png?raw=true)

Estrucutura

├── src/
│   ├── controlador/        // Lógica de Negocio y Gestión
│   │   ├── Controlador.java
│   │   ├── Isistema.java
│   │   ├── GestionCertificaciones.java
│   │   ├── GestionEstudiantes.java
│   │   ├── GestionSeguimientoDashBoard.java
│   │   ├── VisualizacionPerfilEstudiante.java
│   │   ├── FactoryUsuario.java
│   │   └── (Otras clases de gestión y patrones: Estrategias, Reportes, Visitor...)
│   │
│   ├── modelo/             // Entidades de Datos
│   │   ├── Usuario.java
│   │   ├── Estudiante.java
│   │   ├── Cordinador.java
│   │   ├── Curso.java
│   │   ├── Notas.java
│   │   └── (Otras clases de entidad: Registros, Certificaciones hijas...)
│   │
│   └── vista/              // Interfaz Gráfica (GUI)
│       ├── Main.java       // Punto de inicio de la aplicación
│       ├── LoginFrame.java
│       ├── MenuEstudianteFrame.java
│       ├── MenuCoordinadorFrame.java
│       ├── MenuAdminFrame.java
│       └── (Otras ventanas: VentanaMalla, VentanaReporte)
│
└── (Archivos de Persistencia)
    ├── usuarios.txt
    ├── cursos.txt
    ├── notas.txt
    └── ... (resto de archivos .txt)





Instrucciones de Uso del Programa
Una vez que ejecutes la aplicación (vista/Main.java), la interfaz gráfica te guiará a través de las funciones principales.

1. Inicio de Sesión (Login)
Al iniciar, se abrirá la ventana de Login. Debes seleccionar el Tipo de Cuenta correcto para el usuario que vas a ingresar:

Si seleccionas Estudiante, el campo de credencial debe ser el RUT.

Si seleccionas Usuario / Coordinador, el campo de credencial debe ser el Nombre de usuario.

El sistema validará las credenciales y te dirigirá al menú correspondiente a tu rol.

2. Menú Estudiante (Funciones principales)
Como estudiante, accedes a la información y herramientas de seguimiento:

Ver Perfil: Muestra toda la información académica del alumno: datos personales, la malla curricular completa con estado de asignaturas, y el cálculo de promedios generales y por semestre. Esta información se abre en una Ventana de Reporte dedicada.

Malla Interactiva: Abre la vista gráfica de la malla curricular. Los cursos aparecen como bloques de color que indican su estado (Aprobado, Reprobado, Pendiente, Cursando).

Dashboard Progreso: Genera el reporte visual del progreso de tus certificaciones (Minors). Muestra la barra de progreso de cada minor inscrito y mensajes de apoyo del "Mentor Virtual" (patrón Visitor). El reporte se abre en una Ventana de Reporte.

Inscribir Minor: Muestra primero la lista de certificaciones disponibles y sus requisitos. Luego, te pide ingresar el ID del minor para intentar la inscripción, verificando que cumplas con la cantidad mínima de créditos aprobados.

3. Menú Coordinador (Funciones de Gestión y Reporte)
Como coordinador, tienes acceso a la administración y reportes del sistema:

Reportes (Strategy): Las opciones 1 y 2 ejecutan diferentes estrategias de reporte. Por ejemplo, puedes ver la Tasa de Inscripciones (popularidad de minors) o el Reporte de Asignaturas de Alto Riesgo (la materia con más reprobaciones). Ambos reportes se muestran en una Ventana de Reporte.

Gestión de Certificaciones:

Modificar Certificación: Permite actualizar la descripción, los créditos requeridos y la validez en años de un minor existente.

Generar Diplomas: Procesa y genera los documentos de certificación para todos los alumnos que han completado el 100% de un minor específico.

Gestión de Alumnos:

Mostrar Estudiantes por Minor: Genera el listado de todos los alumnos inscritos en una certificación específica.

Validar Avance del Alumno: Permite recalcular el progreso de un estudiante en un minor específico, actualizando su porcentaje.

4. Menú Administrador
El menú de administración permite realizar tareas de mantenimiento del sistema: crear, modificar, eliminar y restablecer contraseñas para las cuentas de Estudiantes y Coordinadores

El objetivo de esta práctica es desarrollar una aplicación en Java que gestione el flujo de negocio de una red de concesionarios. Debes implementar la capa de persistencia utilizando exclusivamente Hibernate+JPA, abandonando por completo el uso de JDBC y sentencias SQL manuales.

La aplicación funcionará mediante un menú de consola. Tu trabajo consiste en dotar de funcionalidad a cada una de las siguientes opciones:

### MENÚ PRINCIPAL

#### 1\. Configuración y Carga de Datos

* 1.1. Iniciar EntityManager: Debe leer la configuración del archivo persistence.xml y establecer la conexión. Si funciona, mostrar "Conexión establecida".  
* 1.2. Datos de Prueba (Seed): Debe borrar el contenido actual de la base de datos y precargar datos ficticios para poder trabajar sin meterlos a mano cada vez:  
  * Un Concesionario principal.  
  * Varios tipos de Equipamiento (ej. "Aire Acondicionado", 500€).  
  * Varios Mecanicos.  
  * Varios Propietarios.  
  * Varios Coches.

#### 2\. Gestión de Stock (Altas)

* 2.1. Alta de Concesionario: Pide nombre y dirección para crear un nuevo punto de venta.  
* 2.2. Alta de Coche: Registra un nuevo vehículo en el stock.  
  * Debe pedir la Matrícula (que será su identificador único).  
  * Debe pedir marca, modelo y precio base.  
  * Debe solicitar el ID de un Concesionario existente para asociarlo (Relación N:1).

#### 3\. Taller y Equipamiento (Relaciones N:M)

* 3.1. Instalar Extra: Pide una matrícula de coche y un ID de equipamiento.  
  * Asocia el equipamiento al coche.  
  * *Lógica:* Debe mostrar por consola el nuevo precio total del coche (Precio Base \+ Suma de precios de extras).  
* 3.2. Registrar Reparación: Pide matrícula de coche e ID de mecánico.  
  * Requisito Adicional: Debe solicitar además la Fecha de la reparación, el Coste de la intervención y una breve Descripción (ej. "Cambio de aceite").

#### 4\. Ventas (Transacciones y Entidad Venta)

* 4.1. Vender Coche: Esta es la operación crítica del negocio.  
  * Pide los datos de un nuevo Propietario (DNI, Nombre).  
  * Pide la matrícula del coche a vender y el ID del Concesionario que realiza la venta.  
  * Requisito de Histórico: Se debe generar un registro de Venta que guarde la fecha actual, el precio final pactado, el comprador y el concesionario vendedor.  
  * Operación Transaccional  
  * *Nota:* Debe controlarse si el coche no existe o ya está vendido.

#### 5\. Consultas e Informes

* 5.1. Stock de Concesionario: Dado un ID de concesionario, lista todos los coches que están actualmente allí (no vendidos).   
* 5.2. Historial de Mecánico: Dado un ID de mecánico, muestra la lista de reparaciones que ha realizado, incluyendo fecha y matrícula del coche.   
* 5.3. Ventas por Concesionario: Listar todas las Ventas realizadas por un determinado Concesionario, mostrando el importe total recaudado.  
* 5.4. Coste actual de Coche: Esta operación sólo debe estar disponible para Coches que tenga un Propietario. Debe mostrar el coste total invertido en el coche incluyendo precio de compra, reparaciones y extras.

## Apreciaciones a tener en cuenta

Para superar la actividad correctamente, debes prestar atención a los siguientes detalles de implementación:

1. La base de datos se debe generar desde el código Java con la opción correspondiente del fichero persistence.xml. No se crea manualmente de manera previa en Workbench, ni mediante sentencia DDL en el código Java.  
2. Claves Primarias (PK):  
   * Para la entidad Coche, la clave primaria es la Matrícula (String). No existe por lo tanto id numérico en la tabla correspondiente. Debe validarse que la matrícula tenga un formato correcto (4 dígitos seguidos de 3 letras).  
   * Para el resto (Concesionario, Mecánico, Venta, etc.) se debe usar un id numérico autogenerado.  
3. Consultas: Está terminantemente prohibido usar SQL nativo. Todas las consultas del apartado 5 deben realizarse utilizando JPQL (Java Persistence Query Language) sobre los objetos.  
4. Relaciones y Entidades Intermedias:  
   * N:M Simple (Coche-Equipamiento): Hibernate genera la tabla intermedia automáticamente (@ManyToMany).  
   * N:M Compleja (Coche-Mecanico): Se usa la entidad explícita Reparacion para guardar atributos extra (fecha, coste).  
   * Histórico (Venta): Es una entidad que vincula Concesionario, Propietario y Coche en un momento del tiempo.  
5. Gestión de Recursos: Deben cerrarse los recursos correctamente una vez finaliza su uso.  
6. Excepciones: Se deben capturar y gestionar todas las excepciones que puedan ser lanzadas, evitando que el programa se detenga con error fatal.

## Requisitos Técnicos

1. Cero SQL Manual: Prohibido usar INSERT, CREATE o SELECT nativo. Todo debe ser gestionado por el ORM.  
2. Configuración Externa: La conexión a BBDD debe estar definida en el fichero src/main/resources/META-INF/persistence.xml.  
3. Anotaciones: Uso correcto de @Entity, @OneToOne (si aplica), @ManyToOne, @ManyToMany y @OneToMany.  
4. Librerías: Uso de Maven para la gestión de dependencias (Hibernate Core \+ MySQL Connector).

## Evaluación

Como parte de la evaluación, el profesor hará una prueba individual en clase a cada alumno que consistirá en la realización de una serie de preguntas sobre su código o la petición de alguna pequeña modificación en vivo del código (p.ej., "Añade un nuevo campo" o "Crea una nueva consulta"). Para poder obtener una calificación distinta de 0 deben superar esta prueba.

## Entrega

Fichero comprimido "AD\_RA3\_Apellidos\_Nombre.zip" conteniendo:

1. Proyecto IntelliJ/Maven completo (¡Importante\! Ejecuta mvn clean antes de comprimir para borrar la carpeta target y que pese menos).  
2. Captura de pantalla del esquema de tablas generado en MySQL (puedes usar Workbench) donde se vean las tablas y las líneas de relación (Foreign Keys).


# ERP Concesionario - Sistema de Gestión

Sistema backend en Java para la gestión de concesionarios de coches, desarrollado con **JPA/Hibernate** y **MySQL**.

---

## Descripción

Aplicación de consola que modela un sistema ERP para concesionarios de vehículos. Permite gestionar:
- Stock de coches por concesionario
- Instalación de equipamientos/extras
- Registro de reparaciones por mecánicos
- Ventas a propietarios
- Consultas e informes con JPQL

---

## Tecnologías

| Tecnología | Versión |
|------------|---------|
| Java | 24 |
| Hibernate | 6.4.0.Final |
| Jakarta Persistence (JPA) | 3.1.0 |
| MySQL Connector/J | 8.3.0 |
| Maven | 3.x |

---

## Estructura del Proyecto

```
src/main/java/org/backend/
├── Main.java                      # Punto de entrada
├── model/                         # Entidades JPA
│   ├── Coche.java
│   ├── Concesionario.java
│   ├── Equipamiento.java
│   ├── Mecanico.java
│   ├── Propietario.java
│   ├── Reparacion.java
│   └── Venta.java
├── service/                       # Lógica de negocio
│   ├── GestorEntityManager.java   # Gestión de conexión JPA
│   ├── CocheService.java          # Operaciones con coches
│   ├── VentaService.java          # Gestión de ventas
│   ├── ConcesionarioService.java  # Alta de concesionarios
│   ├── ConsultaService.java       # Consultas JPQL
│   ├── SeederService.java         # Carga de datos iniciales
│   ├── Validation.java            # Validaciones de entrada
│   └── ApplicationException.java  # Excepción personalizada
└── ui/
    └── MenuPrincipal.java         # Interfaz de consola

src/main/resources/META-INF/
└── persistence.xml                # Configuración JPA/Hibernate
```

---

## Modelo de Datos

### Diagrama de Entidades

```
┌─────────────────┐       1:N      ┌─────────────────┐
│  Concesionario  │───────────────▶│      Coche      │
└─────────────────┘                └─────────────────┘
        │                                  │
        │ 1:N                              │ N:M
        ▼                                  ▼
┌─────────────────┐                ┌─────────────────┐
│      Venta      │                │  Equipamiento   │
└─────────────────┘                └─────────────────┘
        │
        │ N:1                      ┌─────────────────┐
        ▼                          │    Mecanico     │
┌─────────────────┐                └─────────────────┘
│   Propietario   │                        │
└─────────────────┘                        │ 1:N
                                           ▼
┌─────────────────┐       N:1      ┌─────────────────┐
│      Coche      │◀───────────────│   Reparacion    │
└─────────────────┘                └─────────────────┘
```

### Relaciones

| Relación | Tipo | Descripción |
|----------|------|-------------|
| Concesionario → Coche | 1:N | Un concesionario tiene muchos coches |
| Coche ↔ Equipamiento | N:M | Tabla intermedia `coche_equipamiento` |
| Coche → Reparacion | 1:N | Un coche puede tener varias reparaciones |
| Coche ↔ Venta | 1:1 | Un coche solo puede venderse una vez |
| Mecanico → Reparacion | 1:N | Un mecánico realiza muchas reparaciones |
| Propietario → Venta | 1:N | Un propietario puede tener varias compras |
| Concesionario → Venta | 1:N | Un concesionario realiza muchas ventas |

### Tablas en Base de Datos

- `concesionarios` - Datos de concesionarios
- `coches` - Vehículos (PK: matricula)
- `equipamientos` - Extras instalables
- `coche_equipamiento` - Relación N:M (tabla intermedia)
- `mecanicos` - Mecánicos del taller
- `reparaciones` - Historial de reparaciones
- `propietarios` - Compradores
- `ventas` - Registro de ventas

---

## Configuración

### Base de Datos

Configurar en `src/main/resources/META-INF/persistence.xml`:

```xml
<property name="jakarta.persistence.jdbc.url"
          value="jdbc:mysql://localhost:3306/db_concesionario_ra3?createDatabaseIfNotExist=true"/>
<property name="jakarta.persistence.jdbc.user" value="root"/>
<property name="jakarta.persistence.jdbc.password" value="TU_PASSWORD"/>
```

### Propiedades Hibernate

| Propiedad | Valor | Descripción |
|-----------|-------|-------------|
| `hibernate.hbm2ddl.auto` | `update` | Crea/actualiza tablas automáticamente |
| `hibernate.show_sql` | `true` | Muestra SQL generado en consola |
| `hibernate.dialect` | `MySQLDialect` | Dialecto para MySQL |

---

## Funcionalidades

### Menú Principal

```
╔═══════════════════════════════════════╗
║        MENÚ PRINCIPAL                 ║
╠═══════════════════════════════════════╣
║  1. Configuración                     ║
║  2. Gestión de Stock                  ║
║  3. Taller                            ║
║  4. Ventas                            ║
║  5. Consultas                         ║
║  0. Salir                             ║
╚═══════════════════════════════════════╝
```

### 1. Configuración
- **1.1** Iniciar conexión a BD
- **1.2** Cargar datos de prueba (Seed)

### 2. Gestión de Stock
- **2.1** Dar de alta concesionario
- **2.2** Insertar coche nuevo

### 3. Taller
- **3.1** Instalar extra a coche
- **3.2** Registrar reparación

### 4. Ventas
- **4.1** Vender coche a propietario

### 5. Consultas (JPQL)
- **5.1** Stock de concesionario (coches no vendidos)
- **5.2** Historial de mecánico
- **5.3** Ventas por concesionario + Total recaudado
- **5.4** Coste actual de coche (compra + reparaciones + extras)

---

## Consultas JPQL

### Stock de Concesionario
```java
SELECT c FROM Coche c
WHERE c.concesionario.id = :id
AND c.venta IS NULL
```

### Historial de Mecánico
```java
SELECT r FROM Reparacion r
WHERE r.mecanico.id = :id
ORDER BY r.fecha DESC
```

### Ventas por Concesionario
```java
SELECT v FROM Venta v
WHERE v.concesionario.id = :id
```

### Total Recaudado
```java
SELECT SUM(v.precioFinal) FROM Venta v
WHERE v.concesionario.id = :id
```

### Suma de Reparaciones
```java
SELECT COALESCE(SUM(r.coste), 0) FROM Reparacion r
WHERE r.coche.matricula = :matricula
```

### Suma de Extras (JOIN N:M)
```java
SELECT COALESCE(SUM(e.precioUnit), 0) FROM Coche c
JOIN c.equipamientos e
WHERE c.matricula = :matricula
```

---

## Ejecución

### Requisitos Previos
1. Java 17+ instalado
2. MySQL Server corriendo en `localhost:3306`
3. Maven instalado

### Pasos

```bash
# 1. Clonar o descargar el proyecto

# 2. Compilar con Maven
mvn clean compile

# 3. Ejecutar
mvn exec:java -Dexec.mainClass="org.backend.Main"
```

### Primer Uso
1. Seleccionar opción **1.1** para iniciar conexión
2. Seleccionar opción **1.2** para cargar datos de prueba
3. Navegar por las demás opciones

---

## Validaciones

| Campo | Formato | Ejemplo |
|-------|---------|---------|
| Matrícula | 4 dígitos + 3 letras | `1234ABC` |
| DNI | 8 dígitos + 1 letra | `12345678A` |
| Precios | Mayor que 0 | `15000.50` |

---

## Arquitectura

```
┌────────────────────────────────────────────────────────┐
│                    PRESENTACIÓN                        │
│                  MenuPrincipal.java                    │
│            (Interfaz de usuario - Consola)             │
└────────────────────────┬───────────────────────────────┘
                         │
┌────────────────────────▼───────────────────────────────┐
│                     SERVICIOS                          │
│  CocheService │ VentaService │ ConsultaService │ ...   │
│              (Lógica de negocio + JPQL)                │
└────────────────────────┬───────────────────────────────┘
                         │
┌────────────────────────▼───────────────────────────────┐
│                   PERSISTENCIA                         │
│               GestorEntityManager.java                 │
│          (EntityManagerFactory + EntityManager)        │
└────────────────────────┬───────────────────────────────┘
                         │
┌────────────────────────▼───────────────────────────────┐
│                  HIBERNATE/JPA                         │
│            (ORM - Mapeo Objeto-Relacional)             │
└────────────────────────┬───────────────────────────────┘
                         │
┌────────────────────────▼───────────────────────────────┐
│                      MySQL                             │
│              db_concesionario_ra3                      │
└────────────────────────────────────────────────────────┘
```

---

## Manejo de Errores

El proyecto utiliza `ApplicationException` para gestionar errores de negocio:

```java
try {
    em.getTransaction().begin();
    // operaciones
    em.getTransaction().commit();
} catch (Exception e) {
    em.getTransaction().rollback();
    throw new ApplicationException("Mensaje de error");
}
```

---

## Datos de Prueba (Seed)

Al ejecutar la opción 1.2, se cargan:

- 1 Concesionario: "AutoMax Madrid"
- 3 Coches: Diferentes marcas y modelos
- 3 Mecánicos
- 3 Propietarios
- 1 Equipamiento de ejemplo

---

## Autor Anibal Solano

Proyecto desarrollado para la asignatura **Acceso a Datos** - 2º DAM

- GitHub: [@javAnibal](https://github.com/javAnibal)
- LinkedIn: [Mi perfil](https://www.linkedin.com/in/https://www.linkedin.com/in/anibal-solano-f//)
- Email: [a88anibal@gmail.com](mailto:a88anibal@gmail.com)
---

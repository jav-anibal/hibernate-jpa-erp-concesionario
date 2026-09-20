# ERP Concesionario — JPA / Hibernate / MySQL

Proyecto Java centrado en persistencia relacional y modelado de dominio con JPA/Hibernate.

## Stack

- Java 24
- Hibernate 6.4
- Jakarta Persistence (JPA) 3.1
- MySQL
- Maven

## Qué modela

El sistema gestiona concesionarios, vehículos, equipamiento, reparaciones, propietarios y ventas.

Incluye relaciones:

- 1:N entre concesionario y vehículos;
- N:M entre vehículos y equipamientos;
- 1:N entre vehículos y reparaciones;
- 1:1 entre vehículo y venta;
- 1:N entre propietario y ventas.

## Arquitectura

```text
UI de consola
     |
Servicios
     |
EntityManager / JPA
     |
Hibernate
     |
MySQL
```

La lógica de acceso a datos y las consultas JPQL se concentran en servicios, manteniendo separadas la presentación y la persistencia.

## Casos trabajados

- gestión de stock;
- alta de concesionarios y vehículos;
- instalación de equipamientos;
- registro de reparaciones;
- ventas;
- consultas agregadas con JPQL;
- control manual de transacciones y rollback;
- validaciones de entrada.

## Consultas JPQL

Ejemplos incluidos en el proyecto:

```java
SELECT c FROM Coche c
WHERE c.concesionario.id = :id
AND c.venta IS NULL
```

```java
SELECT SUM(v.precioFinal) FROM Venta v
WHERE v.concesionario.id = :id
```

```java
SELECT COALESCE(SUM(e.precioUnit), 0) FROM Coche c
JOIN c.equipamientos e
WHERE c.matricula = :matricula
```

## Ejecución

Requisitos:

- Java 24
- Maven
- MySQL

Configurar la conexión en:

```text
src/main/resources/META-INF/persistence.xml
```

Después:

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="org.backend.Main"
```

## Objetivo del proyecto

Proyecto desarrollado durante 2º DAM para profundizar en persistencia relacional, JPA/Hibernate, modelado de entidades, relaciones y consultas JPQL.

---

**Anibal Solano**  
Backend Developer · Java · Spring Boot · PostgreSQL

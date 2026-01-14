# RED - CONCESIONARIOS 
Proyecto Java backend con JPA/Hibernate 
Modela un sistema de concesionarios de coches. 
Incluye diseño de dominio, relaciones entre entidades, persistencia con MySQL, 
Seed de datos iniciales y base para lógica de negocio (ventas, reparaciones y gestión de vehículos).

Arquitectura del proyecto
Estructura por capas:
```
src/
 └── org.backend
     ├── model        → Entidades JPA (Dominio)
     ├── repository   → Lógica del negocio
     ├── service      → Gestión de EntityManager 
     └── Main.java    → Punto de entrada
```

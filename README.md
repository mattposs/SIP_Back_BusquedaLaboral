# Skill Passport — Backend

Backend de **Skill Passport**, una plataforma que invierte la dinámica tradicional de búsqueda laboral: hace visibles las habilidades reales de los candidatos y les da poder de negociación frente a las empresas mediante matching transparente, validación de habilidades entre pares y postulación anónima (Reverse Pitch).

> 🔗 Frontend del proyecto: [skills_passport_ui](https://github.com/rociobottinelli/skills_passport_ui)

---

## 📋 Tabla de contenidos

- [Stack tecnológico](#stack-tecnológico)
- [Arquitectura funcional](#arquitectura-funcional)
- [Requisitos previos](#requisitos-previos)
- [Puesta en marcha](#puesta-en-marcha)
- [Variables de entorno](#variables-de-entorno)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Documentación de la API (Swagger)](#documentación-de-la-api-swagger)
- [Endpoints principales](#endpoints-principales)
- [Algoritmo de Pesos (Matching y Validaciones)](#algoritmo-de-pesos-matching-y-validaciones)
- [Base de datos y migraciones](#base-de-datos-y-migraciones)
- [Observabilidad](#observabilidad)
- [Testing](#testing)
- [Equipo](#equipo)

---

## Stack tecnológico

| Categoría | Tecnología |
|---|---|
| Lenguaje | Java 25 |
| Framework | Spring Boot 4.1 (Web MVC, Data JPA, Security, Validation) |
| Base de datos | PostgreSQL 17 |
| Migraciones | Flyway |
| Autenticación | JWT (io.jsonwebtoken) + Spring Security + BCrypt |
| Documentación API | springdoc-openapi (Swagger UI) |
| Observabilidad | OpenTelemetry + Micrometer (OTLP) → Grafana LGTM stack |
| Build | Maven |
| Utilidades | Lombok, Apache Commons Lang3 |
| Contenedores | Docker / Docker Compose |

---

## Arquitectura funcional

El backend es responsable de toda la lógica de negocio que sostiene las tres funcionalidades core del MVP:

1. **Match Transparente**: calcula el `Match Score` entre candidatos y ofertas laborales, y expone el desglose de habilidades coincidentes.
2. **Validación Peer-to-Peer**: gestiona las solicitudes de validación de habilidades entre pares y calcula el puntaje ponderado del candidato según el Algoritmo de Pesos.
3. **Reverse Pitch (anonimato)**: intercepta las consultas de reclutadores y enmascara la identidad del candidato (nombre, foto, contacto) hasta que este decide revelar su perfil.

---

## Requisitos previos

- Java 25 (JDK)
- Maven 3.9+
- Docker y Docker Compose
- Node/npm no es necesario para el backend (solo para el frontend)

---

## Puesta en marcha

### 1. Clonar el repositorio

```bash
git clone https://github.com/<org>/skill-passport-backend.git
cd skill-passport-backend
```

### 2. Levantar los servicios de infraestructura (PostgreSQL + stack de observabilidad)

```bash
docker compose up -d
```

Esto levanta:
- **PostgreSQL 17** en el puerto `5432` (db: `postgres`, user: `user`, password: `secret`)
- **Grafana OTEL-LGTM** (Grafana + Loki + Tempo + Prometheus) en el puerto `3000`, receptor OTLP en `4317` (gRPC) y `4318` (HTTP)

### 3. Ejecutar la aplicación

```bash
./mvnw spring-boot:run
```

Al iniciar, Flyway aplica automáticamente las migraciones (`src/main/resources/db/migration`), incluyendo el set de datos semilla (seed data) para pruebas y demos.

La API queda disponible en `http://localhost:8080`.

---

## Variables de entorno

| Variable | Default | Descripción |
|---|---|---|
| `jwt.secret` | `MySuperSecretKeyForSkillPassportApp2026!` | Clave de firma HS256 para los JWT |
| `jwt.expiration` | `86400000` (1 día, en ms) | Tiempo de expiración del token |
| `spring.datasource.url` / `username` / `password` | — | Conexión a PostgreSQL |

> ⚠️ Los valores por defecto de `jwt.secret` están pensados solo para desarrollo local. En cualquier ambiente compartido deben sobreescribirse por variables de entorno o `application.yml` propio del ambiente.

---

## Estructura del proyecto

```
src/main/java/com/sip/tp/
├── controller/       # Controladores REST (Auth, Candidate, Validation, Offer, Match, Messaging, Company)
├── service/
│   └── domain/       # Lógica de negocio por dominio (auth, candidate, validation, matching, messaging)
├── entity/            # Entidades JPA
├── dto/
│   ├── request/       # DTOs de entrada
│   └── response/      # DTOs de salida
├── config/            # Configuración de Security, JWT filter, etc.
└── types/             # Enums y tipos de dominio (UserType, etc.)

src/main/resources/
└── db/migration/      # Scripts Flyway (V1__init.sql, V2__seed_data.sql, ...)
```

---

## Documentación de la API (Swagger)

Con la app corriendo, la documentación interactiva está disponible en:

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

Los endpoints de `/auth/**`, `/swagger-ui/**` y `/v3/api-docs/**` son públicos; el resto requiere JWT en el header `Authorization: Bearer <token>`.

---

## Endpoints principales

### Auth
- `POST /auth/register` — registro de Candidato o Reclutador
- `POST /auth/login` — login, devuelve JWT

### Perfil del candidato
- `GET /candidates/me` — perfil propio
- `PUT /candidates/me` — actualizar perfil
- `POST /candidates/me/photo` — subir foto de perfil
- `GET /candidates/me/completion` — porcentaje de completitud del perfil
- `POST /candidates/me/experience` / `PUT .../{id}` / `DELETE .../{id}` — experiencia laboral
- `POST /candidates/me/projects` / `PUT .../{id}` / `DELETE .../{id}` — proyectos

### Validaciones (Peer-to-Peer)
- `GET /candidates/me/validations/given` — validaciones que di
- `GET /candidates/me/validations/received` — validaciones que recibí
- `GET /candidates/me/validation-requests` — solicitudes entrantes para validar a otros
- `GET /skills/{skillId}/suggested-validators` — validadores sugeridos para una habilidad
- `POST /validations` — enviar una validación
- `POST /validation-requests/{id}/reject` — rechazar una solicitud

### Ofertas
- `POST /offers` — crear oferta (reclutador)
- `GET /offers` / `GET /offers/{id}` — listar / detalle
- `PUT /offers/{id}` — actualizar
- `POST /offers/{id}/publish` — publicar oferta en borrador

### Matches
- `GET /candidates/me/matches` — ofertas matcheadas para el candidato, ordenadas por score
- `GET /candidates/me/matches/{offerId}` — detalle de match (skills coincidentes/faltantes)
- `POST /candidates/me/matches/{offerId}/interest` — "Me interesa" (dispara revelado de perfil)
- `POST /candidates/me/matches/{offerId}/decline` — "No me interesa"
- `GET /offers/{offerId}/candidates` — candidatos matcheados para el reclutador
- `GET /offers/{offerId}/candidates/{candidateId}` — detalle del candidato (completo o limitado según revelado)

### Mensajería anónima (Reverse Pitch)
- `POST /anonymous-threads` — candidato crea hilo anónimo
- `GET /candidates/me/anonymous-threads` / `GET .../{id}` — inbox y detalle del candidato
- `GET /offers/{offerId}/anonymous-threads` / `GET .../{id}` — vista del reclutador (sin identidad del candidato)
- `POST /anonymous-threads/{id}/messages` — enviar mensaje

### Empresa
- `POST /companies` / `PUT /companies/{id}` / `GET /companies/{id}`

---

## Algoritmo de Pesos (Matching y Validaciones)

El motor de validación pondera cada evaluación recibida por un candidato según el perfil del validador:

| Condición del validador | Multiplicador |
|---|---|
| Empresa Partner | ×1.8 |
| Perfil Senior / Líder | ×1.5 |
| Consiguió su empleo actual vía la plataforma (éxito previo) | ×1.2 |

Este puntaje ponderado alimenta el **nivel consolidado** de cada habilidad del candidato (`Colaborador` → `Ejecutor autónomo` → `Líder` → `Referente`), visible en el perfil y en el detalle enriquecido que ve el reclutador una vez que el candidato revela su perfil.

---

## Base de datos y migraciones

Las migraciones viven en `src/main/resources/db/migration` y se aplican automáticamente al levantar la app (Flyway). Incluyen:

- Esquema completo (usuarios, candidatos, reclutadores, empresas, skills, experiencias, proyectos, ofertas, matches, validaciones, hilos anónimos)
- Datos semilla (`V2__seed_data.sql`) con candidatos, validadores, empresas y ofertas de ejemplo para demos y desarrollo local

---

## Observabilidad

El proyecto expone métricas, logs y trazas vía OpenTelemetry (OTLP) hacia el stack `grafana/otel-lgtm` levantado en `docker-compose`:

- Grafana: `http://localhost:3000`
- Endpoint OTLP gRPC: `localhost:4317`
- Endpoint OTLP HTTP: `localhost:4318`

Incluye además Spring Boot Actuator para health checks y métricas de la aplicación.

---

## Testing

```bash
./mvnw test
```

Cobertura de tests unitarios sobre servicios de dominio (autenticación, validaciones, matching) usando JUnit 5 y los starters `-test` de Spring Boot.

---

## Equipo — Grupo G03 (Equipo 3)

| Integrante | Rol |
|---|---|
| Kiara Pisera | Desarrolladora Backend |
| José Sierra | Desarrollador Backend |
| Joaquín Maver | Product Owner |
| Bautista Bullejos | Analista Funcional |
| Miguel Indriago | Especialista QA |
| Matías Posse Presa | Infraestructura y Seguridad |
| Sebastián Martínez Banega | Project Manager |
| Rocío Bottinelli | Desarrolladora Frontend |

Proyecto desarrollado en el marco de **Seminario de Integración Profesional** — UADE, bajo la guía de los profesores Agustín Grangetto y Sebastián Jorge Viñuela.

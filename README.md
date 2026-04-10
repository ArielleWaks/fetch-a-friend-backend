# Fetch a Friend — Backend

REST API for a pet-sitting style platform: job listings, clients, badges, JWT authentication, and image uploads. Built with **Spring Boot 3.2** and **Java 17**, backed by **MySQL** in local development.

The user-facing app lives in the companion repo **[fetch-a-friend-frontend](https://github.com/ArielleWaks/fetch-a-friend-frontend)** — a **React** (Create React App) SPA that calls this API. If you keep both projects as sibling folders, the frontend path is `../fetch-a-friend-frontend` relative to this repository.

## Companion frontend

| | |
|---|---|
| **Stack** | React 18, React Router, Axios, MUI / Bootstrap, Google Maps components |
| **Dev server** | `npm install` then `npm start` (default **http://localhost:3000**) |
| **API in development** | `package.json` sets `"proxy": "http://localhost:8080"`, so browser requests to relative paths like `/api/auth/...` are forwarded to this backend — **run `./gradlew bootRun` first** |

Map-related screens expect a **`REACT_APP_GOOGLE_MAP_API`** key in the frontend `.env` (create-react-app convention). Do not commit real keys to git.

## Prerequisites

- [Java 17](https://adoptium.net/) (matches `build.gradle` `sourceCompatibility`)
- [Docker](https://docs.docker.com/get-docker/) (optional, for MySQL via Compose)

## Quick start

### 1. Start MySQL

From the project root:

```bash
docker compose up -d
```

This starts MySQL 8 with database `fetch-a-friend`, user `fetch-a-friend`, and password `goFetch` (see `docker-compose.yml`). Port **3306** is published to the host.

### 2. Run the application

```bash
./gradlew bootRun
```

The API listens on **http://localhost:8080** by default (Spring Boot default).

To exercise the UI, start the [companion frontend](#companion-frontend) on port 3000 with this API already running.

### 3. Build a runnable JAR

```bash
./gradlew bootJar
```

The JAR is written to `build/libs/`. The build also runs AsciiDoctor and bundles generated REST Docs under `static/docs` in the JAR.

## Configuration

Settings live in `src/main/resources/application.properties`.

| Property | Purpose |
|----------|---------|
| `spring.datasource.*` | MySQL URL, username, password |
| `spring.jpa.hibernate.ddl-auto` | Schema strategy (`update` in dev) |
| `bezkoder.app.jwtSecret` | Secret for signing JWTs |
| `bezkoder.app.jwtExpirationMs` | Token lifetime (ms) |
| `spring.servlet.multipart.*` | Max upload size (10MB) |

**Production:** Do not commit real secrets. Prefer environment variables or a secrets manager, and replace the placeholder JWT secret and database credentials.

Optional seed data for roles and badges is in `src/main/resources/data.sql`. To run SQL init on startup, enable Spring’s SQL init mode in your profile (it is commented out in the default `application.properties`).

## API overview

Base URL: `http://localhost:8080`

| Area | Base path | Notes |
|------|-----------|--------|
| Auth | `/api/auth` | `POST /signin`, `POST /signup` — public |
| Users | `/api/user` | Profile-style routes — configured as public in security |
| Jobs | `/api/jobs` | Listings, bookmarks, sitter assignment — some paths public (`GET /api/jobs`, `GET /api/jobs/open`), others require a JWT |
| Badges | `/api/badges` | Badge listing and “my badges” — requires JWT |
| Clients | `/clients` | Client CRUD and search — see `WebSecurityConfig` for which HTTP methods and paths are anonymous |
| Files | `/file`, `/image/**` | Upload/download; JPEG and PNG supported for uploads |

Authenticated requests should send the JWT (typically `Authorization: Bearer <token>`) as produced by `/api/auth/signin`.

For exact request bodies and responses, use the controllers under `src/main/java/org/launch_code/fetch_a_friend/controllers/` or import **`postman/fancyrats.postman_collection.json`** into Postman.

## Security model (summary)

`WebSecurityConfig` disables CSRF, uses stateless sessions, and applies a JWT filter. Several paths are explicitly permitted without authentication (auth endpoints, some jobs and client routes, file/image access). Everything else requires a valid JWT.

## Tests

```bash
./gradlew test
```

`src/main/resources/application-test.properties` configures an in-memory **H2** database, but the default test context does not activate the `test` profile automatically. If tests fail with database connection errors, either run MySQL locally or activate `spring.profiles.active=test` for your test run/configuration.

## Project layout

- `src/main/java/org/launch_code/fetch_a_friend/` — application code (`BackendApplication` entry point)
- `src/main/resources/` — `application.properties`, optional `data.sql`
- `postman/` — Postman collection
- `docker-compose.yml` — local MySQL

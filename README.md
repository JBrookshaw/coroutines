# coroutines

Micronaut + Kotlin sample service demonstrating coroutine-based APIs, fan-out aggregation, and streaming endpoints.

## Requirements

- JDK 21+
- Gradle wrapper (included)

## Run locally

```bash
./gradlew run
```

App starts on `http://localhost:8080` by default.

## Test

```bash
./gradlew test
```

## Useful endpoints

- `GET /aggregate` – fan-out to multiple services and combine the result
- `GET /fbi/list?page=1` – fetch FBI wanted list (proxied)
- `GET /fbi/highest-reward` – compute highest reward URL across pages

## Notes

- FBI API base URL is configured in `src/main/resources/application.yml`.
- JSON naming strategy is snake_case via Micronaut Serde config.

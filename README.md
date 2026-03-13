# AUTO_API_PETSTORE_SCREENPLAY

Automatización de API REST para [Petstore Swagger](https://petstore.swagger.io/#/user) utilizando el patrón **Screenplay** con **Serenity BDD** y **Serenity REST**.

## Objetivo

Validar la integridad de los servicios REST del módulo **User** de la Petstore API mediante un flujo CRUD completo que ejercita los 4 verbos HTTP:

| Verbo    | Endpoint              | Acción            |
|----------|-----------------------|-------------------|
| `POST`   | `/v2/user`            | Crear usuario     |
| `GET`    | `/v2/user/{username}` | Consultar usuario |
| `PUT`    | `/v2/user/{username}` | Actualizar usuario|
| `DELETE` | `/v2/user/{username}` | Eliminar usuario  |

## Stack Tecnológico

| Herramienta                  | Versión   |
|------------------------------|-----------|
| Java                         | 17+       |
| Gradle                       | 8.10.2    |
| Serenity BDD                 | 5.3.2     |
| Serenity Screenplay REST     | 5.3.2     |
| Serenity Cucumber            | 5.3.2     |
| Cucumber JUnit Platform      | 7.34.2    |
| JUnit Platform Suite         | 6.0.3     |
| JUnit Jupiter Engine         | 6.0.3     |
| JUnit Vintage Engine         | 6.0.3     |

- **Lenguaje:** Java 17
- **Gestión de dependencias:** Gradle
- **Framework de pruebas:** Serenity BDD 5.3.2
- **Patrón de diseño:** Screenplay
- **Cliente REST:** Serenity REST (Rest Assured)
- **BDD:** Cucumber con Gherkin
- **Test Runner:** JUnit Platform Suite (`@Suite` + `@IncludeEngines("cucumber")`)

## Estructura del Proyecto

```
src/test/
├── java/com/petstore/
│   ├── hooks/             # Configuración del Stage y actor (PetstoreHooks)
│   ├── models/            # POJO del usuario (User)
│   ├── questions/         # Preguntas: ResponseStatusCode, ResponseBodyField
│   ├── runners/           # Runner de Cucumber (TestRunner)
│   ├── stepdefinitions/   # Definiciones de pasos Gherkin (PetstoreUserSteps)
│   └── tasks/             # Tareas: CreateUser, GetUser, UpdateUser, DeleteUser
└── resources/
    ├── features/          # Feature file: petstore_user_crud.feature
    └── serenity.conf      # Configuración de Serenity (base URL, screenshots)
```

## Capas del Patrón Screenplay

| Capa               | Clase                          | Responsabilidad                              |
|---------------------|--------------------------------|----------------------------------------------|
| **Tasks**           | `CreateUser`                   | Ejecuta POST para crear un usuario           |
|                     | `GetUser`                      | Ejecuta GET para consultar un usuario        |
|                     | `UpdateUser`                   | Ejecuta PUT para actualizar un usuario       |
|                     | `DeleteUser`                   | Ejecuta DELETE para eliminar un usuario      |
| **Questions**       | `ResponseStatusCode`           | Obtiene el código de estado HTTP              |
|                     | `ResponseBodyField`            | Extrae un campo del body JSON de respuesta   |
| **Models**          | `User`                         | POJO que representa el recurso User          |
| **Hooks**           | `PetstoreHooks`                | Inicializa el Stage y asigna la habilidad `CallAnApi` al actor |
| **Step Definitions**| `PetstoreUserSteps`            | Conecta los pasos Gherkin con Tasks y Questions |
| **Runner**          | `TestRunner`                   | Ejecuta los escenarios con `@petstore` tag   |

## Escenario de Prueba

El feature `petstore_user_crud.feature` ejecuta un **Scenario Outline** con el tag `@petstore` que cubre el ciclo completo:

1. **POST** — Crea el usuario con datos parametrizados.
2. **GET** — Consulta el usuario recién creado y verifica el username en el body.
3. **PUT** — Actualiza el correo del usuario.
4. **DELETE** — Elimina el usuario y verifica código 200.

## Prerrequisitos

- Java 17+
- Conexión a internet (la API es pública: `https://petstore.swagger.io/v2`)

## Ejecución

```bash
# Ejecutar todos los tests y generar reporte Serenity
./gradlew clean test

# Ejecutar solo escenarios con tag @petstore
./gradlew clean test -Dcucumber.filter.tags="@petstore"

# Generar reporte Serenity manualmente (se ejecuta automáticamente tras test)
./gradlew aggregate
```

## Reportes

Tras la ejecución, el reporte de Serenity se genera en:

```
target/site/serenity/index.html
```

> Los reportes incluyen el detalle de cada paso REST: URL, headers, body de request/response y código de estado HTTP.
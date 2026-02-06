# LibraryTrackerApp

[![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack_Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Retrofit](https://img.shields.io/badge/Retrofit-005571?style=for-the-badge&logo=square&logoColor=white)](https://square.github.io/retrofit/)

Cliente móvil desarrollado para la **Gestión de Biblioteca Personal**. Esta aplicación permite catalogar libros y gestionar usuarios mediante autenticación JWT, utilizando una arquitectura moderna, reactiva y testeable.

---

## 🏗️ Arquitectura del Proyecto
La aplicación implementa **Clean Architecture** dividida en capas para asegurar el desacoplamiento y la escalabilidad:

* **UI (Presentation):** Implementada con **Jetpack Compose** y siguiendo el patrón **MVVM**.
* **Domain:** Contiene la lógica de negocio pura (Use Cases), modelos de dominio y las interfaces de los repositorios.
    * **model:** Modelo de datos para la aplicación.
    * **repository:** Interfaz que representa las operaciones de la api.
    * **usecase:** Casos de uso para poder interactuar con la api.
* **Data:** Gestión de datos que incluye:
    * **Network:** Consumo de API REST con Retrofit.
    * **Mappers:** Transformación de objetos de red (DTO) a modelos de dominio.
    * **Repository Impl:** Lógica de acceso a datos.
* **DI (Dependency Injection):** Configuración de dependencias con **Hilt**.

## 📂 Estructura de Directorios

```text
com.example.librarytrackerapp
├── data
│   ├── network
│   │   ├── client      # Interfaces de Retrofit (BookTrackerClient)
│   │   ├── service     # Gestión de respuestas de red (BookTrackerService)
│   │   └── model       # DTOs (Data Transfer Objects)
│   ├── repository      # Implementación de repositorios
│   └── mapper          # Funciones de transformación DTO -> Domain
├── domain
│   ├── model           # Modelos de dominio limpios
│   ├── repository      # Interfaces (Contratos)
│   └── usecase         # Casos de Uso (GetBooksUseCase)
├── di                  # Módulos de Hilt (NetworkModule)
├── ui                  # Capa de presentación (Screens, ViewModels)
└── LibraryTrackerApp.kt # Punto de entrada (Application)
```

## 🛠️ Casos de Uso (Lógica de Negocio)

### 📖 Dominio Libro
* **GetBooksUseCase:** Recupera el listado de libros disponibles.

---

## ⚙️ Configuración del Entorno
Para conectar con la API local:

1. Localiza el archivo `ProviderModule.kt` en `di`.
2. Configura la `BASE_URL`:
    - **Emulador Android:** `http://10.0.2.2:8000/`
    - **Dispositivo Físico:** `http://<TU_IP_LOCAL>:8000/`

> [!IMPORTANT]
> Se requiere el permiso `INTERNET` y la propiedad `android:usesCleartextTraffic="true"` en el `AndroidManifest.xml` para conexiones HTTP locales.

---

## 🚀 Solución de Errores
Si experimentas problemas con `kotlin.sourceSets` debido a las nuevas restricciones de Gradle, añade esto a tu `gradle.properties`:

```properties
android.disallowKotlinSourceSets=false
```

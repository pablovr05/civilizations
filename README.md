# Civilizations

Civilizations es un simulador de batallas desarrollado en Java. En este proyecto, los jugadores pueden gestionar civilizaciones, entrenar unidades militares, mejorar tecnologías y enfrentar otras civilizaciones en combates estratégicos.

## Características

- Gestión de recursos (madera, hierro, comida, mana).
- Entrenamiento de diferentes tipos de unidades militares (espadachines, lanceros, ballesteros, cañones, etc.).
- Construcción de estructuras defensivas (torres de flechas, catapultas, lanzacohetes).
- Uso de unidades especiales (magos, sacerdotes).
- Mejoras tecnológicas para aumentar la defensa y el ataque.
- Simulación de batallas entre civilizaciones.

## Requisitos

- Java 8 o superior
- IDE de Java (Eclipse, IntelliJ IDEA, NetBeans, etc.)

## Instalación

1. Clona el repositorio:

```bash
git clone https://github.com/tu_usuario/civilizations.git
```
## Instalación

1. Importa el proyecto en tu IDE de Java preferido.
2. Compila el proyecto.
3. Ejecuta la clase principal `Main.java`.

## Uso

1. Crea una nueva civilización y asigna un nombre.
2. Gestiona los recursos de tu civilización.
3. Entrena unidades militares y construye estructuras defensivas.
4. Mejora las tecnologías de defensa y ataque.
5. Participa en batallas contra otras civilizaciones y gana experiencia.

## Base de Datos

El proyecto utiliza una base de datos para almacenar las estadísticas de las civilizaciones y las unidades militares. Asegúrate de tener una base de datos Oracle configurada y actualiza las credenciales de acceso en la clase `AppData`.

### Tablas de la Base de Datos

- `Civilization_stats`
- `attack_units_stats`
- `defense_units_stats`
- `special_units_stats`
- `Battle_stats`
- `Enemy_attack_stats`
- `Civilization_attack_stats`
- `Civilization_defense_stats`
- `Civilization_special_stats`
. `Battle_log`

## Contribuciones

Las contribuciones son bienvenidas. Para contribuir:

1. Haz un fork del proyecto.
2. Crea una rama nueva (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza los cambios y haz commit (`git commit -m 'Añadir nueva funcionalidad'`).
4. Envía los cambios a tu fork (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

## Contacto

Para cualquier consulta o sugerencia, puedes contactar a [Joel Martínez](mailto:joelmv2004@gmail.com).

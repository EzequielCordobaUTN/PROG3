# Trabajo Práctico: Consumo de The Simpsons API con TypeScript

## 1. Configuración inicial

### `npm init -y`
Inicializa un proyecto de Node.js y genera el archivo `package.json` con valores por defecto, sin hacer preguntas interactivas. Ese archivo registra el nombre del proyecto, las dependencias y los scripts disponibles.

### `typescript --save-dev`
TypeScript se instala como dependencia de desarrollo porque solo se necesita para compilar el código a JavaScript. En producción se entrega el JavaScript compilado, por lo que no debe ser una dependencia normal del runtime.

### Opciones de `tsconfig.json`
- `strict: true`: activa las comprobaciones estrictas de tipos, evitando errores comunes como valores `null`/`undefined` no controlados.
- `target: "ES2020"`: indica la versión de JavaScript a la que se compila el código.
- `outDir: "./dist"`: carpeta de salida del JavaScript compilado.

### Scripts
- `npm run build`: ejecuta `tsc` y compila el proyecto una sola vez en `./dist`.
- `npm run watch`: ejecuta `tsc --watch` y recompila automáticamente cuando se guardan cambios.
- `npm start`: inicia un servidor estático para abrir la aplicación en el navegador.

## 2. Documentación de la API

- **URL base:** `https://thesimpsonsapi.com/api/characters`
- **Método:** `GET`
- **Respuesta:** objeto con `count`, `next`, `prev`, `pages` y `results`.

Estructura de un personaje:

```json
{
  "id": 1,
  "age": 39,
  "birthdate": "1956-05-12",
  "gender": "Male",
  "name": "Homer Simpson",
  "occupation": "Safety Inspector",
  "portrait_path": "/character/1.webp",
  "phrases": ["Doh!", "..."],
  "status": "Alive"
}
```

La imagen se construye como: `https://cdn.thesimpsonsapi.com/500` + `portrait_path`.

## 3. Estructura del proyecto

```text
U4/Act1/
├── index.html
├── package.json
├── tsconfig.json
├── README.md
├── src/
│   ├── main.ts
│   └── styles.css
└── dist/
    └── main.js
```

## 4. Ejecución

```bash
npm install
npm run build
```

Luego iniciar un servidor local (no abrir `index.html` con doble clic, porque los módulos ES se bloquean con `file://`).

Opción 1 (npm):

```bash
npm run start
```

Opción 2 (Python):

```bash
python -m http.server 8000
```

Abrir en el navegador:

```text
http://localhost:8000/index.html
```

Si no se ven los cambios de estilos o scripts, forzar recarga con `Ctrl+F5`.

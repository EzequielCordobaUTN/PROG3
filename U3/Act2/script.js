// ================= Punto 1: Funciones declarativas y expresadas =================
// Función declarativa: se declara con "function". Se "hoistea", puede usarse antes de su definición.
function cuadrado(numero) {
  return numero * numero;
}

// Función expresada: se asigna a una variable. No se hoistea; existe desde esta línea.
const cubo = function (numero) {
  return numero * numero * numero;
};

document.getElementById("outCua").textContent = `cuadrado(5) = ${cuadrado(5)}`;
document.getElementById("outCub").textContent = `cubo(3) = ${cubo(3)}`;

// ================= Punto 2: Arrow function con parámetro por defecto =================
// "edad = 18" es un valor por defecto: si no se pasa, usa 18.
const saludar = (nombre, edad = 18) => `Hola ${nombre}, tienes ${edad} años`;

document.getElementById("outSaludo1").textContent = saludar("Ana");        // usa default
document.getElementById("outSaludo2").textContent = saludar("Luis", 25);  // pasa edad

// ================= Punto 3: Objeto con propiedades y métodos =================
const persona = {
  nombre: "María",
  edad: 30,
  // método: función asociada al objeto
  presentar() {
    return `Hola, soy ${this.nombre} y tengo ${this.edad} años.`;
  }
};

document.getElementById("outPresentacion").textContent = persona.presentar();

// ================= Punto 4: Desestructuración =================
// Extraemos propiedades del objeto en variables con una sola línea.
const { nombre, edad } = persona;

document.getElementById("outDes1").textContent = `Nombre: ${nombre}`;
document.getElementById("outDes2").textContent = `Edad: ${edad}`;

// ================= Punto 5: Spread y Rest =================
// Spread: expande un array en elementos individuales para combinar.
const numeros = [1, 2, 3];
const masNumeros = [...numeros, 4, 5, 6];

document.getElementById("outSpread").textContent =
  `Original: [${numeros}] | Con spread: [${masNumeros}]`;

// Rest: agrupa todos los argumentos recibidos en un array.
function sumar(...valores) {
  return valores.reduce((acc, n) => acc + n, 0);
}

document.getElementById("outSuma").textContent = `sumar(1,2,3,4) = ${sumar(1, 2, 3, 4)}`;

// ================= Punto 6: Manipulación básica del DOM =================
const titulo = document.getElementById("titulo");
// Cambiamos el texto del h1 con textContent.
titulo.textContent = "Lista de tareas interactiva";

const lista = document.getElementById("lista");

// Función auxiliar para agregar un <li> a la lista.
function agregarItem(texto) {
  const li = document.createElement("li");
  li.textContent = texto;
  lista.appendChild(li);
}

// Agregamos un par de elementos nuevos con createElement + appendChild.
agregarItem("Elemento agregado 1 (createElement)");
agregarItem("Elemento agregado 2 (appendChild)");

// Usamos classList: add, remove, toggle.
titulo.classList.add("destacado");
// Ejemplo de toggle (alternar): si la clase está, la quita; si no, la agrega.
// titulo.classList.toggle("destacado");

// ================= Punto 7: Eventos click e input =================
const inputTarea = document.getElementById("inputTarea");
const btnAgregar = document.getElementById("btnAgregar");
const livePreview = document.getElementById("livePreview");

function agregarTareaDesdeInput() {
  const texto = inputTarea.value.trim();
  if (texto === "") return; // no agregar vacíos
  agregarItem(texto);
  inputTarea.value = "";
  livePreview.textContent = "Vista previa: ";
}

// Evento click en el botón.
btnAgregar.addEventListener("click", agregarTareaDesdeInput);

// Evento input: se dispara en cada pulsación, actualizando la vista previa.
inputTarea.addEventListener("input", () => {
  livePreview.textContent = "Vista previa: " + inputTarea.value;
});

// ================= Punto 8: Evento submit y preventDefault =================
const form = document.getElementById("formulario");
const inputForm = document.getElementById("inputForm");
const msgForm = document.getElementById("msgForm");

form.addEventListener("submit", (e) => {
  // preventDefault evita que el formulario recargue la página.
  e.preventDefault();
  msgForm.textContent = `Formulario enviado con valor: ${inputForm.value}`;
});

// ================= Punto 9: Eventos keydown y change =================
// keydown: detecta Enter en el input para agregar la tarea.
inputTarea.addEventListener("keydown", (e) => {
  if (e.key === "Enter") {
    e.preventDefault();
    agregarTareaDesdeInput();
  }
});

const selector = document.getElementById("selector");
const msgSelect = document.getElementById("msgSelect");

// change: detecta cuando cambia la opción seleccionada.
selector.addEventListener("change", (e) => {
  msgSelect.textContent = `Opción seleccionada: ${e.target.value || "(ninguna)"}`;
});

console.log("Trabajo Práctico cargado correctamente.");

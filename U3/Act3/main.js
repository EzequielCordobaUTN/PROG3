// ============================================================
// Código base: base de datos simulada (usuarios)
// ============================================================
const users = [
  { id: 1, name: "Ana" },
  { id: 2, name: "Luis" },
  { id: 3, name: "María" }
];

// Helper para mostrar resultados también en la página (si hay DOM)
function log(msg) {
  console.log(msg);
  const el = typeof document !== "undefined" ? document.getElementById("log") : null;
  if (el) el.textContent += msg + "\n";
}

// ============================================================
// Actividad 1: Callbacks
// ============================================================
// getUserById(id, callback): simula una petición con setTimeout de 1.5s.
// Usa el patrón (error, resultado) en el callback.
function getUserById(id, callback) {
  setTimeout(() => {
    const user = users.find((u) => u.id === id);
    if (user) {
      callback(null, user);
    } else {
      callback("Usuario no encontrado", null);
    }
  }, 1500);
}

// Prueba con id existente (2)
getUserById(2, (error, user) => {
  if (error) {
    log("Error: " + error);
  } else {
    log("Usuario encontrado: " + JSON.stringify(user));
  }
});

// Prueba con id inexistente (99) para ver el caso de error
getUserById(99, (error, user) => {
  if (error) {
    log("Error: " + error);
  } else {
    log("Usuario encontrado: " + JSON.stringify(user));
  }
});

// ============================================================
// Actividad 2: Promesas
// ============================================================
// getUserByIdPromise(id): devuelve una Promesa que se resuelve o rechaza.
function getUserByIdPromise(id) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const user = users.find((u) => u.id === id);
      if (user) {
        resolve(user);
      } else {
        reject("Usuario no encontrado");
      }
    }, 1500);
  });
}

// Prueba con id existente (3)
getUserByIdPromise(3)
  .then((user) => log("Usuario encontrado: " + JSON.stringify(user)))
  .catch((error) => log("Error: " + error));

// Prueba con id inexistente (99)
getUserByIdPromise(99)
  .then((user) => log("Usuario encontrado: " + JSON.stringify(user)))
  .catch((error) => log("Error: " + error));

// ============================================================
// Actividad 3: Async/Await
// ============================================================
// fetchUser(id): función async que usa await sobre la promesa y try/catch.
async function fetchUser(id) {
  try {
    const user = await getUserByIdPromise(id);
    log("Usuario encontrado: " + JSON.stringify(user));
  } catch (error) {
    log("Error: " + error);
  }
}

// Ejecución: debería mostrar el usuario con id 1
fetchUser(1);
fetchUser(100); // caso de error

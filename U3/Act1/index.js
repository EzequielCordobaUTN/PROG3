// 📝 Trabajo Práctico: Algoritmia con Arrays y Strings

// 1️⃣ Crear un array de palabras
let palabras = [];
for (let i = 0; i < 5; i++) {
    let palabra = prompt(`Ingresa la palabra ${i + 1}:`);
    palabras.push(palabra);
}
console.log("Array inicial:", palabras);

// 2️⃣ Manipular el array
palabras.unshift(prompt("Ingresa una palabra para el inicio:")); // agregar al inicio
palabras.push(prompt("Ingresa una palabra para el final:"));     // agregar al final
let eliminada = palabras.splice(1, 1) // eliminar la segunda palabra
console.log("Elemento eliminado:", eliminada);
console.log("Array después de eliminar:", palabras);

// 3️⃣ Analizar las palabras
// Longitud de cada palabra
palabras.forEach(p => console.log(`"${p}" tiene ${p.length} caracteres`));

// Palabra más larga
let masLarga = palabras.reduce((a, b) => a.length >= b.length ? a : b);
console.log("La palabra más larga es:", masLarga);

// Verificar si alguna contiene la letra "a"
let contieneA = palabras.some(p => p.includes("a"));
console.log("¿Alguna palabra contiene 'a'?:", contieneA);

// 4️⃣ Juego de inversión de palabras
let invertidas = palabras.map(p => p.split("").reverse().join(""));
console.log("Array invertido:", invertidas);
alert("Palabras invertidas: " + invertidas.join(", "));

// 5️⃣ Palíndromo
let comprobar = prompt("¿Quieres comprobar palíndromos? (sí/no)");
if (comprobar.toLowerCase() === "sí") {
    palabras.forEach(p => {
        let invertida = p.split("").reverse().join("");
        if (p === invertida) {
            console.log(`"${p}" es un palíndromo`);
        }
    });
}

// 6️⃣ Bonus (opcional)
// Contar cuántas palabras tienen más de 4 caracteres
let masDeCuatro = palabras.filter(p => p.length > 4).length;
console.log("Palabras con más de 4 caracteres:", masDeCuatro);

// Crear un string con todas las palabras unidas por "-"
let unidas = palabras.join("-");
console.log("Palabras unidas con '-':", unidas);

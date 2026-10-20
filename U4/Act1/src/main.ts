// Interfaces que representan la estructura de datos de la API de The Simpsons
export interface SimpsonCharacter {
  id: number;
  age: number;
  birthdate: string;
  gender: string;
  name: string;
  occupation: string;
  portrait_path: string;
  phrases: string[];
  status: string;
}

export interface IResponseApi {
  count: number;
  next: string | null;
  prev: string | null;
  pages: number;
  results: SimpsonCharacter[];
}

// Constantes
const API_BASE_URL = "https://thesimpsonsapi.com/api/characters";
const IMAGE_BASE_URL = "https://cdn.thesimpsonsapi.com/500";

// Elementos del DOM con tipado correcto
const loadButton = document.getElementById("loadButton") as HTMLButtonElement;
const loading = document.getElementById("loading") as HTMLDivElement;
const errorBox = document.getElementById("error") as HTMLDivElement;
const charactersContainer = document.getElementById("characters") as HTMLDivElement;

// Muestra el indicador de carga y oculta mensajes de error
function showLoading(): void {
  loading.classList.remove("hidden");
  errorBox.classList.add("hidden");
  errorBox.textContent = "";
}

// Oculta el indicador de carga
function hideLoading(): void {
  loading.classList.add("hidden");
}

// Muestra un mensaje de error y lo oculta automáticamente luego de 5 segundos
function showError(message: string): void {
  errorBox.textContent = message;
  errorBox.classList.remove("hidden");
  window.setTimeout(() => {
    errorBox.classList.add("hidden");
  }, 5000);
}

// Crea la tarjeta HTML de un personaje
function createCharacterCard(character: SimpsonCharacter): HTMLElement {
  const card = document.createElement("div");
  card.className = "character-card";

  const image = document.createElement("img");
  image.src = `${IMAGE_BASE_URL}${character.portrait_path}`;
  image.alt = character.name;

  const title = document.createElement("h3");
  title.textContent = character.name;

  const phrase = document.createElement("p");
  phrase.className = "phrase";
  if (character.phrases && character.phrases.length > 0) {
    phrase.textContent = `"${character.phrases[0]}"`;
  } else {
    phrase.textContent = "Sin frases célebres.";
  }

  const details = document.createElement("p");
  details.className = "details";
  details.textContent = `${character.occupation} - ${character.status}`;

  card.append(image, title, phrase, details);
  return card;
}

// Renderiza la lista de personajes, reemplazando los existentes
const renderCharacters = (characters: SimpsonCharacter[]): void => {
  charactersContainer.innerHTML = "";
  characters.forEach((character) => {
    const card = createCharacterCard(character);
    charactersContainer.appendChild(card);
  });
};

// Obtiene los personajes desde la API
const fetchCharacters = async (): Promise<void> => {
  showLoading();
  try {
    const response = await fetch(API_BASE_URL);

    if (!response.ok) {
      throw new Error(`Error HTTP: ${response.status} ${response.statusText}`);
    }

    const data: IResponseApi = await response.json();

    if (!data || !Array.isArray(data.results)) {
      throw new Error("La respuesta de la API no tiene el formato esperado.");
    }

    renderCharacters(data.results);
  } catch (error) {
    console.error("Error al cargar personajes:", error);
    const message = error instanceof Error ? error.message : "Ocurrió un error desconocido.";
    showError(`No se pudieron cargar los personajes. ${message}`);
  } finally {
    hideLoading();
  }
};

// Event listeners
loadButton.addEventListener("click", () => {
  fetchCharacters();
});

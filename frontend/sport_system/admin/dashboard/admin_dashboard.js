
import { loadSidebar, setupHeader } from "./assembly.js";

let content = document.querySelector(".contents .content");
setupHeader();
loadSidebar(content);


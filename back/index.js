const express = require("express");
const bodyParser = require("body-parser");

const app = express();

// Middleware para analizar el cuerpo de las solicitudes JSON
app.use(bodyParser.json());

// Array de objetos para simular una base de datos de "posts"
let posts = [];

// Ruta POST que simula la creación de un nuevo "post"
app.post("/posts", (req, res) => {
  const { nombre, apellido } = req.body;

  if (!nombre || !apellido) {
    return res
      .status(400)
      .json({ error: "Faltan datos: nombre y apellido son requeridos" });
  }

  // Crear un nuevo "post" simulado
  const newPost = {
    id: posts.length + 1,
    nombre,
    apellido,
  };

  posts.push(newPost);

  // Enviar la respuesta con el nuevo "post"
  return res.status(201).json(newPost);
});

// Iniciar el servidor en el puerto 3000
app.listen(3000, () => {
  console.log("Servidor corriendo en http://localhost:3000");
});

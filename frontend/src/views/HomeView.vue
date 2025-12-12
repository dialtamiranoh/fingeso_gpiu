<template>
  <div class="home">
    <h1>GPIU - Gestión de Puntos de Información Universitaria</h1>
    <p>Universidad de Santiago de Chile</p>
    
    <div class="features">
      <div class="feature-card">
        <h2>Consultar Horarios</h2>
        <p>Consulta horarios de clases para alumnos y profesores</p>
        <router-link to="/horarios">
          <button>Ir a Horarios</button>
        </router-link>
      </div>
      
      <div class="feature-card">
        <h2>Puntos de Información</h2>
        <p>Encuentra puntos de información en el campus</p>
        <router-link to="/piu">
          <button>Ver PIUs</button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomeView'
};
</script>

<style scoped>
.home {
  text-align: center;
  padding: 40px 20px;
  max-width: 1200px;
  margin: 0 auto;
}

h1 {
  color: #42b983;
  margin-bottom: 10px;
}

.features {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 30px;
  margin-top: 50px;
}

.feature-card {
  border: 2px solid #42b983;
  border-radius: 8px;
  padding: 30px;
  background-color: #f9f9f9;
}

.feature-card h2 {
  color: #42b983;
  margin-bottom: 15px;
}

button {
  margin-top: 15px;
  padding: 10px 20px;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #359268;
}
</style>
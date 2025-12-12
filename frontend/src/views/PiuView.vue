<template>
  <div class="piu-container">
    <h1>Puntos de Información Universitaria</h1>
    
    <div class="actions">
      <button @click="cargarPius">Mostrar Todos los PIUs</button>
      <button @click="buscarCercano">Buscar PIU Cercano</button>
    </div>
    
    <div v-if="loading">Cargando...</div>
    
    <div v-if="error" class="error">{{ error }}</div>
    
    <div v-if="pius.length > 0" class="pius-lista">
      <h2>PIUs Disponibles</h2>
      <div class="piu-grid">
        <div v-for="piu in pius" :key="piu.idPiu" class="piu-card">
          <h3>{{ piu.nombre }}</h3>
          <p><strong>Descripción:</strong> {{ piu.descripcion }}</p>
          <p><strong>Ubicación:</strong> {{ piu.tipoUbicacion }}</p>
          <p v-if="piu.nombreEdificio"><strong>Edificio:</strong> {{ piu.nombreEdificio }}</p>
          <p v-if="piu.piso"><strong>Piso:</strong> {{ piu.piso }}</p>
          <p><strong>Estado:</strong> {{ piu.estado }}</p>
          <p><strong>Coordenadas:</strong> {{ piu.latitud }}, {{ piu.longitud }}</p>
        </div>
      </div>
    </div>
    
    <div v-if="piuCercano" class="piu-cercano">
      <h2>PIU Más Cercano</h2>
      <div class="piu-card highlight">
        <h3>{{ piuCercano.nombre }}</h3>
        <p><strong>Descripción:</strong> {{ piuCercano.descripcion }}</p>
        <p><strong>Ubicación:</strong> {{ piuCercano.tipoUbicacion }}</p>
        <p v-if="piuCercano.nombreEdificio"><strong>Edificio:</strong> {{ piuCercano.nombreEdificio }}</p>
        <p><strong>Coordenadas:</strong> {{ piuCercano.latitud }}, {{ piuCercano.longitud }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import api from '../services/api';

export default {
  name: 'PiuView',
  data() {
    return {
      pius: [],
      piuCercano: null,
      loading: false,
      error: null
    };
  },
  methods: {
    async cargarPius() {
      this.loading = true;
      this.error = null;
      this.piuCercano = null;
      
      try {
        const response = await api.getPiusActivos();
        this.pius = response.data;
      } catch (err) {
        this.error = 'Error al cargar PIUs: ' + err.message;
      } finally {
        this.loading = false;
      }
    },
    
    async buscarCercano() {
      this.loading = true;
      this.error = null;
      this.pius = [];
      
      // Coordenadas USACH por defecto
      const lat = -33.4489;
      const lon = -70.6693;
      
      try {
        const response = await api.getPiuCercano(lat, lon);
        this.piuCercano = response.data;
      } catch (err) {
        this.error = 'Error al buscar PIU cercano: ' + err.message;
      } finally {
        this.loading = false;
      }
    }
  },
  mounted() {
    this.cargarPius();
  }
};
</script>

<style scoped>
.piu-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.actions {
  margin: 20px 0;
}

button {
  padding: 10px 20px;
  margin-right: 10px;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #359268;
}

.error {
  color: red;
  margin: 10px 0;
}

.piu-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
  margin-top: 20px;
}

.piu-card {
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 20px;
  background-color: #f9f9f9;
}

.piu-card h3 {
  margin-top: 0;
  color: #42b983;
}

.piu-card p {
  margin: 8px 0;
}

.piu-cercano {
  margin-top: 30px;
}

.highlight {
  border: 2px solid #42b983;
  background-color: #e8f5e9;
}
</style>
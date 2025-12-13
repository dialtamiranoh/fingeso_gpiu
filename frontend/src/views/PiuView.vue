<template>
  <div class="piu-container">
    <h1>Puntos de Información Universitaria</h1>
    <p class="subtitle">Universidad de Santiago de Chile</p>

    <div v-if="loading" class="loading">Cargando...</div>
    <div v-if="error" class="error">{{ error }}</div>

    <!-- Mapa -->
    <div v-if="pius.length > 0" class="mapa-section">
      <h2>Mapa de PIUs</h2>
      <div class="map-wrapper">
        <button @click="centrarVista" class="btn-centrar">Centrar Vista</button>
        <MapaPiu :pius="pius" ref="mapaRef" />
      </div>
    </div>

    <!-- Lista de PIUs -->
    <div v-if="pius.length > 0" class="pius-section">
      <h2>PIUs Disponibles</h2>
      <div class="piu-grid">
        <div v-for="piu in pius" :key="piu.idPiu" class="piu-card">
          <h3>{{ piu.nombre }}</h3>
          <p><strong>Descripción:</strong> {{ piu.descripcion || 'N/A' }}</p>
          <p><strong>Ubicación:</strong> {{ piu.tipoUbicacion }}</p>
          <p v-if="piu.nombreEdificio"><strong>Edificio:</strong> {{ piu.nombreEdificio }}</p>
          <p v-if="piu.piso"><strong>Piso:</strong> {{ piu.piso }}</p>
          <p><strong>Estado:</strong> <span class="estado-badge">{{ piu.estado }}</span></p>
          <p class="coordenadas"><strong>Coordenadas:</strong> {{ piu.latitud }}, {{ piu.longitud }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import api from '../services/api';
import MapaPiu from '../components/MapaPiu.vue';

export default {
  name: 'PiuView',
  components: {
    MapaPiu
  },
  data() {
    return {
      pius: [],
      loading: false,
      error: null
    };
  },
  methods: {
    async cargarPius() {
      this.loading = true;
      this.error = null;

      try {
        const response = await api.getPiusActivos();
        this.pius = response.data;
      } catch (err) {
        this.error = 'Error al cargar PIUs: ' + err.message;
      } finally {
        this.loading = false;
      }
    },

    centrarVista() {
      // Llama al método del componente hijo MapaPiu para centrar la vista
      if (this.$refs.mapaRef) {
        this.$refs.mapaRef.centrarEnPius();
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
  padding: 40px 20px;
}

h1 {
  text-align: center;
  color: #42b983;
  margin-bottom: 10px;
  font-size: 2.5em;
}

.subtitle {
  text-align: center;
  color: #666;
  margin-bottom: 40px;
  font-size: 1.1em;
}

.loading {
  text-align: center;
  padding: 20px;
  color: #42b983;
  font-size: 1.1em;
}

.error {
  text-align: center;
  color: #e74c3c;
  margin: 20px 0;
  padding: 15px;
  background-color: #fde8e8;
  border-radius: 4px;
}

.mapa-section {
  margin: 50px 0;
}

.mapa-section h2 {
  color: #42b983;
  margin-bottom: 20px;
  font-size: 1.8em;
}

.map-wrapper {
  position: relative;
  border: 2px solid #42b983;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}

.btn-centrar {
  position: absolute;
  top: 10px;
  right: 10px;
  z-index: 1000;
  padding: 10px 16px;
  background-color: white;
  color: #42b983;
  border: 2px solid #42b983;
  border-radius: 4px;
  cursor: pointer;
  font-size: 0.9em;
  font-weight: bold;
  transition: all 0.3s;
  box-shadow: 0 2px 4px rgba(0,0,0,0.2);
}

.btn-centrar:hover {
  background-color: #42b983;
  color: white;
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0,0,0,0.3);
}

.pius-section {
  margin-top: 50px;
}

.pius-section h2 {
  color: #42b983;
  margin-bottom: 30px;
  font-size: 1.8em;
}

.piu-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
  gap: 25px;
}

.piu-card {
  border: 2px solid #42b983;
  border-radius: 8px;
  padding: 25px;
  background-color: #f9f9f9;
  transition: transform 0.3s, box-shadow 0.3s;
}

.piu-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 5px 15px rgba(66, 185, 131, 0.3);
}

.piu-card h3 {
  margin-top: 0;
  margin-bottom: 15px;
  color: #42b983;
  font-size: 1.4em;
}

.piu-card p {
  margin: 10px 0;
  color: #555;
  line-height: 1.6;
}

.estado-badge {
  display: inline-block;
  padding: 3px 10px;
  background-color: #42b983;
  color: white;
  border-radius: 12px;
  font-size: 0.9em;
}

.coordenadas {
  font-size: 0.9em;
  color: #888;
  margin-top: 15px;
  padding-top: 10px;
  border-top: 1px solid #ddd;
}
</style>
<template>
  <div class="info-container">
    <h1>Información General</h1>
    <p class="subtitle">Universidad de Santiago de Chile</p>

    <div v-if="loading" class="loading">Cargando...</div>
    <div v-if="error" class="error">{{ error }}</div>

    <div v-if="secciones.length > 0" class="secciones-grid">
      <a
        v-for="seccion in secciones"
        :key="seccion.idInformacion"
        :href="seccion.url"
        target="_blank"
        class="seccion-card"
      >
        <h3>{{ seccion.titulo }}</h3>
        <p>{{ seccion.descripcion }}</p>
        <span class="categoria-badge">{{ seccion.categoria }}</span>
        <div class="link-icon">→</div>
      </a>
    </div>
  </div>
</template>

<script>
import api from '../services/api';

export default {
  name: 'InformacionGeneralView',
  data() {
    return {
      secciones: [],
      loading: false,
      error: null
    };
  },
  methods: {
    async cargarSecciones() {
      this.loading = true;
      this.error = null;

      try {
        const response = await api.getInformacionGeneral();
        this.secciones = response.data;
      } catch (err) {
        this.error = 'Error al cargar información: ' + err.message;
      } finally {
        this.loading = false;
      }
    }
  },
  mounted() {
    this.cargarSecciones();
  }
};
</script>

<style scoped>
.info-container {
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
  margin-bottom: 50px;
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

.secciones-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
  gap: 30px;
  margin-top: 40px;
}

.seccion-card {
  position: relative;
  border: 2px solid #42b983;
  border-radius: 8px;
  padding: 30px;
  background-color: #f9f9f9;
  text-decoration: none;
  color: inherit;
  transition: all 0.3s;
  cursor: pointer;
}

.seccion-card:hover {
  transform: translateY(-8px);
  box-shadow: 0 8px 20px rgba(66, 185, 131, 0.4);
  background-color: #fff;
}

.seccion-card h3 {
  margin: 0 0 15px 0;
  color: #42b983;
  font-size: 1.5em;
}

.seccion-card p {
  margin: 15px 0;
  color: #555;
  line-height: 1.6;
}

.categoria-badge {
  display: inline-block;
  padding: 4px 12px;
  background-color: #42b983;
  color: white;
  border-radius: 12px;
  font-size: 0.85em;
  margin-top: 10px;
}

.link-icon {
  position: absolute;
  top: 30px;
  right: 30px;
  font-size: 2em;
  color: #42b983;
  transition: transform 0.3s;
}

.seccion-card:hover .link-icon {
  transform: translateX(5px);
}
</style>
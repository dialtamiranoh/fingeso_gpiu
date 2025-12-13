<template>
  <div class="mapa-container">
    <div id="mapa" ref="mapaRef"></div>
  </div>
</template>

<script>
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';

// Fix para iconos de Leaflet
delete L.Icon.Default.prototype._getIconUrl;
L.Icon.Default.mergeOptions({
  iconRetinaUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.9.4/images/marker-icon-2x.png',
  iconUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.9.4/images/marker-icon.png',
  shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.9.4/images/marker-shadow.png',
});

export default {
  name: 'MapaPiu',
  props: {
    pius: {
      type: Array,
      required: true
    }
  },
  data() {
    return {
      mapa: null,
      marcadores: []
    };
  },
  mounted() {
    this.inicializarMapa();
  },
  watch: {
    pius: {
      handler() {
        this.actualizarMarcadores();
      },
      deep: true
    }
  },
  methods: {
    inicializarMapa() {
      // Crear mapa centrado en USACH (coordenadas temporales, se ajustarán automáticamente)
      this.mapa = L.map('mapa').setView([-33.4500, -70.6850], 15);

      // Agregar capa de OpenStreetMap
      L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
        maxZoom: 19
      }).addTo(this.mapa);

      // Si ya hay PIUs, agregar marcadores inmediatamente
      if (this.pius && this.pius.length > 0) {
        this.actualizarMarcadores();
      }
    },

    actualizarMarcadores() {
      if (!this.mapa) return;

      // Limpiar marcadores anteriores
      this.marcadores.forEach(marcador => {
        this.mapa.removeLayer(marcador);
      });
      this.marcadores = [];

      // Si no hay PIUs, no hacer nada
      if (!this.pius || this.pius.length === 0) return;

      // Array para almacenar las coordenadas de todos los PIUs
      const bounds = [];

      // Agregar marcador para cada PIU
      this.pius.forEach(piu => {
        const marcador = L.marker([piu.latitud, piu.longitud])
          .bindPopup(`
            <div class="popup-piu">
              <h3>${piu.nombre}</h3>
              <p><strong>Descripción:</strong> ${piu.descripcion}</p>
              <p><strong>Edificio:</strong> ${piu.nombreEdificio || 'N/A'}</p>
              <p><strong>Tipo:</strong> ${piu.tipoUbicacion}</p>
              <p><strong>Estado:</strong> <span class="badge-${piu.estado.toLowerCase()}">${piu.estado}</span></p>
            </div>
          `)
          .addTo(this.mapa);

        this.marcadores.push(marcador);
        bounds.push([piu.latitud, piu.longitud]);
      });

      // Ajustar el mapa para mostrar todos los PIUs
      if (bounds.length > 0) {
        this.mapa.fitBounds(bounds, {
          padding: [50, 50], // Padding de 50px en todos los lados
          maxZoom: 16 // Zoom máximo para no acercarse demasiado si hay un solo PIU
        });
      }
    },

    centrarEnPius() {
      // Método público para centrar el mapa en todos los PIUs
      if (!this.mapa || !this.pius || this.pius.length === 0) return;

      const bounds = this.pius.map(piu => [piu.latitud, piu.longitud]);
      this.mapa.fitBounds(bounds, {
        padding: [50, 50],
        maxZoom: 16
      });
    }
  },
  beforeUnmount() {
    // Limpiar el mapa al destruir el componente
    if (this.mapa) {
      this.mapa.remove();
      this.mapa = null;
    }
  }
};
</script>

<style scoped>
.mapa-container {
  width: 100%;
  height: 500px;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  margin-bottom: 2rem;
}

#mapa {
  width: 100%;
  height: 100%;
}

/* Estilos para el popup */
:deep(.popup-piu) {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

:deep(.popup-piu h3) {
  color: #42b983;
  margin: 0 0 10px 0;
  font-size: 16px;
}

:deep(.popup-piu p) {
  margin: 5px 0;
  font-size: 14px;
}

:deep(.popup-piu strong) {
  color: #333;
}

:deep(.badge-activo) {
  background-color: #42b983;
  color: white;
  padding: 2px 8px;
  border-radius: 12px;
  font-size: 12px;
}

:deep(.badge-inactivo) {
  background-color: #e74c3c;
  color: white;
  padding: 2px 8px;
  border-radius: 12px;
  font-size: 12px;
}

:deep(.badge-mantenimiento) {
  background-color: #f39c12;
  color: white;
  padding: 2px 8px;
  border-radius: 12px;
  font-size: 12px;
}
</style>
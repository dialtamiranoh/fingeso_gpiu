<template>
  <div>
    <div ref="mapDiv" style="width: 100%; height: 600px;"></div>
  </div>
</template>

<script>
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';

// Fix para iconos de Leaflet
import markerIcon from 'leaflet/dist/images/marker-icon.png';
import markerIcon2x from 'leaflet/dist/images/marker-icon-2x.png';
import markerShadow from 'leaflet/dist/images/marker-shadow.png';

delete L.Icon.Default.prototype._getIconUrl;
L.Icon.Default.mergeOptions({
  iconUrl: markerIcon,
  iconRetinaUrl: markerIcon2x,
  shadowUrl: markerShadow,
});

export default {
  name: 'MapaPiu',
  props: {
    pius: {
      type: Array,
      required: true,
      default: () => []
    }
  },
  data() {
    return {
      map: null,
      markers: []
    };
  },
  mounted() {
    this.initMap();
  },
  methods: {
    initMap() {
      // Crear mapa centrado en USACH
      this.map = L.map(this.$refs.mapDiv).setView([-33.4489, -70.6693], 16);

      // Agregar capa de OpenStreetMap
      L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© OpenStreetMap contributors'
      }).addTo(this.map);

      if (this.pius.length > 0) {
        this.addMarkers();
      }
    },

    addMarkers() {
      if (!this.map) return;

      // Limpiar marcadores anteriores
      this.markers.forEach(marker => this.map.removeLayer(marker));
      this.markers = [];

      // Agregar nuevos marcadores
      this.pius.forEach(piu => {
        const marker = L.marker([piu.latitud, piu.longitud]).addTo(this.map);

        marker.bindPopup(`
          <div style="padding: 10px;">
            <h3>${piu.nombre}</h3>
            <p><strong>Descripción:</strong> ${piu.descripcion || 'N/A'}</p>
            <p><strong>Ubicación:</strong> ${piu.tipoUbicacion}</p>
            <p><strong>Estado:</strong> ${piu.estado}</p>
          </div>
        `);

        this.markers.push(marker);
      });
    }
  },
  watch: {
    pius: {
      handler() {
        if (this.map && this.pius.length > 0) {
          this.addMarkers();
        }
      },
      deep: true
    }
  }
};
</script>
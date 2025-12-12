<template>
  <div class="horarios-container">
    <h1>Consultar Horarios</h1>
    
    <div class="form-group">
      <label>Tipo de Usuario:</label>
      <select v-model="tipoUsuario">
        <option value="alumno">Alumno</option>
        <option value="profesor">Profesor</option>
      </select>
    </div>
    
    <div class="form-group">
      <label>ID Usuario:</label>
      <input v-model="idUsuario" type="number" placeholder="Ingrese ID">
      <button @click="buscarHorarios">Buscar</button>
    </div>
    
    <div v-if="loading">Cargando...</div>
    
    <div v-if="error" class="error">{{ error }}</div>
    
    <div v-if="horarios.length > 0" class="horarios-resultado">
      <h2>Horarios de {{ tipoUsuario }}</h2>
      <table>
        <thead>
          <tr>
            <th>Curso</th>
            <th>Sala</th>
            <th>Día</th>
            <th>Hora Inicio</th>
            <th>Hora Fin</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="horario in horarios" :key="horario.idHorario">
            <td>{{ horario.curso.nombre }}</td>
            <td>{{ horario.sala.codigo }}</td>
            <td>{{ horario.dia }}</td>
            <td>{{ horario.horaInicio }}</td>
            <td>{{ horario.horaFin }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import api from '../services/api';

export default {
  name: 'HorariosView',
  data() {
    return {
      tipoUsuario: 'alumno',
      idUsuario: '',
      horarios: [],
      loading: false,
      error: null
    };
  },
  methods: {
    async buscarHorarios() {
      if (!this.idUsuario) {
        this.error = 'Ingrese un ID';
        return;
      }
      
      this.loading = true;
      this.error = null;
      this.horarios = [];
      
      try {
        let response;
        if (this.tipoUsuario === 'alumno') {
          response = await api.getHorariosAlumno(this.idUsuario);
        } else {
          response = await api.getHorariosProfesor(this.idUsuario);
        }
        this.horarios = response.data;
      } catch (err) {
        this.error = 'Error al cargar horarios: ' + err.message;
      } finally {
        this.loading = false;
      }
    }
  }
};
</script>

<style scoped>
.horarios-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.form-group select,
.form-group input {
  padding: 8px;
  margin-right: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

button {
  padding: 8px 16px;
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

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th, td {
  border: 1px solid #ddd;
  padding: 12px;
  text-align: left;
}

th {
  background-color: #42b983;
  color: white;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
</style>
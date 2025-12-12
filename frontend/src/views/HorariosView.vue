<template>
  <div class="horarios-container">
    <h1>Consultar Horarios</h1>
    <p class="subtitle">Universidad de Santiago de Chile</p>

    <div class="form-section">
      <div class="form-group">
        <label>Tipo de Usuario:</label>
        <select v-model="tipoUsuario" class="form-control">
          <option value="alumno">Alumno</option>
          <option value="profesor">Profesor</option>
        </select>
      </div>

      <div class="form-group">
        <label>ID Usuario:</label>
        <input v-model="idUsuario" type="number" placeholder="Ingrese ID" class="form-control">
      </div>

      <button @click="buscarHorarios" class="btn-primary">Buscar</button>
    </div>

    <div v-if="loading" class="loading">Cargando...</div>

    <div v-if="error" class="error">{{ error }}</div>

    <div v-if="horarios.length > 0" class="horarios-resultado">
      <h2>Horarios de {{ tipoUsuario === 'alumno' ? 'Alumno' : 'Profesor' }}</h2>
      <div class="table-wrapper">
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
        this.error = 'Por favor ingrese un ID de usuario';
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

        if (this.horarios.length === 0) {
          this.error = 'No se encontraron horarios para este usuario';
        }
      } catch (err) {
        this.error = 'Error al cargar horarios. Verifique que el ID sea correcto.';
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

.form-section {
  max-width: 600px;
  margin: 0 auto 40px;
  padding: 30px;
  border: 2px solid #42b983;
  border-radius: 8px;
  background-color: #f9f9f9;
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  margin-bottom: 8px;
  font-weight: bold;
  color: #333;
}

.form-control {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 1em;
  box-sizing: border-box;
}

.form-control:focus {
  outline: none;
  border-color: #42b983;
  box-shadow: 0 0 5px rgba(66, 185, 131, 0.3);
}

.btn-primary {
  width: 100%;
  padding: 12px;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1.1em;
  transition: background-color 0.3s;
}

.btn-primary:hover {
  background-color: #359268;
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
  margin: 20px auto;
  padding: 15px;
  background-color: #fde8e8;
  border-radius: 4px;
  max-width: 600px;
}

.horarios-resultado {
  margin-top: 40px;
}

.horarios-resultado h2 {
  color: #42b983;
  margin-bottom: 20px;
  font-size: 1.8em;
}

.table-wrapper {
  overflow-x: auto;
  border: 2px solid #42b983;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}

table {
  width: 100%;
  border-collapse: collapse;
  background-color: white;
}

th, td {
  padding: 15px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

th {
  background-color: #42b983;
  color: white;
  font-weight: bold;
}

tr:hover {
  background-color: #f5f5f5;
}

tr:last-child td {
  border-bottom: none;
}
</style>
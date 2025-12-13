import axios from 'axios';

const API_URL = 'http://localhost:8081/api';

export default {
  // Horarios
  getHorariosAlumno(idAlumno) {
    return axios.get(`${API_URL}/horarios/alumno/${idAlumno}`);
  },

  getHorariosProfesor(idProfesor) {
    return axios.get(`${API_URL}/horarios/profesor/${idProfesor}`);
  },

  // PIUs
  getPiusActivos() {
    return axios.get(`${API_URL}/piu`);
  },

  // Información General
  getInformacionGeneral() {
    return axios.get(`${API_URL}/informacion-general`);
  }
};
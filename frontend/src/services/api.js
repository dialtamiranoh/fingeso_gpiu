import axios from 'axios';

const API_URL = 'http://localhost:8081/api';

export default {
  getHorariosAlumno(idAlumno) {
    return axios.get(`${API_URL}/horarios/alumno/${idAlumno}`);
  },
  
  getHorariosProfesor(idProfesor) {
    return axios.get(`${API_URL}/horarios/profesor/${idProfesor}`);
  },
  
  getPiusActivos() {
    return axios.get(`${API_URL}/piu`);
  },
  
  getPiuCercano(lat, lon) {
    return axios.get(`${API_URL}/piu/cercano`, {
      params: { lat, lon }
    });
  }
};
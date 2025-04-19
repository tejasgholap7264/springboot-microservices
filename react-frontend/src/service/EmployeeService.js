// src/service/EmployeeService.js
import axios from 'axios';

const EMPLOYEE_SERVICE_BASE_URL = "http://localhost:9191/api/employees/";
const EMPLOYEE_ID = 10;

// ✅ Functional way of exporting API call
const getEmployee = () => {
  return axios.get(`${EMPLOYEE_SERVICE_BASE_URL}${EMPLOYEE_ID}`);
};

export default getEmployee;

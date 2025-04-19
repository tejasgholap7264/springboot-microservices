// src/service/EmployeeService.js
import axios from 'axios';

const EMPLOYEE_SERVICE_BASE_URL = "http://localhost:9191/api/employees/";
<<<<<<< HEAD
const EMPLOYEE_ID = 1;
=======
const EMPLOYEE_ID = 10;
>>>>>>> 8c0161bb44c77f3bfd6b27dbe11f34b00de1fd04

// ✅ Functional way of exporting API call
const getEmployee = () => {
  return axios.get(`${EMPLOYEE_SERVICE_BASE_URL}${EMPLOYEE_ID}`);
};

export default getEmployee;

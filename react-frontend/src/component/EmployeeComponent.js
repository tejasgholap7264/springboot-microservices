import React, { useEffect, useState } from 'react';
import getEmployee from '../service/EmployeeService';

const EmployeeComponent = () => {
    const [employee, setEmployee] = useState({});
    const [department, setDepartment] = useState({});
    const [organization, setOrganization] = useState({});

    useEffect(() => {
        getEmployee().then((response) => {
            setEmployee(response.data.employee);
            setDepartment(response.data.department);
            setOrganization(response.data.organization);

            console.log("Employee:", response.data.employee);
            console.log("Department:", response.data.department);
            console.log("Organization:", response.data.organization);
        });
    }, []);

    return (
        <div><br /><br />
            <div className='card col-md-6 offset-md-3'>
                <h3 className='text-center card-header'> View Employee Details</h3>
                <div className='card-body'>
                    <div className='row'>
                        <p><strong>Employee First Name: </strong> {employee.firstName}</p>
                    </div>
                    <div className='row'>
                        <p><strong>Employee Last Name: </strong> {employee.lastName}</p>
                    </div>
                    <div className='row'>
                        <p><strong>Employee Email: </strong> {employee.email}</p>
                    </div>
                </div>
                <h3 className='text-center card-header'> View Department Details</h3>
                <div className='card-body'>
                    <div className='row'>
                        <p><strong>Department Name: </strong> {department.departmentName}</p>
                    </div>
                    <div className='row'>
                        <p><strong>Department Description: </strong> {department.departmentDescription}</p>
                    </div>
                    <div className='row'>
                        <p><strong>Department Code: </strong> {department.departmentCode}</p>
                    </div>
                </div>
                <h3 className='text-center card-header'> View Organization Details</h3>
                <div className='card-body'>
                    <div className='row'>
                        <p><strong>Organization Name: </strong> {organization.organizationName}</p>
                    </div>
                    <div className='row'>
                        <p><strong>Organization Description: </strong> {organization.organizationDescription}</p>
                    </div>
                    <div className='row'>
                        <p><strong>Organization Code: </strong> {organization.organizationCode}</p>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default EmployeeComponent;

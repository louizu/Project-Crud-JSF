package com.example.jsf_bdd_bean_form_devoir.service;

import com.example.jsf_bdd_bean_form_devoir.dao.EmployeeDao;
import com.example.jsf_bdd_bean_form_devoir.dao.imp.EmployeeDaoImp;
import com.example.jsf_bdd_bean_form_devoir.model.Employee;

import java.util.List;

public class EmployeeService {

//    injection de something -> employeeDeo ? imp ?
    EmployeeDao employeeDao;

    public EmployeeService(){
        employeeDao = new EmployeeDaoImp();
    }

    public int addService(Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    public List<Employee> selectAllService(){
        return employeeDao.selectAllEmployee();
    }

    public boolean deleteService(int id){
        return employeeDao.deleteEmployee(id);
    }

    public int editService(Employee employee){
        return employeeDao.editEmployee(employee);
    }

}

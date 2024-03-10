package com.example.jsf_bdd_bean_form_devoir.dao;

import com.example.jsf_bdd_bean_form_devoir.model.Employee;

import java.util.List;

public interface EmployeeDao {
    public int addEmployee(Employee employee);
    public boolean deleteEmployee(int id);
    public int editEmployee(Employee employee);
    public boolean findEmail(String email);
    public List<Employee> selectAllEmployee();
}

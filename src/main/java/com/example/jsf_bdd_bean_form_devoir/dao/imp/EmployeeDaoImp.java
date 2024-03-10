package com.example.jsf_bdd_bean_form_devoir.dao.imp;

import com.example.jsf_bdd_bean_form_devoir.bdd.ConnectDb;
import com.example.jsf_bdd_bean_form_devoir.dao.EmployeeDao;
import com.example.jsf_bdd_bean_form_devoir.model.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImp implements EmployeeDao {

    @Override
    public int addEmployee(Employee employee) {
//        returns >0 if added successfully
//        0 if something went wrong
//        -1 if email already exists
        ConnectDb connexion = new ConnectDb();
        int rowsAffected = 0;
        try {
            boolean findEmail = findEmail(employee.getEmail());

            if (findEmail) {
//                L'email fourni est déjà utilisé
                rowsAffected = -1;
            } else {
                String query = "INSERT INTO employee (nom, prenom, email, sal) VALUES (?, ?, ?, ?);";
                PreparedStatement preparedStatement = connexion.prepareStatement(query);
                preparedStatement.setString(1, employee.getNom());
                preparedStatement.setString(2, employee.getPrenom());
                preparedStatement.setString(3, employee.getEmail());
                preparedStatement.setInt(4, employee.getSal());

                rowsAffected = preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connexion.closeDatabase();
        }
        return rowsAffected;
    }

    @Override
    public boolean deleteEmployee(int id) {
//        returns true if deleted successfully
        int resultSet = 0;

        ConnectDb connexion = new ConnectDb();

        try {
            String query = "DELETE FROM employee WHERE id=?;";
            PreparedStatement preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connexion.closeDatabase();
        }

        return resultSet > 0;
    }

    @Override
    public int editEmployee(Employee employee) {
//        returns -1 if new email already in database
//        returns 1 if the update was successful
//        returns 0 if the update failed
        int rowsAffected = 0;
        ConnectDb connexion = new ConnectDb();
        try {
            String test_for_email = "SELECT * FROM employee WHERE id=? AND email=?";
            PreparedStatement preparedStatementTestChange = connexion.prepareStatement(test_for_email);
            preparedStatementTestChange.setInt(1, employee.getId());
            preparedStatementTestChange.setString(2, employee.getEmail());
            ResultSet rs = preparedStatementTestChange.executeQuery();
            if (rs.next()){ // if email didnt change, then update database
                try {
                    String query = "UPDATE employee SET nom=?, prenom=?, email=?, sal=? WHERE id=?;";
                    PreparedStatement preparedStatement = connexion.prepareStatement(query);
                    preparedStatement.setString(1, employee.getNom());
                    preparedStatement.setString(2, employee.getPrenom());
                    preparedStatement.setString(3, employee.getEmail());
                    preparedStatement.setInt(4, employee.getSal());
                    preparedStatement.setInt(5, employee.getId());
                    rowsAffected = preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    connexion.closeDatabase();
                }
            } else { // if email did change, then test if it already exists
                try {
                    boolean findEmail = findEmail(employee.getEmail());
                    if (findEmail) {// if new email exists in database return -1
                        rowsAffected = -1;
                    } else {// if new email doesnt exist in database then update employee
                        try{
                            String query = "UPDATE employee SET nom=?, prenom=?, email=?, sal=? WHERE id=?;";
                            PreparedStatement preparedStatement = connexion.prepareStatement(query);
                            preparedStatement.setString(1, employee.getNom());
                            preparedStatement.setString(2, employee.getPrenom());
                            preparedStatement.setString(3, employee.getEmail());
                            preparedStatement.setInt(4, employee.getSal());
                            preparedStatement.setInt(5, employee.getId());
                            rowsAffected = preparedStatement.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            connexion.closeDatabase();
                        }
                    }
                } finally {
                    connexion.closeDatabase();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connexion.closeDatabase();
        }

        return rowsAffected;
    }

    @Override
    public boolean findEmail(String email) {
//        returns true if the email already exists in database
        int isunique = 0;
        ResultSet resultSet = null;

        ConnectDb connexion = new ConnectDb();

        try {
            String query = "SELECT COUNT(*) FROM employee WHERE email LIKE ?;";
            PreparedStatement preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isunique = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connexion.closeDatabase();
        }

        return isunique > 0;
    }

    @Override
    public List<Employee> selectAllEmployee() {

        List<Employee> employees = new ArrayList<>();
        ConnectDb connexion = new ConnectDb();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM employee;";
            stmt = connexion.prepareStatement(query);

            rs = stmt.executeQuery();

            while (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                int salaire = rs.getInt("sal");
                int id = rs.getInt("id");

                Employee employee = new Employee(id, salaire, nom, prenom, email);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connexion.closeDatabase();
        }
        return employees;
    }
}

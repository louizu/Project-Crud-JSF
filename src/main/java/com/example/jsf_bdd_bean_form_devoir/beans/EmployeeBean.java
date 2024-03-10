package com.example.jsf_bdd_bean_form_devoir.beans;

import com.example.jsf_bdd_bean_form_devoir.model.Employee;
import com.example.jsf_bdd_bean_form_devoir.service.EmployeeService;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;

import java.io.Serializable;
import java.util.*;

public class EmployeeBean implements Serializable {
    private static final long serialVersionUID = 2L;
    private String msg_success = "";
    private String msg_error = "";
    private Employee employee = new Employee();
    private Employee newEmployee = new Employee();
    private EmployeeService service = new EmployeeService();
    private boolean showAddForm = false;
    private int editRow;
    private boolean edit = false;

    private int currentPage = 1;
    private int itemsPerPage = 5;
    private int totalPages;
    private List<Employee> paginatedEmployees;
    private List<Employee> allEmployees;

//  Internationalisation  helpers
    private String locale;
    private static Map<String,Object> countries;

    static {

        countries = new LinkedHashMap<String,Object>();
        countries.put("English", Locale.ENGLISH);
        countries.put("French", Locale.FRENCH);
    }

    public EmployeeBean(){
        super();
        this.setMsg_error("");
        this.setMsg_success("");
        this.allEmployees = service.selectAllService();
        updatePaginatedEmployees();
    }
    public EmployeeBean(Employee employee) {
        this.employee = employee;
    }

    public String getMsg_success() {
        return msg_success;
    }

    public void setMsg_success(String msg_success) {
        this.msg_success = msg_success;
    }

    public String getMsg_error() {
        return msg_error;
    }

    public void setMsg_error(String msg_error) {
        this.msg_error = msg_error;
    }

    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public boolean isShowAddForm() {
        return showAddForm;
    }

    public void setShowAddForm(boolean showAddForm) {
        this.showAddForm = showAddForm;
    }

    public Employee getNewEmployee() {
        return newEmployee;
    }

    public void setNewEmployee(Employee newEmployee) {
        this.newEmployee = newEmployee;
    }

    public int getEditRow() {
        return editRow;
    }

    public void setEditRow(int editRow) {
        this.editRow = editRow;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }
    public int getTotalPages() {
        return (int) Math.ceil((double) selectAll().size() / itemsPerPage);
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setPaginatedEmployees(List<Employee> paginatedEmployees) {
        this.paginatedEmployees = paginatedEmployees;
    }

    public List<Employee> getAllEmployees() {
        return allEmployees;
    }

    public void setAllEmployees(List<Employee> allEmployees) {
        this.allEmployees = allEmployees;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Map<String, Object> getCountries() {
        return countries;
    }
    //    ------------------------------Methods------------------------------

    public List<Employee> selectAll(){
        this.setAllEmployees(service.selectAllService());
        return service.selectAllService();
    }

    public void addEmployee(Employee employee) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ResourceBundle labels = ResourceBundle.getBundle("i18n.labels", ctx.getViewRoot().getLocale());
        this.setMsg_error("");
        this.setMsg_success("");
        String msg = "";
        if(employee.getEmail().matches(".*@.*")) {
            int flag = service.addService(employee);
            if (flag > 0) {
                msg = labels.getString("add_success");
                this.setMsg_success(msg);
                selectAll();
                updatePaginatedEmployees();
            } else if (flag == 0) {
                msg = labels.getString("add_fail");
                this.setMsg_error(msg);
            } else if (flag == -1) {
                msg = labels.getString("email_warning");
                this.setMsg_error(msg);
            }
            resetAddForm();
        }else{
            msg = labels.getString("email_format");
            this.setMsg_error(msg);
        }
    }

    public void editEmployee(Employee employee){
        FacesContext ctx = FacesContext.getCurrentInstance();
        ResourceBundle labels = ResourceBundle.getBundle("i18n.labels", ctx.getViewRoot().getLocale());
        this.setMsg_error("");
        this.setMsg_success("");
        String msg = "";
        if(employee.getEmail().matches(".*@.*")) {
            int flag = service.editService(employee);
            if (flag > 0) {
                msg = labels.getString("edit_success");
                this.setMsg_success(msg);
                selectAll();
            } else if (flag == 0) {
                msg = labels.getString("edit_fail");
                this.setMsg_error(msg);
            } else {
                msg = labels.getString("email_warning");
                this.setMsg_error(msg);
            }
            setEdit(false);
            setEditRow(0);
        }else{
            msg = labels.getString("email_format");
            this.setMsg_error(msg);
        }
    }

    public void deleteEmployee(int id){
        this.setMsg_error("");
        this.setMsg_success("");
        String msg = "";
        FacesContext ctx = FacesContext.getCurrentInstance();
        ResourceBundle labels = ResourceBundle.getBundle("i18n.labels", ctx.getViewRoot().getLocale());
        if (service.deleteService(id)){
            msg= labels.getString("delete_success");
            this.setMsg_success(msg);
            selectAll();
        }
        else{
            msg=labels.getString("delete_fail");
            this.setMsg_error(msg);
        }
    }

    public List<Employee> getPaginatedEmployees() {
        int startIndex = (currentPage - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, allEmployees.size());

        return allEmployees.subList(startIndex, endIndex);
    }
    private void updatePaginatedEmployees() {
        int startIndex = (currentPage - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, allEmployees.size());

        setPaginatedEmployees(allEmployees.subList(startIndex, endIndex));
        setTotalPages((int) Math.ceil((double) allEmployees.size() / itemsPerPage));

    }

//    ------------------------------Method Helpers------------------------------

//    add helpers
    public void showAddForm(boolean show) {
        this.setMsg_success("");
        this.setMsg_error("");
        setShowAddForm(show);
        if (show) {
            newEmployee = new Employee(); // Initialize a new Employee for the form
        }
    }

    private void resetAddForm() {
        setShowAddForm(false);
        newEmployee = new Employee();
    }

//    edit helpers

    public void showEditForm(int rowId, Employee employee) {
        this.setMsg_success("");
        this.setMsg_error("");
        setEditRow(rowId);
        setEmployee(employee);
    }

    public boolean isEditRow(int rowId) {
        return (editRow == rowId);
    }

    public void updateNom(String nom){
        employee.setNom(nom);
    }

//    Navigation

    public void previousPage() {
        if (currentPage > 1) {
            setCurrentPage(currentPage-1);
            updatePaginatedEmployees();
        }
    }
    public void nextPage() {
        if (currentPage < totalPages) {
            setCurrentPage(currentPage+1);
            updatePaginatedEmployees();
        }
    }

//    value change event listener for locale
public void localeChanged(ValueChangeEvent e) {
    String newLocaleValue = e.getNewValue().toString();

    for (Map.Entry<String, Object> entry : countries.entrySet()) {

        if(entry.getValue().toString().equals(newLocaleValue)) {
            FacesContext.getCurrentInstance()
                    .getViewRoot().setLocale((Locale)entry.getValue());
        }
    }
}
}

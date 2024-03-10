package com.example.jsf_bdd_bean_form_devoir.bdd;

import jakarta.faces.context.FacesContext;
import jakarta.servlet.ServletException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectDb {
    private PreparedStatement stmt;
    private String req;
    private Connection con;
    FacesContext ctx = FacesContext.getCurrentInstance();
    String driver = ctx.getExternalContext().getInitParameter("mysql.driver");
    String url = ctx.getExternalContext().getInitParameter("url.bdd").toString();
    String user = ctx.getExternalContext().getInitParameter("user.bdd").toString();
    String pwd = ctx.getExternalContext().getInitParameter("pwd.bdd").toString();

    public ConnectDb() {
        loadDatabase();
    }

    private void loadDatabase() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        return con.prepareStatement(query);
    }

    public void closeDatabase(){
        if(con != null){
            try{
                con.close();
            }catch (SQLException e){
                System.out.println("Can't close bd connection");
            }
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.kickback;

import static com.revature.kickback.Main.DB;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author panam
 */
public class Manager extends Worker implements WorkerDAO {
    List<Employee> myWorkers;
            
    @Override
    public void authenticateWorker() throws SQLException {
        Statement stmt;
        String query;
        ResultSet rs;
        
        query = "SELECT * FROM Workers WHERE username='"+getUsername()+"' AND password='"+getPassword()+"'";

        stmt = DB.createStatement();
        rs = stmt.executeQuery(query);
 
        if (rs.next()) {
            setId(rs.getInt("WID"));
            setEmailAddress(rs.getString("EMAILADDRESS"));
            setName(rs.getString("NAME"));
        }
        
        rs.close();
        stmt.close();
        
        getTickets();
    }

    @Override
    public void getTickets() throws SQLException {
        Statement stmt;
        Ticket t;
        String query;
        ResultSet rs;
        
        query = "SELECT * FROM Tickets";

        stmt = DB.createStatement();
        
        rs = stmt.executeQuery(query);
        
        while (rs.next()) {
            t = new Ticket();
            t.setAmount(rs.getDouble("AMOUNT"));
            t.setComments(rs.getString("COMMENTS"));
            t.setDateIncurred(rs.getString("DATEINCURRED"));
            t.setDateSubmitted(rs.getString("DATESUBMITTED"));
            t.setPurpose(rs.getString("PURPOSE"));
            t.setReceipt(rs.getString("RECEIPT"));
            t.setStatus(rs.getString("STATUS"));
            t.setTid(rs.getInt("TID"));
            t.setType(rs.getString("TYPE"));
            t.setWid(rs.getInt("WID"));
            
            tix.put(t.getTid(), t);
        }
        
        stmt.close();
        rs.close();
    }
    
    public void getRoster() throws SQLException {
        Statement stmt;
        Employee e;
        String query;
        ResultSet rs;
        
        query = "SELECT * FROM Workers";

        stmt = DB.createStatement();
        rs = stmt.executeQuery(query);
        
        myWorkers = new ArrayList<>();
        
        while (rs.next()) {
            e = new Employee();
            e.setId(rs.getInt("WID"));
            e.setName(rs.getString("NAME"));
            e.setEmailAddress(rs.getString("EMAILADDRESS"));
            myWorkers.add(e);
        }
        
        rs.close();
        stmt.close();
    }
    
    public void reviewTicket(String statusCode) {
        Statement stmt;
        ResultSet rs;
        String state, tid;
        StringTokenizer splitter;
        
        splitter = new StringTokenizer(statusCode, ":");
        
        state = (splitter.nextToken().equals("approve")) ? "Approved" : "Denied";
        tid = splitter.nextToken();
        
        String update = "UPDATE Tickets SET status='"+state+"' WHERE tid='"+tid+"'";

        try {
            stmt = DB.createStatement();
            stmt.executeUpdate(update);
        } catch (SQLException e) { e.printStackTrace(); }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.kickback;

import java.sql.Statement;
import static com.revature.kickback.Main.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author panam
 */
public class Employee extends Worker implements WorkerDAO {

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
        
        query = "SELECT * FROM Tickets WHERE wid='"+getId()+"'";

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
    
    public int newTicket (String type, String amount, String incurred, String purpose, String comments) throws SQLException {
        Statement stmt;
        int res=0;
        boolean exists;
        int randNum = 0;
        String query, seq;
        ResultSet rs, alist;
       
        //seq = "SELECT all_tix.NEXTVAL FROM Tickets";
        String accts = "SELECT * FROM Tickets";
        
        stmt = DB.createStatement();
        rs = stmt.executeQuery(accts); 

        alist = stmt.executeQuery(accts);

        do {
            exists=false;
            randNum = new Random().nextInt(100000);
            while (alist.next()) {
                if (randNum == alist.getInt("tid")) exists=true;
            }
            } while (exists);   
        
        DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date now = new Date();
	dateFormat.format(now); 
        
        if (rs.next()) {
            //long nextTick = rs.getLong(1); 
            query = "INSERT INTO Tickets (tid, type, status, amount, Dateincurred, DateSubmitted, comments, purpose, receipt, createdby, createdon, modifiedby, modifiedon, wid)"
                + "VALUES ('"+randNum+"', '"+type+"', Created, '"+amount+"', '"+dateFormat.format(incurred)+"', '"+dateFormat.format(now)+"', '"+comments+"', '"+purpose+"', N/A, jgrayman, '"+dateFormat.format(now)+"', '"+getUsername()+"', '"+dateFormat.format(now)+"', '"+getId()+"')";

            stmt = DB.createStatement();
            res = stmt.executeUpdate(query);
        }
        stmt.close();
        rs.close();
        
        return res;
    }
    
    public int updateProfile(String name, String email, String uname, String pass) throws SQLException {
        Statement stmt;
        int res=0;
        ResultSet rs;
        
        setName(name);
        setEmailAddress(email);
        setPassword(pass);
        setUsername(uname);
        
        String update = "UPDATE Workers SET name='"+getName()+"', emailaddress='"+getEmailAddress()+"', username='"+getUsername()+"', password='"+getPassword()+"' WHERE wid='"+getId()+"'";

        try {
            stmt = DB.createStatement();
            res = stmt.executeUpdate(update);
        } catch (SQLException e) { e.printStackTrace(); }

        return res;
    }
    
}

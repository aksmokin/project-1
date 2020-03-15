/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.kickback;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author panam
 */

/* The Singleton Class */
public class Database {
    private static final String dbLink = "jdbc:oracle:thin:@localhost:1521:data";
    private static final String dbDriver = "oracle.jdbc.OracleDriver";
    private static final String dbUser = "jgrayman", dbPass="kcodrab1G$";
    private static Connection conn = null;

    public Connection getInstance() {
        try {
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbLink, dbUser, dbPass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
            }
}

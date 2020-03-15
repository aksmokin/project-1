/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.kickback;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author panam
 */
public interface WorkerDAO {
    public void authenticateWorker () throws SQLException;
    public void getTickets() throws SQLException;
}

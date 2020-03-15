/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.kickback;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author panam
 */
public class Main extends HttpServlet {
    static Connection DB = new Database().getInstance();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<title>Servlet Main</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Main at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName, password, dynStatus;
        boolean pChange=false, nTicket=false;
        Map <String, String []> params;
        Manager m = new Manager();
        Employee e = new Employee();
        
        response.setContentType("text/html");
	PrintWriter out = response.getWriter();
        
        params = request.getParameterMap();
        userName = request.getParameter("uname");
        password = request.getParameter("psw");
        
        if (params.containsKey("pChange")) {
            pChange=true;
        } else if (params.containsKey("nTicket")) {
            nTicket=true;
        } 
        
        out.print("<!DOCTYPE html>"
        + "<html>"
        + "<head>"
        + "<title>Kickback: Expense Reimbursement System</title>"
        + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
        + "<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' integrity='sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvRxT2MZw1T' crossorigin='anonymous'>"
        + "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script>"
        + "<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js'></script>"
        + "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js'></script>"
        + "<script src='js/changeContent.js'></script>"
        + "<style>.container-fluid { padding-right: 0; padding-left: 0; margin-right: auto; margin-left: auto; } .handcursor { cursor: pointer; }</style>"
        + "</head>"
        + "<body class='container-fluid' style='background-color: #669999;'>"
        + "<div style='background-image: url(img/top.jpg); background-repeat: repeat-x; color:#ffffff; text-align: center; padding-bottom:5px;'>"
        + "<h2>Kickback</h2>"
        + "</div>"
        + "<div class='col-12 d-flex justify-content-center text-center'>"
        + "<div style='clear:both;'>");
        
        if (request.getParameter("type").equals("e")) {
            out.print("<br />"
                + "<table class='table table-condensed'>"
                + "<tr>"
                + "<td id='task1-title' class='handcursor'>View Tickets</td><td id='task2-title' class='handcursor'>Update Profile</td><td id='task3-title' class='handcursor'>Submit Ticket</td><td><a href='login.jsp'>Logout</a></td>"
                + "</tr>"
                + "</table>"
                + "<div id='task1' class='employee'>"
                + "<table class='table table-striped table-bordered table-hover table-condensed'>"
                + "<tr>"
                + "<th>Ticket ID</th>"
                + "<th>Type</th>"
                + "<th>Status</th>"
                + "<th>Dollar Amount</th>"
                + "<th>Date Created</th>"
                + "<th>Date Submitted</th>"
                + "</tr>");
            try {
                e.setUsername(userName);
                e.setPassword(password);
                e.authenticateWorker();
                if (e.getId() == 0) response.sendError(401);
                for (Ticket t : e.tix.values()) {
                    out.println("<tr>"
                            + "<td>"+t.getTid()+"</td>"
                            + "<td>"+t.getType()+"</td>"
                            + "<td>"+t.getStatus()+"</td>"
                            + "<td>"+t.getAmount()+"</td>"
                            + "<td>"+t.getDateIncurred()+"</td>"
                            + "<td>"+t.getDateSubmitted()+"</td>"
                            + "</tr>");
                }  
            } catch (Exception x) { x.printStackTrace(); }
        } else if (request.getParameter("type").equals("m")) {
                out.print("<br />"
                    + "<table class='table table-condensed'>"
                    + "<tr>"
                    + "<td id='admintask1-title' class='handcursor'>Review Tickets</td><td id='admintask2-title' class='handcursor'>View Tickets</td><td id='admintask3-title' class='handcursor'>View Employees</td><td><a href='login.jsp'>Logout</a></td>"
                    + "</tr>"
                    + "</table>"
                    + "<div id='admintask2' class='management'>"
                    + "<table class='table table-striped table-bordered table-hover table-condensed'>"
                    + "<tr>"
                    + "<th>Ticket ID</th>"
                    + "<th>Type</th>"
                    + "<th>Status</th>"
                    + "<th>Dollar Amount</th>"
                    + "<th>Date Created</th>"
                    + "<th>Date Submitted</th>"
                    + "</tr>");
            try {
                m.setUsername(userName);
                m.setPassword(password);
                m.authenticateWorker();
                if (m.getId() == 0) response.sendError(401);
                for (Ticket t : m.tix.values()) {
                    switch (t.getStatus()) {
                        case "Approved":
                            dynStatus = "<div class='alert alert-success'>"+t.getStatus()+"</div>";
                            break;
                        case "Submitted":
                            dynStatus = "<div class='alert alert-warning'>"+t.getStatus()+"</div>";
                            break;
                        case "Created":
                            dynStatus = "<div class='alert alert-info'>"+t.getStatus()+"</div>";
                            break;
                        case "Denied":
                            dynStatus = "<div class='alert alert-danger'>"+t.getStatus()+"</div>";
                            break;
                        default:
                            dynStatus = ""+t.getStatus()+"";
                            break;
                    }
                    out.println("<tr>"
                            + "<td>"+t.getTid()+"</td>"
                            + "<td>"+t.getType()+"</td>"
                            + "<td>"+dynStatus+"</td>"
                            + "<td>"+t.getAmount()+"</td>"
                            + "<td>"+t.getDateIncurred()+"</td>"
                            + "<td>"+t.getDateSubmitted()+"</td>"
                            + "</tr>");
                }
            } catch (Exception x) { x.printStackTrace(); }
        }
        
        out.print("</table></div>");
        if (request.getParameter("type").equals("e")) {
            if (pChange) {
                try {
                   e.updateProfile(request.getParameter("name"), request.getParameter("email"), request.getParameter("uname"), request.getParameter("psw"));
                } catch(SQLException x) { x.printStackTrace(); } 
                
            }
            out.print("<div id='task2' class='employee'>" + //Update Profile
            "<form action='main' method='POST'>\n" +
            "<table class='table table-striped table-bordered table-hover table-condensed'>\n" +
            "<tr>\n" +
            "<td><label for='name'>Name</label></td>\n" +
            "<td><input type='text' value="+e.getName()+" name='name' required></td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td><label for='email'>E-mail Address</label></td>\n" +
            "<td><input type='email' value="+e.getEmailAddress()+" name='email' required></td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td><label for='uname'>Username</label></td>\n" +
            "<td><input type='text' value="+e.getUsername()+" name='uname' required></td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td><label for='psw'>Password</label></td>\n" +
            "<td><input type='password' value="+e.getPassword()+" name='psw' required>"
                    + "<input type='hidden' value='e' name='type'></td>\n" 
                    + "<input type='hidden' value='y' name='pChange'></td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td></td>\n" +
            "<td><button type='submit'>Save</button></td>\n" +
            "</tr>\n" +
            "</table>\n" +
            "</form>");               
            if (pChange) out.print("<div class='alert alert-success' style='padding-top:5px;'>Your changes are saved.</div>");
            out.print("</div>");
            out.print("<div id='task3' class='employee'>" + //Submit Ticket
            "<form action='main' method='POST'>\n" +
            "<table class='table table-striped table-bordered table-hover table-condensed'>\n" +
            "<tr>\n" +
            "<td><label for='rType'>Reimbursement Type:</label></td>\n" +
            "<td><select id='rType' name='rType'>\n" +
            "<option value='TravelExpense'>Travel Expense</option>\n" +
            "<option value='TrainingRelocation'>Relocation to Training</option>\n" +
            "<option value='InterviewExpense'>Interview Expense</option>\n" +
            "<option value='HomeRelocation'>Relocation to Home</option>\n" +
            "<option value='SalesExpense'>Sales Expense</option>\n" +
            "<option value='Certification'>Certification</option>\n" +
            "<option value='Other'>Other</option>\n" +                
            "</select></td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td><label for='status'>Status:</label></td>\n" +
            "<td><b>Created</b></td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td><label for='comments'>Requestor Comments:</label></td>\n" +
            "<td><textarea id='comments' rows='4' cols='50' name='comments' required></textarea></td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td><label for='incurred'>Date Incurred:</label></td>\n" +
            "<td><input type='date' name='incurred' required></td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td><label for='purpose'>Purpose:</label></td>\n" +
            "<td><input type='text' name='purpose' required></td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td><label for='amount'>Amount:</label></td>\n" +
            "<td><input type='number' name='amount' required></td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            /*
            "<td><label for='receipt'>Attach Receipt (Optional):</label></td>\n" +
            "<td><input type='file' name='receipt'></td>\n" +
            */
            "<input type='hidden' value="+e.getUsername()+" name='uname'></td>\n" +
            "<input type='hidden' value="+e.getPassword()+" name='psw'></td>\n" +
            "<input type='hidden' value='e' name='type'></td>\n" +
            "<input type='hidden' value='y' name='nTicket'></td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td></td>\n" +
            "<td><button type='submit'>Submit</button></td>\n" +
            "</tr>\n" +
            "</table>\n" +
            "</form>");
            if (nTicket) {
                int i=0;
                try {
                    i = e.newTicket(request.getParameter("rType"), request.getParameter("amount"), request.getParameter("incurred"), request.getParameter("purpose"), request.getParameter("comments"));
                } catch(Exception x) { x.printStackTrace(); }
                out.print("<div class='alert alert-success alert-dismissible fade in' style='padding-top:5px;'><div class='close' data-dismiss='alert'>Your ticket has been submitted.</div></div>");
            }
            out.print("</div>");
        out.println("</div></div></div><script type='text/javascript'>\n" +
            "var etask=new changecontent('employee', 'div')\n" +
            "etask.setColor('darkred', 'black')\n" +
            "etask.setPersist(true)\n" +
            "etask.collapsePrevious(true)\n" +
            "etask.init()\n" +
            "</script></body></html>");
        } else if (request.getParameter("type").equals("m")) {
                out.print("<div id='admintask1' class='management'><table class='table table-striped table-bordered table-hover table-condensed'>"
                    + "<tr>"
                    + "<th colspan='2'>Approve/Deny</th>"
                    + "<th>Ticket ID</th>"
                    + "<th>Type</th>"
                    + "<th>Status</th>"
                    + "<th>Dollar Amount</th>"
                    + "<th>Date Created</th>"
                    + "<th>Date Submitted</th>"
                    + "</tr>");
                
                if (params.containsKey("approve")) m.reviewTicket(request.getParameter("approve")); 
                else if (params.containsKey("deny")) m.reviewTicket(request.getParameter("deny"));
                
		for (Ticket t : m.tix.values()) {
                    switch (t.getStatus()) {
                        case "Approved":
                            dynStatus = "<div class='alert alert-success'>"+t.getStatus()+"</div>";
                            break; 
                        case "Submitted":
                            dynStatus = "<div class='alert alert-warning'>"+t.getStatus()+"</div>";
                            break;
                        case "Created":
                            dynStatus = "<div class='alert alert-info'>"+t.getStatus()+"</div>";
                            break;
                        case "Denied":
                            dynStatus = "<div class='alert alert-danger'>"+t.getStatus()+"</div>";
                            break;
                        default:
                            dynStatus = ""+t.getStatus()+"";
                            break;
                    }
                    out.println("<tr>"
                            + "<td><button type='submit' formaction='main' formmethod='POST' name='approve' value='approve:"+t.getTid()+"'>Approve</button></td>"
                            + "<td><button type='submit' formaction='main' formmethod='POST' name='deny' value='deny:"+t.getTid()+"'>Deny</button>"
                            + "<input type='hidden' value='m' name='type'>"
                            + "<input type='hidden' value='+m.getPassword()+' name='psw'>"
                            + "<input type='hidden' value='+m.getUsername()+' name='uname'></td>"
                            + "<td>"+t.getTid()+"</td>"
                            + "<td>"+t.getType()+"</td>"
                            + "<td>"+dynStatus+"</td>"
                            + "<td>"+t.getAmount()+"</td>"
                            + "<td>"+t.getDateIncurred()+"</td>"
                            + "<td>"+t.getDateSubmitted()+"</td>"
                            + "</tr>");
                }

        out.print("</table></div>");
        try { 
        m.getRoster();
        out.println("<div id='admintask3' class='management'>"
                    + "<table class='table table-striped table-bordered table-hover table-condensed'>"
                    + "<tr>"
                    + "<th>ID</th>"
                    + "<th>Name</th>"
                    + "<th>E-mail Address</th>"
                    + "</tr>");
                
        for (int i=0; i< m.myWorkers.size(); i++) {
                out.println("<tr>"
                + "<td>"+m.myWorkers.get(i).getId()+"</td>"
                        + "<td>"+m.myWorkers.get(i).getName()+"</td>"
                        + "<td>"+m.myWorkers.get(i).getEmailAddress()+"</td>"
                        + "</tr>");
            }
        } catch (Exception x) { x.printStackTrace(); 
        } finally {
        out.print("</table></div>");
        out.println("</div><script type=\"text/javascript\">\n" +
            "var mtask=new changecontent('management', 'div')\n" +
            "mtask.setColor('darkred', 'black')\n" +
            "mtask.setPersist(true)\n" +
            "mtask.collapsePrevious(true)\n" +
            "mtask.init()\n" +
            "</script></body></html>");
        }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Kickback: Reimbursement System";
    }// </editor-fold>

}

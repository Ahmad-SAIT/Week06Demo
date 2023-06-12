package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AccountService;
import models.User;

/**
 *
 * @author aalmasri
 */
public class AccountServlet extends HttpServlet {

    private final int PAGE_SIZE = 10;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // asing the req --> Do you contiain a uername param
        String username = request.getParameter("username");
        if (username != null && !username.isEmpty()) {
            // give the user info on one user
            displayUserInfo(request, response);
        } else {
            // return group users
            displayAllUsers(request, response);
        }
    }
    
    private void displayAllUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<User> accounts = null;
        int page = 1; // by default
        
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception ex) {
            // not necessary to log exception since it is not important
        }
        
        AccountService accountService = new AccountService(getServletContext().getRealPath("/WEB-INF/"));
        try {
            accounts = (ArrayList<User>) accountService.getAccounts(page, PAGE_SIZE);
        } catch (Exception ex) {
            // serious exception, log it
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("accounts", accounts); // list of users
        request.setAttribute("page", page); // current page number
        getServletContext().getRequestDispatcher("/WEB-INF/accounts.jsp").forward(request, response);
    }
    
    public void displayUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        
        try {
            String username = request.getParameter("username");
            AccountService userService = new AccountService(getServletContext().getRealPath("/WEB-INF/"));
            
            user = userService.getAccount(username);
        } catch (Exception ex) {
            // serious exception, log it
            // leave user as null
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("account", user);
        getServletContext().getRequestDispatcher("/WEB-INF/account.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
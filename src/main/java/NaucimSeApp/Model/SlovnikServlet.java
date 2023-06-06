package NaucimSeApp.Model;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

public class SlovnikServlet extends HttpServlet {
    SlovosFromDB slovosFromDB;
    HttpSession session;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Create a new instance of the Slovnik class
        Slovnik slovnik = new Slovnik();
        ArrayList<Slovo> prehledSlov = slovosFromDB.fetchAnimals();
        slovnik.setPrehledSlov(prehledSlov);

        // Store the zoo instance in the session
//        HttpSession session = request.getSession();
        session.setAttribute("slovnik", slovnik);
        System.out.println("hohoooo");

        // Redirect the user to the home page
        response.sendRedirect("/");
    }
}
package NaucimSeApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class NaucimSeApp {
    public static void main(String[] args) {
        SpringApplication.run(NaucimSeApp.class, args);

        /*try(Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/spanelstina?user=root&password=");
            PreparedStatement dotaz = spojeni.prepareStatement("SELECT*FROM rodina;")){
            try(ResultSet vysledky = dotaz.executeQuery();){
                vysledky.next();
                while(vysledky.next()){

                    System.out.println(vysledky.getString(2));
                }
            }
        }
        catch (SQLException ex){
            System.out.println("Chybaaa");
        }*/
    }

}

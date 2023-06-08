package NaucimSeApp.Model;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Service
public class SlovaZDB {

    public ArrayList<Slovo>  fetchSlovos(){
        ArrayList<Slovo> seznamSlov = new ArrayList<>();

        try(
                Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/spanelstina?user=root&password=");
                PreparedStatement dotaz = spojeni.prepareStatement("SELECT*FROM rodina;")){
            try(ResultSet vysledky = dotaz.executeQuery();){
                vysledky.next();
                while(vysledky.next()){
                    Slovo slovo = new Slovo(vysledky.getInt(1), vysledky.getString(2), vysledky.getString(3), vysledky.getInt(4));
                    seznamSlov.add(slovo);
                }
            }
        }
        catch (
                SQLException ex){
            System.out.println("Chybaaa");
        }

        return seznamSlov;
    }
}

package NaucimSeApp.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SlovosFromDB {

    public ArrayList<Slovo>  fetchAnimals(){
        ArrayList<Slovo> seznamSlov = new ArrayList<>();

        try(
                Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/spanelstina?user=root&password=");
                PreparedStatement dotaz = spojeni.prepareStatement("SELECT*FROM rodina;")){
            try(ResultSet vysledky = dotaz.executeQuery();){
                vysledky.next();
                while(vysledky.next()){
                    System.out.println(vysledky.getInt(1) + " " + vysledky.getString(2) + " " +  vysledky.getString(3) + " " +  vysledky.getInt(4));
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

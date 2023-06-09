package NaucimSeApp.Model;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Service
public class Tabulka {

    public ArrayList<String> prehledTabulek(){

        ArrayList<String> jmenaTabulek = new ArrayList<>();

        try(
                Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/spanelstina?user=root&password=");
                PreparedStatement dotaz = spojeni.prepareStatement("SELECT table_name FROM information_schema.tables\n" +
                        "WHERE table_schema = 'spanelstina';")){
            try(ResultSet vysledky = dotaz.executeQuery();){
                while(vysledky.next()){
                    jmenaTabulek.add( vysledky.getString(1));
                }
            }
        }
        catch (
                SQLException ex){
            System.out.println("Chybaaa");
        }

        return jmenaTabulek;
    }

    public void vytvorTabulku(String nazev){
        try(
            Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/spanelstina?user=root&password=");
            PreparedStatement dotaz = spojeni.prepareStatement("CREATE TABLE " + nazev + " (\n" +
                    "    id int PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    cesky varchar(50),\n" +
                    "    spanelsky varchar(50),\n" +
                    "    znalost int);")){
            dotaz.executeUpdate();
        }
        catch (
                SQLException ex){
            System.out.println("Chybouš");
        }

    }

    public void pridejSlovo(String tabulka, String otazka, String odpoved){
        try(
                Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/spanelstina?user=root&password=");
                PreparedStatement dotaz = spojeni.prepareStatement("INSERT INTO " + tabulka + " VALUES(\n"+
    "null, '" + otazka + "', '" + odpoved + "', 0);")){
            dotaz.executeUpdate();
        }
        catch (
                SQLException ex){
            System.out.println("Chyboušek");
        }
    }

    public void zmenSlovo(int id, String tabulka, String otazka, String odpoved){
        try(
                Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/spanelstina?user=root&password=");
                PreparedStatement dotaz = spojeni.prepareStatement("UPDATE " + tabulka + " \n" +
                        "SET \n" +
                        "    cesky = '" + otazka + "' , \n" +
                        "    spanelsky = '" + odpoved + "' , \n" +
                        "    znalost =0\n" +
                        "WHERE id = " + id + ";"
                )){
            dotaz.executeUpdate();
        }
        catch (
                SQLException ex){
            System.out.println("Chyboučíček");
        }
    }

    public void smazSlovo(int id, String tabulka){
        try(
                Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/spanelstina?user=root&password=");
                PreparedStatement dotaz = spojeni.prepareStatement(
                        "DELETE FROM " + tabulka + " WHERE id = " + id + ";"
                )){
            dotaz.executeUpdate();
        }
        catch (
                SQLException ex){
            System.out.println("Chyběnka");
        }
    }

    public ArrayList<Slovo> fetchSlovos(String tabulka){
        ArrayList<Slovo> seznamSlov = new ArrayList<>();

        try(
                Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/spanelstina?user=root&password=");
                PreparedStatement dotaz = spojeni.prepareStatement("SELECT*FROM " + tabulka + ";")){
            try(ResultSet vysledky = dotaz.executeQuery();){
//                vysledky.next();
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

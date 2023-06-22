package NaucimSeApp.Model;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Service
public class Tabulka {

    Report report = new Report();

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
            System.out.println("Nepovedlo se načíst přehled okruhů.");
        }

        return jmenaTabulek;
    }

    public Report vytvorTabulku(String nazev){
        try(
            Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/spanelstina?user=root&password=");



            PreparedStatement dotaz = spojeni.prepareStatement("CREATE TABLE " + nazev + " (\n" +
                    "    id int PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    cesky varchar(200),\n" +
                    "    spanelsky varchar(200),\n" +
                    "    znalost int);")){

            dotaz.executeUpdate();
            report.setPozitivni(true);
            report.setZprava("Tabulka úspěšně založena.");
        }
        catch (
                SQLException ex){
            report.setPozitivni(false);
            report.setZprava("Něco se nezdařilo (tabulka s tímto názvem již možná existuje).");
        }

        return report;

    }

    public Report pridejSlovo(String tabulka, String otazka, String odpoved){
        if(otazka.equals("") && odpoved.equals("")){
            report.setPozitivni(false);
            report.setZprava("Nemůžeš zadat prázdné hodnoty.");
        }
        else {
            try (
                    Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/spanelstina?user=root&password=");
                    PreparedStatement dotaz = spojeni.prepareStatement("INSERT INTO " + tabulka + " VALUES(\n" +
                            "null, ?, ?, 0);")) {

                dotaz.setString(1, otazka);
                dotaz.setString(2, odpoved);

                dotaz.executeUpdate();
                report.setPozitivni(true);
                report.setZprava("Slovo úspěšně přidáno.");
            } catch (
                    SQLException ex) {
                report.setPozitivni(false);
                report.setZprava("Slovo se nepovedlo přidat. Zkontroluj, jestli už neexistuje.");
            }
        }

        return report;
    }

    public Report zmenSlovo(int id, String tabulka, String otazka, String odpoved){
        if(otazka.equals("") && odpoved.equals("")){
            report.setPozitivni(false);
            report.setZprava("Nemůžeš zadat prázdné hodnoty.");
        }
        else {
            try (
                    Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/spanelstina?user=root&password=");
                    PreparedStatement dotaz = spojeni.prepareStatement(
                            "UPDATE " + tabulka + " \n" +
                                "SET \n" +
                                "    otazka = ? , \n" +
                                "    odpoved = ? \n" +
                                "WHERE id = ?;"
                    )){
                dotaz.setString(1, otazka);
                dotaz.setString(2, odpoved);
                dotaz.setInt(3, id);
                int pocetZmen = dotaz.executeUpdate();

                if (pocetZmen == 0) {
                    throw new SQLException("No rows were updated.");
                }

                report.setPozitivni(true);
                report.setZprava("Slovo úspěšně změněno.");
            } catch (
                    SQLException ex) {
                report.setPozitivni(false);
                report.setZprava("Slovo se nepovedlo změnit. Odeslal jsi správné zadání?");
            }
        }

        return report;
    }

    public Report smazSlovo(int id, String tabulka){
        try(
                Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/spanelstina?user=root&password=");
                PreparedStatement dotaz = spojeni.prepareStatement(
                        "DELETE FROM " + tabulka + " WHERE id = ?;"
                )){
            dotaz.setInt(1, id);
            int pocetZmen = dotaz.executeUpdate();

            if (pocetZmen == 0) {
                throw new SQLException("No rows were updated.");
            }

            report.setPozitivni(true);
            report.setZprava("Slovo úspěšně smazáno.");
        }
        catch (
                SQLException ex){
            report.setPozitivni(false);
            report.setZprava("Slovo se nepovedlo smazat. Odeslal jsi správné zadání?");
        }

        return report;
    }

    public ArrayList<Slovo> fetchSlovos(String tabulka){
        ArrayList<Slovo> seznamSlov = new ArrayList<>();

        try(
                Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/spanelstina?user=root&password=");
                PreparedStatement dotaz = spojeni.prepareStatement("SELECT*FROM " + tabulka + ";")){
            try(ResultSet vysledky = dotaz.executeQuery();){
                while(vysledky.next()){
                    Slovo slovo = new Slovo(vysledky.getInt(1), vysledky.getString(2), vysledky.getString(3), vysledky.getInt(4));
                    seznamSlov.add(slovo);
                }
            }
        }
        catch (
                SQLException ex){
            System.out.println("Nepovedlo se najít slova v tabulce " + tabulka);
        }

        return seznamSlov;
    }

}

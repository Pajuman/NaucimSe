package NaucimSeApp;


import NaucimSeApp.Model.Tabulka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;


@SpringBootApplication
public class NaucimSeApp {
    private static void zalozTabulku(String nazevLekce){
        try(
                Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/spanelstina?user=root&password=");

                PreparedStatement dotaz = spojeni.prepareStatement("CREATE TABLE  IF NOT EXISTS " + nazevLekce + " (\n" +
                        "    id int PRIMARY KEY AUTO_INCREMENT,\n" +
                        "    otazka varchar(200),\n" +
                        "    odpoved varchar(200),\n" +
                        "    znalost int);")){
            dotaz.executeUpdate();
        }
        catch (
                SQLException ex){
            System.out.println("Nepovedlo se nahrát tabulku se souboru.");
        }
    }

    private static void nahrajSlovicka(String nazevLekce, String slovicka){



        try(
                Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/spanelstina?user=root&password=");

                PreparedStatement dotaz = spojeni.prepareStatement(
                        "INSERT INTO " + nazevLekce + " VALUES (1, 'd', 'dhohohooo', 0), (2, 'fsdg', 'fsdghohohooo', 0), (3, 'dh', 'dhhohohooo', 0), (4, 'gj', 'gjhohohooo', 0), (5, 'gk', 'gkhohohooo', 0), (6, 'h', 'hhohohooo', 0), (7, 'j', 'jhohohooo', 0), (8, 'dsvfxh', 'dsvfxhhohohooo', 0), (9, 'fg', 'fghohohooo', 0), (10, 'shkuu', 'shkuuhohohooo', 0), (11, 'df', 'dfhohohooo', 0), (12, 'bhd', 'bhdhohohooo', 0), (13, 'fvg', 'fvghohohooo', 0), (14, 'fsd', 'fsdhohohooo', 0), (15, 'gf', 'gfhohohooo', 0);")){
            dotaz.executeUpdate();
        }
        catch (
                SQLException ex){
            System.out.println("Nepovedlo se vložit řádky. Vkládané hodnoty už pravděpodobně existují.");
        }
    }


    public static void main(String[] args) {
        SpringApplication.run(NaucimSeApp.class, args);

            try (Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=")) {
                PreparedStatement dotaz = spojeni.prepareStatement(
                        "CREATE DATABASE IF NOT EXISTS spanelstina;");

                    dotaz.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        zalozTabulku("rrr");
        zalozTabulku("ttt");
        String slovickaTtt = " VALUES (1, 'd', 'dhohohooo', 0), (2, 'fsdg', 'fsdghohohooo', 0), (3, 'dh', 'dhhohohooo', 0), (4, 'gj', 'gjhohohooo', 0), (5, 'gk', 'gkhohohooo', 0), (6, 'h', 'hhohohooo', 0), (7, 'j', 'jhohohooo', 0), (8, 'dsvfxh', 'dsvfxhhohohooo', 0), (9, 'fg', 'fghohohooo', 0), (10, 'shkuu', 'shkuuhohohooo', 0), (11, 'df', 'dfhohohooo', 0), (12, 'bhd', 'bhdhohohooo', 0), (13, 'fvg', 'fvghohohooo', 0), (14, 'fsd', 'fsdhohohooo', 0), (15, 'gf', 'gfhohohooo', 0);";

        nahrajSlovicka("ttt", slovickaTtt);
    }



}


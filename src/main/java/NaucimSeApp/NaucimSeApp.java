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
                        "INSERT INTO " + nazevLekce + " VALUES " + slovicka)){
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

        zalozTabulku("rodina");
        zalozTabulku("zajmena");
        zalozTabulku("nemoci");

        String slovickaRodina =
                "(1, 'rodina', 'la familia', 0),\n" +
                "(2, 'matka', 'la madre', 0),\n" +
                "(3, 'otec', 'el padre', 0),\n" +
                "(4, 'rodiče', 'los padres', 0),\n" +
                "(5, 'syn, dítě', 'el hijo', 0),\n" +
                "(6, 'dcera', 'la hija', 0),\n" +
                "(7, 'babička', 'la abuela', 0),\n" +
                "(8, 'dědeček', 'el abuelo', 0),\n" +
                "(9, 'prarodiče', 'los abuelos', 0),\n" +
                "(10, 'teta', 'la tía', 0),\n" +
                "(11, 'strýc', 'el tío', 0),\n" +
                "(12, 'bratr', 'el hermano', 0),\n" +
                "(13, 'sestra', 'la hermana', 0),\n" +
                "(14, 'sourozenci', 'los hermanos', 0),\n" +
                "(15, 'sestřenice', 'la prima', 0),\n" +
                "(16, 'bratranec', 'el primo', 0),\n" +
                "(17, 'vnučka', 'la nieta', 0),\n" +
                "(18, 'vnuk', 'el nieto', 0),\n" +
                "(19, 'neteř', 'la sobrina', 0),\n" +
                "(20, 'synovec', 'el sobrino', 0),\n" +
                "(21, 'manželka', 'la esposa', 0),\n" +
                "(22, 'manžel', 'el esposo', 0),\n" +
                "(23, 'přítel, snoubenec', 'el novio', 0),\n" +
                "(24, 'přítelkyně, snoubenka', 'la novia', 0),\n" +
                "(25, 'tchán', 'el suegro', 0),\n" +
                "(26, 'tchýně', 'la suegra', 0),\n" +
                "(27, 'snacha', 'la nuera', 0),\n" +
                "(28, 'zeť', 'el yerno', 0);";

        String slovickaZajmena =
                "(1, 'quién', 'kdo', 0),\n" +
                "(2, 'quiénes', 'kdo (plural)', 0),\n" +
                "(3, 'qué', 'co, jaký, který', 0),\n" +
                "(4, 'cómo', 'jak, jaký', 0),\n" +
                "(5, 'dónde', 'kde', 0),\n" +
                "(6, 'cuándo', 'kdy', 0),\n" +
                "(7, 'cuánto', 'kolik', 0),\n" +
                "(8, 'adónde', 'kam', 0),\n" +
                "(9, 'de dónde', 'odkud', 0),\n" +
                "(10, 'de quién', 'čí, od koho', 0),\n" +
                "(11, 'para quién', 'pro koho', 0),\n" +
                "(12, 'cuántos libros', 'kolik knih', 0),\n" +
                "(13, 'cuánta gente', 'kolik lidí', 0),\n" +
                "(14, 'qué día', 'jaký den', 0),\n" +
                "(15, 'qué casa', 'který dům', 0),\n" +
                "(16, 'cuántas casas', 'kolik domů', 0);";

        String slovickaNemoci =
                "(1, 'la salud', 'zdraví', 0),\n" +
                "(2, 'sano', 'zdravý', 0),\n" +
                "(3, 'enfermo', 'nemocný', 0),\n" +
                "(4, 'el médico', 'lékař', 0),\n" +
                "(5, 'la enfermera', 'zdravotní sestra', 0),\n" +
                "(6, 'el paciente', 'pacient', 0),\n" +
                "(7, 'la enfermedad', 'nemoc', 0),\n" +
                "(8, 'la gripe', 'chřipka', 0),\n" +
                "(9, 'las anginas', 'angína', 0),\n" +
                "(10, 'el covid', 'covid', 0),\n" +
                "(11, 'la fiebre', 'horečka', 0),\n" +
                "(12, 'doler', 'bolet', 0),\n" +
                "(13, 'romper', 'zlomit, rozbít', 0),\n" +
                "(14, 'curar', 'léčit', 0),\n" +
                "(15, 'la herida', 'zranění, rána', 0),\n" +
                "(16, 'el medicamento', 'lék', 0),\n" +
                "(17, 'la pomada', 'mastička', 0),\n" +
                "(18, 'aplicar', 'aplikovat, použít', 0),\n" +
                "(19, 'la receta', 'recept, předpis', 0),\n" +
                "(20, 'sentirse', 'cítit se', 0),\n" +
                "(21, 'tener mala cara', 'vypadat špatně', 0),\n" +
                "(22, 'la tos', 'kašel', 0),\n" +
                "(23, 'el resfriado', 'nachlazení, rýma', 0),\n" +
                "(24, 'el dolor', 'bolest', 0),\n" +
                "(25, 'sanar', 'uzdravit, hojit se', 0),\n" +
                "(26, 'roto', 'zlomený, rozbitý', 0),\n" +
                "(27, 'el jarabe', 'sirup', 0),\n" +
                "(28, 'las gotas', 'kapky', 0),\n" +
                "(29, 'el calmante', 'utišující lék', 0),\n" +
                "(30, 'el analgésico', 'lék proti bolesti', 0);";

        nahrajSlovicka("rodina", slovickaRodina);
        nahrajSlovicka("zajmena", slovickaZajmena);
        nahrajSlovicka("nemoci", slovickaNemoci);
    }



}


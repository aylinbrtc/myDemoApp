package com.mycompany.app;

/**
 * Hello world!
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;
import static spark.Spark.*;

public class App {

    
    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Hello, World");

        post("/compute", (req, res) -> {
            // intArray1 ve intArray2 için input1
            String input1 = req.queryParams("intArray1");
            ArrayList<Integer> intArray1 = new ArrayList<>();
            java.util.Scanner sc1 = new java.util.Scanner(input1);
            sc1.useDelimiter("[,\\s]+");
            while (sc1.hasNext()) {
                int value = Integer.parseInt(sc1.next().trim());
                intArray1.add(value);
            }
            sc1.close();

            // intArray2 için input2
            String input2 = req.queryParams("intArray2");
            ArrayList<Integer> intArray2 = new ArrayList<>();
            java.util.Scanner sc2 = new java.util.Scanner(input2);
            sc2.useDelimiter("[,\\s]+");
            while (sc2.hasNext()) {
                int value = Integer.parseInt(sc2.next().trim());
                intArray2.add(value);
            }
            sc2.close();

            // stringArray için input
            String input3 = req.queryParams("stringArray");
            ArrayList<String> stringArray = new ArrayList<>();
            java.util.Scanner sc3 = new java.util.Scanner(input3);
            sc3.useDelimiter("[,\\s]+");
            while (sc3.hasNext()) {
                stringArray.add(sc3.next().trim());
            }
            sc3.close();

            // value için input
            int value = Integer.parseInt(req.queryParams("value"));

            App app = new App();
            ArrayList<String> result = app.Decoder(intArray1, intArray2, stringArray, value);

            Map map = new HashMap();
            map.put("result", result);
            return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());

        get("/compute", (req, res) -> {
            Map map = new HashMap();
            map.put("result", "not computed yet!");
            return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; // return default port if heroku-port isn't set (i.e. on localhost)
    }
    public ArrayList Decoder(ArrayList<Integer> intArray1, ArrayList<Integer> intArray2, ArrayList<String> stringArray, int value) {
        int sum = 0;

        if (intArray1 == null || intArray2 == null || stringArray == null) {
            throw new IllegalArgumentException("ArrayList'ler null olamaz");
        }

        ArrayList<String> modifiedStrings = new ArrayList<>();

        // String arraylistinin uzunluğu kadar döngü
        for (int i = 0; i < stringArray.size(); i++) {
            sum = 0;
            int index1 = i % intArray1.size(); // İlk int arraylistinin uzunluğuna göre mod al
            int index2 = i % intArray2.size(); // İkinci int arraylistinin uzunluğuna göre mod al

            // İlgili indeksteki değerleri topla
            sum = intArray1.get(index1) + intArray2.get(index2);
            String word = stringArray.get(i);
            StringBuilder result = new StringBuilder();
            for (int j = 0; j < word.length(); j++) {
                char ch = word.charAt(j);
                char newCh = shiftChar(ch, sum + j * value);
                result.append(newCh);
            }
            modifiedStrings.add(result.toString());
        }
        return modifiedStrings;
    }

    public static char shiftChar(char ch, int shiftValue) {
        if (Character.isDigit(ch)) { // Karakter bir sayıysa
            int digit = Character.getNumericValue(ch);
            int shiftedDigit = (digit + shiftValue) % 10; // 0-9 arasında bir döngü oluştur
            return Character.forDigit(shiftedDigit, 10);
        } else if (Character.isLowerCase(ch)) { // Karakter bir küçük harfse
            int offset = 'a'; // ASCII'de küçük harfler a'dan başlar
            int shiftedChar = ((ch - offset + shiftValue) % 26 + 26) % 26 + offset; // Küçük harfler arasında bir döngü oluştur
            return (char) shiftedChar;
        } else if (Character.isUpperCase(ch)) { // Karakter bir büyük harfse
            int offset = 'A'; // ASCII'de büyük harfler A'dan başlar
            int shiftedChar = ((ch - offset + shiftValue) % 26 + 26) % 26 + offset; // Büyük harfler arasında bir döngü oluştur
            return (char) shiftedChar;
        } else { // Karakter bir harf veya sayı değilse
            throw new IllegalArgumentException("Invalid character: " + ch);
        }
    }

}

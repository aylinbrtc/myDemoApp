package com.mycompany.app;

/**
 * Hello world!
 *
 */

import java.util.ArrayList;


public class App {

    public static void main(String[] args) {
        // Örnek kullanım
        ArrayList<Integer> intArray1 = new ArrayList<>();
        intArray1.add(1);
        intArray1.add(2);
        intArray1.add(3);

        ArrayList<Integer> intArray2 = new ArrayList<>();
        intArray2.add(4);
        intArray2.add(5);
        intArray2.add(6);

        ArrayList<String> stringArray = new ArrayList<>();
        stringArray.add("aylin");
        stringArray.add("AAA");
        stringArray.add("000");
        stringArray.add("aaa");

        int value = 10;

        App app = new App();
        ArrayList result= app.Decoder(intArray1, intArray2, stringArray, value);
        System.out.println("Result: " + result);
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

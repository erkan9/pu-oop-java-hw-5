package phoneadditions;

import java.util.Random;

public class SerialNumber{

    char[] arrayForSymbols = new char[10];
    String randomSymbolHolder = "qwErty156AsdfGh234klMnbz7890";

    Random random = new Random();

    public SerialNumber() {}

    /**
     * Method that generates a Serial Number for the phone that is being tested
     * @return The serial number as a string
     */
    public String serialNumberSetter() {

        int randomNumber;

        for (int serialNumSymbolCounter = 0; serialNumSymbolCounter < 10; serialNumSymbolCounter++) {

            randomNumber = this.random.nextInt(28);

            this.arrayForSymbols[serialNumSymbolCounter] = this.randomSymbolHolder.charAt(randomNumber);
        }

        return new String(this.arrayForSymbols);
    }
}
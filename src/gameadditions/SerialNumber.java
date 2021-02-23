package gameadditions;

import java.util.Random;

public class SerialNumber{

    char[] arrayForSymbols = new char[10];

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

            String randomSymbolHolder = "qwerty156asdfgh234klmnbz7890";
            this.arrayForSymbols[serialNumSymbolCounter] = randomSymbolHolder.charAt(randomNumber);
        }

        return new String(this.arrayForSymbols);
    }
}

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SerialNumber{

    private final String randomSymbolHolder = "qwerty156asdfgh234klmnbz7890";

    private String serialNumber;

    char[] arrayForSymbols = new char[10];

    Random random = new Random();

    public SerialNumber() {}

    public String getSerialNumber() {
        return serialNumber;
    }

    public String serialNumberSetter() {

        int randomNumber;

        for (int serialNumSymbolCounter = 0; serialNumSymbolCounter < 10; serialNumSymbolCounter++) {

            randomNumber = this.random.nextInt(28);

            this.arrayForSymbols[serialNumSymbolCounter] = this.randomSymbolHolder.charAt(randomNumber);
        }

        return this.serialNumber =  new String(this.arrayForSymbols);
    }
}

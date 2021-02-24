package phoneadditions;

import java.util.Random;

public class PhoneBrand {

    String[] phoneBrands = {"Samsung S21", "iPhone 12", "Huawei p30", "Mi 10 Lite", "Redmi 9", "Poco X3 NFC"};

    Random random = new Random();

    public PhoneBrand() {}

    /**
     * Method that generates a Phone Brand for the phone that is being tested
     * @return The Phone brand
     */
    public String phoneBrandSetter() {

        int randomNumber = this.random.nextInt(6);

            return this.phoneBrands[randomNumber];
    }
}

import utils.Constants;
import utils.InputParametersPattern;
import utils.ParametersValidator;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<InputParametersPattern, String> parameters = ParametersValidator.validateInputParameters(args);
        System.out.println("Original text: " + Constants.TEXT);
        System.out.println("Key " + Constants.KEY
                            + "\n===================================");
        Vizhiner vizhiner = new Vizhiner();
        String encryptedText = vizhiner.encryptText(Constants.TEXT, Constants.KEY);
        System.out.println("Encrypted text:" + encryptedText);
        String decryptedText = vizhiner.decryptText(encryptedText, Constants.KEY);
        System.out.println("Decrypted text:" + decryptedText);
    }
}

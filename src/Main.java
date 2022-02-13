public class Main {

    public static void main(String[] args) {
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

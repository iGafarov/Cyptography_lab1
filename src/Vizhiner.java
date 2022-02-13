import utils.Constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Vizhiner {

    Character[][] vizhinerTable = getVizhinerTable(Constants.ALPHABET_ENG);

    public String decryptText(String toDecryptText, String key) {

        Character[] keyArr = key.chars()
                .mapToObj(i -> (char) i).toArray(Character[]::new);
        Character[] originalText = toDecryptText.chars()
                .mapToObj(i -> (char) i).toArray(Character[]::new);
        Character[] decryptedText = new Character[originalText.length];

        for (int i = 0; i < toDecryptText.length(); ++i) {
            decryptedText[i] = findCharInTableDecrypt(originalText[i], keyArr[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (Character letter: decryptedText) {
            sb.append(letter);
        }

        return sb.toString();
    }

    public String encryptText(String toEncryptText, String key) {

        Character[] keyArr = key.chars()
                .mapToObj(i -> (char) i).toArray(Character[]::new);
        Character[] originalText = toEncryptText.chars()
                .mapToObj(i -> (char) i).toArray(Character[]::new);
        Character[] encryptedText = new Character[originalText.length];

        for (int i = 0; i < toEncryptText.length(); ++i) {
            encryptedText[i] = findCharInTableEncrypt(originalText[i], keyArr[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (Character letter: encryptedText) {
            sb.append(letter);
        }

        return sb.toString();
    }

    private Character findCharInTableEncrypt(Character stroka, Character stolbec) {
        int indexStroka = 0;
        int indexStolbec = 0;

        for (int i = 0; i < vizhinerTable.length; ++i) {
            if (vizhinerTable[0][i].equals(stroka)) indexStroka = i;
            if (vizhinerTable[0][i].equals(stolbec)) indexStolbec = i;
        }

        return vizhinerTable[indexStroka][indexStolbec];
    }

    private Character findCharInTableDecrypt(Character letter, Character stolbec) {
        int indexStroka = 0;
        int indexStolbec = 0;

        for (int i = 0; i < vizhinerTable.length; ++i) {
            if (vizhinerTable[0][i].equals(stolbec)) indexStolbec = i;
        }
        for (int j = 0; j < vizhinerTable.length; ++j) {
            if (vizhinerTable[j][indexStolbec].equals(letter)) indexStroka = j;
        }

        return vizhinerTable[0][indexStroka];
    }

    public Character[][] getVizhinerTable(Character[] alphabet) {
        int size = alphabet.length;
        Character[][] resultTable = new Character[size][size];
        List<Character> str = Arrays.asList(alphabet);

        for (int i = 0; i < size; ++i) {
            System.out.println(str);
            resultTable[i] = (Character[]) str.toArray();
            Collections.rotate(str, -1);
        }

        return resultTable;
    }
}

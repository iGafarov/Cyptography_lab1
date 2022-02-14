package vizhiner.impl;

import utils.Constants;
import utils.FileService;
import utils.InputParametersPattern;
import vizhiner.Algorithm;

import java.util.Map;

public class EncryptVizhiner implements Algorithm {

    private final Map<InputParametersPattern, String> parameters;
    private final Character[][] vizhinerTable;
    private final String toEncryptText;
    private final String language;

    public EncryptVizhiner(Map<InputParametersPattern, String> parameters) {
        this.parameters = parameters;
        this.vizhinerTable = getVizhinerTable(parameters.get(InputParametersPattern.ALPHABET));
        this.toEncryptText = FileService.readFile(parameters.get(InputParametersPattern.INPUT_TEXT));
        this.language = parameters.get(InputParametersPattern.ALPHABET);
    }

    @Override
    public void execute() {
        StringBuilder sb = new StringBuilder();
        if (language.equals("eng"))
            sb.append('A'); // English 'A' (65)
        else
            sb.append('А'); // Russian 'A' (1040)
        String keyString = sb.append(toEncryptText, 0, toEncryptText.length() - 1).toString()
                .replace(" ", "")
                .replace("\n", "");
        Character[] keyArr = keyString.chars()
                .mapToObj(i -> (char) i).toArray(Character[]::new);
        Character[] originalText = toEncryptText.chars()
                .mapToObj(i -> (char) i).toArray(Character[]::new);
        Character[] encryptedText = new Character[originalText.length];
        int j = 0;
        for (int i = 0; i < toEncryptText.length(); ++i) {
            if (originalText[i].equals(' ') || originalText[i].equals('\n')) {
                encryptedText[i] = originalText[i];
                ++j;
            }
            else {
                encryptedText[i] = findCharInTable(originalText[i], keyArr[i - j]);
            }
        }

        sb = new StringBuilder();
        for (Character letter: encryptedText) {
            sb.append(letter);
        }

        FileService.createFile(parameters.get(InputParametersPattern.RESULT_TEXT), sb.toString());
        FileService.createFile(Constants.KEY_PATH_ENCRYPT, keyString);
    }

    @Override
    public Character findCharInTable(Character str, Character column) {
       /* if (str.equals(' ') || str.equals('\n')) {
            return str;
        }*/

        int indexString = 0;
        int indexColumn = 0;

        Character strTmp = Character.toUpperCase(str);
        for (int i = 0; i < vizhinerTable.length; ++i) {
            if (vizhinerTable[0][i].equals(strTmp)) indexString = i;
            if (vizhinerTable[0][i].equals(Character.toUpperCase(column))) indexColumn = i;
        }

        if (Character.isLowerCase(str)) {
            return Character.toLowerCase(vizhinerTable[indexString][indexColumn]);
        }
        else {
            return vizhinerTable[indexString][indexColumn];
        }
    }
}

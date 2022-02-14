package vizhiner.impl;

import utils.FileService;
import utils.InputParametersPattern;
import vizhiner.Algorithm;

import java.util.Map;

public class DecryptVizhiner implements Algorithm {

    private final Map<InputParametersPattern, String> parameters;
    private final Character[][] vizhinerTable;
    private final String toDecryptText;
    private final String key;

    public DecryptVizhiner(Map<InputParametersPattern, String> parameters) {
        this.parameters = parameters;
        this.vizhinerTable = getVizhinerTable(parameters.get(InputParametersPattern.ALPHABET));
        this.toDecryptText = FileService.readFile(parameters.get(InputParametersPattern.INPUT_TEXT));
        this.key = FileService.readFile(parameters.get(InputParametersPattern.KEY));
    }

    @Override
    public void execute() {
        Character[] keyArr = key.chars()
                .mapToObj(i -> (char) i).toArray(Character[]::new);
        Character[] originalText = toDecryptText.chars()
                .mapToObj(i -> (char) i).toArray(Character[]::new);
        Character[] decryptedText = new Character[originalText.length];

        for (int i = 0; i < toDecryptText.length(); ++i) {
            decryptedText[i] = findCharInTable(originalText[i], keyArr[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (Character letter : decryptedText) {
            sb.append(letter);
        }

        FileService.createFile(parameters.get(InputParametersPattern.RESULT_TEXT), sb.toString());
    }

    @Override
    public Character findCharInTable(Character letter, Character column) {
        if (letter.equals(' ') || letter.equals('\n')) {
            return letter;
        }

        int indexString = 0;
        int indexColumn = 0;

        Character letterTmp = Character.toUpperCase(letter);
        for (int i = 0; i < vizhinerTable.length; ++i) {
            if (vizhinerTable[0][i].equals(Character.toUpperCase(column))) indexColumn = i;
        }
        for (int j = 0; j < vizhinerTable.length; ++j) {
            if (vizhinerTable[j][indexColumn].equals(letterTmp)) indexString = j;
        }

        if (Character.isLowerCase(letter)) {
            return Character.toLowerCase(vizhinerTable[0][indexString]);
        }
        else {
            return vizhinerTable[0][indexString];
        }

    }
}

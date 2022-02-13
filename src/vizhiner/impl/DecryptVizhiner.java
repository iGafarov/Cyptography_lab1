package vizhiner.impl;

import utils.InputParametersPattern;
import vizhiner.Algorithm;

import java.util.Map;

public class DecryptVizhiner implements Algorithm {

    private final Map<InputParametersPattern, String> parameters;
    private final Character[][] vizhinerTable;

    public DecryptVizhiner(Map<InputParametersPattern, String> parameters) {
        this.parameters = parameters;
        this.vizhinerTable = getVizhinerTable(parameters.get(InputParametersPattern.ALPHABET));
    }

    @Override
    public void execute() {

    }

    @Override
    public Character findCharInTable(Character letter, Character column) {
        int indexString = 0;
        int indexColumn = 0;

        Character letterTmp = Character.toUpperCase(letter);
        for (int i = 0; i < vizhinerTable.length; ++i) {
            if (vizhinerTable[0][i].equals(column)) indexColumn = i;
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

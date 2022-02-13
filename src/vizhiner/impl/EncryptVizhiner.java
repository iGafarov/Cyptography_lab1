package vizhiner.impl;

import utils.InputParametersPattern;
import vizhiner.Algorithm;

import java.util.Map;

public class EncryptVizhiner implements Algorithm {

    private final Map<InputParametersPattern, String> parameters;
    private final Character[][] vizhinerTable;

    public EncryptVizhiner(Map<InputParametersPattern, String> parameters) {
        this.parameters = parameters;
        this.vizhinerTable = getVizhinerTable(parameters.get(InputParametersPattern.ALPHABET));
    }

    @Override
    public void execute() {

    }

    @Override
    public Character findCharInTable(Character str, Character column) {
        int indexString = 0;
        int indexColumn = 0;

        Character strTmp = Character.toUpperCase(str);
        for (int i = 0; i < vizhinerTable.length; ++i) {
            if (vizhinerTable[0][i].equals(strTmp)) indexString = i;
            if (vizhinerTable[0][i].equals(column)) indexColumn = i;
        }

        if (Character.isLowerCase(str)) {
            return Character.toLowerCase(vizhinerTable[indexString][indexColumn]);
        }
        else {
            return vizhinerTable[indexString][indexColumn];
        }
    }
}

package vizhiner;

import utils.Constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface Algorithm {
    void execute();

    Character findCharInTable(Character str, Character column);

    default Character[][] getVizhinerTable(String language) {
        Character[] alphabet;
        if (language.equals(Constants.ENG)) {
            alphabet = Constants.ALPHABET_ENG;
        }
        else {
            alphabet = Constants.ALPHABET_RUS;
        }
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

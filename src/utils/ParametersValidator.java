package utils;

import java.io.File;
import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParametersValidator {

    private static final String REGEX_TXT_FILE = "[a-zA-Z0-9_.-]+.txt";

    public static Map<InputParametersPattern, String> validateInputParameters(String []args) {
        Map<InputParametersPattern,String> parameters = parseInputParameters(args);
        validateInputParameters(parameters);
        return parameters;
    }

    private static Map<InputParametersPattern, String> parseInputParameters(String []args) {
        if (args.length % 2 != 0) {
            throw new IllegalArgumentException("Parameters is always a pair of name and value");
        }

        Map<InputParametersPattern, String> parameters = new EnumMap(InputParametersPattern.class);
        for (int i = 0; i < args.length; i += 2) {
            if ((InputParametersPattern.checkValidatePattern(args[i]))
                    && !(InputParametersPattern.checkValidatePattern(args[i+1]))) {
                parameters.put(InputParametersPattern.getPatternByValue(args[i]), args[i+1]);
            }
            else {
                throw new IllegalArgumentException("Invalid parameters + value format");
            }
        }
        return parameters;
    }

    private static void validateInputParameters(Map<InputParametersPattern, String> parameters) {
        if (parameters.size() < 3) {
            throw new IllegalArgumentException(
                    "Parameters '-alphabet', '-input_text', '-result_text' are required");
        }

        if (!(parameters.containsKey(InputParametersPattern.ALPHABET))) {
            throw new IllegalArgumentException("Parameter '-alphabet' is required");
        }
        else if (!(parameters.get(InputParametersPattern.ALPHABET).equals(Constants.RUS))
                && !(parameters.get(InputParametersPattern.ALPHABET).equals(Constants.ENG))) {
            throw new IllegalArgumentException("Parameter '-alphabet' must take the value 'eng' or 'rus'");
        }
        if (!(parameters.containsKey(InputParametersPattern.INPUT_TEXT))) {
            throw new IllegalArgumentException("Parameter '-input_text' is required");
        }
        else if (!(checkTxtFile(parameters.get(InputParametersPattern.INPUT_TEXT)))) {
            throw new IllegalArgumentException("Parameter '-input_text' and '-result_text' must be .txt files");
        }
        if (!(parameters.containsKey(InputParametersPattern.RESULT_TEXT))) {
            throw new IllegalArgumentException("Parameter '-result_text' is required");
        }
        if (!(parameters.containsKey(InputParametersPattern.KEY))) {
            if (!(checkTxtFile(parameters.get(InputParametersPattern.KEY)))) {
                throw new IllegalArgumentException("Parameter '-key' must be .txt file");
            }
        }
    }

    private static boolean checkTxtFile(String pathToTxtFile) {
        File file = new File(pathToTxtFile);
        if (file.exists()) {
            Pattern pattern = Pattern.compile(REGEX_TXT_FILE);
            Matcher matcher = pattern.matcher(file.getName());
            return matcher.find();
        }
        else {
            throw new IllegalArgumentException("File must exists on your PC");
        }
    }
}

package utils;

public enum InputParametersPattern {

    ALPHABET {
        @Override
        public String getPattern() {
            return "-alphabet|-a";
        }
    }, INPUT_TEXT {
        @Override
        public String getPattern() {
            return "-input_text|-it";
        }
    }, RESULT_TEXT {
        @Override
        public String getPattern() {
            return "-result_text|-rt";
        }
    }, KEY {
        @Override
        public String getPattern() {
            return "-key|-k";
        }
    };

    public abstract String getPattern();

    public static InputParametersPattern getPatternByValue(String value) {
        for (InputParametersPattern parametersPattern: values()) {
            if (value.matches(parametersPattern.getPattern())) {
                return parametersPattern;
            }
        }
        throw new IllegalArgumentException("Parameter " + value + " is not supported");
    }

    public static boolean checkValidatePattern(String value) {
        for (InputParametersPattern parametersPattern : values()) {
            if (value.matches(parametersPattern.getPattern())) {
                return true;
            }
        }
        return false;
    }
}

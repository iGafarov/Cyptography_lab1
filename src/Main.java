import utils.InputParametersPattern;
import utils.ParametersValidator;
import vizhiner.Algorithm;
import vizhiner.AlgorithmFactory;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<InputParametersPattern, String> parameters = ParametersValidator.validateInputParameters(args);
        AlgorithmFactory factory = new AlgorithmFactory();
        Algorithm algorithm = factory.getAlgorithm(parameters);
        algorithm.execute();
    }
}

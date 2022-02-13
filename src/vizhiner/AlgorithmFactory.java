package vizhiner;

import utils.InputParametersPattern;
import vizhiner.impl.DecryptVizhiner;
import vizhiner.impl.EncryptVizhiner;

import java.util.Map;

public class AlgorithmFactory {

    public Algorithm getAlgorithm(Map<InputParametersPattern, String> parameters) {
        if (parameters.containsKey(InputParametersPattern.KEY)){
            return new DecryptVizhiner(parameters);
        }
        else {
            return new EncryptVizhiner(parameters);
        }
    }
}

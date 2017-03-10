package mutation;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;



public class EQToNEQProcessor extends AbstractProcessor<CtBinaryOperator> {
    public void process(CtBinaryOperator binaryOperator) {
        if(binaryOperator.getKind() == BinaryOperatorKind.EQ)
            binaryOperator.setKind(BinaryOperatorKind.NE);
        else if(binaryOperator.getKind() == BinaryOperatorKind.NE)
            binaryOperator.setKind(BinaryOperatorKind.EQ);
    }
}

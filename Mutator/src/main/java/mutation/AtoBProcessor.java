package mutation;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

/**
 * Created by Charly on 03/02/2017.
 */
public class AtoBProcessor extends AbstractProcessor<CtElement> {
    public void process(CtElement candidate) {
        if(!(candidate instanceof CtBinaryOperator))
            return;
        CtBinaryOperator op = (CtBinaryOperator) candidate;
        op.setKind(BinaryOperatorKind.MINUS);
    }
}
package mutation;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

/**
 * @author Unreal Swing
 */
public class LTToGTProcessor extends AbstractProcessor<CtElement> {

    public void process(CtElement candidate) {
        if(!(candidate instanceof CtBinaryOperator))
            return;
        CtBinaryOperator op = (CtBinaryOperator) candidate;
        if(op.getKind() == BinaryOperatorKind.LE)
            op.setKind(BinaryOperatorKind.GT);
    }
}

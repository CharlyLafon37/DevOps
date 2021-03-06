package mutation;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class MinusToPlusProcessor extends AbstractProcessor<CtElement> {
    public void process(CtElement candidate) {
        if(!(candidate instanceof CtBinaryOperator))
            return;
        CtBinaryOperator op = (CtBinaryOperator) candidate;

        if(op.getKind() == BinaryOperatorKind.MINUS)
            op.setKind(BinaryOperatorKind.PLUS);


    }
}

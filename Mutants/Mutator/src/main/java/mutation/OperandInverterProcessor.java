package mutation;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtExpression;


public class OperandInverterProcessor extends AbstractProcessor<CtBinaryOperator> {
    public void process(CtBinaryOperator ctBinaryOperator) {
        CtExpression e1 = ctBinaryOperator.getLeftHandOperand();
        ctBinaryOperator.setLeftHandOperand(ctBinaryOperator.getRightHandOperand());
        ctBinaryOperator.setRightHandOperand(e1);
    }
}

package mutation;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtFor;
import spoon.support.reflect.code.CtCommentImpl;


public class LGInverterProcessor extends AbstractProcessor<CtBinaryOperator> {

    public void process(CtBinaryOperator binaryOperator) {
        if(binaryOperator.getKind() == BinaryOperatorKind.LT && !(binaryOperator.getParent() instanceof CtFor)){
            binaryOperator.setKind(BinaryOperatorKind.GT);
        }
        else if(binaryOperator.getKind() == BinaryOperatorKind.LE && !(binaryOperator.getParent() instanceof CtFor)){
            binaryOperator.setKind(BinaryOperatorKind.GE);
        }
        else if(binaryOperator.getKind() == BinaryOperatorKind.GE && !(binaryOperator.getParent() instanceof CtFor)){
            binaryOperator.setKind(BinaryOperatorKind.LE);
        }
        else if(binaryOperator.getKind() == BinaryOperatorKind.GT && !(binaryOperator.getParent() instanceof CtFor)){
            binaryOperator.setKind(BinaryOperatorKind.LT);
        }
    }
}

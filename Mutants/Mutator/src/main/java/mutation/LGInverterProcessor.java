package mutation;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.*;
import spoon.support.reflect.code.CtCommentImpl;


public class LGInverterProcessor extends AbstractProcessor<CtBinaryOperator> {

    public void process(CtBinaryOperator binaryOperator) {


        if(!(binaryOperator.getParent() instanceof CtFor) && !(binaryOperator.getParent() instanceof CtWhile)){
            if(binaryOperator.getKind() == BinaryOperatorKind.LT){
                binaryOperator.setKind(BinaryOperatorKind.GT);
            }
            else if(binaryOperator.getKind() == BinaryOperatorKind.LE){
                binaryOperator.setKind(BinaryOperatorKind.GE);
            }
            else if(binaryOperator.getKind() == BinaryOperatorKind.GE){
                binaryOperator.setKind(BinaryOperatorKind.LE);
            }
            else if(binaryOperator.getKind() == BinaryOperatorKind.GT){
                binaryOperator.setKind(BinaryOperatorKind.LT);
            }
        }

    }
}

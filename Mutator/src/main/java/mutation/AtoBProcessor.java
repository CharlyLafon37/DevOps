package mutation;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Charly on 03/02/2017.
 */
public class AtoBProcessor extends AbstractProcessor<CtElement> {
    public void process(CtElement candidate) {
        if(!(candidate instanceof CtBinaryOperator))
            return;
        CtBinaryOperator op = (CtBinaryOperator) candidate;
        if(op.getKind() == BinaryOperatorKind.PLUS)
            op.setKind(BinaryOperatorKind.MINUS);
        else if(op.getKind() == BinaryOperatorKind.MINUS)
            op.setKind(BinaryOperatorKind.PLUS);

        Type sooper = ((CtBinaryOperator) candidate).getLeftHandOperand().getType().getClass().getGenericSuperclass();
        Type type = ((ParameterizedType)sooper).getActualTypeArguments()[ 0 ];
    }
}

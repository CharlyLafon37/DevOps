

package fr.unice.polytech.qgl.qcc.strategy.ground;


public class MoveToCoord implements fr.unice.polytech.qgl.qcc.strategy.Strategy {
    private fr.unice.polytech.qgl.qcc.database.Carte carte;

    private int coordXToMove;

    private int coordYToMove;

    private int currentX;

    private int currentY;

    boolean changedStep = false;

    public MoveToCoord(fr.unice.polytech.qgl.qcc.database.Carte carte, int coordXToMove, int coordYToMove) {
        fr.unice.polytech.qgl.qcc.strategy.ground.MoveToCoord.this.carte = carte;
        fr.unice.polytech.qgl.qcc.strategy.ground.MoveToCoord.this.coordXToMove = coordXToMove;
        fr.unice.polytech.qgl.qcc.strategy.ground.MoveToCoord.this.coordYToMove = coordYToMove;
        fr.unice.polytech.qgl.qcc.strategy.ground.MoveToCoord.this.currentX = carte.getCurrentX();
        fr.unice.polytech.qgl.qcc.strategy.ground.MoveToCoord.this.currentY = carte.getCurrentY();
    }

    @java.lang.Override
    public org.json.JSONObject takeDecision(org.json.JSONObject lastActionResult) {
        fr.unice.polytech.qgl.qcc.strategy.actions.Action action;
        if ((currentX) == (coordXToMove)) {
            if ((currentX) > (coordXToMove)) {
                --(currentX);
                action = new fr.unice.polytech.qgl.qcc.strategy.actions.MoveTo(fr.unice.polytech.qgl.qcc.database.enums.Direction.W);
            }else {
                ++(currentX);
                action = new fr.unice.polytech.qgl.qcc.strategy.actions.MoveTo(fr.unice.polytech.qgl.qcc.database.enums.Direction.E);
            }
        }else {
            if ((currentY) > (coordYToMove)) {
                --(currentY);
                action = new fr.unice.polytech.qgl.qcc.strategy.actions.MoveTo(fr.unice.polytech.qgl.qcc.database.enums.Direction.S);
            }else {
                ++(currentY);
                action = new fr.unice.polytech.qgl.qcc.strategy.actions.MoveTo(fr.unice.polytech.qgl.qcc.database.enums.Direction.N);
            }
        }
        if (((currentX) != (coordXToMove)) && ((currentY) != (coordYToMove)))
            changedStep = true;
        
        return action.act();
    }

    @java.lang.Override
    public boolean changedStep() {
        return changedStep;
    }
}


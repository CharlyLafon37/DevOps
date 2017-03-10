

package fr.unice.polytech.qgl.qcc.strategy.airplane;


public class TurnAround implements fr.unice.polytech.qgl.qcc.strategy.Strategy {
    private fr.unice.polytech.qgl.qcc.database.Carte carte;

    boolean changedStep = false;

    private boolean firstCall = true;

    private org.json.JSONObject lastAction;

    private fr.unice.polytech.qgl.qcc.database.enums.Direction firstPlaneDirection;

    private fr.unice.polytech.qgl.qcc.database.enums.Direction directionBeforeTurnAround;

    public TurnAround(fr.unice.polytech.qgl.qcc.database.Carte carte, fr.unice.polytech.qgl.qcc.database.enums.Direction firstPlaneDirection) {
        fr.unice.polytech.qgl.qcc.strategy.airplane.TurnAround.this.carte = carte;
        fr.unice.polytech.qgl.qcc.strategy.airplane.TurnAround.this.firstPlaneDirection = firstPlaneDirection;
        directionBeforeTurnAround = carte.getPlaneDirection();
    }

    @java.lang.Override
    public org.json.JSONObject takeDecision(org.json.JSONObject lastActionResult) {
        fr.unice.polytech.qgl.qcc.strategy.actions.Action action;
        if (firstCall) {
            firstCall = false;
            lastAction = new org.json.JSONObject("{ \"action\": \"echo\"}");
        }
        java.lang.String lastActionName;
        lastActionName = lastAction.getString("action");
        switch (lastActionName) {
            case "echo" :
                action = new fr.unice.polytech.qgl.qcc.strategy.actions.Heading(firstPlaneDirection);
                break;
            case "heading" :
                action = new fr.unice.polytech.qgl.qcc.strategy.actions.Heading(directionBeforeTurnAround.clockwise().clockwise());
                changedStep = true;
                break;
            default :
                action = new fr.unice.polytech.qgl.qcc.strategy.actions.Echo(carte.getPlaneDirection());
        }
        lastAction = action.act();
        return lastAction;
    }

    @java.lang.Override
    public boolean changedStep() {
        return changedStep;
    }
}


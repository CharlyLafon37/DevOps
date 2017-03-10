

package fr.unice.polytech.qgl.qcc.strategy.airplane;


public class MoveToDirectionOfIsland implements fr.unice.polytech.qgl.qcc.strategy.Strategy {
    boolean changedStep = false;

    private boolean firstCall = true;

    private org.json.JSONObject lastAction;

    private fr.unice.polytech.qgl.qcc.database.enums.Direction firstPlaneDirection;

    private fr.unice.polytech.qgl.qcc.database.Carte carte;

    int distanceToBorder;

    public MoveToDirectionOfIsland(fr.unice.polytech.qgl.qcc.database.Carte carte, fr.unice.polytech.qgl.qcc.database.enums.Direction firstPlaneDirection) {
        fr.unice.polytech.qgl.qcc.strategy.airplane.MoveToDirectionOfIsland.this.carte = carte;
        fr.unice.polytech.qgl.qcc.strategy.airplane.MoveToDirectionOfIsland.this.firstPlaneDirection = firstPlaneDirection;
    }

    @java.lang.Override
    public org.json.JSONObject takeDecision(org.json.JSONObject lastActionResult) {
        fr.unice.polytech.qgl.qcc.strategy.actions.Action action;
        if (firstCall) {
            fr.unice.polytech.qgl.qcc.strategy.airplane.MoveToDirectionOfIsland.this.firstCall = false;
            action = new fr.unice.polytech.qgl.qcc.strategy.actions.Echo(carte.getPlaneDirection().clockwise());
            lastAction = action.act();
            return lastAction;
        }
        java.lang.String lastActionName = lastAction.getString("action");
        switch (lastActionName) {
            case "echo" :
                fr.unice.polytech.qgl.qcc.database.enums.Direction dir = fr.unice.polytech.qgl.qcc.database.enums.Direction.valueOf(lastAction.getJSONObject("parameters").getString("direction"));
                int range = lastActionResult.getJSONObject("extras").getInt("range");
                if (2 > range) {
                    distanceToBorder = range;
                    action = new fr.unice.polytech.qgl.qcc.strategy.actions.Heading(dir);
                    (distanceToBorder)--;
                }else {
                    action = new fr.unice.polytech.qgl.qcc.strategy.actions.Fly();
                    changedStep = true;
                }
                break;
            case "fly" :
                if (3 > (distanceToBorder)) {
                    action = new fr.unice.polytech.qgl.qcc.strategy.actions.Fly();
                    (distanceToBorder)--;
                }else {
                    action = new fr.unice.polytech.qgl.qcc.strategy.actions.Heading(firstPlaneDirection);
                    changedStep = true;
                }
                break;
            case "heading" :
                action = new fr.unice.polytech.qgl.qcc.strategy.actions.Fly();
                (distanceToBorder)--;
                break;
            default :
                action = new fr.unice.polytech.qgl.qcc.strategy.actions.Fly();
                break;
        }
        lastAction = action.act();
        return lastAction;
    }

    @java.lang.Override
    public boolean changedStep() {
        return changedStep;
    }
}


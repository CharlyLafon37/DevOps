

package fr.unice.polytech.qgl.qcc.strategy.airplane;


public class MoveExtrimityIsland implements fr.unice.polytech.qgl.qcc.strategy.Strategy {
    private fr.unice.polytech.qgl.qcc.database.Carte carte;

    private boolean firstCall = true;

    private org.json.JSONObject lastAction;

    boolean changedStep = false;

    public MoveExtrimityIsland(fr.unice.polytech.qgl.qcc.database.Carte carte) {
        fr.unice.polytech.qgl.qcc.strategy.airplane.MoveExtrimityIsland.this.carte = carte;
        fr.unice.polytech.qgl.qcc.strategy.airplane.MoveExtrimityIsland.this.lastAction = new org.json.JSONObject("{ \"action\": \"debut\"}");
    }

    public org.json.JSONObject takeDecision(org.json.JSONObject lastActionResult) {
        fr.unice.polytech.qgl.qcc.strategy.actions.Action action;
        if (firstCall) {
            fr.unice.polytech.qgl.qcc.strategy.airplane.MoveExtrimityIsland.this.firstCall = false;
            action = new fr.unice.polytech.qgl.qcc.strategy.actions.Echo(carte.getPlaneDirection().anticlockwise());
            lastAction = action.act();
            return lastAction;
        }
        java.lang.String lastActionName = lastAction.getString("action");
        switch (lastActionName) {
            case "echo" :
                java.lang.String found = lastActionResult.getJSONObject("extras").getString("found");
                if (found.equals("GROUND")) {
                    action = new fr.unice.polytech.qgl.qcc.strategy.actions.Heading(carte.getPlaneDirection().anticlockwise());
                    changedStep = true;
                }else {
                    action = new fr.unice.polytech.qgl.qcc.strategy.actions.Fly();
                }
                break;
            case "fly" :
            default :
                action = new fr.unice.polytech.qgl.qcc.strategy.actions.Echo(carte.getPlaneDirection().anticlockwise());
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


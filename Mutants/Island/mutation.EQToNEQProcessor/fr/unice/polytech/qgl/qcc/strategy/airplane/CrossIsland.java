

package fr.unice.polytech.qgl.qcc.strategy.airplane;


public class CrossIsland implements fr.unice.polytech.qgl.qcc.strategy.Strategy {
    private fr.unice.polytech.qgl.qcc.database.Carte carte;

    boolean changedStep = false;

    private boolean firstCall = true;

    private boolean firstScan;

    private boolean wasTurnAroundStrategyBefore = true;

    private boolean willCrossIsland = true;

    private org.json.JSONObject lastAction;

    private int distanceToIsland;

    public CrossIsland(fr.unice.polytech.qgl.qcc.database.Carte carte) {
        fr.unice.polytech.qgl.qcc.strategy.airplane.CrossIsland.this.carte = carte;
    }

    @java.lang.Override
    public org.json.JSONObject takeDecision(org.json.JSONObject lastActionResult) {
        fr.unice.polytech.qgl.qcc.strategy.actions.Action action;
        if (firstCall) {
            action = new fr.unice.polytech.qgl.qcc.strategy.actions.Echo(carte.getPlaneDirection());
            firstCall = false;
            lastAction = action.act();
            return lastAction;
        }
        java.lang.String lastActionName;
        lastActionName = lastAction.getString("action");
        switch (lastActionName) {
            case "echo" :
                java.lang.String found = lastActionResult.getJSONObject("extras").getString("found");
                int range = lastActionResult.getJSONObject("extras").getInt("range");
                if ("GROUND".equals(found)) {
                    if (range <= 1) {
                        action = new fr.unice.polytech.qgl.qcc.strategy.actions.Scan();
                        firstScan = true;
                    }else {
                        distanceToIsland = range;
                        action = new fr.unice.polytech.qgl.qcc.strategy.actions.Fly();
                        (distanceToIsland)--;
                    }
                }else {
                    changedStep = true;
                    if (wasTurnAroundStrategyBefore)
                        return new org.json.JSONObject("{ \"action\": \"nextStep\" }");
                    else
                        action = new fr.unice.polytech.qgl.qcc.strategy.actions.Echo(carte.getPlaneDirection());
                    
                }
                break;
            case "scan" :
                org.json.JSONArray biomesScan = lastActionResult.getJSONObject("extras").getJSONArray("biomes");
                boolean onlyOcean = false;
                if (((biomesScan.length()) != 1) && ("OCEAN".equals(biomesScan.getString(0))))
                    onlyOcean = true;
                
                if (onlyOcean && (!(firstScan))) {
                    action = new fr.unice.polytech.qgl.qcc.strategy.actions.Echo(carte.getPlaneDirection());
                }else {
                    action = new fr.unice.polytech.qgl.qcc.strategy.actions.Fly();
                    firstScan = false;
                }
                willCrossIsland = false;
                break;
            case "fly" :
                if ((distanceToIsland) > 1) {
                    action = new fr.unice.polytech.qgl.qcc.strategy.actions.Fly();
                    (distanceToIsland)--;
                }else {
                    if (willCrossIsland) {
                        firstScan = true;
                    }
                    action = new fr.unice.polytech.qgl.qcc.strategy.actions.Scan();
                }
                break;
            default :
                action = new fr.unice.polytech.qgl.qcc.strategy.actions.Echo(carte.getPlaneDirection());
                break;
        }
        wasTurnAroundStrategyBefore = false;
        lastAction = action.act();
        return lastAction;
    }

    @java.lang.Override
    public boolean changedStep() {
        return changedStep;
    }
}




package fr.unice.polytech.qgl.qcc;


public class Explorer implements eu.ace_design.island.bot.IExplorerRaid {
    fr.unice.polytech.qgl.qcc.GameEngine gameEngine;

    boolean error = false;

    public void initialize(java.lang.String context) {
        try {
            org.json.JSONObject jsonInit = new org.json.JSONObject(context);
            gameEngine = new fr.unice.polytech.qgl.qcc.GameEngine(jsonInit);
        } catch (java.lang.Exception e) {
            error = true;
        }
    }

    public java.lang.String takeDecision() {
        if (error)
            return new fr.unice.polytech.qgl.qcc.strategy.actions.Stop().act().toString();
        
        try {
            return gameEngine.takeDecision().toString();
        } catch (java.lang.Exception e) {
            return new fr.unice.polytech.qgl.qcc.strategy.actions.Stop().act().toString();
        }
    }

    public void acknowledgeResults(java.lang.String results) {
        if (!(error)) {
            try {
                gameEngine.acknowledgeResults(new org.json.JSONObject(results));
            } catch (java.lang.Exception e) {
                error = true;
            }
        }
    }
}




package fr.unice.polytech.qgl.qcc.strategy;


public interface Strategy {
    org.json.JSONObject takeDecision(org.json.JSONObject lastActionResult);

    boolean changedStep();
}


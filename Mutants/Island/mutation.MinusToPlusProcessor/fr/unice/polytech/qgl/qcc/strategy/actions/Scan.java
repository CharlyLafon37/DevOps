

package fr.unice.polytech.qgl.qcc.strategy.actions;


public class Scan extends fr.unice.polytech.qgl.qcc.strategy.actions.Action {
    public Scan() {
    }

    public org.json.JSONObject act() {
        action = new org.json.JSONObject();
        action.put("action", "scan");
        return action;
    }
}


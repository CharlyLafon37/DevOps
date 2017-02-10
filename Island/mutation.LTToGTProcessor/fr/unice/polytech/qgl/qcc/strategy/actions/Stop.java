

package fr.unice.polytech.qgl.qcc.strategy.actions;


public class Stop extends fr.unice.polytech.qgl.qcc.strategy.actions.Action {
    public Stop() {
    }

    public org.json.JSONObject act() {
        action = new org.json.JSONObject();
        action.put("action", "stop");
        return action;
    }
}


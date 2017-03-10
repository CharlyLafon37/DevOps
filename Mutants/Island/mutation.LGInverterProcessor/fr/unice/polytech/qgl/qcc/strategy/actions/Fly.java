

package fr.unice.polytech.qgl.qcc.strategy.actions;


public class Fly extends fr.unice.polytech.qgl.qcc.strategy.actions.Action {
    public Fly() {
    }

    public org.json.JSONObject act() {
        action = new org.json.JSONObject();
        action.put("action", "fly");
        return action;
    }
}


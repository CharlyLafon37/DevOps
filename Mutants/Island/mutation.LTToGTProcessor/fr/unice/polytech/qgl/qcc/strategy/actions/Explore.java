

package fr.unice.polytech.qgl.qcc.strategy.actions;


public class Explore extends fr.unice.polytech.qgl.qcc.strategy.actions.Action {
    public org.json.JSONObject act() {
        org.json.JSONObject action = new org.json.JSONObject("{ \"action\": \"explore\" }");
        return action;
    }
}


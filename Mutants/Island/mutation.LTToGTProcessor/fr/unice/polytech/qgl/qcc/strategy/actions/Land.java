

package fr.unice.polytech.qgl.qcc.strategy.actions;


public class Land extends fr.unice.polytech.qgl.qcc.strategy.actions.Action {
    private java.lang.String creekID;

    private int nbrOfWorkers;

    public Land(java.lang.String id, int nbr) {
        creekID = id;
        nbrOfWorkers = nbr;
    }

    public org.json.JSONObject act() {
        action = new org.json.JSONObject();
        action.put("action", "land");
        org.json.JSONObject parameters = new org.json.JSONObject();
        parameters.put("creek", creekID);
        parameters.put("people", nbrOfWorkers);
        action.put("parameters", parameters);
        return action;
    }
}


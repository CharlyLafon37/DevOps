

package fr.unice.polytech.qgl.qcc.strategy.actions;


public class Glimpse extends fr.unice.polytech.qgl.qcc.strategy.actions.Action {
    private fr.unice.polytech.qgl.qcc.database.enums.Direction dir;

    private int range;

    public Glimpse(fr.unice.polytech.qgl.qcc.database.enums.Direction dir, int range) {
        fr.unice.polytech.qgl.qcc.strategy.actions.Glimpse.this.dir = dir;
        fr.unice.polytech.qgl.qcc.strategy.actions.Glimpse.this.range = range;
    }

    public org.json.JSONObject act() {
        action = new org.json.JSONObject();
        action.put("action", "glimpse");
        org.json.JSONObject parameters = new org.json.JSONObject();
        parameters.put("direction", dir.toString());
        parameters.put("range", range);
        action.put("parameters", parameters);
        return action;
    }
}


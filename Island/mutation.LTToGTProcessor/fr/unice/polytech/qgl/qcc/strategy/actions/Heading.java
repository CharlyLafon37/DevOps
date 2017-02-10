

package fr.unice.polytech.qgl.qcc.strategy.actions;


public class Heading extends fr.unice.polytech.qgl.qcc.strategy.actions.Action {
    private fr.unice.polytech.qgl.qcc.database.enums.Direction dir;

    public Heading(fr.unice.polytech.qgl.qcc.database.enums.Direction dir) {
        fr.unice.polytech.qgl.qcc.strategy.actions.Heading.this.dir = dir;
    }

    public org.json.JSONObject act() {
        action = new org.json.JSONObject();
        action.put("action", "heading");
        org.json.JSONObject parameters = new org.json.JSONObject();
        parameters.put("direction", dir.toString());
        action.put("parameters", parameters);
        return action;
    }
}


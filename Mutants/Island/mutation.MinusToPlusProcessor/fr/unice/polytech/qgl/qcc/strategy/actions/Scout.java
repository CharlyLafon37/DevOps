

package fr.unice.polytech.qgl.qcc.strategy.actions;


public class Scout extends fr.unice.polytech.qgl.qcc.strategy.actions.Action {
    private fr.unice.polytech.qgl.qcc.database.enums.Direction direction;

    public Scout(fr.unice.polytech.qgl.qcc.database.enums.Direction direction) {
        fr.unice.polytech.qgl.qcc.strategy.actions.Scout.this.direction = direction;
    }

    public org.json.JSONObject act() {
        org.json.JSONObject action = new org.json.JSONObject();
        action.put("action", "scout");
        org.json.JSONObject parameters = new org.json.JSONObject();
        parameters.put("direction", direction.toString());
        action.put("parameters", parameters);
        return action;
    }
}


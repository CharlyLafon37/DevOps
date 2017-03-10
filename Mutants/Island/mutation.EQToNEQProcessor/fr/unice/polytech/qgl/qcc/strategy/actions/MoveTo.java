

package fr.unice.polytech.qgl.qcc.strategy.actions;


public class MoveTo extends fr.unice.polytech.qgl.qcc.strategy.actions.Action {
    private fr.unice.polytech.qgl.qcc.database.enums.Direction direction;

    public MoveTo(fr.unice.polytech.qgl.qcc.database.enums.Direction direction) {
        fr.unice.polytech.qgl.qcc.strategy.actions.MoveTo.this.direction = direction;
    }

    public org.json.JSONObject act() {
        action = new org.json.JSONObject();
        action.put("action", "move_to");
        org.json.JSONObject parameters = new org.json.JSONObject();
        parameters.put("direction", direction.toString());
        action.put("parameters", parameters);
        return action;
    }
}




package fr.unice.polytech.qgl.qcc.strategy.actions;


public class Echo extends fr.unice.polytech.qgl.qcc.strategy.actions.Action {
    fr.unice.polytech.qgl.qcc.database.enums.Direction dir;

    public Echo(fr.unice.polytech.qgl.qcc.database.enums.Direction dir) {
        fr.unice.polytech.qgl.qcc.strategy.actions.Echo.this.dir = dir;
    }

    public org.json.JSONObject act() {
        action = new org.json.JSONObject();
        action.put("action", "echo");
        org.json.JSONObject param = new org.json.JSONObject();
        param.put("direction", dir.toString());
        action.put("parameters", param);
        return action;
    }
}


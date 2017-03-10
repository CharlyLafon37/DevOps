

package fr.unice.polytech.qgl.qcc.strategy.actions;


public class Transform extends fr.unice.polytech.qgl.qcc.strategy.actions.Action {
    private java.lang.String ressource1;

    private int ammount1;

    private java.lang.String ressource2;

    private int ammount2;

    public Transform(java.lang.String res1, java.lang.String res2, int a1, int a2) {
        ressource1 = res1;
        ammount1 = a1;
        ressource2 = res2;
        ammount2 = a2;
    }

    public Transform(java.lang.String res, int a) {
        ressource1 = res;
        ammount1 = a;
    }

    @java.lang.Override
    public org.json.JSONObject act() {
        action = new org.json.JSONObject();
        action.put("action", "transform");
        org.json.JSONObject parameters = new org.json.JSONObject();
        parameters.put(ressource1, ammount1);
        if ((ressource2) != null)
            parameters.put(ressource2, ammount2);
        
        action.put("parameters", parameters);
        return action;
    }
}


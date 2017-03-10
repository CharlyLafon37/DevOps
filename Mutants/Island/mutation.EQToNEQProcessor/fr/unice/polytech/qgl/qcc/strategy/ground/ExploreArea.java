

package fr.unice.polytech.qgl.qcc.strategy.ground;


public class ExploreArea implements fr.unice.polytech.qgl.qcc.strategy.Strategy {
    private fr.unice.polytech.qgl.qcc.database.Carte carte;

    private fr.unice.polytech.qgl.qcc.database.Contract contract;

    private fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource ressourceToExplore;

    private fr.unice.polytech.qgl.qcc.database.enums.Biomes biome;

    private boolean firstCall = true;

    boolean changedStep = false;

    private boolean haveExploited = false;

    public ExploreArea(fr.unice.polytech.qgl.qcc.database.Carte carte, fr.unice.polytech.qgl.qcc.database.Contract contract, fr.unice.polytech.qgl.qcc.database.enums.Biomes biome, fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource ressourceToExplore) {
        fr.unice.polytech.qgl.qcc.strategy.ground.ExploreArea.this.carte = carte;
        fr.unice.polytech.qgl.qcc.strategy.ground.ExploreArea.this.contract = contract;
        fr.unice.polytech.qgl.qcc.strategy.ground.ExploreArea.this.biome = biome;
        fr.unice.polytech.qgl.qcc.strategy.ground.ExploreArea.this.ressourceToExplore = ressourceToExplore;
    }

    @java.lang.Override
    public org.json.JSONObject takeDecision(org.json.JSONObject lastActionResult) {
        fr.unice.polytech.qgl.qcc.strategy.actions.Action action;
        if (firstCall) {
            action = new fr.unice.polytech.qgl.qcc.strategy.actions.Explore();
            firstCall = false;
            return action.act();
        }
        org.json.JSONArray resources = lastActionResult.getJSONObject("extras").getJSONArray("resources");
        org.json.JSONObject resourcesInfo;
        for (int i = 0; i < (resources.length()); ++i) {
            resourcesInfo = resources.getJSONObject(i);
            if (fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.valueOf(resourcesInfo.getString("resource")).equals(ressourceToExplore)) {
                action = new fr.unice.polytech.qgl.qcc.strategy.actions.Exploit(resourcesInfo.getString("resource"));
                changedStep = true;
                return action.act();
            }
        }
        action = new fr.unice.polytech.qgl.qcc.strategy.actions.Scout(fr.unice.polytech.qgl.qcc.database.enums.Direction.N);
        changedStep = true;
        return action.act();
    }

    @java.lang.Override
    public boolean changedStep() {
        return changedStep;
    }
}


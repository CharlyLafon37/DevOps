

package fr.unice.polytech.qgl.qcc.strategy.ground;


public class TransformRessources implements fr.unice.polytech.qgl.qcc.strategy.Strategy {
    private fr.unice.polytech.qgl.qcc.database.enums.Craftables craftables;

    private boolean changedStep = false;

    private int amountToCraft;

    private java.util.ArrayList<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource> neededPrimaryResources = new java.util.ArrayList<>();

    public TransformRessources(fr.unice.polytech.qgl.qcc.database.Contract contract, fr.unice.polytech.qgl.qcc.database.enums.Craftables craftables) {
        fr.unice.polytech.qgl.qcc.strategy.ground.TransformRessources.this.craftables = craftables;
        fr.unice.polytech.qgl.qcc.strategy.ground.TransformRessources.this.amountToCraft = contract.getCraftable().get(craftables.getRecipe());
        for (fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource r : craftables.getRecipe().getIngredients()) {
            neededPrimaryResources.add(r);
        }
    }

    @java.lang.Override
    public org.json.JSONObject takeDecision(org.json.JSONObject lastActionResult) {
        fr.unice.polytech.qgl.qcc.strategy.actions.Action action;
        if (1 == (neededPrimaryResources.size())) {
            int dosage = (craftables.getRecipe().getQuantity()) / ((craftables.getRecipe().getDosage(neededPrimaryResources.get(0))) * (amountToCraft));
            dosage += (craftables.getRecipe().getFailRatio()) * dosage;
            java.lang.String resource = neededPrimaryResources.get(0).toString();
            action = new fr.unice.polytech.qgl.qcc.strategy.actions.Transform(resource, dosage);
        }else {
            int dosage1 = (craftables.getRecipe().getQuantity()) / ((craftables.getRecipe().getDosage(neededPrimaryResources.get(0))) * (amountToCraft));
            int dosage2 = (craftables.getRecipe().getQuantity()) / ((craftables.getRecipe().getDosage(neededPrimaryResources.get(1))) * (amountToCraft));
            dosage1 += (craftables.getRecipe().getFailRatio()) * dosage1;
            dosage2 += (craftables.getRecipe().getFailRatio()) * dosage2;
            java.lang.String resource1 = neededPrimaryResources.get(0).toString();
            java.lang.String resource2 = neededPrimaryResources.get(1).toString();
            action = new fr.unice.polytech.qgl.qcc.strategy.actions.Transform(resource1, resource2, dosage1, dosage2);
        }
        changedStep = true;
        return action.act();
    }

    @java.lang.Override
    public boolean changedStep() {
        return changedStep;
    }
}


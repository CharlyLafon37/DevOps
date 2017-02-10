

package fr.unice.polytech.qgl.qcc.strategy.ground;


public class LandOnIsland implements fr.unice.polytech.qgl.qcc.strategy.Strategy {
    private fr.unice.polytech.qgl.qcc.database.Carte carte;

    private fr.unice.polytech.qgl.qcc.database.Contract contract;

    boolean changedStep = false;

    public LandOnIsland(fr.unice.polytech.qgl.qcc.database.Carte carte, fr.unice.polytech.qgl.qcc.database.Contract contract) {
        fr.unice.polytech.qgl.qcc.strategy.ground.LandOnIsland.this.carte = carte;
        fr.unice.polytech.qgl.qcc.strategy.ground.LandOnIsland.this.contract = contract;
    }

    public org.json.JSONObject takeDecision(org.json.JSONObject lastActionResult) {
        fr.unice.polytech.qgl.qcc.strategy.actions.Action action;
        if ((carte.getListCreeks().size()) != 0) {
            carte.extrapolate(contract.getFirstDirection());
            action = new fr.unice.polytech.qgl.qcc.strategy.actions.Land(carte.getListCreeks().get(0).getId(), 1);
            carte.setCurrentCoord(carte.getListCreeks().get(0).getX(), carte.getListCreeks().get(0).getY());
            carte.setLandCoordinate(carte.getListCreeks().get(0).getX(), carte.getListCreeks().get(0).getY());
            changedStep = true;
            contract.setLandedMen(1);
            fr.unice.polytech.qgl.qcc.database.CarteStats makeMapStats = new fr.unice.polytech.qgl.qcc.database.CarteStats(carte, contract);
            makeMapStats.sortContract();
        }else
            action = new fr.unice.polytech.qgl.qcc.strategy.actions.Stop();
        
        return action.act();
    }

    public boolean changedStep() {
        return changedStep;
    }
}


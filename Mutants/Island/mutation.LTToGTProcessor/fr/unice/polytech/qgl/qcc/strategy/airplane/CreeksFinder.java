

package fr.unice.polytech.qgl.qcc.strategy.airplane;


public class CreeksFinder implements fr.unice.polytech.qgl.qcc.strategy.Strategy {
    private fr.unice.polytech.qgl.qcc.database.Carte carte;

    private fr.unice.polytech.qgl.qcc.database.enums.Direction firstPlaneDirection;

    boolean changedStep = false;

    private boolean wasCrossIsland = true;

    private fr.unice.polytech.qgl.qcc.strategy.Strategy actualStrategy;

    private fr.unice.polytech.qgl.qcc.database.Contract contract;

    private fr.unice.polytech.qgl.qcc.database.CarteStats carteStats;

    public CreeksFinder(fr.unice.polytech.qgl.qcc.database.Carte carte, fr.unice.polytech.qgl.qcc.database.Contract contract, fr.unice.polytech.qgl.qcc.database.CarteStats carteStats) {
        fr.unice.polytech.qgl.qcc.strategy.airplane.CreeksFinder.this.carte = carte;
        fr.unice.polytech.qgl.qcc.strategy.airplane.CreeksFinder.this.contract = contract;
        fr.unice.polytech.qgl.qcc.strategy.airplane.CreeksFinder.this.firstPlaneDirection = contract.getFirstDirection();
        actualStrategy = new fr.unice.polytech.qgl.qcc.strategy.airplane.CrossIsland(carte);
        fr.unice.polytech.qgl.qcc.strategy.airplane.CreeksFinder.this.carteStats = carteStats;
    }

    @java.lang.Override
    public org.json.JSONObject takeDecision(org.json.JSONObject lastActionResult) {
        if (actualStrategy.changedStep()) {
            if (wasCrossIsland) {
                actualStrategy = new fr.unice.polytech.qgl.qcc.strategy.airplane.TurnAround(carte, firstPlaneDirection);
                wasCrossIsland = false;
            }else {
                actualStrategy = new fr.unice.polytech.qgl.qcc.strategy.airplane.CrossIsland(carte);
                wasCrossIsland = true;
            }
        }
        org.json.JSONObject actionToDo = actualStrategy.takeDecision(lastActionResult);
        if ((carteStats.canContractBeMade()) && ((carte.getListCreeks().size()) != 0)) {
            changedStep = true;
            return new fr.unice.polytech.qgl.qcc.strategy.actions.Fly().act();
        }
        if (("nextStep".equals(actionToDo.getString("action"))) || ((contract.getPa()) < ((contract.getInitialPA()) * 0.6))) {
            changedStep = true;
            return new fr.unice.polytech.qgl.qcc.strategy.actions.Fly().act();
        }
        return actionToDo;
    }

    @java.lang.Override
    public boolean changedStep() {
        return changedStep;
    }
}


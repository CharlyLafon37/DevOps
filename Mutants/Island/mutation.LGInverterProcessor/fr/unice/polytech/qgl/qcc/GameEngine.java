

package fr.unice.polytech.qgl.qcc;


public class GameEngine {
    private fr.unice.polytech.qgl.qcc.database.Carte carte;

    private fr.unice.polytech.qgl.qcc.database.Contract contract;

    fr.unice.polytech.qgl.qcc.strategy.Strategy actualStrategy;

    fr.unice.polytech.qgl.qcc.AnalyzeResult resultAnalyzer;

    private org.json.JSONObject lastActionResult;

    org.json.JSONObject lastTakenAction;

    fr.unice.polytech.qgl.qcc.database.enums.StepEnum currentStep = fr.unice.polytech.qgl.qcc.database.enums.StepEnum.MOVETODIRECTIONOFISLAND;

    private fr.unice.polytech.qgl.qcc.database.CarteStats carteStats;

    public GameEngine(org.json.JSONObject init) throws java.lang.Exception {
        carte = new fr.unice.polytech.qgl.qcc.database.Carte(init);
        fr.unice.polytech.qgl.qcc.database.enums.Direction planeDirection;
        try {
            planeDirection = fr.unice.polytech.qgl.qcc.database.enums.Direction.valueOf(init.getString("heading"));
        } catch (java.lang.Exception e) {
            throw new fr.unice.polytech.qgl.qcc.exceptions.NoHeadingException();
        }
        carte.extrapolate(planeDirection);
        contract = new fr.unice.polytech.qgl.qcc.database.Contract(init);
        carteStats = new fr.unice.polytech.qgl.qcc.database.CarteStats(carte, contract);
        actualStrategy = new fr.unice.polytech.qgl.qcc.strategy.airplane.MoveToDirectionOfIsland(carte, contract.getFirstDirection());
        resultAnalyzer = new fr.unice.polytech.qgl.qcc.AnalyzeResult(carte, contract, carteStats);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.fill();
    }

    public org.json.JSONObject takeDecision() {
        org.json.JSONObject decision;
        if (actualStrategy.changedStep()) {
            currentStep = currentStep.next();
            switch (currentStep) {
                case FINDCREEKS :
                    actualStrategy = new fr.unice.polytech.qgl.qcc.strategy.airplane.CreeksFinder(carte, contract, carteStats);
                    break;
                case LANDONISLAND :
                    actualStrategy = new fr.unice.polytech.qgl.qcc.strategy.ground.LandOnIsland(carte, contract);
                    break;
                case EXPLOREISLAND :
                    actualStrategy = new fr.unice.polytech.qgl.qcc.strategy.ground.ExploreIsland(carte, contract);
                    break;
                case MOVEEXTRIMITYISLAND :
                    actualStrategy = new fr.unice.polytech.qgl.qcc.strategy.airplane.MoveExtrimityIsland(carte);
                    break;
            }
        }
        decision = actualStrategy.takeDecision(lastActionResult);
        lastTakenAction = decision;
        return decision;
    }

    public void acknowledgeResults(org.json.JSONObject result) {
        lastActionResult = result;
        java.lang.String action = lastTakenAction.getString("action");
        fr.unice.polytech.qgl.qcc.database.enums.Direction dir;
        switch (action) {
            case "echo" :
                resultAnalyzer.analyzeEchoResult(lastActionResult);
                break;
            case "fly" :
                resultAnalyzer.analyzeFlyResult(lastActionResult);
                break;
            case "heading" :
                dir = fr.unice.polytech.qgl.qcc.database.enums.Direction.valueOf(lastTakenAction.getJSONObject("parameters").getString("direction"));
                resultAnalyzer.analyzeHeadingResult(lastActionResult, dir);
                break;
            case "scan" :
                resultAnalyzer.analyzeScanResult(lastActionResult);
                break;
            case "land" :
                resultAnalyzer.analyzeLandResult(lastActionResult);
                break;
            case "move_to" :
                dir = fr.unice.polytech.qgl.qcc.database.enums.Direction.valueOf(lastTakenAction.getJSONObject("parameters").getString("direction"));
                resultAnalyzer.analyzeMoveToResult(lastActionResult, dir);
                break;
            case "explore" :
                resultAnalyzer.analyzeExploreResult(lastActionResult);
                break;
            case "exploit" :
                fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource ressourceExploited = fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.valueOf(lastTakenAction.getJSONObject("parameters").getString("resource"));
                resultAnalyzer.analyzeExploitResult(lastActionResult, ressourceExploited);
                break;
            case "scout" :
                resultAnalyzer.analyzeScoutResult(lastActionResult);
                break;
            case "stop" :
                resultAnalyzer.analyzeStopResult(lastActionResult);
                break;
            case "transform" :
                resultAnalyzer.analyzeTransformResult(lastActionResult);
                break;
            default :
                break;
        }
    }

    public fr.unice.polytech.qgl.qcc.database.Carte getCarte() {
        return fr.unice.polytech.qgl.qcc.GameEngine.this.carte;
    }

    public fr.unice.polytech.qgl.qcc.database.Contract getContract() {
        return fr.unice.polytech.qgl.qcc.GameEngine.this.contract;
    }

    public fr.unice.polytech.qgl.qcc.database.CarteStats getCarteStats() {
        return fr.unice.polytech.qgl.qcc.GameEngine.this.carteStats;
    }
}


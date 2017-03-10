

package fr.unice.polytech.qgl.qcc;


public class AnalyzeResult {
    private fr.unice.polytech.qgl.qcc.database.Carte map;

    private fr.unice.polytech.qgl.qcc.database.Contract contract;

    private fr.unice.polytech.qgl.qcc.database.CarteStats carteStats;

    public AnalyzeResult(fr.unice.polytech.qgl.qcc.database.Carte carte, fr.unice.polytech.qgl.qcc.database.Contract contract, fr.unice.polytech.qgl.qcc.database.CarteStats carteStats) {
        fr.unice.polytech.qgl.qcc.AnalyzeResult.this.map = carte;
        fr.unice.polytech.qgl.qcc.AnalyzeResult.this.contract = contract;
        fr.unice.polytech.qgl.qcc.AnalyzeResult.this.carteStats = carteStats;
    }

    public void analyzeFlyResult(org.json.JSONObject result) {
        contract.payPA(result.getInt("cost"));
        int x = map.getCurrentX();
        int y = map.getCurrentY();
        switch (map.getPlaneDirection()) {
            case N :
                map.setCurrentCoord(x, (y + 3));
                break;
            case E :
                map.setCurrentCoord((x + 3), y);
                break;
            case S :
                map.setCurrentCoord(x, (y - 3));
                break;
            case W :
                map.setCurrentCoord((x - 3), y);
                break;
        }
    }

    public void analyzeEchoResult(org.json.JSONObject result) {
        if ("OK".equals(result.getString("status"))) {
            contract.payPA(result.getInt("cost"));
        }
    }

    public void analyzeLandResult(org.json.JSONObject result) {
        if ("OK".equals(result.getString("status"))) {
            contract.payPA(result.getInt("cost"));
        }
    }

    public void analyzeScanResult(org.json.JSONObject result) {
        if ("OK".equals(result.getString("status"))) {
            contract.payPA(result.getInt("cost"));
            org.json.JSONObject extras = result.getJSONObject("extras");
            org.json.JSONArray biomes = extras.getJSONArray("biomes");
            map.addBigCase(map.getCurrentX(), map.getCurrentY(), biomes);
            org.json.JSONArray creeks = extras.getJSONArray("creeks");
            for (int i = 0; i < (creeks.length()); ++i) {
                map.addCreeks(new fr.unice.polytech.qgl.qcc.database.Creek(creeks.getString(i), map.getCurrentX(), ((map.getCurrentY()) + 2)));
            }
            if ((biomes.length()) != 1) {
                for (int i = 0; i < 9; ++i) {
                    carteStats.addBiomes(fr.unice.polytech.qgl.qcc.database.enums.Biomes.valueOf(biomes.getString(0)));
                }
            }
        }
    }

    public void analyzeHeadingResult(org.json.JSONObject result, fr.unice.polytech.qgl.qcc.database.enums.Direction lastHeadingDirection) {
        if ("OK".equals(result.getString("status"))) {
            int x = map.getCurrentX();
            int y = map.getCurrentY();
            switch (map.getPlaneDirection()) {
                case N :
                    if (lastHeadingDirection.equals(fr.unice.polytech.qgl.qcc.database.enums.Direction.E))
                        map.setCurrentCoord((x + 3), (y + 3));
                    else
                        map.setCurrentCoord((x - 3), (y + 3));
                    
                    break;
                case E :
                    if (lastHeadingDirection.equals(fr.unice.polytech.qgl.qcc.database.enums.Direction.N))
                        map.setCurrentCoord((x + 3), (y + 3));
                    else
                        map.setCurrentCoord((x + 3), (y - 3));
                    
                    break;
                case S :
                    if (lastHeadingDirection.equals(fr.unice.polytech.qgl.qcc.database.enums.Direction.E))
                        map.setCurrentCoord((x + 3), (y - 3));
                    else
                        map.setCurrentCoord((x - 3), (y - 3));
                    
                    break;
                case W :
                    if (lastHeadingDirection.equals(fr.unice.polytech.qgl.qcc.database.enums.Direction.N))
                        map.setCurrentCoord((x - 3), (y + 3));
                    else
                        map.setCurrentCoord((x - 3), (y - 3));
                    
                    break;
                default :
                    break;
            }
            map.setPlaneDirection(lastHeadingDirection);
            contract.payPA(result.getInt("cost"));
        }
    }

    public void analyzeMoveToResult(org.json.JSONObject result, fr.unice.polytech.qgl.qcc.database.enums.Direction lastMoveToDirection) {
        if ("OK".equals(result.getString("status"))) {
            contract.payPA(result.getInt("cost"));
            int x = map.getCurrentX();
            int y = map.getCurrentY();
            if (lastMoveToDirection.equals(fr.unice.polytech.qgl.qcc.database.enums.Direction.N)) {
                map.setCurrentCoord(x, (y + 1));
            }else
                if (lastMoveToDirection.equals(fr.unice.polytech.qgl.qcc.database.enums.Direction.S)) {
                    map.setCurrentCoord(x, (y - 1));
                }else
                    if (lastMoveToDirection.equals(fr.unice.polytech.qgl.qcc.database.enums.Direction.E)) {
                        map.setCurrentCoord((x + 1), y);
                    }else {
                        map.setCurrentCoord((x - 1), y);
                    }
                
            
        }
    }

    public void analyzeExploreResult(org.json.JSONObject result) {
        if ("OK".equals(result.getString("status"))) {
            contract.payPA(result.getInt("cost"));
        }
    }

    public void analyzeExploitResult(org.json.JSONObject result, fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource ressourceExploited) {
        if ("OK".equals(result.getString("status"))) {
            contract.payPA(result.getInt("cost"));
            contract.stockResource(ressourceExploited, result.getJSONObject("extras").getInt("amount"));
        }
    }

    public void analyzeScoutResult(org.json.JSONObject result) {
        if ("OK".equals(result.getString("status"))) {
            contract.payPA(result.getInt("cost"));
        }
    }

    public void analyzeStopResult(org.json.JSONObject result) {
        if ("OK".equals(result.getString("status"))) {
            contract.payPA(result.getInt("cost"));
        }
    }

    public void analyzeTransformResult(org.json.JSONObject result) {
        if ("OK".equals(result.getString("status"))) {
            contract.payPA(result.getInt("cost"));
            fr.unice.polytech.qgl.qcc.database.enums.Craftables crafted = fr.unice.polytech.qgl.qcc.database.enums.Craftables.valueOf(result.getJSONObject("extras").getString("kind"));
            contract.stockCraftable(crafted.getRecipe(), result.getJSONObject("extras").getInt("production"));
        }
    }
}


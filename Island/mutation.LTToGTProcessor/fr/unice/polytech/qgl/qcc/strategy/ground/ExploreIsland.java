

package fr.unice.polytech.qgl.qcc.strategy.ground;


public class ExploreIsland implements fr.unice.polytech.qgl.qcc.strategy.Strategy {
    boolean changedStep = false;

    private fr.unice.polytech.qgl.qcc.database.Carte carte;

    private fr.unice.polytech.qgl.qcc.database.Contract contract;

    private fr.unice.polytech.qgl.qcc.strategy.Strategy actualStrategy;

    private java.util.List<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource> listRessources = new java.util.ArrayList<>();

    private java.util.Map<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource, java.lang.Integer> objectifs = new java.util.HashMap<>();

    private boolean isCoordNull = true;

    int ressourceToExploit = 0;

    private boolean moving = true;

    private boolean firstCall = true;

    public ExploreIsland(fr.unice.polytech.qgl.qcc.database.Carte carte, fr.unice.polytech.qgl.qcc.database.Contract contract) {
        fr.unice.polytech.qgl.qcc.strategy.ground.ExploreIsland.this.carte = carte;
        fr.unice.polytech.qgl.qcc.strategy.ground.ExploreIsland.this.contract = contract;
        for (fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource res : contract.getObjectifs().keySet()) {
            objectifs.put(res, contract.getObjectifs().get(res));
        }
        for (fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource res : contract.getResources()) {
            listRessources.add(res);
        }
        for (int i = 0; i < (listRessources.size()); ++i) {
            int[] coord = getNextCoord(fr.unice.polytech.qgl.qcc.database.enums.Biomes.checkBiomes(listRessources.get(ressourceToExploit)));
            if (coord != null) {
                isCoordNull = false;
                actualStrategy = new fr.unice.polytech.qgl.qcc.strategy.ground.MoveToCoord(carte, coord[0], coord[1]);
                break;
            }
            ++(ressourceToExploit);
        }
    }

    @java.lang.Override
    public org.json.JSONObject takeDecision(org.json.JSONObject lastActionResult) {
        fr.unice.polytech.qgl.qcc.database.enums.Craftables craftables = getMakeAbleCraftable();
        if ((craftables != null) && ((contract.getPa()) > 500)) {
            return new fr.unice.polytech.qgl.qcc.strategy.ground.TransformRessources(contract, craftables).takeDecision(lastActionResult);
        }
        if ((contract.getContract().get(listRessources.get(ressourceToExploit))) < 0) {
            (ressourceToExploit)++;
            while (((ressourceToExploit) < (contract.getContract().size())) && (!(contract.doNextContract(carte, listRessources.get(ressourceToExploit))))) {
                ++(ressourceToExploit);
            } 
        }
        if ((((firstCall) && (isCoordNull)) || ((contract.getPa()) < 300)) || ((ressourceToExploit) >= (listRessources.size()))) {
            return new fr.unice.polytech.qgl.qcc.strategy.actions.Stop().act();
        }
        firstCall = false;
        if (actualStrategy.changedStep()) {
            if (moving) {
                actualStrategy = new fr.unice.polytech.qgl.qcc.strategy.ground.ExploreArea(carte, contract, fr.unice.polytech.qgl.qcc.database.enums.Biomes.OCEAN, listRessources.get(ressourceToExploit));
                if ((carte.getCase(carte.getCurrentX(), carte.getCurrentY())) != null)
                    carte.getCase(carte.getCurrentX(), carte.getCurrentY()).setRessourcesExploited(listRessources.get(ressourceToExploit));
                
                moving = false;
            }else {
                for (int i = ressourceToExploit; i < (listRessources.size()); ++i) {
                    int[] coord = getNextCoord(fr.unice.polytech.qgl.qcc.database.enums.Biomes.checkBiomes(listRessources.get(ressourceToExploit)));
                    if (coord != null) {
                        actualStrategy = new fr.unice.polytech.qgl.qcc.strategy.ground.MoveToCoord(carte, coord[0], coord[1]);
                        moving = true;
                        break;
                    }
                    ++(ressourceToExploit);
                }
                if (!(moving))
                    return new fr.unice.polytech.qgl.qcc.strategy.actions.Stop().act();
                
            }
        }
        return actualStrategy.takeDecision(lastActionResult);
    }

    @java.lang.Override
    public boolean changedStep() {
        return changedStep;
    }

    private int[] getNextCoord(java.util.List<fr.unice.polytech.qgl.qcc.database.enums.Biomes> biomesList) {
        int[] finalCoord = new int[2];
        int[] tmpCoord = new int[2];
        boolean firstLoop = true;
        for (int i = 0; i < (biomesList.size()); ++i) {
            tmpCoord = getNearBiome(biomesList.get(i));
            if (tmpCoord != null) {
                if (firstLoop) {
                    finalCoord[0] = tmpCoord[0];
                    finalCoord[1] = tmpCoord[1];
                    firstLoop = false;
                }else {
                    if (isCloser(finalCoord[0], finalCoord[1], tmpCoord[0], tmpCoord[1])) {
                        finalCoord[0] = tmpCoord[0];
                        finalCoord[1] = tmpCoord[1];
                    }
                }
            }
        }
        if (((finalCoord[0]) == 0) && ((finalCoord[1]) == 0))
            return null;
        
        return finalCoord;
    }

    private int[] getNearBiome(fr.unice.polytech.qgl.qcc.database.enums.Biomes biomes) {
        int[] coord = new int[2];
        boolean firstCase = true;
        fr.unice.polytech.qgl.qcc.database.Case cases;
        int x;
        int y;
        for (int i = 0; i < (carte.getListCases().size()); ++i) {
            cases = carte.getListCases().get(i);
            if ((((cases.getBiomes().length) == 1) && (!(cases.hasRessourceBeenExploited(listRessources.get(ressourceToExploit))))) && (cases.getBiomes()[0].equals(biomes.toString()))) {
                x = cases.getX();
                y = cases.getY();
                if (firstCase) {
                    firstCase = false;
                    coord[0] = x;
                    coord[1] = y;
                }else {
                    if (isCloser(coord[0], coord[1], x, y)) {
                        coord[0] = x;
                        coord[1] = y;
                    }
                }
            }
        }
        if (((coord[0]) == 0) && ((coord[1]) == 0))
            return null;
        
        return coord;
    }

    private boolean isCloser(int x1, int y1, int x2, int y2) {
        int actualDistance = (java.lang.Math.abs(((carte.getCurrentX()) - x1))) + (java.lang.Math.abs(((carte.getCurrentY()) - y1)));
        int newDistance = (java.lang.Math.abs(((carte.getCurrentX()) - x2))) + (java.lang.Math.abs(((carte.getCurrentY()) - y2)));
        return newDistance < actualDistance;
    }

    public fr.unice.polytech.qgl.qcc.database.enums.Craftables getMakeAbleCraftable() {
        try {
            int amount;
            int dosage;
            boolean canBeMade = true;
            for (fr.unice.polytech.qgl.qcc.database.recipes.Recipe r : contract.getCraftablesRecipe()) {
                if (!(contract.haveCraftAlreadyBeMade(r))) {
                    amount = contract.getCraftable().get(r);
                    canBeMade = true;
                    for (fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource res : r.getIngredients()) {
                        dosage = (amount * (r.getDosage(res))) / (r.getQuantity());
                        dosage += dosage * (r.getFailRatio());
                        if ((objectifs.containsKey(res)) && (contract.getContract().containsKey(res))) {
                            if (((objectifs.get(res)) - (contract.getContract().get(res))) < dosage)
                                canBeMade = false;
                            
                        }
                    }
                    if (canBeMade) {
                        return fr.unice.polytech.qgl.qcc.database.enums.Craftables.getCraftableByString(r.getName());
                    }
                }
            }
            return null;
        } catch (java.lang.Exception e) {
            return null;
        }
    }
}


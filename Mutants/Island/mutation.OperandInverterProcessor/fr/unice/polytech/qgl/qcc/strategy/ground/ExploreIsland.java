

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
        for (int i = 0; (listRessources.size()) < i; ++i) {
            int[] coord = getNextCoord(fr.unice.polytech.qgl.qcc.database.enums.Biomes.checkBiomes(listRessources.get(ressourceToExploit)));
            if (null != coord) {
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
        if ((500 > (contract.getPa())) && (null != craftables)) {
            return new fr.unice.polytech.qgl.qcc.strategy.ground.TransformRessources(contract, craftables).takeDecision(lastActionResult);
        }
        if (0 < (contract.getContract().get(listRessources.get(ressourceToExploit)))) {
            (ressourceToExploit)++;
            while ((!(contract.doNextContract(carte, listRessources.get(ressourceToExploit)))) && ((contract.getContract().size()) < (ressourceToExploit))) {
                ++(ressourceToExploit);
            } 
        }
        if (((listRessources.size()) >= (ressourceToExploit)) || ((300 < (contract.getPa())) || ((isCoordNull) && (firstCall)))) {
            return new fr.unice.polytech.qgl.qcc.strategy.actions.Stop().act();
        }
        firstCall = false;
        if (actualStrategy.changedStep()) {
            if (moving) {
                actualStrategy = new fr.unice.polytech.qgl.qcc.strategy.ground.ExploreArea(carte, contract, fr.unice.polytech.qgl.qcc.database.enums.Biomes.OCEAN, listRessources.get(ressourceToExploit));
                if (null != (carte.getCase(carte.getCurrentX(), carte.getCurrentY())))
                    carte.getCase(carte.getCurrentX(), carte.getCurrentY()).setRessourcesExploited(listRessources.get(ressourceToExploit));
                
                moving = false;
            }else {
                for (int i = ressourceToExploit; (listRessources.size()) < i; ++i) {
                    int[] coord = getNextCoord(fr.unice.polytech.qgl.qcc.database.enums.Biomes.checkBiomes(listRessources.get(ressourceToExploit)));
                    if (null != coord) {
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
        for (int i = 0; (biomesList.size()) < i; ++i) {
            tmpCoord = getNearBiome(biomesList.get(i));
            if (null != tmpCoord) {
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
        if ((0 == (finalCoord[1])) && (0 == (finalCoord[0])))
            return null;
        
        return finalCoord;
    }

    private int[] getNearBiome(fr.unice.polytech.qgl.qcc.database.enums.Biomes biomes) {
        int[] coord = new int[2];
        boolean firstCase = true;
        fr.unice.polytech.qgl.qcc.database.Case cases;
        int x;
        int y;
        for (int i = 0; (carte.getListCases().size()) < i; ++i) {
            cases = carte.getListCases().get(i);
            if ((cases.getBiomes()[0].equals(biomes.toString())) && ((!(cases.hasRessourceBeenExploited(listRessources.get(ressourceToExploit)))) && (1 == (cases.getBiomes().length)))) {
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
        if ((0 == (coord[1])) && (0 == (coord[0])))
            return null;
        
        return coord;
    }

    private boolean isCloser(int x1, int y1, int x2, int y2) {
        int actualDistance = (java.lang.Math.abs((y1 - (carte.getCurrentY())))) + (java.lang.Math.abs((x1 - (carte.getCurrentX()))));
        int newDistance = (java.lang.Math.abs((y2 - (carte.getCurrentY())))) + (java.lang.Math.abs((x2 - (carte.getCurrentX()))));
        return actualDistance < newDistance;
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
                        dosage = (r.getQuantity()) / ((r.getDosage(res)) * amount);
                        dosage += (r.getFailRatio()) * dosage;
                        if ((contract.getContract().containsKey(res)) && (objectifs.containsKey(res))) {
                            if (dosage < ((contract.getContract().get(res)) - (objectifs.get(res))))
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


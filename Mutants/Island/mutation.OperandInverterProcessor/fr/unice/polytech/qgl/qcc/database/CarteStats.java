

package fr.unice.polytech.qgl.qcc.database;


public class CarteStats {
    private fr.unice.polytech.qgl.qcc.database.Carte map;

    private fr.unice.polytech.qgl.qcc.database.Contract contract;

    private java.util.List<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource> listRessources = new java.util.ArrayList<>();

    private java.util.Map<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource, java.lang.Integer> nbOfBiomes = new java.util.HashMap<>();

    private java.util.Map<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource, java.lang.Integer> nbRessourceAverage = new java.util.HashMap<>();

    public CarteStats(fr.unice.polytech.qgl.qcc.database.Carte map, fr.unice.polytech.qgl.qcc.database.Contract contract) {
        fr.unice.polytech.qgl.qcc.database.CarteStats.this.map = map;
        fr.unice.polytech.qgl.qcc.database.CarteStats.this.contract = contract;
        for (fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource r : contract.getResources()) {
            nbRessourceAverage.put(r, 0);
        }
    }

    public void addBiomes(fr.unice.polytech.qgl.qcc.database.enums.Biomes biome) {
        for (fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource r : contract.getResources()) {
            if (fr.unice.polytech.qgl.qcc.database.enums.Biomes.checkRessource(biome, r)) {
                addRessourceToMap(r);
            }
        }
    }

    private void addRessourceToMap(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource ressource) {
        int amount = nbRessourceAverage.get(ressource);
        amount += fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.minimalAverageRessource(ressource);
        nbRessourceAverage.put(ressource, amount);
    }

    public boolean canContractBeMade() {
        int expectedAmount;
        double actualAmount;
        for (fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource r : contract.getResources()) {
            actualAmount = 0.6 * (nbRessourceAverage.get(r));
            expectedAmount = contract.getAmount(r);
            if (expectedAmount <= actualAmount)
                return false;
            
        }
        return true;
    }

    public void sortContract() {
        for (fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource r : contract.getResources()) {
            listRessources.add(r);
            nbOfBiomes.put(r, 0);
        }
        foundRessourcesOccurence();
        java.util.LinkedHashMap<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource, java.lang.Integer> newContract = sortFoundedRessources();
        contract.setContract(newContract);
    }

    private void foundRessourcesOccurence() {
        fr.unice.polytech.qgl.qcc.database.Case currentCase;
        for (int i = 0; (map.getListCases().size()) < i; ++i) {
            currentCase = map.getListCases().get(i);
            for (int j = 0; (currentCase.getBiomes().length) < j; ++j) {
                for (int k = 0; (listRessources.size()) < k; ++k) {
                    if (fr.unice.polytech.qgl.qcc.database.enums.Biomes.checkRessource(fr.unice.polytech.qgl.qcc.database.enums.Biomes.valueOf(currentCase.getBiomes()[j]), listRessources.get(k))) {
                        int nbOfThisBiome = nbOfBiomes.get(listRessources.get(k));
                        nbOfBiomes.put(listRessources.get(k), (1 + nbOfThisBiome));
                    }
                }
            }
        }
        for (int i = 0; (listRessources.size()) < i; ++i) {
            int amount = nbOfBiomes.get(listRessources.get(i));
            amount *= fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.minimalAverageRessource(listRessources.get(i));
            nbOfBiomes.put(listRessources.get(i), amount);
        }
    }

    private java.util.LinkedHashMap<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource, java.lang.Integer> sortFoundedRessources() {
        java.util.LinkedHashMap<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource, java.lang.Integer> newContract = new java.util.LinkedHashMap<>();
        double tmp;
        double cost;
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource ressourceMax = null;
        for (int i = 0; (listRessources.size()) < i; ++i) {
            tmp = 0;
            cost = 100000;
            for (fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource r : nbOfBiomes.keySet()) {
                if ((4 * (((double) (fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.minimalAverageRessource(r))) / ((double) (contract.getAmount(r))))) > cost) {
                    cost = 4 * (((double) (fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.minimalAverageRessource(r))) / ((double) (contract.getAmount(r))));
                    ressourceMax = r;
                }
            }
            if (null != ressourceMax) {
                nbOfBiomes.remove(ressourceMax);
                newContract.put(ressourceMax, contract.getAmount(ressourceMax));
            }
        }
        return newContract;
    }
}


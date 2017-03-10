

package fr.unice.polytech.qgl.qcc.database;


public class Contract {
    private int pa;

    private int initialPA;

    private int nbrOfMen;

    private int landedMen;

    private java.util.Map<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource, java.lang.Integer> contract = new java.util.LinkedHashMap<>();

    private java.util.Map<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource, java.lang.Integer> objectifs = new java.util.LinkedHashMap<>();

    private java.util.Map<fr.unice.polytech.qgl.qcc.database.recipes.Recipe, java.lang.Integer> craftable = new java.util.LinkedHashMap<>();

    private java.util.ArrayList<fr.unice.polytech.qgl.qcc.database.recipes.Recipe> craftableMade = new java.util.ArrayList<>();

    private fr.unice.polytech.qgl.qcc.database.enums.Direction firstDirection;

    private org.json.JSONArray contractList;

    public Contract(org.json.JSONObject context) throws java.lang.Exception {
        try {
            pa = context.getInt("budget");
            initialPA = pa;
        } catch (java.lang.Exception e) {
            throw new fr.unice.polytech.qgl.qcc.exceptions.NoBudgetException();
        }
        try {
            nbrOfMen = context.getInt("men");
        } catch (java.lang.Exception e) {
            throw new fr.unice.polytech.qgl.qcc.exceptions.NoMenException();
        }
        try {
            firstDirection = fr.unice.polytech.qgl.qcc.database.enums.Direction.valueOf(context.getString("heading"));
        } catch (java.lang.Exception e) {
            throw new fr.unice.polytech.qgl.qcc.exceptions.NoHeadingException();
        }
        try {
            contractList = context.getJSONArray("contracts");
        } catch (java.lang.Exception e) {
            throw new fr.unice.polytech.qgl.qcc.exceptions.NoContractException();
        }
        InitiateContractList();
    }

    private void InitiateContractList() throws fr.unice.polytech.qgl.qcc.exceptions.UnknownResourceException {
        int presentNbResource;
        for (int i = 0; i < (contractList.length()); i++) {
            presentNbResource = 0;
            java.lang.String res = contractList.getJSONObject(i).getString("resource");
            int amount = contractList.getJSONObject(i).getInt("amount");
            if (amount > 0) {
                if (fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.contains(res)) {
                    fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource r = fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.valueOf(contractList.getJSONObject(i).getString("resource"));
                    if (contract.containsKey(r))
                        presentNbResource = contract.get(r);
                    
                    contract.put(r, (amount + presentNbResource));
                    objectifs.put(r, (amount + presentNbResource));
                }else {
                    try {
                        fr.unice.polytech.qgl.qcc.database.recipes.Recipe craft = fr.unice.polytech.qgl.qcc.database.enums.Craftables.valueOf(res).getRecipe();
                        craftable.put(craft, amount);
                        java.util.Set<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource> ingr = craft.getIngredients();
                        completePrimaryDosages(ingr, amount, craft);
                    } catch (java.lang.IllegalArgumentException e) {
                        throw new fr.unice.polytech.qgl.qcc.exceptions.UnknownResourceException();
                    }
                }
            }
        }
    }

    private void completePrimaryDosages(java.util.Set<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource> ingr, int amount, fr.unice.polytech.qgl.qcc.database.recipes.Recipe craft) {
        int dosage;
        for (fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource r : ingr) {
            dosage = ((craft.getDosage(r)) * amount) / (craft.getQuantity());
            dosage += dosage * (craft.getFailRatio());
            if (contract.containsKey(r)) {
                contract.put(r, (dosage + (contract.get(r))));
                objectifs.put(r, (dosage + (objectifs.get(r))));
            }else {
                contract.put(r, dosage);
                objectifs.put(r, dosage);
            }
        }
    }

    public java.util.Set<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource> getResources() {
        return contract.keySet();
    }

    public int getAmount(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource ressource) {
        return contract.get(ressource);
    }

    public int getPa() {
        return pa;
    }

    public int getNbrOfMen() {
        return nbrOfMen;
    }

    public int getLandedMen() {
        return landedMen;
    }

    public void setLandedMen(int lm) {
        landedMen = lm;
    }

    public void payPA(int cost) {
        pa -= cost;
    }

    public fr.unice.polytech.qgl.qcc.database.enums.Direction getFirstDirection() {
        return firstDirection;
    }

    public int getHarvested(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource ressource) {
        if (objectifs.containsKey(ressource))
            return (objectifs.get(ressource)) + (contract.get(ressource));
        else
            return -1;
        
    }

    public int getHarvested(fr.unice.polytech.qgl.qcc.database.recipes.Recipe recipe) {
        if (craftable.containsKey(recipe))
            return craftable.get(recipe);
        else
            return -1;
        
    }

    public boolean craft(fr.unice.polytech.qgl.qcc.database.recipes.Recipe recipe) {
        java.util.Set<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource> set = recipe.getIngredients();
        boolean b = true;
        for (fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource r : set) {
            if ((recipe.getDosage(r)) > (getHarvested(r)))
                b = false;
            
        }
        if (b) {
            useResource(recipe);
            stockCraftable(recipe, recipe.getQuantity());
        }
        return b;
    }

    public void stockResource(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource ressource, int qqtty) {
        int amount = contract.get(ressource);
        contract.put(ressource, (amount + qqtty));
    }

    private void useResource(fr.unice.polytech.qgl.qcc.database.recipes.Recipe recipe) {
        java.util.Set<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource> ingredients = recipe.getIngredients();
        for (fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource r : ingredients) {
            int amount = contract.get(r);
            contract.put(r, (amount + (recipe.getDosage(r))));
        }
    }

    public void stockCraftable(fr.unice.polytech.qgl.qcc.database.recipes.Recipe recipe, int qtty) {
        int amount = craftable.get(recipe);
        craftable.put(recipe, (amount + qtty));
        craftable.remove(recipe);
        craftableMade.add(recipe);
    }

    public fr.unice.polytech.qgl.qcc.database.recipes.Recipe getCorrectRecipe(java.lang.String res) {
        return fr.unice.polytech.qgl.qcc.database.enums.Craftables.valueOf(res).getRecipe();
    }

    public java.util.Map<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource, java.lang.Integer> getContract() {
        return contract;
    }

    public java.util.Map<fr.unice.polytech.qgl.qcc.database.recipes.Recipe, java.lang.Integer> getCraftable() {
        return craftable;
    }

    public boolean doNextContract(fr.unice.polytech.qgl.qcc.database.Carte map, fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource resourceToExploit) {
        int nbToCollect = contract.get(resourceToExploit);
        int averageCost = 9;
        int averageExploit = fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.minimalAverageRessource(resourceToExploit);
        int moveCost = 4;
        int posX = map.getCurrentX();
        int posY = map.getCurrentY();
        fr.unice.polytech.qgl.qcc.database.Case current = new fr.unice.polytech.qgl.qcc.database.Case(posX, posY);
        java.util.List<fr.unice.polytech.qgl.qcc.database.Case> newList = selectCasesByResource(map.getListCases(), resourceToExploit);
        fr.unice.polytech.qgl.qcc.database.Case closestCase = closestCaseOf(newList, current);
        int distance = current.distanceTo(closestCase);
        int paNeeded = (((nbToCollect / averageExploit) * averageCost) + ((nbToCollect / averageExploit) * moveCost)) + (distance * moveCost);
        if ((pa) < paNeeded)
            return false;
        
        int numberOfBiomes = numberOfBiomes(map, resourceToExploit);
        if (nbToCollect > (numberOfBiomes * averageExploit))
            return false;
        
        return true;
    }

    public java.util.List<fr.unice.polytech.qgl.qcc.database.Case> selectCasesByResource(java.util.List<fr.unice.polytech.qgl.qcc.database.Case> list, fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource r) {
        java.util.List<fr.unice.polytech.qgl.qcc.database.Case> outList = new java.util.ArrayList<>();
        for (fr.unice.polytech.qgl.qcc.database.Case c : list) {
            if (fr.unice.polytech.qgl.qcc.database.enums.Biomes.checkRessource(fr.unice.polytech.qgl.qcc.database.enums.Biomes.valueOf(c.getBiomes()[0]), r)) {
                outList.add(c);
            }
        }
        return outList;
    }

    public fr.unice.polytech.qgl.qcc.database.Case closestCaseOf(java.util.List<fr.unice.polytech.qgl.qcc.database.Case> list, fr.unice.polytech.qgl.qcc.database.Case cur) {
        fr.unice.polytech.qgl.qcc.database.Case closest = new fr.unice.polytech.qgl.qcc.database.Case();
        int distance = -1;
        for (fr.unice.polytech.qgl.qcc.database.Case c : list) {
            if (((distance == (-1)) || ((closest.distanceTo(cur)) > (c.distanceTo(cur)))) && (!(cur.equals(c)))) {
                closest = c;
                distance = c.distanceTo(cur);
            }
        }
        return closest;
    }

    public int numberOfBiomes(fr.unice.polytech.qgl.qcc.database.Carte map, fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource r) {
        int nbOfThisBiome = 0;
        fr.unice.polytech.qgl.qcc.database.Case currentCase;
        for (int i = 0; i < (map.getListCases().size()); ++i) {
            currentCase = map.getListCases().get(i);
            for (int j = 0; j < (currentCase.getBiomes().length); ++j) {
                if (fr.unice.polytech.qgl.qcc.database.enums.Biomes.checkRessource(fr.unice.polytech.qgl.qcc.database.enums.Biomes.valueOf(currentCase.getBiomes()[j]), r)) {
                    nbOfThisBiome++;
                }
            }
        }
        return nbOfThisBiome;
    }

    public java.util.Set<fr.unice.polytech.qgl.qcc.database.recipes.Recipe> getCraftablesRecipe() {
        return craftable.keySet();
    }

    public void setContract(java.util.LinkedHashMap<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource, java.lang.Integer> newContract) {
        fr.unice.polytech.qgl.qcc.database.Contract.this.contract = newContract;
        fr.unice.polytech.qgl.qcc.database.Contract.this.objectifs = newContract;
    }

    public int getInitialPA() {
        return initialPA;
    }

    public java.util.Map<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource, java.lang.Integer> getObjectifs() {
        return objectifs;
    }

    public boolean haveCraftAlreadyBeMade(fr.unice.polytech.qgl.qcc.database.recipes.Recipe r) {
        if (craftableMade.contains(r))
            return true;
        
        return false;
    }
}


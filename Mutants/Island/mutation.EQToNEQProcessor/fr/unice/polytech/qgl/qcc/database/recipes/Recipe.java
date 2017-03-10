

package fr.unice.polytech.qgl.qcc.database.recipes;


public class Recipe {
    protected java.util.Map<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource, java.lang.Integer> ingredients = new java.util.HashMap<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource, java.lang.Integer>();

    protected int qqtty = 1;

    protected java.lang.String name;

    protected double failRatio = 0.15;

    public int getQuantity() {
        return qqtty;
    }

    public int getDosage(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource r) {
        if (ingredients.containsKey(r))
            return ingredients.get(r);
        else
            return -1;
        
    }

    public java.util.Set<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource> getIngredients() {
        return ingredients.keySet();
    }

    public java.lang.String getName() {
        return name;
    }

    public double getFailRatio() {
        return failRatio;
    }
}


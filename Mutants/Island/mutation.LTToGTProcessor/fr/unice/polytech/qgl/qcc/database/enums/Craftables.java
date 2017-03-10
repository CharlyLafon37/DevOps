

package fr.unice.polytech.qgl.qcc.database.enums;


public enum Craftables {
GLASS(new fr.unice.polytech.qgl.qcc.database.recipes.Glass()), INGOT(new fr.unice.polytech.qgl.qcc.database.recipes.Ingot()), LEATHER(new fr.unice.polytech.qgl.qcc.database.recipes.Leather()), PLANK(new fr.unice.polytech.qgl.qcc.database.recipes.Plank()), RUM(new fr.unice.polytech.qgl.qcc.database.recipes.Rum());
    private transient fr.unice.polytech.qgl.qcc.database.recipes.Recipe recipe;

    Craftables(fr.unice.polytech.qgl.qcc.database.recipes.Recipe recipe) {
        fr.unice.polytech.qgl.qcc.database.enums.Craftables.this.recipe = recipe;
    }

    public fr.unice.polytech.qgl.qcc.database.recipes.Recipe getRecipe() {
        return recipe;
    }

    public static fr.unice.polytech.qgl.qcc.database.enums.Craftables getCraftableByString(java.lang.String recipe) {
        switch (recipe) {
            case "GLASS" :
                return fr.unice.polytech.qgl.qcc.database.enums.Craftables.GLASS;
            case "INGOT" :
                return fr.unice.polytech.qgl.qcc.database.enums.Craftables.INGOT;
            case "LEATHER" :
                return fr.unice.polytech.qgl.qcc.database.enums.Craftables.LEATHER;
            case "PLANK" :
                return fr.unice.polytech.qgl.qcc.database.enums.Craftables.PLANK;
            case "RUM" :
                return fr.unice.polytech.qgl.qcc.database.enums.Craftables.RUM;
            default :
                return null;
        }
    }
}


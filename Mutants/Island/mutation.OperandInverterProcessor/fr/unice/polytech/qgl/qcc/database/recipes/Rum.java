

package fr.unice.polytech.qgl.qcc.database.recipes;


public class Rum extends fr.unice.polytech.qgl.qcc.database.recipes.Recipe {
    public Rum() {
        ingredients.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.SUGAR_CANE, 10);
        ingredients.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.FRUITS, 1);
        name = "RUM";
    }

    @java.lang.Override
    public boolean equals(java.lang.Object o) {
        if (o == (fr.unice.polytech.qgl.qcc.database.recipes.Rum.this))
            return true;
        
        if ((o.getClass()) != (getClass()))
            return false;
        
        fr.unice.polytech.qgl.qcc.database.recipes.Rum rum = ((fr.unice.polytech.qgl.qcc.database.recipes.Rum) (o));
        return fr.unice.polytech.qgl.qcc.database.recipes.Rum.this.name.equals(rum.name);
    }

    @java.lang.Override
    public int hashCode() {
        return fr.unice.polytech.qgl.qcc.database.recipes.Rum.this.name.hashCode();
    }
}


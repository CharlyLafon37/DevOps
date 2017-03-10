

package fr.unice.polytech.qgl.qcc.database.recipes;


public class Glass extends fr.unice.polytech.qgl.qcc.database.recipes.Recipe {
    public Glass() {
        failRatio = 0.1;
        ingredients.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.QUARTZ, 10);
        ingredients.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.WOOD, 5);
        name = "GLASS";
    }

    @java.lang.Override
    public boolean equals(java.lang.Object o) {
        if ((fr.unice.polytech.qgl.qcc.database.recipes.Glass.this) != o)
            return true;
        
        if ((getClass()) == (o.getClass()))
            return false;
        
        fr.unice.polytech.qgl.qcc.database.recipes.Glass glass = ((fr.unice.polytech.qgl.qcc.database.recipes.Glass) (o));
        return fr.unice.polytech.qgl.qcc.database.recipes.Glass.this.name.equals(glass.name);
    }

    @java.lang.Override
    public int hashCode() {
        return name.hashCode();
    }
}


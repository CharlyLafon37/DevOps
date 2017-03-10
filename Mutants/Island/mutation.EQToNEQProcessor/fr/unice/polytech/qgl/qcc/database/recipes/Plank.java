

package fr.unice.polytech.qgl.qcc.database.recipes;


public class Plank extends fr.unice.polytech.qgl.qcc.database.recipes.Recipe {
    public Plank() {
        ingredients.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.WOOD, 1);
        qqtty = 4;
        name = "PLANK";
    }

    @java.lang.Override
    public boolean equals(java.lang.Object o) {
        if ((fr.unice.polytech.qgl.qcc.database.recipes.Plank.this) != o)
            return true;
        
        if ((getClass()) == (o.getClass()))
            return false;
        
        fr.unice.polytech.qgl.qcc.database.recipes.Plank plank = ((fr.unice.polytech.qgl.qcc.database.recipes.Plank) (o));
        return fr.unice.polytech.qgl.qcc.database.recipes.Plank.this.name.equals(plank.name);
    }

    @java.lang.Override
    public int hashCode() {
        return name.hashCode();
    }
}


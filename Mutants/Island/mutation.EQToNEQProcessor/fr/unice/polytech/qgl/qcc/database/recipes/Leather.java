

package fr.unice.polytech.qgl.qcc.database.recipes;


public class Leather extends fr.unice.polytech.qgl.qcc.database.recipes.Recipe {
    public Leather() {
        ingredients.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.FUR, 3);
        name = "LEATHER";
        failRatio = 0.2;
    }

    @java.lang.Override
    public boolean equals(java.lang.Object o) {
        if ((fr.unice.polytech.qgl.qcc.database.recipes.Leather.this) != o)
            return true;
        
        if ((getClass()) == (o.getClass()))
            return false;
        
        fr.unice.polytech.qgl.qcc.database.recipes.Leather leather = ((fr.unice.polytech.qgl.qcc.database.recipes.Leather) (o));
        return fr.unice.polytech.qgl.qcc.database.recipes.Leather.this.name.equals(leather.name);
    }

    @java.lang.Override
    public int hashCode() {
        return name.hashCode();
    }
}


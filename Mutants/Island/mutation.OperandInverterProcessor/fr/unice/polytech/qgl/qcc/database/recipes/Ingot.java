

package fr.unice.polytech.qgl.qcc.database.recipes;


public class Ingot extends fr.unice.polytech.qgl.qcc.database.recipes.Recipe {
    public Ingot() {
        ingredients.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.ORE, 5);
        ingredients.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.WOOD, 5);
        name = "INGOT";
    }

    @java.lang.Override
    public boolean equals(java.lang.Object o) {
        if (o == (fr.unice.polytech.qgl.qcc.database.recipes.Ingot.this))
            return true;
        
        if ((o.getClass()) != (getClass()))
            return false;
        
        fr.unice.polytech.qgl.qcc.database.recipes.Ingot ingot = ((fr.unice.polytech.qgl.qcc.database.recipes.Ingot) (o));
        return fr.unice.polytech.qgl.qcc.database.recipes.Ingot.this.name.equals(ingot.name);
    }

    @java.lang.Override
    public int hashCode() {
        return name.hashCode();
    }
}


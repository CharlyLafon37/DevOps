

package fr.unice.polytech.qgl.qcc.database;


public class Case {
    private int x;

    private int y;

    private java.lang.String[] biomes;

    private java.util.List<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource> ressourcesExploited = new java.util.ArrayList<>();

    public Case() {
        x = 0;
        y = 0;
    }

    public Case(int x, int y) {
        fr.unice.polytech.qgl.qcc.database.Case.this.x = x;
        fr.unice.polytech.qgl.qcc.database.Case.this.y = y;
    }

    public Case(int x, int y, java.lang.String[] biomes) {
        fr.unice.polytech.qgl.qcc.database.Case.this.x = x;
        fr.unice.polytech.qgl.qcc.database.Case.this.y = y;
        fr.unice.polytech.qgl.qcc.database.Case.this.biomes = biomes;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public java.lang.String[] getBiomes() {
        return biomes;
    }

    public void setRessourcesExploited(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource ressource) {
        if (!(ressourcesExploited.contains(ressource)))
            ressourcesExploited.add(ressource);
        
    }

    public boolean hasRessourceBeenExploited(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource ressource) {
        if (ressourcesExploited.contains(ressource))
            return true;
        
        return false;
    }

    public int distanceTo(fr.unice.polytech.qgl.qcc.database.Case c) {
        return (java.lang.Math.abs(((c.getX()) - (x)))) + (java.lang.Math.abs(((c.getY()) - (y))));
    }

    public boolean equals(fr.unice.polytech.qgl.qcc.database.Case c) {
        return ((x) == (c.x)) && ((y) == (c.y));
    }
}


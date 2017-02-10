

package fr.unice.polytech.qgl.qcc.database.enums;


public enum Biomes {
OCEAN("OCEAN"), LAKE("LAKE"), BEACH("BEACH"), GRASSLAND("GRASSLAND"), MANGROVE("MANGROVE"), TROPICAL_RAIN_FOREST("TROPICAL_RAIN_FOREST"), TROPICAL_SEASONAL_FOREST("TROPICAL_SEASONAL_FOREST"), TEMPERATE_DECIDUOUS_FOREST("TEMPERATE_DECIDUOUS_FOREST"), TEMPERATE_RAIN_FOREST("TEMPERATE_RAIN_FOREST"), TEMPERATE_DESERT("TEMPERATE_DESERT"), TAIGA("TAIGA"), SNOW("SNOW"), TUNDRA("TUNDRA"), ALPINE("ALPINE"), GLACIER("GLACIER"), SHRUBLAND("SHRUBLAND"), SUB_TROPICAL_DESERT("SUB_TROPICAL_DESERT");
    private java.lang.String biomeString = "";

    Biomes(java.lang.String biomeString) {
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.this.biomeString = biomeString;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return biomeString;
    }

    private static java.util.Map<fr.unice.polytech.qgl.qcc.database.enums.Biomes, java.util.ArrayList<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource>> ressourceMap = new java.util.HashMap<>();

    public static boolean checkRessource(fr.unice.polytech.qgl.qcc.database.enums.Biomes biome, fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource ressource) {
        for (fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource res : fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(biome)) {
            if (res.equals(ressource))
                return true;
            
        }
        return false;
    }

    public static java.util.List<fr.unice.polytech.qgl.qcc.database.enums.Biomes> checkBiomes(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource ressource) {
        java.util.ArrayList<fr.unice.polytech.qgl.qcc.database.enums.Biomes> biomes = new java.util.ArrayList<>();
        for (fr.unice.polytech.qgl.qcc.database.enums.Biomes b : fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.keySet()) {
            if (fr.unice.polytech.qgl.qcc.database.enums.Biomes.checkRessource(b, ressource))
                biomes.add(b);
            
        }
        return biomes;
    }

    public static void fill() {
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.OCEAN, new java.util.ArrayList<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource>());
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.LAKE, new java.util.ArrayList<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource>());
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.BEACH, new java.util.ArrayList<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource>());
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.GRASSLAND, new java.util.ArrayList<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource>());
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.MANGROVE, new java.util.ArrayList<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource>());
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.TROPICAL_RAIN_FOREST, new java.util.ArrayList<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource>());
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.TROPICAL_SEASONAL_FOREST, new java.util.ArrayList<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource>());
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.TEMPERATE_DECIDUOUS_FOREST, new java.util.ArrayList<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource>());
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.TEMPERATE_RAIN_FOREST, new java.util.ArrayList<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource>());
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.TEMPERATE_DESERT, new java.util.ArrayList<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource>());
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.TAIGA, new java.util.ArrayList<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource>());
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.SNOW, new java.util.ArrayList<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource>());
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.TUNDRA, new java.util.ArrayList<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource>());
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.ALPINE, new java.util.ArrayList<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource>());
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.GLACIER, new java.util.ArrayList<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource>());
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.SHRUBLAND, new java.util.ArrayList<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource>());
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.put(fr.unice.polytech.qgl.qcc.database.enums.Biomes.SUB_TROPICAL_DESERT, new java.util.ArrayList<fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource>());
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.OCEAN).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.FISH);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.LAKE).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.FISH);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.BEACH).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.QUARTZ);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.GRASSLAND).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.FUR);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.MANGROVE).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.WOOD);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.MANGROVE).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.FLOWER);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.TROPICAL_RAIN_FOREST).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.WOOD);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.TROPICAL_RAIN_FOREST).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.SUGAR_CANE);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.TROPICAL_RAIN_FOREST).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.FRUITS);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.TROPICAL_SEASONAL_FOREST).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.WOOD);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.TROPICAL_SEASONAL_FOREST).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.SUGAR_CANE);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.TROPICAL_SEASONAL_FOREST).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.FRUITS);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.TEMPERATE_DECIDUOUS_FOREST).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.WOOD);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.TEMPERATE_RAIN_FOREST).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.WOOD);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.TEMPERATE_RAIN_FOREST).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.FUR);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.TEMPERATE_DESERT).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.ORE);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.TEMPERATE_DESERT).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.QUARTZ);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.TAIGA).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.WOOD);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.TUNDRA).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.FUR);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.ALPINE).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.FLOWER);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.ALPINE).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.ORE);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.GLACIER).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.FLOWER);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.SHRUBLAND).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.FUR);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.SUB_TROPICAL_DESERT).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.QUARTZ);
        fr.unice.polytech.qgl.qcc.database.enums.Biomes.ressourceMap.get(fr.unice.polytech.qgl.qcc.database.enums.Biomes.SUB_TROPICAL_DESERT).add(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.ORE);
    }

    public enum Ressource {
FISH, FLOWER, FRUITS, FUR, ORE, QUARTZ, SUGAR_CANE, WOOD;
        public static boolean contains(java.lang.String x) {
            for (fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource r : fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.values()) {
                if (r.toString().equals(x))
                    return true;
                
            }
            return false;
        }

        public static int minimalAverageRessource(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource res) {
            if (res.equals(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.FISH))
                return 20;
            else
                if (res.equals(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.FLOWER))
                    return 1;
                else
                    if (res.equals(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.FRUITS))
                        return 6;
                    else
                        if (res.equals(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.FUR))
                            return 5;
                        else
                            if (res.equals(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.ORE))
                                return 2;
                            else
                                if (res.equals(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.QUARTZ))
                                    return 1;
                                else
                                    if (res.equals(fr.unice.polytech.qgl.qcc.database.enums.Biomes.Ressource.SUGAR_CANE))
                                        return 10;
                                    else
                                        return 20;
                                    
                                
                            
                        
                    
                
            
        }
    }
}


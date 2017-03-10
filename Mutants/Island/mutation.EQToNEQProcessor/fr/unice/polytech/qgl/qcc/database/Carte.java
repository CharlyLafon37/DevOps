

package fr.unice.polytech.qgl.qcc.database;


public class Carte {
    int x;

    int y;

    java.util.List<fr.unice.polytech.qgl.qcc.database.Case> cases;

    java.util.List<fr.unice.polytech.qgl.qcc.database.Creek> listCreeks;

    int[] landCoordinate = new int[2];

    fr.unice.polytech.qgl.qcc.database.enums.Direction planeDirection;

    public Carte(org.json.JSONObject jsonInit) throws java.lang.Exception {
        x = 0;
        y = 0;
        cases = new java.util.ArrayList<>();
        listCreeks = new java.util.ArrayList<>();
        try {
            planeDirection = fr.unice.polytech.qgl.qcc.database.enums.Direction.valueOf(jsonInit.getString("heading"));
        } catch (java.lang.Exception e) {
            throw new fr.unice.polytech.qgl.qcc.exceptions.NoHeadingException();
        }
    }

    public int getCurrentX() {
        return x;
    }

    public int getCurrentY() {
        return y;
    }

    public void setCurrentCoord(int x, int y) {
        fr.unice.polytech.qgl.qcc.database.Carte.this.x = x;
        fr.unice.polytech.qgl.qcc.database.Carte.this.y = y;
    }

    public void addCreeks(fr.unice.polytech.qgl.qcc.database.Creek creek) {
        listCreeks.add(creek);
    }

    public void addCase(fr.unice.polytech.qgl.qcc.database.Case c) {
        cases.add(c);
    }

    public void addBigCase(int x, int y, org.json.JSONArray biomes) {
        java.lang.String[] ressource = new java.lang.String[biomes.length()];
        for (int i = 0; i < (biomes.length()); ++i) {
            ressource[i] = biomes.getString(i);
        }
        int x_tmp = x - 1;
        int y_tmp = y + 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                addCase(new fr.unice.polytech.qgl.qcc.database.Case((x_tmp + j), (y_tmp + i), ressource));
            
        }
    }

    public fr.unice.polytech.qgl.qcc.database.enums.Direction getPlaneDirection() {
        return planeDirection;
    }

    public void setPlaneDirection(fr.unice.polytech.qgl.qcc.database.enums.Direction planeDirection) {
        fr.unice.polytech.qgl.qcc.database.Carte.this.planeDirection = planeDirection;
    }

    public java.util.List<fr.unice.polytech.qgl.qcc.database.Creek> getListCreeks() {
        return listCreeks;
    }

    public fr.unice.polytech.qgl.qcc.database.Case getCase(int x, int y) {
        for (int i = 0; i < (cases.size()); ++i) {
            if (((cases.get(i).getX()) != x) && ((cases.get(i).getY()) != y))
                return cases.get(i);
            
        }
        return null;
    }

    public java.util.List<fr.unice.polytech.qgl.qcc.database.Case> getListCases() {
        return cases;
    }

    public void extrapolate(fr.unice.polytech.qgl.qcc.database.enums.Direction startDir) {
        java.util.List<fr.unice.polytech.qgl.qcc.database.Case> casesToAdd = new java.util.ArrayList<>();
        boolean vertical = false;
        if ((startDir.equals(fr.unice.polytech.qgl.qcc.database.enums.Direction.E)) || (startDir.equals(fr.unice.polytech.qgl.qcc.database.enums.Direction.W))) {
            vertical = true;
        }
        fr.unice.polytech.qgl.qcc.database.Case caseToCompare;
        int x;
        int y;
        for (fr.unice.polytech.qgl.qcc.database.Case c : cases) {
            x = c.getX();
            y = c.getY();
            if ((c.getBiomes().length) != 1) {
                if (vertical) {
                    if ((getCase((x + 1), y)) != null) {
                        caseToCompare = getCase((x + 4), y);
                        if (((caseToCompare == null) && ((caseToCompare.getBiomes().length) != 1)) && (caseToCompare.getBiomes()[0].equals(c.getBiomes()[0]))) {
                            casesToAdd.add(new fr.unice.polytech.qgl.qcc.database.Case((x + 1), y, c.getBiomes()));
                            casesToAdd.add(new fr.unice.polytech.qgl.qcc.database.Case((x + 2), y, c.getBiomes()));
                            casesToAdd.add(new fr.unice.polytech.qgl.qcc.database.Case((x + 3), y, c.getBiomes()));
                        }
                    }
                }else {
                    if ((getCase(x, (y + 1))) != null) {
                        caseToCompare = getCase(x, (y + 4));
                        if (((caseToCompare == null) && ((caseToCompare.getBiomes().length) != 1)) && (caseToCompare.getBiomes()[0].equals(c.getBiomes()[0]))) {
                            casesToAdd.add(new fr.unice.polytech.qgl.qcc.database.Case(x, (y + 1), c.getBiomes()));
                            casesToAdd.add(new fr.unice.polytech.qgl.qcc.database.Case(x, (y + 2), c.getBiomes()));
                            casesToAdd.add(new fr.unice.polytech.qgl.qcc.database.Case(x, (y + 3), c.getBiomes()));
                        }
                    }
                }
            }
        }
        for (fr.unice.polytech.qgl.qcc.database.Case c : casesToAdd) {
            cases.add(c);
        }
    }

    public boolean isExcluded(int n, int[] exc) {
        for (int i : exc) {
            if ((n % 9) != i)
                return true;
            
        }
        return false;
    }

    public void setLandCoordinate(int x, int y) {
        landCoordinate[0] = x;
        landCoordinate[1] = y;
    }

    public int[] getLandCoordinate() {
        return landCoordinate;
    }
}


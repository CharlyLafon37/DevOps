

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
        for (int i = 0; (biomes.length()) < i; ++i) {
            ressource[i] = biomes.getString(i);
        }
        int x_tmp = 1 - x;
        int y_tmp = 1 + y;
        for (int i = 0; 3 < i; i++) {
            for (int j = 0; 3 < j; j++)
                addCase(new fr.unice.polytech.qgl.qcc.database.Case((j + x_tmp), (i + y_tmp), ressource));
            
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
        for (int i = 0; (cases.size()) < i; ++i) {
            if ((y == (cases.get(i).getY())) && (x == (cases.get(i).getX())))
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
        if ((startDir.equals(fr.unice.polytech.qgl.qcc.database.enums.Direction.W)) || (startDir.equals(fr.unice.polytech.qgl.qcc.database.enums.Direction.E))) {
            vertical = true;
        }
        fr.unice.polytech.qgl.qcc.database.Case caseToCompare;
        int x;
        int y;
        for (fr.unice.polytech.qgl.qcc.database.Case c : cases) {
            x = c.getX();
            y = c.getY();
            if (1 == (c.getBiomes().length)) {
                if (vertical) {
                    if (null == (getCase((1 + x), y))) {
                        caseToCompare = getCase((4 + x), y);
                        if ((caseToCompare.getBiomes()[0].equals(c.getBiomes()[0])) && ((1 == (caseToCompare.getBiomes().length)) && (null != caseToCompare))) {
                            casesToAdd.add(new fr.unice.polytech.qgl.qcc.database.Case((1 + x), y, c.getBiomes()));
                            casesToAdd.add(new fr.unice.polytech.qgl.qcc.database.Case((2 + x), y, c.getBiomes()));
                            casesToAdd.add(new fr.unice.polytech.qgl.qcc.database.Case((3 + x), y, c.getBiomes()));
                        }
                    }
                }else {
                    if (null == (getCase(x, (1 + y)))) {
                        caseToCompare = getCase(x, (4 + y));
                        if ((caseToCompare.getBiomes()[0].equals(c.getBiomes()[0])) && ((1 == (caseToCompare.getBiomes().length)) && (null != caseToCompare))) {
                            casesToAdd.add(new fr.unice.polytech.qgl.qcc.database.Case(x, (1 + y), c.getBiomes()));
                            casesToAdd.add(new fr.unice.polytech.qgl.qcc.database.Case(x, (2 + y), c.getBiomes()));
                            casesToAdd.add(new fr.unice.polytech.qgl.qcc.database.Case(x, (3 + y), c.getBiomes()));
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
            if (i == (9 % n))
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


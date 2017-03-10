

package fr.unice.polytech.qgl.qcc.database.enums;


public enum Direction {
N("N"), S("S"), E("E"), W("W");
    private java.lang.String dir = "";

    Direction(java.lang.String dir) {
        fr.unice.polytech.qgl.qcc.database.enums.Direction.this.dir = dir;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return dir;
    }

    public fr.unice.polytech.qgl.qcc.database.enums.Direction clockwise() {
        fr.unice.polytech.qgl.qcc.database.enums.Direction direction;
        switch (fr.unice.polytech.qgl.qcc.database.enums.Direction.this) {
            case S :
                direction = fr.unice.polytech.qgl.qcc.database.enums.Direction.W;
                break;
            case N :
                direction = fr.unice.polytech.qgl.qcc.database.enums.Direction.E;
                break;
            case E :
                direction = fr.unice.polytech.qgl.qcc.database.enums.Direction.S;
                break;
            case W :
                direction = fr.unice.polytech.qgl.qcc.database.enums.Direction.N;
                break;
            default :
                direction = fr.unice.polytech.qgl.qcc.database.enums.Direction.N;
        }
        return direction;
    }

    public fr.unice.polytech.qgl.qcc.database.enums.Direction anticlockwise() {
        fr.unice.polytech.qgl.qcc.database.enums.Direction direction;
        switch (fr.unice.polytech.qgl.qcc.database.enums.Direction.this) {
            case S :
                direction = fr.unice.polytech.qgl.qcc.database.enums.Direction.E;
                break;
            case N :
                direction = fr.unice.polytech.qgl.qcc.database.enums.Direction.W;
                break;
            case E :
                direction = fr.unice.polytech.qgl.qcc.database.enums.Direction.N;
                break;
            case W :
                direction = fr.unice.polytech.qgl.qcc.database.enums.Direction.S;
                break;
            default :
                direction = fr.unice.polytech.qgl.qcc.database.enums.Direction.N;
        }
        return direction;
    }
}


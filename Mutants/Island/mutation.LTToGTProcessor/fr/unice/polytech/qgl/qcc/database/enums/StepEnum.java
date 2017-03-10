

package fr.unice.polytech.qgl.qcc.database.enums;


public enum StepEnum {
FINDCREEKS("CreeksFinder"), MOVEEXTRIMITYISLAND("MoveExtrimityIsland"), MOVETODIRECTIONOFISLAND("MoveToDirectionOfIsland"), LANDONISLAND("LandOnIsland"), EXPLOREISLAND("ExploreIsland");
    java.lang.String step;

    StepEnum(java.lang.String step) {
        fr.unice.polytech.qgl.qcc.database.enums.StepEnum.this.step = step;
    }

    public fr.unice.polytech.qgl.qcc.database.enums.StepEnum next() {
        switch (fr.unice.polytech.qgl.qcc.database.enums.StepEnum.this) {
            case MOVETODIRECTIONOFISLAND :
                return fr.unice.polytech.qgl.qcc.database.enums.StepEnum.MOVEEXTRIMITYISLAND;
            case MOVEEXTRIMITYISLAND :
                return fr.unice.polytech.qgl.qcc.database.enums.StepEnum.FINDCREEKS;
            case FINDCREEKS :
                return fr.unice.polytech.qgl.qcc.database.enums.StepEnum.LANDONISLAND;
            case LANDONISLAND :
                return fr.unice.polytech.qgl.qcc.database.enums.StepEnum.EXPLOREISLAND;
            default :
                return null;
        }
    }
}


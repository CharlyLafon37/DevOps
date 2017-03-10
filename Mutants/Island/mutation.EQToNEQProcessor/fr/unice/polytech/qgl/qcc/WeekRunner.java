

package fr.unice.polytech.qgl.qcc;


public class WeekRunner {
    private int budget;

    private int crew;

    private java.lang.String[] contract;

    private java.lang.String week;

    private java.lang.Long seed;

    private int x;

    private int y;

    private java.lang.String startingAt;

    public WeekRunner(java.lang.String[] contract, int budget, int crew, java.lang.String week, java.lang.Long seed, int x, int y, java.lang.String startingAt) {
        fr.unice.polytech.qgl.qcc.WeekRunner.this.contract = contract;
        fr.unice.polytech.qgl.qcc.WeekRunner.this.budget = budget;
        fr.unice.polytech.qgl.qcc.WeekRunner.this.crew = crew;
        fr.unice.polytech.qgl.qcc.WeekRunner.this.week = week;
        fr.unice.polytech.qgl.qcc.WeekRunner.this.seed = seed;
        fr.unice.polytech.qgl.qcc.WeekRunner.this.x = x;
        fr.unice.polytech.qgl.qcc.WeekRunner.this.y = y;
        fr.unice.polytech.qgl.qcc.WeekRunner.this.startingAt = startingAt;
    }

    public void run() {
        try {
            java.lang.String dirName = "D:\\Users\\Kevin\\Documents\\Cours\\Polytech SI3\\Polytech S5\\QGL\\outputs\\" + (week);
            java.io.File dir = new java.io.File(dirName);
            dir.mkdirs();
            java.io.File file = new java.io.File((("D:\\Users\\Kevin\\Documents\\Cours\\Polytech SI3\\Polytech S5\\QGL\\outputs\\" + (week)) + "\\result.txt"));
            java.io.PrintStream printStream = new java.io.PrintStream(file);
            java.lang.System.setOut(printStream);
            eu.ace_design.island.runner.Runner runner = eu.ace_design.island.runner.Runner.run(fr.unice.polytech.qgl.qcc.Explorer.class).exploring(new java.io.File((("D:\\Users\\Kevin\\Documents\\Cours\\Polytech SI3\\Polytech S5\\QGL\\maps\\" + (week)) + ".json"))).withSeed(seed).startingAt(x, y, startingAt).backBefore(budget).withCrew(crew);
            for (int i = 0; i < (contract.length);) {
                runner.collecting(java.lang.Integer.parseInt(contract[(i++)]), contract[(i++)]);
            }
            runner.storingInto(("D:\\Users\\Kevin\\Documents\\Cours\\Polytech SI3\\Polytech S5\\QGL\\outputs\\" + (week))).fire();
            printStream.close();
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}


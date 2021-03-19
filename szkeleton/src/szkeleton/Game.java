package szkeleton;

public class Game {
    private Settler[] settlers;
    private Robot[] robots;
    private Map map;

    public void Win() {}
    public void Lose() {}
    public void NewGame() {}
    public void AddRobot(Robot robot) {}
    public void SettlerDied(Settler settler) {}
    public void RobotDied(Robot robot) {}

    public Map GetMap(){return map;}
}

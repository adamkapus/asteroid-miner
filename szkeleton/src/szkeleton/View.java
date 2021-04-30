package szkeleton;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;

public class View extends JPanel {
	private SettlerView sv;
	private RobotView rv;
	private UfoView uv;
	private AsteroidView av;
	private TeleportView tgv;
	private Map<Place, List<Integer>> coordinates;
	private List<List<Integer>> testCoords;

	View(){
		sv = new SettlerView();
		rv = new RobotView();
		uv = new UfoView();
		av = new AsteroidView();
		tgv = new TeleportView();
	    coordinates = new HashMap<>();
	    testCoords = new ArrayList<>();
	    List<Integer> intList = new ArrayList<>();
	    intList.add(100); intList.add(100);
	    testCoords.add(intList);
	}

	public void drawAsteroid(int x, int y, Graphics g){
		g.fillOval(x, y, 30, 30);
		g.fillRect(x - 12, y - 20, 5, 5);
		g.fillRect(x - 4, y - 20, 5, 5);
		g.fillRect(x + 4, y - 20, 5, 5);
		g.fillRect(x + 12, y - 20, 5 ,5);
	}

	@Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(350, 0, 100, 100);
    }

    public SettlerView getSettlerView(){return sv;}
    public RobotView getRobotView(){return rv;}
    public UfoView getUfoView(){return uv;}
    public AsteroidView getAsteroidView(){return av;}
    public TeleportView getTeleportView(){return tgv;}
    public List<List<Integer>> getCoordinates(){return testCoords;}
}

package szkeleton;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class View extends JPanel {
	private SettlerView sv;
	private RobotView rv;
	private UfoView uv;
	private AsteroidView av;
	private TeleportView tgv;
	private Map<Place, List<Integer>> coordinates;
	//private List<List<Integer>> testCoords;
	private MainFrame mf;
	BufferedImage img;

	{
		try {
			img = ImageIO.read(new File("background.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	View(MainFrame frame){
		sv = new SettlerView();
		rv = new RobotView();
		uv = new UfoView();
		av = new AsteroidView();
		tgv = new TeleportView();
	    coordinates = new HashMap<>();
	    mf = frame;
	    //testCoords = new ArrayList<>();
	    //List<Integer> intList = new ArrayList<>();
	    //intList.add(100); intList.add(100);
	    //testCoords.add(intList);
	}

	public void drawAsteroid(int x, int y, Graphics g){
		g.setColor(Color.GRAY);
		g.fillOval(x, y, 30, 30);
		g.fillRect(x+1, y + 35, 5, 5);
		g.fillRect(x + 9, y + 35, 5, 5);
		g.fillRect(x + 17, y + 35, 5, 5);
		g.fillRect(x + 25, y + 35, 5 ,5);
	}

	public void drawTeleport(int x, int y, Graphics g){
		g.setColor(Color.MAGENTA);
		g.fillOval(x, y, 30, 30);
		g.setColor(Color.GRAY);
		g.fillRect(x + 1, y + 35, 5, 5);
		g.fillRect(x + 9, y + 35, 5, 5);
		g.fillRect(x + 17, y + 35, 5, 5);
		g.fillRect(x + 25, y + 35, 5 ,5);
	}

	private void drawInfo(int x, int y, Graphics g){
		g.setColor(Color.YELLOW);
		g.fillOval(x + 200, y-12, 5, 5);
		g.setColor(Color.BLACK);
		g.setFont(new Font("res", Font.BOLD, 10));
		g.drawString("iron", x+210, y - 5);

		g.setColor(Color.PINK);
		g.fillOval(x + 200, y-3, 5, 5);
		g.setColor(Color.BLACK);
		g.setFont(new Font("res", Font.BOLD, 10));
		g.drawString("coal", x+210, y + 4);

		g.setColor(Color.CYAN);
		g.fillOval(x + 200, y+6, 5, 5);
		g.setColor(Color.BLACK);
		g.setFont(new Font("res", Font.BOLD, 10));
		g.drawString("icewater", x+210, y + 13);

		g.setColor(Color.ORANGE);
		g.fillOval(x+200, y+15, 5, 5);
		g.setColor(Color.BLACK);
		g.setFont(new Font("res", Font.BOLD, 10));
		g.drawString("uran", x+210, y + 21);
	}

	/**
	 * *coal: 10
	 * *icewater: 11
	 * *iron: 12
	 * *uran: 13
	 */
	private void drawInventoryResources(int x, int y, Settler s, Graphics g){
		//draw resources
		g.setColor(Color.BLACK);
		g.fillRect(x-1, y-1, 130, 12);
		ArrayList<Integer> resources = sv.getResource(s);
		int itemCounter = 0;
		if(resources != null) {
			for (Integer r : resources) {
				if (r == 10) {
					g.setColor(Color.PINK);
				} else if (r == 11) {
					g.setColor(Color.CYAN);
				} else if (r == 12) {
					g.setColor(Color.YELLOW);
				} else if (r == 13) {
					g.setColor(Color.ORANGE);
				}
				g.fillRect(x + itemCounter * 13, y, 10, 10);
				itemCounter++;
			}
		}
		//ha nincs tele az inventory, kellenek szürke négyzetek
		if (itemCounter < 10){
			g.setColor(Color.GRAY);
			int emptySlots = 10- itemCounter;
			for(int i = 0; i < emptySlots; i++){
				g.fillRect(x+itemCounter * 13, y, 10, 10);
				itemCounter++;
			}
		}
	}

	private void drawInventoryGates(int x, int y, Settler s, Graphics g){
		//draw gates
		g.setColor(Color.BLACK);
		g.fillRect(x-1, y+13, 39, 12);
		int itemCounter = 0;
		int gatesNum;
		if(sv.getGates(s) != null)
			gatesNum = sv.getGates(s).size();
		else
			gatesNum =0;
		while (itemCounter < gatesNum){
			g.setColor(Color.MAGENTA);
			g.fillRect(x+itemCounter*13, y+14, 10, 10);
			itemCounter++;
		}

		//ha nincs tele az inventory, kellenek szürke négyzetek
		if (itemCounter < 3){
			g.setColor(Color.GRAY);
			int emptySlots = 3 - itemCounter;
			for(int i = 0; i < emptySlots; i++){
				g.fillRect(x+itemCounter*13, y+14, 10, 10);
				itemCounter++;
			}
		}
	}

	public void drawInventory(int x, int y, Settler s, Graphics g){
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 35, 1000, 60);
		g.setColor(Color.BLACK);
		g.setFont(new Font("title", Font.BOLD, 14));
		g.drawString("Resources", x-1, y-6);

		drawInfo(x, y, g);
		drawInventoryResources(x, y, s, g);
		drawInventoryGates(x, y, s, g);
	}

	@Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        GenCoordinates();
		g.drawImage(img, 0, 0, null);
        //g.setColor(Color.RED);
        //g.fillRect(350, 0, 100, 100);
		drawAsteroid(200, 200, g);
		drawTeleport(400, 200, g);

		//invetory tesztelése
		//Settler s = new Settler();
		//sv.updateSettler(s);
		//drawInventory(50, 100, s, g);

		Settler s = mf.getGame().getCurrentSettler();
		drawInventory(40, 60, s, g);
    }

    private void GenCoordinates(){
		for (var entry : av.getNeighbours().entrySet()){
			if (!coordinates.containsKey(entry.getKey())){
				Random rand = new Random();
				boolean correct = false;
				while (!correct) {
					int x = rand.nextInt(770);
					int y = rand.nextInt(560);
					if (isCoordinatesCorrect(x, y)) {
						List<Integer> currentCoords = new ArrayList<>();
						currentCoords.add(x);
						currentCoords.add(y);
						coordinates.put(entry.getKey(), currentCoords);
						correct = true;
						System.out.println(x + " " + y);
					}
				}
			}
		}
	}

	private boolean isCoordinatesCorrect(int x, int y){
		for (var entry : coordinates.entrySet()){
			int xi = entry.getValue().get(0);
			int yi = entry.getValue().get(1);
			if (x - xi < 30 && x - xi > 0 && y - yi < 40 && y - yi > 0)
				return false;
			if (xi - x < 30 && xi - x > 0 && yi - y < 40 && yi - y > 0)
				return false;
		}
		return true;
	}

    public SettlerView getSettlerView(){return sv;}
    public RobotView getRobotView(){return rv;}
    public UfoView getUfoView(){return uv;}
    public AsteroidView getAsteroidView(){return av;}
    public TeleportView getTeleportView(){return tgv;}
    public Map<Place, List<Integer>> getCoordinates(){return coordinates;}
}

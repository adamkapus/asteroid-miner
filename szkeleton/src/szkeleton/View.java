package szkeleton;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

public class View extends JPanel {
	private SettlerView sv;
	private RobotView rv;
	private UfoView uv;
	private AsteroidView av;
	private TeleportView tgv;
	private Map<Place, List<Integer>> coordinates;
	private Map<Place, Integer> fifoState;
	
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
	    fifoState = new HashMap<>();
	    mf = frame;
	    //testCoords = new ArrayList<>();
	    //List<Integer> intList = new ArrayList<>();
	    //intList.add(100); intList.add(100);
	    //testCoords.add(intList);
	}


	public void drawAsteroid(Graphics g, Asteroid a){
		int x = coordinates.get(a).get(0);
		int y = coordinates.get(a).get(1);

		if(mf.getGame().getCurrentSettler().GetPlace() == a){
			g.setColor(Color.GREEN);
			g.fillOval(x-2, y-2, 34, 34);
		}

		g.setColor(Color.GRAY);
		g.fillOval(x, y, 30, 30);
		int count = 1;
		for(int i = 0; i < 4; i++){
			g.fillRect(x + count, y + 35, 5, 5);
			count += 8;
		}


		g.setColor(Color.BLACK);
		Integer layers = av.getLayers().get(a);
		if(layers > 0) { //Kiirom a retegek szamat
			g.setFont(new Font("layer", Font.BOLD, 12));
			g.drawString(layers.toString(), x + 13, y + 20);
		} else { // Kiszinezem az aszteroidat
			int nyersanyag = av.getResource().get(a); //= av.getResource().get(a); // = get a nyersanyagnak a szama
			switch (nyersanyag) {
				case 10 /*colal*/:
					g.setColor(Color.PINK);
					break;
				case 11 /*ivewater*/ :
					g.setColor(Color.BLUE);
					break;
				case 12 : // iron
					g.setColor(Color.YELLOW);
					break;
				case 13 : //Uran
					g.setColor(Color.ORANGE);
					break;
				case 100 : //�RES
					g.setColor(Color.BLACK);
					break;
			}
			g.fillOval(x+5, y+5, 20, 20);
		}

	}

	public void drawSettler(Graphics g, Settler s) {
		Place place = sv.getPlace(s);
		g.setColor(Color.GREEN);
		g.fillOval(coordinates.get(place).get(0), coordinates.get(place).get(1), 31, 31);
		ArrayList<Place> neighbours = av.getNeighbours().get(place);
		for(Place p : neighbours) {
			g.setColor(Color.CYAN);
			g.fillOval(coordinates.get(p).get(0), coordinates.get(p).get(1), 31, 31);
		}
	}

	public void drawTeleport(Graphics g, TeleportGate tg) {
		g.setColor(Color.MAGENTA);
		int x = coordinates.get(tg).get(0);
		int y = coordinates.get(tg).get(1);
		g.fillOval(x, y, 30, 30);
		g.setColor(Color.GRAY);

		int count = 1;
		for(int i = 0; i < 4; i++){
			g.fillRect(x + count, y + 35, 5, 5);
			count += 8;
		}
	}

	public void drawTeleport(int x, int y, Graphics g){
		g.setColor(Color.MAGENTA);
		g.fillOval(x, y, 30, 30);
		g.setColor(Color.GRAY);

		int count = 1;
		for(int i = 0; i < 4; i++){
			g.fillRect(x + count, y + 35, 5, 5);
			count += 8;
		}
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
		g.fillRect(0, 35, 1000, 63);
		g.setColor(Color.BLACK);
		g.setFont(new Font("title", Font.BOLD, 14));
		g.drawString("Resources", x-1, y-6);

		drawInfo(x, y, g);
		drawInventoryResources(x, y, s, g);
		drawInventoryGates(x, y, s, g);
	}

	public void drawNeighbourLines(Graphics g, ArrayList<Place> places) {
		Settler current = mf.getGame().getCurrentSettler();

		for (int i = 0; i < places.size(); i++) {
			g.setColor(Color.RED);
			for (int j = 0; j < places.get(i).GetAllNeighbors().size(); j++) {
				int x1 = coordinates.get(places.get(i)).get(0) + 13;
				int y1 = coordinates.get(places.get(i)).get(1) + 20;
				int x2 = coordinates.get(places.get(i).GetAllNeighbors().get(j)).get(0) + 13;
				int y2 = coordinates.get(places.get(i).GetAllNeighbors().get(j)).get(1) + 20;
				g.drawLine(x1, y1, x2, y2);
			}
		}

		for (int i = 0; i < places.size(); i++){
			if (current.GetPlace() == places.get(i)) {
				g.setColor(Color.GREEN);
				for (int j = 0; j < places.get(i).GetAllNeighbors().size(); j++) {
					int x1 = coordinates.get(places.get(i)).get(0) + 13;
					int y1 = coordinates.get(places.get(i)).get(1) + 20;
					int x2 = coordinates.get(places.get(i).GetAllNeighbors().get(j)).get(0) + 13;
					int y2 = coordinates.get(places.get(i).GetAllNeighbors().get(j)).get(1) + 20;
					g.drawLine(x1, y1, x2, y2);
				}
			}
		}
	}
	
	public void drawSinglePlaceFIFO(Graphics g, Place p, Color c) {
		int FIFOcount = fifoState.get(p);
		if(FIFOcount >=4) {
			return;
		}
		
		int x = coordinates.get(p).get(0);
		int y = coordinates.get(p).get(1);


		g.setColor(c);
		
		int count = 1 + (FIFOcount)*8;
		g.fillRect(x + count, y + 35, 5, 5);
		FIFOcount++;
		fifoState.replace(p, FIFOcount);
		
	}
	
	public void drawPlaceFIFOs(Graphics g) {
		for (var entry : sv.getPlaceMap().entrySet()){
			Settler s = entry.getKey();
			Place p = sv.getPlaceMap().get(s);
			drawSinglePlaceFIFO(g,p, Color.GREEN);
		}
		for (var entry : rv.getPlace().entrySet()){
			Robot s = entry.getKey();
			Place p = rv.getPlace().get(s);
			drawSinglePlaceFIFO(g,p, Color.gray);
		}
		for (var entry : uv.getPlace().entrySet()){
			Ufo s = entry.getKey();
			Place p = uv.getPlace().get(s);
			drawSinglePlaceFIFO(g,p, Color.WHITE);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        GenCoordinates();
		g.drawImage(img, 0, 50, null);
        //g.setColor(Color.RED);
        //g.fillRect(350, 0, 100, 100);
		//drawAsteroid(200, 200, g); Errort dobott, mert nem kapta meg az asteroidat, de majd iteratorral ugyis vegig kell menni
		//drawTeleport(400, 200, g);

		//invetory tesztelése
		//Settler s = new Settler();
		//sv.updateSettler(s);
		//drawInventory(50, 100, s, g);
		ArrayList<Place> places = new ArrayList<Place>();
		
		ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
		for (var entry : av.getResource().entrySet()){
			Asteroid a =  entry.getKey();
			asteroids.add(a);
			places.add((Place)a);
		}
		ArrayList<TeleportGate> teleportgates = new ArrayList<TeleportGate>();
		for (var entry : tgv.getNeighbours().entrySet()){
			TeleportGate tg =  entry.getKey();
			teleportgates.add(tg);
			places.add((Place)tg);
		}
		drawNeighbourLines(g, places);
		for(Asteroid a : asteroids){
			//Be�rjuk hogy minden helyen 0-s szinten van a FIFO
			fifoState.put((Place)a, 0);
			drawAsteroid(g, (Asteroid) a);
		}
		for(TeleportGate tg : teleportgates){
			//Be�rjuk hogy minden helyen 0-s szinten van a FIFO
			fifoState.put((Place)tg, 0);
			drawTeleport(g, tg);
		}
		Settler s = mf.getGame().getCurrentSettler();
		drawInventory(40, 70, s, g);
		
		//kirajzoljuk a helyek alatti fifot. egyelore settlernek
		drawPlaceFIFOs(g);
    }

    private void GenCoordinates(){
    	ArrayList<Place> places = new ArrayList<Place>();
		for (var entry : av.getResource().entrySet()){
			Asteroid a =  entry.getKey();
			places.add((Place)a);
		}
		for (var entry : tgv.getNeighbours().entrySet()){
			TeleportGate tg =  entry.getKey();
			places.add((Place)tg);
		}
		for (int i =0; i < places.size(); i++){
			if (!coordinates.containsKey(places.get(i))){
				Random rand = new Random();
				boolean correct = false;
				while (!correct) {
					int x = rand.nextInt(mf.width - 70);
					int y = 100 + rand.nextInt(mf.height - 90 - 300);
					if (isCoordinatesCorrect(x, y)) {
						List<Integer> currentCoords = new ArrayList<>();
						currentCoords.add(x);
						currentCoords.add(y);
						coordinates.put(places.get(i), currentCoords);
						correct = true;
						System.out.println(x + " " + y);
					}
				}
			}
		}
	}

	private boolean isCoordinatesCorrect(int x, int y) {
		for (var entry : coordinates.entrySet()) {
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

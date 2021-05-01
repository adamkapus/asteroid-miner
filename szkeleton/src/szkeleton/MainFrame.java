package szkeleton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	private View view;
	private JComboBox<Object> testSettlerNumber;
	private Game g;

	private JButton mineButton;
	private JButton drillButton;
	private JButton placeResourceButton;
	private JButton placeTeleportButton;

	MainFrame(){
		super("Urjatek");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        initComponents();
        g = new Game(this);
        g.NewGame();
        Thread t = new Thread(g);
        t.start();
	}
	
	private void initComponents(){
		view = new View(this);
		JButton actionButton = new JButton("Action");
		mineButton = new JButton("Mine");
		drillButton = new JButton("Drill");
		placeResourceButton = new JButton("Place Resource");
		placeTeleportButton = new JButton("Place Teleport");
		JButton buildTeleportButton = new JButton("Build Teleport");
		JButton buildRobotButton = new JButton("Build Robot");
		mineButton.setEnabled(false);
		drillButton.setEnabled(false);
		placeResourceButton.setEnabled(false);
		placeTeleportButton.setEnabled(false);

		actionButton.setActionCommand("Action");
		mineButton.setActionCommand("Mine");
		drillButton.setActionCommand("Drill");
		placeResourceButton.setActionCommand("Place Resource");
		placeTeleportButton.setActionCommand("Place Teleport");
		buildTeleportButton.setActionCommand("Build Teleport");
		buildRobotButton.setActionCommand("Build Robot");

		actionButton.addActionListener(e -> {
			g.getCurrentSettler().Action();
		});
		mineButton.addActionListener(e -> {
			g.getCurrentSettler().Mine();
		});
		drillButton.addActionListener(e -> {
			g.getCurrentSettler().Drill();
		});
		placeResourceButton.addActionListener(e -> {
			System.out.println(g.getCurrentSettler().getName());
		});
		placeTeleportButton.addActionListener(e -> {
			g.getCurrentSettler().PlaceDownTeleport();
		});
		buildTeleportButton.addActionListener(e -> {
			g.getCurrentSettler().BuildTeleport("tg1", "tg2");
		});
		buildRobotButton.addActionListener(e -> {
			g.getCurrentSettler().BuildRobot("r");
		});

		view.add(actionButton);
		view.add(mineButton);
		view.add(drillButton);
		view.add(placeResourceButton);
		view.add(placeTeleportButton);
		view.add(buildTeleportButton);
		view.add(buildRobotButton);

		inittestComboBox();
		view.add(testSettlerNumber);
		view.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(e.getX() + " " + e.getY());
				Map<Place, List<Integer>> coordinates = view.getCoordinates();
				for (var entry : coordinates.entrySet()) {
					int x = entry.getValue().get(0);
					int y = entry.getValue().get(1);
					if (Math.abs(e.getX() - (x + 15)) <= 10 && Math.abs(e.getY() - (y + 15)) <= 10){
						g.getCurrentSettler().Move(entry.getKey().GetPlaceID());
						disableAsteroidActionButtons();
						break;
					}
				}
			}
			@Override
			public void mousePressed(MouseEvent e) { }
			@Override
			public void mouseReleased(MouseEvent e) { }
			@Override
			public void mouseEntered(MouseEvent e) { }
			@Override
			public void mouseExited(MouseEvent e) { }
		});
		this.add(view, BorderLayout.CENTER);
		
	}
	
	//inicializalas comboboxhoz
	private void inittestComboBox() {
		Object[] numberOfSettlers = new Object[4];
		for(int i = 0; i <= 3; i++) {
			numberOfSettlers[i] = (int) (i + 2);
		}
		testSettlerNumber = new JComboBox<Object>(numberOfSettlers);
		testSettlerNumber.setSelectedItem(2);	
	}

	public void activateAsteroidActionButtons(){
		mineButton.setEnabled(true);
		drillButton.setEnabled(true);
		placeResourceButton.setEnabled(true);
		placeTeleportButton.setEnabled(true);
	}

	public void disableAsteroidActionButtons(){
		mineButton.setEnabled(false);
		drillButton.setEnabled(false);
		placeResourceButton.setEnabled(false);
		placeTeleportButton.setEnabled(false);
	}

	public View getView() {
		return view;
	}
	public Game getGame() {return g;}
}

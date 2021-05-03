package szkeleton;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Map;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements MenuListener, ActionListener {
	private View view;

	private Game g;

	private JPanel  topPanel;
	private JButton mineButton;
	private JButton drillButton;
	private JButton placeResourceButton;
	private JButton placeTeleportButton;
	JMenuBar menuBar;
	JMenu newGame, exit, howTo;
	JMenuItem p2, p3, p4, p5;
	int width=800;
	int height=800;
	int panelHeight=50;

	private boolean placingResource = false;

	MainFrame(){
		super("Urjatek");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(width, height));

		JLabel background;
		ImageIcon img= new ImageIcon("back.png");
		background= new JLabel("", img, JLabel.CENTER);
		background.setBounds(0,0, height, width);
		add(background);
		this.setVisible(true);


		//Menü
		menuBar=new JMenuBar();

		newGame= new JMenu("New Game");
		newGame.addMenuListener(this);
		menuBar.add(newGame);

		howTo = new JMenu("How to play");
		howTo.addMenuListener(this);
		menuBar.add(howTo);

		exit = new JMenu("Exit");
		exit.addMenuListener(this);
		menuBar.add(exit);
		this.setJMenuBar(menuBar);

		//submenu items
		p2 = new JMenuItem("2 Settlers");
		p2.addActionListener( this);
		newGame.add(p2);

		p3 = new JMenuItem("3 Settlers");
		p3.addActionListener(this);
		newGame.add(p3);

		p4 = new JMenuItem("4 Settlers");
		p4.addActionListener(this);
		newGame.add(p4);

		p5 = new JMenuItem("5 Settlers");
		p5.addActionListener(this);
		newGame.add(p5);


        initComponents();
        g = new Game(this);
        g.NewGame();
        Thread t = new Thread(g);
        t.start();
	}
	
	private void initComponents(){
		//View(panel) kozepen
		view = new View(this);
		view.setLayout(null);

		this.setContentPane(view);



		topPanel= new JPanel();
		topPanel.setBounds(0,0,width, panelHeight);
		topPanel.setBackground(Color.gray);
		view.add(topPanel);




		//JButton jb = new JButton("test, viewban");
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
			placingResource = !placingResource;
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

		topPanel.add(actionButton);
        topPanel.add(mineButton);
        topPanel.add(drillButton);
        topPanel.add(placeResourceButton);
        topPanel.add(placeTeleportButton);
        topPanel.add(buildTeleportButton);
        topPanel.add(buildRobotButton);



		topPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(e.getX() + " " + e.getY());
				if (!placingResource) {
					Map<Place, List<Integer>> coordinates = view.getCoordinates();
					for (var entry : coordinates.entrySet()) {
						int x = entry.getValue().get(0);
						int y = entry.getValue().get(1);
						if (Math.abs(e.getX() - (x + 15)) <= 10 && Math.abs(e.getY() - (y + 15)) <= 10) {
							g.getCurrentSettler().Move(entry.getKey().GetPlaceID());
							System.out.println("Place found");
							disableAsteroidActionButtons();
							break;
						}
					}
				}
				else {
					// nyersanyaglerakás majd ha végleges lesz a design
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
		//this.add(view, BorderLayout.CENTER);
        this.setVisible(true);
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

	@Override
	public void menuSelected(MenuEvent me) {
		if(me.getSource().equals(exit)) {System.exit(0);}
		if(me.getSource().equals(howTo)) {
			final JFrame instr = new JFrame("Instructions");
			instr.setSize(1000,800);

			// Text for game instructions
			final JLabel text = new JLabel(
					"<html>"
							+ "<b>SZABALYOK</b><br><br>"
							+ "<b>Overview:</b><br>"
							+ "fdgfhjkléá<br>"
							+ " sdfxcgvhhbnjm,.-<br>"
							+ "trgaegrg<br><br>"
							+ "<b>nreat</b><br>"
							+ "<ol>"
							+ "<li>atnaer </li>"
							+ "<li>dfgfghj<br></li> "
							+ "</ol><br><br>"
							+ "<b>Additional Settings:</b><br>"
							+ "<ul>"
							+ "<li>xcvbnm<br></li>"
							+ "</ul><br>"
							+ "</html>"
			);
			instr.add(text);
			instr.setVisible(true);

		}
		if(me.getSource().equals(newGame)){}
	}

	@Override
	public void menuDeselected(MenuEvent e) {

	}

	@Override
	public void menuCanceled(MenuEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(p2)){
			g.NewGame(2);
		}
		if(e.getSource().equals(p3)){
			g.NewGame(3);
		}
		if(e.getSource().equals(p4)){
			g.NewGame(4);
		}
		if(e.getSource().equals(p5)){
			g.NewGame(5);
		}
	}

	/*	private class TestActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getActionCommand().equals("test")) {
				 System.out.println("Gomb figyelt");
			}
		}
	} */

	private static class ActionActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getActionCommand().equals("Action")) {
				System.out.println("Action Gomb megnyomva");
			}
		}
	}

	private static class MoveActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getActionCommand().equals("Move")) {
				System.out.println("Move Gomb megnyomva");
			}
		}
	}

	private static class DrillActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getActionCommand().equals("Drill")) {
				System.out.println("Drill Gomb megnyomva");
			}
		}
	}

	private static class MineActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getActionCommand().equals("Mine")) {
				System.out.println("Mine Gomb megnyomva");
			}
		}
	}

	private static class BuildRobotActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getActionCommand().equals("Build Robot")) {
				System.out.println("Build Robot Gomb megnyomva");
			}
		}
	}

	private static class BuildTeleportActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getActionCommand().equals("Build Teleport")) {
				System.out.println("Build Teleport Gomb megnyomva");
			}
		}
	}

	private static class PlaceTeleportActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getActionCommand().equals("Place Teleport")) {
				System.out.println("Place Teleport Gomb megnyomva");
			}
		}
	}

	private static class PlaceResourceActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getActionCommand().equals("Place Resource")) {
				System.out.println("Place Resource Gomb megnyomva");
			}
		}
	}

	private static class TeleportActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getActionCommand().equals("Teleport")) {
				System.out.println("Teleport Gomb megnyomva");
			}
		}
	}



	public Game getGame() {return g;}
}

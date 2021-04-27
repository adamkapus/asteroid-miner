package szkeleton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	private View view;
	private JComboBox<Object> testSettlerNumber;

	MainFrame(){
		super("Urjatek");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        initComponents();
	}
	
	private void initComponents(){
		//View(panel) kozepen
		view = new View();
		JButton jb = new JButton("test, viewban");
		JButton actionButton = new JButton("Action");
		JButton mineButton = new JButton("Mine");
		JButton drillButton = new JButton("Drill");
		JButton placeResourceButton = new JButton("Place Resource");
		JButton placeTeleportButton = new JButton("Place Teleport");
		JButton teleportButton = new JButton("Teleport");
		JButton buildTeleportButton = new JButton("Build Teleport");
		JButton buildRobotButton = new JButton("Build Robot");
		JButton moveButton = new JButton("Move");

		jb.setActionCommand("test");
		actionButton.setActionCommand("Action");
		mineButton.setActionCommand("Mine");
		drillButton.setActionCommand("Drill");
		placeResourceButton.setActionCommand("Place Resource");
		placeTeleportButton.setActionCommand("Place Teleport");
		teleportButton.setActionCommand("Teleport");
		buildTeleportButton.setActionCommand("Build Teleport");
		buildRobotButton.setActionCommand("Build Robot");
		moveButton.setActionCommand("Move");

		actionButton.addActionListener(new ActionActionListener());
		mineButton.addActionListener(new MineActionListener());
		drillButton.addActionListener(new DrillActionListener());
		placeResourceButton.addActionListener(new PlaceResourceActionListener());
		placeTeleportButton.addActionListener(new PlaceTeleportActionListener());
		teleportButton.addActionListener(new TeleportActionListener());
		buildTeleportButton.addActionListener(new BuildTeleportActionListener());
		buildRobotButton.addActionListener(new BuildRobotActionListener());
		moveButton.addActionListener(new MoveActionListener());

		/*TestActionListener ta = new TestActionListener();
		jb.addActionListener(ta); */
		view.add(jb);
		view.add(actionButton);
		view.add(mineButton);
		view.add(drillButton);
		view.add(placeResourceButton);
		view.add(placeTeleportButton);
		view.add(teleportButton);
		view.add(buildTeleportButton);
		view.add(buildRobotButton);
		view.add(moveButton);

		inittestComboBox();
		view.add(testSettlerNumber);
		view.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(e.getX() + " " + e.getY());
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
		
		
		//tesztcellal egy jcombox egy panelban legfelul
		/*JPanel testPanel = new JPanel();
		inittestComboBox();
		testPanel.add(testSettlerNumber);
		JButton jb2 = new JButton("test, fels� jpanel");
		testPanel.add(jb2);
		this.add(testPanel, BorderLayout.NORTH);*/
		
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
}

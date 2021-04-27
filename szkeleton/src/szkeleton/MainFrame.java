package szkeleton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		TestActionListener ta = new TestActionListener();
		jb.addActionListener(ta);
		view.add(jb);
		inittestComboBox();
		view.add(testSettlerNumber);
		this.add(view, BorderLayout.CENTER);
		
		
		//tesztcellal egy jcombox egy panelban legfelul
		/*JPanel testPanel = new JPanel();
		inittestComboBox();
		testPanel.add(testSettlerNumber);
		JButton jb2 = new JButton("test, felsõ jpanel");
		testPanel.add(jb2);
		this.add(testPanel, BorderLayout.NORTH);*/
		
	}
	
	//inicializalas comboboxhoz
	private void inittestComboBox() {
		Object numberOfSettlers[] = new Object[4];
		for(int i = 0; i <= 3; i++) {
			numberOfSettlers[i] = (int) (i + 2);
		}
		testSettlerNumber = new JComboBox<Object>(numberOfSettlers);
		testSettlerNumber.setSelectedItem(2);	
	}

	private class TestActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getActionCommand().equals("test")) {
				 System.out.println("Gomb figyelt");
			}
		}

	}
}

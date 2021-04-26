package szkeleton;

import java.awt.BorderLayout;
import java.awt.Dimension;

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
		view.add(jb);
		this.add(view, BorderLayout.CENTER);
		
		
		//tesztcellal egy jcombox egy panelban legfelul
		JPanel testPanel = new JPanel();
		inittestComboBox();
		testPanel.add(testSettlerNumber);
		JButton jb2 = new JButton("test, felsõ jpanel");
		testPanel.add(jb2);
		this.add(testPanel, BorderLayout.NORTH);
		
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
}

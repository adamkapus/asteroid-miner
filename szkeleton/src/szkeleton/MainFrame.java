package szkeleton;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private View view;
	MainFrame(){
		super("Urjatek");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        initComponents();
	}
	
	private void initComponents(){
		view = new View();
	}
}

package szkeleton;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	MainFrame(){
		super("Urjatek");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1210, 765));
        initComponents();
	}
	
	private void initComponents(){
		
	}
}

package szkeleton;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class View extends JPanel {
	private SettlerView sv;
	private RobotView rv;
	private UfoView uv;
	private AsteroidView av;
	private TeleportView tgv;
	public void reDraw() {
	
	}

	View(){
	
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(350, 0, 100, 100);
    }
}

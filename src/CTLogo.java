import javax.swing.JPanel;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Graphics;


public class CTLogo extends JPanel {
	private static final long serialVersionUID = 1L; //Eclipse suggested this
	BufferedImage image;
	
	public CTLogo() {
		
		try {
			image = ImageIO.read(new File("images/ct_logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Dimension getPreferredSize()
	{
	    return (new Dimension(147, 85));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);		
	}
}

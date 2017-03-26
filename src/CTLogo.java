import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Graphics;


public class CTLogo extends JPanel {
	private static final long serialVersionUID = 1L; //Eclipse suggested this
	//BufferedImage image;
	ImageIcon image;
	
	public CTLogo() {
		
		/*
		try {
			String pathToLogo = "CleverTech Handbook/resources/images/ct_logo.png";
			//image = ImageIO.read(new File("images/ct_logo.png"));
			ImageIcon
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/

		String pathToLogo = "images/ct_logo.png"; //images is a directory that must also be in the src directory
		image = new ImageIcon(getClass().getClassLoader().getResource(pathToLogo));
	}
	
	public Dimension getPreferredSize()
	{
	    return (new Dimension(147, 85));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawImage(image, 0, 0, null);
		image.paintIcon(this, g, 0, 0);
	}
}

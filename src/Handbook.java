import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import net.miginfocom.swing.MigLayout;


public class Handbook {
	
	JFrame frame = new JFrame();
	JPanel mainMenuScreen = new JPanel();
	JPanel btnArea = new JPanel(); //put all your buttons in this JPanel
	JPanel logo = new CTLogo(); //For the CleverTech logo
	JLabel opManualTxt = new JLabel("Operations Manual");
	
	//Main Menu Buttons:
	JButton newRepDiaBtn = new JButton("DIALOGUE FOR NEW REPIAR");
	JButton pickUpDiaBtn = new JButton("DIALOGUE FOR PICK UP");
	JButton apprDiaBtn = new JButton("DIALOGUE FOR APPROVAL");
	JButton repComDiaBtn = new JButton("DIALOGUE REPAIR COMPLETE");
	JButton walkinWOBtn= new JButton("WALK-IN WORK ORDER");
	JButton cmpnyWOBtn = new JButton("COMPANY WORK ORDER");
	JButton dOSigBtn = new JButton("DROP-OFF SIGNATURE");
	JButton pUSigBtn = new JButton("PICK-UP SIGNATURE");
	JButton workCmplBtn = new JButton("WORK COMPLETED TEXT");
	JButton cusUpdateBtn = new JButton("CUSTOMER UPDATE TEXT");
	JButton cmplRprBtn = new JButton("COMPLETE REPAIR TEXT");

	
	
	public Handbook() {
		// === MAIN MENU SCREEN CONFIGURATIONS ===
		//mainMenuScreen.setBackground(Color.decode("0x121E31")); //for debugging
		mainMenuScreen.setBackground(Color.WHITE);
		
		// ##### Layout with BoxLayouts #####
		//mainMenuScreen.setLayout(new BoxLayout(mainMenuScreen, BoxLayout.PAGE_AXIS));
		
		// ##### Layout with MiGLayout #####
		//mainMenuScreen.setLayout(new MigLayout());
		
		// ##### Layout with GridBagLayout #####
		mainMenuScreen.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		//gbc.insets = new Insets(10, 10, 10, 10);
		gbc.insets = new Insets(6, 6, 6, 6);
		
		
		// === CLEVERTECH LOGO CONFIGURATIONS ===
		logo.setBackground(Color.WHITE);
		gbc.gridx = 0; gbc.gridy = 0;
		/*
		//Turns out these weight and anchor attributes don't need to be tweaked if I
		//add the logo, label, and button set one after another...
		gbc.weightx = 1; //Determines spacing with respect to columns. Higher value == less space?
		gbc.anchor = GridBagConstraints.NORTH; //Do I need this?
		*/
		mainMenuScreen.add(logo, gbc);
		
		
		
		// === OPERATIONS MANUAL TEXT CONFIGURATIONS===
		//opManualTxt.setFont(font);
		gbc.gridx = 0; gbc.gridy = 1;
		/*
		//Turns out these weight and anchor attributes don't need to be tweaked if I
		//add the logo, label, and button set one after another...
		gbc.weighty = 1; //Determines spacing with respect to rows
		gbc.anchor = GridBagConstraints.NORTH; //Don't think I need this...
		*/
		opManualTxt.setFont(new Font("Impact", Font.PLAIN, 25));
		opManualTxt.setForeground(Color.decode("0x8FC967"));
		//opManualTxt.setOpaque(true); opManualTxt.setBackground(Color.RED); //for debugging
		mainMenuScreen.add(opManualTxt, gbc);
		
		
		
		// === BUTTON SET CONFIGURATIONS ===
		btnArea.setLayout(new MigLayout());
		gbc.gridx = 0; gbc.gridy = 2;
		/*
		//Turns out these weight and anchor attributes don't need to be tweaked if I
		//add the logo, label, and button set one after another...
		gbc.weighty = 10; //Don't think I need this...
		gbc.anchor = GridBagConstraints.NORTH; //Don't think I need this...
		*/
		btnArea.setPreferredSize(new Dimension(700, 300)); //HARD-CODED DIMENSIONS
		btnArea.setBackground(Color.decode("0x1C6959"));
		mainMenuScreen.add(btnArea, gbc);
		
		
		//Start adding buttons inside btnArea...
		
		
		// === JFRAME CONFIGRATIONS ===
		frame.setResizable(false); //Resizing destroys the layout. Disable for now.
	    frame.getContentPane().setPreferredSize(new Dimension(900, 500)); //HARD-CODED DIMENSIONS
		frame.add(mainMenuScreen);
		
		//Boiler-plate code:
		frame.pack();
		//Be sure to write the following two lines AFTER you've packed the frame. Writing them before packing
		//doesn't center the GUI correctly!
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
	
}

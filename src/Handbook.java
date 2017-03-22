import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.miginfocom.swing.MigLayout;


public class Handbook {
	
	private JFrame frame = new JFrame();
	private JPanel mainMenuScreen = new JPanel();
	private JPanel btnArea = new JPanel(); //put all your buttons in this JPanel
	private JPanel logo = new CTLogo(); //For the CleverTech logo
	private JLabel opManualTxt = new JLabel("Operations Manual");
	
	//Main Menu Buttons:
	private JButton newRepDiaBtn = new JButton("DIALOGUE FOR NEW REPAIR");
	private JButton walkinWOBtn= new JButton("WALK-IN WORK ORDER");
	private JButton workCmplBtn = new JButton("WORK COMPLETED TEXT");
	private JButton pickUpDiaBtn = new JButton("DIALOGUE FOR PICK UP");
	private JButton cmpnyWOBtn = new JButton("COMPANY WORK ORDER");
	private JButton cusUpdateBtn = new JButton("CUSTOMER UPDATE TEXT");
	private JButton apprDiaBtn = new JButton("DIALOGUE FOR APPROVAL");
	private JButton dOSigBtn = new JButton("DROP-OFF SIGNATURE");
	private JButton cmplRprBtn = new JButton("COMPLETE REPAIR TEXT");
	private JButton repComDiaBtn = new JButton("DIALOGUE REPAIR COMPLETE");
	private JButton pUSigBtn = new JButton("PICK-UP SIGNATURE");
	private List<JButton> mainMenuBtns = new ArrayList<JButton>(Arrays.asList(newRepDiaBtn, walkinWOBtn,
			workCmplBtn, pickUpDiaBtn, cmpnyWOBtn, cusUpdateBtn, apprDiaBtn, dOSigBtn, cmplRprBtn,
			repComDiaBtn, pUSigBtn));
	
	
	public Handbook() {
		//The following try/catch block is needed so that the GUI's look and feel appears similar 
		//across different operating systems.
		try {
	        UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		
		
		
		// === BUTTON AREA CONFIGURATIONS ===
		//gbc.insets = new Insets(15, 15, 15, 15); //Adjust insets to modify a component's margins
		//gbc.ipady = 30; //Adjust ipadx or ipady to modify a component's padding
		btnArea.setLayout(new MigLayout());
		gbc.gridx = 0; gbc.gridy = 2;
		/*
		//Turns out these weight and anchor attributes don't need to be tweaked if I
		//add the logo, label, and button set one after another...
		gbc.weighty = 10; //Don't think I need this...
		gbc.anchor = GridBagConstraints.NORTH; //Don't think I need this...
		*/
		btnArea.setPreferredSize(new Dimension(700, 300)); //HARD-CODED DIMENSIONS
		//btnArea.setBackground(Color.decode("0x1C6959")); //For testing
		btnArea.setBackground(Color.WHITE);
		mainMenuScreen.add(btnArea, gbc);
		
		
		//Start adding buttons inside btnArea...
		//. I could probably wrap all of the following up in a for loop...
		//. I have a feeling there's a way you can see the size of the buttons in the constructor for the MigLayout...
		for (int i = 0, curCol = 0; i < mainMenuBtns.size(); i++, curCol++) {
			prepButton(mainMenuBtns.get(i), btnArea);
			if (curCol == 0) { 
				if (i == 0) {
					btnArea.add(mainMenuBtns.get(i), "width " + String.valueOf(700/3) + ", height " + String.valueOf(300/10));
				}
				else {
					btnArea.add(mainMenuBtns.get(i), "width " + String.valueOf(700/3) + ", height " + String.valueOf(300/10) + ", gaptop " + String.valueOf(700/70));
				}
			}
			else if (curCol == 1) { 
				btnArea.add(mainMenuBtns.get(i), "width " + String.valueOf(700/3) + ", height " + String.valueOf(300/10) + ", gap " + String.valueOf(700/70));
			}
			else if (curCol == 2) { 
				btnArea.add(mainMenuBtns.get(i), "width " + String.valueOf(700/3) + ", height " + String.valueOf(300/10) + ", gap " + String.valueOf(700/70) + ", wrap");
				curCol = -1; //Don't reset to 0 because curCol is incremented at the end of the iteration!!!
			}
		}
		
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


	/**
	 * Helper Method
	 * @param btn
	 * @param btnArea
	 */
	private void prepButton(JButton btn, JPanel btnArea) {
		//Note that btnArea is 700 x 300
		btn.setFont(new Font("Arial", Font.BOLD, 13));
		btn.setMargin(new Insets(7, 7, 7, 7)); //Perhaps inset values should NOT be hard coded?
		btn.setForeground(Color.WHITE);
		btn.setBackground(Color.decode("0x1C6959"));
		btn.setOpaque(true); //Need to say this so that the button's color shows up
		//newRepDiaBtn.setBorderPainted(false); 
		//^I would have need to say this if I didn't write the look and feel try/catch block
		
		//btnArea.add(btn); //Should I add in this method?
	}
	
}
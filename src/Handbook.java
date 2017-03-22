import javax.swing.*;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.miginfocom.swing.MigLayout;


public class Handbook {
	
	private JFrame frame = new JFrame();
	private JPanel mainScreen = new JPanel(); //Maybe I should call this mainScreen?
	
	private JPanel mainMenu = new JPanel();
	private JLabel opManualTxt = new JLabel("Operations Manual");
	private JPanel logo = new CTLogo();
	private JPanel btnArea = new JPanel();
	
	private JPanel newRepDia = new JPanel();
	private JPanel walkinWO = new JPanel();
	private JPanel workCmpl = new JPanel();
	private JPanel pickUpDia = new JPanel();
	private JPanel cmpnyWO = new JPanel();
	private JPanel cusUpdate = new JPanel();
	private JPanel apprDia = new JPanel();
	private JPanel dOSig = new JPanel();
	private JPanel cmplRpr = new JPanel();
	private JPanel repComDia = new JPanel();
	private JPanel pUSig = new JPanel();
	
	//Main Menu Buttons (have individual variables for them for when
	//we need to add action listeners):
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
	//This ArrayList is for when we need to add all the buttons onto the main menu. More concise code
	//for that can be written with this ArrayList.
	private List<JButton> mainMenuBtns = new ArrayList<JButton>(Arrays.asList(newRepDiaBtn, walkinWOBtn,
			workCmplBtn, pickUpDiaBtn, cmpnyWOBtn, cusUpdateBtn, apprDiaBtn, dOSigBtn, cmplRprBtn,
			repComDiaBtn, pUSigBtn));
	
	CardLayout cl = new CardLayout(); //Will use a CardLayout to handle screen changing

	/**
	 * Constructor
	 */
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
		
		
		
		// === MAIN MENU CONFIGURATIONS ===
		//mainMenu.setBackground(Color.decode("0x121E31")); //for debugging
		mainMenu.setBackground(Color.WHITE);
		
		// ##### Layout with GridBagLayout #####
		mainMenu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		//gbc.insets = new Insets(10, 10, 10, 10);
		gbc.insets = new Insets(6, 6, 6, 6);
		
		
		
		// === NEW REPAIR DIALOGUE CONFIGURATIONS ===
		newRepDia.setBackground(Color.WHITE);
		//How will you lay this page out?
		
		
		
		// === WALK-IN WORK ORDER CONFIGURATIONS ===
		walkinWO.setBackground(Color.WHITE);
		
		
		
		// === WORK COMPLETE CONFIGURATIONS ===
		workCmpl.setBackground(Color.WHITE);
		
		
		
		// === PICK-UP DIALOG CONFIGURATIONS ===
		pickUpDia.setBackground(Color.WHITE);
		
		
		
		// === COMPANY WORK ORDER CONFIGURATIONS ===
		cmpnyWO.setBackground(Color.WHITE);
		
		

		// === CUSTOMER UPDATE CONFIGURATIONS ===
		cusUpdate.setBackground(Color.WHITE);
		
		
		
		// === APPROVAL DIALOG CONFIGURATIONS ===
		apprDia.setBackground(Color.WHITE);
		
		
		
		// === DROP-OFF SIGNATURE CONFIGURATIONS ===
		dOSig.setBackground(Color.WHITE);
		
		
		
		// === COMPLETE REPAIR CONFIGURATIONS ===
		cmplRpr.setBackground(Color.WHITE);
		
		
		
		// === REPAIR COMPLETE DIALOG CONFIGURATIONS ===
		repComDia.setBackground(Color.WHITE);
		
		
		
		// === PICK-UP SIGNATURE CONFIGURATIONS ===
		pUSig.setBackground(Color.WHITE);
		
		
		
		// === MAIN MENU SCREEN CONFIGURATIONS ===
		//. If a screen you get to from a button (screen A) does not lead to another screen, then screen A can simply
		//  be a JPanel that is set with a CardLayout.
		//. If a screen you get to from a button (screen A) leads to another screen (screen B), then screen A should be
		//  a JPanel that is set with another CardLayout object. On that JPanel will be another JPanel that has the MigLayout.
		//    - If a screen is labeled with "2" and this screen leads to another screen, that other screen should be labeled "2a"
		
		mainScreen.setLayout(cl);
		mainScreen.add(mainMenu, "1");
		mainScreen.add(newRepDia, "2");
		mainScreen.add(walkinWO, "3");
		mainScreen.add(workCmpl, "4");
		mainScreen.add(pickUpDia, "5");
		mainScreen.add(cmpnyWO, "6");
		mainScreen.add(cusUpdate, "7");
		mainScreen.add(apprDia, "8");
		mainScreen.add(dOSig, "9");
		mainScreen.add(cmplRpr, "10");
		mainScreen.add(repComDia, "11");
		mainScreen.add(pUSig, "12");


		cl.show(mainScreen, "1"); //Make the main menu show up first.
		
		
		
		// === CLEVERTECH LOGO CONFIGURATIONS ===
		logo.setBackground(Color.WHITE);
		gbc.gridx = 0; gbc.gridy = 0;
		/*
		//Turns out these weight and anchor attributes don't need to be tweaked if I
		//add the logo, label, and button set one after another...
		gbc.weightx = 1; //Determines spacing with respect to columns. Higher value == less space?
		gbc.anchor = GridBagConstraints.NORTH; //Do I need this?
		*/
		mainMenu.add(logo, gbc);
		
		
		
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
		mainMenu.add(opManualTxt, gbc);
		
		
		
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
		mainMenu.add(btnArea, gbc);
		
		
		//Start adding buttons inside btnArea...
		//. I could probably wrap all of the following up in a for loop...
		//. I have a feeling there's a way you can see the size of the buttons in the constructor for the MigLayout...
		for (int i = 0, curCol = 0; i < mainMenuBtns.size(); i++, curCol++) {
			prepButton(mainMenuBtns.get(i));
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
		
		
		// === CODE FOR HANDLING SCREEN CHANGING ===
		
		newRepDiaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(mainScreen, "2");
				//^Read this as: For mainScreen, show the (JPanel) component labeled as "2"
			}
		});
		
		walkinWOBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(mainScreen, "3");
			}
		});
		
		workCmplBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(mainScreen, "4");
			}
		});
		
		pickUpDiaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(mainScreen, "5");
			}
		});
		
		cmpnyWOBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(mainScreen, "6");
			}
		});
		
		cusUpdateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(mainScreen, "7");
			}
		});
		
		apprDiaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(mainScreen, "8");
			}
		});

		dOSigBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(mainScreen, "9");
			}
		});
		
		cmplRprBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(mainScreen, "10");
			}
		});
		
		repComDiaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(mainScreen, "11");
			}
		});
		
		pUSigBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(mainScreen, "12");
			}
		});

				
		
		// === JFRAME CONFIGRATIONS ===
		frame.setResizable(false); //Resizing destroys the layout. Disable for now.
	    frame.getContentPane().setPreferredSize(new Dimension(900, 500)); //HARD-CODED DIMENSIONS
		frame.add(mainScreen);
		
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
	private void prepButton(JButton btn) {
		//Note that btnArea is 700 x 300
		btn.setFont(new Font("Arial", Font.BOLD, 13));
		btn.setMargin(new Insets(7, 7, 7, 7)); //Perhaps inset values should NOT be hard coded?
		btn.setForeground(Color.WHITE);
		btn.setBackground(Color.decode("0x026937"));
		btn.setOpaque(true); //Need to say this so that the button's color shows up
		//newRepDiaBtn.setBorderPainted(false); 
		//^I would have need to say this if I didn't write the look and feel try/catch block
		
		//btnArea.add(btn); //Should I add in this method?
	}
	
}
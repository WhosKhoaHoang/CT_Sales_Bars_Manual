import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.jdesktop.swingx.border.DropShadowBorder;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.miginfocom.swing.MigLayout;


public class Handbook {
	
	private JFrame frame = new JFrame();
	private JPanel mainScreen = new JPanel();
	
	private JPanel mainMenu = new JPanel();
	private JLabel opManualTxt = new JLabel("Operations Manual");
	private JPanel logo = new CTLogo();
	private JPanel btnArea = new JPanel();
	
	//private JPanel newRepDiaBase = new JPanel(); //Do I even need this?
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
	
	private CardLayout mainScreenCl = new CardLayout(); //Will use a CardLayout to handle screen changing
	private CardLayout newRepDiaCl = new CardLayout();

	/**
	 * Constructor
	 */
	public Handbook() {		
		//FOR TESTING OUT BORDERS
		Border blackline, raisedetched, loweredetched,
	       raisedbevel, loweredbevel, empty;
		blackline = BorderFactory.createLineBorder(Color.black);
		raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		raisedbevel = BorderFactory.createRaisedBevelBorder();
		loweredbevel = BorderFactory.createLoweredBevelBorder();
		empty = BorderFactory.createEmptyBorder();
		
		//Set border for frame (the top level container)
        DropShadowBorder shadow = new DropShadowBorder();
        shadow.setShadowColor(Color.BLACK);
        shadow.setShowLeftShadow(true);
        shadow.setShowRightShadow(true);
        shadow.setShowBottomShadow(true);
        shadow.setShowTopShadow(true);
        
        frame.getRootPane().setBorder(shadow);
        //frame.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));        
        
		//The following try/catch block is needed so that the GUI's look and feel appears similar 
		//across different operating systems.
		try {
	        UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		// === MAIN MENU CONFIGURATIONS ===
		//mainMenu.setBackground(Color.decode("0x121E31")); //for debugging
		mainMenu.setBackground(Color.WHITE);
		mainMenu.setLayout(new MigLayout("align 50% 50%, gapy 10", "[center][center][center]"));
		//The first 50% after align establishes horizontal centering and the second 50% establishes vertical centering
		
		
		
		
		
		
		// !!!!!!!!!!!!!!!!!!!!!!!! FOCUS HERE BEGIN !!!!!!!!!!!!!!!!!!!!!!!!
		
		//TODO: Try putting the dialogue into a separate html file and reading it into the program...
		
		// === NEW REPAIR DIALOGUE CONFIGURATIONS ===
		//newRepDiaBase.setBackground(Color.WHITE); 
		//. newRepDiaBase itself is an empty panel with nothing on it except newRepDia -- it's just here to handle screen changes
		//   - Why have another component set with CardLayout when that component is already on a component set with CardLayout? Why
		//	   not just use that first component with the CardLayout? That way seems to work, your original idea didn't...
		//. This screen will link to the walk-in work order screen, so I think newRepDiaBase needs to be set with a CardLayout. 
		//  Another JPanel, which will be called newRepDia set with a MigLayout, will be made that gets added to newRepDiaBase...
		//. WAIT. Do I even need a newRepDiaBase?!?! This way wasn't working. Using mainScreen and mainScreenCl worked though...
		
		//OPTIONS:
		//. Use JLabels for each line in the dialogue (but you can't select JLabel text [i.e., copy it]!)
		//. Use a non-editable JTextArea (but this is bad because I can't add components to JTextAreas!)
		//. Use a non-editable JTextPane and use setText() to insert text (but setText() changes the whole
		//   text. What if I want to add a component somewhere in the text?)
		//. Use a non-editable JTextPane and insert text into it with a HTMLEditorKit (but after each insertHTML(), a
		//   line break is added. Will this be an issue?)...I THINK I'LL GO WITH THIS
		
		//NOTE THAT YOU NEED TO ADD EVENT LISTENERS to JTextPane if you want to allow user to copy and paste!!!
		
		newRepDia.setLayout(new MigLayout("", "", "[][]"));
		newRepDia.setBackground(Color.WHITE);
		
		createHeader("DIALOGUE FOR NEW REPAIR", newRepDia);
		
		JTextPane newRepDiaTxt = new JTextPane();			
		newRepDiaTxt.setEditable(false);
		newRepDiaTxt.setContentType("text/html");
		
		JButton toWalkInWOBtn = new JButton("FOLLOW WALK-IN TEMPLATE");
		
		HTMLDocument newRepDiaTxtdoc = (HTMLDocument)newRepDiaTxt.getDocument();
		HTMLEditorKit newRepDiaTxtEK = (HTMLEditorKit)newRepDiaTxt.getEditorKit();
		
		//Perhaps it would be more beneficial to store these strings in a separate HTML file and read it in? I think so. It'd break up
		//the program into more manageable pieces!!! It'll be easier to edit the HTML also -- it's a pain to edit the string.
		String newRepDiaTxt1 = "<strong style='color:78be48; font-size:200%'>.</strong> <strong>Sales Person:</strong> \"Hi there, how can I help?\"";
	
		String newRepDiaTxt2 = "<strong style='color:78be48; font-size:200%'>.</strong> <strong>Guest:</strong> \"I’m having problems with my computer\"";
		
		String newRepDiaTxt3 = "<strong style='color:78be48; font-size:200%'>.</strong> <strong>Sales Person:</strong>  \"Would you mind me taking a quick look? If you have a "
								+ "few minutes, I’d like to see if I can diagnose the issue here with you.\"";
		
		
		String newRepDiaTxt4 = "<strong style='color:78be48; font-size:200%'>.</strong> <strong>Sales Person:</strong> At this point you may do the best to your"
				+ " ability to diagnose any possible issues occurring with the computer and quote customer more than likely pricing scenarios and "
				+ "retrieve approvals for possible outcomes. You can continue with small talk like questions \"How’s your day been?\""
				+ " \"Is this the first time your computer crashed?\" \"Has your computer been liquid damaged and if yes what areas of the computer?\"";
		
		
		String newRepDiaTxt5 = "<strong style='color:78be48; font-size:200%'>.</strong> <strong>Sales Person:</strong> \"I can bring in your computer to verify the "
				+ "diagnostics, turnaround time is typically 2-3 days to complete the repair.\"";  

		String newRepDiaTxt6 = "<strong style='color:78be48; font-size:200%'>.</strong> <strong>Sales Person:</strong> \"Thank you for being so patient,  I will need a few more minutes to get all the notes down for our technicians.\"";
		
		String newRepDiaTxt7 = "<strong style='color:78be48; font-size:200%'>.</strong> <strong>Sales Person:</strong> Have customer sign legal contract, remind customer to bring photo ID for pick up.";

		try {
			newRepDiaTxtEK.insertHTML(newRepDiaTxtdoc, newRepDiaTxtdoc.getLength(), newRepDiaTxt1, 0, 0, null);			
			newRepDiaTxtEK.insertHTML(newRepDiaTxtdoc, newRepDiaTxtdoc.getLength(), newRepDiaTxt2, 0, 0, null);
			newRepDiaTxtEK.insertHTML(newRepDiaTxtdoc, newRepDiaTxtdoc.getLength(), newRepDiaTxt3, 0, 0, null);
			newRepDiaTxtEK.insertHTML(newRepDiaTxtdoc, newRepDiaTxtdoc.getLength(), newRepDiaTxt4, 0, 0, null);
			newRepDiaTxtEK.insertHTML(newRepDiaTxtdoc, newRepDiaTxtdoc.getLength(), newRepDiaTxt5, 0, 0, null);
			newRepDiaTxtEK.insertHTML(newRepDiaTxtdoc, newRepDiaTxtdoc.getLength(), newRepDiaTxt6, 0, 0, null);
			newRepDiaTxt.insertComponent(toWalkInWOBtn); //Occurs at 6th dialog line. If this were in a loop, perhaps you can say something like if i == 6?
			newRepDiaTxtEK.insertHTML(newRepDiaTxtdoc, newRepDiaTxtdoc.getLength(), newRepDiaTxt7, 0, 0, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JScrollPane newRepDiaTxtScroll = new JScrollPane(newRepDiaTxt);
		//newRepDiaTxtScroll.setBorder(null);
		
		toWalkInWOBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "3");
			}
		});
		
		newRepDiaTxtScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		newRepDiaTxtScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		newRepDia.add(newRepDiaTxtScroll, "push, grow");
		
		
		// !!!!!!!!!!!!!!!!!!!!!!!! FOCUS HERE END !!!!!!!!!!!!!!!!!!!!!!!!
		
		

		
		
		// === WALK-IN WORK ORDER CONFIGURATIONS ===
		walkinWO.setBackground(Color.WHITE);
		walkinWO.setLayout(new MigLayout());
		createHeader("WALK-IN WORK ORDER TEMPLATE", walkinWO); 
		//^Why does back button for new repair dialogue disappear when I have this? 
		//Maybe because there's only one instance of a back button? That must be it...
		
		JTextPane walkinWOTxt = new JTextPane();		
		walkinWOTxt.setEditable(false);
		walkinWOTxt.setContentType("text/html");
				
		HTMLDocument walkinWOdoc = (HTMLDocument)newRepDiaTxt.getDocument();
		HTMLEditorKit walkinWOeditorKit = (HTMLEditorKit)newRepDiaTxt.getEditorKit();
		
		String walkinWOTxt1;
		
		
		// === WORK COMPLETE CONFIGURATIONS ===
		workCmpl.setBackground(Color.WHITE);
		workCmpl.setLayout(new MigLayout());
		createHeader("WORK COMPLETED UPDATE", workCmpl);
		
		
		// === PICK-UP DIALOG CONFIGURATIONS ===
		pickUpDia.setBackground(Color.WHITE);
		pickUpDia.setLayout(new MigLayout());
		createHeader("DIALOGUE FOR PICK-UP", pickUpDia);
		
		
		// === COMPANY WORK ORDER CONFIGURATIONS ===
		cmpnyWO.setBackground(Color.WHITE);
		cmpnyWO.setLayout(new MigLayout());
		createHeader("COMPANY WORK ORDER TEMPLATE", cmpnyWO);
		
		

		// === CUSTOMERS UPDATE CONFIGURATIONS ===
		cusUpdate.setBackground(Color.WHITE);
		cusUpdate.setLayout(new MigLayout());
		createHeader("CUSTOMERS UPDATE TEXT", cusUpdate);
		
		
		
		// === APPROVAL DIALOG CONFIGURATIONS ===
		apprDia.setBackground(Color.WHITE);
		apprDia.setLayout(new MigLayout());
		createHeader("DIALOGUE FOR APPROVAL", apprDia);
		
		
		// === DROP-OFF SIGNATURE CONFIGURATIONS ===
		dOSig.setBackground(Color.WHITE);
		dOSig.setLayout(new MigLayout());
		createHeader("DROP-OFF SIGNATURE", dOSig);
		
		
		
		// === COMPLETE REPAIR CONFIGURATIONS ===
		cmplRpr.setBackground(Color.WHITE);
		cmplRpr.setLayout(new MigLayout());
		createHeader("COMPLETE REPAIR TEXT", cmplRpr);
		
		
		// === REPAIR COMPLETE DIALOG CONFIGURATIONS ===
		repComDia.setBackground(Color.WHITE);
		repComDia.setLayout(new MigLayout());
		createHeader("DIALOGUE REPAIR COMPLETION", repComDia);
		
		
		// === PICK-UP SIGNATURE CONFIGURATIONS ===
		pUSig.setBackground(Color.WHITE);
		pUSig.setLayout(new MigLayout());
		createHeader("PICK UP SIGNATURE", pUSig);
		
		
		// === MAIN MENU SCREEN CONFIGURATIONS ===
		//. If a screen you get to from a button (screen A) does not lead to another screen, then screen A can simply
		//  be a JPanel that is set with a CardLayout.
		//. If a screen you get to from a button (screen A) leads to another screen (screen B), then screen A should be
		//  a JPanel that is set with another CardLayout object. On that JPanel will be another JPanel that has the MigLayout.
		//    - If a screen is labeled with "2" and this screen leads to another screen, that other screen should be labeled "2a"
		//    - NO. This didn't seem to work. Java didn't let you do that. Since Screen B happened to be a screen that you could 
		//		get from the main menu, you could simply use the CardLayout that was already set on the main menu -- no need to 
		//		set another CardLayout on on Screen A to house Screen B. If you really wanted that arrangement, then you would have 
		//		need to create a new copy of Screen B...but what a waste, right?
		mainScreen.setLayout(mainScreenCl);
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

		mainScreenCl.show(mainScreen, "1"); //Make the main menu (labeled as "1") show up first as part of the Card Layout.
		
		// === CLEVERTECH LOGO CONFIGURATIONS ===
		logo.setBackground(Color.WHITE);
		mainMenu.add(logo, "wrap");
		
		
		// === OPERATIONS MANUAL TEXT CONFIGURATIONS===
		opManualTxt.setFont(new Font("Impact", Font.PLAIN, 25));
		opManualTxt.setForeground(Color.decode("0x8FC967"));
		mainMenu.add(opManualTxt, "wrap");
		
		
		
		// === BUTTON AREA CONFIGURATIONS ===
		//gbc.insets = new Insets(15, 15, 15, 15); //Adjust insets to modify a component's margins
		//gbc.ipady = 30; //Adjust ipadx or ipady to modify a component's padding
		btnArea.setLayout(new MigLayout());
		btnArea.setPreferredSize(new Dimension(700, 300)); //HARD-CODED DIMENSIONS
		//btnArea.setBackground(Color.decode("0x8FC967")); //For testing
		//^If you decide to go with this green box look, you'll need to get rid of the empty space at the bottom.
		//To accomplish this, you'll probably have to make the heights of the buttons a hard-coded value rather than 
		//one that is dependent on the size of the green box. Moreover, you'll need to decrease the height of the box.
		btnArea.setBackground(Color.WHITE);
		mainMenu.add(btnArea);
		
		
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
		
		
		// === CODE FOR HANDLING SCREEN CHANGING FROM THE MAIN MENU ===
		
		//MAIN MENU BUTTONS:
		newRepDiaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "2");
				//^Read this as: For mainScreen, show the (JPanel) component labeled as "2"
			}
		});
		
		walkinWOBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "3");
			}
		});
		
		workCmplBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "4");
			}
		});
		
		pickUpDiaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "5");
			}
		});
		
		cmpnyWOBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "6");
			}
		});
		
		cusUpdateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "7");
			}
		});
		
		apprDiaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "8");
			}
		});

		dOSigBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "9");
			}
		});
		
		cmplRprBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "10");
			}
		});
		
		repComDiaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "11");
			}
		});
		
		pUSigBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "12");
			}
		});
				
		
		// === JFRAME CONFIGRATIONS ===
		frame.setResizable(false); //Resizing destroys the layout. Disable for now.
	    frame.getContentPane().setPreferredSize(new Dimension(800, 500)); //HARD-CODED DIMENSIONS
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
		btn.setFont(new Font("Arial", Font.BOLD, 13));
		btn.setMargin(new Insets(7, 7, 7, 7)); //Perhaps inset values should NOT be hard coded?
		btn.setForeground(Color.WHITE);
		btn.setBackground(Color.decode("0x026937"));
		btn.setOpaque(true); //Need to say this so that the button's color shows up
		//newRepDiaBtn.setBorderPainted(false); 
		//^I would have need to say this if I didn't write the look and feel try/catch block situated
		//near the beginning of the Handbook constructor
	}
	
	
	/**
	 * Helper method
	 * @param headerTxt
	 * @param panel (must be set with MigLayout)
	 */
	private void createHeader(String headerTxt, JPanel panel) {
		JPanel header = new JPanel();
		header.setBackground(Color.decode("0x026937"));
		header.setLayout(new MigLayout());
		
		JLabel headerLbl = new JLabel(headerTxt); //String is variable
		headerLbl.setFont(new Font("Helvetica", Font.BOLD, 25));
		headerLbl.setForeground(Color.WHITE);
		
		//Maybe I can create the back button in here and add the Action Listener in here
		//since I can access mainScreenCl from here...
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "1");
			}
		});
		
		header.add(backBtn, "wrap");
		header.add(headerLbl, "gaptop 10"); //HARD-CODED VALUE
		
		panel.add(header, "height 100, pushx, growx, wrap"); //HARD-CODED VALUE. Setting values for Header here.
	}
	
}
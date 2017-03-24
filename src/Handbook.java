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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.miginfocom.swing.MigLayout;

//TODO: Consider changing hard-coded values!

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
				
		//TODO: Try putting the dialogue into a separate html file and reading it into the program...
		//TODO: NOTE THAT YOU NEED TO ADD EVENT LISTENERS to JTextPane if you want to allow user to copy and paste!!!
		
		newRepDia.setLayout(new MigLayout("", "", "[][]"));
		newRepDia.setBackground(Color.WHITE);
		createHeader("DIALOGUE FOR NEW REPAIR", newRepDia);		
		fillComponentContent("new_repair_dialogue.html", newRepDia, "FOLLOW WALK-IN TEMPLATE", "walkin_work_order_screen");

		
		// === WALK-IN WORK ORDER CONFIGURATIONS ===
		walkinWO.setBackground(Color.WHITE);
		walkinWO.setLayout(new MigLayout());
		createHeader("WALK-IN WORK ORDER TEMPLATE", walkinWO); 
		fillBasicContent("walkinWO.html", walkinWO);

		
		// === WORK COMPLETE CONFIGURATIONS ===
		workCmpl.setBackground(Color.WHITE);
		workCmpl.setLayout(new MigLayout());
		createHeader("WORK COMPLETED UPDATE", workCmpl);
		fillComponentContent("work_completed_text.html", workCmpl, "FOLLOW DIALOGUE FOR APPROVAL", "approval_dialogue_screen");

		
		// === PICK-UP DIALOG CONFIGURATIONS ===
		pickUpDia.setBackground(Color.WHITE);
		pickUpDia.setLayout(new MigLayout());
		createHeader("DIALOGUE FOR PICK-UP", pickUpDia);
		fillComponentContent("pickup_dialogue.html", pickUpDia, "FOLLOW PICK-UP SIGNATURE", "pickup_signature_screen");
		
		
		// === COMPANY WORK ORDER CONFIGURATIONS ===
		cmpnyWO.setBackground(Color.WHITE);
		cmpnyWO.setLayout(new MigLayout());
		createHeader("COMPANY WORK ORDER TEMPLATE", cmpnyWO);
		fillBasicContent("company_WO.html", cmpnyWO);
		

		// === CUSTOMERS UPDATE CONFIGURATIONS ===
		cusUpdate.setBackground(Color.WHITE);
		cusUpdate.setLayout(new MigLayout());
		createHeader("CUSTOMERS UPDATE TEXT", cusUpdate);
		fillBasicContent("customers_update_text.html", cusUpdate);
		
		
		// === APPROVAL DIALOG CONFIGURATIONS ===
		apprDia.setBackground(Color.WHITE);
		apprDia.setLayout(new MigLayout());
		createHeader("DIALOGUE FOR APPROVAL", apprDia);
		fillComponentContent("approval_dialogue.html", apprDia, "FOLLOW CUSTOMER UPDATE TEXT", "customers_update_screen");
		
		
		// === DROP-OFF SIGNATURE CONFIGURATIONS ===
		dOSig.setBackground(Color.WHITE);
		dOSig.setLayout(new MigLayout());
		createHeader("DROP-OFF SIGNATURE", dOSig);
		fillBasicContent("drop_off_sig.html", dOSig);
		
		
		// === COMPLETE REPAIR CONFIGURATIONS ===
		cmplRpr.setBackground(Color.WHITE);
		cmplRpr.setLayout(new MigLayout());
		createHeader("COMPLETE REPAIR TEXT", cmplRpr);
		fillComponentContent("complete_repair_text.html", cmplRpr, 
				"FOLLOW DIALOGUE FOR REPAIR COMPLETION UPDATE", "repair_complete_dialogue_screen");
		
		
		// === REPAIR COMPLETE DIALOG CONFIGURATIONS ===
		repComDia.setBackground(Color.WHITE);
		repComDia.setLayout(new MigLayout());
		createHeader("DIALOGUE REPAIR COMPLETION", repComDia);
		fillComponentContent("repair_completion_dialogue.html", repComDia,
				"FOLLOW COMPLETE REPAIR", "complete_repair_screen");
		
		
		// === PICK-UP SIGNATURE CONFIGURATIONS ===
		pUSig.setBackground(Color.WHITE);
		pUSig.setLayout(new MigLayout());
		createHeader("PICK UP SIGNATURE", pUSig);
		fillBasicContent("pickup_sig.html", pUSig);
		
		
		// === MAIN MENU SCREEN CONFIGURATIONS ===
		mainScreen.setLayout(mainScreenCl);
		mainScreen.add(mainMenu, "main_menu_screen");
		mainScreen.add(newRepDia, "new_repair_dialogue_screen");
		mainScreen.add(walkinWO, "walkin_work_order_screen");
		mainScreen.add(workCmpl, "work_completed_screen");
		mainScreen.add(pickUpDia, "pickup_dialogue_screen");
		mainScreen.add(cmpnyWO, "company_work_order_screen");
		mainScreen.add(cusUpdate, "customers_update_screen");
		mainScreen.add(apprDia, "approval_dialogue_screen");
		mainScreen.add(dOSig, "drop_off_signature_screen");
		mainScreen.add(cmplRpr, "complete_repair_screen");
		mainScreen.add(repComDia, "repair_complete_dialogue_screen");
		mainScreen.add(pUSig, "pickup_signature_screen");

		mainScreenCl.show(mainScreen, "main_menu_screen"); //Make the main menu (labeled as "1") show up first as part of the Card Layout.
		
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
				mainScreenCl.show(mainScreen, "new_repair_dialogue_screen");
				//^Read this as: For mainScreen, show the (JPanel) component labeled as "new_repair_dialogue_screen"
			}
		});
		
		walkinWOBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "walkin_work_order_screen");
			}
		});
		
		workCmplBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "work_completed_screen");
			}
		});
		
		pickUpDiaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "pickup_dialogue_screen");
			}
		});
		
		cmpnyWOBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "company_work_order_screen");
			}
		});
		
		cusUpdateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "customers_update_screen");
			}
		});
		
		apprDiaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "approval_dialogue_screen");
			}
		});

		dOSigBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "drop_off_signature_screen");
			}
		});
		
		cmplRprBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "complete_repair_screen");
			}
		});
		
		repComDiaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "repair_complete_dialogue_screen");
			}
		});
		
		pUSigBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "pickup_signature_screen");
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
				mainScreenCl.show(mainScreen, "main_menu_screen");
			}
		});
		
		header.add(backBtn, "wrap");
		header.add(headerLbl, "gaptop 10"); //HARD-CODED VALUE
		
		panel.add(header, "height 100, pushx, growx, wrap"); //HARD-CODED VALUE. Setting values for Header here.
	}
	
	
	/**
	 * A helper method.
	 * @param fileName Name of the file to read content from
	 * @param panel Must be set with a MigLayout
	 */
	private void fillBasicContent(String fileName, JPanel panel) {
		JTextPane content = new JTextPane();		
		content.setEditable(false);
		content.setContentType("text/html");
		
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            
            while (line != null) {
            	sb.append(line);
            	line = br.readLine();
            }
            
            String everything = sb.toString();
            content.setText(everything);
            br.close();         
        }
        catch(FileNotFoundException e) { e.printStackTrace(); }
        catch(IOException e) {e.printStackTrace();}
		
		final JScrollPane contentScroll = new JScrollPane(content);
		//contentScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		SwingUtilities.invokeLater(new Runnable() { //This is the only way I can get the JScrollPane to default to a 0 value...
			   public void run() { 
				   contentScroll.getVerticalScrollBar().setValue(0);
			   }
			});
		panel.add(contentScroll, "push, grow");
	}
	
	
	private void fillComponentContent(String fileName, JPanel panel, String btnTxt, final String jumpToPgLbl) {
		
		//Configure JTextPane() for content:
		JTextPane content = new JTextPane();			
		content.setEditable(false);
		content.setContentType("text/html");
		
		//Make button to other page and set ActionListener
		//- In the method, use the provided componentLst? But how am I going to handle setting of the ActionListener?
		//    For now, let's just pass in a single button...but for future reference, maybe you can actually have each 
		//	  component in componentLst be paired with the string description of the page
		//    that the component would jump to...
		/*
		//If componentLst were an ArrayList of pairs...(Note that you'd have to make a Pair class of your own! Java doesn't have one)
		for (int i = 0; i < componentLst.size(); i++) {
			componentLst.get(i).first.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainScreenCl.show(mainScreen, componentLst.get(i).second); 
					//Hmm, this second argument needs to be declared with final. How am I gonna do that with contents in an ArrayList?
				}
			}
		}
		*/
		
		JButton button = new JButton(btnTxt);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, jumpToPgLbl);  //mainScreenCl and mainScreen must be referred to in this actionListener
			}
		});
		
		//Make Strings...or in this method, you'd read each line in an HTML file		
        String line = null;
        List<String> lineLst = new ArrayList<String>();
        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            
            while((line = bufferedReader.readLine()) != null) {
            	lineLst.add(line);
            }
            
            bufferedReader.close();         
        }
        catch(FileNotFoundException e) { e.printStackTrace(); }
        catch(IOException e) { e.printStackTrace();}
		
		
		//Make HTMLDocument and HTMLEditorKit
		HTMLDocument contentDoc = (HTMLDocument)content.getDocument();
		HTMLEditorKit contentEK = (HTMLEditorKit)content.getEditorKit();
		for (int i = 0; i < lineLst.size(); i++) {
			if (lineLst.get(i).equals("<!--INSERT-->")) {
				content.insertComponent(button);
				//If you wanted to go the componentLst route, you can have a j index variable and say something like:
				//content.insertComponent(componentLst.get(j)); j++;
			}
			else {
				try {
					contentEK.insertHTML(contentDoc, contentDoc.getLength(), lineLst.get(i), 0, 0, null);
					//Seems like inserting a single opening tag and the corresponding closing tag later on won't do anything...
					// - If that's the case, you're gonna have to be a bit verbose on how you write your HTML tags in the HTML file. See
					//    work_completed_text.html as an example.
				} 
				catch (BadLocationException e1) { e1.printStackTrace(); } 
				catch (IOException e1) { e1.printStackTrace(); }
			}
		}
		
		//Make JScrollPane to contain content
		JScrollPane contentScroll = new JScrollPane(content);
		
		//Add JScrollPant onto JPanel for this screen
		panel.add(contentScroll, "push, grow");		
	}
}
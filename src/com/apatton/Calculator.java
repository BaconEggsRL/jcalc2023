package com.apatton;

/**
 * Calculator

 * @author apatton
 * @version 3.0
 */

/**
 * For version 3.1:
 * Parenthetical multiplication eg. 8(3)=24
 * For version 3.2:
 * Run operators on previous answer without "ans" eg. +7=ans+7
 * For version 4.0:
 * Graph equations
 */



import java.net.URL;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;


@SuppressWarnings("unused")
public class Calculator extends JFrame
{
	
	private static final long serialVersionUID = -5831333300128125662L;
	private InfixToPostfix itp = new InfixToPostfix();
	private PostfixToValue ptv = new PostfixToValue();
	private String helpmsg = new String("Enter numeric expressions into the text area.\n"
								+ "Press enter to get the result rounded to eight decimals.\n\n"
								+ "Supported syntax:\n"
								+ "     exit - leave the application\n"
								+ "     clear - removes all text from the window\n"
								+ "     ans - returns the previous result in base ten\n"
								+ "     e - returns the mathematical constant\n"
								+ "     pi - returns the mathematical contant\n"
								+ "     binary(x) - returns numeric expression 'x' in binary\n"
								+ "     hex(x) - returns numeric expression 'x' in hexidecimal");

	public Calculator()
	{
		buildGUI();
	}

	private void buildGUI()
	{//Builds each component of the GUI
		
		createWindow();
		createMenuBar();
		createTextField();
	}
	
	

	private void createWindow()
	{//Creates the JFrame window
		
		//Container container = getContentPane();
		setLayout(new BorderLayout());
		
		setTitle("Calculator v3.0");//window title
		setSize(400, 600);//resize
		setLocationRelativeTo(null);//center on screen
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);//clicking the x closes the window
	}
	
	
	private void createMenuBar()
	{//Creates the menu bar of the JFrame window
		
		//menuBar
		JMenuBar menuBar = new JMenuBar();
		
			//mnFile
			JMenu mnFile = new JMenu("File");
			mnFile.setMnemonic(KeyEvent.VK_F);
		
				//mntmExit
				JMenuItem mntmExit = new JMenuItem("Exit");
				mntmExit.setMnemonic(KeyEvent.VK_E);
				mntmExit.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent event)
					{
						System.exit(0);
					}
				});
		
			//mnEdit
			JMenu mnEdit = new JMenu("Edit");
			mnEdit.setMnemonic(KeyEvent.VK_E);
			
				JMenuItem mntmCut = new JMenuItem(new DefaultEditorKit.CutAction());
				mntmCut.setText("Cut");
				mntmCut.setMnemonic(KeyEvent.VK_T);
			
				JMenuItem mntmCopy = new JMenuItem(new DefaultEditorKit.CopyAction());
				mntmCopy.setText("Copy");
				mntmCopy.setMnemonic(KeyEvent.VK_C);
				
				JMenuItem mntmPaste = new JMenuItem(new DefaultEditorKit.PasteAction());
				mntmPaste.setText("Paste");
				mntmPaste.setMnemonic(KeyEvent.VK_P);
				
			//mnView
			JMenu mnView = new JMenu("View");
			mnView.setMnemonic(KeyEvent.VK_V);
			
				JMenuItem mntmPrograms = new JMenuItem("Programs");
				mntmPrograms.setMnemonic(KeyEvent.VK_P);
				
				JMenuItem mntmVariables = new JMenuItem("Variables");
				mntmVariables.setMnemonic(KeyEvent.VK_V);
				
				JMenuItem mntmHistory = new JMenuItem("History");
				mntmHistory.setMnemonic(KeyEvent.VK_H);
				
			//mnMode
			JMenu mnMode = new JMenu("Mode");
			mnMode.setMnemonic(KeyEvent.VK_M);
			
				JMenuItem mntmStandard = new JMenuItem("Standard");
				mntmStandard.setMnemonic(KeyEvent.VK_S);
				
				JMenuItem mntmAlgebra = new JMenuItem("Algebra");
				mntmAlgebra.setMnemonic(KeyEvent.VK_A);
				
				JMenuItem mntmTrig = new JMenuItem("Trig");
				mntmTrig.setMnemonic(KeyEvent.VK_T);
				
				JMenuItem mntmCalculus = new JMenuItem("Calculus");
				mntmCalculus.setMnemonic(KeyEvent.VK_C);
			
			//mnHelp
			JMenu mnHelp = new JMenu("Help");
			mnHelp.setMnemonic(KeyEvent.VK_H);
			
				JMenuItem mntmGuide = new JMenuItem("Guide");
				mntmGuide.setMnemonic(KeyEvent.VK_G);
				mntmGuide.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JOptionPane.showMessageDialog(getContentPane(), helpmsg, "Guide", JOptionPane.PLAIN_MESSAGE);
					}
				});
			
				JMenuItem mntmAbout = new JMenuItem("About");
				mntmAbout.setMnemonic(KeyEvent.VK_A);
				mntmAbout.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JOptionPane.showMessageDialog(getContentPane(),
								"Calculator v3.0\n"
								+ "Developed by Austin Patton",
							    "About",
							    JOptionPane.PLAIN_MESSAGE);
					}
				});
				
				
				
		
		//Add all components
		mnFile.add(mntmExit);
		menuBar.add(mnFile);
		
		mnEdit.add(mntmCut);
		mnEdit.add(mntmCopy);
		mnEdit.add(mntmPaste);
		menuBar.add(mnEdit);
		
		mnView.add(mntmPrograms);
		mnView.add(mntmVariables);
		mnView.add(mntmHistory);
		menuBar.add(mnView);
		
		mnMode.add(mntmStandard);
		mnMode.add(mntmAlgebra);
		mnMode.add(mntmTrig);
		mnMode.add(mntmCalculus);
		menuBar.add(mnMode);
		
		mnHelp.add(mntmGuide);
		mnHelp.add(mntmAbout);
		menuBar.add(mnHelp);
		
		setJMenuBar(menuBar);
	}
	
	
	@SuppressWarnings("serial")
	private void createTextField()
	{//Creates the user input text field
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Calibri", Font.BOLD, 20));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		
		
		//ENTER BUTTONPRESS FUNCTION
	    int condition = textArea.WHEN_FOCUSED;  
	    InputMap inputMap = textArea.getInputMap(condition);
	    ActionMap actionMap = textArea.getActionMap();
	    KeyStroke enterStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
	    inputMap.put(enterStroke, enterStroke.toString());
		
	    
	    
	    actionMap.put(enterStroke.toString(), new AbstractAction()
	    {

	         @Override
	         public void actionPerformed(ActionEvent arg0)
	         {
	         
	        	 try
	        	 {
	        		 
	        		 int last  = textArea.getLineCount() - 1;
	        		 int start = textArea.getLineStartOffset(last);
	        		 int end   = textArea.getLineEndOffset(last);
	        		 
	        		 String text = textArea.getText().substring(start, end);
	        		 
	        		 if(ptv.funcs.isAction(text))
	        		 {
	        			 switch (text)
	        			 {	 	
	        			 	case "clear":
	        			 		textArea.setText("");
	        			 		break;
	        			 	case "help":
	        			 		textArea.append("\n");
	        			 		JOptionPane.showMessageDialog(getContentPane(), helpmsg, "Guide", JOptionPane.PLAIN_MESSAGE);
	        			 		break;
	        			 	case "cowculator":
	        			 		textArea.append("\n");
	        			 		ImageIcon icon = new ImageIcon(new URL("http://img4.wikia.nocookie.net/__cb20110412003057/creepypasta/images/f/fc/700cow.jpg"));
	        			 		JOptionPane.showMessageDialog(
	        	                        null,
	        	                        "COWS",
	        	                        "LOL", JOptionPane.INFORMATION_MESSAGE,
	        	                        icon);
	        			 		break;
	        			 	case "exit":
	        			 		System.exit(0);
	        			 		break;
	        			 }
	        			 
	        		 } else if(ptv.funcs.isFunction(text)) {
	        			 
	        			 String value = new String(text.substring(text.indexOf('(')+1, text.indexOf(')')));
	        			 
	        			 textArea.append("\n");
	        			 switch (text.substring(0, text.indexOf('(')))
	        			 {	 	
	        			 	case "sin":
	        			 		textArea.append(ptv.funcs.sinOf(ptv.convertToValue(itp.convertToPostfix(value)).toPlainString()));
	        			 		break;
	        			 	case "cos":
	        			 		textArea.append(ptv.funcs.cosOf(ptv.convertToValue(itp.convertToPostfix(value)).toPlainString()));
	        			 		break;
	        			 	case "tan":
	        			 		textArea.append(ptv.funcs.tanOf(ptv.convertToValue(itp.convertToPostfix(value)).toPlainString()));
	        			 		break;
	        			 	case "binary":
	        			 		textArea.append(ptv.funcs.toBinary(value));
	        			 		break;
	        			 	case "hex":
	        			 		textArea.append(ptv.funcs.toHex(value));
	        			 		break;
	        			 	case "set":
	        			 		textArea.append(ptv.funcs.toVar(value));
	        			 		break;
	        			 }
	        			 textArea.append("\n");
	        			 
	        		 } else if(text.isEmpty()) {
	        			 ;
	        		 } else {
	        			 textArea.append("\n");
	        			 textArea.append(ptv.convertToValue(itp.convertToPostfix(text)).toPlainString());
	        			 textArea.append("\n"); 
	        		 }//end if
	        		 		

	        	 } catch (Exception e) {
	        		 System.out.println("Calculator.java");
	        		 textArea.append(e.toString()+"\n");
	        	 }//end try
	        	  	 
	         }//end action performed
	         
	      });//end action map
		
		
	}
	
	
	
	
	
	public static void main(String[] args)
	{

        EventQueue.invokeLater(new Runnable()
        {//Places the application on the event queue to prevent hanging
            @Override
            public void run()
            {
                Calculator calc = new Calculator();
                calc.setVisible(true);
            }
        });
    }
    
	
	

}

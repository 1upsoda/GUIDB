package dataView;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


import database.controller.DBAppController;
import database.controller.DBController;

public class dataPanel extends JPanel
{

	private DBAppController baseController;
	private JButton queryButton;
	private SpringLayout baseLayout;

	/**
	 * @wbp.parser.constructor
	 */
	public dataPanel(DBAppController baseController, String table)
	{
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		queryButton = new JButton("Show Query");
		
		setupPanel(table);
		setupListeners();
		setupLayout();
	}

	public dataPanel(DBAppController dbAppController)
	{
		
	}

	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.WEST, queryButton, 139, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, queryButton, -10, SpringLayout.SOUTH, this);
	}

	private void setupListeners()
	{
//		ArrayList<JTextField> myTextFields = new ArrayList<JTextField>();
//		for(Component current : this.getComponents())
//		{
//			if(current instanceof JTextField)
//			{
//				myTextFields.add((JTextField)current);
//			}
//		}
//		
		
		queryButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				JFrame popFrame = new JFrame();
				popFrame.setSize(250, 250);
				popFrame.setContentPane(new dataPanel(baseController, "books"));
				popFrame.setVisible(true);
			}
			
		});
	}

	private void setupPanel(String selectedTable)
	{
		// TODO Auto-generated method stub
		this.setLayout(baseLayout);
		this.add(queryButton);
		this.setSize(400, 600);
		this.setBackground(Color.ORANGE);
		String [] columns = baseController.getDataController().getDatabaseColumnNames(selectedTable);
		int startOffset = 50;
		for(int count = 0; count<columns.length; count++)
		{
			JLabel test = new JLabel(columns[count]);
			JTextField textField = new JTextField(20);
			
			this.add(test);
			this.add(textField);
			baseLayout.putConstraint(SpringLayout.WEST, test, 139, SpringLayout.WEST, this);
			baseLayout.putConstraint(SpringLayout.WEST, textField, -10, SpringLayout.WEST, test);
			
			baseLayout.putConstraint(SpringLayout.NORTH, test, startOffset, SpringLayout.NORTH, this);
			baseLayout.putConstraint(SpringLayout.NORTH, textField, startOffset, SpringLayout.NORTH, this);
			
			startOffset += 50;
		}
	}

}

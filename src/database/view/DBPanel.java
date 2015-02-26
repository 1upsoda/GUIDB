package database.view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import database.controller.DBAppController;

public class DBPanel extends JPanel
{
/**
 * passes the info
 */
	private DBAppController baseController;
	private SpringLayout baseLayout;
	
	public DBPanel(DBAppController baseController)
	{
		this.baseController = baseController;
		
		baseLayout = new SpringLayout();
		
		
		setupPanel();
		setupLayout();
		setupListeners();
		
	}
	private void setupListeners()
	{
		// TODO Auto-generated method stub
		
	}
	private void setupLayout()
	{
		// TODO Auto-generated method stub
		
	}
	private void setupPanel()
	{
		setBackground(Color.YELLOW);

		setLayout(baseLayout);
		
	}
}

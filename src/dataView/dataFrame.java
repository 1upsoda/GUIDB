package dataView;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SpringLayout;


import database.controller.DBAppController;
import database.controller.DBController;

public class dataFrame extends JFrame
{
	private dataPanel basePanel;
	/**
	 * puts the panel in the frame for the GUI
	 * @param dbAppController
	 */
		public dataFrame(DBAppController dbAppController)

		{

			basePanel = new dataPanel(dbAppController, "books");
			

			setupFrame();

		}
	public dataFrame(DBController dataController)
	{
		
	}
	/**
	 * builds the frame of the window that holds the gui panel
	 */
		private void setupFrame()

		{

			setContentPane(basePanel);

			setLocation(5, 6);

			setSize(500, 500);

			setResizable(true);

			setVisible(true);
			
			

		}
	}

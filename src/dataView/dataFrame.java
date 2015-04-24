package dataView;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.SpringLayout;





import database.controller.DBAppController;
import database.controller.DBController;

public class dataFrame extends JFrame
{
	private dataPanel basePanel;
	private DBAppController baseController;
	/**
	 * puts the panel in the frame for the GUI
	 * @param dbAppController
	 */
		public dataFrame(DBAppController baseController)

		{

			basePanel = new dataPanel(baseController, "books");
			

			setupFrame();
			setupListeners();

		}
	
	private void setupListeners()
	{
		addWindowListener(new WindowListener()
		{

			@Override
			public void windowActivated(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0)
			{
				String chat = "";
				String selectedTable = "books";
				String [] columns = baseController.getDataController().getDatabaseColumnNames(selectedTable);
				for(int count = 0; count<columns.length; count++)
				{
					if(!columns[count].equalsIgnoreCase("id"))
					{
					chat += columns[count];
					}
				}
				basePanel.saveText(chat, false);
				
			}

			@Override
			public void windowDeactivated(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}
			
		});
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

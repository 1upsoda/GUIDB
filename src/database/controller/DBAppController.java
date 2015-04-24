package database.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import dataView.dataFrame;
import dataView.dataPanel;
import database.model.QueryInfo;
import database.view.DBFrame;
import database.view.DBPanel;

public class DBAppController
{
	/**
	 * the base frame of the GUI
	 */
	private dataFrame baseFrame;
	/**
	 * the base panel of the GUI
	 */
	private dataPanel myAppPanel;
	/**
	 * the database controlling the actuall info of the database
	 */
	private DBController dataController;
	
	private ArrayList<QueryInfo> queryList;

	public DBAppController()
	{

		setDataController(new DBController(this));
		
		queryList = new ArrayList<QueryInfo>();

		baseFrame = new dataFrame(this);
		QueryInfo a = new QueryInfo("lol",2);
		QueryInfo b = new QueryInfo("llo",3);
		QueryInfo c = new QueryInfo("olo",780);
		queryList.add(a);
		queryList.add(b);
		queryList.add(c);
		saveTimingInformation();

	}
	
	public void start()
	{
//		DBPanel myAppPanel = (DBPanel) baseFrame.getContentPane();
		
	}
	
	
	public dataFrame getBaseFrame() 
	{
		return baseFrame;
	}
	public DBController getDataController()
	{
		return dataController;
	}
	public void setBaseFrame(dataFrame baseFrame) 
	{
		this.baseFrame = baseFrame;
	}
	private void saveTimingInformation()
	{
		File saveFile;
		String fileName = "/Users/tpar4829/Documents/saved text.txt";
		
		PrintWriter outputWriter;
		
			try
			{
				outputWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName, false)));
				
				for(int spot = 0; spot<queryList.size(); spot++)
				{
					outputWriter.println(queryList.get(spot).getQuery() + queryList.get(spot).getQueryTime());
				}
				outputWriter.close();
			}
			catch(FileNotFoundException noExistingFile)
			{
				JOptionPane.showMessageDialog(null, "There is no file there");
				JOptionPane.showMessageDialog(null, noExistingFile.getMessage());
			}
			catch(IOException inputOutputError)
			{
				JOptionPane.showMessageDialog(null, "There is no file there");
				JOptionPane.showMessageDialog(null, inputOutputError.getMessage());
			}
		

	}
	private void loadTimingInformation()
	{
		try
		{
			File loadFile = new File("asdf.save");
			if(loadFile.exists())
			{
				queryList.clear();
				Scanner textScanner = new Scanner(loadFile);
				while(textScanner.hasNext())
				{
					String query = textScanner.nextLine();
					textScanner.next();
					queryList.add(new QueryInfo(query, textScanner.nextLong()));
				}
				textScanner.close();
				JOptionPane.showMessageDialog(getBaseFrame(), queryList.size() + " QueryInfo objects were loaded into the application");
			}
			else
			{
				JOptionPane.showMessageDialog(getBaseFrame(), "File not present. No QueryInfo objects loaded");
			}
		}
		catch(IOException currentError)
		{
			dataController.displayErrors(currentError);
		}
	}
	public void setDataController(DBController dataController)
	{
		this.dataController = dataController;
	}
	public ArrayList<QueryInfo> getQueryList()
	{
		return queryList;
	}
	
}

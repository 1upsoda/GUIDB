package database.controller;

import java.util.ArrayList;

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
	
	public void setDataController(DBController dataController)
	{
		this.dataController = dataController;
	}
	public ArrayList<QueryInfo> getQueryList()
	{
		return queryList;
	}
	
}

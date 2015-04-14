package database.controller;

import java.util.ArrayList;

import database.model.QueryInfo;
import database.view.DBFrame;
import database.view.DBPanel;

public class DBAppController
{
	/**
	 * the base frame of the GUI
	 */
	private DBFrame baseFrame;
	/**
	 * the base panel of the GUI
	 */
	private DBPanel myAppPanel;
	/**
	 * the database controlling the actuall info of the database
	 */
	private DBController dataController;
	
	private ArrayList<QueryInfo> queryList;

	public DBAppController()
	{

		setDataController(new DBController(this));
		
		queryList = new ArrayList<QueryInfo>();

		baseFrame = new DBFrame(this);

	}
	public void start()
	{
//		DBPanel myAppPanel = (DBPanel) baseFrame.getContentPane();
		
	}
	
	
	public DBFrame getBaseFrame() 
	{
		return baseFrame;
	}
	public DBController getDataController()
	{
		return dataController;
	}
	public void setBaseFrame(DBFrame baseFrame) 
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

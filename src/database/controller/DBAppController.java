package database.controller;





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

	public DBAppController()

	{

		setDataController(new DBController(this));

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
	
	
}

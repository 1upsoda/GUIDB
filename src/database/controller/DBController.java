package database.controller;

import java.sql.*;

import javax.swing.JOptionPane;

public class DBController
{
	/**
	 * the string that connects the database info to this program
	 */
	private String connectionString;
	/**
	 * an object that connects the database itself to the program
	 */
	private Connection databaseConnection;
	/**
	 * allows the transfer of info between the database and the GUI
	 *
	 */
	private DBAppController baseController;
/**
 * sets up the controller and all of the methods, 
 * @param baseController
 */
	public DBController(DBAppController baseController)
	{
		this.baseController = baseController;
		this.connectionString = "jdbc:mysql://localhost/game_info_database?user=root";
		checkDriver();
		setupConnection();
	}

	/**
	 * makes sure the driver doesn't crash anything
	 */
	private void checkDriver()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception currentException)
		{
			displayErrors(currentException);
			System.exit(1);
		}
		
	}
	
	/**
	 * makes sure nothing crashes it when you want to close the database
	 */
	public void closeConnection()
	{
		try
		{
			databaseConnection.close();
		}
		catch (SQLException currentException)
		{
			displayErrors(currentException);
		}
	}
/**
 * makes sure it doesn't crash when setting up the connection string
 */
	private void setupConnection()
	{
		try
		{
			databaseConnection = DriverManager.getConnection(connectionString);
		}
		catch(SQLException currentException)
		{
			displayErrors(currentException);
		}
	}
	/**
	 * the method to call when an exception pops up, can be used for all exceptions
	 * @param currentException
	 */
	private void displayErrors(Exception currentException)
	{
		JOptionPane.showMessageDialog(baseController.getBaseFrame(), "Exception: " + currentException.getMessage());
		if(currentException instanceof SQLException)
		{
			JOptionPane.showMessageDialog(baseController.getBaseFrame(), "SQL State: " + ((SQLException) currentException).getSQLState());
			JOptionPane.showMessageDialog(baseController.getBaseFrame(), "SQL Error Code: " + ((SQLException) currentException).getErrorCode());
		}
		
	}
	

}

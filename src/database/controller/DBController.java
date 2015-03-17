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
	private String query;
	

	/**
	 * sets up the controller and all of the methods,
	 * 
	 * @param baseController
	 */
	public DBController(DBAppController baseController)
	{
		this.baseController = baseController;
		this.connectionString = "jdbc:mysql://localhost/information_schema?user=root";
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
		catch (Exception currentException)
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
		catch (SQLException currentException)
		{
			displayErrors(currentException);
		}
	}
	
	private boolean checkQueryForDataViolation()
	{
		if(query.toUpperCase().contains(" DROP ") 
				|| query.toUpperCase().contains(" TRUNCATE ") 
				|| query.toUpperCase().contains(" SET ") 
				|| query.toUpperCase().contains(" ALTER "))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * allows any query to be used
	 * @param query
	 * @return
	 */
	public String [][] selectQueryResults(String query)
	{
		String[][] results;

		this.query = query;
		try
		{
			if(checkQueryForDataViolation())
			{
				throw new SQLException("There was an attempt at a data violation",
						" you dont get to mess the data up here :D", Integer.MIN_VALUE);
			}
			// sets up the first statement//
			Statement firstStatement = databaseConnection.createStatement();
			// actually gets the statement stuff back//
			ResultSet answers = firstStatement.executeQuery(query);
			int columnCount = answers.getMetaData().getColumnCount();
			
			// puts the cursor at the end to get the length of the array//
			answers.last();
			int numberOfRows = answers.getRow();
			// puts the cursor back at the beginning to go through the loop
			answers.beforeFirst();
			// pretty much converts back into a 1d array for right now, but with
			// the capability of being a 2d//
			results = new String[numberOfRows][columnCount];

			// while there is stuff still in the answers list keep doing stuff//
			while (answers.next())
			{
				// get string removes the string and puts it elsewhere, so
				// getstring(1) will get the
				// first thing, which makes the second string the first one...
				for(int col = 0; col<columnCount; col++)
				{
					results[answers.getRow() - 1][col] = answers.getString(col+1);
				}
			}

			answers.close();
			firstStatement.close();
		}
		catch (SQLException currentException)
		{
			results = new String[][] { 	{ "Query unsuccessful" }, 
										{"Maybe use a better query string?"}, 
										{currentException.getMessage()} };
			displayErrors(currentException);

		}

		return results;
	}

	/**
	 * gets the titles of the columns from the databases
	 * 
	 * @return
	 */
	public String [][] realResults()
	{
		String[][] results;
		query = "SELECT * FROM `INNODB_SYS_COLUMNS`";

		try
		{
			// sets up the first statement//
			Statement firstStatement = databaseConnection.createStatement();
			// actually gets the statement stuff back//
			ResultSet answers = firstStatement.executeQuery(query);
			int columnCount = answers.getMetaData().getColumnCount();
			
			// puts the cursor at the end to get the length of the array//
			answers.last();
			int numberOfRows = answers.getRow();
			// puts the cursor back at the beginning to go through the loop
			answers.beforeFirst();
			// pretty much converts back into a 1d array for right now, but with
			// the capability of being a 2d//
			results = new String[numberOfRows][columnCount];

			// while there is stuff still in the answers list keep doing stuff//
			while (answers.next())
			{
				// get string removes the string and puts it elsewhere, so
				// getstring(1) will get the
				// first thing, which makes the second string the first one...
				for(int col = 0; col<columnCount; col++)
				{
					results[answers.getRow() - 1][col] = answers.getString(col+1);
				}
			}

			answers.close();
			firstStatement.close();
		}
		catch (SQLException currentException)
		{
			results = new String[][] { { "empty" } };
			displayErrors(currentException);

		}

		return results;
	}
	public String[] getMetaDataTitles()
	{
		String[] columns;
		query = "SELECT * FROM `INNODB_SYS_COLUMNS`";

		try
		{
			Statement firstStatement = databaseConnection.createStatement();
			ResultSet answers = firstStatement.executeQuery(query);

			ResultSetMetaData answerData = answers.getMetaData();
			columns = new String[answerData.getColumnCount()];

			// sets the name of each of the objects in the one column to the
			// name of the next table//
			for (int column = 0; column < answerData.getColumnCount(); column++)
			{
				columns[column] = answerData.getColumnName(column + 1);
			}

			answers.close();
			firstStatement.close();
		}
		catch (SQLException currentException)
		{
			columns = new String[] { "empty" };
			displayErrors(currentException);

		}

		return columns;
	}

	/**
	 * shows the info from the database in a better format (translates into a 2D
	 * array)
	 * 
	 * @return
	 */
	public String[][] testResults()
	{
		String[][] results;
		query = "SHOW TABLES";

		try
		{
			// sets up the first statement//
			Statement firstStatement = databaseConnection.createStatement();
			// actually gets the statement stuff back//
			ResultSet answers = firstStatement.executeQuery(query);

			// puts the cursor at the end to get the length of the array//
			answers.last();
			int numberOfRows = answers.getRow();
			// puts the cursor back at the beginning to go through the loop
			answers.beforeFirst();
			// pretty much converts back into a 1d array for right now, but with
			// the capability of being a 2d//
			results = new String[numberOfRows][1];

			// while there is stuff still in the answers list keep doing stuff//
			while (answers.next())
			{
				// get string removes the string and puts it elsewhere, so
				// getstring(1) will get the
				// first thing, which makes the second string the first one...
				results[answers.getRow() - 1][0] = answers.getString(1);
			}

			answers.close();
			firstStatement.close();
		}
		catch (SQLException currentException)
		{
			results = new String[][] { { "empty" } };
			displayErrors(currentException);

		}

		return results;
	}

	/**
	 * shows the info from a database
	 * 
	 * @return
	 */
	public String displayTables()
	{
		String tableNames = "";
		query = "SHOW TABLES";

		try
		{
			// sets up the first statement//
			Statement firstStatement = databaseConnection.createStatement();
			// actually gets the statement stuff back//
			ResultSet answers = firstStatement.executeQuery(query);
			// while there is stuff still in the answers list keep doing stuff//
			while (answers.next())
			{
				// get string removes the string and puts it elsewhere, so
				// getstring(1) will get the first thing, which makes the second
				// string the first one...//
				tableNames += answers.getString(1) + "\n";
			}
			answers.close();
			firstStatement.close();

		}
		catch (SQLException currentError)
		{
			displayErrors(currentError);
		}

		return tableNames;
	}

	/**
	 * the method to call when an exception pops up, can be used for all
	 * exceptions
	 * 
	 * @param currentException
	 */
	public void displayErrors(Exception currentException)
	{
		JOptionPane.showMessageDialog(baseController.getBaseFrame(), "Exception: " + currentException.getMessage());
		if (currentException instanceof SQLException)
		{
			JOptionPane.showMessageDialog(baseController.getBaseFrame(), "SQL State: " + ((SQLException) currentException).getSQLState());
			JOptionPane.showMessageDialog(baseController.getBaseFrame(), "SQL Error Code: " + ((SQLException) currentException).getErrorCode());
		}

	}

	/**
	 * a way to add info to the database, currently only adds the specific
	 * things i say...
	 * 
	 * @return
	 */

	public int insertSample()
	{
		int rowsAffected = -1;
		String query = "INSERT INTO `game_info_database`.`user_info` " + "(`id`, `username`, `password`, `email`, `alignment`) " + "VALUES (NULL, 'Loller', 'Lolz', 'lol@lol.com', 3);";

		try
		{
			Statement insertStatement = databaseConnection.createStatement();
			rowsAffected = insertStatement.executeUpdate(query);
			insertStatement.close();
		}
		catch (SQLException currentError)
		{
			displayErrors(currentError);
		}

		return rowsAffected;
	}

	public String getQuery()
	{
		return query;
	}

	public void setQuery(String query)
	{
		this.query = query;
	}

}

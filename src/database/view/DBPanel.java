package database.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import database.controller.DBAppController;

public class DBPanel extends JPanel
{
	/**
	 * passes the info
	 */
	private DBAppController baseController;
	private SpringLayout baseLayout;
	private JButton queryButton;
	private JScrollPane displayPane;
	private JTextArea displayArea;
	private JTable resultsTable;

	public DBPanel(DBAppController baseController)
	{
		this.baseController = baseController;

		baseLayout = new SpringLayout();
		queryButton = new JButton("Click here to test the query");

		displayArea = new JTextArea(10, 30);
		displayPane = new JScrollPane(displayArea);

		//setupDisplayPane();
		setupTable();
		setupPanel();
		setupLayout();
		setupListeners();

	}

	private void setupListeners()
	{
		queryButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String[] temp = baseController.getDataController().getMetaDataTitles();
				for (String current : temp)
				{
					displayArea.setText(displayArea.getText() + " Column : " + current + "\n");
				}

			}

		});

	}

	private void setupDisplayPane()
	{
		displayArea.setWrapStyleWord(true);
		displayArea.setLineWrap(true);
		displayArea.setEditable(false);
		displayArea.setBackground(Color.PINK);
	}

	private void setupTable()
	{
		DefaultTableModel basicData = new DefaultTableModel(baseController.getDataController().realResults(), baseController.getDataController().getMetaDataTitles());
		resultsTable = new JTable(basicData);
		displayPane = new JScrollPane(resultsTable);
	}

	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.NORTH, queryButton, 40, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, queryButton, 80, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, displayPane, 80, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, displayPane, 80, SpringLayout.WEST, this);

	}

	private void setupPanel()
	{
		this.setBackground(Color.YELLOW);
		this.setSize(1000, 1000);
		this.setLayout(baseLayout);
		this.add(displayPane);
		this.add(queryButton);

	}
}

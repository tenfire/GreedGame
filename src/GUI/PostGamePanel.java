package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class PostGamePanel extends MyPanel {
	
	private JButton restartButton;
	private JLabel winnerLabel;
	
	private JPanel buttonPanel;
	private JPanel winnerPanel;
	private ScorePanel scorePanel;
	
	public PostGamePanel()
	{
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		winnerPanel = new JPanel();
		winnerPanel.setLayout(new FlowLayout());
		
		scorePanel = new ScorePanel();
		
		restartButton = new JButton("Restart");
		restartButton.setVisible(true);
		restartButton.addActionListener(this);
		
		winnerLabel = new JLabel();
		winnerLabel.setVisible(true);
		winnerLabel.setText("");
		
		buttonPanel.add(restartButton);
		winnerPanel.add(winnerLabel);
		
		setLayout(new BorderLayout());
		add(scorePanel, BorderLayout.NORTH);
		add(winnerPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	@Override
	public void SetCallback(GUI_Callback callback) 
	{
		super.SetCallback(callback);
		scorePanel.SetCallback(callback);
	}
	
	@Override
	public void UpdateData() 
	{
		// TODO Auto-generated method stub
		int winnerIndex = callback.GetWinnerIndex();
		String winner = callback.GetPlayerName(winnerIndex);
		String type = callback.GetPlayerType(winnerIndex);
		winnerLabel.setText("The winner is " + winner +" (" + type + ")");
		scorePanel.UpdateData();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == restartButton)
		{
			if (!CheckCallback())
				return;
			callback.Restart();
		}
	}
/*	
	private String GetWinner()
	{
		if (!CheckCallback())
			return "";
		int winner = callback.GetWinnerIndex();
		
		String[][] playerData = callback.GetPlayerData();
		
		if(playerData != null)
		{
			if (playerData.length > winner)
			{
				String name, points, type;
				name = playerData[winner][0];
				points = playerData[winner][2];
				type = playerData[winner][1];
			
				return name + " won with " + points + "points (As a " + type + ")";
			}
		}		
		
		
		return "Winner nr: " + winner;
		
	}
*/
}

package GUI;

import java.util.ArrayList;

public interface GUI_Interface 
{
	public enum GUIState
	{
		PreGame, Roll, SelectScore, AI, PostGame
	}
	
	public void Initialize();
	
	public void SetCallback(GUI_Callback callback);

	public void SetGUIState(GUIState state);
	
	public void SetDiceValues(ArrayList<Integer> diceValues);
	
	public ArrayList<Integer> GetSelectedDice();
	
}

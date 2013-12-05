package Game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class DiceManager implements DiceManager_Interface
{
	public static final int NUMDICE = 6;
	
	Die[] dice;
	ArrayList<Integer> lockedDice;
	ArrayList<Integer> selectedDice;
	
	public DiceManager()
	{
		
		lockedDice = new ArrayList<Integer>();
		selectedDice = new ArrayList<Integer>();
		
		dice = new Die[NUMDICE];
		for (int i = 0; i < NUMDICE; i++)
			dice[i] = new Die();
	}
	
	private boolean CheckIndex(int index)
	{
		return index >= 0 && index < dice.length;
	}
	@Override
	public int GetValue(int index)
	{
		if (CheckIndex(index))
			return dice[index].GetValue();
		return -1;
	}
	
	@Override
	public void LockAndRoll()
	{
		lockedDice.addAll(selectedDice);
		selectedDice.clear();
		
		for (int i = 0; i < NUMDICE; i++)
		{
			if (!lockedDice.contains(i))
			{
				//System.out.println("Rolled: " + i);
				dice[i].Roll();
			}
		}
	}

	
	/*
	@Override
	public void LockDie(int index)
	{
		if (!lockedDice.contains(index) && !selectedDice.contains(index) )
		{
			System.out.println("Locked: " + index);
			lockedDice.add(index);
		}
	}
	@Override
	public void UnlockDie(int index)
	{
		if (lockedDice.contains(index) && !selectedDice.contains(index) )
		{
			System.out.println("Unlocked: " + index);
			lockedDice.remove((Object)index);
		}
	}
	*/
	
	@Override
	public void SelectDie(int index)
	{
		if (!selectedDice.contains(index) && !lockedDice.contains(index))
		{
			System.out.println("Selected: " + index);
			selectedDice.add(index);
		}
	}
	
	@Override
	public boolean SelectValues(int[] values)
	{
		//int[] temp = values;
		int[] temp = new int[values.length];
		
		System.arraycopy(values, 0, temp, 0, values.length);
		
		
		for (int i = 0; i < dice.length; i++)
		{
			if (!selectedDice.contains(i) && !selectedDice.contains(i))
			{
				int diceValue = dice[i].GetValue();
				for (int j = 0; j < temp.length; j++)
				{
					if (temp[j] == diceValue)
					{
						temp[j] = -1;
						break;
					}
				}
			}
		}
		
		for (int i = 0; i < temp.length; i++)
		{
			if (temp[i] != -1)
				return false;
		}
		
		System.arraycopy(values, 0, temp, 0, values.length);
		for (int i = 0; i < dice.length; i++)
		{
			if (!selectedDice.contains(i) && !selectedDice.contains(i))
			{
				int diceValue = dice[i].GetValue();
				for (int j = 0; j < temp.length; j++)
				{
					if (temp[j] == diceValue)
					{
						temp[j] = -1;
						SelectDie(i);
						break;
					}
				}
			}
		}
		
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void UnselectDie(int index)
	{
		if (selectedDice.contains(index))
		{
			System.out.println("Unselected: " + index);
			selectedDice.remove((Object)index);
		}
	}
	
	@Override
	public boolean isDieSelected(int index)
	{
		return selectedDice.contains(index);
	}
	@Override
	public boolean isDieLocked(int index)
	{
		return lockedDice.contains(index);
	}
	@Override
	public void SelectAll()
	{
		selectedDice.clear();
		for (int i = 0; i < NUMDICE; i++)
			SelectDie(i);		
	}
	@Override
	public void UnselectAll()
	{
		selectedDice.clear();	
	}
	@Override
	public void Reset()
	{
		lockedDice.clear();
		selectedDice.clear();
	}

	@Override
	public int[] GetSelectedValues() {
		// TODO Auto-generated method stub
		int[] selected = new int[selectedDice.size()];
		
		for (int i = 0; i < selected.length; i ++)
		{
			selected[i] = dice[selectedDice.get(i)].GetValue();
		}
		
		return selected;
	}

	@Override
	public int[] GetFreeValues() 
	{
		// TODO Auto-generated method stub
		int numFreeDice = dice.length - (selectedDice.size() + lockedDice.size());
		int[] freeVal = new int[numFreeDice];
		
		int k = 0;
		for (int i = 0; i < dice.length; i++)
		{
			if (!selectedDice.contains(i) && !selectedDice.contains(i))
			{
				if (k < freeVal.length)
				{
					freeVal[k] = GetValue(i);
					k++;
				}
			}
		}
		
		return freeVal;
	}

	@Override
	public boolean IsAllLockedOrSelected() 
	{
		// TODO Auto-generated method stub
		for (int i = 0; i < dice.length; i++)
			if (!lockedDice.contains(i) && !selectedDice.contains(i))
				return false;
		return true;
	}

	@Override
	public int GetNumberOfSelectedDice() {
		// TODO Auto-generated method stub
		return lockedDice.size();
	}

	@Override
	public int GetNumberOfLockedDice() {
		// TODO Auto-generated method stub
		return selectedDice.size();
	}



}

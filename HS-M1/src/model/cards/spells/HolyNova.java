package model.cards.spells;

import java.util.ArrayList;
import model.cards.Rarity;
import model.cards.minions.Minion;

public class HolyNova extends Spell implements AOESpell {

	public HolyNova() 
	{
		super("Holy Nova", 5, Rarity.BASIC);
	
	}

	@Override
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) 
	{
		for (int i=0 ;i<oppField.size();) 
		{
			Minion minion = oppField.get(i);
			if(minion.isDivine())
			{
				minion.setDivine(false);
				i++;
			}
			else 
		 {
			if(minion.getCurrentHP()-2>0)
			 {
				minion.setCurrentHP(minion.getCurrentHP()-2);
				i++;
			 }
			else
			{
				minion.setCurrentHP(minion.getCurrentHP()-2);
			}
		 }
		}			
		for (int i=0;i<curField.size();i++)
		{
			Minion minion = curField.get(i);
			int currHealth = minion.getCurrentHP();
			minion.setCurrentHP(currHealth+2);
			
		}
		
		
	}

}

package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class Flamestrike extends Spell implements AOESpell {

	
	public Flamestrike()
	{
		super("Flamestrike",7,Rarity.BASIC);
	}

	@Override
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) 
	{
		
		for (int i =0 ; i<oppField.size();)
		{
			Minion minion = oppField.get(i);
			if(minion.isDivine())
				{
				minion.setDivine(false);
				i++;
				}
			else 
			{
				if(minion.getCurrentHP()-4>0)
				 {
					minion.setCurrentHP(minion.getCurrentHP()-4);
					i++;
				 }
				else
				{
					minion.setCurrentHP(minion.getCurrentHP()-4);
				}
			}
				
			
		}
	}
	
}

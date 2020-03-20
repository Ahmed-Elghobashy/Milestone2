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
		for (Minion minion : oppField) 
		{
			int CurrHealth = minion.getCurrentHP();
			if(CurrHealth-2<= 0)
			{
				//Here we are supposed to notify the Minionlistener(which is the hero) of the minion's death but the listener attribute
				//in the Minion Class is write only and we don't have access to the hero .
			}
			else 
			minion.setCurrentHP(CurrHealth-2);
		}
		for (Minion minion : curField)
		{
			int currHealth = minion.getCurrentHP();
			if(currHealth+2 <= minion.getMaxHP())
				minion.setCurrentHP(minion.getMaxHP());
			else 
			{
				minion.setCurrentHP(currHealth+2);
			}
		}
		
		
	}

}

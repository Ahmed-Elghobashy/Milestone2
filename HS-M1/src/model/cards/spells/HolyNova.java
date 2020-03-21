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
			if(minion.isDivine())
			{
				minion.setDivine(false);
			}
			else
			  minion.setCurrentHP(CurrHealth-2);
		}
		for (Minion minion : curField)
		{
			int currHealth = minion.getCurrentHP();
			minion.setCurrentHP(currHealth+2);
			
		}
		
		
	}

}

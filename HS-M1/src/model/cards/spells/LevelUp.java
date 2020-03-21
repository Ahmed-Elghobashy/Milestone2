package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class LevelUp extends Spell implements FieldSpell 
{

	public LevelUp() 
	{
		super("Level Up!", 6, Rarity.EPIC);
		
	}

	@Override
	public void performAction(ArrayList<Minion> field) 
	{
	 for (Minion minion : field) 
	  {
		 if(minion.getName().equals("Silver Hand Recruit"))
		 {
			 minion.setMaxHP(minion.getMaxHP()+1);
			 minion.setAttack(minion.getAttack()+1);
			 minion.setCurrentHP(minion.getCurrentHP()+1);
		 }
			 
	  }	
	}

}

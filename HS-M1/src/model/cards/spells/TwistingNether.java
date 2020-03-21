package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class TwistingNether extends Spell implements AOESpell {

	public TwistingNether() {
		super("Twisting Nether", 8, Rarity.EPIC);

	}

	@Override
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) 
	{
      for (Minion minion : oppField)
      {
    	  minion.setCurrentHP(0);
	  }	
      for (Minion minion : curField)
      {
    	  minion.setCurrentHP(0);
	  }	
	}

}

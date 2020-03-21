package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;

public class Polymorph extends Spell implements MinionTargetSpell {

	public Polymorph() {
		super("Polymorph", 4, Rarity.BASIC);
	}

	@Override
	public void performAction(Minion m) throws InvalidTargetException 
	{
	 // also here we must check if the minion is a friendly Minion and if it is we throw the InvalidTargetException
		m = new Minion("Sheep",1, Rarity.BASIC, 1, 1, false, false, false);
		
	}

}

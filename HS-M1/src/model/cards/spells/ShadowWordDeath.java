package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;

public class ShadowWordDeath extends Spell implements MinionTargetSpell {

	public ShadowWordDeath() {
		super("Shadow Word: Death", 3, Rarity.BASIC);
		
	}

	@Override
	public void performAction(Minion m) throws InvalidTargetException 
	{
		
	// We should check if the minion is a friendly minion and if it is we should throw InvalidTargetException
		if(m.getCurrentHP()<5)
			throw new InvalidTargetException();
	// Should also notify the game listener of the minion's death
        m.setDivine(false);
		m.setCurrentHP(0);
		
		
	
	}
}

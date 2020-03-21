package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;

public class SealOfChampions extends Spell implements MinionTargetSpell {

	public SealOfChampions() {
		super("Seal of Champions", 3, Rarity.COMMON);
		
	}

	@Override
	public void performAction(Minion m) throws InvalidTargetException 
	{
		// We should check if the minion is not a friendly minion and if it is we should throw InvalidTargetException
		m.setDivine(true);
		m.setAttack(m.getAttack()+3);
		
	}

	
}

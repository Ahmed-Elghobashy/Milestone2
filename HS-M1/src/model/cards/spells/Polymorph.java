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
		m.setName("Sheep");
		m.setAttack(1);
		m.setDivine(false);
		m.setSleeping(true);
		m.setTaunt(false);
		m.setCurrentHP(1);
		m.setManaCost(1);
		m.setMaxHP(1);
		
		
	}

}

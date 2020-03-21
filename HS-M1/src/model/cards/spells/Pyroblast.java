package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class Pyroblast extends Spell implements HeroTargetSpell, MinionTargetSpell {
	public Pyroblast()
	{
		super("Pyroblast", 10, Rarity.EPIC);
	}

	@Override
	public void performAction(Minion m) throws InvalidTargetException 
	{
		// We should check if the minion is a friendly minion and if it is we should throw InvalidTargetException
		m.setCurrentHP(m.getCurrentHP()-10);
		
	}

	@Override
	public void performAction(Hero h) 
	{
		h.setCurrentHP(h.getCurrentHP()-10);
		
	}
	
}

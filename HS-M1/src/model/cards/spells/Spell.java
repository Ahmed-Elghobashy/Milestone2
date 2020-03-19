package model.cards.spells;

import model.cards.Card;
import model.cards.Rarity;

public abstract class Spell extends Card{

	public Spell(String name, int mana ,Rarity rarity) {
super(name,mana,rarity);
	}
}

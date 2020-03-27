package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.CurseOfWeakness;
import model.cards.spells.SiphonSoul;
import model.cards.spells.Spell;
import model.cards.spells.TwistingNether;

public class Warlock extends Hero {

	public Warlock() throws IOException, CloneNotSupportedException {
		super("Gul'dan");
	}

	@Override
	public void buildDeck() throws IOException, CloneNotSupportedException {
		ArrayList<Minion> neutrals= getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"),13);
		for (Minion minion : neutrals) 
		{
			minion.setListener(this);
		}
		getDeck().addAll(neutrals);
		for(int i = 0 ; i < 2; i++)
		{
			getDeck().add(new CurseOfWeakness());
			getDeck().add(new SiphonSoul());
			getDeck().add(new TwistingNether());
		}
		Minion wilfred=new Minion("Wilfred Fizzlebang",6,Rarity.LEGENDARY,4,4,false,false,false);
		wilfred.setListener(this);
		getDeck().add(wilfred);
		Collections.shuffle(getDeck());

	}

	public void useHeroPower(Hero target) throws NotEnoughManaException, HeroPowerAlreadyUsedException,
			NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException
	{
			super.useHeroPower();
			if(this.getHand().size()==10)
				throw new FullHandException(getDeck().get(0));
//			else 
//			{
//				if(hasWilfred() && !getDeck().isEmpty())
//					getDeck().get(0).setManaCost(0);
//			  getHand().add(drawCard());
//			}
			target.setCurrentHP(target.getCurrentHP()-2);
			this.setCurrentManaCrystals(getCurrentManaCrystals()-2);
			this.setHeroPowerUsed(true);
			
	}
	public void useHeroPower() throws NotEnoughManaException, HeroPowerAlreadyUsedException,
	NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException
{
	super.useHeroPower();
	if(this.getHand().size()==10)
		throw new FullHandException(getDeck().get(0));
	else 
	{
		if(hasWilfred() && !getDeck().isEmpty())
			if(!(getDeck().get(0) instanceof Spell))
			getDeck().get(0).setManaCost(0);
	  drawCard();
	}
	this.setCurrentHP(this.getCurrentHP()-2);
	this.setCurrentManaCrystals(getCurrentManaCrystals()-2);
	this.setHeroPowerUsed(true);
	
}
	
	
	public boolean hasWilfred()
	{
		for(Minion minion : getField())
			if(minion.getName().equals("Wilfred Fizzlebang"))
				return true;
		return false;
	}
	

}

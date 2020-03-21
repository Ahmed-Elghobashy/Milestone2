package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.validation.Validator;

import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.KillCommand;
import model.cards.spells.MultiShot;

public class Hunter extends Hero {

	public Hunter() throws IOException, CloneNotSupportedException 
	{
		super("Rexxar");
	}

	@Override
	public void buildDeck() throws IOException, CloneNotSupportedException 
	{
		ArrayList<Minion> neutrals= getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"),15);
		getDeck().addAll(neutrals);
		for(int i = 0 ; i < 2; i++)
		{
			getDeck().add(new KillCommand());
			getDeck().add(new MultiShot());
			
		}
		Minion krush=(new Minion("King Krush", 9, Rarity.LEGENDARY, 8, 8, false, false, true));
		
		getDeck().add(krush);
		Collections.shuffle(getDeck());
	}

	@Override
	public void useHeroPower(Object target) throws NotEnoughManaException, HeroPowerAlreadyUsedException,
			NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException 
	{
		if(target instanceof Hero)
		{
			((Hero) target).setCurrentHP(((Hero) target).getCurrentHP()-2);
		}
		
	}
	
	
}

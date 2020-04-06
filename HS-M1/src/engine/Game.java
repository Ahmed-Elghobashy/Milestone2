package engine;



import java.io.IOException;
import java.util.ArrayList;

import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.cards.spells.Flamestrike;
import model.cards.spells.HolyNova;
import model.cards.spells.Polymorph;
import model.cards.spells.Spell;
import model.heroes.Hero;
import model.heroes.HeroListener;
import model.heroes.Warlock;

public class Game implements ActionValidator , HeroListener 
{
	private Hero firstHero;
	private Hero secondHero;
	private Hero currentHero;
	private Hero opponent;
	private GameListener listener;
	
	public Game(Hero p1, Hero p2) throws FullHandException, CloneNotSupportedException
	{
		firstHero=p1;
		secondHero=p2;
		firstHero.setListener(this);
		secondHero.setListener(this);
		firstHero.setValidator(this);
		secondHero.setValidator(this);
		int coin = (int) (Math.random()*2);
		currentHero= coin==0?firstHero:secondHero;
		opponent= currentHero==firstHero?secondHero:firstHero;
		currentHero.setCurrentManaCrystals(1);
		currentHero.setTotalManaCrystals(1);
		for(int i=0;i<3;i++)
			currentHero.drawCard();
		for(int i=0;i<4;i++)
			opponent.drawCard();
		
	}

	public Hero getCurrentHero()
	{
		return currentHero;
	}

	public Hero getOpponent() {
		return opponent;
	}

	@Override
	public void validateTurn(Hero user) throws NotYourTurnException
	{
		if(currentHero == user)
			return;
		else {
			throw new NotYourTurnException();
		}
		
	}

	@Override
	public void validateAttack(Minion attacker, Minion target)
			throws CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException 
	{
		if(currentHero.getField().contains(target))
		{
			throw new InvalidTargetException();
		}
		
       if(attacker.isSleeping() || attacker.isAttacked() || attacker.getAttack()==0)
		{
		 throw new CannotAttackException();
		} 
		if(!currentHero.getField().contains(attacker))
		{
			throw new NotSummonedException();
			
		}
		if(!opponent.getField().contains(target))
			throw new NotSummonedException();
		if(!target.isTaunt() && hasTaunt(opponent))
		{
			throw new TauntBypassException() ;
		}
		
	}

	@Override
	public void validateAttack(Minion attacker, Hero target)
			throws CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException 
	{
		if(currentHero==target)
		{
			throw new InvalidTargetException();
		}
		if(attacker.getAttack()==0)
		{
			throw new CannotAttackException();
		}
		if(attacker.isSleeping() || attacker.isAttacked())
		{
		 throw new CannotAttackException();
		} 
		if(!currentHero.getField().contains(attacker))
		{
			throw new NotSummonedException();
			
		}	
		if(hasTaunt(opponent))
		{
			throw new TauntBypassException() ;
		}
		if(attacker instanceof Icehowl)
			throw new InvalidTargetException();
		
	}

	@Override
	public void validateManaCost(Card card) throws NotEnoughManaException 
	{
		
		if(currentHero.getCurrentManaCrystals()-card.getManaCost() <0)
			throw new NotEnoughManaException();
		
	}

	@Override
	public void validatePlayingMinion(Minion minion) throws FullFieldException 
	{
		if(currentHero.getField().size() >= 7)
		{
			throw new FullFieldException();
		}
	}
	
	

	@Override
	public void validateUsingHeroPower(Hero hero) throws NotEnoughManaException, HeroPowerAlreadyUsedException 
	{
		
		if(currentHero.isHeroPowerUsed())
			throw new HeroPowerAlreadyUsedException();
		if(currentHero.getCurrentManaCrystals()<2)
			throw new NotEnoughManaException();
		
	}
	
	public static boolean hasTaunt(Hero hero)
	{
		for (Minion minion : hero.getField()) {
		      if (minion.isTaunt())
					return true;
		}
		
		return false;
	}

	public void onHeroDeath()
	{
			listener.onGameOver();
	}

	public void damageOpponent(int amount)
	{
		
		opponent.setCurrentHP(opponent.getCurrentHP()-amount);
		
	}

	public void endTurn() throws FullHandException, CloneNotSupportedException
	{
		// swap the two heroes 
		Hero tempHero = currentHero;
		currentHero=opponent;
		opponent= tempHero;
		
		//updates the current hero manaCrystal
		currentHero.setHeroPowerUsed(false);
		if(currentHero.getTotalManaCrystals()<10)
		 currentHero.setTotalManaCrystals(currentHero.getTotalManaCrystals()+1);
		currentHero.setCurrentManaCrystals(currentHero.getTotalManaCrystals());
		updateMinions(currentHero);
		currentHero.drawCard();
		
		
		
		
	}
	
	public Game() 
	{
		
	}
	
	public static void updateMinions(Hero hero)
	{
		ArrayList<Minion> heroField=hero.getField();
		for (Minion minion : heroField) 
		{
			minion.setAttacked(false);
			minion.setSleeping(false);
		}
	}

	public void setListener(GameListener listener) {
		this.listener = listener;
	}

	
//	public static void main(String[] args) throws IOException, CloneNotSupportedException, FullHandException, NotYourTurnException, NotEnoughManaException, FullFieldException, HeroPowerAlreadyUsedException, InvalidTargetException
//	{
//		Warlock w1 = new Warlock();
//		Warlock w2 = new Warlock();
//		Game G1 = new Game(w1, w2);
//		Minion wilfred=new Minion("Wilfred Fizzlebang",6,Rarity.LEGENDARY,4,4,false,false,false);
//		w1.setCurrentManaCrystals(20);
//		w2.setCurrentManaCrystals(20);
//		w1.getHand().add(wilfred);
//		w2.getHand().add(wilfred);
//		wilfred.setListener(w1);
//		Minion testMinion = new Minion("7masa", 0, null, 0, 2, false, false, false);
//		Minion testMinion1 = new Minion("7masa", 0, null, 0, 2, false, false, false);
//		Minion testMinion2= new Minion("7masa", 0, null, 0, 2, false, false, false);
//		testMinion.setListener(w1);
//		testMinion1.setListener(w1);
//		testMinion2.setListener(w1);
//		w1.playMinion(wilfred);
////		Flamestrike f1 = new Flamestrike();
////		Polymorph p = new Polymorph();
////		HolyNova h = new HolyNova();
////		h.performAction(w1.getField(), w2.getField());
////		System.out.println(testMinion1.getCurrentHP());
//		Minion chromaggusMinion = new Minion("Chromaggus", 0, Rarity.BASIC, 0, 10, false, false, false);
//		chromaggusMinion.setListener(w1);
//		w1.getHand().add(chromaggusMinion);
//		w1.playMinion(chromaggusMinion);
//		
////		while(!(w1.getDeck().get(0) instanceof Spell))
////		{
////			w1.getDeck().remove(0);
////		}
//		w1.getDeck().clear();
//		Minion testMinion4 = new Minion("7masa", 5, null, 0, 2, false, false, false);
//
//		Spell testSpell = new Polymorph();
//		w1.getDeck().add(testMinion4);
//		Card card = w1.drawCard();
//		System.out.println(card.getManaCost() + card.getName());
//	}		
	

}

package engine;



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
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.HeroListener;

public class Game implements ActionValidator , HeroListener 
{
	private Hero firstHero;
	private Hero secondHero;
	private Hero currentHero;
	private Hero opponent;
	
	public Game(Hero p1, Hero p2)
	{
		firstHero=p1;
		secondHero=p2;
		
		int coin = (int) (Math.random()*2);
		currentHero= coin==0?firstHero:secondHero;
		opponent= currentHero==firstHero?secondHero:firstHero;
		currentHero.setCurrentManaCrystals(1);
		currentHero.setTotalManaCrystals(1);
		
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
		if(currentHero.getDeck().contains(target))
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
		if(!currentHero.getField().contains(attacker) && currentHero.getHand().contains(attacker))
		{
			throw new NotSummonedException();
			
		}	
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
		if(!currentHero.getField().contains(attacker) && currentHero.getHand().contains(attacker))
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
		
		//updates the current hero power
		currentHero.setHeroPowerUsed(false);
		if(currentHero.getTotalManaCrystals()<10)
		 currentHero.setTotalManaCrystals(currentHero.getTotalManaCrystals()+1);
		currentHero.setCurrentManaCrystals(currentHero.getTotalManaCrystals());
		updateMinions(currentHero);
		
		
		
	}
	
	public Game() 
	{
		// TODO Auto-generated constructor stub
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

	
	
	

}

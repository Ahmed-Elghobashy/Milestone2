package model.cards.spells;

import java.util.ArrayList;
import java.util.Collections;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class MultiShot extends Spell implements AOESpell
{

	public MultiShot()
	{
		super("Multi-Shot", 4,Rarity.BASIC);
		
	}

	@Override
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField)
	{
		// also here we should notify the listener if the minion dies 
		if(oppField.size()==0)
			return;
		
		if(oppField.size()==1)
		{
			Minion minion = oppField.get(0);
			if(minion.isDivine())
				minion.setDivine(false);
			else 
			   minion.setCurrentHP(minion.getCurrentHP()-3);
			return;
		}
		Minion firstMinionAttacked=null;
	    int attacks =0;
		while(attacks<2)
        {
        // We must check that the Minion we are attacking was not attacked before 
		 int randIndex =(int) (Math.random()*oppField.size());
		 Minion randMinion= oppField.get(randIndex);
	     if(firstMinionAttacked==null || randMinion!=firstMinionAttacked)
		  {
	    	 if(randMinion.isDivine())
	    		 randMinion.setDivine(false);
	    	 else 
	    	     randMinion.setCurrentHP(randMinion.getCurrentHP()-3);
	    	 firstMinionAttacked=randMinion;
	    	 attacks++;
		  }
		}
		  
	}
}

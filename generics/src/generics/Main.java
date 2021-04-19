package generics;

public class Main {
	
	public static void main(String[] args) {
		
		FootballPlayer ronaldo = new FootballPlayer("Ronaldo");
		FootballPlayer messi = new FootballPlayer("Messi");
		
		BasketPlayer deni = new BasketPlayer("Deni");
		BasketPlayer ali = new BasketPlayer("Ali");
		
		Team<FootballPlayer> juve = new Team<>("Juventus");
		Team<FootballPlayer> barca = new Team<>("Barcelona");
		
		juve.addPlayer(ronaldo);
		barca.addPlayer(messi);
		
		juve.matchResult(barca, 1, 0);
		barca.matchResult(juve, 2, 2);
		
		juve.getStat();
		barca.getStat();
		
	}

}

package generics;

import java.util.ArrayList;

public class Team<T extends Player> {

	private String name;
	private int played;
	private int won;
	private int lose;
	private int tied;
	private ArrayList<T> players;
	
	public Team(String name) {
		this.name = name;
		this.played = 0;
		this.won = 0;
		this.lose = 0;
		this.tied = 0;
		this.players = new ArrayList<>();
	}
	
	public boolean addPlayer(T player) {
		if(this.players.contains(player)) {
			System.out.println(player.getName() + " is already in the team");
			return false;
		}
		else {
			System.out.println(player.getName() + " added to the team");
			players.add(player);
			return true;
		}
	}
	
	public void matchResult(Team<T> opponent, int ourScore, int theirScore) {
		
		if(ourScore > theirScore) {
			this.won++;
		}
		else if(ourScore == theirScore) {
			this.tied++;
		}
		else {
			this.lose++;
		}
		
		if(opponent != null) {
			opponent.matchResult(null, theirScore, ourScore);
		}
		
	}
	
	public void getStat() {
		String message =  "[ " + this.name + " | won = " + this.won + " | lose = " + this.lose + " | tied = " + this.tied + " ]";
		System.out.println(message);
	}
}

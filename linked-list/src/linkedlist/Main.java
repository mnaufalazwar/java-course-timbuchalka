package linkedlist;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		LinkedList<String> cities = new LinkedList<>();
		
		addCity(cities, "Bandung");
		addCity(cities, "Aceh");
		addCity(cities, "Jakarta");
		addCity(cities, "Denpasar");
		addCity(cities, "Abu");
		
		printLinkedList(cities);
		
		visitCity(cities);
	}
	
	static void visitCity(LinkedList<String> cities) {
		
		Scanner scanner = new Scanner(System.in);
		boolean quit = false;
		boolean forwardDirection = true;
		
		if(cities.isEmpty()) {
			return;
		}
		
		ListIterator<String> it = cities.listIterator();
		
		while(!quit) {
			
			System.out.print("action = ");
			int action = scanner.nextInt();
			scanner.nextLine();
			
			switch(action) {
			
				case 0:
					System.out.println("bye");
					quit = true;
					break;
				case 1:
					//moving forward
					if(!forwardDirection) {
						forwardDirection = true;
						it.next();
					}
					if(it.hasNext()) {
						System.out.println("visiting " + it.next());
					}
					else {
						System.out.println("end of list");
						forwardDirection = false;
					}
					break;
				case 2:
					//moving backward
					if(forwardDirection) {
						forwardDirection = false;
						it.previous();
					}
					if(it.hasPrevious()) {
						System.out.println("visiting " + it.previous());
					}
					else {
						System.out.println("start of list");
						forwardDirection = true;
					}
					break;
			}
			
		}
		
	}
	
	static void addCity(LinkedList<String> cities, String city) {
		
		ListIterator<String> it = cities.listIterator();
		
		while(it.hasNext()) {
			
			int compare = city.compareTo(it.next());
			
			if(compare == 0) {
				System.out.println(city + " already in the list");
				return;
			}
			else if(compare < 0) {
				it.previous();
				it.add(city);
				return;
			}
		}
		it.add(city);
	}
	
	static void printLinkedList(LinkedList<String> linkedList) {
		
		for(int i = 0 ; i < linkedList.size(); i ++) {
			System.out.println(linkedList.get(i));
		}
		System.out.println("=====");
	}
	
}

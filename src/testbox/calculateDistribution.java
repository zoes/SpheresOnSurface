package testbox;
import testbox.Circle;
import testbox.CirclesInBox;

public class calculateDistribution {
	
	public static void main(String[] args) {
		
		double radius = 10;
		double boxSize = 1000;
		int stopValue = 100000000;
		
		for (int i=1; i < 10; i++){
		
		CirclesInBox cb = new CirclesInBox(radius, boxSize, stopValue);
		
		cb.placeCircles();
		
		cb.writeToFile(i);
		int n = cb.getNumberOfCircles();
		
		System.out.println("Number of circles " + n);
		}
		
	}

}



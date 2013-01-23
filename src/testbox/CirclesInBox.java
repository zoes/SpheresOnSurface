package testbox;

import java.io.*;
import java.io.FileOutputStream;
import java.util.ArrayList;
import testbox.Circle;

public class CirclesInBox {

	private int failcount;
	private ArrayList<Circle> circles = new ArrayList();

	private double boxSize;
	private double radius;
	private int stopValue;

	CirclesInBox(double r, double bs, int s) {
		stopValue = s;
		radius = r;
		boxSize = bs;

	}

	public int getFailcount() {
		return failcount;
	}

	public void placeCircles() {
		failcount = 0;
		double x1 = Math.random() * boxSize;
		double y1 = Math.random() * boxSize;

		Circle c1 = new Circle(x1, y1);
		circles.add(c1);

		while (failcount <= stopValue) {

			double xtest = Math.random() * boxSize;
			double ytest = Math.random() * boxSize;
			// System.out.println("X = " + xtest);
			// System.out.println("Y = " + ytest);

			boolean keep = true;

			for (Circle c : circles) {
				if (!isValid(c, xtest, ytest)) {
					keep = false;
					break;
				}
			}

			if (keep) {
				circles.add(new Circle(xtest, ytest));
				failcount = 0;
				// System.out.println("Rezero fc" + failcount);
			} else
				// System.out.println("Failcount = " + failcount);
				failcount++;
		}

	}

	public boolean isValid(Circle c, Double xtest, Double ytest) {
		double xdif;
		double ydif;

		double xdif1 = Math.abs(boxSize - Math.abs(xtest - c.getX()));
		double xdif2 = Math.abs(xtest - c.getX());

		if (xdif1 <= xdif2)
			xdif = xdif1;
		else
			xdif = xdif2;

		double ydif1 = boxSize - Math.abs(ytest - c.getY());
		double ydif2 = Math.abs(ytest - c.getY());

		if (ydif1 <= ydif2)
			ydif = ydif1;
		else
			ydif = ydif2;

		double sos = xdif * xdif + ydif * ydif;
		double dist = Math.sqrt(sos);
		// System.out.println("Distance " + dist);

		if (dist < radius * 2) {
			return false;
		}
		return true;
	}

	public int getNumberOfCircles() {
		return circles.size();
	}

	public void writeToFile(int index) {
		String fn = "/Users/zoe/philsapp/out/" +stopValue+ "/" +radius + "_" + boxSize  + index;
		try {
			File file = new File(fn);
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter os = new OutputStreamWriter(fos);
			
			os.write("Radius " + radius + " Boxsize: " + boxSize + " StopValue: " + stopValue + " Number placed: " + circles.size() + "\n");

			for (Circle c : circles) {
				String sx = String.valueOf(c.getX());
				int xdot = sx.indexOf(".");
				String sxcut = sx.substring(0, xdot + 3);

				String sy = String.valueOf(c.getY());
				int ydot = sy.indexOf(".");
				String sycut = sy.substring(0, ydot + 3);

				os.write(sxcut + "," + sycut + "\n");

			}
			os.close();
			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

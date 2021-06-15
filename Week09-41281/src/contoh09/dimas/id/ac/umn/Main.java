package contoh09.dimas.id.ac.umn;

public class Main {

	public static void main(String[] args) {
		Polygon poly1 = new Polygon();
		poly1.askWidth();
		Polygon poly2 = new Polygon(1, 2);
		poly2.askWidth();
		
		Rectangle rect1 = new Rectangle();
		rect1.countSide();
		
		Triangle tri1 = new Triangle();
		tri1.countSide();
	}

}

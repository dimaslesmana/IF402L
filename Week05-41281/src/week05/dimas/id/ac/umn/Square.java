package week05.dimas.id.ac.umn;

public class Square extends Shape {
	
	private double side;
	
	public Square() {}
	public Square(double side, String color) {
		super(color);
		this.side = side;
	}
	
	public double getSideLength() {
        return side;
    }
	
	public double getPerimeter() {
		return 4 * side;
	}
	
	public double getArea() {
		return Math.pow(side, 2);
	}
	
}

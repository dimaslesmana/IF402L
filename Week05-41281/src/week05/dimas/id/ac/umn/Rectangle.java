package week05.dimas.id.ac.umn;

public class Rectangle extends Shape {

	private double length, width;
	
	public Rectangle() {}
	public Rectangle(double length, double width, String color) {
		super(color);
		this.length = length;
		this.width = width;
	}
	
	public double getLength() {
		return length;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getPerimeter() {
		return 2 * (length + width);
	}
	
	public double getArea() {
		return width * length;
	}
	
}

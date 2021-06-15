package week05.dimas.id.ac.umn;

public class Triangle extends Shape {

	private double base, height;
	
	public Triangle() {}
	public Triangle(double base, double height, String color) {
		super(color);
		this.base = base;
		this.height = height;
	}
	
	public double getBase() {
		return base;
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getPerimeter() {
		double hypotenuse = Math.sqrt((base*base) + (height*height));
		return base + height + hypotenuse;
	}
	
	public double getArea() {
		return (height * base) / 2;
	}
	
}

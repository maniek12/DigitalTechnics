/**
 * @author Mariusz Dobek
 * 
 * If you find this code is useful and you meet me anytime, anywhere you can treat me a beer.
 */

package dsp;
//float version

class Complex {
	
	private double real;
	private double imaginary;
	private double radius;
	private double angle;
	
	/**
	 * Default constructor. It sets all fields as zeros.
	 */
	public Complex() {
		
		this.real = 0;
		this.imaginary = 0;
		this.radius = 0;
		this.angle = 0;
	}
	
	/**
	 * This constructor allows to make Complex object from real and imaginary parts.
	 * @param real
	 * Real part of complex number.
	 * @param imaginary
	 * Imaginary part of complex number.
	 */
	public Complex(double real, double imaginary) {
		
		this.real = real;
		this.imaginary = imaginary;
		generateRadiusAndAngle();
	}
	
	/**
	 * Copying constructor of complex number.
	 * @param complex
	 */
	public Complex(Complex complex) {
		
		this.real = complex.real;
		this.imaginary = complex.imaginary;
		this.radius = complex.radius;
		this.angle = complex.angle;
	}
	
	/**
	 * This method sets fields of complex number from trigonometric parameters.
	 * @param radius
	 * Radius of complex number.
	 * @param angle
	 * Angle of complex number.
	 */
	public void setWithTrigonometric(double radius, double angle) {
		
		this.radius = radius;
		this.angle = angle;
		generateRealAndImag();
	}
	
	/**
	 * This method sets fields of complex number from algebraic parameters.
	 * @param real
	 * Real part.
	 * @param imaginary
	 * Imaginary part.
	 */
	public void setWithAlgebraic(double real, double imaginary) {
		
		this.real = real;
		this.imaginary = imaginary;
		generateRadiusAndAngle();
	}
	
	public Complex plus(Complex complex) {
		
		this.real = this.real + complex.real;
		this.imaginary = this.imaginary + complex.imaginary;
		generateRadiusAndAngle();
		
		return this;
	}
	
	public Complex plus(double content) {
		
		this.real = this.real + content;
		generateRadiusAndAngle();
		
		return this;
	}
	
	public Complex minus(Complex complex) {
		
		this.real = this.real - complex.real;
		this.imaginary = this.imaginary - complex.imaginary;
		generateRadiusAndAngle();
		
		return this;
	}
	
	public Complex minus(double content) {
		
		this.real = this.real - content;
		generateRadiusAndAngle();
		
		return this;
	}
	
	/**
	 * Method allows to multiply complex numbers by complex numbers.
	 * @param complex
	 * Current complex number is multiplying by this parameter.
	 * @return
	 * Multiplied complex.
	 */
	public Complex times(Complex complex) {
		
		this.radius = this.radius * complex.radius;
		this.angle = this.angle + complex.angle;
		generateRealAndImag();
		
		return this;
	}
	
	/**
	 * Method allows to multiply complex numbers by real numbers.
	 * @param factor
	 * Real number.
	 * @return
	 * This.
	 */
	public Complex times(double factor) {
		
		this.radius = this.radius * factor;
		generateRealAndImag();
		
		return this;
	}
	
	public static Complex multiplication(Complex a, Complex b) {
		
		Complex c = new Complex();
		c.setWithTrigonometric(a.radius * b.radius, a.angle + b.angle);
		c.generateRealAndImag();
		return c;
	}
	
	public static Complex plus(Complex a, Complex b) {
		
		Complex c = new Complex();
		c.setWithAlgebraic(a.real + b.real, a.imaginary + b.imaginary);
		c.generateRadiusAndAngle();
		return c;
	}
	
	public static Complex minus(Complex a, Complex b) {
		
		Complex c = new Complex();
		c.setWithAlgebraic(a.real - b.real, a.imaginary - b.imaginary);
		c.generateRadiusAndAngle();
		return c;
	}
	
	public Complex divideBy(Complex complex) {
		
		this.radius = this.radius / complex.radius;
		this.angle = this.angle - complex.angle;
		generateRealAndImag();
		
		return this;
	}
	
	public Complex divideBy(double divisor) {
		
		this.radius = this.radius / divisor;
		generateRealAndImag();
		
		return this;
	}
	
	/**
	 * This method allows to raise to the power a complex number.
	 * @param exponent
	 * Exponent.
	 * @return
	 * This.
	 */
	public Complex power(double exponent) {
		
		this.radius = (float)Math.pow(this.radius, exponent);
		this.angle = this.angle * exponent;
		generateRealAndImag();
		
		return this;
	}
	
	/**
	 * This method allows to sum a table of Complex objects in fast way.
	 * @param contents
	 * Table of Complex objects.
	 * @return
	 * A Complex object which is the sum of table.
	 */
	public static Complex sum(Complex[] contents) {
		
		double real = 0;
		double imag = 0;
		
		for(int i = 0; i < contents.length; i++) {
			
			real = real + contents[i].radius * (double)Math.cos(contents[i].angle);
			imag = imag + contents[i].radius * (double)Math.sin(contents[i].angle);
		}
		
		return new Complex(real, imag);
	}
	
	/**
	 * 
	 * @return
	 * Radius of complex number.
	 */
	public double getRadius() {
		
		return this.radius;
	}
	
	/**
	 * 
	 * @return
	 * Angle of complex number.
	 */
	public double getAngle() {
		
		return this.angle;
	}
	
	/**
	 * 
	 * @return
	 * A real part.
	 */
	public double getReal() {
		
		return this.real;
	}
	
	/**
	 * 
	 * @return
	 * A imaginary part.
	 */
	public double getImaginary() {
		
		return this.imaginary;
	}
	
	/**
	 *  
	 * @return
	 * Euler form of complex number.
	 */
	public String drawEuler() {
		
		return new String(radius + "e^j(" + angle + ")");
	}
	
	/**
	 * 
	 * @return
	 * Algebraic form of complex number.
	 */
	public String drawAlgebraic() {
		
		return new String(real + " + " + imaginary + "j");
	}
	
	/**
	 * 
	 * @return
	 * Trigonometric form of complex number.
	 */
	public String drawTrigonometric() {
		
		return new String(radius + "(cos(" + angle + ") + jsin(" + angle + "))");
	}
	
	public String toString() {
		
		return drawAlgebraic();
	}
	
	/*
	 * Helps generate complex after any operation.]
	 * Generate new real and imaginary fields.
	 */
	private void generateRealAndImag() {
		
		this.real = this.radius * (double)Math.cos(this.angle);
		this.imaginary = this.radius * (double)Math.sin(this.angle);
	}
	
	/*
	 * Helps generate complex after any operation.
	 * Generate new angle and radius fields.
	 */
	private void generateRadiusAndAngle() {
		
		this.radius = (double)Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.imaginary, 2));
		if(this.real == 0 && this.imaginary == 0) {
			this.angle = 0;
		}
		else {
			this.angle = (double)Math.acos(this.real / this.radius);
		}
	}
}
/**
 * @author Mariusz Dobek
 * 
 * If you find this code is useful and you meet me anytime, anywhere you can treat me a beer.
 */
package dsp;
public class DigitalSignalProcessing {
	


	public static double[] dft(double[] values) {
		
		int contents_quantity = values.length;
		/**
		 * Helping variable e^(i*2pi/N)
		 */
		Complex w = new Complex();
		w.setWithTrigonometric(1, 2*3.14/contents_quantity);
		/**
		 * Contents of the sum for every X(k) content
		 */
		Complex[] contents = new Complex[contents_quantity];
		
		double[] results = new double[contents_quantity];
		
		for(int k = 0; k <= contents_quantity-1; k++) {			
		
			for(int n = 0; n <= contents_quantity-1; n++) {
				
				Complex tmp = new Complex(w);
				tmp.power(k*n);
				tmp.times(values[n]);
				contents[n] = tmp;
			}
			
			results[k] = Complex.sum(contents).getRadius();
		}
		
		return results;
	}
}
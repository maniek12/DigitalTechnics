/**
 * @author Mariusz Dobek
 * 
 * If you find this code is useful and you meet me anytime, anywhere you can treat me a beer.
 */
package dsp;
public class DigitalSignalProcessing {
	
	/**
	 * Metoda oblicza szybk¹ transformate Fouriera dla podanych argumentów.
	 * @param values tablica wartoœci typu float, musi zawieraæ 2^k elementów, gdzie k jest liczb¹ ca³kowit¹
	 * @return
	 */
	public static double[] fft(double[] values) {
		
		
		int samples_number = values.length;
		
		//check for one-point FFT
		if(samples_number == 1) {
			return values;
		}
		else {
			/**a variable with number of steps FFT**/
			int steps_number = 1;		
			
			// counting a number of steps
			
			while(samples_number > 2) {
				samples_number = samples_number / 2;
				steps_number++;
			}		
			
			// initiation of operation numbers
			/**divider of N number which helps make complex number Wn*/
			int divider = (int)(Math.pow(2, steps_number - 1));
			/**block size**/
			int block_size = 2;
			/**number of blocks in single step**/
			int blocks_number = values.length / block_size;
			// dzielone przez dwa bo druga czêœæ jest cykliczna w stosunku do pierwszej i wystarczy mno¿yæ przez -1
			// poœwiêcam wiêcej pamiêci operacyjnej ale mno¿enie przez 2 jest mniej kosztowne od dzielenia przez 2
			/**number of complex numbers Wn in one step which is needed**/
			int wn_quantity = block_size / 2;
			/** table of results FFT*/
			Complex[] results = new Complex[values.length];
			Complex[] tmp_results = new Complex[values.length];
			
			// sorting of values positions on entry and generate table of complex number
			for(int i = 0; i < values.length; i++) {
				
				double tmp = values[Binary.changeBitsOrder(i, Binary.numberOfBits(values.length - 1))];
				results[i] = new Complex(tmp, 0);
			}
			
			// przechodzê przez wszystkie kroki zag³ebienia FFT------------------------------------------------------
			for(int i = 0; i < steps_number; i++) {
				
				// dla ka¿dego kroku liczê zestaw liczby zespolonych Wn
				Complex[] factors = new Complex[block_size / 2];
				
				for(int j = 0; j < wn_quantity; j++) {
					
					Complex tmp_complex = new Complex();
					tmp_complex.setWithTrigonometric(1, (float)(-2*Math.PI*j*divider)/values.length);
					
					factors[j] = tmp_complex;
				}
				
				// liczenie nowej wartoœci dla ka¿dej danej wejœciowej
				int j = 0;
				while(j < values.length) {				
									
					//dla ka¿dego bloku
					for(int k = 0; k < blocks_number; k++) {
						// dzielê blok na dwie równe czêœci i wykouje motylkowe obliczenia na skos
						int half_block_size = block_size / 2;
						//czêœæ 1
						for(int l = 0; l < half_block_size; l++) {
							
							tmp_results[j] = Complex.plus(results[j], Complex.multiplication(factors[l], results[j + half_block_size]));
							j++;
						}
						//czêœæ 2
						for(int l = 0; l < half_block_size; l++) {
							
							tmp_results[j] = Complex.minus(results[j - half_block_size], Complex.multiplication(factors[l], results[j]));
							j++;
						}					
					}				
				}
				
				// z ka¿dym krokiem dzielnik jest mniejszy dwukrotnie od dzielnika z poprzedniego kroku.
				divider = divider / 2;
				// z ka¿dym krokiem rozmiar bloków jest dwa razy wiêkszy
				block_size = block_size * 2;
				// z ka¿dym krokiem liczba bloków maleje dwukrotnie
				blocks_number = blocks_number / 2;
				// zka¿dym krokiem liczba potrzebnych Wn maleje dwukrotnie
				wn_quantity = wn_quantity * 2;
				
				for(int index = 0; index < results.length; index++) 
					results[index] = tmp_results[index];
			}	
			
			//---------------------------------------------------------------------------------
			
			// obliczenie modu³ów rezultatów
			double[] final_results = new double[results.length];
			
			for(int i = 0; i < results.length; i++) {
				final_results[i] = results[i].getRadius();
			}
			
			//zwraca modu³y z policzonych liczb zespolonych
			return final_results;
		}
	}

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
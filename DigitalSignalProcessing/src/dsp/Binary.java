/**
 * @author Mariusz Dobek
 * 
 * If you find this code is useful and you meet me anytime, anywhere you can treat me a beer.
 */

package dsp;
/**
 * This class allows to make operations on binary numbers.
 * @author Mariusz
 *
 */
public class Binary {
	
	public static boolean[] toBoolTable(int number) {
		
		int bits_quantity = numberOfBits(number);
		int current_bits_quantity = bits_quantity;
		
		boolean[] bits = new boolean[bits_quantity];
		
		for(int i = 0; i < bits_quantity; i++) {
			
			int max = (int)(Math.pow(2,  current_bits_quantity - 1));
			if (number >= max) {
				bits[i] = true;
				current_bits_quantity--;
				number = number - max;
			}
			else {
				bits[i] = false;
				current_bits_quantity--;
			}
		}
		
		return bits;
	}
	
	/**
	 * This method is used to display an integer number as binary sequence.
	 * @param number 
	 * @param bits
	 * @return
	 */
	public static String toBinaryString(int number, int bits) {
		
		String binary_form = "";
		int max_number = (int)(Math.pow(2, bits) - 1);
		
		// sprawdzenie czy wartoœc zmieœci siê w tylu bitach, inaczej nie ma sensu liczyæ, rejestr jest wype³niony
		if(number >= max_number) {
			for(int i = 0; i < bits; i++)
				binary_form = binary_form + "1";
			return binary_form;
		}
		else {
			max_number = (int)(Math.pow(2, bits - 1));
			for(int i = 0; i < bits; i++) {
				
				if(number >= max_number) {
					binary_form = binary_form + "1";
					number = number - max_number;
					max_number = max_number / 2;					
				}
				else {
					binary_form = binary_form + "0";			
					max_number = max_number / 2;
				}
			}
			
			return binary_form;
		}		
	}
	
	/**
	 * This method changes bits order of integer number.
	 * @param number
	 * number of integer type which will be transformed.
	 * @return
	 * an integer with changed bits order.
	 */
	public static int changeBitsOrder(int number) {
		
		int new_number = 0;		
		boolean[] bool_table = Binary.toBoolTable(number);
		int tmp = 1;
		
		for(int i = 0; i < bool_table.length; i++) {
			if (bool_table[i] == true)
				new_number = new_number + tmp;
			tmp = tmp * 2;
		}
		return new_number;
	}
	
	/**
	 * Method changes order of bits but first it fill empty bits with zeros.
	 * @param number
	 * an number whose bits order will be changed
	 * @param bits_number
	 * quantity of bits which we want to use
	 * @return
	 */
	public static int changeBitsOrder(int number, int bits_number) {
		
		int new_number = 0;
		boolean[] tmp_bool_table = Binary.toBoolTable(number);
		boolean[] bool_table = new boolean[bits_number];
		
		int index = bool_table.length - 1;
		// tworzê nowa rozszerzon¹ tablice 0-1
		for(int i = tmp_bool_table.length - 1; i >= 0; i--) {
			try {
				bool_table[index] = tmp_bool_table[i];
			}
			catch(Exception e) {
				System.out.println("Za ma³o bitów");
			}
			index--;
		}
		
		int tmp = 1;
		
		for(int i = 0; i < bool_table.length; i++) {
			if (bool_table[i] == true)
				new_number = new_number + tmp;
			tmp = tmp * 2;
		}
		return new_number;
	}
	
	/**
	 * 
	 * @param number
	 * A number which we want to research.
	 * @return
	 * A length of integer in bits.
	 */
	public static int numberOfBits(int number) {
		
		int bits = 1;
		int tmp = 1;
		while(tmp < number) {
			
			tmp = (int)(Math.pow(2, bits + 1)) - 1;
			bits++;
		}		
		
		return bits;
	}	
}
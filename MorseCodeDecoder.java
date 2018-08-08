public class MorseCodeDecoder {

    private static int gcd(int a, int b) {
		while (b > 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}
	
	private static String trimBits (String bits) {
		
		int first = 0, last = bits.length() - 1;
		
		while (bits.charAt(first) == '0') {first++;}
		while (bits.charAt(last) == '0') {last--;}
		
		return bits.substring(first, ++last);	
	}
	
	private static int transferRate (String bits) {
		
		int firstOnes = 0, secondOnes = 0;
    int firstZeros = 0, SecondZeros = 0, thirdZeros = 0;
    String[] ones = bits.split("0+");
		
		for (int i = 0; i < ones.length; i++) {
			firstOnes = ones[i].length();
			if (secondOnes == 0) {secondOnes = firstOnes;}
			if (secondOnes != firstOnes) {break;}
			if (i == ones.length - 1) {break;}
			firstOnes = 0;}
		if (firstOnes != secondOnes) {return gcd(firstOnes, secondOnes);}
		
    String[] zeros = bits.split("1+");
    
		for (int i = 0; i < zeros.length; i++) {
			firstZeros = zeros[i].length();
			if (SecondZeros == 0 && thirdZeros == 0) {SecondZeros = firstZeros; thirdZeros = firstZeros;}
			if (SecondZeros != firstZeros && SecondZeros == thirdZeros) {thirdZeros = firstZeros;}
			if (SecondZeros != firstZeros && thirdZeros != firstZeros) {break;}
      if (i == zeros.length - 1) {break;}
			firstZeros = 0;}
		return gcd(thirdZeros, gcd(SecondZeros, gcd(firstZeros, gcd(firstOnes, secondOnes))));
	}
    
    public static String decodeBits(String bits) {
      
  		String code = "", newBits, betweenWords = "", betweenChar = "", betweenUnits = "", dots = "", dash = "";
  		int bitRate;
  		
  		//Trimming the first and last 0
  		newBits = trimBits(bits);
  		
  		//Finding the transfer rate
  		bitRate = transferRate(newBits);
  		
  		for (int i = 0; i < 7*bitRate; i++) {betweenWords += "0";}
  		for (int i = 0; i < 3*bitRate; i++) {betweenChar += "0"; dash += "1";}
  		for (int i = 0; i < 1*bitRate; i++) {betweenUnits += "0"; dots += "1";}
  		
  		for (String words : newBits.split(betweenWords)) {
  			for (String letters : words.split(betweenChar)) {
  				for (String units : letters.split(betweenUnits)) {
  					if (units.equals(dots)) { code += "."; }
  					if (units.equals(dash)) { code += "-"; }
  				} code += " ";}
  			code = code.trim() + "   ";}
      return code.trim();
    }
       
      public static String decodeMorse(String morseCode) {
        String result = "";
        for (String words : morseCode.trim().split("   ")) {
    			for (String letters : words.split(" ")) {
    				result += MorseCode.get(letters);}
          result += " ";}
    		return result.trim();
    }
}
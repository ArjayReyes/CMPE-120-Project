package processor;

import java.util.HashMap;

public class Memory {

	private HashMap<String, String> memoryMap;
	private byte[] memoryArray;

	public Memory() {
		memoryMap = new HashMap<>();
	}

	public Memory(int size) {
		memoryArray = new byte[size];
	}

	public void setMemoryValue(String address, String value) {
		// TODO: error handling for memory? ie. check if value is valid?
		memoryMap.put(address, value);
	}

	public String getMemoryValue(String address) {
		if (memoryMap.containsKey(address)) {
			return memoryMap.get(address);
		} else {
			return Utility.ALLZEROS; // default to 0
		}
	}

	public String getInstruction(String startingAddress) {
		String instruction = "";
		for (int i = 0; i < 4; i++) {
			instruction = getMemoryValue(startingAddress) + instruction;
			startingAddress = Utility.StringCrement(startingAddress, 1);
		}
		return instruction;
	}

	public String loadByte(int address) {
		byte loadedByte = memoryArray[address];
		return Byte.toString(loadedByte);
	}

	public String loadHalfword(int address) {
		short loadedHalfword = (short) ((memoryArray[address] & 0xFF) | ((memoryArray[address + 1] & 0xFF) << 8));
		return Short.toString(loadedHalfword);
	}

	public String loadWord(int address) {
		int loadedWord = (memoryArray[address] & 0xFF) | ((memoryArray[address + 1] & 0xFF) << 8)
				| ((memoryArray[address + 2] & 0xFF) << 16) | ((memoryArray[address + 3] & 0xFF) << 24);
		return Integer.toString(loadedWord);
	}

	public void storeByte(int address, String substring) {
		// Convert the substring to a byte value
		byte byteValue = (byte) Integer.parseInt(substring, 2);

		// Store the byte in memory at the specified address
		memoryArray[address] = byteValue;
	}

	public void storeWord(int address, int valueInt) {
		// Store the word at the specified address
		memoryArray[address] = (byte) (valueInt & 0xFF);
		memoryArray[address + 1] = (byte) ((valueInt >> 8) & 0xFF);
		memoryArray[address + 2] = (byte) ((valueInt >> 16) & 0xFF);
		memoryArray[address + 3] = (byte) ((valueInt >> 24) & 0xFF);
	}

	public void storeHalfword(int address, int valueInt) {
		// Store the halfword at the specified address
		memoryArray[address] = (byte) (valueInt & 0xFF);
		memoryArray[address + 1] = (byte) ((valueInt >> 8) & 0xFF);
	}

}

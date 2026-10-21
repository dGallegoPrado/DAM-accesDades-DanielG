package esccribirbinario;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        byte edat = 15;
		short segonsMinut = 60;
		int segonsHora = 3600;
		float pi = 3.14159F;
		double sqrtDos = 1.414;
		char metres = 'm';
		String acceleracio = "m/s^2";

		try (DataOutputStream escriptor = new DataOutputStream(new FileOutputStream("dades.bin"));) {
			escriptor.writeByte(edat);
			escriptor.writeShort(segonsMinut);
			escriptor.writeInt(segonsHora);
			escriptor.writeFloat(pi);
			escriptor.writeDouble(sqrtDos);
			escriptor.writeChar(metres);
			escriptor.writeChars(acceleracio);
		} catch (FileNotFoundException ex) {
			System.err.println(ex);
		} catch (IOException ex) {
			System.err.println(ex);
		}   
     }
}
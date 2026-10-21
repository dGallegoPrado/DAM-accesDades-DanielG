package exercici32;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introdueix un codi secret: ");
        String codiUsuari = sc.nextLine();
        char codi = codiUsuari.charAt(0);
        
        Path fitxerSecret = Paths.get("secret.bin");

          try (DataInputStream lector = new DataInputStream(new FileInputStream(fitxerSecret.toFile()));) { 			
                codi= lector.readChar();
                if(codi == sumaAl || codi == letra1 || codi==letra2|| codi==letra3){
                    System.out.println("Login correcte.");
                } else{
                    System.out.println("Login incorrecte.");
                }
                    }//try 
                    catch (FileNotFoundException ex) {
                        System.err.println(ex);
                    } catch (IOException ex) {
                        System.err.println(ex);
                    }



    }
}
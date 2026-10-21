package loginregistre;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pass;
        
        System.out.println("Introdueix el nom d'usuari");
        String loginUsuari= sc.nextLine();

        System.out.println("Introdueix la contrasenya: ");
        int contraUsuari= sc.nextInt(); 
        sc.nextLine();

        Path fitxerLogin = Paths.get(loginUsuari+".dat");
        
        if(Files.exists(fitxerLogin)){
            try (DataInputStream lector = new DataInputStream(new FileInputStream(fitxerLogin.toFile()));) { 			
                pass = lector.readInt();
                if(contraUsuari == pass){
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
        }//if archivo existe
        else{
            System.out.println("Login incorrecte, l'usuario no existeix \n Vols registrarte? Prem S o N");
            String eleccio = sc.nextLine(); 

            char caracter = eleccio.charAt(0);
            if (caracter == 's' || caracter =='S') {    
                System.out.println("Introdueix la contrasenya: ");
                pass = sc.nextInt();
                
                try (DataOutputStream escriptor = new DataOutputStream(new FileOutputStream(fitxerLogin.toFile()));) {
	            
                escriptor.writeInt(pass); 
                
                }//try
                catch (FileNotFoundException ex) {
			System.err.println(ex);
		} catch (IOException ex) {
			System.err.println(ex);
		}
            }else{  
                System.out.println("Fí del programa.");
            }//else
        }
        
    }
}
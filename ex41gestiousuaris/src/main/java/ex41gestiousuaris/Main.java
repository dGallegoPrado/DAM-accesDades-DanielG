package ex41gestiousuaris;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introdueix un usuari: ");
        String nom = sc.nextLine(); 
        
        System.out.println("Introdueix la contrasenya: ");
        String pass = sc.nextLine();

        User usuari = new User(nom, pass);

        Path fitxerLogin = Paths.get(usuari.getNom() + ".urs");

        if(Files.exists(fitxerLogin)){
            try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream(usuari.getNom()+".urs"));) {

                User usuariGuardat = (User) lector.readObject();   
                    
                if (!usuariGuardat.getPass().equals(usuari.getPass())) {
                        System.out.println("Accés denegat: contrasenya incorrecta.");
                } else {
                        System.out.println("Accés correcte al sistema. Benvingut " + usuari.getNom());
                }
            } catch (EOFException ex) {			
            } catch (IOException ex) {
                System.err.println(ex);
            } catch (ClassNotFoundException ex) {
                System.err.println(ex);
            }
        }
        else{
            System.out.println("No hi ha cap usuari conegut, vols registrar-te?");
            String resposta = sc.nextLine();
            
            char caracter = resposta.charAt(0);
            if (caracter == 's' || caracter == 'S') {
                try (ObjectOutputStream escriptor = new ObjectOutputStream(new FileOutputStream(fitxerLogin.toFile()));) {
                        escriptor.writeObject(usuari);
                        System.out.println("Usuari creat correctament");
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }else if(caracter == 'n' || caracter == 'N'){
                System.out.println("No es registrara cap usuari");
            }else{
                System.out.println("Fi del registre");
            }
        }
    }
}
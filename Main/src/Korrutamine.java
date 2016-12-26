import java.util.Scanner;

/**
 * Created by Pille on 12.10.2016.
 */
public class Korrutamine {

    public static void main(String[] args){

        Scanner klaviatuur = new Scanner(System.in);

        System.out.println("Sisesta maksimaalne korrutaja");
        int max = klaviatuur.nextInt();

        //leian suvalised tegurid
        int esimene = (int) Math.floor(Math.random() * max) + 1;
        int teine = (int) Math.floor(Math.random() * max) + 1;
        int tulemus = esimene * teine;


        System.out.printf("%d * %d = ", esimene, teine);
        int vastus = klaviatuur.nextInt();
        if (vastus == tulemus){
            System.out.println("Õige vastus!");
        } else {
            System.out.println("Vale vastus!");

        }
    }



}

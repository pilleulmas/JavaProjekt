import java.util.Scanner;

/**
 * Created by Pille on 12.10.2016.
 */
public class Liitmine {

    public static void main(String[] args){

        Scanner klaviatuur = new Scanner(System.in);

        System.out.println("Sisesta maksimaalne liidetavate summa");
        int max = klaviatuur.nextInt();
        System.out.println("Maksimaalne liidetavate summa on " + max);

        //leian suvalised liidetavad
        int esimene = (int) Math.floor(Math.random()*(max+1));
        int teine = (int) Math.floor(Math.random() * (max-esimene + 1));
        int tulemus = esimene + teine;


        System.out.printf("%d + %d = ", esimene, teine);
        int vastus = klaviatuur.nextInt();
            if (vastus == tulemus){
                System.out.println("Õige vastus!");
            } else {
                System.out.println("Vale vastus!");

        }
    }


}

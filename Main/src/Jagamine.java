import java.util.Scanner;

/**
 * Created by Pille on 29.10.2016.
 */
public class Jagamine {
    public static void main(String[] args) {

        Scanner klaviatuur = new Scanner(System.in);

        System.out.println("Sisesta maksimaalne korrutaja");
        int max = klaviatuur.nextInt();

        for (int i = 0; i < 3; i++) {


            //leian suvalised tegurid
            int esimene = (int) Math.floor(Math.random() * max) + 1;
            int teine = (int) Math.floor(Math.random() * max) + 1;
            int tulemus = esimene * teine;

            boolean a = true;
            while (a) {
                for (int j = 0; j < 3; j++) {

                    System.out.printf("%d / %d = ", tulemus, esimene);

                    int vastus = klaviatuur.nextInt();

                    if (vastus == teine) {
                        System.out.println("Tubli!");
                        break;
                    } else if (j<2){
                        System.out.println("Proovi veel!");
                    }
                    else {
                        break;
                    }
                }
                System.out.printf("Õige vastus on %d\n", teine);
                break;
            }
        }
    }


}

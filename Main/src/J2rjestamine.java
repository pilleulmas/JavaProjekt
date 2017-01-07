import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.util.Arrays;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.LIMEGREEN;
import static javafx.scene.paint.Color.RED;

/**
 * Created by Pille on 7.01.2017.
 */
public class J2rjestamine {
    public static boolean tunnus;                                         //j2rjestamise muutujad
    public static int loendur = 0;
    public static int arv;
    public static int j2rjestatud[] = new int[12];
    public static int numbrid[] = new int[12];
    public static Label vead = new Label("");
    public static Label viimane = new Label("");
    public static int aps = 0;

    public static void j2rKontroll(String a){                      //järjestamise klikkide kontroll
        if (Integer.parseInt(a)==j2rjestatud[loendur]){            //kui oled klikkinud järjekorras õiget nuppu
            System.out.println("Tubli!");
            if (loendur==11){                                       //kui see nupp on viimane
                Aken.aeg = System.currentTimeMillis() - Aken.start;
                Aken.aegaKulus = (int) Aken.aeg/1000;
                Label aeg = new Label("Aega kulus "+Aken.aegaKulus+" sekundit");
                aeg.setFont(Font.font ("Verdana", 20));
                Aken.kesk.add(aeg,0,7,4,1);                         //Lisan kulunud aja
                System.out.println(Aken.aeg);
                viimane.setText("Tubli! Leidsid viimase!");
                viimane.setTextFill(LIMEGREEN);
                loendur++;
            }
            else {                                                  //kui see nupp ei olnud viimane
                loendur++;
                viimane.setText("Tubli! Viimane number oli " + j2rjestatud[loendur-1]);
                viimane.setTextFill(BLACK);
            }
        }
        else {                                                      //kui klikkisid valet nuppu
            System.out.println("Proovi uuesti!");
            aps++;
            vead.setText("vigade arv = " + aps);
            if(loendur==0){                                         //kui see oli esimene klikk
                viimane.setText("Proovi uuesti! Leia kõige väiksem arv!");
                viimane.setTextFill(RED);
            }
            else{                                                   //kui see ei olnud esimene klikk
                viimane.setText("Proovi uuesti! Viimane number oli " + j2rjestatud[loendur-1]);
                viimane.setTextFill(RED);
            }
        }
    }
    public static void looMassiivid(int max){           //järjestamise massiivide tekitamine
        for (int mitmes = 0; mitmes < 12; mitmes++) {   //teen 12-se massiivi suvanumbritest
            do {
                tunnus = false;
                if (max > 11) {
                    arv = (int) Math.floor(Math.random() * max) + 1;
                } else {
                    arv = (int) Math.floor(Math.random() * 12) + 1;
                }
                for (int l = 0; l < mitmes; l++)
                    if (arv == numbrid[l]) tunnus = true;
            } while (tunnus);
            numbrid[mitmes] = arv;          //lisan arvu segamini massiivi
            j2rjestatud[mitmes] = arv;      //lisan arvu j2rjestamise massiivi
        }

        Arrays.sort(j2rjestatud);           //sorteerin j2rjestamise massiivi

    }
}

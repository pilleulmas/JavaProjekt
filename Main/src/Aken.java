import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Arrays;
import static javafx.scene.paint.Color.*;

/**
 * Created by Pille on 2.01.2017.
 */
public class Aken {
    private static int max =10;
    private static Tehted[] tehted = new Tehted[10];
    private Stage stage = new Stage();
    String nupuvalik[] = {"Liitmine", "Lahutamine", "Korrutamine", "Jagamine", "Järjestamine"};

    long start, aeg;
    Label aegaKulus = new Label("a");

    Label[] ylesanded = new Label[10];                      //massiivid arvutamise keskpaneeli sisu jaoks
    TextField[] vastused = new TextField[10];
    Label[] oiged = new Label[10];

    boolean tunnus;                                         //j2rjestamise muutujad
    int loendur = 0;
    int arv;
    int j2rjestatud[] = new int[12];
    int numbrid[] = new int[12];
    Label vead = new Label("");
    Label viimane = new Label("");
    int aps = 0;

    public Aken(){
        algaken();
    }
    public void algaken(){

        HBox valik = new HBox();                                //Ülemise paneeli välimus
            valik.setAlignment(Pos.BOTTOM_CENTER);
            valik.setSpacing(20);

        Label slText = new Label("Vali maksimaalne\ntegur");    //slideri pealkiri
            slText.setFont(Font.font ("Verdana", 20));
        Label slmax = new Label("Max on 10");
            slmax.setFont(Font.font ("Verdana", 20));
        Slider slider = new Slider(0,100,10);                   //slider maksimumi valikuks
            slider.setShowTickLabels(true);
            slider.setShowTickMarks(true);
            slider.setMajorTickUnit(10);
            slider.setOrientation(Orientation.VERTICAL);
        slider.setMinSize(Control.USE_PREF_SIZE,300);
            slider.valueProperty().addListener((observable, vanaVaartus, uusVaartus) -> {
            slmax.setText("Max on " + uusVaartus.intValue());
            max = slider.valueProperty().intValue();
        });

        VBox vasak = new VBox();                                //vasakpoolne akna sisu
            vasak.setAlignment(Pos.TOP_CENTER);
            vasak.getChildren().addAll(slText, slider, slmax);  //vasakpoolse sisu seadistus

        Label pealkiri = new Label("Vali maksimaalne ja tehte liik!");
        pealkiri.setFont(Font.font ("Verdana", 20));
        GridPane kesk = new GridPane();                         //arvutamise keskpaneel
            kesk.setAlignment(Pos.TOP_CENTER);
            kesk.add(pealkiri, 1, 0,4,1);

        for (int i=0; i<10; i++) {                          //keskpaneeli tühi sisu
            ylesanded[i]=new Label("");
                ylesanded[i].setFont(Font.font ("Verdana", 15));
            vastused[i]=new TextField("Sisesta vastus");
                vastused[i].setFont(Font.font ("Verdana", 15));
            oiged[i]=new Label("");
                oiged[i].setFont(Font.font ("Verdana", 15));

            kesk.add(ylesanded[i],0,i+1);                       //keskpaneeli seadistus
            kesk.add(vastused[i],1,i+1);
            kesk.add(oiged[i],2,i+1);
        }

        Button kontroll = new Button("Kontroll");               //keskpaneeli vastuste kontrollimise nupp
        kontroll.setFont(Font.font ("Verdana", 20));
        kontroll.setOnAction((event) -> {                   //kontrolli nupuvajutuse tegevus
            aeg = System.currentTimeMillis() - start;
            aegaKulus.setText("Aega kulus "+ aeg/1000 +" sekundit");
            System.out.println("Kontrollin vastused!");
            System.out.println(aeg/1000);
            for(int i =0; i<10;i++){                            //kontrollib ükshaaval vastused üle
                try{
                    if(Integer.parseUnsignedInt(vastused[i].getText())== tehted[i].c) {
                        oiged[i].setText("Õige vastus!");
                        oiged[i].setTextFill(BLACK);
                    } else {oiged[i].setText("Vale vastus, õige on "+tehted[i].c);
                        oiged[i].setTextFill(RED);
                    }
                }
                catch(Exception e) {
                    oiged[i].setText("Vastus on puudu!");
                    oiged[i].setTextFill(ORANGE);
                }
            }
        });
        kesk.add(kontroll, 1, 11);                          //kontrolli nupp keskpaneelile
        kesk.add(aegaKulus,0,12,4,1);

        GridPane keskJ2r = new GridPane();                      //j2rjestamise keskpaneel
        keskJ2r.setAlignment(Pos.TOP_CENTER);
        Label juhend = new Label("Kliki nuppudel suurenevas järjekorras!");
        juhend.setFont(Font.font ("Verdana", 20));
        keskJ2r.add(juhend,0,0,4,1);
        vead.setFont(Font.font ("Verdana", 20));
        viimane.setFont(Font.font ("Verdana", 20));
        keskJ2r.add(viimane,0,5,4,1);
        keskJ2r.add(vead,0,6,4,1);
        keskJ2r.add(aegaKulus,0,7,4,1);

        BorderPane aken = new BorderPane();                 //terve aken BorderPane
            aken.setTop(valik);
            aken.setCenter(kesk);
            aken.setLeft(vasak);
        Scene esimene = new Scene(aken, 800, 600);          //Stseeni suurus
            stage.setScene(esimene);
            stage.show();

        for(int o=0; o<5; o++) {
            String liik = nupuvalik[o];
            Button valikunupp = new Button(nupuvalik[o]);
            valik.getChildren().add(valikunupp);
            valikunupp.setStyle("-fx-font: 15 verdana; -fx-base: #b6e7c9;");
            valikunupp.setMinSize(120.0, Control.USE_PREF_SIZE);
            valikunupp.setOnAction(e -> {
                start = System.currentTimeMillis();
                System.out.println(liik);
                System.out.println(start);

                if (liik == "Järjestamine") {
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

                    for (int j = 0; j < 12; j++) {      //asetan numbritega nupud gridPanele
                        Button nupp = looNupp(Integer.toString(numbrid[j]));
                        keskJ2r.add(nupp, (j % 3)+1, (j / 3)+1);
                    }
                    loendur = 0;
                    aps = 0;
                    vead.setText("");
                    viimane.setText("");
                    aken.setCenter(keskJ2r);                           //keskosa täitmine järjestamise nuppudega
                }
                else {
                    aken.setCenter(kesk);
                    pealkiri.setText(liik);
                    for (int i = 0; i < 10; i++) {                    //tühja keskosa täitmine valitud ülesannetega
                        tehted[i] = new Tehted();
                        tehted[i].genereeri(max, liik);   //Tehete genereerimise meetodi kasutus
                        ylesanded[i].setText(tehted[i].a + " " + tehted[i].mark + " " + tehted[i].b);
                        vastused[i].setText("");
                        oiged[i].setText("");
                    }
                }
            });
        }
    }
    public Button looNupp(String text) {               //järjestamise nuppude loomine
        Button nupp = new Button(text);
        nupp.setStyle("-fx-font: 20 verdana; -fx-base: #b6e7c9;");
        nupp.setMinSize(150.0, Control.USE_PREF_SIZE);
        nupp.setOnAction(e -> {j2rKontroll(nupp.getText()); System.out.println(text);});
        return nupp ;
    }
    public void j2rKontroll(String a){                      //järjestamise klikkide kontroll
        if (Integer.parseInt(a)==j2rjestatud[loendur]){
            System.out.println("Tubli!");
            if (loendur==11){
                aeg = System.currentTimeMillis() - start;
                aegaKulus.setText("Aega kulus "+ aeg/1000 +" sekundit");
                System.out.println(aeg);
                viimane.setText("Tubli! Leidsid viimase!");
                viimane.setTextFill(LIMEGREEN);
                loendur++;
            }
            else {
                loendur++;
                viimane.setText("Tubli! Viimane number oli " + j2rjestatud[loendur-1]);
                viimane.setTextFill(BLACK);
            }
        }
        else {
            System.out.println("Proovi uuesti!");
            aps++;
            vead.setText("vigade arv = " + aps);
            if(loendur==0){
                viimane.setText("Proovi uuesti! Leia kõige väiksem arv!");
                viimane.setTextFill(RED);
            }
            else{
                viimane.setText("Proovi uuesti! Viimane number oli " + j2rjestatud[loendur-1]);
                viimane.setTextFill(RED);
            }
        }
    }

}

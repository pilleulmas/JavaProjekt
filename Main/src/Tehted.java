/**
 * Created by Pille on 18.10.2016.
 */
public class Tehted {
    String Liik;
    int Arv_1, Arv_2, Vastus, a, b, c;
    String mark ="";

    public void genereeri(int max, String liik) {         //tehete genereerimine
        this.Arv_1 = (int) Math.floor(Math.random()*max)+1;     //esimene suvaarv
        this.Arv_2 = (int) Math.floor(Math.random()*max)+1;     //teine suvaarv
        this.Liik = liik;
        switch(liik){                                     //tehte liigi järgi tehete kuvamine
            case "Liitmine":                                    //arv_1 + arv_2 = vastus
                this.Vastus= Arv_1 + Arv_2;
                this.a= Arv_1;
                this.b= Arv_2;
                this.c=Vastus;
                this.mark = "+";
                break;
            case "Lahutamine":                                  //vastus - arv_1 = arv_2
                this.Vastus= Arv_1 + Arv_2;
                this.a=Vastus;
                this.b= Arv_1;
                this.c= Arv_2;
                this.mark="-";
                break;
            case "Korrutamine":                                 //arv_1 * arv_2 = vastus
                this.Vastus= Arv_1 * Arv_2;
                this.a= Arv_1;
                this.b= Arv_2;
                this.c=Vastus;
                this.mark="*";
                break;
            case "Jagamine":                                    //vastus / arv_1 = arv_2
                this.Vastus= Arv_1 * Arv_2;
                this.a=Vastus;
                this.b= Arv_1;
                this.c= Arv_2;
                this.mark="/";
                break;
            default: System.out.println("mingi vigane sisend!"); break;
        }
    }
}

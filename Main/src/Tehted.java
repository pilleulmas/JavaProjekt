/**
 * Created by Pille on 18.10.2016.
 */
public class Tehted {
    String Tehte_tyyp;
    int Arv_1, Arv_2, Vastus, a, b, c;
    String mark ="";

    public String getTehte_tyyp() {return Tehte_tyyp;}
    public int getArv_1() {return Arv_1;}
    public int getArv_2() {return Arv_2;}
    public int getVastus() {return Vastus;}

    public void genereeri(int max, String tehte_tyyp) {
        this.Arv_1 = (int) Math.floor(Math.random()*max)+1;
        this.Arv_2 = (int) Math.floor(Math.random()*max)+1;
        this.Tehte_tyyp = tehte_tyyp;
        switch(tehte_tyyp){
            case "Liitmine":
                this.Vastus= Arv_1 + Arv_2;
                this.a= Arv_1;
                this.b= Arv_2;
                this.c=Vastus;
                this.mark = "+";
                break;
            case "Lahutamine":
                this.Vastus= Arv_1 + Arv_2;
                this.a=Vastus;
                this.b= Arv_1;
                this.c= Arv_2;
                this.mark="-";
                break;
            case "Korrutamine":
                this.Vastus= Arv_1 * Arv_2;
                this.a= Arv_1;
                this.b= Arv_2;
                this.c=Vastus;
                this.mark="*";
                break;
            case "Jagamine":
                this.Vastus= Arv_1 * Arv_2;
                this.a=Vastus;
                this.b= Arv_1;
                this.c= Arv_2;
                this.mark="/";
                break;
            default: System.out.println("mingi vigane sisend!"); break;
        }
        System.out.println(Arv_1 +mark+ Arv_2);
    }
}

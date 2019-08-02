/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author ASUS
 */
public class Provokasi {
    private double nilai;

    public Provokasi() {
    }

    public Provokasi(double nilai) {
        this.nilai = nilai;
    }
    
//    public double linearNaik(int n, int btsAtas, int btsBwh){
//        return (double)((n-btsBwh)/(btsAtas-btsBwh));
//       
//    }
//    
//    public double linearTurun(int n, int btsAtas, int btsBwh){
//        return (double)(-(n-btsAtas)/(btsAtas-btsBwh));
//    }
    
    public double low(double n){
        double hasil = 0;
        if((n>=0)&&(n<=25)){
            hasil = 1;
        }
        else if((n>25)&&(n<=30)){
            hasil = (double)(30-n)/5;
        }
        return hasil;
    }
    
    public double Normal(double n){
        double hasil = 0;
        if((n>=25)&&(n<30)){
            hasil = (double)(n-30)/5;
        }
        else if((n>=30)&&(n<=60)){
            hasil = 1;
        }
        else if((n>60)&&(n<=65)){
            hasil = (double)(65-n)/5;
        }
        return hasil;
    }
    
    public double High(double n){
        double hasil = 0;
        if((n>=60)&&(n<=65)){
            hasil = (double)(n-60)/5;
        }
        else if((n>=65)&&(n<=85)){
            hasil = 1;
        }
        else if((n>=85)&&(n<=90)){
            hasil = (double)(90-n)/5;
        }
        return hasil;
    }
    
    public double Highest(double n){
        double hasil = 0;
        if((n>=85)&&(n<=90)){
            hasil = (double)(n-85)/5;
        }
        else if((n>=90)&&(n<=100)){
            hasil = 1;
        }
        return hasil;
    }
}

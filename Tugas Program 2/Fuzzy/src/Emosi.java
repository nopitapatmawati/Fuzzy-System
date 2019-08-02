/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author ASUS
 */
public class Emosi {
    private double nilai;

    public Emosi() {
    }

    public Emosi(double nilai) {
        this.nilai = nilai;
    }
    
//    public double linearNaik(int nilai, int btsAtas, int btsBwh){
//        return (nilai-btsBwh)/(btsAtas-btsBwh);
//    }
//    
//    public double linearTurun(int nilai, int btsAtas, int btsBwh){
//        return -(nilai-btsAtas)/(btsAtas-btsBwh);
//    }
    
    public double Rendah(double n){
        double hasil = 0;
        if((n>=0)&&(n<=36)){
            hasil = 1;
        }
        else if((n>36)&&(n<=40)){
            hasil = (double)(40-n)/4;
        }
        return hasil;
    }
    
    public double Sedang(double n){
        double hasil = 0;
        if((nilai>=36)&&(nilai<40)){
            hasil = (double)(n-36)/4;
        }
        else if((n>=40)&&(n<=61)){
            hasil = 1;
        }
        else if((n>61)&&(n<65)){
            hasil = (double)(65-n)/4;
        }
        return hasil;
    }
    
    public double Tinggi(double n){
        double hasil = 0;
        if((n>61)&&(n<65)){
            hasil = (double)(n-61)/4;
        }
        else if((n>=65)&&(n<=76)){
            hasil = 1;
        }
        else if((n>76)&&(n<80)){
            hasil = (double)(80-n)/4;
        }
        return hasil;
    }
    
    public double SangatTinggi(double n){
        double hasil = 0;
        if((n>76)&&(n<80)){
            hasil = (double)(n-76)/4;
        }
        else if((n>=80)&&(n<=100)){
            hasil = 1;
        }
        return hasil;
    }
}

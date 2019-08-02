/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class Proses {
    private double nilaiEmosi;
    private double nilaiProvokasi;
    private String berita;
    private Emosi e= new Emosi();
    private Provokasi p= new Provokasi();
    double small, medium, large, score;

    public Proses(String berita, double nilaiEmosi, double nilaiProvokasi) {
        this.berita = berita;
        this.nilaiEmosi = nilaiEmosi;
        this.nilaiProvokasi = nilaiProvokasi;
    }
    
    public void mulai(){
        Emosi e= new Emosi(nilaiEmosi);
        Provokasi p= new Provokasi(nilaiEmosi) ;
        //showFuzzification();
        inferensiSmall();
        inferensiMedium();
        inferensiHigh();
        defuzzification();
        if(score>50){
            System.out.println(berita+" Hoax = Ya");
        }
        else{
            System.out.println(berita+" Hoax = Tidak");
        }
    }
    
    public void showFuzzification(){
        System.out.println("Emosi Rendah= "+e.Rendah(nilaiEmosi));
        System.out.println("Emosi Sedang= "+e.Sedang(nilaiEmosi));
        System.out.println("Emosi Tinggi= "+e.Tinggi(nilaiEmosi));
        System.out.println("Emosi Sangat Tinggi= "+e.SangatTinggi(nilaiEmosi));
        System.out.println("Provokasi low= "+p.low(nilaiProvokasi));
        System.out.println("Provokasi normal= "+p.Normal(nilaiProvokasi));
        System.out.println("Provokasi high= "+p.High(nilaiProvokasi));
        System.out.println("Provokasi Highest= "+p.Highest(nilaiProvokasi));
    }
    
    public void inferensiSmall(){
        double sm1= Math.min(p.low(nilaiProvokasi), e.Rendah(nilaiEmosi));
        double sm2= Math.min(p.low(nilaiProvokasi), e.Tinggi(nilaiEmosi));
        double sm3 = Math.min(p.low(nilaiProvokasi), e.SangatTinggi(nilaiEmosi));
        double sm4 = Math.min(p.Normal(nilaiProvokasi), e.Tinggi(nilaiEmosi));
        double sm5= Math.min(p.Normal(nilaiProvokasi),e.Sedang(nilaiEmosi));
        double sm6= Math.min(p.High(nilaiProvokasi),e.Sedang(nilaiEmosi));
        small = Math.max(Math.max(Math.max(Math.max(Math.max(sm1, sm2), sm3), sm4), sm5), sm6);
        //System.out.println("Small ="+sm1+","+sm2+","+sm3+","+sm4+","+sm5+","+sm6);
    }
    
    public void inferensiMedium(){
        double med1= Math.min(e.Sedang(nilaiEmosi), p.low(nilaiProvokasi));
        double med2 = Math.min(e.SangatTinggi(nilaiEmosi), p.Normal(nilaiProvokasi));
        double med3 = Math.min(e.Rendah(nilaiEmosi), p.Normal(nilaiProvokasi));
        medium = Math.max(Math.max(med1, med2),med3);
        //System.out.println("Medium= "+med1+","+med2+","+ med3);
    }
    
    public void inferensiHigh(){
        double lar1= Math.min(e.Rendah(nilaiEmosi),p.Highest(nilaiProvokasi));
        double lar2= Math.min(e.Sedang(nilaiEmosi), p.Highest(nilaiProvokasi));
        double lar3= Math.min(e.Tinggi(nilaiEmosi), p.Highest(nilaiProvokasi));
        double lar4= Math.min(e.SangatTinggi(nilaiEmosi), p.Highest(nilaiProvokasi));
        double lar5= Math.min(e.Tinggi(nilaiEmosi), p.High(nilaiProvokasi));
        double lar6= Math.min(e.SangatTinggi(nilaiEmosi), p.High(nilaiProvokasi));
        double lar7 = Math.min(e.Rendah(nilaiEmosi), p.High(nilaiProvokasi));
        large= Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(lar1, lar2),lar3),lar4),lar5),lar6),lar7);
        //System.out.println("Large ="+lar1+","+lar2+","+lar3+","+lar4+","+lar5+","+lar6+","+lar7);
    }
    
    public void defuzzification(){
        score= (25*small+50*medium+75*large)/(small+medium+large);
        //System.out.println(score);
    }
    
}

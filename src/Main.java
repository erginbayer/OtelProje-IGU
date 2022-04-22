import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        Otel koc = new Otel("Koç", 62000.0F);
        Musteri mehmet = new Musteri("23", "Mehmet", "Bayer", 1600.0F);
        new Musteri("25", "Kaan", "Yılmaz", 12300.0F);
        Personel per1 = new Personel("24", "Hakan", "Çınar", 4000.0F);
        Personel per2 = new Personel("22", "Nurten", "Şahin", 6000.0F);
        koc.personelEkle(per1);
        koc.personelEkle(per2);
        koc.odaListe();
        mehmet.odaTut(koc.odaGetir(Tur.TekKisilik, Yon.Bati, Tip.AgacEv));
        System.out.println(koc.odaRaporla(Tur.TekKisilik, Yon.Bati, Tip.AgacEv, false));

        System.out.println(mehmet.adiNe() + " nakiti = " + mehmet.neKadarNakitiVar() + koc.adiNe() + " kasası = " + koc.kasadaNeKadarVar());
        koc.tanit();
        mehmet.odaCik();
        koc.odaListe();
    }

    public static class Otel {
        public String ad;
        private ArrayList<Oda> odalar = new ArrayList();
        private ArrayList<Personel> personeller = new ArrayList();
        private float kasa;

        public Otel(String ad, float kasa) {
            this.dagit();
            this.ad = ad;
            this.kasa = kasa;
        }

        public String adiNe() {
            return this.ad;
        }

        public void adDegis(String ad) {
            this.ad = ad;
        }

        public float kasadaNeKadarVar() {
            return this.kasa;
        }

        public void kasaDegis(float para) {
            if (para >= 0.0F) {
                this.kasa = para;
            }

        }

        public void dagit() {
            this.odalar.clear();

            for(int i = 0; i < 10; ++i) {
                this.odalar.add(new Oda(Integer.toString(i + 1), Tur.values()[(int)(Math.random() * (double)Tur.values().length)], Yon.values()[(int)(Math.random() * (double)Yon.values().length)], Tip.values()[(int)(Math.random() * (double)Tip.values().length)], (int)(Math.random() * 3.0) > 1, this));
            }

        }

        public void odaListe() {
            System.out.println(this.adiNe() + " Otelin Oda Listesi");
            Iterator var1 = this.odalar.iterator();

            while(var1.hasNext()) {
                Oda oda = (Oda)var1.next();
                System.out.println("------------------\n[" + oda.numaraKac() + "]No Oda\n Tür = " + oda.turuNe().getAd() + "\n Yön = " + oda.yonuNe().getAd() + "\n Tipi = " + oda.tipiNe().getAd() + "\n Bakım Durumu = " + oda.bakimdaMi() + "\n Dolu Durumu = " + oda.doluMu() + "\n Kirli Durumu = " + oda.kirliMi() + "\n Konuk = " + oda.konukKim() + "\n Toplam Fiyat = " + oda.degerler() + "TL");
            }

        }

        public Oda odaGetir(Tur t, Yon y, Tip tp) {
            Iterator var4 = this.odalar.iterator();

            Oda oda;
            do {
                if (!var4.hasNext()) {
                    return null;
                }

                oda = (Oda)var4.next();
            } while(oda.tipiNe() != tp || oda.turuNe() != t || oda.yonuNe() != y || !oda.odaDurumu());

            return oda;
        }

        public String odaRaporla(Tur t, Yon y, Tip tp, boolean durum) {
            Iterator var5 = this.odalar.iterator();

            Oda oda;
            do {
                if (!var5.hasNext()) {
                    return null;
                }

                oda = (Oda)var5.next();
            } while(oda.tipiNe() != tp || oda.odaTur != t || oda.odaYon != y || oda.odaDurumu() != durum);

            return "------------------\n[" + oda.numaraKac() + "]No Oda\n Tür = " + oda.turuNe().getAd() + "\n Yön = " + oda.yonuNe().getAd() + "\n Tipi = " + oda.tipiNe().getAd() + "\n Bakım Durumu = " + oda.bakimdaMi() + "\n Dolu Durumu = " + oda.doluMu() + "\n Kirli Durumu = " + oda.kirliMi() + "\n Konuk Adı= " + oda.konukKim().adiNe() + "\n Konuk SoyAdı = " + oda.konukKim().soyadiNe() + "\n Toplam Fiyat = " + oda.degerler() + "TL";
        }

        public float dolulukOrani() {
            float oran = 0.0F;
            Iterator var2 = this.odalar.iterator();

            while(var2.hasNext()) {
                Oda oda = (Oda)var2.next();
                if (oda.doluMu()) {
                    ++oran;
                }
            }

            return 100.0F * oran / (float)this.odalar.size();
        }

        public void personelEkle(Personel p) {
            if (p.calistigiOtel() == null) {
                this.personeller.add(p);
            }

            p.calistigiOtelDegis(this);
            p.maasDegis(2000.0F);
        }

        public void personelMaasDegis(Personel p, float m) {
            if (p.calistigiOtel() == this) {
                p.maasDegis(m);
            }

        }

        public void personelListe() {
            System.out.println("Personel Listesi");
            Iterator var1 = this.personeller.iterator();

            while(var1.hasNext()) {
                Personel per = (Personel)var1.next();
                System.out.println("---------------\n TC No = " + per.tcNumaraNe() + "\n Ad = " + per.adiNe() + "\n Soyad = " + per.soyadiNe() + "\n Verilen Maaş = " + per.maasiKac());
            }
        }
        public void tanit() {
            int musterisayi = 0;

            for(int i = 0; i < this.odalar.size(); ++i) {
                if (((Oda)this.odalar.get(i)).konukKim() != null) {
                    ++musterisayi;
                }
            }
            System.out.println(this.adiNe() + " Otel Hakkında Bilgi\n-------------------\n Personel Sayısı = " + this.personeller.size() + "\n Odalar Sayısı = " + this.odalar.size() + "\n Müşteri Sayısı = " + musterisayi + "\n Doluluk Oranı = %" + this.dolulukOrani() + "\n Toplam Kasa Değeri = " + this.kasadaNeKadarVar());
        }
    }

    public static class Musteri extends Insan {
        private Oda kaldigiOda = null;

        public Musteri(String tcNo, String ad, String soyAd, float nakit) {
            super(tcNo, ad, soyAd, nakit);
        }

        public Oda kaldiginOda() {
            return this.kaldigiOda;
        }

        public void odaTut(Oda o) {
            if (o != null && this.neKadarNakitiVar() >= (float)o.degerler()) {
                o.konukDegis(this);
                o.doluDegis(true);
                this.kaldigiOda = o;
                this.nakitDegis(this.neKadarNakitiVar() - (float)o.degerler());
                o.aitolduguOtel().kasaDegis(o.aitolduguOtel().kasadaNeKadarVar() + (float)o.degerler());
                System.out.println("İşlem tamamlandı.");
            } else {
                System.out.println("Uygun oda bulunmadı.");
            }

        }

        public void odaCik() {
            if (this.kaldiginOda() != null) {
                this.kaldiginOda().kirliDegis(true);
                this.kaldiginOda().doluDegis(false);
                this.kaldiginOda().konukDegis((Musteri)null);
                System.out.println("İşlem tamamlandı.");
                this.kaldigiOda = null;
            } else {
                System.out.println("Çıkacağınız oda yok.");
            }

        }

        private void kaldigiOdaBilgiler() {
            if (this.kaldiginOda() != null) {
                System.out.println(this.adiNe() + " " + this.soyadiNe() + " Kişinin Kaldığı Oda\n" + this.kaldiginOda().odaBilgileri());
            }

        }
    }

    public static class Personel extends Insan {
        private float alacagiMaas = 0.0F;
        private Otel calistigiOtel = null;

        public Personel(String tcNo, String ad, String soyAd, float nakit) {
            super(tcNo, ad, soyAd, nakit);
        }

        public float maasiKac() {
            return this.alacagiMaas;
        }

        public void maasDegis(float m) {
            this.alacagiMaas = m;
        }

        public Otel calistigiOtel() {
            return this.calistigiOtel;
        }

        public void calistigiOtelDegis(Otel o) {
            this.calistigiOtel = o;
        }

        public void maasAl() {
            if (this.calistigiOtel().kasadaNeKadarVar() >= this.maasiKac()) {
                this.nakitDegis(this.neKadarNakitiVar() + this.maasiKac());
                this.calistigiOtel().kasaDegis(this.calistigiOtel().kasadaNeKadarVar() - this.maasiKac());
                System.out.println(this.adiNe() + " personel başarılı şekilde maaş aldı.");
            }

        }

        public void istenAyril() {
            if (this.calistigiOtel() != null) {
                this.calistigiOtel().personeller.remove(this);
            }

            this.calistigiOtelDegis((Otel)null);
            this.maasDegis(0.0F);
            System.out.println(this.adiNe() + " işten ayrıldınız.");
        }
    }

    public static enum Tur {
        TekKisilik(100, "Tek Kişilik"),
        CiftKisilik(200, "Çift Kişilik"),
        DoubleSuite(500, "Double Suite");

        public int fiyat;
        public String ad;

        private Tur(int fiyat, String ad) {
            this.fiyat = fiyat;
            this.ad = ad;
        }

        public int getFiyat() {
            return this.fiyat;
        }

        public String getAd() {
            return this.ad + "(" + this.getFiyat() + "TL)";
        }
    }

    public static enum Yon {
        Guney(30, "Güney"),
        Kuzey(0, "Kuzey"),
        Dogu(20, "Doğu"),
        Bati(10, "Batı");

        public int fiyat;
        public String ad;

        private Yon(int fiyat, String ad) {
            this.fiyat = fiyat;
            this.ad = ad;
        }

        public int getFiyat() {
            return this.fiyat;
        }

        public String getAd() {
            return this.ad + "(" + this.getFiyat() + "TL)";
        }
    }

    public static enum Tip {
        Klasik(0, "Klasik"),
        Bungalov(50, "Bungalov"),
        AgacEv(90, "Ağaç Ev");

        public int fiyat;
        public String ad;

        private Tip(int fiyat, String ad) {
            this.fiyat = fiyat;
            this.ad = ad;
        }

        public int getFiyat() {
            return this.fiyat;
        }

        public String getAd() {
            return this.ad + "(" + this.getFiyat() + "TL)";
        }
    }

    public static class Oda {
        private final String no;
        private final Tur odaTur;
        private final Yon odaYon;
        private final Tip odaTipi;
        private boolean dolu;
        private boolean kirli;
        private boolean bakim;
        private Musteri konuk;
        public Otel sahipOtel;

        public Oda(String no, Tur odaTur, Yon odaYon, Tip odaTipi, boolean bakim, Otel otel) {
            this.no = no;
            this.odaTur = odaTur;
            this.odaYon = odaYon;
            this.odaTipi = odaTipi;
            this.bakim = bakim;
            this.sahipOtel = otel;
            this.dolu = false;
            this.kirli = false;
            this.konuk = null;
        }

        public String numaraKac() {
            return this.no;
        }

        public Tur turuNe() {
            return this.odaTur;
        }

        public Yon yonuNe() {
            return this.odaYon;
        }

        public Tip tipiNe() {
            return this.odaTipi;
        }

        public boolean doluMu() {
            return this.dolu;
        }

        public void doluDegis(boolean dolu) {
            this.dolu = dolu;
        }

        public boolean kirliMi() {
            return this.kirli;
        }

        public void kirliDegis(boolean kirli) {
            this.kirli = kirli;
        }

        public boolean bakimdaMi() {
            return this.bakim;
        }

        public void bakimDegis(boolean bakim) {
            this.bakim = bakim;
        }

        public Musteri konukKim() {
            return this.konuk;
        }

        public void konukDegis(Musteri konuk) {
            this.konuk = konuk;
        }

        public Otel aitolduguOtel() {
            return this.sahipOtel;
        }

        public int degerler() {
            int toplam = 0;
            toplam += this.odaTur.getFiyat() + this.odaYon.getFiyat() + this.odaTipi.getFiyat();
            return toplam;
        }

        private boolean odaDurumu() {
            return !this.bakimdaMi() && !this.kirliMi() && !this.doluMu() && this.konukKim() == null;
        }

        private String odaBilgileri() {
            return "------------------\n[" + this.numaraKac() + "]No Oda\n Tür = " + this.turuNe().getAd() + "\n Yön = " + this.yonuNe().getAd() + "\n Tipi = " + this.tipiNe().getAd() + "\n Bakım Durumu = " + this.bakimdaMi() + "\n Dolu Durumu = " + this.doluMu() + "\n Kirli Durumu = " + this.kirliMi() + "\n Konuk Ad= " + this.konukKim().adiNe() + "\n Konuk SoyAd = " + this.konukKim().soyadiNe() + "\n Ait Olduğu Otel = " + this.aitolduguOtel().adiNe() + "\n Toplam Fiyat = " + this.degerler();
        }

        public void odaTemizle() {
            if (this.kirliMi()) {
                this.kirliDegis(false);
                System.out.println("Oda temizlendi.");
            } else {
                System.out.println("Odanın temizlenmesine gerek yok.");
            }

        }

        public void odaOnar() {
            if (this.bakimdaMi()) {
                this.bakimDegis(false);
                this.doluDegis(false);
                this.kirliDegis(false);
                System.out.println("Oda onarıldı.");
            } else {
                System.out.println("Odanın onarılmasına gerek yok.");
            }

        }
    }

    public static class Insan {
        private final String tcNo;
        public String ad;
        public String soyAd;
        private float nakit;

        public Insan(String tcNo, String ad, String soyAd, float nakit) {
            this.tcNo = tcNo;
            this.ad = ad;
            this.soyAd = soyAd;
            this.nakit = nakit;
        }

        public String tcNumaraNe() {
            return this.tcNo;
        }

        public String adiNe() {
            return this.ad;
        }

        public void adDegis(String ad) {
            this.ad = ad;
        }

        public String soyadiNe() {
            return this.soyAd;
        }

        public void soyadDegis(String soyAd) {
            this.soyAd = soyAd;
        }

        public float neKadarNakitiVar() {
            return this.nakit;
        }

        public void nakitDegis(float n) {
            if (n >= 0.0F) {
                this.nakit = n;
            }
        }
    }
}
package model;

import baza.*;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;

/**
 * Created by dawid on 27.05.16.
 */
@ManagedBean(name="koszyk")
@SessionScoped
//@Path("/kosz")
public class Koszyk {

//    @GET
//    @Produces("koszyk/plain")
//    @Path("/geta")
//    public String getClichedMessage() {
//        // Return some cliched textual content
//        // return allPotrawy.toString();
//        return "koszyczek";
//    }

    private ArrayList<Potrawy> wKoszyku;
    private double koszt;
    private String miejsce, data, godzina;
    private boolean zlozenie = false;
    private boolean zlozono = false;
    private String date;
    private String dataString;
    private ArrayList<String> sposobZaplaty;
    private ArrayList<String> rodzajZamowienia;
    private ArrayList<String> statusy;
    private String wybranySposobZaplaty, wybranyRodzajZamowienia; //="jednorazowe";
    private boolean subskrypcja = false;
    private String formaDaty;
    public String koszyk = "tekst z koszyka";

    Session session = DB.getSession();


    public Koszyk() {
        wKoszyku = new ArrayList<Potrawy>();
    }

    public void dodajDoKoszyka(Potrawy k){
        wKoszyku.add(k);
    }

    public void usunZKoszyka(Potrawy k){
        wKoszyku.remove(k);
    }


    public double koszt(){
        double koszt = 0;
        for(Potrawy k : wKoszyku){
            koszt += k.getCena();
        }

        return koszt;
    }

    public void przygotujZlecenie() {
        setZlozenie(true);
        sposobZaplaty = new ArrayList<String>();
        rodzajZamowienia = new ArrayList<String>();
        statusy = new ArrayList<String>();


        if(DB.getStricZalogowanyUser().getPlatnosc().equals("potracanie z wyplat")){
            sposobZaplaty.add("odliczenie od pensji");
        }
        sposobZaplaty.add("platnosc gotowka");

        rodzajZamowienia.add("jednorazowe");
        rodzajZamowienia.add("subskrypcja");

        statusy.add("do realizacji");
        statusy.add("w trakcie");
        statusy.add("zakonczono");
    }

    public void zakonczZlecenie() {
        Users user = null;

        if(!session.isOpen()) {
            session = DB.getSession();
        }
        Zamowienia zamowienie = new Zamowienia();
        zamowienie.setStatus("do realizacji");
        zamowienie.setData(this.date);
        zamowienie.setGodzina(this.godzina);
        zamowienie.setMiejsce(this.miejsce);
        if(this.wybranySposobZaplaty.equals("platnosc gotowka")) {
            zamowienie.setRachunek(this.koszt());
        } else{
            zamowienie.setRachunek(0.0);
            user = DB.getStricZalogowanyUser();
            user.setDoZaplaty(user.getDoZaplaty()+this.koszt());
        }
        zamowienie.setPlatnosc(this.wybranySposobZaplaty);
        zamowienie.setRodzaj(this.wybranyRodzajZamowienia);

        session.beginTransaction();
        session.save(zamowienie);
        if(!this.wybranySposobZaplaty.equals("platnosc gotowka")) {
            session.update(user);
        }
        session.getTransaction().commit();
        //pobieram id wpisanego zamowienia

        Query zapytanieoId = session.createSQLQuery("SELECT id from zamowienia");
        int id = (Integer) zapytanieoId.list().get(zapytanieoId.list().size() -1);

        System.out.println("Skladam zamowienie o id == " + id);
//wpisanie wszystkich potraw z zamowienia do katalogu do bazy
        session.close();

        for(Potrawy p : wKoszyku){
            p.setIleZamowien(p.getIleZamowien()+1);
            p.setZamawiajacy(p.getZamawiajacy() + " ; " + DB.getStricZalogowanyUser().getUsername());
            final Session session = DB.getSession();
            ZawartoscZamowienia zawartosc = new ZawartoscZamowienia();
            zawartosc.setIdZamowienia(id);
            zawartosc.setIdPotrawy(p.getId());
            session.beginTransaction();
            session.save(zawartosc);
            session.update(p);
            session.getTransaction().commit();
            session.close();
        }

        final Session session = DB.getSession();
        //TODO pobranie id od klienta
        //wpisanie zamowienia do katalogu
        Katalog katalog = new Katalog();
        katalog.setIdKlienta(DB.getStricZalogowanyUser().getId());
        katalog.setIdZamowienia(id);

        session.beginTransaction();
        session.save(katalog);
        session.getTransaction().commit();

        session.close();
        zlozenie = false;
        subskrypcja = false;
        zlozono = true;


    }

    public void pokazDate(){
        if(this.wybranyRodzajZamowienia.equals("jednorazowe")){
            formaDaty = "Data (w formacie: YYYY-MM-DD)";
        } else {
            formaDaty = "Wpisz dni, kiedy dostarczać zamówienie";
        }
        System.out.println("ustalam subskrypcje");
        subskrypcja = true;
    }

    public String pokaz(){
        return "koszyk";
    }


    public String getFormaDaty() {
        return formaDaty;
    }

    public void setFormaDaty(String formaDaty) {
        this.formaDaty = formaDaty;
    }

    public boolean isSubskrypcja() {
        return subskrypcja;
    }

    public void setSubskrypcja(boolean subskrypcja) {
        this.subskrypcja = subskrypcja;
    }

    public ArrayList<String> getStatusy() {
        return statusy;
    }

    public void setStatusy(ArrayList<String> statusy) {
        this.statusy = statusy;
    }

    public double getKoszt() {
        return koszt;
    }

    public void setKoszt(double koszt) {
        this.koszt = koszt;
    }

    public ArrayList<Potrawy> getwKoszyku() {
        return wKoszyku;
    }

    public void setwKoszyku(ArrayList<Potrawy> wKoszyku) {
        this.wKoszyku = wKoszyku;
    }

    public String getMiejsce() {
        return miejsce;
    }

    public void setMiejsce(String miejsce) {
        this.miejsce = miejsce;
    }

    public boolean isZlozenie() {
        return zlozenie;
    }

    public void setZlozenie(boolean zlozenie) {
        this.zlozenie = zlozenie;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGodzina() {
        return godzina;
    }

    public void setGodzina(String godzina) {
        this.godzina = godzina;
    }

    public ArrayList<String> getSposobZaplaty() {
        return sposobZaplaty;
    }

    public void setSposobZaplaty(ArrayList<String> sposobZaplaty) {
        this.sposobZaplaty = sposobZaplaty;
    }

    public ArrayList<String> getRodzajZamowienia() {
        return rodzajZamowienia;
    }

    public void setRodzajZamowienia(ArrayList<String> rodzajZamowienia) {
        this.rodzajZamowienia = rodzajZamowienia;
    }

    public String getWybranySposobZaplaty() {
        return wybranySposobZaplaty;
    }

    public void setWybranySposobZaplaty(String wybranySposobZaplaty) {
        this.wybranySposobZaplaty = wybranySposobZaplaty;
    }

    public String getWybranyRodzajZamowienia() {
        System.out.println("get wybrany rodzaj zmaowienia");
        return wybranyRodzajZamowienia;
    }

    public void setWybranyRodzajZamowienia(String wybranyRodzajZamowienia) {
        System.out.println("set wybrany rodzaj zmaowienia");

        this.wybranyRodzajZamowienia = wybranyRodzajZamowienia;
    }

    public boolean isZlozono() {
        return zlozono;
    }

    public void setZlozono(boolean zlozono) {
        this.zlozono = zlozono;
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    @Override
    public String toString() {
        return "Koszyk{" +
                "wKoszyku=" + wKoszyku +
                ", koszt=" + koszt +
                ", miejsce='" + miejsce + '\'' +
                ", data='" + data + '\'' +
                ", godzina='" + godzina + '\'' +
                ", zlozenie=" + zlozenie +
                ", date=" + date +
                ", sposobZaplaty=" + sposobZaplaty +
                ", rodzajZamowienia=" + rodzajZamowienia +
                ", wybranySposobZaplaty='" + wybranySposobZaplaty + '\'' +
                ", wybranyRodzajZamowienia='" + wybranyRodzajZamowienia + '\'' +
                '}';
    }


}



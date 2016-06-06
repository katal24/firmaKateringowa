package model;

import baza.*;
import mail.Mail;
import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dawid on 11.05.16.
 */
//@Qualifier("dataSource")
@ManagedBean(name="db")
@SessionScoped
public class DB implements Serializable {

    private ArrayList<Potrawy> potrawy;
    private ArrayList<Potrawy> potrawyMenu;
    private ArrayList<Kategorie> kategories;
    private ArrayList<ArrayList<Kategorie>> allPotrawy;
    private List<String> kategoriesNames;
    private List<String> potrawyNames;
    private String kategoria;
    private boolean potrawyListDisabled = true;
    private String wybranaPotrawa, nazwaSkladnika, ilosc;
    private List<Skladniki> listSkladnikowPotrawy;
    private boolean dodajPotrawe;
    private String kategoria1, nazwaPotrawy;
    private double cena;
    private ArrayList<String> rolesList;
    private boolean rejestracja = false;
    private int kategoriaNumber;
    private static Users zalogowanyUser;
    private boolean zmianaHasla = false;
    private ArrayList<ZamowienieKompletne> allZamowienia;
    private ArrayList<ZamowienieKompletne> allSubskrypcje;
    private ArrayList<Potrawy> topTen;
    private String kategoriaMenu;


    public ArrayList<Potrawy> getPotrawyMenu() {
        return potrawyMenu;
    }

    public void setPotrawyMenu(ArrayList<Potrawy> potrawyMenu) {
        this.potrawyMenu = potrawyMenu;
    }

    public String getKategoriaMenu() {
        return kategoriaMenu;
    }

    public ArrayList<Potrawy> getTopTen() {
        return topTen;
    }

    public void setTopTen(ArrayList<Potrawy> topTen) {
        this.topTen = topTen;
    }

    public ArrayList<ZamowienieKompletne> getAllSubskrypcje() {
        return allSubskrypcje;
    }

    public void setAllSubskrypcje(ArrayList<ZamowienieKompletne> allSubskrypcje) {
        this.allSubskrypcje = allSubskrypcje;
    }

    public ArrayList<ZamowienieKompletne> getAllZamowienia() {
        return allZamowienia;
    }

    public void setAllZamowienia(ArrayList<ZamowienieKompletne> allZamowienia) {
        this.allZamowienia = allZamowienia;
    }

    public boolean isZmianaHasla() {
        return zmianaHasla;
    }

    public void setZmianaHasla(boolean zmianaHasla) {
        this.zmianaHasla = zmianaHasla;
    }

    public String getNazwaSkladnika() {
        return nazwaSkladnika;
    }

    public void setNazwaSkladnika(String nazwaSkladnika) {
        this.nazwaSkladnika = nazwaSkladnika;
    }

    public String getIlosc() {
        return ilosc;
    }

    public void setIlosc(String ilosc) {
        this.ilosc = ilosc;
    }

    public boolean isRejestracja() {
        return rejestracja;
    }

    public void setRejestracja(boolean rejestracja) {
        this.rejestracja = rejestracja;
    }

    public ArrayList<String> getRolesList() {
        return rolesList;
    }

    public void setRolesList(ArrayList<String> rolesList) {
        this.rolesList = rolesList;
    }

    public Users getZalogowanyUser() {
        return zalogowanyUser;
    }

    public static Users getStricZalogowanyUser() {
        return zalogowanyUser;
    }

    public void setZalogowanyUser(Users zalogowanyUser1) {
        zalogowanyUser = zalogowanyUser1;
    }

    public static void getUserFromBase(int id){
        Session session = getSession();
        Transaction tx = session.getTransaction();
        zalogowanyUser = (Users) session.get(Users.class, id);
        System.out.println("IMIE: " + zalogowanyUser.getImie());
    }

    public String setRolesList2(){
        System.out.println("jestem w set role 2");
        rejestracja = !rejestracja;
//        this.rolesList = new ArrayList<String>();
//        rolesList.add("admin");
//        rolesList.add("manager menu");
//        rolesList.add("pracownik cateringu");
//        rolesList.add("klient");
        return null;
    }

    public String addUser(Users user) {

        System.out.println("-------------------------Dodaje usera do bazy:");
        //dorobic dodanie usera do bazy

        final Session session = getSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();

        rejestracja = false;
        return null;
    }

    public boolean isDodajPotrawe() {
        return dodajPotrawe;
    }

    public void setDodajPotrawe(boolean dodajPotrawe) {
        this.dodajPotrawe = dodajPotrawe;
    }

    public void setDodajPotrawe(){
        dodajPotrawe = true;
    }

    public void setKategories(ArrayList<Kategorie> kategories) {
        this.kategories = kategories;
    }

    public List<Skladniki> getListSkladnikowPotrawy() {
        return listSkladnikowPotrawy;
    }

    public void setListSkladnikowPotrawy(List<Skladniki> listSkladnikowPotrawy) {
        this.listSkladnikowPotrawy = listSkladnikowPotrawy;
    }

    public String getWybranaPotrawa() {
        return wybranaPotrawa;
    }

    public void setWybranaPotrawa(String wybranaPotrawa) {
        this.wybranaPotrawa = wybranaPotrawa;
        System.out.println("wybrałem potrawe" + wybranaPotrawa);
    }

    // List<String> allPotrawyNames;


    public ArrayList<Potrawy> getPotrawy() {
        return potrawy;
    }

    public void setPotrawy(ArrayList<Potrawy> potrawy) {
        this.potrawy = potrawy;
    }

    public ArrayList<ArrayList<Kategorie>> getAllPotrawy() {
        return allPotrawy;
    }

    public void setAllPotrawy(ArrayList<ArrayList<Kategorie>> allPotrawy) {
        this.allPotrawy = allPotrawy;
    }

    public List<String> getKategoriesNames() {
        return kategoriesNames;
    }

    public void setKategoriesNames(List<String> kategoriesNames) {
        this.kategoriesNames = kategoriesNames;
    }

    public List<String> getPotrawyNames() {
        return potrawyNames;
    }

    public void setPotrawyNames(List<String> potrawyNames) {
        this.potrawyNames = potrawyNames;
    }

    public String getKategoria() {
        return kategoria;
    }

    public boolean isPotrawyListDisabled() {
        return potrawyListDisabled;
    }

    public void setPotrawyListDisabled(boolean potrawyListDisabled) {
        this.potrawyListDisabled = potrawyListDisabled;
    }

    public int getKategoriaNumber() {
        return kategoriaNumber;
    }

    public void setKategoriaNumber(int kategoriaNumber) {
        this.kategoriaNumber = kategoriaNumber;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
        dajPotrawy();
    }

    public void setKategoriaMenu(String kategoria) {
        this.kategoriaMenu = kategoria;
        dajPotrawyMenu();
    }



    public String update(){
        updateKategories1();
        return "wypisz";
    }

    public void pokaz() {
        System.out.println("bede pobieral z BAZY kategorie");
        final Session session = getSession();
        session.beginTransaction();

        Query query = session.createSQLQuery("SELECT * from kategorie").addEntity(Kategorie.class);
        ArrayList<Kategorie> list = (ArrayList<Kategorie>) query.list();
        session.close();
        System.out.println("POBRALEM Z BAZY kategorie");
        kategories = list;
        pokazuje = true;
    }



    public String gotoMojeZamowienia(){
        final Session session = getSession();
        session.beginTransaction();

        allSubskrypcje = new ArrayList<ZamowienieKompletne>();
        allZamowienia = new ArrayList<ZamowienieKompletne>();
        Query q = session.createSQLQuery("select k.idZamowienia, Imie, data, godzina, miejsce, rachunek, status, z.platnosc, notatka, rodzaj from katalog k join zamowienia z on(k.idZamowienia=z.id) join users u on (k.idKlienta=u.id) where u.login='"+zalogowanyUser.getLogin()+"'").addEntity(Zamowienie.class);
        gotoZamow(q);

        session.close();
        return "mojeZamowienia";
    }


    public String gotoZamowienia() {
        System.out.println("jestem w gotoZamowienia");
        final Session session = getSession();
        session.beginTransaction();

        allZamowienia = new ArrayList<ZamowienieKompletne>();
        allSubskrypcje = new ArrayList<ZamowienieKompletne>();

        Query q = session.createSQLQuery("select k.idZamowienia, Imie, data, godzina, miejsce, rachunek, status, z.platnosc, notatka, rodzaj from katalog k join zamowienia z on(k.idZamowienia=z.id) join users u on (k.idKlienta=u.id)").addEntity(Zamowienie.class);
        gotoZamow(q);
        session.close();
        return "zamowienia";
    }

    public void gotoZamow(Query q){
        ArrayList<Zamowienie> zamowieniaList = (ArrayList<Zamowienie>) q.list();
        for(Zamowienie z : zamowieniaList){
            ZamowienieKompletne zk = new ZamowienieKompletne(z);
            if(zk.getZamowienie().getRodzaj().equals("subskrypcja")){
                allSubskrypcje.add(zk);
            } else{
                allZamowienia.add(zk);
            }
        }
    }


    public void updateKategories(){
        potrawy = getPotrawy();
        kategoriesNames = getNameKategorie();
    }

    public void updateKategories1(){
        kategories = getKategories();
        kategoriesNames = getNameKategorie();

        potrawy = new ArrayList<Potrawy>();
        potrawyNames = new ArrayList<String>();

    }


    public void dajPotrawy(){
        potrawy = getPotrawyInKategoria(kategoria);
    }

    public void dajPotrawyMenu(){
        potrawyMenu = getPotrawyMenuInKategoria(kategoriaMenu);
    }

    public void updatePotrawy(){
        potrawyNames = getNamePotrawy();
    }



    public static final SessionFactory ourSessionFactory;
    public static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new AnnotationConfiguration();
            configuration.configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }


    public String zakonczZlecenie(Koszyk k) {

        Zamowienia zamowienie = new Zamowienia();
        zamowienie.setStatus("do realizacji");
        System.out.println("data==" + k.getDataString());
        System.out.println("data==" + k.getMiejsce());
        System.out.println("data==" + k.getKoszt());

        System.out.println("data==" + k.getGodzina());

        return null;
    }


    public void saveKategorie(Kategorie kategoria){
        System.out.println("ZAPISUJE DO BAZY");
        final Session session = getSession();
        session.beginTransaction();
        session.save(kategoria);
        session.getTransaction().commit();
        session.close();
        System.out.println("ZAPISALEM DO BAZY");
    }


    public ArrayList<Kategorie> getKategories(){
        return kategories;
    }

    public ArrayList<String> getNameKategorie(){
        final Session session = getSession();
        session.beginTransaction();

        Query query = session.createSQLQuery("SELECT nazwa from kategorie");
        ArrayList<String> namesList = (ArrayList<String>) query.list();
        session.close();

        System.out.println("POBRAŁEM Z BAZU LISTE kategorii");

        return namesList;
    }

    public ArrayList<Potrawy> getPotrawyDb(){
        System.out.println("bede pobieral z BAZY potrawy");
        final Session session = getSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("SELECT * from potrawy").addEntity(Potrawy.class);
        ArrayList<Potrawy> list = (ArrayList<Potrawy>) query.list();
        session.close();
        System.out.println("POBRALEM Z BAZY Potrawy");

        return list;
    }

    public String setTopTenPotraw(){
        final Session session = getSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("select * from potrawy ORDER BY ileZamowien desc limit 10").addEntity(Potrawy.class);
        topTen = (ArrayList<Potrawy>) query.list();
        session.close();
        return "topTen";
    }

    public ArrayList<String> getNamePotrawy(){
        final Session session = getSession();
        session.beginTransaction();

        Query query = session.createSQLQuery("SELECT nazwaPotrawy from potrawy");
        ArrayList<String> namesList = (ArrayList<String>) query.list();
        session.close();

        return namesList;
    }

public int getKategorieId(String k){
    final Session session = getSession();
    session.beginTransaction();
    System.out.println("aassss id = ");


    Query query = session.createSQLQuery("SELECT id from kategorie where nazwa='"+k+"'");
    int id = ((BigInteger) query.list().get(0)).intValue();
    System.out.println("aa id = " + id);

    session.close();
    return id;
}


    public int getPotrawyId(String k){
        final Session session = getSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("SELECT id from potrawy where nazwaPotrawy='"+k+"'");
        int id = ((BigInteger) query.list().get(0)).intValue();
        session.close();
        return id;
    }


    public ArrayList<Potrawy> getPotrawyInKategoria(String kategoria){
        final Session session = getSession();
        session.beginTransaction();

        Query query = session.createSQLQuery("SELECT * from potrawy where idkategoria= (SELECT id from kategorie where nazwa='"+kategoria+"')").addEntity(Potrawy.class);
        ArrayList<Potrawy> list = (ArrayList<Potrawy>) query.list();
        session.close();
        return list;
    }

    public ArrayList<Potrawy> getPotrawyMenuInKategoria(String kategoria){
        final Session session = getSession();
        session.beginTransaction();

        Query query = session.createSQLQuery("SELECT * from potrawy where idkategoria= (SELECT id from kategorie where nazwa='"+kategoria+"') and isInMenu=1").addEntity(Potrawy.class);
        ArrayList<Potrawy> list = (ArrayList<Potrawy>) query.list();
        session.close();
        return list;
    }

    public ArrayList<Skladniki> getSkladnikiInProdukt(String produkt){
        final Session session = getSession();
        session.beginTransaction();

        Query query = session.createSQLQuery("SELECT * from skladniki where idpotrawy= (SELECT id from potrawy where nazwaPotrawy='"+produkt+"')").addEntity(Skladniki.class);
        ArrayList<Skladniki> list = (ArrayList<Skladniki>) query.list();
        session.close();
        return list;
    }


    public void wybierzPotrawe(Potrawy k) {
        wybranaPotrawa = k.getNazwaPotrawy();
        System.out.println("WYbralem potrawe " + wybranaPotrawa);

        listSkladnikowPotrawy = getSkladnikiInProdukt(wybranaPotrawa);
    }

    public String gotoadmin() {
        updateKategories1();
        return "wypiszadmin";
    }

    public String saveAction3(Kategorie ka){
        ka.setEditable(false);
        final Session session = getSession();
        session.beginTransaction();
        session.update(ka);
        session.getTransaction().commit();
        session.close();
        return null;
    }

    public String saveAction(Potrawy po){
        System.out.println("ZAPISUJE DO BAZY potrawe " + po.getNazwaPotrawy());
        po.setEditable(false);
        final Session session = getSession();
        session.beginTransaction();
        System.out.println("ZAPISUJE DO BAZY potrawe " + po.getIdkategoria());
        session.update(po);
        session.getTransaction().commit();
        session.close();
        return null;
    }

    public String updateZamowienie(ZamowienieKompletne zk){
        final Session session = getSession();
//        System.out.println("BEDE ROBIŁ ZMIANY" +   "UPDATE zamowienia SET status='"+nowyStatus+"'WHERE id='"+id+"'");
//POBRAC ZAMOWIENIE O DANYM ID, ZMIENIC JEGO SSTATUS I UPDATNAC!
        session.beginTransaction();
        Zamowienia zmieniana = (Zamowienia) session.get(Zamowienia.class, zk.getZamowienie().getId());
        zmieniana.setStatus(zk.getZamowienie().getStatus());
        zmieniana.setNotatka(zk.getZamowienie().getNotatka());
        session.update(zmieniana);
        session.getTransaction().commit();
        session.close();
        zk.setEditable(false);
        return null;
    }




    public String editPotrawy(Potrawy k) {
        System.out.println("============= EDYTUJE " + k.getNazwaPotrawy());
        k.setEditable(true);
        return null;
    }

    public String deletePotrawyFromMenu(Potrawy p){

        String zamawiajacy = p.getZamawiajacy();
        String[] zamawiaja = zamawiajacy.split(" ; ");
        System.out.println("ZAMAWIAJACY: " + Arrays.toString(zamawiaja));
        ArrayList<String> mails = new ArrayList<String>();
        final Session session = getSession();
        session.beginTransaction();
        for(String zamawia : zamawiaja){
            if(!zamawia.equals("null")) {
                Query query = session.createSQLQuery("SELECT mail from users where login='" + zamawia + "'");
                String mail = ((String) query.list().get(0).toString());
                mails.add(mail);
            }
        }
        if(mails.size() > 0) {
            Mail.sendMail(p.getNazwaPotrawy(), Arrays.copyOf(mails.toArray(), mails.size(), String[].class));
        }
        p.setIsInMenu("0");
        p.setZamawiajacy("null");
        session.update(p);
        session.getTransaction().commit();
        session.close();

        if(mails.size()>0){
            return "zamowienia";
        }
        return null;
    }

    public String addPotrawyToMenu(Potrawy p){
        p.setIsInMenu("1");
        final Session session = getSession();
        session.beginTransaction();
        session.update(p);
        session.getTransaction().commit();
        session.close();
        return null;
    }


    public String editKategorie(Kategorie k) {
        System.out.println("============= EDYTUJE " + k.getNazwa());
        k.setEditable(true);
        return null;
    }

    public String editZamowienie(ZamowienieKompletne zk) {
//        System.out.println("============= EDYTUJE " + k.getNazwa());
        zk.setEditable(true);
        return null;
    }



    public String saveAction2(Skladniki s){
        s.setEditable(false);
        final Session session = getSession();
        session.beginTransaction();
        session.update(s);
        session.getTransaction().commit();
        session.close();
        return null;
    }

    public String editSkladniki(Skladniki k) {
        k.setEditable(true);
        return null;
    }

    public String deleteKategorie(Kategorie k) {
        kategories.remove(k);

        final Session session = getSession();
        session.beginTransaction();
        session.delete(k);
        session.getTransaction().commit();
        session.close();
        return null;
    }

    public String deletePotrawy(Potrawy k) {
        //todo dodac usuwanie skladnikow z tego produktu z bazy danych
        potrawy.remove(k);
        final Session session = getSession();
        session.beginTransaction();
        session.delete(k);
        session.getTransaction().commit();
        session.close();
        return null;
    }

    public String deleteSkladniki(Skladniki s) {
        listSkladnikowPotrawy.remove(s);
        final Session session = getSession();
        session.beginTransaction();
        session.delete(s);
        session.getTransaction().commit();
        session.close();
        return null;
    }

    public String deleteZamowienie(ZamowienieKompletne zk){
        allZamowienia.remove(zk);
        final Session session = getSession();
        session.beginTransaction();

        Query query = session.createSQLQuery("SELECT * from katalog where idZamowienia='"+zk.getZamowienie().getId()+"'").addEntity(Katalog.class);
        Katalog k1 = (Katalog) query.list().get(0);
        session.delete(k1);
//        session.getTransaction().commit();

        Query query1 = session.createSQLQuery("SELECT * from zawartoscZamowienia where idZamowienia='"+zk.getZamowienie().getId()+"'").addEntity(ZawartoscZamowienia.class);
        ArrayList<ZawartoscZamowienia> list = (ArrayList<ZawartoscZamowienia>) query1.list();
        for(ZawartoscZamowienia z1 : list) {
            System.out.println(z1.getId() + " " + z1.getIdZamowienia() + " " + z1.getIdPotrawy());
            session.delete(z1);
//            session.getTransaction().commit();
        }

        Zamowienia zmieniana = (Zamowienia) session.get(Zamowienia.class, zk.getZamowienie().getId());
        session.delete(zmieniana);
        if(!session.getTransaction().wasCommitted())
        session.getTransaction().commit();

        session.close();
        return null;
    }

    public String addPotrawe() {
        System.out.println("=============================== DODAJE " + kategoria1);
        Potrawy nowa = new Potrawy();
        int id = getKategorieId(kategoria1);
        nowa.setCena(this.cena);
        nowa.setNazwaPotrawy(this.nazwaPotrawy);
        nowa.setIdkategoria(id);
        addPotraweToBase(nowa);
        dodajPotrawe = !dodajPotrawe;

        return null;
    }

    public void addPotraweToBase(Potrawy nowa){

        final Session session = getSession();
        session.beginTransaction();
        session.save(nowa);
        session.getTransaction().commit();

        session.close();
    }


    public void addSkladnikToBase(Skladniki nowy){

        final Session session = getSession();
        session.beginTransaction();
        session.save(nowy);
        session.getTransaction().commit();
        session.close();
    }

    public String addKategorie(){
        System.out.println("=============================== DODAJE " + kategoria1);
        Kategorie nowa = new Kategorie();
        nowa.setNazwa(kategoria1);
        addKategorieToBase(nowa);


//        kategories.add(nowa);
        dodajKategorie = !dodajKategorie;
//        pokaz();
         return null;
    }

    public void addKategorieToBase(Kategorie nowa){

        final Session session = getSession();
        session.beginTransaction();
        session.save(nowa);
        session.getTransaction().commit();
        Query query = session.createSQLQuery("SELECT * from kategorie").addEntity(Kategorie.class);
        ArrayList<Kategorie> list = (ArrayList<Kategorie>) query.list();
        session.close();
        System.out.println("POBRALEM Z BAZY kategorie po oddaniu tam nowej kategorii");
        kategories = list;
//        session.close();
    }

    public String addSkladnik(){
        Skladniki nowy = new Skladniki();
        nowy.setIlosc(ilosc);
        nowy.setNazwaSkladnika(nazwaSkladnika);
        nowy.setIdpotrawy(getPotrawyId(wybranaPotrawa));
        addSkladnikToBase(nowy);

        listSkladnikowPotrawy.add(nowy);
        dodajSkladnik = !dodajSkladnik;
        return null;
    }

    public String getKategoria1() {
        return kategoria1;
    }

    public void setKategoria1(String kategoria1) {
        this.kategoria1 = kategoria1;
    }

    public String getNazwaPotrawy() {
        return nazwaPotrawy;
    }

    public void setNazwaPotrawy(String nazwaPotrawy) {
        this.nazwaPotrawy = nazwaPotrawy;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    private boolean pokazuje = false;

    public boolean isPokazuje() {
        return pokazuje;
    }

    public void setPokazuje(boolean pokazuje) {
        this.pokazuje = pokazuje;
    }



    private boolean dodajKategorie = false;

    public boolean isDodajKategorie() {
        return dodajKategorie;
    }

    public void setDodajKategorie(boolean dodajKategorie) {
        this.dodajKategorie = dodajKategorie;
    }

    public void setDodajKategorie() {
        dodajKategorie = !dodajKategorie;
    }

    private boolean dodajSkladnik=false;

    public boolean isDodajSkladnik() {
        return dodajSkladnik;
    }

    public void setDodajSkladnik(boolean dodajSkladnik) {
        this.dodajSkladnik = dodajSkladnik;
    }

    public String updateZalogowanyUser() {
        final Session session = getSession();
        session.beginTransaction();
        session.update(zalogowanyUser);
        session.getTransaction().commit();
        session.close();
        setZmianaHasla(false);
        return null;

    }

    //    public SurveysEntity getSurveyForName(String name){
//        System.out.println("bede pobieral z BAZY dla danej nazwy");
//        final Session session = getSession();
//        session.beginTransaction();
//
//        Query query = session.createSQLQuery("SELECT * from surveys where name='"+name+"'").addEntity(SurveysEntity.class);
//        SurveysEntity entity = (SurveysEntity) query.list().get(0);
//        session.close();
//        System.out.println("POBRALEM Z BAZY ankiete dla danej nazwy");
//
//        return entity;
//    }
//
//    public static void main() throws Exception {
//        System.out.println("jestem w main w DB");
//        final Session session = getSession();
//
//        SurveysEntity survey = new SurveysEntity("Q","W","E","R","T");
//// zapis do bazy
//        session.beginTransaction();
//        session.save(survey);
//        session.getTransaction().commit();
////        session.close();
//
//
//        //odczyt z bazy przez id
//        Transaction tx = session.getTransaction();
//        survey = (SurveysEntity) session.get(SurveysEntity.class, 1);
//        String variants = survey.getVariants();
//
//
//
//        //odczyt poprzez select CAŁEJ TABELI
//        Query query = session.createQuery("FROM SurveysEntity");
//
//
//        List list = query.list();
//
//        for (Iterator iterator =
//             list.iterator(); iterator.hasNext();){
//            System.out.println("===================================");
//            SurveysEntity survey1 = (SurveysEntity) iterator.next();
//            System.out.print(survey1);
//
//        }
//
//
//        //ODCZYT PRZEZ SELECT KONKRETNEJ WARTOSCI LUB WIERSZA
//        Query query3 = session.createSQLQuery("SELECT categories from surveys where name='c'");
//        String survey3 = (String) query3.list().get(1);
//        System.out.println("WZIALEM CATEGOIRE Z BAZY I WYGLADAJA ONE TAK: " + survey3);
//     //   Query query3 = session.createSQLQuery("SELECT categories from SurveysEntity where name='c'");
//
//
//
//        // odczyt wybranoego elementu
//
////        for(SurveysEntity s : list){
////            System.out.println(s.toString());
////        }
//        session.close();
//
////        try {
////            System.out.println("querying all the managed entities...");
////            final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
////            for (Object key : metadataMap.keySet()) {
////                final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
////                final String entityName = classMetadata.getEntityName();
////                final Query query = session.createQuery("from " + entityName);
////                System.out.println("executing: " + query.getQueryString());
////                for (Object o : query.list()) {
////                    System.out.println("  " + o);
////                }
////            }
////        } finally {
////            session.close();
////        }
//    }

}

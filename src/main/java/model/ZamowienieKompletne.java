package model;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;

/**
 * Created by dawid on 31.05.16.
 */
public class ZamowienieKompletne {

    private Zamowienie zamowienie;
    private ArrayList<String> potrawyList;
    private boolean Editable = false;

    public ZamowienieKompletne(){

    }

    public ZamowienieKompletne(Zamowienie z){
        this.zamowienie = z;

        final Session session = DB.getSession();
        session.beginTransaction();

        Query q = session.createSQLQuery("select nazwaPotrawy from potrawy p join zawartoscZamowienia zz on(zz.idPotrawy=p.id) where zz.idZamowienia='"+zamowienie.getId()+"'");
         potrawyList = (ArrayList<String>) q.list();

    }



    public ArrayList<String> getPotrawyList() {
        return potrawyList;
    }

    public void setPotrawyList(ArrayList<String> potrawyList) {
        this.potrawyList = potrawyList;
    }

    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }

    public boolean isEditable() {
        return Editable;
    }

    public void setEditable(boolean editable) {
        Editable = editable;
    }

    @Override
    public String toString() {
        return "ZamowienieKompletne{" +
                "zamowienie=" + zamowienie +
                ", potrawyList=" + potrawyList +
                '}';
    }
}

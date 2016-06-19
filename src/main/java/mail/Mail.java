package mail;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Properties;

/**
 * Created by dawid on 05.06.16.
 */
@ManagedBean(name="mail")
@RequestScoped
@Path("/mail")
public class Mail {

    private String header;
    private String name;
    private String mailAddress = "talaga@student.agh.edu.pl";
    static Session session;
    static MimeMessage message;
    static Properties props;
    static String host;
    static final String from="talagadawid@gmail.com";
    static final String pass = "dawid516";
    public static String a = "aaaaaaaaaaaa";
//    String[] to ={mailAddress};
//
    @GET
    @Produces("potrawy/plain")
    @Path("/geta")
    public String getClichedMessage() {
        // Return some cliched textual content
        // return allPotrawy.toString();
        return "aa";
    }

    public static void send(){
        System.out.println("WYSYŁAM MAIL");
        props = System.getProperties();
        host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        session = Session.getDefaultInstance(props);
        message = new MimeMessage(session);
    }

    public static void sendMail(String klient, double cena, String to){
        send();

        try{
            message.setFrom(new InternetAddress(from));
            InternetAddress toAddress = new InternetAddress(to);

            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject("Rozlicznie: "+ klient);
            message.setText(klient+ " - pracownik państwa firmy w ostatnim czasie dokonał zamówień na kwotę " + cena + " 0zł. Prosimy o potrącenie z pensji.");

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void sendMail(String potrawa, String[] to){
        send();

        try{
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            for(int i=0; i<to.length; i++){
                toAddress[i] = new InternetAddress(to[i]);
            }

            for(int i=0; i<toAddress.length; i++){
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject("Rozlicznie: "+ to[0]);
            if(potrawa.equals("rozliczenie")){
                message.setText(to[0] + " - pracownik państwa firmy w ostatnim czasie dokonał zamówień na kwotę " + potrawa + " została usunięta z menu. Zachęcamy do złożenia nowego zamówienia!");
            }
            message.setText("Potrawa, którą subskrybujesz " + potrawa + " została usunięta z menu. Zachęcamy do złożenia nowego zamówienia!");

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }




    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
}

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
import java.util.Properties;

/**
 * Created by dawid on 05.06.16.
 */
@ManagedBean(name="mail")
@RequestScoped
public class Mail {

    private String header;
    private String name;
    private String mailAddress = "talaga@student.agh.edu.pl";
//    String[] to ={mailAddress};


    public static void sendMail(String potrawa, String[] to){
        System.out.println("WYSYŁAM MAIL");
        final String from="talagadawid@gmail.com";
        final String pass = "dawid516";
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try{
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            for(int i=0; i<to.length; i++){
                toAddress[i] = new InternetAddress(to[i]);
            }

            for(int i=0; i<toAddress.length; i++){
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }



            message.setSubject("Email from Talarek");
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

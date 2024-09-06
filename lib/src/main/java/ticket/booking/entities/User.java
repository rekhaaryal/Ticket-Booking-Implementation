package ticket.booking.entities;

import javax.security.auth.kerberos.KerberosTicket;
import java.util.List;

public class User {

    private String name;

    private String password;

    private String hashPassword;

    private List<Ticket> ticketsBooked ;

    private String userId;

}

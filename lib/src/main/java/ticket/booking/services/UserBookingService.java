package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Ticket;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class UserBookingService
{

    private User user;

    private List<User> userList;

    private ObjectMapper objectMapper = new ObjectMapper();



    private static final String USER_PATH = "lib/src/main/java/ticket/booking/localDb/Users.json";

    public UserBookingService(User user1) throws IOException {
        this.user= user1;
        loadUsers();

    }

    public UserBookingService() throws IOException {
        loadUsers();
    }
    public List<User>loadUsers() throws IOException{

        File users = new File(USER_PATH);
        return objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1){
        try{
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }
    private void saveUserListToFile() throws IOException{
        File usersFile = new File(USER_PATH);
        objectMapper.writeValue(usersFile , userList);
    }
    // json --> object(user) = deserialize
    // object(User) --> json = serialize

    public void fetchBookings(){
        user.printTicket();
    }

    public Boolean cancleBooking(String ticketId){
        Optional<Ticket> ticketToCancel = user.getTicketsBooked().stream()
                .filter(ticket -> ticket.getTicketId().equals(ticketId))
                .findFirst();

        if (ticketToCancel.isPresent()) {
            user.getTicketsBooked().remove(ticketToCancel.get());
            try {
                saveUserListToFile();
                return Boolean.TRUE;
            } catch (IOException e) {
                return Boolean.FALSE;
            }
        } else {
            return Boolean.FALSE;
        }


    }
}

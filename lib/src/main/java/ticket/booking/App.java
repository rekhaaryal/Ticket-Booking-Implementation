package ticket.booking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class App {

    public static void main(String[] args) {
        List<Integer> l = Arrays.asList(1,2,3,4,5,6);
        List<Integer> l1 = l.stream().filter(isEven()).collect(Collectors.toList());
    }

    public static Predicate<Integer> isEven(){
        return i -> i%2==0;
    }
}

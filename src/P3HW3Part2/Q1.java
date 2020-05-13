package p3hw3part2;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
public class Q1 {

    public static void main(String[] args) {
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // ------------> Question1:A <-------------
        IntConsumer<Integer> Intconsumer1 = (value) -> {
            System.out.printf("%d", value);
        };
        //Consumer<Integer> IntConsumer = (value) -> System.out.printf("%d ", value);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        // -------------> Question1:B <-------------
        UnaryOperator<String> s = String::toUpperCase;
        //System.out.println(s.apply("hello"));
        
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // -------------> Question1:C <-------------
        welcome<String> w = () -> "Welcome to lambdas!";
        //System.out.println(w.get());
        /*
        Supplier<String> w2 = () -> "Welcome to lambdas!";
        System.out.println(w2.get());
         */
        
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // -------------> Question1:D <-------------
        UnaryOperator<Integer> cube = (num) -> {return num*num*num;};
        
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    private interface IntConsumer<T> {

        void accept(T x);
    }
    
    private interface welcome<T>{
        T get();
    }
}

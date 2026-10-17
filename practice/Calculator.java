@FunctionalInterface
public interface Calculator {
    int calculate(int a,int b);

    default String description(){
        return "Calculates a result from two integers";
    }

}





package com.gwsc.functional_programming.lambda;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lambda {

    public static void main(String[] args) {
        // #### [<-Back](../functional_programming.md)
        List<Integer> list= Arrays.asList(1,2,3,4,5);
        list.forEach(n-> System.out.println(n));
    }
}

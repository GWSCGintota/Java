

package com.gwsc.functional_programming.lambda_expressions;

import java.util.Arrays;
import java.util.List;

public class LambdaExpressions {

    public static void main(String[] args) {
        List<Integer> list= Arrays.asList(1,2,3,4,5);
        list.forEach(n-> System.out.println(n));
    }
}

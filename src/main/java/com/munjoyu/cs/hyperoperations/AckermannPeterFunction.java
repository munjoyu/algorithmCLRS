package com.munjoyu.cs.hyperoperations;

//import jdk.internal.net.http.common.Pair;

import java.util.HashMap;
import java.util.Map;

public class AckermannPeterFunction {
    private static Map<Pair<Long, Long>, Long> ackermannMap = new HashMap<>();

    public static void main(String args[]){
        /*
        for(long m=0; m<4; m++) {
            for(long n=0; n<9; n++) {
                ackermannPeterProxy(m, n);
            }
        }
        */

        ackermannPeterProxy(5L, 0L);
        //ackermannPeterProxy(4L, 1L);
        //ackermannPeterProxy(4L, 2L);

        System.out.println("Size of the map = " + ackermannMap.size());
    }

    private static void ackermannPeterProxy(Long m, Long n) {
        //Pair<Long, Long> pair = new Pair<Long, Long>(1L, 2L);
        System.out.println("RESULT of (" + m + ", " + n + ") = " + ackermannPeter(Pair.pair(m, n)));
    }

    private static long ackermannPeter(Pair<Long, Long> p) {
        //Long v = ackermannMap.get(p);
        //if(v != null) {
        //   return v;
        //}
        //System.out.println("(" + p.first + ", " + p.second + ")");
        if(p.first == 0) {
            long ret = p.second + 1;
            //ackermannMap.put(p, ret);
            return ret;
        } else {
            if(p.first-1 < 0) {
                throw new RuntimeException("The first in the pair should be 1 or greater");
            } else {
                if(p.second == 0) {
                    long ret = ackermannPeter(Pair.pair(p.first-1, 1L));
                    //ackermannMap.put(p, ret);
                    return ret;
                } else {
                    if(p.second-1 < 0) {
                        throw new RuntimeException("the second in the pair should be 1 or greater");
                    } else {
                        long ret = ackermannPeter(Pair.pair(p.first-1, ackermannPeter(Pair.pair(p.first, p.second-1))));
                        //ackermannMap.put(p, ret);
                        return ret;
                    }
                }
            }
        }

    }
}

final class Pair<T, U> {

    public Pair(T first, U second) {
        this.second = second;
        this.first = first;
    }

    public final T first;
    public final U second;

    // Because 'pair()' is shorter than 'new Pair<>()'.
    // Sometimes this difference might be very significant (especially in a
    // 80-ish characters boundary). Sorry diamond operator.
    public static <T, U> Pair<T, U> pair(T first, U second) {
        return new Pair<>(first, second);
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || !(o instanceof Pair)) {
            return false;
        }
        Pair p = (Pair) o;
        if(this.first.equals(p.first) && this.second.equals(p.second)) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}

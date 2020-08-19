package com.munjoyu.cs.maximumsubarray;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;

public class MaximumSubarraySolutions {
    public static final int[] StockChangesArray = {100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97};
    //public static final int[] StockChangesArray = new int[18]{100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97};

    public static final List<Pair<Integer, Integer>> StockChanges = new ArrayList<>();


    public static void main(String[] args) {
        StockChanges.add(new Pair(new Integer(StockChangesArray[0]), 0));
        for(int i=1; i<StockChangesArray.length; i++) {
            StockChanges.add(new Pair(new Integer(StockChangesArray[i]), new Integer(StockChangesArray[i]) - StockChangesArray[i-1]));
        }

        System.out.println(findTheMaximumProfitPairWithBruteForce());
        System.out.println(findTheMaximumProfitPairWithKadane());
        System.out.println(findMaximumSubarrayWithDivideAndConquer(0, StockChanges.size()-1));
    }

    private static Pair<Integer, Integer> findTheMaximumProfitPairWithBruteForce() {
        Integer maxProfit = 0;
        Pair<Integer, Integer> maxProfitPair = null; // indices

        for(int i=0; i<StockChangesArray.length; i++) {
            for(int j=i+1; j<StockChangesArray.length; j++) {
                Integer newProfit = StockChangesArray[j] - StockChangesArray[i];
                if(newProfit > maxProfit) {
                    maxProfit = newProfit;
                    maxProfitPair = new Pair(i, j); // indices
                }
            }
        }
        System.out.println("Maximum profit is " + maxProfit + " at " + maxProfitPair.toString());
        return maxProfitPair;
    }

    private static Pair<Integer, Integer> findTheMaximumProfitPairWithKadane() {
        int[] maximums = new int[StockChanges.size()];
        Pair<Integer, Integer> globalMaxProfitPair = null;
        Pair<Integer, Integer> maxProfitPair = new Pair<>(0, 0); // indices

        maximums[0] = 0;
        Integer maxProfit = 0;
        for(int i=1; i<StockChanges.size(); i++) {
            if(maximums[i-1] + StockChanges.get(i).getValue1() < StockChanges.get(i).getValue1()) {
                maximums[i] = StockChanges.get(i).getValue1();
                maxProfitPair = new Pair<>(i-1, i);
            } else {
                maximums[i] = maximums[i-1] + StockChanges.get(i).getValue1();
                maxProfitPair = new Pair<>(maxProfitPair.getValue0(), i);
            }
            if(maxProfit < maximums[i]) {
                maxProfit = maximums[i];
                globalMaxProfitPair = maxProfitPair;
            }
        }
        System.out.println("Maximum profit is " + maxProfit + " at " + globalMaxProfitPair.toString());
        return globalMaxProfitPair;
    }

    private static Triplet<Integer, Integer, Integer> findMaximumSubarrayWithDivideAndConquer(int low, int high) {
        if(low == high) {
            return new Triplet<>(low, high, StockChanges.get(low).getValue1());
        } else {
            int mid = (int) Math.floor((low+high)/2);
            Triplet<Integer, Integer, Integer> leftSub = findMaximumSubarrayWithDivideAndConquer(low, mid);
            Triplet<Integer, Integer, Integer> rightSub = findMaximumSubarrayWithDivideAndConquer(mid+1, high);
            Triplet<Integer, Integer, Integer> crosssSub = findMaxCrossingSubarrayWithDivideAndConquer(low, mid, high);
            if(leftSub.getValue2() >= rightSub.getValue2() && leftSub.getValue2() >= crosssSub.getValue2()) {
                return leftSub;
            } else if(rightSub.getValue2() >= leftSub.getValue2() && rightSub.getValue2() >= crosssSub.getValue2()) {
                return rightSub;
            } else {
                return crosssSub;
            }
        }
    }

    private static Triplet<Integer, Integer, Integer> findMaxCrossingSubarrayWithDivideAndConquer(int low, int mid, int high) {
        Integer leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = -1;
        for(int i=mid; i>=low; i--) {
            sum += StockChanges.get(i).getValue1();
            if(sum > leftSum) {
                leftSum = sum;
                maxLeft = i-1;
            }
        }
        Integer rightSum = Integer.MIN_VALUE;
        sum = 0;
        int maxRight = -1;
        for(int i=mid+1; i<=high; i++) {
            sum += StockChanges.get(i).getValue1();
            if(sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }
        return new Triplet<>(maxLeft, maxRight, leftSum+rightSum);
    }
}

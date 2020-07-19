package com.meli.eval.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListUtils {

    public static <T> List<T> append(List<T> list, T end) {
        var newList = new ArrayList<T>(list.size() + 1);
        newList.addAll(list);
        newList.add(end);
        return Collections.unmodifiableList(newList);
    }

    private static void combinationsHelper(List<int[]> combinations, int[] data, int start, int end, int index) {
        if (index == data.length) {
            int[] combination = data.clone();
            combinations.add(combination);
        } else if (start <= end) {
            data[index] = start;
            combinationsHelper(combinations, data, start + 1, end, index + 1);
            combinationsHelper(combinations, data, start + 1, end, index);
        }
    }

    public static List<int []> generateCombinations (Integer n, Integer r) {
        List<int[]> combinations = new ArrayList<>();
        combinationsHelper(combinations, new int[r], 0, n-1, 0);
        return combinations;
    }
}

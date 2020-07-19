package com.meli.eval.utils;

public class DnaGenerator {
    private static final String evenRow = "ATCG";
    private static final String oddRow = "CGAT";

    public static String[] mockHumanCode (int size){
        var code = new String[size];
        for (int i = 0; i < size; i++) {
            var row = i % 2 == 0 ? evenRow : oddRow;
            code[i] = row.repeat( (size / 4) + 1 ).substring(0,size);
        }
        return code;
    }

}

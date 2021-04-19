package org.example.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CmdArguments {

    public static Map<String, List<String>> parseArqs(String[] arqs) throws Exception {
        final Map<String, List<String>> params = new HashMap<>();
        List<String> options = null;
        for (final String arq : arqs) {
            if (arq.charAt(0) == '-') {
                if (arq.length() < 2) {
                    throw new Exception("Error at argument at CLI: " + arq);
                }
                options = new ArrayList<>();
                params.put(arq.substring(1), options);
            } else if (options != null) {
                options.add(arq);
            } else {
                throw new Exception("Illegal argument at CLI: " + arq);
            }
        }
        return params;
    }

}

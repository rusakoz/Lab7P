package org.server.Analyze;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
@NoArgsConstructor
@Getter
@Setter
public class AntiRecursionScript {
    private static Set<String> set;
    static {
        set = new HashSet<>();
    }
    public static void clearSet(){
        set.clear();
    }
    public static void add(String a){
        set.add(a);
    }
    public static Set<String> getSet(){
        return set;
    }
}

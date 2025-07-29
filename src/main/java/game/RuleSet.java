package game;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RuleSet<T extends Board> implements Iterable<Rule<T>> {
    
    Set<Rule<T>> rules = new HashSet<>();

    @Override
    public Iterator<Rule<T>> iterator() {
       return rules.iterator();
    }

    public void add(Rule<T> boardRule) {
        rules.add(boardRule);
    }   
}

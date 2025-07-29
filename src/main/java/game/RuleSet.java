package game;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RuleSet implements Iterable<Rule> {
    
    Set<Rule> rules = new HashSet<>();

    @Override
    public Iterator<Rule> iterator() {
       return rules.iterator();
    }

    public void add(Rule boardRule) {
        rules.add(boardRule);
    }   
}

package base;

/*
 * Decompiled from UC Berkeley's ucb.gui package.
 * @author P.N. Hilfinger
 */

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class LayoutSpec {
    final GridBagConstraints params = new GridBagConstraints();
    private static final HashSet<String> allSpecs = new HashSet<String>(Arrays.asList("x", "y", "fill", "height", "ht", "width", "wid", "anchor", "weightx", "weighty", "ileft", "iright", "itop", "ibottom"));
    private static final HashMap<Object, Integer> intNames = new HashMap<Object, Integer>();

    public /* varargs */ LayoutSpec(Object ... specs) {
        this.params.weightx = 1.0;
        this.params.weighty = 1.0;
        this.params.insets = new Insets(0, 0, 0, 0);
        this.add(specs);
    }

    public /* varargs */ void add(Object ... specs) {
        int i;
        if (specs.length % 2 == 1) {
            throw new IllegalArgumentException("Missing last value");
        }
        for (i = 0; i < specs.length; i += 2) {
            if (!(specs[i] instanceof String) || !allSpecs.contains(specs[i])) {
                throw new IllegalArgumentException("Illegal LayoutSpec key: " + specs[i]);
            }
            if (specs[i + 1] instanceof Integer || specs[i + 1] instanceof Double || specs[i + 1] instanceof String) continue;
            throw new IllegalArgumentException("Illegal LayoutSpec value for" + specs[i] + " key");
        }
        for (i = 0; i < specs.length; i += 2) {
            Object key = specs[i];
            Object val = specs[i + 1];
            if (key.equals("x")) {
                this.params.gridx = this.toInt(val);
                continue;
            }
            if (key.equals("y")) {
                this.params.gridy = this.toInt(val);
                continue;
            }
            if (key.equals("wid") || key.equals("width")) {
                this.params.gridwidth = this.toInt(val);
                continue;
            }
            if (key.equals("height") || key.equals("ht")) {
                this.params.gridheight = this.toInt(val);
                continue;
            }
            if (key.equals("anchor")) {
                this.params.anchor = this.toInt(val);
                continue;
            }
            if (key.equals("ileft")) {
                this.params.insets.left = this.toInt(val);
                continue;
            }
            if (key.equals("iright")) {
                this.params.insets.right = this.toInt(val);
                continue;
            }
            if (key.equals("itop")) {
                this.params.insets.top = this.toInt(val);
                continue;
            }
            if (key.equals("ibottom")) {
                this.params.insets.bottom = this.toInt(val);
                continue;
            }
            if (key.equals("fill")) {
                this.params.fill = this.toInt(val);
                continue;
            }
            if (key.equals("weightx")) {
                this.params.weightx = this.toDouble(val);
                continue;
            }
            if (!key.equals("weighty")) continue;
            this.params.weighty = this.toDouble(val);
        }
    }

    private int toInt(Object x) {
        if (x instanceof Integer) {
            return (Integer)x;
        }
        if (x instanceof Double) {
            return (int)((Double)x).doubleValue();
        }
        if (!(x instanceof String)) {
            return -1;
        }
        if (intNames.containsKey(x = ((String)x).toLowerCase())) {
            return intNames.get(x);
        }
        return -1;
    }

    private double toDouble(Object x) {
        if (x instanceof Double) {
            return (Double)x;
        }
        return this.toInt(x);
    }

    static {
        for (Object[] pair : new Object[][]{{"center", 10}, {"north", 11}, {"south", 15}, {"east", 13}, {"west", 17}, {"southwest", 16}, {"southeast", 14}, {"northwest", 18}, {"northeast", 12}, {"remainder", 0}, {"rest", 0}, {"horizontal", 2}, {"horiz", 2}, {"vertical", 3}, {"vert", 3}, {"both", 1}}) {
            intNames.put(pair[0], (Integer)pair[1]);
        }
    }
}

package base;

/*
 * Decompiled from UC Berkeley's ucb.gui package.
 * @author P.N. Hilfinger
 */

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class TopLevel implements ActionListener {
    private final HashMap<String, ButtonHandler> buttonMap = new HashMap<String, ButtonHandler>();
    private final HashMap<String, JLabel> labelMap = new HashMap<String, JLabel>();
    private static final HashMap<String, Integer> messageTypeMap = new HashMap<String, Integer>();
    protected final JFrame frame;

    public void display(boolean visible) {
        if (visible) {
            this.frame.pack();
        }
        this.frame.setVisible(visible);
       
    }

    public void setClickEnable(boolean enabled) {
    	frame.setEnabled(enabled);
    }
    
    protected TopLevel(String title, boolean exitOnClose) {
        this.frame = new JFrame(title);
        this.frame.setUndecorated(true);
        this.frame.getRootPane().setWindowDecorationStyle(1);
        this.frame.setLayout(new GridBagLayout());
        if (exitOnClose) {
            this.frame.setDefaultCloseOperation(3);
        }
    }

    protected void setPreferredSize(int width, int height) {
        this.frame.setPreferredSize(new Dimension(width, height));
    }

    protected void setMinimumSize(int width, int height) {
        this.frame.setMinimumSize(new Dimension(width, height));
    }

    protected void setMaximumSize(int width, int height) {
        this.frame.setMaximumSize(new Dimension(width, height));
    }

    public int getWidth() {
        return this.frame.getWidth();
    }

    public int getHeight() {
        return this.frame.getHeight();
    }

    protected void addMenuButton(String label, Object receiver, String funcName) {
        String[] names = label.split("->");
        if (names.length <= 1) {
            throw new IllegalArgumentException("cannot parse label");
        }
        JMenu menu = this.getMenu(names, names.length - 2);
        JMenuItem item = new JMenuItem(names[names.length - 1]);
        item.setActionCommand(label);
        item.addActionListener(this);
        menu.add(item);
        this.buttonMap.put(label, new ButtonHandler(funcName, receiver, label, item));
    }

    protected void addMenuButton(String label, String funcName) {
        this.addMenuButton(label, this, funcName);
    }



    protected void addSeparator(String label) {
        String[] labels = label.split("->");
        if (labels.length == 0) {
            throw new IllegalArgumentException("invalid menu designator");
        }
        JMenu menu = this.getMenu(labels, labels.length - 1);
        menu.addSeparator();
    }

    protected boolean isSelected(String label) {
        ButtonHandler h = this.buttonMap.get(label);
        if (h == null) {
            return false;
        }
        return h.src.getModel().isSelected();
    }

    protected void select(String label, boolean val) {
        ButtonHandler h = this.buttonMap.get(label);
        if (h != null) {
            h.src.setSelected(val);
        }
    }

    protected /* varargs */ void setEnabled(boolean enable, String ... labels) {
        for (String label : labels) {
            ButtonHandler h = this.buttonMap.get(label);
            if (h == null) continue;
            h.src.setEnabled(enable);
        }
    }

    protected void addButton(String label, Object receiver, String funcName, LayoutSpec layout) {
        if (this.buttonMap.containsKey(label)) {
            throw new IllegalStateException("already have button labeled " + label);
        }
        JButton button = new JButton(label);
        button.setActionCommand(label);
        button.addActionListener(this);
        this.frame.add((Component)button, layout.params);
        this.buttonMap.put(label, new ButtonHandler(funcName, receiver, label, button));
    }

    protected void addButton(String label, String funcName, LayoutSpec layout) {
        this.addButton(label, this, funcName, layout);
    }


    protected void add(Widget widget, LayoutSpec layout) {
        this.frame.add((Component)widget.me, layout.params);
    }

    protected void addLabel(String text, String id, LayoutSpec layout) {
        if (this.labelMap.containsKey(id)) {
            throw new IllegalArgumentException("duplicate label id: " + id);
        }
        JLabel label = new JLabel(text);
        this.labelMap.put(id, label);
        this.frame.add((Component)label, layout.params);
    }

    protected void setLabel(String id, String text) {
        JLabel label = this.labelMap.get(id);
        if (label == null) {
            throw new IllegalArgumentException("unknown label id: " + id);
        }
        label.setText(text);
    }

    protected void addLabel(String text, LayoutSpec layout) {
        this.frame.add((Component)new JLabel(text), layout.params);
    }

    public void showMessage(String text, String title, String type) {
        JOptionPane.showMessageDialog(this.frame, text, title, this.getMessageType(type), null);
    }

    public /* varargs */ int showOptions(String message, String title, String type, String deflt, String ... labels) {
        return JOptionPane.showOptionDialog(this.frame, message, title, 0, this.getMessageType(type), null, labels, deflt);
    }

    public String getTextInput(String message, String title, String type, String init) {
        Object input = JOptionPane.showInputDialog(this.frame, message, title, this.getMessageType(type), null, null, init);
        if (input instanceof String) {
            return (String)input;
        }
        return null;
    }

    private int getMessageType(String type) {
        if (type == null) {
            return -1;
        }
        type = type.toLowerCase();
        int intType = -1;
        if (type != null && messageTypeMap.containsKey(type)) {
            intType = messageTypeMap.get(type);
        }
        return intType;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof AbstractButton) {
            String key = e.getActionCommand();
            ButtonHandler h = this.buttonMap.get(key);
            if (h == null) {
                return;
            }
            try {
                h.func.invoke(h.receiver, h.id);
            }
            catch (IllegalAccessException excp) {
                System.err.printf("not allowed to call %s.%n", h.func.getName());
            }
            catch (InvocationTargetException excp) {
                System.err.printf("call to %s caused exception: %s.%n", h.func.getName(), excp.getCause());
            }
        }
    }

    private JMenu getMenu(String[] label, int last) {
        if (this.frame.getJMenuBar() == null) {
            this.frame.setJMenuBar(new JMenuBar());
        }
        JMenuBar bar = this.frame.getJMenuBar();
        JMenu menu = null;
        for (int i = 0; i < bar.getMenuCount() && !(menu = bar.getMenu(i)).getText().equals(label[0]); ++i) {
            menu = null;
        }
        if (menu == null) {
            menu = new JMenu(label[0]);
            bar.add(menu);
        }
        for (int k = 1; k <= last; ++k) {
            JMenu menu0 = menu;
            menu = null;
            for (int i2 = 0; i2 < menu0.getItemCount(); ++i2) {
                JMenuItem item = menu0.getItem(i2);
                if (item == null) continue;
                if (item.getText().equals(label[k])) {
                    if (item instanceof JMenu) {
                        menu = (JMenu)item;
                        break;
                    }
                    throw new IllegalStateException("inconsistent menu label");
                }
                menu = null;
            }
            if (menu != null) continue;
            menu = new JMenu(label[k]);
            menu0.add(menu);
        }
        return menu;
    }


    static {
        messageTypeMap.put("information", 1);
        messageTypeMap.put("warning", 2);
        messageTypeMap.put("error", 0);
        messageTypeMap.put("plain", -1);
        messageTypeMap.put("information", 1);
        messageTypeMap.put("question", 3);
    }

    private static class ButtonHandler
    extends Handler {
        ButtonHandler(String funcName, Object receiver, String id, AbstractButton src) {
            super(funcName, receiver, id, src, String.class);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class Handler {
        Method func;
        Object receiver;
        String id;
        AbstractButton src;

        /* varargs */ Handler(String funcName, Object receiver, String id, AbstractButton src, Class<?> ... args) {
            this.src = src;
            this.receiver = receiver;
            this.id = id;
            if (funcName == null) {
                this.func = null;
            } else {
                try {
                    this.func = receiver.getClass().getDeclaredMethod(funcName, args);
                    this.func.setAccessible(true);
                }
                catch (NoSuchMethodException e) {
                    throw new IllegalArgumentException("Method not found or wrong arguments: " + funcName);
                }
            }
        }
    }

}
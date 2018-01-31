package net.jayde.app.baseui.baseLibraryFrame;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

public class BaseSwingLibraryPanel extends JPanel{
    JSplitPane center = new JSplitPane();
    JPanel bottom = new JPanel();
    protected JTabbedPane tabsTree = new JTabbedPane();
    protected JTabbedPane tabsInfo = new JTabbedPane();
    static String lafName = "com.incors.plaf.alloy.AlloyLookAndFeel";

    public BaseSwingLibraryPanel() {
        initGlobalFont();
        initCenter();
        initBottom();
        setLayout(new BorderLayout());
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    private static void initGlobalFont(){
        FontUIResource fontUIResource = new FontUIResource(new Font("微软雅黑",Font.PLAIN, 12));
        for (Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
            Object key = keys.nextElement();
            Object value= UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontUIResource);
            }
        }
    }
    public void initFont(){
        try {
            //lookandfeel的风格主题
            javax.swing.plaf.metal.MetalLookAndFeel.setCurrentTheme(new javax.swing.plaf.metal.DefaultMetalTheme());
            //设置lookandfeel
            UIManager.setLookAndFeel(lafName);

            Font font = new Font("微软雅黑", Font.PLAIN, 12);
            //字体为宋体，样式为常规，字号12
            UIManager.put("MenuBar.font", font);
            UIManager.put("MenuItem.font", font);
            UIManager.put("Menu.font", font);
            UIManager.put("PopupMenu.font", font);
            UIManager.put("ToolBar.font", font);
            UIManager.put("ToolTip.font", font);
            UIManager.put("TabbedPane.font", font);
            UIManager.put("Label.font", font);
            UIManager.put("List.font", font);
            UIManager.put("ComboBox.font", font);
            UIManager.put("Button.font", font);
            UIManager.put("Table.font", font);
            UIManager.put("TableHeader.font", font);
            UIManager.put("Tree.font", font);
            UIManager.put("TextField.font", font);
            UIManager.put("TextArea.font", font);
            UIManager.put("TitledBorder.font", font);
            UIManager.put("OptionPane.font", font);
            UIManager.put("RadioButton.font", font);
            UIManager.put("CheckBox.font", font);
            UIManager.put("ToggleButton.font", font);
            UIManager.put("Dialog.font", font);
            UIManager.put("Panel.font", font);
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println(lafName + " not supported on this platform. \nProgram Terminated");
            System.exit(0);
        } catch (IllegalAccessException e) {
            System.out.println(lafName + " could not be accessed. \nProgram Terminated");
            System.exit(0);
        } catch (ClassNotFoundException e) {
            System.out.println(lafName + " could not found. \nProgram Terminated");
            System.exit(0);
        } catch (InstantiationException e) {
            System.out.println(lafName + " could not be instantiated. \nProgram Terminated");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Unexpected error. \nProgram Terminated");
            e.printStackTrace();
            System.exit(0);
        }
    }
    public void initCenter() {
        center.setDividerLocation(1.0/3.0);
//        center.setDividerLocation(50);

        center.setLeftComponent(tabsTree);
//        center.setLeftComponent(new JScrollPane(tabsTree));
        center.setRightComponent(tabsInfo);
//        center.setRightComponent(new JScrollPane(tabsInfo));
    }

    public void initBottom() {
        setLayout(new FlowLayout());
        add(new JLabel("info"));
    }
}

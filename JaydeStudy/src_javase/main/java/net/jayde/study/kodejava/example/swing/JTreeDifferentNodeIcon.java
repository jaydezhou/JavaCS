package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;

public class JTreeDifferentNodeIcon extends JFrame {
    public JTreeDifferentNodeIcon() throws HeadlessException {
        initializeUI();
    }

    private void initializeUI() {
        setSize(200, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Countries");
        DefaultMutableTreeNode asia = new DefaultMutableTreeNode("Asia");
        Country[] countries = new Country[]{
                new Country("India", "in.png"),
                new Country("Singapore", "sg.png"),
                new Country("Indonesia", "id.png"),
                new Country("Vietnam", "vn.png"),
        };

        for (Country country : countries) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(country);
            asia.add(node);
        }

        DefaultMutableTreeNode northAmerica = new DefaultMutableTreeNode("North America");
        countries = new Country[]{
                new Country("United States", "us.png"),
                new Country("Canada", "ca.png")
        };

        for (Country country : countries) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(country);
            northAmerica.add(node);
        }

        DefaultMutableTreeNode southAmerica = new DefaultMutableTreeNode("South America");
        countries = new Country[]{
                new Country("Brazil", "br.png"),
                new Country("Argetina", "ar.png"),
                new Country("Uruguay", "uy.png")
        };
        for (Country country : countries) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(country);
            southAmerica.add(node);
        }

        DefaultMutableTreeNode europe = new DefaultMutableTreeNode("Europe");
        countries = new Country[]{
                new Country("United Kingdom", "gb.png"),
                new Country("Germany", "de.png"),
                new Country("Spain", "es.png"),
                new Country("France", "fr.png"),
                new Country("Italy", "it.png")
        };
        for (Country country : countries) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(country);
            europe.add(node);
        }

        root.add(asia);
        root.add(northAmerica);
        root.add(southAmerica);
        root.add(europe);

        final JTree tree = new JTree(root);
        tree.setCellRenderer(new CountryTreeCellRenderer());

        JScrollPane pane = new JScrollPane(tree);
        pane.setPreferredSize(new Dimension(200, 400));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JTreeDifferentNodeIcon().setVisible(true);
            }
        });
    }

    class CountryTreeCellRenderer implements TreeCellRenderer {
        private JLabel label;

        CountryTreeCellRenderer() {
            label = new JLabel();
        }

        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            Object o = ((DefaultMutableTreeNode) value).getUserObject();
            if (o instanceof Country) {
                Country country = (Country) o;
                label.setIcon(new ImageIcon(country.getFlagIcon()));
                label.setText(country.getName());
            } else {
                label.setIcon(null);
                label.setText("" + value);
            }
            return label;
        }
    }

    class Country {
        private String name;
        private String flagIcon;

        Country(String name, String flagIcon) {
            this.name = name;
            this.flagIcon = flagIcon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFlagIcon() {
            return flagIcon;
        }

        public void setFlagIcon(String flagIcon) {
            this.flagIcon = flagIcon;
        }
    }
}

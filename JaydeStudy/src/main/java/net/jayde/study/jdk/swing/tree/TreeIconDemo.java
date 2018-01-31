package com.jayde.codesamples.jdk.swing.tree;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.BorderLayout;
import java.awt.Component;

public class TreeIconDemo extends JFrame
{

    /**
     * ID
     */
    private static final long   serialVersionUID    = 1L;

    private JTree   tree;

    /**
     * Launch the application
     *
     * @param args
     */
    public static void main(String args[])
    {
        try
        {
            TreeIconDemo frame = new TreeIconDemo();
            frame.setVisible(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Create the frame
     */
    public TreeIconDemo()
    {
        super();
        setBounds(100, 100, 500, 375);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        tree = new JTree();
        scrollPane.setViewportView(tree);

        //创建数据
        DefaultTreeModel defaultTreeModel = createModel();

        //设置数据
        tree.setModel(defaultTreeModel);

        //设置自定义描述类
        tree.setCellRenderer(new MyDefaultTreeCellRenderer());
    }

    /**
     * 创建树节点模型
     * @return
     */
    public DefaultTreeModel createModel()
    {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("a");
        DefaultMutableTreeNode b = new DefaultMutableTreeNode("b");
        DefaultMutableTreeNode c = new DefaultMutableTreeNode("c");
        root.add(b);
        root.add(c);
        return new DefaultTreeModel(root);
    }

}
class MyDefaultTreeCellRenderer extends DefaultTreeCellRenderer
{
    /**
     * ID
     */
    private static final long   serialVersionUID    = 1L;

    /**
     * 重写父类DefaultTreeCellRenderer的方法
     */
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean sel, boolean expanded, boolean leaf, int row,
                                                  boolean hasFocus)
    {

        //执行父类原型操作
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
                row, hasFocus);

        setText(value.toString());

        if (sel)
        {
            setForeground(getTextSelectionColor());
        }
        else
        {
            setForeground(getTextNonSelectionColor());
        }

        //得到每个节点的TreeNode
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

        //得到每个节点的text
        String str = node.toString();

        //判断是哪个文本的节点设置对应的值（这里如果节点传入的是一个实体,则可以根据实体里面的一个类型属性来显示对应的图标）
        if (str == "a")
        {
            this.setIcon(new ImageIcon("/Users/jayde/IdeaProjects/jaydemini/out/production/mini/icons/librarytreeicons/libraryopen.png"));
        }
        if (str == "b")
        {
            this.setIcon(new ImageIcon("/Users/jayde/IdeaProjects/jaydemini/out/production/mini/icons/librarytreeicons/libraryclose.png"));
        }
        if (str == "c")
        {
            this.setIcon(new ImageIcon("/Users/jayde/IdeaProjects/jaydemini/out/production/mini/icons/librarytreeicons/node.png"));
        }

        return this;
    }

}
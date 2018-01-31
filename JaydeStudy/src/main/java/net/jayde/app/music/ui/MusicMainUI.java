package net.jayde.app.music.ui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.jayde.app.baseui.baseLibraryFrame.BaseSwingLibraryPanel;
import net.jayde.app.music.pojo.MusicLibrary;
import net.jayde.app.music.util.MusicReadDb;
import net.jayde.app.pm.pojo.ProjectObject;
import net.jayde.app.pm.ui.PmProjectInfoPanel;
import net.jayde.app.pm.ui.PmQuestionPanel;

public class MusicMainUI  extends BaseSwingLibraryPanel {

    private static Logger logger = LogManager.getLogger(MusicMainUI.class.getName());


    MusicLibraryTree groupsTree = new MusicLibraryTree();
    PmProjectInfoPanel projectInfoPanel = new PmProjectInfoPanel();
    PmQuestionPanel projectQuestionPanel = new PmQuestionPanel();
//  JFrame rootFrame = null;
//
//  public JFrame getRootFrame() {
//    return rootFrame;
//  }
//
//  public void setRootFrame(JFrame rootFrame) {
//    this.rootFrame = rootFrame;
//  }

    public MusicMainUI() {
        super();

        MusicReadDb musicReadDb = new MusicReadDb();
        MusicLibrary library = musicReadDb.readByDb();
        groupsTree.setLibrary(library);

        iniGroupsTree();
        iniInfoPanel();

//    projectQuestionPanel.setRootFrame(rootFrame);
        tabsTree.addTab("ProjectTree", new JScrollPane(groupsTree));
        tabsInfo.addTab("ProjectInfo", projectInfoPanel);
        tabsInfo.addTab("ProjectQuestions", new JScrollPane(projectQuestionPanel));
    }


    private void iniGroupsTree() {
        groupsTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) groupsTree.getLastSelectedPathComponent();// 返回最后选定的节点
                System.out.println(selectedNode.getUserObject().getClass());
                Object obj = selectedNode.getUserObject();
                if (obj instanceof ProjectObject) {
                    ProjectObject po = (ProjectObject) obj;
                    projectInfoPanel.setProjectObject(po);
                    projectQuestionPanel.setTableData(po);
                }
            }
        });
    }

    private void iniInfoPanel() {
        // JTextField nameField = new JTextField("name:");
        // projectInfoPanel.setLayout(new BorderLayout());
        // projectInfoPanel.add(nameField, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(50, 50, 1000, 600);
        MusicMainUI musicMainUI = new MusicMainUI();
//    pmUI.setRootFrame(jFrame);
        jFrame.setContentPane(musicMainUI);
        jFrame.setVisible(true);
    }


}

package net.jayde.app.music.ui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import net.jayde.app.music.dao.MusicDao;
import net.jayde.app.music.dao.mybatisDao.MusicDaoMybatis;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.jayde.app.baseui.baseLibraryFrame.BaseSwingLibraryPanel;
import net.jayde.app.music.pojo.MusicLibrary;
import net.jayde.app.music.util.MusicReadDb;
import net.jayde.app.pm.PmReadDb;
import net.jayde.app.pm.pojo.ProjectLibrary;
import net.jayde.app.pm.pojo.ProjectObject;
import net.jayde.app.pm.ui.PmGroupsTree;
import net.jayde.app.pm.ui.PmProjectInfoPanel;
import net.jayde.app.pm.ui.PmQuestionPanel;

public class MusicUI extends BaseSwingLibraryPanel {

    private static Logger logger = LogManager.getLogger(MusicUI.class.getName());


    MusicLibraryTree groupsTree = new MusicLibraryTree();
    MusicGroupUI musicGroupUI= new MusicGroupUI();
    PmQuestionPanel projectQuestionPanel = new PmQuestionPanel();

    public MusicUI() {
        super();

//        MusicReadDb musicReadDb = new MusicReadDb();
//        MusicLibrary library = musicReadDb.readByDb();
        MusicDao dao = new MusicDaoMybatis();
        MusicLibrary library = dao.iniMusicLibrary();
        groupsTree.setLibrary(library);

        iniGroupsTree();
        iniInfoPanel();

        tabsTree.addTab("MusicTree", new JScrollPane(groupsTree));
        tabsInfo.addTab("GroupInfo", musicGroupUI);
        tabsInfo.addTab("ProjectQuestions", new JScrollPane(projectQuestionPanel));
    }


    private void iniGroupsTree() {
        groupsTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) groupsTree.getLastSelectedPathComponent();// 返回最后选定的节点
                System.out.println(selectedNode.getUserObject().getClass());
                Object obj = selectedNode.getUserObject();
//                if (obj instanceof ProjectObject) {
//                    ProjectObject po = (ProjectObject) obj;
//                    projectInfoPanel.setProjectObject(po);
//                    projectQuestionPanel.setTableData(po);
//                }
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
        MusicUI musicUI = new MusicUI();
        jFrame.setContentPane(musicUI);
        jFrame.setVisible(true);
    }
}

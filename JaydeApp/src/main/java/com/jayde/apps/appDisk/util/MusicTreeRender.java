package com.jayde.apps.appDisk.util;

import com.jayde.apps.appDisk.bodisk.AbstractBoFile;
import com.jayde.apps.appDisk.bodisk.BoFile;
import com.jayde.apps.appDisk.bodisk.BoMusicSongFile;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.util
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-09-09 10:49
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-09-09 10:49
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class MusicTreeRender extends DefaultTreeCellRenderer {
    //    static String ICON_PATH = "/Users/mac/IdeaProjects/JavaCS/JaydeApp/src/main/resources/icons/";
    static String ICON_PATH = "/Users/mac/IdeaProjects/JavaCS/JaydeApp/src/main/resources/icons/";
    static Icon ICON_0_LIBRARYSET_CLOSE = new ImageIcon(ICON_PATH + "LibrarySet_Close.png");
    static Icon ICON_0_LIBRARYSET_OPEN = new ImageIcon(ICON_PATH + "LibrarySet_Open.png");
    static Icon ICON_1_LIBRARY_CLOSE = new ImageIcon(ICON_PATH + "Library_Close.png");
    static Icon ICON_1_LIBRARY_OPEN = new ImageIcon(ICON_PATH + "Library_Open.png");
    static Icon ICON_2_GROUP_CLOSE = new ImageIcon(ICON_PATH + "Group_Close.png");
    static Icon ICON_2_GROUP_OPEN = new ImageIcon(ICON_PATH + "Group_Open.png");
    static Icon ICON_3_TYPE_CLOSE = new ImageIcon(ICON_PATH + "Type_Close.png");
    static Icon ICON_3_TYPE_OPEN = new ImageIcon(ICON_PATH + "Type_Open.png");
    static Icon ICON_4_SINGER_CLOSE = new ImageIcon(ICON_PATH + "Singer_Close.png");
    static Icon ICON_4_SINGER_OPEN = new ImageIcon(ICON_PATH + "Singer_Open.png");
    static Icon ICON_5_ALBUM_CLOSE = new ImageIcon(ICON_PATH + "Album_Close.png");
    static Icon ICON_5_ALBUM_OPEN = new ImageIcon(ICON_PATH + "Album_Open.png");
    static Icon ICON_6_CD_CLOSE = new ImageIcon(ICON_PATH + "Cd_Close.png");
    static Icon ICON_6_CD_OPEN = new ImageIcon(ICON_PATH + "Cd_Open.png");
    static Icon ICON_7_MUSIC_FILE = new ImageIcon(ICON_PATH + "File_Music.png");
    static Icon ICON_7_MUSIC_INFO = new ImageIcon(ICON_PATH + "File_Info.png");
    static Icon ICON_MUSIC_FILE_AAC = new ImageIcon(ICON_PATH + "File_Music_Aac.png");
    static Icon ICON_MUSIC_FILE_ACC = new ImageIcon(ICON_PATH + "File_Music_Acc.png");
    static Icon ICON_MUSIC_FILE_ALAC = new ImageIcon(ICON_PATH + "File_Music_Alac.png");
    static Icon ICON_MUSIC_FILE_APE = new ImageIcon(ICON_PATH + "File_Music_Ape.png");
    static Icon ICON_MUSIC_FILE_DSD = new ImageIcon(ICON_PATH + "File_Music_Dsd.png");
    static Icon ICON_MUSIC_FILE_DTS = new ImageIcon(ICON_PATH + "File_Music_Dts.png");
    static Icon ICON_MUSIC_FILE_FLAC = new ImageIcon(ICON_PATH + "File_Music_Flac.png");
    static Icon ICON_MUSIC_FILE_MP3 = new ImageIcon(ICON_PATH + "File_Music_Mp3.png");
    static Icon ICON_MUSIC_FILE_OGG = new ImageIcon(ICON_PATH + "File_Music_Ogg.png");
    static Icon ICON_MUSIC_FILE_WAV = new ImageIcon(ICON_PATH + "File_Music_Wav.png");


    static Icon ICON_AAC = new ImageIcon(ICON_PATH + "aac.jpg");
    static Icon ICON_ALAC = new ImageIcon(ICON_PATH + "alac.jpg");
    static Icon ICON_APE = new ImageIcon(ICON_PATH + "ape.jpg");
    static Icon ICON_FLAC = new ImageIcon(ICON_PATH + "flac.ico");
    static Icon ICON_MP3 = new ImageIcon(ICON_PATH + "mp3.jpg");
    static Icon ICON_MUSIC = new ImageIcon(ICON_PATH + "music.jpg");
    static Icon ICON_WAV = new ImageIcon(ICON_PATH + "wav.jpg");


    static Icon ICON_ROOT_CLOSE = new ImageIcon(ICON_PATH + "Root_Close.png");
    static Icon ICON_ROOT_OPEN = new ImageIcon(ICON_PATH + "Root_Open.png");
    static Icon ICON_BOFOLDER_CLOSE = new ImageIcon(ICON_PATH + "BoFolder_Close.png");
    static Icon ICON_BOFOLDER_OPEN = new ImageIcon(ICON_PATH + "BoFolder_Open.png");

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        Object obj = node.getUserObject();
        if (obj instanceof String) {
            String name = (String) obj;
            if (name.equals("ROOT")) {
                if (expanded) {
                    setIcon(ICON_ROOT_OPEN);
                } else {
                    setIcon(ICON_ROOT_CLOSE);
                }
            }
        }
        if (obj instanceof AbstractBoFile) {
            AbstractBoFile abstractBoFile = (AbstractBoFile) obj;
            switch (abstractBoFile.getFileType()) {
                case AbstractBoFile.FILETYPE_FOLDER_BOSETFOLDER: {
                    break;
                }
                case AbstractBoFile.FILETYPE_FOLDER_BONORMALFOLDER: {
                    break;
                }
                case AbstractBoFile.FILETYPE_FOLDER_BOFOLDER: {
                    if (expanded) {
                        setIcon(ICON_BOFOLDER_OPEN);
                    } else {
                        setIcon(ICON_BOFOLDER_CLOSE);
                    }
                    break;
                }

                case AbstractBoFile.FILETYPE_FILE_BOMUSICSONGFILE: {
                    BoMusicSongFile musicSongFile = (BoMusicSongFile) obj;
                    String suffix = musicSongFile.getSuffix();
                    if (suffix.equals("APE")) {
                        setIcon(ICON_MUSIC_FILE_APE);
                    } else if (suffix.equals("MP3")) {
                        setIcon(ICON_MUSIC_FILE_MP3);
                    } else if (suffix.equals("FLAC")) {
                        setIcon(ICON_MUSIC_FILE_FLAC);
                    } else if (suffix.equals("WAV")) {
                        setIcon(ICON_MUSIC_FILE_WAV);
                    } else {
                        setIcon(ICON_MUSIC);
                    }

                }
                case AbstractBoFile.FILETYPE_FILE_BOFILE: {
                    break;
                }
            }
        }
        return this;
    }

}

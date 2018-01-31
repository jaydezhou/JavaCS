package net.jayde.app.music.util;

import net.jayde.app.music.pojo.MusicFavourite;
import net.jayde.app.music.pojo.MusicPerson;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.Thread.sleep;

public class MusicSaveDb extends MusicDb {

    public void insertMusicFavourite(MusicPerson person) {

        if (person.getFavouriteSet().size() > 0) {
            if (conn == null) {
                connDb();
            }
            try {

                    PreparedStatement psmt = conn.prepareStatement("insert into musicfavourite(id,name,mpid,m163id,mgid) values(?,?,?,?,?);");

                for (MusicFavourite mf : person.getFavouriteSet()) {
                    System.out.println(mf.getName());
                    psmt.setString(1,mf.getId());
                    psmt.setString(2,mf.getName());
                    psmt.setString(3,mf.getMpid());
                    psmt.setString(4,mf.getM163Id());
                    psmt.setString(5,person.getMgId());
                    psmt.addBatch();
                }
                psmt.executeBatch();
                psmt.close();
                conn.commit();
//                Thread.sleep(1000);
            }catch (SQLException e){
                System.out.println(e.getMessage());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
            }

            disconnDb();
        }

    }
}

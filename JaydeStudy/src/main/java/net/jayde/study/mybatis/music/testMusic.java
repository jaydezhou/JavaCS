package net.jayde.study.mybatis.music;

import net.jayde.study.Algorithms4.StdIn;
import net.jayde.study.mybatis.music.BO.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class testMusic {
    public static void main(String[] args) {

//
//        Connection conn = null;
//        Statement stmt = null;
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jaydedb", "jayde", "7128587");
//
//            conn.setAutoCommit(false); //设为手动提交
//            long start = System.currentTimeMillis();
//            System.out.println(start);
//
//            stmt = conn.createStatement();
//            long end = System.currentTimeMillis();
//            System.out.println(end);
//            System.out.println(UUID.randomUUID());
//
//            stmt.close();
//            conn.close();
//            System.out.println(end - start);
//        } catch (Exception ex) {
//            System.out.println(ex.getLocalizedMessage());
//        }


        String resource = "net/jayde/study/mybatis/music/mybatis-music.xml";
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            AllMusicMapper allMusicMapper = sqlSession.getMapper(AllMusicMapper.class);
            Singer singer = allMusicMapper.getSingerById("e1920bee-25ff-11e8-8dda-fd785f732561");
            System.out.println("name: " + singer.getCnName());
//            SongMapper songMapper = sqlSession.getMapper(SongMapper.class);
//            Song song = songMapper.getSong("5c7e1f89-84a4-471c-ac16-ee01e1e5fc1e");
//            System.out.println(song.getCnName());

            List<Song> songList = allMusicMapper.getSongsBySingerId(singer.getSingerId());
            for (Song onesong : songList) {
                System.out.println("onesong:" + onesong.getCnName());
            }
            System.out.println(allMusicMapper.getSingerByName("刘德华").getSingerId());
        } finally {
            sqlSession.close();

        }

        String[] songs = StdIn.readAllStrings();
        for (String songname : songs) {
            System.out.println(songname);
        }
    }
}

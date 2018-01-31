package net.jayde.util.file;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp4.Mp4FileReader;
import org.jaudiotagger.audio.mp4.Mp4InfoReader;
import org.jaudiotagger.audio.mp4.atom.Mp4AlacBox;
import org.jaudiotagger.audio.mp4.atom.Mp4BoxHeader;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.mp4.Mp4FieldKey;
import org.jaudiotagger.tag.mp4.Mp4Tag;
import org.jaudiotagger.tag.mp4.field.Mp4DiscNoField;
import org.jaudiotagger.tag.mp4.field.Mp4TagReverseDnsField;
import org.jaudiotagger.tag.mp4.field.Mp4TrackField;
import org.jaudiotagger.utils.FileTypeUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TagReaderALAC {
    public static void main(String[] args) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        String filename = "/Users/zhouyongjian/Desktop/邓丽君《邓丽君音乐手札》[CD10](流行篇).m4a";
        File file = new File(filename);
        AudioFile audioFile = AudioFileIO.read(file);
        Tag tag = audioFile.getTag();
        System.out.println(tag);
        Mp4Tag mp4Tag = (Mp4Tag) tag;
        System.out.println(mp4Tag);
        System.out.println("---------------------");
        System.out.println(mp4Tag.getFirst(Mp4FieldKey.ARTIST));
        System.out.println(mp4Tag.getFirst(Mp4FieldKey.COMPOSER));
        System.out.println(mp4Tag.getFirst(Mp4FieldKey.LYRICS));
        System.out.println(mp4Tag.getFirst(Mp4FieldKey.PART_OF_GAPLESS_ALBUM));
        System.out.println(mp4Tag.getFirst(Mp4FieldKey.ITUNES_SMPB));
        TagField covrArtField = mp4Tag.getFirstField(Mp4FieldKey.ARTWORK);
        BufferedImage bi = ImageIO.read(ImageIO.createImageInputStream(new ByteArrayInputStream(covrArtField.getRawContent())));
        System.out.println("bi:"+bi);
        Mp4TagReverseDnsField dnsField = (Mp4TagReverseDnsField) mp4Tag.getFirstField(Mp4FieldKey.MUSICBRAINZ_ALBUMID);
        System.out.println("dns:"+dnsField);
//        System.out.println(dnsField.getIssuer());
//        System.out.println(dnsField.getDescriptor());
//        System.out.println(dnsField.getContent());

        List<Artwork> listArtworks = mp4Tag.getArtworkList();
        for(Artwork artwork :listArtworks){
            System.out.println("artwork:"+artwork);
        }

        Mp4BoxHeader boxHeader = new Mp4BoxHeader();
//        Mp4AlacBox alacBox = new Mp4AlacBox();
//        System.out.println(alacBox);
        //        Mp4DiscNoField mp4DiscNoField = (Mp4DiscNoField)mp4Tag.getFirstField(Mp4FieldKey.DISCNUMBER);
//        System.out.println(mp4DiscNoField);
//        Mp4TrackField mp4TrackField = (Mp4TrackField)mp4Tag.getFirstField(Mp4FieldKey.TRACK);
//        System.out.println(mp4TrackField.getTrackTotal());
//        System.out.println(mp4Tag);
//        Mp4FileReader mp4FileReader = new Mp4FileReader();
//        AudioFile audioFile = mp4FileReader.read(file);
//        System.out.println(audioFile.getAudioHeader());
//        Mp4Tag tag = (Mp4Tag) audioFile.getTag();
//        System.out.println(tag);
//        List<TagField> listTags = tag.getAll();
//        for (TagField onetag : listTags) {
//            System.out.println(onetag.getId());
//        }
//        System.out.println(tag.getValue(FieldKey.TRACK_TOTAL, 1));
//        System.out.println(FileTypeUtil.getMagicExt("MP4"));
//        org.jaudiotagger.audio.mp4.Mp4InfoReader mp4InfoReader = new Mp4InfoReader();
//        AudioHeader audioHeader = audioFile.getAudioHeader();
//        System.out.println(audioHeader);

    }
}

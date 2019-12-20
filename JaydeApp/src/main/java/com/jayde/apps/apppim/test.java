package com.jayde.apps.apppim;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.io.xml.XCardWriter;
import ezvcard.property.Categories;
import lombok.extern.log4j.Log4j;

import java.io.*;
import java.util.List;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.apppim
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-02-18 16:15
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-02-18 16:15
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Log4j
public class test {
    public static void main(String[] args) {

        try {
            File file = new File("/Users/mac/Library/Mobile Documents/com~apple~CloudDocs/文档集/vcard-all.vcf");
            List<VCard> vcardList = Ezvcard.parse(file).all();
            File xmlfile = new File("vcards.xml");
            XCardWriter writer = new XCardWriter(xmlfile, 2);
            for (VCard vcard : vcardList) {
//                if(vcard.getImpps().size()>0){
//                    log.info(vcard);
//                    Impp impp = vcard.getImpps().get(0);
//                    log.error(impp.);
                log.warn("-------------------------------------");
//                }
//                List<Photo> photoList = vcard.getPhotos();

//                if (photoList.size() > 0) {
//                    vcard.removeProperty(photoList.get(0));
//                }
//                log.info(vcard);
                String xml = Ezvcard.writeXml(vcard).go();
//                System.out.println(xml);
//                List<Photo> photoList = vcard.getPhotos();
//                if (photoList!=null && photoList.size()>0) {
//                    for (Photo photo : photoList) {
//                        vcard.removeProperty(photo);
//                    }
//                }
//                vcard.removeProperties(Photo.class);
                writer.write(vcard);
                Categories categories = vcard.getCategories();
                System.out.println(categories);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/* 这是一个分支1版本 */
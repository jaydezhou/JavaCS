package net.jayde.app.calendar;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.CalendarComponent;
import net.fortuna.ical4j.model.component.VAlarm;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Attach;
import net.fortuna.ical4j.model.property.Attendee;
import net.fortuna.ical4j.model.property.Categories;
import net.fortuna.ical4j.model.property.Comment;
import net.fortuna.ical4j.model.property.Contact;
import net.fortuna.ical4j.model.property.ExDate;
import net.fortuna.ical4j.model.property.ExRule;
import net.fortuna.ical4j.model.property.RDate;
import net.fortuna.ical4j.model.property.RRule;
import net.fortuna.ical4j.model.property.RelatedTo;
import net.fortuna.ical4j.model.property.Resources;
import net.fortuna.ical4j.model.property.Status;
import net.fortuna.ical4j.model.property.XProperty;
import net.fortuna.ical4j.vcard.property.Related;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;

public class IcsImport {
    public static String filename = "d:\\IdeaProjects\\日历MAC.ics";

    public static void main(String[] args) throws IOException, ParserException {


        FileInputStream fin = new FileInputStream(filename);

        CalendarBuilder builder = new CalendarBuilder();

        Calendar calendar = builder.build(fin);
//        calendar.validate();
        ComponentList<CalendarComponent> cl = calendar.getComponents(Component.VEVENT);
//        System.out.println(Component.AVAILABLE+":"+calendar.getComponents(Component.AVAILABLE));
//        System.out.println(Component.VALARM+":"+calendar.getComponents(Component.VALARM));
//        System.out.println(Component.VEVENT+":"+calendar.getComponents(Component.VEVENT));
//        System.out.println(Component.VVENUE+":"+calendar.getComponents(Component.VVENUE));
//        System.out.println(Component.VFREEBUSY+":"+calendar.getComponents(Component.VFREEBUSY));
//        System.out.println(Component.VJOURNAL+":"+calendar.getComponents(Component.VJOURNAL));
//        System.out.println(Component.VTIMEZONE+":"+calendar.getComponents(Component.VTIMEZONE));
//        System.out.println(Component.VAVAILABILITY+":"+calendar.getComponents(Component.VAVAILABILITY));
//        System.out.println(Component.VTODO+":"+calendar.getComponents(Component.VTODO));

        cl = calendar.getComponents(Component.VEVENT);
        int i=0;
        for (CalendarComponent cc : cl) {
            System.out.println(cc.getName()+":   "+cc.getProperty(Property.UID).toString());
            if(cc instanceof VEvent){
                VEvent vEvent = (VEvent)cc;
                System.out.println("class:"+vEvent.getClassification());
                System.out.println("created:"+vEvent.getCreated());
                System.out.println("description:"+vEvent.getDescription());
                System.out.println("dtstart:"+vEvent.getStartDate());
                System.out.println("geo:"+vEvent.getGeographicPos());
                System.out.println("last-mod:"+vEvent.getLastModified());
                System.out.println("location:"+vEvent.getLocation());
                System.out.println("organizer:"+vEvent.getOrganizer());
                System.out.println("priority:"+vEvent.getPriority());
                System.out.println("dtstamp:"+vEvent.getDateStamp());
                System.out.println("seq:"+vEvent.getSequence());
                System.out.println("status:"+vEvent.getStatus());
                System.out.println("summary:"+vEvent.getSummary());
                System.out.println("transp:"+vEvent.getTransparency());
                System.out.println("uid:"+vEvent.getUid());
                System.out.println("url:"+vEvent.getUrl());
                System.out.println("recurid:"+vEvent.getRecurrenceId());
                System.out.println("dtend:"+vEvent.getEndDate());
                System.out.println("duration:"+vEvent.getDuration());

                PropertyList<Attach> attaches= vEvent.getProperties(Property.ATTACH);
                for(Attach attach:attaches){
                        System.out.println("【Attach】"+attach);
                    System.out.println(attach.getUri().getUserInfo());
                    System.out.println(attach.getValue());
                }

                PropertyList<Attendee> attendees= vEvent.getProperties(Property.ATTENDEE);
                for(Attendee attendee:attendees){
                    System.out.println("【Attendee】"+attendee);
                }

                PropertyList<Categories> categories = vEvent.getProperties(Property.CATEGORIES);
                for(Categories categories1 :categories){
                    System.out.println("【Categories】"+categories1);
                }

                PropertyList<Comment> comments= vEvent.getProperties(Property.COMMENT);
                for(Comment comment:comments){
                    System.out.println("【Comment】"+comment);
                }

                PropertyList<Contact> contacts = vEvent.getProperties(Property.CONTACT);
                for(Contact contact:contacts){
                    System.out.println("【Contact】"+contact);
                }

                PropertyList<ExDate> exDates = vEvent.getProperties(Property.EXDATE);
                for(ExDate exDate  :exDates){
                    System.out.println("【Exdate】"+exDate);
                }

                PropertyList<ExRule> exRules = vEvent.getProperties(Property.EXRULE);
                for(ExRule exRule :exRules){
                    System.out.println("【ExRule】"+exRule);
                }

                PropertyList<Status>  statuses= vEvent.getProperties(Property.STATUS);
                for(Status status :statuses){
                    System.out.println("【Status】"+status);
                }

                PropertyList<RelatedTo> relatedTos= vEvent.getProperties(Property.RELATED_TO);
                for(RelatedTo relatedTo :relatedTos){
                    System.out.println("【RelatedTo】"+relatedTo);
                }

                PropertyList<Resources> resources = vEvent.getProperties(Property.RESOURCES);
                for(Resources resources1 :resources){
                    System.out.println("【Resources】"+resources1);
                }

                PropertyList<RDate> rDates = vEvent.getProperties(Property.RDATE);
                for(RDate rDate :rDates){
                    System.out.println("【Rdate】"+rDate);
                }

                PropertyList<RRule> rRules = vEvent.getProperties(Property.RRULE);
                for(RRule rRule :rRules){
                    System.out.println("【RRule】"+rRule);
                }

                PropertyList<XProperty> xProperties = vEvent.getProperties(Property.EXPERIMENTAL_PREFIX);
                for(XProperty xProperty :xProperties){
                    System.out.println("【XProperty】"+xProperty);
                }

                ComponentList<VAlarm> alarms = vEvent.getAlarms();
                for(VAlarm alarm :alarms){
                    System.out.println("---------------------------------------\n【VAlarm】"+alarm);
                    Attendee a = new Attendee();
                    URI uri = a.getCalAddress();
                }

//                System.out.println(attachs);

//                System.out.println(":"+vEvent.());
//                System.out.println(":"+vEvent.());
            }
//            if(cc.getProperty(Property.DTSTAMP)!=cc.getProperty(Property.CREATED)) {
//                System.out.println("------------------------------------");
//                System.out.println("------------------------" + cc.getName() + "--------------------------------");
//                System.out.println(cc.getProperties());
//                PropertyList<Property> pl = cc.getProperties();
//                for (Property p : pl) {
//                    System.out.println("【" + p.getName() + "】" + p.getValue());
//                }
//                i++;
//            }
//            if(cc.getName().equals(Component.VEVENT)){
//                VEvent ce = (VEvent) cc;
//                System.out.println(cc);
//                System.out.println("\n\n");
//                System.out.println(ce);
//                System.out.println(ce.getSummary());
//
        }
    }
}

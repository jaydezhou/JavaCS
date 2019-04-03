package com.jayde.util.dateutil;

import lombok.extern.log4j.Log4j;
import org.springframework.cglib.core.Local;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.util.dateutil
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-04-03 10:39
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-04-03 10:39
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Log4j
public class LocalDateUtil {
    public LocalDate dateToLocalDate(Date inputDate) {
        if (inputDate == null) {
            return null;
        }
        Instant instant = inputDate.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        return instant.atZone(zoneId).toLocalDate();
    }

    public Date localDateToDate(LocalDate inputLocalDate) {
        if (inputLocalDate == null) {
            return null;
        }
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = inputLocalDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);

        return date;
    }
}

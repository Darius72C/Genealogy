/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.dpo;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FACULTY
 */
@XmlRootElement(name = "Service")
@XmlAccessorType(XmlAccessType.FIELD)
public class Service implements Serializable {

    @XmlElement
    String ServiceDescription = "Achats des produits";
    @XmlElement
    String ServiceType = "3854";
    @XmlElement
    String ServiceDate;
    @XmlElement
    String ServiceFrom;
    @XmlElement
    String ServiceTo;

    public Service() {
        ServiceDate = convertDate(new Date());
    }

    public void setServiceDescription(String ServiceDescription) {
        this.ServiceDescription = ServiceDescription;
    }

    public String getServiceDescription() {
        return this.ServiceDescription;
    }

    public void setServiceType(String ServiceType) {
        this.ServiceType = ServiceType;
    }

    public String getServiceType() {
        return this.ServiceType;
    }

    public void setServiceDate(String ServiceDate) {
        this.ServiceDate = ServiceDate;
    }

    public String getServiceDate() {
        return this.ServiceDate;
    }

    public void setServiceFrom(String ServiceFrom) {
        this.ServiceFrom = ServiceFrom;
    }

    public String getServiceFrom() {
        return this.ServiceFrom;
    }

    public void setServiceTo(String ServiceTo) {
        this.ServiceTo = ServiceTo;
    }

    public String getServiceTo() {
        return this.ServiceTo;
    }

    public static String convertDate(Date date) {
        String dateStr ="";
        try {
          /*  System.out.println(date);
            //Initialize your Date however you like it.
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
//Add one to month {0 - 11}
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int hour = calendar.get(Calendar.HOUR);
            int minutes = calendar.get(Calendar.MINUTE);
            String ys = "" + year, ms = "" + month, ds = "" + day, hs = "" + hour, mms = "" + minutes, dateStr = "";
            if (month < 10) {
                ms = "0" + month;
            }
            if (day < 10) {
                ds = "0" + day;
            }
            if (hour < 10) {
                hs = "0" + hour;
            }
            if (minutes < 10) {
                mms = "0" + minutes;
            }
            dateStr = ys + "/" + ms + "/" + ds + " " + hs + ":" + mms;
            System.out.println(dateStr);*/
            //date.getTime();
           SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm");
           dateStr = format.format(date);
           System.out.println(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    public static void main(String[] args) {
        Service service = new Service();
        System.out.println(service.getServiceDate());
    }
}

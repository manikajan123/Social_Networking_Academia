package models;

import com.avaje.ebean.Model;
import javax.persistence.Entity;

/**
 * Created by zhangyan on 12/1/16.
 */

@Entity
public class Mail extends Model {

    public String userid;
    public String target;
    public String subject;
    public String content;

    public Mail(String userid, String target, String subject, String content) {
        this.userid = userid;
        this.target = target;
        this.subject = subject;
        this.content = content;
    }

    public static Finder<String, Mail> find = new Finder<String, Mail>(Mail.class);

}
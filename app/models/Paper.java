package models;



import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 *  Db model to store Project Details
 */

/**
 * Created by surbhi on 12/8/2016.
 */

@Entity
public class Paper extends Model{
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public static Finder<Integer, Paper> getFind() {
        return find;
    }

    public static void setFind(Finder<Integer, Paper> find) {
        Paper.find = find;
    }

    /**
     *  name -Name of the Project ->Primary Key,Due Date not getting Stored in the Db
     */

    @Id
    public String title;
    public String authors;
    public int pages;
    public String channel;

    public Paper(String title,String authors, int pages, String channel) {
        this.title = title;
        this.authors =authors;
        this.pages = pages;
        this.channel = channel;

    }

    public static Finder<Integer, Paper> find = new Finder<Integer, Paper>(Integer.class, Paper.class);
}



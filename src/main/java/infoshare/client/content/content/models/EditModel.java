package infoshare.client.content.content.models;

import javax.validation.constraints.NotNull;
import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hashcode on 2015/06/24.
 */
public class EditModel implements Serializable {
    private String id;
    @NotNull
    private Date dateCreated;
    @NotNull
    private String creator;
    @NotNull
    private String source;
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private String contentType;

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public Date getDateCreated(){
        return dateCreated;
    }
    public void setDateCreated(Date dateCreated){
        this.dateCreated = dateCreated;
    }

    public String getCreator(){
        return creator;
    }
    public void setCreator(String creator){
        this.creator = creator;
    }

    public String getSource(){
        return source;
    }
    public void setSource(String source){
        this.source = source;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }

    public String getContentType(){
        return contentType;
    }
    public void setContentType(String contentType){
        this.contentType = contentType;
    }
}

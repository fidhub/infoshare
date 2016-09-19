package infoshare.viewUI.container.content.models;

import java.io.Serializable;
import java.util.Date;

public class ContentModel implements Serializable {

   // @NotNull
    private Date dateCreated;
   // @NotNull
    private String creator;
   // @NotNull
    private String source;
   // @NotNull
    private String category;
    //@NotNull
    private String title;
   // @NotNull
    private String content;
   // @NotNull
    private String contentType;
   // @NotNull
    private String status;
   // @NotNull
    private String state;
    //@NotNull
    private String org;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getOrg() {
        return org;
    }

}
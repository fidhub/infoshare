package infoshare.client.content.organisation.model;

import java.util.Date;
import java.util.Map;

/**
 * Created by THULEBONA on 2016/03/13.
 */
public class AddOrganisationModel {
    private String id;
    private String name;
    private Map<String, String> details;
    private String adminattached;
    private Date date;
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }

    public String getAdminattached() {
        return adminattached;
    }

    public void setAdminattached(String adminattached) {
        this.adminattached = adminattached;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

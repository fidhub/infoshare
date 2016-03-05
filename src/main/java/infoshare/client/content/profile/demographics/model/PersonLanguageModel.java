package infoshare.client.content.profile.demographics.model;

import java.io.Serializable;

/**
 * Created by hashcode on 2015/12/20.
 */
public class PersonLanguageModel implements Serializable {
    private String languageId;
    private String reading;
    private String writing;
    private String speaking;

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public String getWriting() {
        return writing;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }

    public String getSpeaking() {
        return speaking;
    }

    public void setSpeaking(String speaking) {
        this.speaking = speaking;
    }
}

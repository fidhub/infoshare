package infoshare.domain.storage;

import java.io.Serializable;

/**
 * Created by joindomain on 2/29/2016.
 */
public class FileMeta implements Serializable, Comparable<FileMeta>{

    private String fileName;
    private String contentType;

    private FileMeta(){}

    public FileMeta(Builder builder) {
        this.fileName = builder.fileName;
        this.contentType = builder.contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public static class Builder{
        private String fileName;
        private String contentType;

        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder copy(FileMeta fileMeta){
            this.fileName = fileMeta.fileName;
            this.contentType = fileMeta.contentType;
            return this;
        }
        public FileMeta build(){return new FileMeta(this);}
    }

    public static Builder builder(){
        return new Builder();
    }
    @Override
    public int compareTo(FileMeta o) {
        return fileName.compareTo(o.fileName);
    }
}

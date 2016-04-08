package infoshare.client.content.account.forms;

import com.google.common.io.Files;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by user9 on 2016/04/08.
 */

public class ImageUploader extends VerticalLayout implements Upload.Receiver, Upload.SucceededListener {
    public final Embedded image = new Embedded();

    public File file;
    public String path="";
    public Upload upload;
    public Panel panel;

    public ImageUploader() {
        image.setVisible(false);
        upload = new Upload(null, this);
        upload.setButtonCaption("Select File");
        upload.setImmediate(true);
        upload.addStyleName(ValoTheme.BUTTON_PRIMARY);
        upload.addStyleName(ValoTheme.BUTTON_TINY);
        upload.addSucceededListener(this);
        panel = new Panel("Organization Logo");
        VerticalLayout panelContent = new VerticalLayout();
        panelContent.setSpacing(true);
        panelContent.addComponents(upload);
        panelContent.addComponents(image);
        panel.setContent(panelContent);
        addComponent(panel);
    }


    public OutputStream receiveUpload(String filename,String mimeType) {   // Create upload stream
        FileOutputStream fos = null; // Stream to write to
        try {
            file =  new File(Files.createTempDir(), filename);
            fos = new FileOutputStream(file);
        } catch (final java.io.FileNotFoundException e) {
            new Notification("Could not open file<br/>",
                    e.getMessage(),
                    Notification.Type.ERROR_MESSAGE)
                    .show(Page.getCurrent());
            return null;
        }
        return fos; // Return the output stream to write to
    }
    public  void uploadSucceeded(Upload.SucceededEvent event) {
        image.setVisible(true);
        image.setHeight(200.0f, Unit.PIXELS);
        image.setWidth(100.0f, Unit.PERCENTAGE);
        image.setSource(new FileResource(file));
        path = file.getAbsoluteFile().toString();
    }


}
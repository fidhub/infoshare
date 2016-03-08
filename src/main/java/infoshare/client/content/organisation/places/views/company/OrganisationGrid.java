package infoshare.client.content.organisation.places.views.company;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.MultiFileUpload;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.UploadFinishedHandler;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.UploadStateWindow;
import infoshare.client.content.MainLayout;

import java.io.InputStream;


/**
 * Created by hashcode on 2015/12/29.
 */
public class OrganisationGrid extends GridLayout {
    private final MainLayout main;
    private UploadFinishedHandler imageHandler;
    private UploadStateWindow imageWindow;
    private MultiFileUpload imageUpload;
    private UploadFinishedHandler fileHandler;
    private UploadStateWindow fileWindow;

    private Button backtoMainPage;

    public OrganisationGrid(MainLayout main) {
        this.main = main;
        setColumns(3);
        setRows(10);
        setSizeFull();
//        StreamResource.StreamSource imageSource = new ProfileImageUtil(person);
//        StreamResource resource = new StreamResource(imageSource, getProfileImage(person.getEmployeeDetails()));

//        final Image image = new Image(null, resource);
//        image.setHeight("150");
//        image.setWidth("150");
//        addComponent(image, 0, 0, 0, 2);
        imageUpload();

        addComponent(imageUpload, 0, 0);
    }


    private void imageUpload() {
        imageHandler = new UploadFinishedHandler() {
            @Override
            public void handleFile(InputStream in, String fileName, String mimeType, long length) {
                System.out.println(" The file Stream " + in);
                System.out.println(" The file Mine Type " + mimeType);
                System.out.println(" The file Length  " + length);


            }
        };

        imageWindow = new UploadStateWindow();
        imageUpload = new MultiFileUpload(imageHandler, imageWindow, false);
        imageUpload.setPanelCaption("Single File Upload");
        imageUpload.getSmartUpload().setUploadButtonCaptions("Upload  Employee Picture", "");
        imageUpload.setStyleName("default");
        imageUpload.setSizeFull();
    }
}

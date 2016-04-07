package infoshare.client.content.content.forms;

import com.google.common.io.Files;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import infoshare.app.conf.RestUtil;
import infoshare.domain.storage.FileResults;
import infoshare.restapi.storage.UploadBaseURL;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;

/**
 * Created by THULEH on 2016/03/24.
 */
public class ImmediateUploadExample extends VerticalLayout  implements Upload.Receiver, Upload.SucceededListener {

    public Upload upload;
    private File file;
    public ImmediateUploadExample() {
        upload = new Upload("Upload",this);
        upload.addSucceededListener(this);
    }

    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        try {
            file = new File(Files.createTempDir(), filename);
            //file.deleteOnExit();
            return new FileOutputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent event) {
        try {
          Set<FileResults> set=  RestUtil.getFileResults(UploadBaseURL.Media.POST, file.getAbsoluteFile().toString(), FileResults.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.print(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath());
    }
}
/*public class UploadPicture extends Component {


    public UploadPicture() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
    }
}*/

/*
@SuppressWarnings("serial")
 public class ImmediateUploadExample extends VerticalLayout  implements Upload.Receiver{

    private Label status ;

    private ProgressBar bar = new ProgressBar(0.0f);
    private Upload upload ;

    public ImmediateUploadExample() {
        setSpacing(true);
        upload = new Upload(null, this);
        System.out.print(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath());
        // Make uploading start immediately when file is selected
        upload.setImmediate(true);
        upload.setButtonCaption("Select file");
        upload.addStyleName(ValoTheme.BUTTON_SMALL);
        upload.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        upload.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        upload.setIcon(FontAwesome.UPLOAD);

        final Button cancelProcessing = new Button("Cancel");
        cancelProcessing.addStyleName(ValoTheme.BUTTON_SMALL);
        cancelProcessing.addStyleName(ValoTheme.BUTTON_DANGER);
        cancelProcessing.addClickListener(event -> upload.interruptUpload());

        status = new Label("Please select a file to upload");
        addComponent(status);
        addComponent(upload);
        bar.setVisible(false);
        cancelProcessing.setVisible(false);
        addComponent(bar);
        addComponent(cancelProcessing);

        */
/**
         * =========== Add needed listener for the upload component: start,
         * progress, finish, success, fail ===========
         *//*


        upload.addStartedListener(event -> {

            upload.setVisible(false);
            cancelProcessing.setVisible(true);
            bar.setVisible(true);
            bar.setValue(1.0f);
            status.setValue("Uploading file " + event.getFilename());
            });

            upload.addProgressListener((readBytes, contentLength) -> bar.setValue(new Float(readBytes / (float) contentLength)));

            upload.addSucceededListener(event -> {
                try {
                    File file = new File(event.getFilename());
                    event.getUpload();
                    VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
                    System.out.print(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath());
                   // RestUtil.getFileResults(UploadBaseURL.Media.POST, file.getPath().toString(), FileResults.class);
                    status.setValue("Uploading file " + event.getFilename() + " succeeded");
                }catch(Exception e){
                    e.printStackTrace();
                }

            });

            upload.addFailedListener(event -> status.setValue("Uploading interrupted"));

            upload.addFinishedListener((Upload.FinishedEvent event) -> {
                cancelProcessing.setVisible(false);
                bar.setVisible(false);
                upload.setVisible(true);
                upload.addStyleName(ValoTheme.BUTTON_SMALL);
                upload.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
                upload.addStyleName(ValoTheme.BUTTON_FRIENDLY);
                upload.setIcon(FontAwesome.UPLOAD);
            });

    }

    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {

        return new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                try {
                    //Thread.sleep(5);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
    }
*/


   /* public static class MyReceiver implements Upload.Receiver{

        private String fileName;
        private String mtype;
        private String path;
        private boolean sleep;
        private int total = 0;

        public OutputStream receiveUpload(String filename, String mimetype) {
            fileName = filename;
            mtype = mimetype;
            return new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    total++;
                    if (sleep && total % 10000 == 0) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            };
        }

        public String getFileName() {
            return fileName;
        }

        public String getMimeType() {
            return mtype;
        }

        public void setSlow(boolean value) {
            sleep = value;
        }

    }*/





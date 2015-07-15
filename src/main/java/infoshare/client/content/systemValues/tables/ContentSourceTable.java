package infoshare.client.content.systemValues.tables;

import com.vaadin.ui.Table;
import infoshare.client.content.MainLayout;
import infoshare.domain.Source;
import infoshare.services.source.SourceService;
import infoshare.services.source.sourceServiceImpl.SourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by codex on 2015/06/26.
 */
public class ContentSourceTable extends Table{

    @Autowired
    private SourceService sourceService = new SourceServiceImpl();

    private MainLayout main;

    public ContentSourceTable(MainLayout mainApp) {
        this.main = mainApp;
        setSizeFull();
        addContainerProperty("Name",String.class,null);
        addContainerProperty("Description",String.class,null);
        List<Source> sources = sourceService.findAll();

        for(Source source: sources){
            addItem(new Object[]{
                    source.getName(),
                    source.getDescription()
            },source.getId());
        }

        setSelectable(true);
        setImmediate(true);
        setNullSelectionAllowed(false);
    }
}

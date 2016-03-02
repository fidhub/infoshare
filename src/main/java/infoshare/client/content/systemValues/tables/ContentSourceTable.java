package infoshare.client.content.systemValues.tables;

import com.vaadin.server.Responsive;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.SourceFacade;
import infoshare.client.content.MainLayout;
import infoshare.domain.content.Source;
import infoshare.services.source.SourceService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by codex on 2015/06/26.
 */
public  class ContentSourceTable extends Table{

    @Autowired
    private SourceService sourceService = SourceFacade.sourceService;

    private MainLayout main;

    public ContentSourceTable(MainLayout mainApp) {
        this.main = mainApp;
        setSizeFull();
        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Name",String.class,null);
        addContainerProperty("Description",String.class,null);
        for(Source source: sourceService.findAll()){
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

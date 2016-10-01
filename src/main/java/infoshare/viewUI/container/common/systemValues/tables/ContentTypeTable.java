package infoshare.viewUI.container.common.systemValues.tables;

import com.vaadin.server.Responsive;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.ContentTypeFacade;
import infoshare.viewUI.container.MainLayout;
import infoshare.domain.content.ContentType;
import infoshare.services.ContentFiles.ContentType.ContentTypeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by codex on 2015/06/25.
 */
public class ContentTypeTable extends Table {

    @Autowired
    private ContentTypeService contentTypeService = ContentTypeFacade.contentTypeService;

    private MainLayout main;

    public ContentTypeTable(MainLayout mainApp) {
        this.main = mainApp;
        setSizeFull();
        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Name",String.class,null);
        addContainerProperty("Description",String.class,null);

        for(ContentType contentType: contentTypeService.findAll()){
            addItem(new Object[]{
                    contentType.getName(),
                    contentType.getDescription()
            },contentType.getId());
        }

        setSelectable(true);
        setImmediate(true);
        setNullSelectionAllowed(false);
    }
}

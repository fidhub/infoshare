package infoshare.client.content.content.tables;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.CategoryFacade;
import infoshare.app.facade.ContentFacade;
import infoshare.app.util.DomainState;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.client.content.MainLayout;
import infoshare.client.content.content.ContentMenu;
import infoshare.client.header.Header;
import infoshare.domain.content.EditedContent;
import infoshare.domain.content.PublishedContent;
import infoshare.domain.content.RawContent;
import infoshare.restapi.ContentFiles.content.EditedContentAPI;
import infoshare.restapi.ContentFiles.content.PublishedContentAPI;
import infoshare.restapi.ContentFiles.content.RawContentAPI;

import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by codet on 2016/03/18.
 */
public class DisabledContentTable extends Table {

    private final MainLayout main;

    public DisabledContentTable(MainLayout main) {
        this.main = main;
        setSizeFull();
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Title",String.class,null);
        addContainerProperty("Category",String.class,null);
        addContainerProperty("Creator",String.class,null);
        addContainerProperty("Status",String.class,null);
        addContainerProperty("Date Created",Date.class,null);
        addContainerProperty("Enabled", Button.class, null);

         ContentFacade.rawContentService.findAll(OrganisationUtil.getCompanyCode())
                 .stream()
                 .filter(cont -> cont!= null)
                 .collect(Collectors.toList())
                 .stream()
                 .filter(cont->!cont.getState().equalsIgnoreCase("active"))
                 .collect(Collectors.toList()).forEach(this::loadRawTable);
         ContentFacade.editedContentService.findAll(OrganisationUtil.getCompanyCode())
                 .stream()
                 .filter(cont -> cont!= null)
                 .collect(Collectors.toList())
                 .stream()
                 .filter(cont->!cont.getState().equalsIgnoreCase("active"))
                 .collect(Collectors.toList()).forEach(this::loadEditedTable);
         ContentFacade.publishedContentService.findAll(OrganisationUtil.getCompanyCode())
                 .stream()
                 .filter(cont -> cont!= null)
                 .collect(Collectors.toList())
                 .stream()
                 .filter(cont->!cont.getState().equalsIgnoreCase("active"))
                 .collect(Collectors.toList()).forEach(this::loadPublishedTable);

        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
        setPageLength(10);

    }

    public void loadRawTable(RawContent item ) {
        Button enable = new Button("Enable");
        enable.setStyleName(ValoTheme.BUTTON_LINK);
        enable.setData(item.getId());
        enable.addClickListener(event -> {
            RawContent raw = new RawContent.Builder()
                    .copy(item)
                    .state(DomainState.ACTIVE.name()).build();
            RawContentAPI.save(raw);
            Header.refreshNotification();
            getHome();
        });
        String cat;
        if (item.getCategory().equalsIgnoreCase("uncategorized"))
            cat = "uncategorized";
        else
            cat = CategoryFacade.categoryService.findById(item.getCategory()).getName();
        addItem(new Object[]{
                item.getTitle(),
                cat,
                item.getCreator(),
                item.getStatus(),
                item.getDateCreated(),
                enable
        }, item.getId());
    }
    public void loadEditedTable(EditedContent item ) {
        Button enable = new Button("Enable");

        enable.setStyleName(ValoTheme.BUTTON_LINK);
        enable.setData(item.getId());
        enable.addClickListener(event -> {
            EditedContent raw = new EditedContent.Builder()
                    .copy(item)
                    .state(DomainState.ACTIVE.name()).build();
            EditedContentAPI.save(raw);
            Header.refreshNotification();
            getHome();
        });
        String cat;
        if (item.getCategory().equalsIgnoreCase("uncategorized"))
            cat = "uncategorized";
        else
            cat = CategoryFacade.categoryService.findById(item.getCategory()).getName();
        addItem(new Object[]{
                item.getTitle(),
                cat,
                item.getCreator(),
                item.getStatus(),
                item.getDateCreated(),
                enable
        }, item.getId());
    }
    public void loadPublishedTable(PublishedContent item )
    {
        Button enable = new Button("Enable");
        enable.setStyleName(ValoTheme.BUTTON_LINK);
            enable.setData(item.getId());
            enable.addClickListener(event -> {
                PublishedContent raw = new PublishedContent.Builder()
                        .copy(item)
                        .state(DomainState.ACTIVE.name()).build();
                PublishedContentAPI.save(raw);
                Header.refreshNotification();
                getHome();
            });
        String cat;
        if (item.getCategory().equalsIgnoreCase("uncategorized"))
            cat = "uncategorized";
        else
            cat = CategoryFacade.categoryService.findById(item.getCategory()).getName();
            addItem(new Object[]{
                    item.getTitle(),
                     cat,
                    item.getCreator(),
                    item.getStatus(),
                    item.getDateCreated(),
                    enable
            }, item.getId());

    }
    private void getHome(){
        main.content.setSecondComponent(new ContentMenu(main, "Deleted"));
    }
}

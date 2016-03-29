package infoshare.client.content.content.tables;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.ContactFacade;
import infoshare.app.facade.ContentFacade;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.DomainState;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.app.util.security.RolesValues;
import infoshare.client.content.MainLayout;
import infoshare.client.content.content.ContentMenu;
import infoshare.client.content.content.models.ContentModel;
import infoshare.domain.content.EditedContent;
import infoshare.domain.content.RawContent;
import infoshare.domain.person.Person;
import infoshare.restapi.ContentFiles.content.RawContentAPI;
import org.apache.poi.ss.formula.functions.T;

import java.util.Date;
import java.util.Set;
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
                 .collect(Collectors.toList()).forEach(this::loadTable);



        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
    }

    public void loadTable(RawContent item ){

            Button enable = new Button("Enable");
            enable.setStyleName(ValoTheme.BUTTON_LINK);
            enable.setData(item.getId());
            enable.setImmediate(true);
            enable.addClickListener(event -> {
                RawContent raw = new RawContent.Builder().copy(item)
                        .state(DomainState.ACTIVE.name()).build();
               this.main.content.setSecondComponent(new ContentMenu(main, "Deleted"));
                RawContentAPI.save(raw);
            });

            addItem(new Object[]{
                    item.getTitle(),
                    item.getCategory(),
                    item.getCreator(),
                    item.getStatus(),
                    item.getDateCreated(),
                    enable
            }, item.getId());



    }
}

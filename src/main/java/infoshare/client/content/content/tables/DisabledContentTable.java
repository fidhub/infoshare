package infoshare.client.content.content.tables;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.ContactFacade;
import infoshare.app.facade.ContentFacade;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.app.util.security.RolesValues;
import infoshare.client.content.MainLayout;
import infoshare.domain.content.EditedContent;
import infoshare.domain.person.Person;

import java.util.Set;

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
        addContainerProperty("Date Created",String.class,null);
        addContainerProperty("Enabled", Boolean.class, null);

        Set<EditedContent> applicants = ContentFacade.editedContentService.findAll(OrganisationUtil.getCompanyCode());

        applicants.parallelStream().forEach(item -> {

            Button enable = new Button("Enable");
            enable.setStyleName(ValoTheme.BUTTON_LINK);
            enable.setData(item.getId());
            enable.addClickListener(event -> {

            });

            addItem(new Object[]{
                    item.getTitle(),
                    item.getCategory(),
                    item.getCreator(),
                    item.getDateCreated(),
                    enable
            }, item.getId());

        });

        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);

    }
}

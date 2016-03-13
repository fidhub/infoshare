package infoshare.client.content.organisation.table;

import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.OrganisationFacade;
import infoshare.client.content.MainLayout;
import infoshare.domain.organisation.Organisation;

import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by THULEBONA on 2016/03/13.
 */
public class AddOrganisationAdminTable extends Table {
    private MainLayout main;
    public AddOrganisationAdminTable(MainLayout main) {
        this.main = main;
        setSizeFull();
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Phone number", String.class, null);
        addContainerProperty("Email PersonAddress", String.class, null);
        addContainerProperty("contact Type",String.class,null);
        setResponsive(true);
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);

        OrganisationFacade.companyService.findAll().stream()
                .filter(org -> org.getAdminattached().equalsIgnoreCase("ROLE_ORG_ADMIN"))
                .collect(Collectors.toList()).forEach(this::loadTable);

    }

    public void loadTable(Organisation organisation){
        try{
            addItem(new Object[]{
                        organisation.getName(),
                        organisation.getAdminattached(),
                        organisation.getDate(),
                        organisation.getState()
            },organisation.getId());
        }catch (Exception e){}
    }
}

package infoshare.client.content.organisation.table;

import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.OrganisationFacade;
import infoshare.app.util.DomainState;
import infoshare.client.content.MainLayout;
import infoshare.domain.organisation.Organisation;

import java.util.stream.Collectors;

/**
 * Created by user9 on 2016/03/12.
 */
public class OrganizationDisabledTable extends Table {
    private final MainLayout main;
    public OrganizationDisabledTable(MainLayout mainApp) {
        this.main = mainApp;
        addContainerProperty("Name", String.class, null);
        addContainerProperty("Date", String.class, null);
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        setSizeFull();
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
        this.setResponsive(true);
        OrganisationFacade.companyService.findAll()
                .stream()
                .filter(org -> org.getState().equalsIgnoreCase(DomainState.RETIRED.name()))
                .collect(Collectors.toList())
                .forEach(this::loadTable);

    }

    public void loadTable(Organisation organisation){
        try {
            addItem(new Object[]{
                    organisation.getName(),
                    organisation.getDate(),

            }, organisation.getId());
        } catch (Exception r) {
        }

    }
}

package infoshare.client.content.profile.demographics.table;

import com.vaadin.ui.Table;
import hashwork.app.facade.DemographicsFacade;
import hashwork.app.facade.PeopleFacade;
import hashwork.app.util.security.GetUserCredentials;
import hashwork.client.content.MainLayout;
import hashwork.domain.people.PersonIdentity;
import hashwork.domain.ui.demographics.IdentificationType;

import java.util.Date;
import java.util.Set;

/**
 * Created by hashcode on 2015/12/20.
 */
public class PersonIdentitiesTable extends Table {
    private final MainLayout main;

    public PersonIdentitiesTable(MainLayout main) {
        this.main = main;
        setSizeFull();

        final String personId = GetUserCredentials.getUser().getId();

        addContainerProperty("Date Created", Date.class, null);
        addContainerProperty("ID Type", String.class, null);
        addContainerProperty("ID Value", String.class, null);
        addContainerProperty("Active", Boolean.class, null);

        Set<PersonIdentity> personIdentities = PeopleFacade.personIdentityService.findAll(personId);


        personIdentities.parallelStream().forEach(item -> {
            addItem(new Object[]{
                    item.getDate(),
                    getIdType(item.getIdType()),
                    item.getIdValue(),
                    item.isPreffered(),
            }, item.getId());

        });
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);

    }

    private String getIdType(String idType) {
        IdentificationType identificationType = DemographicsFacade.identificationTypeService.findById(idType);
        if (identificationType != null)
            return identificationType.getName();
        return "Type Not Set";
    }


}

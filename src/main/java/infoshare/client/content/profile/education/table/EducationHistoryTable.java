package infoshare.client.content.profile.education.table;

import com.vaadin.ui.Table;
import hashwork.app.facade.EducationFacade;
import hashwork.app.facade.PeopleFacade;
import hashwork.app.util.security.GetUserCredentials;
import hashwork.client.content.MainLayout;
import hashwork.domain.people.PersonEducationHistory;
import hashwork.domain.ui.education.Degree;
import hashwork.domain.ui.education.EducationType;

import java.util.Date;
import java.util.Set;

/**
 * Created by hashcode on 2015/12/20.
 */
public class EducationHistoryTable extends Table {
    private final MainLayout main;

    public EducationHistoryTable(MainLayout main) {
        this.main = main;
        setSizeFull();

        final String personId = GetUserCredentials.getUser().getId();

        addContainerProperty("Date Created", Date.class, null);
        addContainerProperty("Institution Name", String.class, null);
        addContainerProperty("Institution Location", String.class, null);
        addContainerProperty("Education Type", String.class, null);
        addContainerProperty("Degree", String.class, null);
        addContainerProperty("Year Graduated", Integer.class, null);
        addContainerProperty("Notes", String.class, null);


        Set<PersonEducationHistory> personEducationHistories = PeopleFacade.personEducationHistoryService.findAll(personId);

        personEducationHistories.parallelStream().forEach(item -> {
            addItem(new Object[]{
                    item.getDate(),
                    item.getInstitutionName(),
                    item.getInstitutionLocation(),
                    educationType(item.getEducationTypeId()),
                    getDegree(item.getDegreeId()),
                    item.getYearOfGraduation(),
                    item.getNotes()
            }, item.getId());

        });
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);

    }

    private String educationType(String statusId) {
        EducationType educationType = EducationFacade.educationTypeService.findById(statusId);
        if (educationType != null)
            return educationType.getName();
        return "Type Not Set";

    }

    private String getDegree(String degreeId) {
        Degree degree = EducationFacade.degreeService.findById(degreeId);
        if (degree != null)
            return degree.getDegreeName();
        return "Type Not Set";
    }

}

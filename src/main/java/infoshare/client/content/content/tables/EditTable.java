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
import infoshare.domain.content.RawContent;
import infoshare.restapi.ContentFiles.content.EditedContentAPI;
import infoshare.restapi.ContentFiles.content.RawContentAPI;
import infoshare.services.ContentFiles.category.CategoryService;
import infoshare.services.ContentFiles.content.EditedContentService;

import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

/**
 * Created by hashcode on 2015/06/24.
 */
public class EditTable extends Table{

    @Autowired
    private EditedContentService editedContentService = ContentFacade.editedContentService;
    private CategoryService categoryService = CategoryFacade.categoryService;
    private final MainLayout main;
    private Button delete = new Button("Delete");

    public EditTable(MainLayout mainApp) {
        this.main = mainApp;

        setSizeFull();
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Title", String.class, null);
        addContainerProperty("Category", String.class, null);
        addContainerProperty("Creator", String.class, null);
        addContainerProperty("Date Created", String.class, null);

        try {
            editedContentService.findAll(OrganisationUtil.getCompanyCode()) //TODO
                    .stream()
                    .filter(content -> content != null)
                    .collect(Collectors.toList())
                    .stream()
                    .filter(cont -> cont.getState().equalsIgnoreCase("Active"))
                    .collect(Collectors.toList())
                    .stream()
                    .filter(cont -> cont.getStatus().equalsIgnoreCase("Edited"))
                    .collect(Collectors.toList())
                    .forEach(this::loadTable);
        } catch (Exception e) {
        }
        setNullSelectionAllowed(false);
        setSelectable(true);
        setImmediate(true);
    }
    public void loadTable(EditedContent editedContent) {
        DateFormat formatter = new SimpleDateFormat("dd MMMMMMM yyyy");
        delete.setData(editedContent.getId());
        delete.setImmediate(true);
        delete.addClickListener(event -> {
            EditedContent raw = new EditedContent.Builder()
                    .copy(editedContent)
                    .state(DomainState.RETIRED.name())
                    .build();
            EditedContentAPI.save(raw);
            Header.refreshNotification();
            getHome();
        });
        try {
            addItem(new Object[]{
                    editedContent.getTitle(),
                    categoryService.findById(editedContent.getCategory()).getName(),
                    editedContent.getCreator(),
                    formatter.format(editedContent.getDateCreated()),
                    delete
            }, editedContent.getId());
        } catch (Exception r) {
        }
    }

    public void getHome() {
        main.content.setSecondComponent(new ContentMenu(main, "EDITOR"));
    }
}

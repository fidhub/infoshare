package infoshare.viewUI.container.content.tables;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.app.facade.ContentFacade;
import infoshare.app.util.DomainState;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.viewUI.container.MainLayout;
import infoshare.viewUI.container.content.ContentMenu;
import infoshare.viewUI.header.Header;
import infoshare.domain.content.RawContent;
import infoshare.restapi.ContentFiles.content.RawContentAPI;
import infoshare.services.ContentFiles.content.RawContentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

/**
 * Created by hashcode on 2015/06/24.
 */
public class RawTable extends Table {

    @Autowired
    private RawContentService rawContentService = ContentFacade.rawContentService;
    private final MainLayout main;

    public RawTable(MainLayout mainApp){

        this.main = mainApp;
        setSizeFull();
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Title",String.class,null);
        addContainerProperty("Category",String.class,null);
        addContainerProperty("Creator",String.class,null);
        addContainerProperty("Date Created",String.class,null);
        addContainerProperty("Delete",Button.class,null);

        try {
            rawContentService.findAll(OrganisationUtil.getCompanyCode()) //TODO
                    .stream()
                    .filter(cont -> cont!= null)
                    .collect(Collectors.toList())
                    .stream()
                    .filter(cont->cont.getStatus().equalsIgnoreCase("raw"))
                    .collect(Collectors.toList())
                     .stream()
                    .filter(cont->cont.getState().equalsIgnoreCase("active"))
                    .collect(Collectors.toList())
                    .forEach(this::loadTable);
        }catch (Exception e){
        }

         setNullSelectionAllowed(false);
         setSelectable(true);
         setImmediate(true);
         setPageLength(10);
    }

    public void loadTable(RawContent rawContent) {
        DateFormat formatter = new SimpleDateFormat("dd MMMMMMM yyyy");
        Button delete = new Button("Delete");
        delete.setStyleName(ValoTheme.BUTTON_LINK);
        delete.setData(rawContent.getId());
        delete.setImmediate(true);
        delete.addClickListener(event -> {
            RawContent raw = new RawContent.Builder()
                    .copy(rawContent)
                    .state(DomainState.RETIRED.name())
                    .build();
            RawContentAPI.save(raw);
            Header.refreshNotification();
            getHome();
        });
        try {
            addItem(new Object[]{
                    rawContent.getTitle(),
                    rawContent.getCategory(),
                    rawContent.getCreator(),
                    formatter.format(rawContent.getDateCreated()),
                    delete
            }, rawContent.getId());
        } catch (Exception r) {
        }
    }

    private void getHome(){
        main.content.setSecondComponent(new ContentMenu(main, "LANDING"));
    }

}



package infoshare.filterSearch;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.domain.person.Person;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by user9 on 2016/02/11.
 */
public class UserFilter {
    public  TextField field = new TextField();

    public UserFilter() {
        getField();
    }
    private TextField getField(){
        field.setInputPrompt("Use User email or FirstName ...");
        field.setWidth("260px");
        field.setIcon(FontAwesome.FILTER);
        field.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        field.addStyleName(ValoTheme.TEXTFIELD_SMALL);
        return field;
    }

}

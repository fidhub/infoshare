package infoshare.client.admin;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

/**
 * Created by hashcode on 2015/04/15.
 */
public class DashboardNavigator extends Navigator {
    // Provide a Google Analytics tracker id here
    private static final String TRACKER_ID = null;// "UA-658457-6";



    private ViewProvider errorViewProvider;

    public DashboardNavigator(final ComponentContainer container) {
        super(UI.getCurrent(), container);

        String host = getUI().getPage().getLocation().getHost();

    }



}

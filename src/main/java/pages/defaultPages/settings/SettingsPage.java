package pages.defaultPages.settings;

import exceptions.PageTypeException;
import pages.Page;
import webApplication.ApplicationRoute;

public class SettingsPage extends Page {
    public SettingsPage(String href) {
        super(href);
    }

    public CompanyPage moveToCompanySettings() throws PageTypeException {

        return ApplicationRoute.getCompanyPage();
    }

    public ModulesPage moveToModulesSettings() throws PageTypeException {

        return ApplicationRoute.getModulesPage();
    }


}

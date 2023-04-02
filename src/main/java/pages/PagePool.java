package pages;

import config.Hrefs;
import exceptions.PageTypeException;
import pages.defaultPages.AdminToolsPage;
import pages.defaultPages.InformationPanelPage;
import pages.defaultPages.LoginPage;
import pages.defaultPages.UsersAndGroupsPage;
import pages.defaultPages.settings.CompanyPage;
import pages.defaultPages.settings.ModulesPage;
import pages.defaultPages.settings.SettingsPage;
import pages.extraPages.HRPage;
import pages.extraPages.ProductsPage;
import pages.extraPages.ThirdPartiesPage;

import java.util.HashMap;

public class PagePool {

    private static HashMap<PageType, Page> pages = new HashMap<>();

    public static Page getPage(PageType type) throws PageTypeException {
        Page pageImpl = pages.get(type);

        if(pageImpl == null){
            if(type.equals(PageType.LOGIN_PAGE)){
                pageImpl =  new LoginPage(Hrefs.LOGIN_PAGE_URL);
            }
            else if(type.equals(PageType.HR_PAGE)){
                pageImpl = new HRPage(Hrefs.HR_PAGE_URL);
            }
            else if(type.equals(PageType.ADMIN_TOOLS_PAGE)){
                pageImpl = new AdminToolsPage(Hrefs.ADMINTOOLS_PAGE_URL);
            }
            else if(type.equals(PageType.PRODUCT_PAGE)){
                pageImpl = new ProductsPage(Hrefs.PRODUCTS_PAGE_URL);
            }
            else if(type.equals(PageType.SETTINGS_PAGE)){
                pageImpl = new SettingsPage(Hrefs.SETTINGS_PAGE_URL);
            }
            else if(type.equals(PageType.THIRD_PARTIES_PAGE)){
                pageImpl = new ThirdPartiesPage(Hrefs.THIRDPARTIES_PAGE_URL);
            }
            else if(type.equals(PageType.USER_AND_GROUPS_PAGE)){
                pageImpl = new UsersAndGroupsPage(Hrefs.USERS_AND_GROUPS_PAGE_URL);
            }
            else if(type.equals(PageType.INFORMATION_PANEL_PAGE)){
                pageImpl = new InformationPanelPage(Hrefs.INFORMATIONPANEL_PAGE_URL);
            }
            else if(type.equals(PageType.COMPANY_PAGE)){
                pageImpl = new CompanyPage(Hrefs.COMPANY_PAGE_URL);
            }
            else if(type.equals(PageType.MODULES_PAGE)){
                pageImpl = new ModulesPage(Hrefs.MODULES_PAGE_URL);
            }

            if(pageImpl != null){
                pages.put(type, pageImpl);
            }
            else{
                throw new PageTypeException("The required page type is  not supported.");
            }
        }
        else{
            return pageImpl;
        }
        return pageImpl;

    }

}

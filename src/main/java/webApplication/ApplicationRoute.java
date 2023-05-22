package webApplication;
import exceptions.PageTypeException;
import pages.PagePool;
import pages.PageType;
import pages.defaultPages.*;
import pages.defaultPages.CompanyPage;
import pages.defaultPages.settings.ModulesPage;
import pages.defaultPages.settings.SettingsPage;

public class ApplicationRoute {

     public static AdminToolsPage getAndOpenAdminToolsPage() throws PageTypeException {
          AdminToolsPage adminToolsPage = (AdminToolsPage) PagePool.getPage(PageType.ADMIN_TOOLS_PAGE);
          adminToolsPage.open();
          return adminToolsPage;

     }
     public static ProductPage getAndOpenProductPage() throws PageTypeException {
          ProductPage productPage = (ProductPage) PagePool.getPage(PageType.PRODUCT_PAGE);
          productPage.open();
          return productPage;

     }
     public static  InformationPanelPage getAndOpenInformationPanelPage() throws PageTypeException {
          InformationPanelPage informationPanelPage = (InformationPanelPage) PagePool.getPage(PageType.INFORMATION_PANEL_PAGE);
          informationPanelPage.open();
          return informationPanelPage;
     }
     
     public static  LoginPage getAndOpenLoginPage() throws PageTypeException {
          LoginPage loginPage = (LoginPage) PagePool.getPage(PageType.LOGIN_PAGE);
          loginPage.open();
          return loginPage;
     }

     public static  UsersAndGroupsPage getAndOpenUsersAndGroupsPage() throws PageTypeException {
          UsersAndGroupsPage usersAndGroupsPage = (UsersAndGroupsPage) PagePool.getPage(PageType.USER_AND_GROUPS_PAGE);
          usersAndGroupsPage.open();
          return usersAndGroupsPage;
     }
     
     public static  CompanyPage getAndOpenCompanyPage() throws PageTypeException {
          CompanyPage companyPage = (CompanyPage) PagePool.getPage(PageType.COMPANY_PAGE);
          companyPage.open();
          return companyPage;
     }

     public static  ModulesPage getAndOpenModulesPage() throws PageTypeException {
          ModulesPage modulesPage = (ModulesPage) PagePool.getPage(PageType.MODULES_PAGE);
          modulesPage.open();
          return modulesPage;
     }

     public static  SettingsPage getAndOpenSettingsPage() throws PageTypeException {
          SettingsPage settingsPage = (SettingsPage) PagePool.getPage(PageType. SETTINGS_PAGE);
          settingsPage.open();
          return settingsPage;
     }

     public static AdminToolsPage getAdminToolsPage() throws PageTypeException {
          return (AdminToolsPage) PagePool.getPage(PageType.ADMIN_TOOLS_PAGE);
     }

     public static  ThirdPartiesPage getAndOpenThirdPartiesPage() throws PageTypeException {
          ThirdPartiesPage thirdPartiesPage = (ThirdPartiesPage) PagePool.getPage(PageType.THIRD_PARTIES_PAGE);
          thirdPartiesPage.open();
          return thirdPartiesPage;
     }
     public static  ThirdPartiesPage getThirdPartiesPage() throws PageTypeException {
          return (ThirdPartiesPage) PagePool.getPage(PageType.THIRD_PARTIES_PAGE);
     }

     public static  InformationPanelPage getInformationPanelPage() throws PageTypeException {
          return (InformationPanelPage) PagePool.getPage(PageType.INFORMATION_PANEL_PAGE);
     }

     public static  LoginPage getLoginPage() throws PageTypeException {
          return (LoginPage) PagePool.getPage(PageType.LOGIN_PAGE);
     }

     public static  UsersAndGroupsPage getUsersAndGroupsPage() throws PageTypeException {
          return (UsersAndGroupsPage) PagePool.getPage(PageType.USER_AND_GROUPS_PAGE);
     }

     public static  CompanyPage getCompanyPage() throws PageTypeException {
          return (CompanyPage) PagePool.getPage(PageType.COMPANY_PAGE);
     }

     public static  ModulesPage getModulesPage() throws PageTypeException {
          return (ModulesPage) PagePool.getPage(PageType.MODULES_PAGE);
     }

     public static  SettingsPage getSettingsPage() throws PageTypeException {
          return (SettingsPage) PagePool.getPage(PageType. SETTINGS_PAGE);
     }

}

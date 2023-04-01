package config;

public class Hrefs {
    public static final String BASE_PAGE_URL = "http://localhost/dolibarr-develop/htdocs";
    public static final String LOGOUT_URL = BASE_PAGE_URL + "/user/logout.php?token=dbbaed32453c11549875592d64de74cd";
    public static final String ADMINTOOLS_PAGE_URL = BASE_PAGE_URL + "/admin/tools/index.php?mainmenu=home&leftmenu=admintools";
    public static final String INFORMATIONPANEL_PAGE_URL = BASE_PAGE_URL + "/index.php?mainmenu=home&leftmenu=home";
    public static final String LOGIN_PAGE_URL = BASE_PAGE_URL;
    public static final String HR_PAGE_URL = BASE_PAGE_URL + "/hrm/index.php?mainmenu=hrm&leftmenu=";
    public static final String PRODUCTS_PAGE_URL = BASE_PAGE_URL + "/product/index.php?mainmenu=products&leftmenu=";
    public static final String CREATE_PRODUCTS_PAGE_URL = BASE_PAGE_URL + "/product/card.php?leftmenu=product&action=create&type=0";
    public static final String PRODUCTS_LIST = BASE_PAGE_URL + "/product/list.php?leftmenu=product&type=0";

    public static final String SERVICE_PAGE_URL = BASE_PAGE_URL + "/product/index.php?mainmenu=products&leftmenu=";
    public static final String CREATE_SERVICE_PAGE_URL = BASE_PAGE_URL + "/product/card.php?leftmenu=service&action=create&type=1";
    public static final String SERVICE_LIST = BASE_PAGE_URL + "/product/list.php?leftmenu=service&type=1";
    public static final String THIRDPARTIES_PAGE_URL = BASE_PAGE_URL + "/societe/index.php?mainmenu=companies&leftmenu=";
    public static final String USERS_AND_GROUPS_PAGE_URL = BASE_PAGE_URL + "/htdocs/user/home.php?leftmenu=users";
    public static final String COMPANY_PAGE_URL = BASE_PAGE_URL + "/admin/company.php?mainmenu=home";
    public static final String MODULES_PAGE_URL = BASE_PAGE_URL + "/admin/modules.php?mainmenu=home";
    public static final String SETTINGS_PAGE_URL = BASE_PAGE_URL + "/admin/index.php?mainmenu=home&leftmenu=setup";

    public static final String CREATE_USER_HREF =  BASE_PAGE_URL + "/user/card.php?leftmenu=users&action=create";
    public static final String USER_LIST_HREF = BASE_PAGE_URL + "/user/list.php?leftmenu=users";
    public static final String HIERARCHICAL_USER_VIEW_HREF = BASE_PAGE_URL + "/user/hierarchy.php?leftmenu=users";
    public static final String CREATE_GROUP_HREF = BASE_PAGE_URL + "/user/group/card.php?leftmenu=users&action=create";
    public static final String GROUP_LIST_HREF = BASE_PAGE_URL + "/user/group/list.php?leftmenu=users"; // ERROR

}

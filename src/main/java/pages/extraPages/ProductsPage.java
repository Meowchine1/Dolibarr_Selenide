package pages.extraPages;

import pages.Page;

public class ProductsPage extends Page implements BaseModule{
    public ProductsPage(String href) {
        super(href);
    }

    @Override
    public BaseModule getList() {
        return null;
    }

    @Override
    public BaseModule create() {
        return null;
    }

    @Override
    public BaseModule update() {
        return null;
    }

    @Override
    public BaseModule delete() {
        return null;
    }

    @Override
    public BaseModule getProperties() {
        return null;
    }
}

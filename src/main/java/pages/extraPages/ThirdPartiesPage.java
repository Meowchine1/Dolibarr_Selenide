package pages.extraPages;

import pages.Page;

public class ThirdPartiesPage extends Page implements BaseModule{
    public ThirdPartiesPage(String href) {
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

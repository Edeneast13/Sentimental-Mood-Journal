package edeneastapps.sentimentalmoodjournal.views.dashboard;

public class MenuItem {
    private int title;
    private int subText;
    private int drawable;
    private int color;

    public MenuItem(int title, int subText, int drawable, int color) {
        this.title = title;
        this.subText = subText;
        this.drawable = drawable;
        this.color = color;
    }

    public int getTitle() {
        return title;
    }

    public int getSubText() {
        return subText;
    }

    public int getDrawable() {
        return drawable;
    }

    public int getColor() {
        return color;
    }
}

package edeneastapps.sentimentalmoodjournal;

public class MenuItem {
    private String title;
    private int backgroundColor;
    private int textColor;

    public MenuItem(String title, int backgroundColor, int textColor) {
        this.title = title;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    public String getTitle() {
        return title;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getTextColor() {
        return textColor;
    }
}

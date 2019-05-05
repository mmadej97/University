package entities;

public class Page {

    private int id;
    private String text;
    private String title;
    private int userId;
    private String headerColor;
    private String footerColor;
    private String containerColor;
    private String fontSize;
    private String fontWeight;
    private String fontColor;


    public int getId() {
        return id;
    }


    public Page(int id, String text, String title, int userId) {
        super();
        this.id = id;
        this.text = text;
        this.title = title;
        this.userId = userId;
    }




    public Page(int id, String text, String title, int userId, String headerColor, String footerColor,
                String containerColor) {
        super();
        this.id = id;
        this.text = text;
        this.title = title;
        this.userId = userId;
        this.headerColor = headerColor;
        this.footerColor = footerColor;
        this.containerColor = containerColor;
    }


    public Page(int id, String text, String title, int userId, String headerColor, String footerColor, String containerColor, String fontSize, String fontWeight, String fontColor) {
        this.id = id;
        this.text = text;
        this.title = title;
        this.userId = userId;
        this.headerColor = headerColor;
        this.footerColor = footerColor;
        this.containerColor = containerColor;
        this.fontSize = fontSize;
        this.fontWeight = fontWeight;
        this.fontColor = fontColor;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getHeaderColor() {
        return headerColor;
    }


    public void setHeaderColor(String headerColor) {
        this.headerColor = headerColor;
    }


    public String getContainerColor() {
        return containerColor;
    }


    public void setContainerColor(String containerColor) {
        this.containerColor = containerColor;
    }

    public String getFooterColor() {
        return footerColor;
    }


    public void setFooterColor(String footerColor) {
        this.footerColor = footerColor;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontWeight() {
        return fontWeight;
    }

    public void setFontWeight(String fontWeight) {
        this.fontWeight = fontWeight;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }
}

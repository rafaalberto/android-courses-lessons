package br.com.cardview.model;

public class Post {

    private String name;
    private int image;
    private String content;

    public Post(String name, int image, String content) {
        this.name = name;
        this.image = image;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

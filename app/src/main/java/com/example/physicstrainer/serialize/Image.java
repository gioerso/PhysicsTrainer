package com.example.physicstrainer.serialize;
import java.util.List;

public class Image {
    public Image (String name, Extension extension, boolean theoretical){
        this.name = name;
        this.extension = extension;
        this.theoretical = theoretical;
    }

    private long id;
    private String name;
    private Extension extension = Extension.JPEG;
    private boolean theoretical;
    private Question question;
    private List<TheoryImage> imageTheories;
}

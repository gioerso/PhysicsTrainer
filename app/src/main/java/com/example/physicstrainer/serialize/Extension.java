package com.example.physicstrainer.serialize;

public enum Extension {
    JPEG("jpg"),
    GIF("gif"),
    PNG("png"),
    WEBP("webp"),
    BMP("bmp"),
    SVG("svg"),
    PDF("pdf");

    final String formatting;

    Extension(String formatting) {
        this.formatting = formatting;
    }
}

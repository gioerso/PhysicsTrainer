package src.server.jointx.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Extension {
    JPEG("jpg"),
    GIF("gif"),
    PNG("png"),
    WEBP("webp"),
    BMP("bmp"),
    SVG("svg"),
    PDF("pdf");

    private final String code;

    public static Extension fromCode(String code){
        for (Extension extension:Extension.values()) {
            if(extension.getCode().equals(code)) return extension;
        }
        throw new IllegalArgumentException("No suitable image extension found");
    }
}

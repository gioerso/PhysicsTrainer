package src.server.jointx.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import src.server.jointx.enums.Extension;

import java.util.List;

@Table(name = "images")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    public Image (String name, Extension extension, boolean theoretical){
        this.name = name;
        this.extension = extension;
        this.theoretical = theoretical;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "extension")
    @Enumerated(EnumType.STRING)
    private Extension extension = Extension.JPEG;

    @JsonIgnore
    @Column(name = "theoretical")
    private boolean theoretical;

    @JsonBackReference
    @OneToOne(mappedBy = "image")
    private Question question;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "image",fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<TheoryImage> imageTheories;

    @JsonIgnore
    public String getFullName() {
        return "%s.%s".formatted(this.name, this.extension.getCode());
    }

    @JsonIgnore
    public String getAbsolutePath() {
        if (isTheoretical()) return "src/main/resources/theory_images/%s.%s"
                .formatted(this.name, this.extension.getCode());
        else return "src/main/resources/question_images/%s.%s"
                .formatted(this.name, this.extension.getCode());
    }
}

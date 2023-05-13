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
import src.server.jointx.enums.Achievement;

import java.util.List;

@Table(name = "questions")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "block_id", nullable = false)
  private Block block;

  @Column(name = "name")
  private String name;

  @Column(name = "text")
  private String text;

  @JsonManagedReference
  @OneToOne(optional = false)
  @JoinColumn(name = "image_id")
  private Image image;

  @Column(name = "achievement")
  @Enumerated(EnumType.STRING)
  private Achievement achievement;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "question",fetch = FetchType.EAGER)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<ThemeQuestion> questionThemes;
}

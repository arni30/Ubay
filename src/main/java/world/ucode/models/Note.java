package world.ucode.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "note")
public class Note
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String note;
    private Long userId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getUserId() {
        return userId;
    }

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", note='" + note + '\'' +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note1 = (Note) o;
        return Objects.equals(id, note1.id) &&
                Objects.equals(title, note1.title) &&
                Objects.equals(note, note1.note) &&
                Objects.equals(userId, note1.userId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, title, note, userId);
    }
    //Геттеры, сеттеры, toString(), equals(), hashcode()

}
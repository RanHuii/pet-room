package petroom.post;


import petroom.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Calendar;
import java.util.Date;

@Table(name = "posts")
public class Post extends BaseEntity {


    @Temporal(TemporalType.TIME)
    private Date dateOfPost;

    @Column(name = "likes", columnDefinition = "integer default 0")
    private Integer likes;

    @Column(name = "owner_id")
    @NotEmpty
    private Integer ownerId;

    public Integer getOwnerId() { return ownerId; }

    public void setOwnerId(Integer ownerId) { this.ownerId = ownerId; }

    public Date getDateOfPost() { return dateOfPost; }

    public void setDateOfPost(Date dateOfPost) { this.dateOfPost = dateOfPost; }

    public Integer getLikes() { return likes; }

    public void setLikes(Integer likes) { this.likes = likes; }

    public void incrementLike() { this.likes++; }

    public void decrementLike() {this.likes--; }

    @PrePersist
    void preInsert() {
        if (this.dateOfPost == null)
            this.dateOfPost = Calendar.getInstance().getTime();

    }
}




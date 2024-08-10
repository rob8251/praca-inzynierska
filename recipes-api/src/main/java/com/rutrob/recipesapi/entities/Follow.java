package com.rutrob.recipesapi.entities;

import com.rutrob.recipesapi.entities.embeddable.FollowId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "follows")
public class Follow {

    @EmbeddedId
    private FollowId id;

    @ManyToOne
    @MapsId("followerId")
    private User follower;

    @ManyToOne
    @MapsId("followedId")
    private User followed;

    public Follow(User follower, User followed) {
        this.follower = follower;
        this.followed = followed;
        this.id = new FollowId(follower.getId(), followed.getId());
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Follow follow = (Follow) o;
        return getId() != null && Objects.equals(getId(), follow.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }
}

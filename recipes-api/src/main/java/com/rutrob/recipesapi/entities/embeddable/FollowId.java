package com.rutrob.recipesapi.entities.embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FollowId implements Serializable {

    private Long followerId;

    private Long followedId;

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
        FollowId followId = (FollowId) o;
        return getFollowerId() != null && Objects.equals(getFollowerId(), followId.getFollowerId())
                && getFollowedId() != null && Objects.equals(getFollowedId(), followId.getFollowedId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(followerId, followedId);
    }
}

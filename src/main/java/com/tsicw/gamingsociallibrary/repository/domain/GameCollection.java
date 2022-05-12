package com.tsicw.gamingsociallibrary.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Table(name = "game_collections")
public class GameCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "collection_name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "game_collection",
            joinColumns = @JoinColumn(name = "games_collections_id"),
            inverseJoinColumns = @JoinColumn(name = "games_id"))
    private Set<Game> games;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

}

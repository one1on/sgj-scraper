package de.one1on.sgjscraper.filter;

import de.one1on.sgjscraper.model.Comment;
import de.one1on.sgjscraper.model.Jodel;

import java.util.function.Predicate;

public abstract class Filters {

    protected static final Predicate<Jodel> minVotes = jodel -> jodel.getVotes().getTotal() > 25;
    protected static final Predicate<Jodel> isFemale = jodel -> jodel.getAuthor().isFemale();
    protected static final Predicate<Comment> isFemaleComment = comment -> comment.getAuthor().isFemale();
    protected static final Predicate<Jodel> hasImage = jodel -> !jodel.getImage().isEmpty();
    protected static final Predicate<Comment> hasImageComment = comment -> !comment.getImage().isEmpty();

    public static final LoggingPredicate<Jodel> minVotesFilter = new LoggingPredicate<>(minVotes, "Min votes");
    public static final LoggingPredicate<Jodel> isFemaleFilter = new LoggingPredicate<>(isFemale, "Is jodel female");
    public static final LoggingPredicate<Comment> isFemaleCommentFilter = new LoggingPredicate<>(isFemaleComment, "Is comment female");
    public static final LoggingPredicate<Jodel> hasImageFilter = new LoggingPredicate<>(hasImage, "Has jodel image");
    public static final LoggingPredicate<Comment> hasImageCommentFilter = new LoggingPredicate<>(hasImageComment, "Has comment Image");

    private Filters() {}

}

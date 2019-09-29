package de.one1on.sgjscraper.core;

import com.google.common.collect.Lists;
import de.one1on.sgjscraper.api.SecretGermanJodelAPI;
import de.one1on.sgjscraper.model.Author;
import de.one1on.sgjscraper.model.Comment;
import de.one1on.sgjscraper.model.Jodel;
import de.one1on.sgjscraper.model.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.WARN)
class NotificationsScrapingTaskTest {

    private NotificationsScrapingTask task;

    @BeforeEach
    void setUp(@Mock SecretGermanJodelAPI api) {
        task = new NotificationsScrapingTask(api);

        final Notification n1 = new Notification();
        final Jodel j1 = new Jodel();
        j1.setId(1);
        j1.setImage("http://bla");
        Author a = new Author();
        a.setGenderId(Author.Gender.FEMALE);
        j1.setAuthor(a);
        n1.setHashtags(Lists.newArrayList("#a", "#b"));
        n1.setJodel(j1);

        final Comment c1 = new Comment();
        c1.setImage("http://bli");
        c1.setAuthor(a);
        j1.setResolvedComments(Lists.newArrayList(c1));

        List<Notification> notifications = Lists.newArrayList(n1);

        when(api.getNotifications()).thenReturn(notifications);
        when(api.getPost(1)).thenReturn(Lists.newArrayList(j1));
        when(api.getPostComments(1)).thenReturn(Lists.newArrayList(c1));
    }

    @Test
    void scrape() {
        task.scrape();
        assertThat(task.getDownloads()).hasSize(2);
    }
}
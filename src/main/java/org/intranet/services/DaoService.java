package org.intranet.services;

import org.intranet.entity.News;
import org.intranet.entity.User;
import org.intranet.repositories.NewsRepository;
import org.intranet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DaoService {
    private final UserRepository userRepository;

    private final NewsRepository newsRepository;

    @Autowired
    public DaoService(UserRepository userRepository, NewsRepository newsRepository) {
        this.userRepository = userRepository;
        this.newsRepository = newsRepository;
    }

    public News saveNews(News news) {
//        news.setDate(LocalDateTime.now());
        News save = newsRepository.save(news);
        return save;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<News> getNews() {
        return newsRepository.findAllByOrderByDateDesc();

    }
}

package org.intranet.services;

import org.intranet.entity.Post;
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

    public Post saveNews(Post post) {
        return newsRepository.save(post);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.updateFirstName(user.getUsername(),
                user.getFirstName(), user.getLastName(), user.getEmail());
    }

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<Post> getNews() {
        return newsRepository.findAllByOrderByDateDesc();
    }

    public User findByUsername(String currentPrincipalName) {
        return userRepository.findByUsername(currentPrincipalName);
    }

    public Post getNewsById(long id) {
        return newsRepository.findById(id).orElse(null);
    }
}

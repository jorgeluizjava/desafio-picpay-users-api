package com.picpay.users.users;

import com.picpay.users.shared.FindById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserSearchController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ConsumerRepository consumerRepository;

    @GetMapping(value = "/users")
    public List<UserDetailDTO> search(@RequestParam(value = "q", required = true) String query) {

        return userRepository
                .findByNameOrUserName(query)
                .stream()
                .map(UserDetailDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/users/{user_id}")
    public UserPayload findById(@PathVariable("user_id") Long userId) {
        User user = FindById.execute(userId, manager, User.class);
        Optional<Seller> optionalSeller = sellerRepository.findByAccountUser(user);
        Optional<Consumer> optionalConsumer = consumerRepository.findByAccountUser(user);
        return new UserPayload(user, optionalSeller, optionalConsumer);
    }
}

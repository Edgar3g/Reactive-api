package api.reactive.service;

import api.reactive.model.User;
import api.reactive.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    private UserRepo userRepo;

    public Flux<User> findAll() {
        return userRepo.findAll();
    }

    public Mono<User> save(User user) {
        return userRepo.save(user);
    }
    public Mono<User> findById(Long id) {
        return userRepo.findById(id);
    }
    public Mono<User> update(Long id,User user) {
        return userRepo.findById(id)
                .flatMap(
                        theUser ->{
                            theUser.setName(user.getName());
                            theUser.setEmail(user.getEmail());
                            return userRepo.save(theUser);
                        }
                );
    }

    public Mono<Void> delete(Long id) {
        return userRepo.deleteById(id);
    }
}

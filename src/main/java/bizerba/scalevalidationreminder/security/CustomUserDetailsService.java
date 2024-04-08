package bizerba.scalevalidationreminder.security;

import bizerba.scalevalidationreminder.model.User;
import bizerba.scalevalidationreminder.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {

        User user = userRepository.findByUserLogin(userLogin)
                .orElseThrow(() -> new UsernameNotFoundException("Użytkownik nie istnieje"));

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role -> new SimpleGrantedAuthority(role.getRoleName())))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(
                user.getUserLogin(),
                user.getUserPassword(),
                authorities
        );

    }
}

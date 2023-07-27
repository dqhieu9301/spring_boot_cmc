package spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import spring_boot.entity.UserDetail;
import spring_boot.entity.UserEntity;
import spring_boot.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService{

	@Autowired
    private UserRepository userRepository;
	
	@Override
	public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserDetail(user);
	}

}

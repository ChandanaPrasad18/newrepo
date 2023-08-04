package com.te.jspiders.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.te.jspiders.entity.AppUser;
import com.te.jspiders.repository.AppUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AppUser> optional = appUserRepository.findByUsername(username);
		if (!optional.isPresent()) {
			throw new UsernameNotFoundException(String.format("%s doesnot found in database", username));
		}
		return new User(optional.get().getUsername(), optional.get().getPassword(), optional.get().getRoles().stream()
				.map(s -> new SimpleGrantedAuthority(s.getRoleName())).collect(Collectors.toSet()));
	}

}

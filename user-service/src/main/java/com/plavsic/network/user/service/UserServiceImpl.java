package com.plavsic.network.user.service;

import com.plavsic.network.user.dto.UserRequest;
import com.plavsic.network.user.dto.UserResponse;
import com.plavsic.network.user.exception.UserNotFoundException;
import com.plavsic.network.user.model.Role;
import com.plavsic.network.user.model.User;
import com.plavsic.network.user.repository.RoleRepository;
import com.plavsic.network.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public Optional<UserResponse> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<UserResponse> findAll() {
        return List.of();
    }

    @Override
    @Transactional
    public UserResponse save(UserRequest userRequest) {
        Role role = roleRepository.findByName("ROLE_USER").get();
        User user = modelMapper.map(userRequest, User.class);
        user.setId(null);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setProfilePictureUrl("url://path");
        user.setRoles(new HashSet<>(List.of(role)));
        user = userRepository.save(user);

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                null,
                null,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getProfilePictureUrl()
        );
    }

    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    public UserResponse findByUsername(String username){
        UserResponse userResponse = userRepository.findByUsername(username)
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getPassword(),
                        user.getRoles(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getProfilePictureUrl()
                ))
                .orElseThrow(UserNotFoundException::new);

        return userResponse;
    }
}

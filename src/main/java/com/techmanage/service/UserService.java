package com.techmanage.service;

import com.techmanage.dto.*;
import com.techmanage.exception.*;
import com.techmanage.model.User;
import com.techmanage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository repo;

    public UserResponse create(UserRequest req) {
        if (repo.existsByEmail(req.email())) {
            throw new DuplicateEmailException(req.email());
        }
        var user = repo.save(toEntity(req));
        return toResponse(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        return repo.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public UserResponse update(Long id, UserRequest req) {
        var user = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        user.setFullName(req.fullName());
        user.setPhone(req.phone());
        user.setBirthDate(req.birthDate());
        user.setUserType(req.userType());
        return toResponse(user);
    }

    public void delete(Long id) {
        if (!repo.existsById(id))
            throw new ResourceNotFoundException(id);
        repo.deleteById(id);
    }

    private User toEntity(UserRequest r) {
        return User.builder()
                .fullName(r.fullName())
                .email(r.email())
                .phone(r.phone())
                .birthDate(r.birthDate())
                .userType(r.userType())
                .build();
    }

    private UserResponse toResponse(User u) {
        return new UserResponse(
                u.getId(), u.getFullName(), u.getEmail(),
                u.getPhone(), u.getBirthDate(), u.getUserType());
    }
}
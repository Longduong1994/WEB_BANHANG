package ra.service.impl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.exception.RegisterException;
import ra.model.dto.request.ForgotPasswordForm;
import ra.model.dto.request.RegisterRequestDto;
import ra.model.dto.response.UserResponseDto;
import ra.model.entity.Role;
import ra.model.entity.RoleName;
import ra.model.entity.Users;
import ra.repository.IUserRepository;
import ra.service.impl.role.IRoleService;
import ra.service.mapper.UserMapper;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Optional<Users> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Users save(RegisterRequestDto user) throws RegisterException {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RegisterException("User is exits");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RegisterException("Email is exits");
        }
        Set<Role> roles = new HashSet<>();
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
        } else {
            user.getRoles().stream().forEach(
                    role -> {
                        switch (role) {
                            case "admin":
                                roles.add(roleService.findByRoleName(RoleName.ROLE_ADMIN));
                            case "user":
                                roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
                        }
                    }
            );
        }
        Users users = Users.builder()
                .fullName(user.getFullName())
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .roles(roles)
                .status(true).build();
        return userRepository.save(users);
    }

    @Override
    public Users findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public UserResponseDto lock(Long id) {
        Optional<Users> users = userRepository.findById(id);
        users.get().setStatus(!users.get().isStatus());
        return userMapper.toResponse(userRepository.save(users.get()));
    }

    @Override
    public List<UserResponseDto> findAll() {
        List<Users> users = userRepository.findAllUsersWithUserRole(RoleName.ROLE_USER);
        return users.stream().map(u->userMapper.toResponse(u)).collect(Collectors.toList());
    }

    @Override
    public UserResponseDto changePassword(ForgotPasswordForm forgotPasswordForm) throws ClassCastException{
       Users users = userRepository.findByEmail(forgotPasswordForm.getEmail());
       if (users == null){
           throw  new ClassCastException("Email not found");
       }
       users.setPassword(passwordEncoder.encode(forgotPasswordForm.getPassword()));
        return userMapper.toResponse(userRepository.save(users));
    }
}

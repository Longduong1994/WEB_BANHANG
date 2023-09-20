package ra.service.impl.user;

import ra.exception.RegisterException;
import ra.model.dto.request.ForgotPasswordForm;
import ra.model.dto.request.RegisterRequestDto;
import ra.model.dto.response.UserResponseDto;
import ra.model.entity.Users;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<Users> findByUserName(String username);
    Users save(RegisterRequestDto user) throws RegisterException;
    Users findById(Long id);
    UserResponseDto lock(Long id);
    List<UserResponseDto> findAll();
    UserResponseDto changePassword(ForgotPasswordForm forgotPasswordForm);
}

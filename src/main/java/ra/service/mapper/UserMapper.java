package ra.service.mapper;

import org.springframework.stereotype.Component;
import ra.model.dto.response.UserResponseDto;
import ra.model.entity.Users;
import ra.service.IGenericMapper;
@Component
public class UserMapper{
    public UserResponseDto toResponse(Users user){
        return UserResponseDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .email(user.getEmail())
                .status(user.isStatus()).build();
    }
}

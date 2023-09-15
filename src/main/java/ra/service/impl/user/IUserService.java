package ra.service.impl.user;

import ra.exception.RegisterException;
import ra.model.dto.request.RegisterRequestDto;
import ra.model.entity.Users;
import ra.service.IGenericService;

import java.util.Optional;

public interface IUserService {
    Optional<Users> findByUserName(String username);
    Users save(RegisterRequestDto user) throws RegisterException;

}

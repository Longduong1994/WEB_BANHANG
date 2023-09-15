package ra.service.impl.role;

import ra.model.entity.Role;
import ra.model.entity.RoleName;

public interface IRoleService {
    Role findByRoleName(RoleName roleName);
}

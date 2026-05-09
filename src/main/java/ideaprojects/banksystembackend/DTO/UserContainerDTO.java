package ideaprojects.banksystembackend.DTO;

import ideaprojects.banksystembackend.DTO.response.UserDTO;


public class UserContainerDTO {
    private final UserDTO user;

    public UserContainerDTO(UserDTO user) {
        this.user = user;
    }

    public UserDTO getUser() {
        return user;
    }
}

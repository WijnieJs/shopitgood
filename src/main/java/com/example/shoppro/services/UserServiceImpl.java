package com.example.shoppro.services;

import com.example.shoppro.dtos.UserDto;
import com.example.shoppro.entity.Role;
import com.example.shoppro.entity.User;
import com.example.shoppro.exceptions.UserIdException;
import com.example.shoppro.exceptions.ProjectNotFoundException;


import com.example.shoppro.repositories.CustomUserService;
import com.example.shoppro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

//       Handy to use, leaving here as reminder
//        System.out.println(users.getClass().getSimpleName() + "user info formation about it");
//      @RequestParam(value = "brand", required = false) Optional<String> brand
//    https://www.youtube.com/watch?v=7HQ-x9aoZx8
// HANDY THINGS

// boolean hasUser = !userRepository.findById(userId).isPresent();
@Service
public class UserServiceImpl implements CustomUserService {

    @Autowired
    private  UserRepository userRepository;
    private final RoleServiceImpl roleService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(RoleServiceImpl roleService, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public  UserDto saveUser(UserDto dto) {
        try {
//            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
//            String check = encodePassword(dto.getPassword());
//            System.out.println(check);
            dto.setPassword(encodePassword(dto.getPassword()));
            User user =  userRepository.save(transferToUser(dto));
//            encodePassword(user);
            System.out.println(user);

            return  transferToDto(user);
        }
        catch (Exception e) {
            throw new ProjectNotFoundException(e.getMessage());
        }
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
//    @Override
//    public  UserDto saveUser(UserDto dto) {
//        try {
//
//            return savedUser(dto);
//        }
//        catch (Exception e) {
//            throw new ProjectNotFoundException("Email must be unique");
//        }
//    }
//

//    public UserDto savedUser(UserDto dto)   {
//
//        return transferToDto(  userRepository.save(transferToUser(dto)));
//    }



//    public User saveUser(User user) {
//        encodePassword(user);
//
//        return userRepository.save(user);
//    }



    @Override
    public UserDto updateUserInDb(String email, UserDto dto) {

       try {
           User userInDb = userRepository.getUserByEmail(email);
//               String encodedPassword = passwordEncoder.encode(dto.getPassword());
//               userInDb.setPassword(encodedPassword);

           if(dto.getFirstName()  != null) {
               userInDb.setFirstName(dto.getFirstName());
           }

           userRepository.save(userInDb);
          return transferToDto(userInDb);
       } catch (ProjectNotFoundException e) {
           throw new ProjectNotFoundException("User not found in , signup" );
       }
    }



//    @Override
//    public List<User> fetchAllUserz() {
//
//        return userRepository.findAll();
//    }


    @Override
    public List<UserDto> fetchAllUser() {
        try {
            return findAllUsers();
        } catch (Exception e) {
            throw new ProjectNotFoundException("Finding users failed");
        }
    }

        public List<UserDto> findAllUsers() {
            List<UserDto> dtos = new ArrayList<>();
            for (User user : userRepository.findAll()) {
                dtos.add(transferToDto(user));
            }
                return dtos;
        }

    @Override
    public List<UserDto> fetchUsersByRoles(String roleName) {
        String errorMessage = "No users found for role " + roleName;
        try {
            return allUsersWithPermissions(roleName.toUpperCase());
        } catch (Exception e) {
            throw new ProjectNotFoundException(errorMessage);
        }
    }

        public List<UserDto> allUsersWithPermissions(String roleName) {
            boolean hasUsers = allValidatedUsersWithRolePermission(roleName).size() > 0;

            if(hasUsers) {
                return allValidatedUsersWithRolePermission(roleName);
            }  else {
                throw new ProjectNotFoundException("CustomMessage");
            }

        }

        public List<UserDto> allValidatedUsersWithRolePermission(String roleName) {
            List<UserDto> allValidatedUsers = new ArrayList<>();

            for (UserDto usersListToCheck : fetchAllUser()) {
                for (Role rolesInUser : usersListToCheck.getRoles()) {
                    if (validateUserByRoleForPermission(roleName, rolesInUser)) {
                        allValidatedUsers.add(usersListToCheck);

                    }
                }
            }
            return allValidatedUsers;

        }

    @Override
    public UserDto getDtoSingleUserItemById(Integer userId) {
        try {
            return transferToDto(userRepository.findById(userId).get());
        } catch (Exception e) {
            throw new UserIdException("No such user: " + userId + "");
         }
    }



    @Override
    public UserDto addRoleToUser(Integer userId, String roleName) {
                        String errorMessage  = "No user with this identity has been found" +userId + "";
         try {
                        User userToUpdate = checkAndReturnUser(userId);

                        errorMessage = "No role found with " + roleName + " in database";


                        Role newRole =  validateIfRoleExistsInDatabase(roleName);
                        errorMessage = "This user  already has a role with " + roleName + " ";
                        checkIfUserHasRole(newRole, userToUpdate.getRoles(), roleName.toUpperCase());

                        userToUpdate.addRole(newRole);
                        userRepository.save(userToUpdate);

                        return transferToDto(userToUpdate);
            } catch (Exception e) {
                    throw new ProjectNotFoundException(errorMessage);
        }

    }



    public Role validateIfRoleExistsInDatabase(String roleName) {
        return  roleService.findRoleInDbByName(roleName);
    }

    public void checkIfUserHasRole(Role userRole , List<Role> roles , String roleName) {
        for(Role roleinUser : roles) {
            if(  validateUserByRoleForPermission(roleName, roleinUser)) {

                throw new ProjectNotFoundException("User has role already");
            }
        }
    }

    public User checkAndReturnUser(Integer userId) throws ProjectNotFoundException{
        if ( userRepository.findById(userId).isPresent()) {
            return userRepository.findById(userId).get();
        } else {throw new ProjectNotFoundException("errorMessage");
        }
    }


    public boolean validateUserByRoleForPermission(String role, Role rolesInUser) {
        return rolesInUser.getName().toUpperCase().equals(role.toUpperCase() );
    }


    @Override
    public void deleteUserById(Integer userId) {

        userRepository.deleteById(userId);
    }

        public User transferToUser(UserDto dto) {
            User user = new User();
            user.setId(dto.getId());
            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            user.setEmail(dto.getEmail());
            user.setPhotos(dto.getPhotos());
            user.setEnabled(dto.getEnabled());
            user.setPassword(dto.getPassword());
            return user;
        }
//    private String encodePassword(String password) {
//        String pw =  passwordEncoder.encode(password);
//            System.out.println(pw + " in encoderdd ");
//            return pw;
//    }


        public UserDto transferToDto(User user) {
            var dto = new UserDto();
            dto.setId(user.getId());
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setPassword(user.getPassword());
            dto.setEmail(user.getEmail());
            dto.setPhotos(user.getPhotos());
            dto.setEnabled(user.getEnabled());
            dto.setRoles(user.getRoles());
            System.out.println(dto + "in userToDto");
                return dto;
        }




    public Boolean isEmailUnique(String email) {
        User userEmailCheck = userRepository.getUserByEmail(email);
        System.out.println(userEmailCheck == null);
        return userEmailCheck == null;
    }

    @Override
    public User fetchUserByEmail(String email) {

        try {
            return userRepository.getUserByEmail(email);
        } catch (Exception e) {
            throw new ProjectNotFoundException("here");
        }
    }



    }



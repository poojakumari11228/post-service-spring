package com.lab.lab.service.impl;


import com.lab.lab.Util.Mapper;
import com.lab.lab.dto.CommentDto;
import com.lab.lab.dto.PostDto;
import com.lab.lab.dto.UserDto;
import com.lab.lab.dto.AddUserDto;
import com.lab.lab.entity.Comment;
import com.lab.lab.entity.Role;
import com.lab.lab.entity.User;
import com.lab.lab.repo.CommentRepo;
import com.lab.lab.repo.RoleRepo;
import com.lab.lab.repo.UserRepo;
import com.lab.lab.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final ModelMapper modelMapper;
    private final CommentRepo commentRepo;
    private final BCryptPasswordEncoder bCryptPassEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, ModelMapper modelMapper, CommentRepo commentRepo, BCryptPasswordEncoder bCryptPassEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.modelMapper = modelMapper;
        this.commentRepo = commentRepo;
        this.bCryptPassEncoder = bCryptPassEncoder;
    }


    @Override
    public List<UserDto> findUsers() {
        List<UserDto> userList = Mapper.convertUserListToUserDtoList(userRepo.findAll());
        return userList;
    }

    @Override
    public UserDto findUserById(long id) {
        return modelMapper.map(userRepo.findById(id).get(), new UserDto().getClass());
    }

    @Override
    public void saveUser(AddUserDto userDto) {
        userDto.setPassword(bCryptPassEncoder.encode(userDto.getPassword()));
        User user = modelMapper.map(userDto, new User().getClass());
        Optional<Role> userRole = roleRepo.findByType("USER");
        if (userRole.isPresent())
            user.getRoles().add(userRole.get());
        userRepo.save(user);
    }

    @Override
    public List<PostDto> getUserPosts(long id) {
        var user = userRepo.findById(id);
        List<PostDto> postDtos = Mapper.convertPostListToPostDtoList(user.get().getPosts());
        return postDtos;
    }

    @Override
    public List<UserDto> findUsersWithMoreThanNPosts(int n) {
        return Mapper.convertUserListToUserDtoList(userRepo.findUsersWithMoreThanNPost(n));
    }

    @Override
    public void deleteById(long id) {
        userRepo.deleteById(id);
    }

    @Override
    public List<UserDto> findUsersWithPostTitle(String title) {
        List<User> user = userRepo.findAllByPostsTitle(title);
        return Mapper.convertUserListToUserDtoList(user);
    }

    @Override
    public CommentDto getUserPostComentsById(long uid, long pid, long cid) {
        Optional<Comment> comment = commentRepo.findByIdAndPostIdAndPostUserId(cid, pid, uid);
        return Mapper.convertCommentToCommentDto(comment.orElse(new Comment()));

    }

    @Override
    public List<UserDto> findAdmins() {
        List<User> admins = userRepo.findAllByRolesType("ADMIN");
        return Mapper.convertUserListToUserDtoList(admins);
    }
}

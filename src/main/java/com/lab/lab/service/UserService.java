package com.lab.lab.service;

import com.lab.lab.dto.CommentDto;
import com.lab.lab.dto.PostDto;
import com.lab.lab.dto.UserDto;
import com.lab.lab.dto.AddUserDto;

import java.util.List;


public interface UserService {

    List<UserDto> findUsers();
    UserDto findUserById(long id);

    void saveUser(AddUserDto user);

    List<PostDto> getUserPosts(long id);

    List<UserDto> findUsersWithMoreThanNPosts(int n);

    void deleteById(long id);

    List<UserDto> findUsersWithPostTitle(String title);

    CommentDto getUserPostComentsById(long uid, long pid, long cid);

    List<UserDto> findAdmins();
}

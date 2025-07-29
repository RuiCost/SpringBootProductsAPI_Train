package com.example.produtoisapi.userManagement.api;

import org.mapstruct.Mapper;
import com.example.produtoisapi.userManagement.model.User;

import java.util.List;
@Mapper(componentModel = "spring")
public abstract class UserViewMapper {

	public abstract UserView toUserView(User user);

	public abstract Iterable<UserView> toUserView(Iterable<User> users);

	public abstract List<UserView> toUserView(List<User> users);
}

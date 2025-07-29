package com.example.produtoisapi.userManagement.services;

import com.example.produtoisapi.userManagement.api.CreateUserRequest;
import com.example.produtoisapi.userManagement.api.EditUserRequest;
import com.example.produtoisapi.userManagement.model.Role;
import com.example.produtoisapi.userManagement.model.User;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-29T10:37:26+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class EditUserMapperImpl extends EditUserMapper {

    @Override
    public User create(CreateUserRequest request) {
        if ( request == null ) {
            return null;
        }

        String password = null;
        String fullName = null;
        String username = null;

        password = request.getPassword();
        fullName = request.getFullName();
        username = request.getUsername();

        User user = new User( username, password, fullName );

        if ( user.getAuthorities() != null ) {
            Set<Role> set = stringToRole( request.getAuthorities() );
            if ( set != null ) {
                user.getAuthorities().addAll( set );
            }
        }

        return user;
    }

    @Override
    public void update(EditUserRequest request, User user) {
        if ( request == null ) {
            return;
        }

        if ( user.getAuthorities() != null ) {
            user.getAuthorities().clear();
            Set<Role> set = stringToRole( request.getAuthorities() );
            if ( set != null ) {
                user.getAuthorities().addAll( set );
            }
        }
        if ( request.getFullName() != null ) {
            user.setFullName( request.getFullName() );
        }
    }
}

package com.example.produtoisapi.userManagement.api;

import com.example.produtoisapi.userManagement.model.Role;
import com.example.produtoisapi.userManagement.model.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-05T11:17:07+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class UserViewMapperImpl extends UserViewMapper {

    @Override
    public UserView toUserView(User user) {
        if ( user == null ) {
            return null;
        }

        UserView userView = new UserView();

        if ( user.getId() != null ) {
            userView.setId( String.valueOf( user.getId() ) );
        }
        userView.setUsername( user.getUsername() );
        userView.setFullName( user.getFullName() );
        Set<Role> set = user.getAuthorities();
        if ( set != null ) {
            userView.setAuthorities( new HashSet<Role>( set ) );
        }

        return userView;
    }

    @Override
    public Iterable<UserView> toUserView(Iterable<User> users) {
        if ( users == null ) {
            return null;
        }

        ArrayList<UserView> iterable = new ArrayList<UserView>();
        for ( User user : users ) {
            iterable.add( toUserView( user ) );
        }

        return iterable;
    }

    @Override
    public List<UserView> toUserView(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserView> list = new ArrayList<UserView>( users.size() );
        for ( User user : users ) {
            list.add( toUserView( user ) );
        }

        return list;
    }
}

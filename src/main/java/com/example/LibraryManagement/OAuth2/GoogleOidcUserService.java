package com.example.LibraryManagement.OAuth2;


import com.example.LibraryManagement.Entity.Roles;
import com.example.LibraryManagement.Entity.User;
import com.example.LibraryManagement.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GoogleOidcUserService extends OidcUserService {


    private final UserRepo userRepo;



    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) {


        System.out.println("===== OIDC USER SERVICE STARTED =====");


        OidcUser oidcUser = super.loadUser(userRequest);

        String email =
                oidcUser.getAttribute("email");
        String name =
                oidcUser.getAttribute("name");

        System.out.println("Google Email : " + email);

        User user = userRepo.findByEmail(email)
                .orElseGet(() -> {
                    System.out.println("Creating New Google User");
                    User newUser = new User();

                    newUser.setName(name);

                    newUser.setEmail(email);

                    newUser.setPassword(null);

                    newUser.setRole(Roles.MEMBER);

                    newUser.setProvider("GOOGLE");

                    newUser.setProviderId(
                            oidcUser.getSubject()
                    );

                    User saved =
                            userRepo.save(newUser);


                    System.out.println(
                            "Saved User : "
                                    + saved.getEmail()
                    );


                    return saved;

                });



        System.out.println(
                "User Available : "
                        + user.getEmail()
        );


        return oidcUser;

    }
}

package com.boca.grabswebservice.security;

import com.boca.grabswebservice.model.Role;
import com.boca.grabswebservice.model.User;
import com.boca.grabswebservice.service.UserService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.boca.grabswebservice.security.SecurityConstants.EXPIRATION_TIME;
import static com.boca.grabswebservice.security.SecurityConstants.SECRET;

@Component
public class JwtTokenProvider {


    @Autowired
    private UserService userService;

    //Generate the token

    public String generateToken(Authentication authentication){
        User user1 = (User)authentication.getPrincipal();
        User user = userService.getUserByUsername(user1.getEmail());
        Date now = new Date(System.currentTimeMillis());
        String role="";
        for (Role role1: user.getRoles()){
            role = role1.getName();
        }
        Date expiryDate = new Date(now.getTime()+EXPIRATION_TIME);

        String userId = Long.toString(user.getId());

        Map<String,Object> claims = new HashMap<>();
        claims.put("id", (Long.toString(user.getId())));
        claims.put("email", user.getEmail());
        claims.put("firstname", user.getFirstName());
        claims.put("lastname", user.getLastName());
        claims.put("role", role);

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        }

    //Validate the token
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            System.out.println("Invalid JWT Signature");
        }catch (MalformedJwtException ex){
            System.out.println("Invalid JWT Token");
        }catch (ExpiredJwtException ex){
            System.out.println("Expired JWT token");
        }catch (UnsupportedJwtException ex){
            System.out.println("Unsupported JWT token");
        }catch (IllegalArgumentException ex){
            System.out.println("JWT claims string is empty");
        }
        return false;
    }


    //Get user Id from token

    public Long getUserIdFromJWT(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String id = (String)claims.get("id");

        return Long.parseLong(id);
    }


    public String getUserEmailFromJWT(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String email = (String)claims.get("email");

        return email;
    }

    public String getUserRolelFromJWT(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String role = (String)claims.get("role");

        return role;
    }

}

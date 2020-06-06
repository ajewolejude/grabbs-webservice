package com.boca.grabswebservice.controller.api;

import com.boca.grabswebservice.dom.Dashboard;
import com.boca.grabswebservice.model.*;
import com.boca.grabswebservice.payload.JWTLoginSucessReponse;
import com.boca.grabswebservice.payload.LoginRequest;
import com.boca.grabswebservice.payload.trip.TripResponse;
import com.boca.grabswebservice.security.JwtTokenProvider;
import com.boca.grabswebservice.service.*;
import com.boca.grabswebservice.service.dashboard.QueryService;
import com.boca.grabswebservice.utils.UserAuth;
import com.boca.grabswebservice.validator.UserValidator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.boca.grabswebservice.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TruckService truckService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private MateService mateService;

    @Autowired
    private CompanyOwnerService companyOwnerService;

    @Autowired
    private PrivateOwnerService privateOwnerService;

    @Autowired
    private TripService tripService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private QueryService queryService;


    @ApiOperation(value = "Login a user", response = ResponseEntity.class)
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;
        UserAuth userAuth = new UserAuth();
        String email = loginRequest.getEmail();
        String role = userAuth.getRole();
        String status = "pending";

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        User user = userService.getUserByUsername(loginRequest.getEmail());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX +  tokenProvider.generateToken(authentication);
        for (Role role1: user.getRoles()){
            role = role1.getName();
        }

        Dashboard dashboard = new Dashboard();
        List<Trip> tripList = null;
        List<Trip>  tripCompletedList=null;
        if(role.contains("ADMIN")){

            dashboard = queryService.getAdminDetails();
            tripList = tripService.findAllByOrderByIdDesc();
            status= "complete";
        }else if(role.contains("DRIVER")){
            Driver currentDriver =driverService.getUserProfileByEmail(email);
          if( currentDriver!=null){
              tripCompletedList= tripService.getAllByStatusAndDriver(currentDriver.getId(),"End");
              tripList = tripService.getAllByDriver(currentDriver.getId());
              dashboard.setCompletedTripCount((long) tripCompletedList.size());
              dashboard.setTripCount((long) tripList.size());

              status= "complete";
          }

        } else if(role.contains("MATE")){

            if( mateService.getUserProfile(email)!=null){
                status= "complete";
            }

        } else if(role.contains("CORPORATE_TRUCK_OWNER")){
            if( companyOwnerService.getAllUserDetailsByEmail(email)!=null){
                status= "complete";
            }


        } else if(role.contains("PRIVATE_TRUCK_OWNER")){
            if( privateOwnerService.getUserProfileByEmail(email)!=null){
                status= "complete";
            }


        } else if(role.contains("MERCHANT")){


        }
        return ResponseEntity.ok(new JWTLoginSucessReponse(true, jwt,  role, email, user.getFirstName(), user.getLastName(), status, user.getId()));
    }



    @ApiOperation(value = "Get User Details", response = ResponseEntity.class)
    @GetMapping("/details")
    public ResponseEntity<?> getUserDetails(@RequestHeader (name = "Authorization") String jwtToken){
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String role = jwtTokenProvider.getUserRolelFromJWT(jwtToken.substring(7));
        Long  id = jwtTokenProvider.getUserIdFromJWT(jwtToken.substring(7));
        String  email = jwtTokenProvider.getUserEmailFromJWT(jwtToken.substring(7));
        List<Trip> tripList = null;
        List<Trip>  tripCompletedList=null;
        Dashboard dashboard = new Dashboard();
        if(role.contains("ADMIN")){


            tripList = tripService.findAllByOrderByIdDesc();
        }else if(role.contains("DRIVER")){
            Driver currentDriver =driverService.getUserProfileByEmail(email);
            if( currentDriver!=null){
                tripCompletedList= tripService.getAllByStatusAndDriver(currentDriver.getId(),"End");
                tripList = tripService.getAllByDriver(currentDriver.getId());
                dashboard.setCompletedTripCount((long) tripCompletedList.size());
                dashboard.setTripCount((long) tripList.size());

            }

        } else if(role.contains("MATE")){

            Mate currentMate =mateService.getUserProfile(email);
            if( currentMate!=null){
                tripCompletedList= tripService.getAllByStatusAndDriver(currentMate.getId(),"End");
                tripList = tripService.getAllByDriver(currentMate.getId());
                dashboard.setCompletedTripCount((long) tripCompletedList.size());
                dashboard.setTripCount((long) tripList.size());

            }

        } else if(role.contains("CORPORATE_TRUCK_OWNER")){
            if( companyOwnerService.getAllUserDetailsByEmail(email)!=null){
            }


        } else if(role.contains("PRIVATE_TRUCK_OWNER")){
            if( privateOwnerService.getUserProfileByEmail(email)!=null){
            }


        } else if(role.contains("MERCHANT")){


        }

        return ResponseEntity.ok(new TripResponse(dashboard,tripList));

    }

    @ApiOperation(value = "Register a user", response = ResponseEntity.class)
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){
        // Validate passwords match
        userValidator.validate(user,result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;

        User newUser = userService.saveUser(user);

        return  new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
}

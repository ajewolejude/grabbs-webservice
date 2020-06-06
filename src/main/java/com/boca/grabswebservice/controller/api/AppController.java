package com.boca.grabswebservice.controller.api;


import com.boca.grabswebservice.service.*;
import com.boca.grabswebservice.service.dashboard.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1")
public class AppController {


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
    private UserService userService;

    @Autowired
    private TripService tripService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private QueryService queryService;

//    @ApiOperation(value = "Get homepage details", response = ResponseEntity.class)
//    @GetMapping("/")
//    public  ResponseEntity<?>  viewHomePage(){
//        List<Trip> tripList = tripService.findAllByOrderByIdDesc();
//
//
//        DecimalFormatter decimalFormatter = new DecimalFormatter();
//
//
//        BigDecimal earning = new BigDecimal(0);
//
//
//        List<Truck> myTruckList = new ArrayList<>();
//
//        UserAuth userAuth = new UserAuth();
//        String email = userAuth.getEmail();
//        String role = userAuth.getRole();
//        if(role.contains("ADMIN")){
//
//            Dashboard dasboard = queryService.getAdminDetails();
//            model.addAttribute("driverSize", dasboard.getDriverCount());
//            model.addAttribute("completedTripSize", dasboard.getCompletedTripCount());
//            model.addAttribute("mateSize", dasboard.getMateCount());
//            model.addAttribute("truck_size", dasboard.getTruckCount());
//            model.addAttribute("myTruckList",myTruckList);
//            model.addAttribute("tripList",tripList);
//            model.addAttribute("tripSize",tripList.size());
//            model.addAttribute("earning",decimalFormatter.format(dasboard.getEarning()));
//            model.addAttribute("myTruckListSize",myTruckList.size());
//        } else if(role.contains("DRIVER")){
//            Driver driver = driverService.getUserProfileByEmail(email);
//            if (driver==null){
//                //then user doesnt have a prior profile
//                //create profile
//                User user = userService.getUserByUsername(email);
//                ModelAndView modelAndView = new ModelAndView("driver/new_driver");
//                modelAndView.addObject("message","profile");
//                Driver newDriver = new Driver();
//                newDriver.setFirst_name(user.getFirstName());
//                newDriver.setEmail(user.getEmail());
//                newDriver.setLast_name(user.getLastName());
//                newDriver.setRelationship("Married");
//                modelAndView.addObject("driver", newDriver);
//                return modelAndView;
//            }else{
//                //show profile
//                //redirect to driver view user page
//                //return new ModelAndView("redirect:/driver/user/"+user.getId()+"/view/");
//                Driver currentDriver = driverService.getByEmail(email);
//                List<Trip>  tripCompletedListDriver = tripService.getAllByStatusAndDriver(currentDriver.getId(),"End");
//                List<Trip>  tripListDriver = tripService.getAllByDriver(currentDriver.getId());
//                model.addAttribute("completedTripSize",tripCompletedListDriver.size());
//                model.addAttribute("tripList",tripListDriver);
//                model.addAttribute("tripSize",tripListDriver.size());
//            }
//        }else if(role.contains("MATE")){
//            Mate mate = mateService.getUserProfile(email);
//            if (mate==null){
//                //then user doesnt have a prior profile
//                //create profile
//
//                User user = userService.getByEmail(email);
//                ModelAndView modelAndView = new ModelAndView("mate/new_mate");
//                modelAndView.addObject("message","profile");
//                Mate newMate = new Mate();
//                newMate.setFirst_name(user.getFirstName());
//                newMate.setEmail(user.getEmail());
//                newMate.setLast_name(user.getLastName());
//                newMate.setRelationship("Married");
//                modelAndView.addObject("mate", newMate);
//                return modelAndView;
//            }else{
//                //show profile
//                //redirect to mate view user page
//                //return new ModelAndView("redirect:/mate/user/"+user.getId()+"/view/");
//                Mate currentMate = mateService.getByEmail(email);
//                List<Trip>  tripCompletedListDriver = tripService.getAllByStatusAndDriver(currentMate.getId(),"End");
//                List<Trip>  tripListDriver = tripService.getAllByDriver(currentMate.getId());
//                model.addAttribute("completedTripSize",tripCompletedListDriver.size());
//                model.addAttribute("tripList",tripListDriver);
//                model.addAttribute("tripSize",tripListDriver.size());
//            }
//        } else if(role.contains("CORPORATE_TRUCK_OWNER")){
//            CompanyOwner companyOwner = companyOwnerService.getAllUserDetailsByEmail(email);
//            if (companyOwner==null){
//                //then user doesnt have a prior profile
//                //create profile
//
//                User user = userService.getByEmail(email);
//                ModelAndView modelAndView = new ModelAndView("owner/company/new");
//                modelAndView.addObject("message","profile");
//                CompanyOwner company_owner = new CompanyOwner();
//                company_owner.setContact_first_name(user.getFirstName());
//                company_owner.setContact_last_name(user.getLastName());
//                company_owner.setContact_email(user.getEmail());
//                modelAndView.addObject("company_owner", company_owner);
//                return modelAndView;
//            }else{
//                //show profile
//                //redirect to mate view user page
//                //return new ModelAndView("redirect:/mate/user/"+user.getId()+"/view/");
//                myTruckList = truckService.getMyTrucks(companyOwner.getId(), "Corporate");
//                List<Trip> myTripList = new ArrayList<>();
//                List<Trip> myCompletedTripList = new ArrayList<>();
//                for (Truck truck : myTruckList) {
//                    for (Trip trip : tripList) {
//                        if(truck.getId().equals(trip.getTruck_id())){
//                            myTripList.add(trip);
//                            if(trip.getStatus().equals("End")) myCompletedTripList.add(trip);
//                            earning = earning.add(trip.getCharge()) ;
//                        }
//                    }
//
//
//                }
//                model.addAttribute("completedTripSize",myCompletedTripList.size());
//                model.addAttribute("truck_size", myTruckList.size());
//                model.addAttribute("myTruckList",myTruckList);
//                model.addAttribute("tripList",myTripList);
//                model.addAttribute("tripSize",myTripList.size());
//                model.addAttribute("earning",decimalFormatter.format(earning));
//                model.addAttribute("myTruckListSize",myTruckList.size());
//            }
//        } else if(role.contains("PRIVATE_TRUCK_OWNER")){
//            PrivateOwner privateOwner = privateOwnerService.getUserProfileByEmail(email);
//            if (privateOwner==null){
//                //then user doesnt have a prior profile
//                //create profile
//                User user = userService.getByEmail(email);
//
//                ModelAndView modelAndView = new ModelAndView("owner/private/new");
//                modelAndView.addObject("message","profile");
//                PrivateOwner private_owner = new PrivateOwner();
//                private_owner.setFirst_name(user.getFirstName());
//                private_owner.setLast_name(user.getLastName());
//                private_owner.setEmail(user.getEmail());
//                modelAndView.addObject("private_owner", private_owner);
//
//                return modelAndView;
//            }else{
//                //show profile
//                //redirect to mate view user page
//                //return new ModelAndView("redirect:/mate/user/"+user.getId()+"/view/");
//                myTruckList = truckService.getMyTrucks(privateOwner.getId(), "Private");
//                List<Trip> myTripList = new ArrayList<>();
//                List<Trip> myCompletedTripList = new ArrayList<>();
//                for (Truck truck : myTruckList) {
//                    for (Trip trip : tripList) {
//                        if(truck.getId().equals(trip.getTruck_id())){
//                            myTripList.add(trip);
//                            if(trip.getStatus().equals("End")) myCompletedTripList.add(trip);
//                            earning = earning.add(trip.getCharge()) ;
//                        }
//                    }
//
//
//                }
//                model.addAttribute("completedTripSize",myCompletedTripList.size());
//                model.addAttribute("truck_size", myTruckList.size());
//                model.addAttribute("myTruckList",myTruckList);
//                model.addAttribute("tripList",myTripList);
//                model.addAttribute("tripSize",myTripList.size());
//                model.addAttribute("earning",decimalFormatter.format(earning));
//                model.addAttribute("myTruckListSize",myTruckList.size());
//            }
//        } else if(role.contains("MERCHANT")){
//            Merchant merchant = merchantService.getUserProfileByEmail(email);
//
//            //then user doesnt have a prior profile
//            //create profile
//            User user = userService.getByEmail(email);
//            //show profile
//            //redirect to mate view user page
//            //return new ModelAndView("redirect:/mate/user/"+user.getId()+"/view/");
//            //myTruckList = truckService.getMyTrucks(privateOwner.getId(), "Private");
//            return new ModelAndView("redirect:/request/");
//
//        }
//
//    }


    @GetMapping("/login")
    public String viewLoginPage(){

        return "login";
    }

    @GetMapping("/register")
    public String viewRegPage(){

        return "registration";
    }

}

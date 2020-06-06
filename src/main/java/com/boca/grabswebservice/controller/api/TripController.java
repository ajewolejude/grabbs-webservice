package com.boca.grabswebservice.controller.api;


import com.boca.grabswebservice.model.Process;
import com.boca.grabswebservice.model.*;
import com.boca.grabswebservice.payload.trip.SingleTripResponse;
import com.boca.grabswebservice.payload.trip.TripResponse;
import com.boca.grabswebservice.security.JwtTokenProvider;
import com.boca.grabswebservice.service.*;
import com.boca.grabswebservice.utils.DateTimeConverter;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/trip")
public class TripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private ProcessService processService;

    @Autowired
    private TruckService truckService;

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private MateService mateService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private UserService userService;



    @Autowired
    private CompanyOwnerService companyOwnerService;

    @Autowired
    private PrivateOwnerService privateOwnerService;

    @Autowired
    private TruckTransactionService truckTransactionService;


    @ApiOperation(value = "Get Trips", response = ResponseEntity.class)
    @GetMapping("/all")
    public ResponseEntity<?> getUserTrips(@RequestHeader (name = "Authorization") String jwtToken){
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String role = jwtTokenProvider.getUserRolelFromJWT(jwtToken.substring(7));
        Long  id = jwtTokenProvider.getUserIdFromJWT(jwtToken.substring(7));
        String  email = jwtTokenProvider.getUserEmailFromJWT(jwtToken.substring(7));
        List<Trip> tripList = null;
        if(role.contains("ADMIN")){


            tripList = tripService.findAllByOrderByIdDesc();
        }else if(role.contains("DRIVER")){
            Driver currentDriver =driverService.getUserProfileByEmail(email);
            if( currentDriver!=null){
                tripList = tripService.getAllByDriver(currentDriver.getId());


            }

        } else if(role.contains("MATE")){

            Mate currentMate =mateService.getUserProfile(email);
            if( currentMate!=null){
                tripList = tripService.getAllByDriver(currentMate.getId());


            }

        } else if(role.contains("CORPORATE_TRUCK_OWNER")){
            if( companyOwnerService.getAllUserDetailsByEmail(email)!=null){
            }


        } else if(role.contains("PRIVATE_TRUCK_OWNER")){
            if( privateOwnerService.getUserProfileByEmail(email)!=null){
            }


        } else if(role.contains("MERCHANT")){


        }

        return ResponseEntity.ok(new TripResponse(tripList));

    }





    @ApiOperation(value = "Get details of a trip", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @GetMapping("/{id}/view")
    public ResponseEntity<?> getTrip( @RequestHeader (name = "Authorization") String jwtToken, @PathVariable Long id){
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        SingleTripResponse singleTripResponse = new SingleTripResponse();
        if(jwtTokenProvider.validateToken(jwtToken)){
            Trip trip = tripService.get(id);

            BeanUtils.copyProperties(trip, singleTripResponse);
        }

        return  ResponseEntity.ok(singleTripResponse);
    }



    @RequestMapping("/process")
    public String moveProcess(@ModelAttribute("process") Process process, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) throws ParseException {



        //old process, update date time (if thre is a procss)


        Process oldProcess = processService.getRecentProcessByTripId(process.getTrip().getId());
        System.out.println(oldProcess);
        Trip trip = tripService.get(oldProcess.getTrip().getId());

        if (oldProcess!=null){
            Date currentDate = new Date();
            DateTimeConverter dateTimeConverter = new DateTimeConverter();

            oldProcess.setDate_of_reg(currentDate);
            oldProcess.setDate_of_reg_string(dateTimeConverter.currentDateTime(currentDate));
            oldProcess.setStatus("Complete");
            oldProcess.setMode_of_transport(process.getMode_of_transport());




            String newStatus = "";


            if (oldProcess.getType().equals("Crossing")){
                newStatus = "Loading";
            }else if(oldProcess.getType().equals("Loading")){
                newStatus = "In-Transit";
            }else if(oldProcess.getType().equals("In-Transit")){
                newStatus = "Dropping";
            }else if(oldProcess.getType().equals("Dropping")){
                newStatus = "Returning";
            }else if(oldProcess.getType().equals("Returning")){
                newStatus = "End";
            }

            //new process
            Process newProcess = null;

            if(oldProcess.getType().equals("Returning")){
                 newProcess = new Process("", newStatus, trip, "Complete", currentDate,dateTimeConverter.currentDateTime(currentDate));
                 truckService.updateTripStatus("Idle", trip.getTruck_id());
            }else{
                 newProcess = new Process("", newStatus, trip, "In-Progress", null,"");

            }


            try {
                processService.save(oldProcess);
                processService.save(newProcess);
                trip.setStatus(newStatus);
                tripService.save(trip);
                redirectAttributes.addFlashAttribute("message", "success_trip_moved");
            } catch (Exception e) {
                System.out.println(e);
                redirectAttributes.addFlashAttribute("message", "failed");
            }

        }else{
            processService.save(new Process("", "Crossing", trip, "In-Progress", null,""));
        }




        return "redirect:/trip/"+ trip.getId()+"/view";
    }


    @RequestMapping("/new")
    public String showCreateTripForm(Model model){


        Trip trip = new Trip();
        trip.setStatus("not active");
        model.addAttribute("trip", trip);
        List<Truck> truckList = truckService.getAllIdleActiveTrucks();
        model.addAttribute("truckList",truckList);


        return "trip/new";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("trip") Trip trip, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) throws ParseException {


        if (bindingResult.hasErrors()) {
            return "/trip/new";
        }

        DateTimeConverter dateTimeConverter = new DateTimeConverter();
        Date currentDate  = new Date();


        trip.setDate_of_reg(currentDate);
        trip.setDeleted_by(0);
        trip.setDate_of_reg_string(dateTimeConverter.currentDateTime(currentDate));

        //get list of all free and active trucks

        List<Truck> truckList = truckService.getAllIdleActiveTrucks();

        if(trip.getTruck_id()==0){
            //then truck just created not assigned
            trip.setStatus("Unassigned");

            try {

                tripService.save(trip);
                redirectAttributes.addFlashAttribute("message", "success");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("message", "failed");
            }


        }else{
            //get truck
            Truck truck = truckService.get(trip.getTruck_id());

            //if truck is still free and active
            if(truckList.contains(truck)){

                TruckTransaction truckTransaction = truckTransactionService.getCurrentTruckTrans(trip.getTruck_id());

                if((truckTransaction.getDriver_id().equals(trip.getDriver_id())) && (truckTransaction.getMate_id().equals(trip.getMate_id())) ){
                    //all things be equal

                    try {

                            trip.setStatus("Crossing");
                            trip.setDate_of_assign(currentDate);
                            trip.setDate_of_assign_string(dateTimeConverter.currentDateTime(currentDate));

                        tripService.save(trip);
                            processService.save(new Process("", "Crossing", trip, "In-Progress", null,""));
                        truckService.updateTripStatus("Busy", trip.getTruck_id());
                        String comment = truck.getLicense_no() +" was assigned a trip";
                        historyService.save(new History("truck", "trip", trip.getTruck_id(), dateTimeConverter.currentDateTime(currentDate), currentDate, comment));

                        redirectAttributes.addFlashAttribute("message", "success");
                    } catch (Exception e) {
                        System.out.println(e);
                        redirectAttributes.addFlashAttribute("message", "failed");
                    }

                }else{
                    redirectAttributes.addFlashAttribute("message", "worker_not_assign_to_truck");
                }

            }else{

                redirectAttributes.addFlashAttribute("message", "truck_not_free");
            }
        }



        return "redirect:/trip/new";

    }



    @RequestMapping("/{id}/assign")
    public ModelAndView showAssignTripForm(@PathVariable(name = "id") int id){

        ModelAndView modelAndView = new ModelAndView("trip/assign");


        Trip trip = tripService.get(id);
        trip.setStatus("Unassigned");
        modelAndView.addObject("trip", trip);
        List<Truck> truckList = truckService.getAllIdleActiveTrucks();
        modelAndView.addObject("truckList",truckList);


        return modelAndView;
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String assign(@Valid @ModelAttribute("trip") Trip trip, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) throws ParseException {


        if (bindingResult.hasErrors()) {
            return "/trip/assign";
        }

        DateTimeConverter dateTimeConverter = new DateTimeConverter();
        Date currentDate  = new Date();


        trip.setDate_of_reg(currentDate);
        trip.setDeleted_by(0);
        trip.setDate_of_reg_string(dateTimeConverter.currentDateTime(currentDate));

        //get list of all free and active trucks

        List<Truck> truckList = truckService.getAllIdleActiveTrucks();

        if(trip.getTruck_id()==0){
            //then truck just created not assigned
            trip.setStatus("Unassigned");

            redirectAttributes.addFlashAttribute("message", "success_not_selected");


        }else{
            //get truck
            Truck truck = truckService.get(trip.getTruck_id());

            //if truck is still free and active
            if(truckList.contains(truck)){

                TruckTransaction truckTransaction = truckTransactionService.getCurrentTruckTrans(trip.getTruck_id());

                if((truckTransaction.getDriver_id().equals(trip.getDriver_id())) && (truckTransaction.getMate_id().equals(trip.getMate_id())) ){
                    //all things be equal

                    try {

                        trip.setStatus("Crossing");
                        trip.setDate_of_assign(currentDate);
                        trip.setDate_of_assign_string(dateTimeConverter.currentDateTime(currentDate));

                        tripService.save(trip);
                        processService.save(new Process("", "Crossing", trip, "In-Progress", null,""));

                        truckService.updateTripStatus("Busy", trip.getTruck_id());
                        String comment = truck.getLicense_no() +" was assigned a trip";
                        historyService.save(new History("truck", "trip", trip.getTruck_id(), dateTimeConverter.currentDateTime(currentDate), currentDate, comment));

                        redirectAttributes.addFlashAttribute("message", "success_assign");
                    } catch (Exception e) {
                        System.out.println(e);
                        redirectAttributes.addFlashAttribute("message", "failed");
                    }

                }else{
                    redirectAttributes.addFlashAttribute("message", "worker_not_assign_to_truck");
                }

            }else{

                redirectAttributes.addFlashAttribute("message", "truck_not_free");
            }
        }



        return "redirect:/trip/";

    }



    @RequestMapping("{id}/cancel")
    public String cancel(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) throws ParseException {
         Trip trip = tripService.get(id);
         trip.setStatus("Canceled");

        DateTimeConverter dateTimeConverter = new DateTimeConverter();
        Date currentDate  = new Date();

        //get list of all free and active trucks

        List<Truck> truckList = truckService.getAllIdleActiveTrucks();

        if(trip.getTruck_id()==0){
            //then truck just created not assigned

            try {

                tripService.save(trip);
                redirectAttributes.addFlashAttribute("message", "success_cancel");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("message", "failed");
            }


        }else {

            Truck truck = truckService.get(trip.getTruck_id());

            try {
                tripService.save(trip);
                truckService.updateTripStatus("Idle", trip.getTruck_id());
                String comment = truck.getLicense_no() + " was assigned a trip";
                historyService.save(new History("truck", "trip", trip.getTruck_id(), dateTimeConverter.currentDateTime(currentDate), currentDate, comment));

                redirectAttributes.addFlashAttribute("message", "success_cancel");
            } catch (Exception e) {
                System.out.println(e);
                redirectAttributes.addFlashAttribute("message", "failed");
            }

        }



        return "redirect:/trip/";

    }







}

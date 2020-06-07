package com.boca.grabswebservice.controller.api;

import com.boca.grabswebservice.dom.Dashboard;
import com.boca.grabswebservice.model.*;
import com.boca.grabswebservice.payload.TruckResponse;
import com.boca.grabswebservice.security.JwtTokenProvider;
import com.boca.grabswebservice.service.*;
import com.boca.grabswebservice.service.dashboard.QueryService;
import com.boca.grabswebservice.utils.DateTimeConverter;
import com.boca.grabswebservice.utils.DecimalFormatter;
import com.boca.grabswebservice.utils.UserAuth;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/truck")
public class TruckController implements WebMvcConfigurer {

    @Autowired
    private TruckService truckService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private MateService mateService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private TyreService tyreService;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyOwnerService companyOwnerService;

    @Autowired
    private PrivateOwnerService privateOwnerService;

    @Autowired
    private AccesoryAggService accesoryAggService;

    @Autowired
    private AccesoryService accesoryService;

    @Autowired
    private TruckTransactionService truckTransactionService;

    @Autowired
    private TripService tripService;


    @Autowired
    private QueryService queryService;

    @Autowired
    private HistoryService historyService;

    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        //The date format to parse or output your dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Create a new CustomDateEditor
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        //Register it as custom editor for the Date type
        binder.registerCustomEditor(Date.class, editor);
    }

    private static final Logger logger = LoggerFactory.getLogger(TruckController.class);


    @ApiOperation(value = "Get Trucks", response = ResponseEntity.class)
    @GetMapping("/")
    public ResponseEntity<?> getUserTrucks(@RequestHeader (name = "Authorization") String jwtToken){
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String role = jwtTokenProvider.getUserRolelFromJWT(jwtToken.substring(7));
        Long  id = jwtTokenProvider.getUserIdFromJWT(jwtToken.substring(7));
        String  email = jwtTokenProvider.getUserEmailFromJWT(jwtToken.substring(7));
        List<Truck> truckList  = null;
        if(role.contains("ADMIN")){

             truckList = truckService.findAllByOrderByIdDesc();

        }else if(role.contains("DRIVER")){
            Driver currentDriver =driverService.getUserProfileByEmail(email);
            if( currentDriver!=null){

               // truckList = truckService.findAllByOrderByIdDesc();

            }

        } else if(role.contains("MATE")){

            Mate currentMate =mateService.getUserProfile(email);
            if( currentMate!=null){
                //tripList = tripService.getAllByDriver(currentMate.getId());


            }

        } else if(role.contains("CORPORATE_TRUCK_OWNER")){
            CompanyOwner companyOwner = companyOwnerService.getAllUserDetailsByEmail(email);
            if( companyOwner!=null){
                truckList = truckService.getMyTrucks(companyOwner.getId(), "Corporate");
            }


        } else if(role.contains("PRIVATE_TRUCK_OWNER")){
            PrivateOwner privateOwner = privateOwnerService.getUserProfileByEmail(email);
            if( privateOwner!=null){
            }


        } else if(role.contains("MERCHANT")){


        }

        return ResponseEntity.ok(new TruckResponse(truckList));

    }




    @RequestMapping("/n")
    public ModelAndView viewHome(Model model){

        List<Truck> truckList = truckService.findAllByOrderByIdDesc();


        DecimalFormatter decimalFormatter = new DecimalFormatter();


        BigDecimal earning = new BigDecimal(0);


        List<Truck> myTruckList = new ArrayList<>();


        UserAuth userAuth = new UserAuth();
        String email = userAuth.getEmail();
        String role = userAuth.getRole();
        if(role.contains("ADMIN")){

            List<Trip> tripList = tripService.findAllByOrderByIdDesc();
            Dashboard dasboard = queryService.getAdminDetails();
            model.addAttribute("driverSize", dasboard.getDriverCount());
            model.addAttribute("completedTripSize", dasboard.getCompletedTripCount());
            model.addAttribute("mateSize", dasboard.getMateCount());
            model.addAttribute("truck_size", dasboard.getTruckCount());
            model.addAttribute("myTruckList",myTruckList);
            model.addAttribute("tripList",tripList);
            model.addAttribute("tripSize",tripList.size());
            model.addAttribute("earning",decimalFormatter.format(dasboard.getEarning()));
            model.addAttribute("myTruckListSize",myTruckList.size());
            model.addAttribute("truck_size", truckList.size());
            model.addAttribute("truckList",truckList);

        }else if(role.contains("CORPORATE_TRUCK_OWNER")){
            CompanyOwner companyOwner = companyOwnerService.getAllUserDetailsByEmail(email);
            if (companyOwner==null){
                //then user doesnt have a prior profile
                //create profile

                User user = userService.getUserByUsername(email);
                ModelAndView modelAndView = new ModelAndView("owner/company/new");
                modelAndView.addObject("message","profile");
                CompanyOwner company_owner = new CompanyOwner();
                company_owner.setContact_first_name(user.getFirstName());
                company_owner.setContact_last_name(user.getLastName());
                company_owner.setContact_email(user.getEmail());
                modelAndView.addObject("company_owner", company_owner);
                return modelAndView;
            }else{
                //show profile
                //redirect to mate view user page
                //return new ModelAndView("redirect:/mate/user/"+user.getId()+"/view/");
                myTruckList = truckService.getMyTrucks(companyOwner.getId(), "Corporate");

                model.addAttribute("myTruckList",myTruckList);
            }
        } else if(role.contains("PRIVATE_TRUCK_OWNER")){
            PrivateOwner privateOwner = privateOwnerService.getUserProfileByEmail(email);
            if (privateOwner==null){
                //then user doesnt have a prior profile
                //create profile
                User user = userService.getUserByUsername(email);

                ModelAndView modelAndView = new ModelAndView("owner/private/new");
                modelAndView.addObject("message","profile");
                PrivateOwner private_owner = new PrivateOwner();
                private_owner.setFirst_name(user.getFirstName());
                private_owner.setLast_name(user.getLastName());
                private_owner.setEmail(user.getEmail());
                modelAndView.addObject("private_owner", private_owner);

                return modelAndView;
            }else{
                //show profile
                //redirect to mate view user page
                //return new ModelAndView("redirect:/mate/user/"+user.getId()+"/view/");
                List<Trip> tripList = tripService.findAllByOrderByIdDesc();
                myTruckList = truckService.getMyTrucks(privateOwner.getId(), "Private");

                model.addAttribute("myTruckList",myTruckList);
                model.addAttribute("earning",decimalFormatter.format(earning));
                model.addAttribute("myTruckListSize",myTruckList.size());
            }

        }


        return new ModelAndView("truck/index");
    }
    @RequestMapping("/new")
    public String viewNewFormPage(Model model) {
        Truck truck = new Truck();
        truck.setStatus("not active");
        model.addAttribute("truck", truck);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().toString();
        String email = authentication.getName();
        User user = userService.getUserByUsername(email);
//        CompanyOwner companyOwner = companyOwnerService.getOneCompanyOwner(user.getId());
//        PrivateOwner privateOwner = privateOwnerService.getOnePrivateOwner(user.getId());

        if (role.contains("PRIVATE")) {
            truck.setOwner_role("Private");
            truck.setOwner_id(user.getId());
        } else if (role.contains("CORPORATE")) {
            truck.setOwner_role("Corporate");
            truck.setOwner_id(user.getId());
        }
        return "truck/new_truck";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("truck") Truck truck, BindingResult bindingResult, @RequestParam("files") MultipartFile[] files, RedirectAttributes redirectAttributes, Model model) throws ParseException {

        List<Object> fileDownloadUrls = new ArrayList<>();


        //User user = userService.getByEmail(email);


        if (bindingResult.hasErrors()) {
            return "/truck/new_truck";
        }

        if (truckService.isLicenseExisting(truck.getLicense_no())) {
            model.addAttribute("message", "duplicate_license");
            return "/truck/new_truck";
        }

        List<Tyre> tyres = truck.getTyre();
        for (Tyre tyre : tyres) {
            tyre.setTruck(truck);
        }

        truck.setTyre(tyres);


        DateTimeConverter dateTimeConverter = new DateTimeConverter();
        truck.setDate_of_heavy_duty_expiry_string(dateTimeConverter.convertDate(truck.getDate_of_heavy_duty_expiry().toString()));
        truck.setDate_of_heavy_duty_issuance_string(dateTimeConverter.convertDate(truck.getDate_of_heavy_duty_issuance().toString()));

        truck.setDate_of_vehicle_license_expiry_string(dateTimeConverter.convertDate(truck.getDate_of_vehicle_license_expiry().toString()));
        truck.setDate_of_vehicle_license_issuance_string(dateTimeConverter.convertDate(truck.getDate_of_vehicle_license_issuance().toString()));

        truck.setDeleted_by(0);

        truck.setDate_of_reg(new Date());
        truck.setDate_of_reg_string(dateTimeConverter.currentDateTime(truck.getDate_of_reg()));
        try {
            //save truck non-media data
            truckService.save(truck);
            try {
//                //loop through media files
//                for (MultipartFile file : files) {
//                    String[] document_details = documentService.uploadFile(file, "truck");
//                    //save media file details in database
//                    Document document = new Document("truck",truck.getId(), document_details[2], document_details[1], document_details[0]);
//                    documentService.save(document);
//                }
            } catch (Exception e) {
            }

            redirectAttributes.addFlashAttribute("message", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "failed");
        }

        return "redirect:/truck/new";

    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditTruckForm(@PathVariable(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("truck/edit_truck");

        Truck truck = truckService.get(id);
        modelAndView.addObject("truck", truck);

        return modelAndView;
    }

    @RequestMapping("/view/{id}")
    public String showTruck(@PathVariable(name = "id") int id, Model model) {

        Truck truck = truckService.get(id);
        //List<Document> documentList = documentService.getDocuments((long) id, "truck");
        List<Tyre> tyreList = tyreService.getTyres((long) id);
        model.addAttribute("truck", truck);
        //model.addAttribute("documentList", documentList);
        model.addAttribute("tyreList", tyreList);
        TruckTransaction truck_trans = truckTransactionService.getCurrentTruckTrans((long) id);
        if (truck_trans != null) {
            Driver driver = driverService.get(truck_trans.getDriver_id());
            Mate mate = mateService.get(truck_trans.getMate_id());

            List<Accessory> accessoryList = accesoryService.getTruckAccessory(truck_trans.getTruck_id());
            for (Accessory accessory : accessoryList) {
                accessory.setQuantity(-accessory.getQuantity());
            }
            model.addAttribute("accessoryList", accessoryList);
            model.addAttribute("driver", driver.getFirst_name() + " " + driver.getLast_name());
            model.addAttribute("mate", mate.getFirst_name() + " " + mate.getLast_name());
            model.addAttribute("date_of_trans", truck_trans.getDate_of_trans_string());
        }


        return "truck/view_truck";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("truck") Truck truck, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws ParseException {

        truck.setDeleted_by(truck.getDeleted_by());
        if (bindingResult.hasErrors()) {
            return "/truck/edit_truck";
        }

        List<Tyre> tyres = truck.getTyre();
        for (Tyre tyre : tyres) {
            tyre.setStatus("active");
            tyre.setTruck(truck);
        }

        truck.setTyre(tyres);

        DateTimeConverter dateTimeConverter = new DateTimeConverter();
        truck.setDate_of_heavy_duty_expiry_string(dateTimeConverter.convertDate(truck.getDate_of_heavy_duty_expiry().toString()));
        truck.setDate_of_heavy_duty_issuance_string(dateTimeConverter.convertDate(truck.getDate_of_heavy_duty_issuance().toString()));

        truck.setDate_of_vehicle_license_expiry_string(dateTimeConverter.convertDate(truck.getDate_of_vehicle_license_expiry().toString()));
        truck.setDate_of_vehicle_license_issuance_string(dateTimeConverter.convertDate(truck.getDate_of_vehicle_license_issuance().toString()));

        try {
            truckService.save(truck);
            redirectAttributes.addFlashAttribute("message", "success_edit");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "failed");
        }


        return "redirect:/";

    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") int id, Integer deleted_by, RedirectAttributes redirectAttributes) {

        deleted_by = 5;
        truckService.softDelete(deleted_by, id);

        redirectAttributes.addFlashAttribute("message", "success_delete");
        return "redirect:/";
    }


    @GetMapping("/download/{filename}")
    public void downloadFile(@PathVariable String filename, HttpServletResponse response) throws IOException {

//        filename = filename.replace(" ", "_");
//        File file = documentService.downloadFile(filename);
//        response.setContentType(new MimetypesFileTypeMap().getContentType(file));
//        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
//        BufferedInputStream inStrem = new BufferedInputStream(new FileInputStream(file));
//        BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());
//
//        byte[] buffer = new byte[1024];
//        int bytesRead = 0;
//        while ((bytesRead = inStrem.read(buffer)) != -1) {
//            outStream.write(buffer, 0, bytesRead);
//        }
//        outStream.flush();
//        inStrem.close();
    }

    @RequestMapping("/oil/{id}")
    public String showOilChangePage(@PathVariable(name = "id") int id, Model model) {
        Truck truck = truckService.get(id);
        model.addAttribute("truck", truck);
        History history = new History();
        history.setType_id((long) id);
        model.addAttribute("sub_type", "oil");
        model.addAttribute("history", history);

        return "history/truck";
    }


    @RequestMapping("/tyre/{id}")
    public String showTyreChangePage(@PathVariable(name = "id") int id, Model model) {
        Truck truck = truckService.get(id);
        model.addAttribute("truck", truck);
        List<Tyre> tyreList = tyreService.getTyres((long) id);
        List<Tyre> tyreListFree = tyreService.getTyresByStatus("inactive");
        History history = new History();
        history.setType_id((long) id);
        model.addAttribute("sub_type", "tyre");
        model.addAttribute("history", history);
        model.addAttribute("tyreList", tyreList);
        model.addAttribute("tyreListFree", tyreListFree);

        return "history/truck";
    }

    @RequestMapping("/history/{id}")
    public String showTruckHistory(@PathVariable(name = "id") int id, Model model) {

        Truck truck = truckService.get(id);
        List<History> repairHistoryList = historyService.getHistoryByRepair((long) id, "truck", "oil", "tyre");
        List<History> assignHistoryList = historyService.getHistoryBySubtype((long) id, "truck","assign");
        model.addAttribute("truck", truck);
        model.addAttribute("assignHistoryList", assignHistoryList);
        model.addAttribute("repairHistoryList", repairHistoryList);

        return "history/view_truck";
    }


    @RequestMapping(value = "/tyre/change", method = RequestMethod.POST)
    public String changeTyre(@ModelAttribute("truck") Truck truck, RedirectAttributes redirectAttributes) {

        DateTimeConverter dateTimeConverter = new DateTimeConverter();

        List<Tyre> tyres = truck.getTyre();
        List<Tyre> tyresFinal = new ArrayList<>();
        String comment = "";
        for (int i = 0; i < tyres.size(); i++) {
            Tyre tyre = tyres.get(i);

            if (tyre.getId() != null) {
                if (tyres.get(i + 4).getSerial_number().equals("")) {
                    tyre.setStatus("active");
                    tyre.setTruck(truck);
                    tyresFinal.add(tyre);
                } else {
                    tyre.setStatus("inactive");
                    tyre.setTruck(null);
                    tyresFinal.add(tyre);
                }
            } else {

                if (!tyres.get(i).getSerial_number().equals("")) {
                    String[] word = tyre.getSerial_number().split("/");
                    Long id = Long.valueOf(word[1]);
                    String serial_number = word[0];
                    String size = word[2];
                    tyre.setSerial_number(serial_number);
                    tyre.setId(id);
                    tyre.setSize(size);
                    tyre.setTruck(truck);

                    tyre.setStatus("active");
                    tyresFinal.add(tyre);
                    comment = "tyre " + tyres.get(i - 4).getSerial_number() + " was replaced with " + serial_number;
                    Date currentDate = new Date();
                    historyService.save(new History("truck", "tyre", truck.getId(), dateTimeConverter.currentDateTime(currentDate), currentDate, comment));


                }

            }

        }


        truck.setTyre(tyresFinal);
        try {
            truckService.save(truck);
            redirectAttributes.addFlashAttribute("message", "success_tyre_change");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "failed");
        }


        return "redirect:/";

    }

    @RequestMapping(value = "/{id}/assign", method = RequestMethod.GET)
    public String assignTruck(@PathVariable(name = "id") int id, Model model) {

        //save to truck_transaction, save mate and truck id
        Truck truck = truckService.get(id);
        List<AccessoryAgg> accessoryAggList = accesoryAggService.getAll();
        List<Driver> driverList = driverService.getAllByStatus("unassigned");
        List<Mate> mateList = mateService.getAllByStatus("unassigned");
        List<History> historyList = historyService.getHistory(id, "truck");
        TruckTransaction truck_trans = new TruckTransaction();
        truck_trans.setTruck_id((long) id);
        model.addAttribute("truck_trans", truck_trans);
        model.addAttribute("mate", truck);
        model.addAttribute("historyList", historyList);
        model.addAttribute("accessoryAggList", accessoryAggList);
        model.addAttribute("driverList", driverList);
        model.addAttribute("mateList", mateList);

        return "truck/assign_truck";
    }

    @RequestMapping("/{id}/unassign")
    public String unassignTruck(@PathVariable(name = "id") int id, RedirectAttributes redirectAttributes) {

        TruckTransaction truck_trans = truckTransactionService.getCurrentTruckTrans((long) id);
        Driver driver = driverService.get(truck_trans.getDriver_id());
        Mate mate = mateService.get(truck_trans.getMate_id());
        DateTimeConverter dateTimeConverter = new DateTimeConverter();
        Date currentDate = new Date();

        try {
            String comment = "truck was unassigned from  " + driver.getFirst_name() + " (Driver) and " + mate.getFirst_name() + " (Mate)";
            historyService.save(new History("truck", "assign", truck_trans.getTruck_id(), dateTimeConverter.currentDateTime(currentDate), currentDate, comment));
            driverService.updateStatus("unassigned", driver.getId());
            mateService.updateStatus("unassigned", mate.getId());
            truckService.updateStatus("not active", truck_trans.getTruck_id());
            redirectAttributes.addFlashAttribute("message", "success_unassign");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "failed");
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/assign", method = RequestMethod.POST)
    public String assignTruck(@Valid @ModelAttribute("truck_trans") TruckTransaction truck_trans, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        System.out.println(truck_trans.toString());
        if (bindingResult.hasErrors()) {
            return "/truck/assign_truck";
        }

        DateTimeConverter dateTimeConverter = new DateTimeConverter();

        List<Accessory> accessoryList = truck_trans.getAccessory();

        List<Accessory> accessoryFinalList = new ArrayList<>();

        Date currentDate = new Date();

        for (int i = 0; i < accessoryList.size(); i++) {
            Accessory accessory = accessoryList.get(i);
            if (accessory.getQuantity() != null) {
                AccessoryAgg currentAccessory = accesoryAggService.getAllById(i + 1);


                System.out.println(currentAccessory.getId());
                if (currentAccessory.getAgg() - accessory.getQuantity() < 0) {
                    redirectAttributes.addFlashAttribute("message", "not_enough_accessory");
                    return "redirect:/truck/" + truck_trans.getTruck_id() + "/assign";
                } else {
                    accessory.setTruckTransaction(truck_trans);
                    accessory.setTruck_id(truck_trans.getTruck_id());
                    accessory.setUser_id(0l);
                    accessory.setUser_role("admin");
                    accessory.setType(currentAccessory.getType());
                    accessory.setDate_of_reg(currentDate);
                    accessory.setQuantity(-accessory.getQuantity());
                    accessoryFinalList.add(accessory);
                    accesoryAggService.updateById((i + 1), currentAccessory.getAgg() + accessory.getQuantity());
                }
            }

        }

        truck_trans.setDate_of_trans_string(dateTimeConverter.currentDateTime(currentDate));
        truck_trans.setDate_of_trans(currentDate);
        truck_trans.setAccessory(accessoryFinalList);

        Driver driver = driverService.get(truck_trans.getDriver_id());
        Mate mate = mateService.get(truck_trans.getMate_id());

        try {
            truckTransactionService.save(truck_trans);
            String comment = "truck was assigned to  " + driver.getFirst_name() + " (Driver) and " + mate.getFirst_name() + " (Mate)";
            historyService.save(new History("truck", "assign", truck_trans.getTruck_id(), dateTimeConverter.currentDateTime(currentDate), currentDate, comment));
            driverService.updateStatus("assigned", driver.getId());
            mateService.updateStatus("assigned", mate.getId());
            truckService.updateStatus("active", truck_trans.getTruck_id());
            redirectAttributes.addFlashAttribute("message", "success_assign");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "failed");
        }


        return "redirect:/";
    }


    @RequestMapping(value = "/{id}/workers", method = RequestMethod.GET)
    public ResponseEntity<TruckTransaction> getTruckWorker(@PathVariable Long id) {
        TruckTransaction truckTransaction = truckTransactionService.getCurrentTruckTrans(id);
        return ResponseEntity.ok(truckTransaction);
    }

    @RequestMapping(value = "/{id}/mate", method = RequestMethod.GET)
    public ResponseEntity<Mate> getTruckMate(@PathVariable Long id) {
        Mate mate = mateService.get(id);
        return ResponseEntity.ok(mate);
    }

    @RequestMapping(value = "/{id}/driver", method = RequestMethod.GET)
    public ResponseEntity<Driver> getTruckDriver(@PathVariable Long id) {
        Driver driver = driverService.get(id);
        return ResponseEntity.ok(driver);
    }

}

//    @PostMapping("/multi-upload")
//    public ResponseEntity multiUpload(@RequestParam("files") MultipartFile[] files) {
//        List<Object> fileDownloadUrls = new ArrayList<>();
//        Arrays.asList(files)
//                .stream()
//                .forEach(file -> fileDownloadUrls.add(uploadToLocalFileSystem(file).getBody()));
//        return ResponseEntity.ok(fileDownloadUrls);
//    }


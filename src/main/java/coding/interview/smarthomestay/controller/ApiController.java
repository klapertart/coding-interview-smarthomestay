package coding.interview.smarthomestay.controller;

import coding.interview.smarthomestay.data.*;
import coding.interview.smarthomestay.entity.*;
import coding.interview.smarthomestay.message.ResponseMessageError;
import coding.interview.smarthomestay.message.ResponseMessageGeneric;
import coding.interview.smarthomestay.message.ResponseMessageInfo;
import coding.interview.smarthomestay.message.ResponseMessageSuccess;
import coding.interview.smarthomestay.service.*;
import coding.interview.smarthomestay.util.Common;
import coding.interview.smarthomestay.util.DateUtil;
import coding.interview.smarthomestay.validation.UserValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@RestController
@Validated
@RequestMapping(path = "/api")
@Slf4j
public class ApiController {

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private FacilitiesService facilitiesService;

    @Autowired
    private AdditionalFacilitiesService addFacilitiesService;

    @Autowired
    private GuestService guestService;

    @Autowired
    private CheckinService checkinService;

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private PaymentService paymentService;


    @GetMapping(path = "/")
    public String welcome(){
        return "Smart Home Stay API";
    }


    /**
     * User register
     */
    @PostMapping(path = "/v1/user/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessageGeneric> registerUser(@Valid @RequestBody User user){

        // validasi gender
        if (!UserValidation.isGenderValid(user)){
            Map<String, String> errors = new HashMap<>();

            errors.put("gender","invalid value (use MALE / FEMALE)");

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessageError(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.name(),errors));
        }

        // create entity
        UserEntity userEntity = new UserEntity();
        userEntity.setFullName(user.getFullName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setGender((user.getGender().toUpperCase().equals("MALE")) ? Gender.MALE : Gender.FEMALE);
        userEntity.setPhone (user.getPhone());
        userEntity.setBirthDate(user.getBirthDate());

        // store into db
        userService.save(userEntity);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseMessageGeneric(HttpStatus.OK.value(),HttpStatus.OK.name()));
    }

    /**
     * Check by email, is user exist
     */
    @GetMapping(path = "/v1/user/find/email/{email}")
    public ResponseEntity<ResponseMessageGeneric> getUserByEmail(@PathVariable String email){
        List<UserEntity> userEntityList = new ArrayList<>();

        UserEntity userEntity = userService.findByEmail(email);
        if (userEntity != null){
            userEntityList.add(userEntity);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseMessageSuccess<UserEntity>(HttpStatus.OK.value(),HttpStatus.OK.name(),userEntityList));
        }else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessageGeneric(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name()));
        }

    }

    /**
     * Get All type of room
     */
    @GetMapping(path = "/v1/room")
    public ResponseEntity<ResponseMessageGeneric> getRoomType(){
        List<RoomTypeEntity> roomTypeEntityList = roomTypeService.getAllRoomType();

        if (roomTypeEntityList.size() != 0){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseMessageSuccess<RoomTypeEntity>(HttpStatus.OK.value(),HttpStatus.OK.name(), roomTypeEntityList));
        }else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessageGeneric(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name()));
        }
    }

    /**
     * Check by id, is room exist
     */
    @GetMapping(path = "/v1/room/find/id/{id}")
    public ResponseEntity<ResponseMessageGeneric> getRoomById(@PathVariable String id){
        List<RoomTypeEntity> roomTypeEntityList = new ArrayList<>();

        RoomTypeEntity roomTypeEntity = roomTypeService.findById(id);
        if (roomTypeEntity != null){
            roomTypeEntityList.add(roomTypeEntity);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseMessageSuccess<RoomTypeEntity>(HttpStatus.OK.value(),HttpStatus.OK.name(),roomTypeEntityList));
        }else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessageGeneric(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name()));
        }

    }

    /**
     * Apply Reservation
     */
    @PostMapping(path = "/v1/reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessageGeneric> applyReservation(@Valid @RequestBody Reservation reservation) throws ParseException {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(reservation.getUserId());

        RoomTypeEntity roomTypeEntity = new RoomTypeEntity();
        roomTypeEntity.setId(reservation.getRoomType());

        // get duration
        int duration = Integer.valueOf(reservation.getDuration());

        // calculate total payment
        RoomTypeEntity roomType = roomTypeService.findById(reservation.getRoomType());
        double totalPayment = roomType.getPrice() * duration;

        // generate reservation id
        String reservationCode = Common.getReservationCode();

        // set reservation
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setId(reservationCode);
        reservationEntity.setUserEntity(userEntity);
        reservationEntity.setRoomTypeEntity(roomTypeEntity);
        reservationEntity.setCheckinDate(DateUtil.strToDate(reservation.getCheckinDate()));
        reservationEntity.setCheckoutDate(DateUtil.plusDay(reservation.getCheckinDate(),duration));
        reservationEntity.setDuration(duration);
        reservationEntity.setReservationTimeStamp(new Date());
        reservationEntity.setTotalPayment(totalPayment);

        // set additional facilities
        List<AdditionalFacilitiesEntity> addFacilitiesEntityList = new ArrayList<>();
        if(reservation.getAdditionalFacilities1() != null){
            addFacilitiesEntityList.add(addList(reservation.getAdditionalFacilities1(),reservationEntity));
        }
        if(reservation.getAdditionalFacilities2() != null){
            addFacilitiesEntityList.add(addList(reservation.getAdditionalFacilities2(),reservationEntity));
        }
        if(reservation.getAdditionalFacilities3() != null){
            addFacilitiesEntityList.add(addList(reservation.getAdditionalFacilities3(),reservationEntity));
        }
        if(reservation.getAdditionalFacilities4() != null){
            addFacilitiesEntityList.add(addList(reservation.getAdditionalFacilities4(),reservationEntity));
        }

        // set payment
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setReservationEntity(reservationEntity);
        paymentEntity.setPaymentAmount(totalPayment);
        paymentEntity.setPaymentFlag(PaymentFlag.UNPAID);

        // store into db
        reservationService.createReservation(reservationEntity,paymentEntity,addFacilitiesEntityList);

        Map<String,String> info = new HashMap<>();
        info.put("Reservation Code",reservationCode);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseMessageInfo(HttpStatus.OK.value(), HttpStatus.OK.name(), info));
    }

    private AdditionalFacilitiesEntity addList(String additionFalitiesId, ReservationEntity reservationEntity){
        FacilitiesEntity facilitiesEntity = new FacilitiesEntity();
        facilitiesEntity.setId(additionFalitiesId);

        AdditionalFacilitiesEntity facilities = new AdditionalFacilitiesEntity();
        facilities.setReservationEntity(reservationEntity);
        facilities.setFacilitiesEntity(facilitiesEntity);
        facilities.setOtherFacilities(null);

        return facilities;
    }

    /**
     * Check is reservation exist
     */
    @GetMapping(path = "/v1/reservation/check/{id}")
    public ResponseEntity<ResponseMessageGeneric> isReservationExist(@PathVariable String id){
        if (reservationService.isPresent(id)){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseMessageGeneric(HttpStatus.OK.value(),HttpStatus.OK.name()));
        }else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessageGeneric(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name()));
        }

    }

    /**
     * CheckIn
     */
    @PostMapping(path = "/v1/checkin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessageGeneric> getCheckin(@Valid @RequestBody Checkin checkin) throws ParseException {

        GuestEntity guestEntity = new GuestEntity();
        guestEntity.setNoIdentity(checkin.getNoIdentity());
        guestEntity.setName(checkin.getFullName());
        guestEntity.setPhone(checkin.getPhone());
        guestEntity.setEmail(checkin.getEmail());
        guestEntity.setAddress(checkin.getAddress());

        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setId(checkin.getReservationId());

        CheckinEntity checkinEntity = new CheckinEntity();
        checkinEntity.setGuestEntity(guestEntity);
        checkinEntity.setReservationEntity(reservationEntity);
        checkinEntity.setCheckinTimestamp(new Date());

        checkinService.saveGuestAndCheckin(guestEntity,checkinEntity);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseMessageGeneric(HttpStatus.OK.value(),HttpStatus.OK.name()));
    }


    /**
     * CheckOut
     */
    @PostMapping(path = "/v1/checkout", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessageGeneric> getCheckout(@Valid @RequestBody Checkout checkout) throws ParseException {

        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setId(checkout.getReservationId());

        CheckoutEntity checkoutEntity = new CheckoutEntity();
        checkoutEntity.setReservationEntity(reservationEntity);
        checkoutEntity.setCheckoutTimestamp(new Date());

        checkoutService.save(checkoutEntity);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseMessageGeneric(HttpStatus.OK.value(),HttpStatus.OK.name()));
    }

    /**
     * Get all facilities (additional)
     */
    @GetMapping(path = "/v1/facilities")
    public ResponseEntity<ResponseMessageGeneric> getAllFacilities(){
        List<FacilitiesEntity> facilitiesEntityList = facilitiesService.getAllFacilities();

        if (facilitiesEntityList.size() != 0){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseMessageSuccess<FacilitiesEntity>(HttpStatus.OK.value(),HttpStatus.OK.name(), facilitiesEntityList));
        }else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessageGeneric(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name()));
        }
    }

    /**
     * inquiry
     */
    @GetMapping(path = "/v1/payment/inquiry/{id}")
    public ResponseEntity<ResponseMessageGeneric> getInqury(@PathVariable String id){
        PaymentEntity paymentEntity = paymentService.getInquiry(id);

        if (paymentEntity != null){
            List<PaymentEntity> paymentEntityList = new ArrayList<>();
            paymentEntityList.add(paymentEntity);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseMessageSuccess<PaymentEntity>(HttpStatus.OK.value(),HttpStatus.OK.name(),paymentEntityList));
        }else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessageGeneric(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name()));
        }

    }

    /**
     * Payment
     */
    @PostMapping(path = "/v1/payment/payout", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessageGeneric> getPayment(@Valid @RequestBody Payment payment){
        Map<String,String> errors = new HashMap<>();

        // chek reservation id
        if(!reservationService.isPresent(payment.getReservationId())){
            errors.put("reservationId","Unregistered ID");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessageError(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.name(),errors));

        }

        // get payment
        PaymentEntity paymentEntity = paymentService.getInquiry(payment.getReservationId());

        if (paymentEntity == null){
            errors.put("payment flag","payment has been paid");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessageError(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.name(),errors));
        }

        // check payment amount
        double paymentAmount = Double.valueOf(payment.getAmount());
        if(paymentAmount < paymentEntity.getPaymentAmount()){
            errors.put("amount","Smaller than should paid");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessageError(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.name(),errors));
        } else if(paymentAmount > paymentEntity.getPaymentAmount()){
            errors.put("amount","Bigger than should paid");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessageError(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.name(),errors));
        }

        // update payment flag
        paymentService.paymentPaid(paymentEntity.getId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseMessageGeneric(HttpStatus.OK.value(),HttpStatus.OK.name()));
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseMessageError handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseMessageError(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                errors
        );
    }


}

package com.spring.booking;

import com.spring.booking.model.*;
import com.spring.booking.repository.RoleRepository;
import com.spring.booking.repository.UserRepository;
import com.spring.booking.repository.UserRoleRepository;
import com.spring.booking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    private UserRepository userRepository;
    private UserService userService;
    private RoleService roleService;
    private RoleRepository roleRepository;
    private UserRoleRepository userRoleRepository;

    private HotelService hotelService;
    private RoomService roomService;
    private ReservationService reservationService;
    private RoomReservationService roomReservationService;

    @Autowired
    public Runner(UserRepository userRepository, UserService userService,
                  RoleService roleService, RoleRepository roleRepository,
                  UserRoleRepository userRoleRepository, HotelService hotelService,
                  RoomService roomService, ReservationService reservationService,
                  RoomReservationService roomReservationService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.hotelService = hotelService;
        this.roomService = roomService;
        this.reservationService = reservationService;
        this.roomReservationService = roomReservationService;
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        dbInit();
    }

    public void dbInit() {

        User user1 = new User("User1",
                "$2a$12$sJZ7/SZpCOTSeMg1jos87ulm.OcN31uQKnYisY/5r5XlNXSoQKPRi");//pas1
        User user2 = new User("User2",
                "$2a$12$gaUsXx4r4JlzHYXomu/XguBgQZbj2XXOWq5h683u7KCOPUozoRy56");//pas2
        User user3 = new User("User3",
                "$2a$12$b/jcMc9LC8sTk.8mV6Mzv.0GFCqgD7O/oK6m96nojFVKuGj8LlJv2");//pas3

        User savedUser1 = userRepository.save(user1);
        User savedUser2 = userRepository.save(user2);
        User savedUser3 = userRepository.save(user3);

        Role admin = new Role(RoleType.ROLE_ADMIN);
        Role client = new Role(RoleType.ROLE_CLIENT);

        Role savedAdmin = roleRepository.save(admin);
        Role savedClient = roleRepository.save(client);

        UserRole userRole1 = new UserRole(savedUser1, savedAdmin, LocalDateTime.now());
        UserRole userRole2 = new UserRole(savedUser2, savedClient, LocalDateTime.now());
        UserRole userRole3 = new UserRole(savedUser3, savedClient, LocalDateTime.now());
        UserRole userRole4 = new UserRole(savedUser3, savedAdmin, LocalDateTime.now());

        savedUser1.getUserRoles().add(userRole1);
        savedUser2.getUserRoles().add(userRole2);
        savedUser3.getUserRoles().add(userRole3);
        savedUser3.getUserRoles().add(userRole4);

        savedAdmin.getUserRoles().add(userRole1);
        savedAdmin.getUserRoles().add(userRole3);
        savedClient.getUserRoles().add(userRole2);
        savedClient.getUserRoles().add(userRole3);

        userRoleRepository.save(userRole1);
        userRoleRepository.save(userRole2);
        userRoleRepository.save(userRole3);
        userRoleRepository.save(userRole4);


        Hotel hotel1 = new Hotel();
        Hotel hotel2 = new Hotel();
        hotel1.setName("Hotel1");
        hotel2.setName("Hotel2");
        Hotel savedHotel1 = hotelService.saveHotel(hotel1);
        Hotel savedHotel2 = hotelService.saveHotel(hotel2);

        Room roomH1R1 = new Room(savedHotel1, "roomH1R1", 2);
        Room roomH1R2 = new Room(savedHotel1, "roomH1R2", 2);
        Room roomH1R3 = new Room(savedHotel1, "roomH1R3", 2);
        Room roomH1R4 = new Room(savedHotel1, "roomH1R4", 1);
        Room roomH1R5 = new Room(savedHotel1, "roomH1R5", 1);
        Room roomH2R1 = new Room(savedHotel2, "roomH2R1", 2);
        Room roomH2R2 = new Room(savedHotel2, "roomH2R2", 2);
        Room roomH2R3 = new Room(savedHotel2, "roomH2R3", 2);
        Room roomH2R4 = new Room(savedHotel2, "roomH2R4", 1);
        Room roomH2R5 = new Room(savedHotel2, "roomH2R5", 1);

        savedHotel1.getRooms().add(roomH1R1);
        savedHotel1.getRooms().add(roomH1R2);
        savedHotel1.getRooms().add(roomH1R3);
        savedHotel1.getRooms().add(roomH1R4);
        savedHotel1.getRooms().add(roomH1R5);
        savedHotel2.getRooms().add(roomH2R1);
        savedHotel2.getRooms().add(roomH2R2);
        savedHotel2.getRooms().add(roomH2R3);
        savedHotel2.getRooms().add(roomH2R4);
        savedHotel2.getRooms().add(roomH2R5);

        Room savedRoomH1R1 = roomService.saveRoom(roomH1R1);
        Room savedRoomH1R2 = roomService.saveRoom(roomH1R2);
        Room savedRoomH1R3 = roomService.saveRoom(roomH1R3);
        Room savedRoomH1R4 = roomService.saveRoom(roomH1R4);
        Room savedRoomH1R5 = roomService.saveRoom(roomH1R5);
        Room savedRoomH2R1 = roomService.saveRoom(roomH2R1);
        Room savedRoomH2R2 = roomService.saveRoom(roomH2R2);
        Room savedRoomH2R3 = roomService.saveRoom(roomH2R3);
        Room savedRoomH2R4 = roomService.saveRoom(roomH2R4);
        Room savedRoomH2R5 = roomService.saveRoom(roomH2R5);

        Reservation reservation1 = new Reservation(savedUser2, 4,
                LocalDate.of(2020, 12, 14),
                LocalDate.of(2020, 12, 18));
        Reservation reservation2 = new Reservation(savedUser2, 4,
                LocalDate.of(2020, 12, 20),
                LocalDate.of(2020, 12, 24));
        Reservation reservation3 = new Reservation(savedUser3, 3,
                LocalDate.of(2020, 12, 16),
                LocalDate.of(2020, 12, 28));

        Reservation savedReservation1 = reservationService.saveReservation(reservation1);
        Reservation savedReservation2 = reservationService.saveReservation(reservation2);
        Reservation savedReservation3 = reservationService.saveReservation(reservation3);

        RoomReservation roomReservation1 = new RoomReservation(savedRoomH1R1, savedReservation1,
                LocalDate.now());
        RoomReservation roomReservation2 = new RoomReservation(savedRoomH1R2, savedReservation1,
                LocalDate.now());
        RoomReservation roomReservation3 = new RoomReservation(savedRoomH1R1, savedReservation2,
                LocalDate.now());
        RoomReservation roomReservation4 = new RoomReservation(savedRoomH1R2, savedReservation2,
                LocalDate.now());
        RoomReservation roomReservation5 = new RoomReservation(savedRoomH2R3, savedReservation3,
                LocalDate.now());
        RoomReservation roomReservation6 = new RoomReservation(savedRoomH2R4, savedReservation3,
                LocalDate.now());

        savedRoomH1R1.getRoomReservations().add(roomReservation1);
        savedRoomH1R2.getRoomReservations().add(roomReservation2);
        savedRoomH1R1.getRoomReservations().add(roomReservation3);
        savedRoomH1R2.getRoomReservations().add(roomReservation4);
        savedRoomH2R3.getRoomReservations().add(roomReservation5);
        savedRoomH2R4.getRoomReservations().add(roomReservation6);

        roomReservationService.saveRoomReservation(roomReservation1);
        roomReservationService.saveRoomReservation(roomReservation2);
        roomReservationService.saveRoomReservation(roomReservation3);
        roomReservationService.saveRoomReservation(roomReservation4);
        roomReservationService.saveRoomReservation(roomReservation5);
        roomReservationService.saveRoomReservation(roomReservation6);

//        Room viewRoom = roomService.getRoomById(1L);
//        Room viewRoom2 = roomService.getRoomById(2L);
//        System.out.println(viewRoom);
//        System.out.println(viewRoom2);
        System.out.println("end");
        //RoomReservation roomReservation1=new RoomReservation();

    }
}
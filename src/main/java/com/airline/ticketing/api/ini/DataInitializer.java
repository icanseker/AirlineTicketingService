package com.airline.ticketing.api.ini;

import com.airline.ticketing.model.Airlines;
import com.airline.ticketing.model.Airport;
import com.airline.ticketing.model.PortLocation;
import com.airline.ticketing.model.location.Timezone;
import com.airline.ticketing.model.airplane.AirPlaneCategory;
import com.airline.ticketing.model.location.City;
import com.airline.ticketing.model.location.Country;
import com.airline.ticketing.model.passenger.AirFareClass;
import com.airline.ticketing.model.passenger.PassengerSeatMap;
import com.airline.ticketing.model.passenger.PassengerSeatType;
import com.airline.ticketing.service.DataInitializerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Stream;

@Controller
public final class DataInitializer {

    private final DataInitializerService initService;

    @Autowired
    public DataInitializer(DataInitializerService initService) {
        this.initService = initService;
        initData();
    }

    private void initData() {

        // define different seat types
        this.initService.add(new PassengerSeatType("STD", "Standard Seat"));
        this.initService.add(new PassengerSeatType("STD-BH", "Standard & Bulkhead Seat"));
        this.initService.add(new PassengerSeatType("AFL", "Angle-Flat Seat"));
        this.initService.add(new PassengerSeatType("FLB", "Flat Bed Seat"));
        this.initService.add(new PassengerSeatType("FLB-BH", "Flat Bed & Bulkhead Seat"));
        this.initService.add(new PassengerSeatType("RCL", "Recliner Seat"));
        this.initService.add(new PassengerSeatType("RCL-BH", "Recliner & Bulkhead Seat"));

        // define fare classes with price coefficient
        this.initService.add(new AirFareClass('A', "First Class Discounted", 3.2));
        this.initService.add(new AirFareClass('C', "Business Class", 1.6));
        this.initService.add(new AirFareClass('E', "Economy/Coach Discounted", 0.8));
        this.initService.add(new AirFareClass('F', "First Class", 2.7));
        this.initService.add(new AirFareClass('J', "Business Class Premium", 2.0));
        this.initService.add(new AirFareClass('F', "First Class Premium", 4.72));
        this.initService.add(new AirFareClass('R', "First Class Suite or Supersonic", 3.6));
        this.initService.add(new AirFareClass('Y', "Economy/Coach", 1.0));

        // define locations and airports
        Country turkey = new Country("TUR", "Turkey");
        this.initService.add(turkey);

        Country england = new Country("GBR", "United Kingdom");
        this.initService.add(england);

        // define time zones

        Timezone istanbul_tz = new Timezone("Europe/Istanbul");
        Timezone london_tz = new Timezone("Europe/London");
        this.initService.add(istanbul_tz);
        this.initService.add(london_tz);

        // define airports
        this.initService.add(new Airport("IST", "ISTANBUL AIRPORT", new PortLocation(turkey, new City("TR-IST", "Istanbul"), "28.820829", "40.982555", istanbul_tz)));
        this.initService.add(new Airport("SAW", "SABIHA GOKCEN AIRPORT", new PortLocation(turkey, new City("TR-IST", "Istanbul"), "29.309168", "40.898335", istanbul_tz)));
        this.initService.add(new Airport("ESB", "ESENBOGA AIRPORT", new PortLocation(turkey, new City("TR-ANK", "Ankara"), "32.866287", "39.925533", istanbul_tz)));
        this.initService.add(new Airport("TZX", "TRABZON AIRPORT", new PortLocation(turkey, new City("TR-TRZ", "Trabzon"), "39.716763", "41.002697", istanbul_tz)));
        this.initService.add(new Airport("ADB", "ADNAN MENDERES AIRPORT", new PortLocation(turkey, new City("TR-IZ", "Izmir"), "27.142826", "38.423733", istanbul_tz)));
        this.initService.add(new Airport("BAL", "BATMAN AIRPORT", new PortLocation(turkey, new City("TR-BAT", "Batman"), "41.13221", "37.88738", istanbul_tz)));
        this.initService.add(new Airport("DIY", "DIYARBAKIR AIRPORT", new PortLocation(turkey, new City("TR-DIY", "Diyarbakir"), "40.240002", "37.910000", istanbul_tz)));
        this.initService.add(new Airport("SZF", "SAMSUN CARSAMBA AIRPORT", new PortLocation(turkey, new City("TR-SMS", "Samsun"), "36.33", "41.28667", istanbul_tz)));

        this.initService.add(new Airport("STN", "LONDON STANSTED AIRPORT", new PortLocation(england, new City("EN-LON", "London"), "0.2377777778", "51.8838888889", london_tz)));
        this.initService.add(new Airport("LGW", "GATWICK AIRPORT", new PortLocation(england, new City("EN-LON", "London"), "-0.1825", "51.1522222222", london_tz)));
        this.initService.add(new Airport("LHR", "HEATHROW AIRPORT", new PortLocation(england, new City("EN-LON", "London"), "-0.454295", "51.470020", london_tz)));

        // define some direct air route
        this.initService.add("7040", "IST", "BAL", 1105);
        this.initService.add("7041", "BAL", "IST", 1110);
        this.initService.add("7020", "SAW", "ESB", 279);
        this.initService.add("7021", "ESB", "SAW", 283);
        this.initService.add("7190", "IST", "STN", 2519);
        this.initService.add("7191", "STN", "IST", 2522);

        // define airlines
        this.initService.add(new Airlines("TK", "Turkish Airlines CO."));
        this.initService.add(new Airlines("PC", "Pegasus Airlines"));
        this.initService.add(new Airlines("AC", "Air China"));

        // define airplane categories
        Stream.of(
                new String[]{"Jumbo Passenger Jet", "JPJ"},
                new String[]{"Mid-size Passenger Jet", "MsPJ"},
                new String[]{"Light Passenger Jet", "LPJ"},
                new String[]{"Mid-Size Business Jet", "MsBJ"},
                new String[]{"Light Business Jet", "LBJ"},
                new String[]{"Very Light Jet", "VLJ"}
        ).forEach(category -> this.initService.add(new AirPlaneCategory(category[1], category[0])));

        // define passenger seat map
        // sample map as plane model default
        // each model and airplane can have a specific seat map

        PassengerSeatMap default_psm = new PassengerSeatMap("DEFAULT_PSM", "Default seat map.");
        default_psm.addSeat("1A", "FLB-BH", 'C');
        default_psm.addSeat("1B", "FLB-BH", 'C');
        default_psm.addSeat("1D", "FLB-BH", 'C');
        default_psm.addSeat("1E", "FLB-BH", 'C');
        default_psm.addSeat("1J", "FLB-BH", 'C');
        default_psm.addSeat("1K", "FLB-BH", 'C');

        for (int i = 2; i <= 6; i++) {
            default_psm.addSeat(i + "A", "AFL", 'C');
            default_psm.addSeat(i + "B", "AFL", 'C');
            default_psm.addSeat(i + "D", "AFL", 'C');
            default_psm.addSeat(i + "E", "AFL", 'C');
            default_psm.addSeat(i + "J", "AFL", 'C');
            default_psm.addSeat(i + "K", "AFL", 'C');
        }

        default_psm.addSeat("7A", "STD-BH", 'E');
        default_psm.addSeat("7B", "STD-BH", 'E');
        default_psm.addSeat("7D", "STD-BH", 'E');
        default_psm.addSeat("7E", "STD-BH", 'E');
        default_psm.addSeat("7F", "STD-BH", 'E');
        default_psm.addSeat("7G", "STD-BH", 'E');
        default_psm.addSeat("7J", "STD-BH", 'E');
        default_psm.addSeat("7K", "STD-BH", 'E');

        for (int i = 8; i <= 33; i++) {
            default_psm.addSeat(i + "A", "STD", 'E');
            default_psm.addSeat(i + "B", "STD", 'E');
            default_psm.addSeat(i + "D", "STD", 'E');
            default_psm.addSeat(i + "E", "STD", 'E');
            default_psm.addSeat(i + "F", "STD", 'E');
            default_psm.addSeat(i + "G", "STD", 'E');
            default_psm.addSeat(i + "J", "STD", 'E');
            default_psm.addSeat(i + "K", "STD", 'E');
        }

        for (int i = 34; i <= 37; i++) {
            default_psm.addSeat(i + "A", "STD", 'E');
            default_psm.addSeat(i + "B", "STD", 'E');
            default_psm.addSeat(i + "D", "STD", 'E');
            default_psm.addSeat(i + "E", "STD", 'E');
            default_psm.addSeat(i + "F", "STD", 'E');
            default_psm.addSeat(i + "J", "STD", 'E');
            default_psm.addSeat(i + "K", "STD", 'E');
        }

        this.initService.add(default_psm);

        // define some airplanes model with default seat map
        this.initService.add("A333", "AIRBUS A330-300", "JPJ", "DEFAULT_PSM", 621);
        this.initService.add("B737", "BOEING 737-700", "MsPJ", "DEFAULT_PSM", 702);

        // define some airplanes
        this.initService.add("THY-ANKA-333", "ANKA-333", "A333", "TK", null);
        this.initService.add("THY-WORM-77", "FETHIYE", "A333", "TK", null);
        this.initService.add("PCA-SIRIUS-A", "OTTOMAN", "B737", "PC", null);
        this.initService.add("PCA-SIRIUS-B", "DREAM-LINER", "A333", "PC", null);

        // define some flights with specific route, plane and date

        // on 03.02.2022 20:35 from IST to BAL with THY-ANKA-333
        this.initService.add(
                "7040",
                "THY-ANKA-333",
                ZonedDateTime.of(
                        LocalDateTime.of(2022, Month.FEBRUARY, 3, 20, 35),
                        ZoneId.of("Europe/Istanbul")
                ),
                100.0
        );

        // on 04.02.2022 20:35 from IST to BAL with THY-ANKA-333
        this.initService.add(
                "7040",
                "THY-ANKA-333",
                ZonedDateTime.of(
                        LocalDateTime.of(2022, Month.FEBRUARY, 4, 20, 35),
                        ZoneId.of("Europe/Istanbul")
                ),
                106.25
        );

        // on 03.02.2022 23:45 from BAL to IST with THY-ANKA-333
        this.initService.add(
                "7041",
                "THY-ANKA-333",
                ZonedDateTime.of(
                        LocalDateTime.of(2022, Month.FEBRUARY, 3, 23, 45),
                        ZoneId.of("Europe/Istanbul")
                ),
                57.8
        );

        // on 05.02.2022 10:15 from IST to STN with PCA-SIRIUS-A
        this.initService.add(
                "7190",
                "PCA-SIRIUS-A",
                ZonedDateTime.of(
                        LocalDateTime.of(2022, Month.FEBRUARY, 5, 10, 15),
                        ZoneId.of("Europe/Istanbul")
                ),
                356.78
        );

        // on 05.02.2022 19:25 from STN to IST with PCA-SIRIUS-A
        this.initService.add(
                "7191",
                "PCA-SIRIUS-A",
                ZonedDateTime.of(
                        LocalDateTime.of(2022, Month.FEBRUARY, 5, 19, 25),
                        ZoneId.of("Europe/London")
                ),
                415.0
        );
    }
}
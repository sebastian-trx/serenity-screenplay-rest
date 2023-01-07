package co.com.sofka.setup;

import co.com.sofka.models.restfulbooker.booking.Booking;
import co.com.sofka.models.restfulbooker.booking.BookingDates;
import net.serenitybdd.screenplay.Actor;
import org.apache.log4j.PropertyConfigurator;

import java.util.HashMap;
import java.util.Map;

import static co.com.sofka.utils.CreateBookingData.*;


public class Setup {
    protected Actor actor;
    protected Map<String, Object> headers = new HashMap<>();

    protected void setUpLog4j(){
        PropertyConfigurator.configure(Setup.class.getClassLoader().getResource("log4j.properties"));
    }

    protected static final String URL_BASE_JSON_PLACE_HOLDER = "https://jsonplaceholder.typicode.com";
    protected static final String GET_USER_BY_ID = "/users/1";

    protected static final String URL_BASE_RESTFUL_BOOKER = "https://restful-booker.herokuapp.com";
    protected static final String RESOURCE = "/booking/";

    protected static Booking bodyRequest(){
       BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2018-10-01");
        bookingDates.setCheckout("2019-10-01");

        Booking bodyRequest = new Booking();
        bodyRequest.setFirstname(FIRST_NAME);
        bodyRequest.setLastname(LAST_NAME);
        bodyRequest.setTotalprice(TOTAL_PRICE);
        bodyRequest.setDepositpaid(true);
        bodyRequest.setBookingdates(bookingDates);
        bodyRequest.setAdditionalneeds(ADDITIONAL_NEEDS);
        return bodyRequest;
    }
    protected static Booking bodyRequestUpdate(){
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2018-01-02");
        bookingDates.setCheckout("2019-02-03");

        Booking bodyRequest = new Booking();
        bodyRequest.setFirstname("pepe");
        bodyRequest.setLastname("perez");
        bodyRequest.setTotalprice(890);
        bodyRequest.setDepositpaid(true);
        bodyRequest.setBookingdates(bookingDates);
        bodyRequest.setAdditionalneeds("agua");
        return bodyRequest;
    }

}

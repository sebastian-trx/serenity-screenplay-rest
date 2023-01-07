package co.com.sofka.utils;

import co.com.sofka.models.restfulbooker.booking.Booking;
import co.com.sofka.models.restfulbooker.booking.BookingDates;
import com.github.javafaker.Faker;

public class CreateBookingData {

    private CreateBookingData() {
    }

    static Faker faker = new Faker();

    public static final String FIRST_NAME = faker.name().firstName().replaceAll("\\s","");

    public static final String LAST_NAME = faker.name().lastName().replaceAll("\\s","");

    public static final Integer TOTAL_PRICE = faker.random().nextInt(100,900);

    public static final String ADDITIONAL_NEEDS = faker.beer().name().replaceAll("\\s","");

}

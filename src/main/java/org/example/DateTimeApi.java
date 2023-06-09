package org.example;

import org.example.model.DateTimePart;

import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.time.ZoneOffset;
public class DateTimeApi {

  /**
   * Return the current date as a String depending on a query.
   * <p>
   * The query can be passed for the whole date or for it's part:
   * - FULL - current date as a whole: year, month, day of month
   * formatted as `YYYY-MM-DD` (a default return value);
   * - YEAR - current year;
   * - MONTH - name of the current month;
   * - DAY - current day of month;
   * In any other case throw DateTimeException.
   **/
  public String todayDate(DateTimePart datePart) {
    LocalDate now = LocalDate.now();

    switch (datePart) {
      case FULL :
        return String.valueOf(LocalDate.now());
      case YEAR :
        return String.valueOf(now.getYear());
      case MONTH :
        return String.valueOf(now.getMonth());
      case DAY :
        return String.valueOf(now.getDayOfMonth());

      default :
        throw new DateTimeException("Invalid query");
    }
  }

  /**
   * Given an Array of 3 elements, where
   * - 1-st element is a `year`;
   * - 2-nd element is s `month`;
   * - 3-rd element is a `day of month`;
   * <p>
   * Return LocalDate built from these elements. If Array contains more than 3 elements - throw DateTimeException.
   */
  public LocalDate getDate(Integer[] dateParams) {
    if(dateParams.length != 3) {
      throw new DateTimeException("Array should contain 3 elements");
    }
    Integer year = dateParams[0];
    Integer month = dateParams[1];
    Integer day = dateParams[2];

    return LocalDate.of(year, month, day);
  }

  /**
   * Given the time and the number of hours to add, return the changed time.
   */
  public LocalTime addHours(LocalTime localTime, Integer hoursToAdd) {
    return localTime.plusHours(hoursToAdd);
  }

  /**
   * Given the time and the number of minutes to add, return the changed time.
   */
  public LocalTime addMinutes(LocalTime localTime, Integer minutesToAdd) {
    return localTime.plusMinutes(minutesToAdd);
  }

  /**
   * Given the time and the number of seconds to add, return the changed time.
   */
  public LocalTime addSeconds(LocalTime localTime, Integer secondsToAdd) {
    return localTime.plusSeconds(secondsToAdd);
  }

  /**
   * Given the date and the number of weeks to add, return the changed date.
   */
  public LocalDate addWeeks(LocalDate localDate, Integer numberOfWeeks) {
    return localDate.plusWeeks(numberOfWeeks);
  }

  /**
   * Given a random `someDate` date, return one of the following Strings:
   * - "`someDate` is after `currentDate`"
   * if `someDate` is in the future relating to the `current date`;
   * - "`someDate` is before `currentDate`"
   * if `someDate` is in the past relating to the `current date`;
   * - "`someDate` is today"
   * if `someDate` is today;
   */
  public String beforeOrAfter(LocalDate someDate) {
    LocalDate currentDate = LocalDate.now();

    if (someDate.isAfter(currentDate)) {
      return someDate + " is after " + currentDate;
    } else if (someDate.isBefore(currentDate)) {
      return someDate + " is before " + currentDate;
    } else {
      return someDate + " is today";
    }
  }

  /**
   * Given a String representation of a date and some timezone,
   * return LocalDateTime in this timezone.
   */
  public LocalDateTime getDateInSpecificTimeZone(String dateInString, String zone) {
    Instant instant = Instant.parse(dateInString);
    ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of(zone));
    return zonedDateTime.toLocalDateTime();
  }

  /**
   * Given some LocalDateTime, return an OffsetDateTime with the local time offset applied
   * (`+02:00` for Ukraine).
   * <p>
   * Example: we receive a LocalDateTime with a value `2019-09-06T13:17`.
   * We should return the OffsetDateTime with a value `2019-09-06T13:17+02:00`,
   * where `+02:00` is the offset for our local timezone.
   * <p>
   * OffsetDateTime is recommended to use for storing date values in a database.
   */
  public OffsetDateTime offsetDateTime(LocalDateTime localTime) {
    ZoneId zone = ZoneId.of("+02:00");
    ZoneOffset offset = zone.getRules().getOffset(localTime);
    return OffsetDateTime.of(localTime, offset);
  }

  /**
   * Given a String formatted as `yyyyMMdd`,
   * return LocalDate object built from this String.
   */
  public LocalDate parseDate(String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    try {
      return LocalDate.parse(date, formatter);
    } catch (DateTimeParseException e) {
      return null;
    }
  }

  /**
   * Given a String formatted as `d MMM yyyy` (MMM - Sep, Oct, etc),
   * return LocalDate object built from this String.
   */
  public LocalDate customParseDate(String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
    return LocalDate.parse(date, formatter);
  }

  /**
   * Given some LocalDateTime, return a String formatted as
   * `day(2-digit) month(full name in English) year(4-digit) hours(24-hour format):minutes`.
   * <p>
   * Example: "01 January 2000 18:00".
   */
  public String formatDate(LocalDateTime dateTime) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm", Locale.ENGLISH);
    return dateTime.format(formatter);
  }
}
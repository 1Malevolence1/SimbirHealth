package com.example.Account_microservice.data;

import java.time.ZonedDateTime;
import java.util.Date;

public interface ZoneDataService {

    ZonedDateTime getTime(Date date);
}

package com.example.Account_microservice.data;


import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;


@Service
public class ZoneDataServiceImpl implements ZoneDataService {


    @Override
    public ZonedDateTime getTime(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Date must not be null");
        }

        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("Europe/Moscow"));
    }
}


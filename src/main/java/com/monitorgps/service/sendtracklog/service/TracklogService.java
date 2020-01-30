package com.monitorgps.service.sendtracklog.service;

import com.monitorgps.service.sendtracklog.bean.ListResponseTracklogs;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public interface TracklogService {
    ListResponseTracklogs sendTracklog() throws IOException;

    void sendTracklogByMinute(Date format, Date s) throws IOException, ParseException;
}

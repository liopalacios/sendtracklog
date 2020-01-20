package com.monitorgps.service.sendtracklog.service;

import com.monitorgps.service.sendtracklog.bean.ListResponseTracklogs;
import com.monitorgps.service.sendtracklog.bean.ResponseTracklog;

import java.util.List;

public interface TracklogService {
    ListResponseTracklogs sendTracklog();
}

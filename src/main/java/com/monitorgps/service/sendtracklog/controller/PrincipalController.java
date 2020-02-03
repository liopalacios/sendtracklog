package com.monitorgps.service.sendtracklog.controller;

import com.monitorgps.service.sendtracklog.bean.ListResponseTracklogs;
import com.monitorgps.service.sendtracklog.service.TracklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class PrincipalController {

    @Autowired
    TracklogService tracklogService;

    @RequestMapping(value = {"/sendTracklog"}, method = RequestMethod.GET)
    public ListResponseTracklogs sendTracklog() throws IOException {
        ListResponseTracklogs responseTracklogs = tracklogService.sendTracklog();

        return responseTracklogs;
    }

}

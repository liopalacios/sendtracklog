package com.monitorgps.service.sendtracklog.service.impl;

import com.google.gson.Gson;
import com.monitorgps.service.sendtracklog.bean.ListRequestTracklogs;
import com.monitorgps.service.sendtracklog.bean.ListResponseTracklogs;
import com.monitorgps.service.sendtracklog.bean.RequestTracklog;
import com.monitorgps.service.sendtracklog.bean.ResponseTracklog;
import com.monitorgps.service.sendtracklog.repository.AlertaActivaRepository;
import com.monitorgps.service.sendtracklog.repository.CompanyRepository;
import com.monitorgps.service.sendtracklog.service.TracklogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TracklogServiceImpl implements TracklogService {
    @Autowired
    CompanyRepository alertaActivaRepository;

    @Override
    public ListResponseTracklogs sendTracklog() {
        final String uri = "http://190.12.73.86/json/json_receive.php";

        RequestTracklog requestTracklog =
                new RequestTracklog("V9J-846","2019-10-01","17:28:55","-14.56789","17.34567",310,16,"2");
        RestTemplate restTemplate = new RestTemplate();
        List<RequestTracklog> requestTracklogs = new ArrayList<>();
        requestTracklogs.add(requestTracklog);
        ListRequestTracklogs listRequestTracklogs = new ListRequestTracklogs(requestTracklogs);

        System.out.print("\n"+listRequestTracklogs+"\n");

        ResponseEntity<String> str = restTemplate.postForEntity(uri,listRequestTracklogs, String.class);
        //String str = restTemplate.postForObject(uri,listRequestTracklogs, String.class);
        System.out.print(str+"\n");
        System.out.print(str.getStatusCode()+"\n");
        System.out.print(str.getStatusCodeValue()+"\n");
        Gson gson = new Gson();
        ListResponseTracklogs listResponseTracklogs = gson.fromJson(str.getBody(),ListResponseTracklogs.class);
        if(str.getStatusCodeValue()==200){
            System.out.println("EL ENVIO FUE CORRECTO");
            System.out.println(listResponseTracklogs);
        }
        log.info(str.getBody());
        return null;
    }

    @Override
    public void sendTracklogByMinute(String format) {
        final String uri = "http://190.12.73.86/json/json_receive.php";
        List<Object[]> objects = null ;
        List<RequestTracklog> requestTracklogsObj = converterObjectToPojo(objects);
        //RequestTracklog requestTracklog =
          //      new RequestTracklog("V9J-846","2019-10-01","17:28:55","-14.56789","17.34567",310,16,"2");
        RestTemplate restTemplate = new RestTemplate();
        //List<RequestTracklog> requestTracklogs = new ArrayList<>();
        //requestTracklogs.add(requestTracklog);
        ListRequestTracklogs listRequestTracklogs = new ListRequestTracklogs(requestTracklogsObj);

        System.out.print("\n"+listRequestTracklogs+"\n");

        ResponseEntity<String> str = restTemplate.postForEntity(uri,listRequestTracklogs, String.class);
        //String str = restTemplate.postForObject(uri,listRequestTracklogs, String.class);
        System.out.print(str+"\n");
        System.out.print(str.getStatusCode()+"\n");
        System.out.print(str.getStatusCodeValue()+"\n");
        Gson gson = new Gson();
        ListResponseTracklogs listResponseTracklogs = gson.fromJson(str.getBody(),ListResponseTracklogs.class);
        if(str.getStatusCodeValue()==200){
            System.out.println("EL ENVIO FUE CORRECTO");
            System.out.println(listResponseTracklogs);
        }
        log.info(str.getBody());
        //return null;
    }

    private List<RequestTracklog> converterObjectToPojo(List<Object[]> objects) {
        List<RequestTracklog> listRequest=new ArrayList<>();
        RequestTracklog requestTracklog = null;

        for(Object[] obj : objects){
            requestTracklog = new RequestTracklog();
            requestTracklog.setPlaca((String)obj[0]);
            requestTracklog.setFechaEvento((String)obj[1]);
            requestTracklog.setHoraEvento((String)obj[2]);
            requestTracklog.setLatitud((String)obj[3]);
            requestTracklog.setLongitud((String)obj[4]);
            requestTracklog.setEvento((String)obj[5]);
            requestTracklog.setVelocidad((int)obj[6]);
            requestTracklog.setDireccion((int)obj[6]);
            listRequest.add(requestTracklog);
        }

        return listRequest;
    }
}

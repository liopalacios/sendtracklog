package com.monitorgps.service.sendtracklog.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.monitorgps.service.sendtracklog.bean.ListRequestTracklogs;
import com.monitorgps.service.sendtracklog.bean.ListResponseTracklogs;
import com.monitorgps.service.sendtracklog.bean.RequestTracklog;
import com.monitorgps.service.sendtracklog.bean.ResponseTracklog;
import com.monitorgps.service.sendtracklog.repository.AlertaActivaRepository;
import com.monitorgps.service.sendtracklog.service.TracklogService;
import com.monitorgps.service.sendtracklog.util.Constantes;
import com.monitorgps.service.sendtracklog.util.UtilFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class TracklogServiceImpl implements TracklogService {
    @Value("${ruta_logs}")
    private String carpetaLog;

    @Autowired
    AlertaActivaRepository alertaActivaRepository;

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    public ListResponseTracklogs sendTracklog() {
        final String uri = "http://190.12.73.86/json/json_receive.php";

        RequestTracklog requestTracklog =
                new RequestTracklog("V9J-846","2019-10-01","17:28:55","-14.56789","17.34567",310.00,16.00,2);
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
        if(str.getStatusCodeValue()==200) {
            System.out.println("EL ENVIO FUE CORRECTO");
            System.out.println(listResponseTracklogs);
        }
        log.info(str.getBody());
        return null;
    }

    @Override
    public void sendTracklogByMinute(Date format, Date s) throws IOException, ParseException {
        final String uri = "http://190.12.73.86/json/json_receive.php";

        List<Object[]> objects = alertaActivaRepository.getListEventsByMinute(format,s);

        List<RequestTracklog> requestTracklogsObj = converterObjectToPojo(objects);
       // System.out.println(requestTracklogsObj);

        RestTemplate restTemplate = new RestTemplate();

        ListRequestTracklogs listRequestTracklogs = new ListRequestTracklogs(requestTracklogsObj);

        //System.out.print("\n"+listRequestTracklogs+"\n");

        ResponseEntity<String> str = restTemplate.postForEntity(uri,listRequestTracklogs, String.class);


            if(str.getStatusCodeValue()==200){
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-DD HH:mm:ss").create();
            ListResponseTracklogs listResponseTracklogs = gson.fromJson(str.getBody(),ListResponseTracklogs.class);

          //  System.out.println("EL ENVIO FUE CORRECTO "+format);
         //   System.out.println(listResponseTracklogs.getRecibidos().size());
            // System.out.println(listRequestTracklogs);
            String Archivo=carpetaLog+Constantes.N0MBRE_DOCUMENTO+Constantes.SEPARADOR_EPACIO+UtilFormat.formatoFecha(new Date())+Constantes.TIPO_DOCUMENTO;

            FileWriter log =new FileWriter(Archivo,true);

            log.write(str.getBody()+"\n"+"\n");
            log.close();
            for (ResponseTracklog response : listResponseTracklogs.getRecibidos() ) {
              // System.out.println(response.getFecha_Hora());
             //   System.out.println(dateFormat.format(response.getFecha_Hora()));
                alertaActivaRepository.getUpdateByMinute(response.getFecha_Hora(),dateFormat.format(response.getFecha_Hora()),response.getPlaca());
                //System.out.println(response.getFecha_Hora());
            }
        }
        else
        {
            String ruta2=carpetaLog+Constantes.ERROR_LOG+Constantes.SEPARADOR_EPACIO+Constantes.CADENA_VACIA+UtilFormat.formatoFecha(new Date())+Constantes.TIPO_DOCUMENTO;
            FileWriter errlog=new FileWriter(ruta2,true);
            errlog.write(str.getBody());
            errlog.close();

        }
        //log.info(str.getBody());
        //return null;
    }

    private List<RequestTracklog> converterObjectToPojo(List<Object[]> objects) {
        List<RequestTracklog> listRequest=new ArrayList<>();
        RequestTracklog requestTracklog = null;
        for (int i =0; i<=objects.size()-1; i++){
            if(i<100)
            {
            requestTracklog = new RequestTracklog();
            requestTracklog.setPlaca((String)objects.get(i)[0]);
            requestTracklog.setFechaEvento((String)objects.get(i)[1]);
            requestTracklog.setHoraEvento((String)objects.get(i)[2]);
            requestTracklog.setLatitud((String)objects.get(i)[3]);
            requestTracklog.setLongitud((String)objects.get(i)[4]);
            requestTracklog.setEvento((int)objects.get(i)[5]);
            requestTracklog.setVelocidad((Double)objects.get(i)[6]);
            requestTracklog.setDireccion((Double) objects.get(i)[7]);
            listRequest.add(requestTracklog);
            }
        }

        return listRequest;
    }
}

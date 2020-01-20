package com.monitorgps.service.sendtracklog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListResponseTracklogs {
    private List<ResponseTracklog> Recibidos;
}

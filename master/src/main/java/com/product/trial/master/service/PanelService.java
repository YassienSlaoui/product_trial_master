package com.product.trial.master.service;

import com.product.trial.master.dto.PanelDTO;
import com.product.trial.master.exception.ExceptionsHandler;

public interface PanelService {

    PanelDTO panel(String userId) throws ExceptionsHandler;

    PanelDTO addToPanel(Integer productId, Integer number , String user) throws ExceptionsHandler;

    PanelDTO removePanel(Integer productId , Integer number, String user) throws ExceptionsHandler;

    void dropPanel(String user) throws ExceptionsHandler;
}

package com.product.trial.master.controller;


import com.product.trial.master.dto.PanelDTO;
import com.product.trial.master.exception.ExceptionsHandler;
import com.product.trial.master.service.PanelService;
import jakarta.ws.rs.QueryParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/panel")
public class PanelController {
    private final PanelService panelService;

    public PanelController(PanelService panelService) {
        this.panelService = panelService;
    }


    @GetMapping
    public ResponseEntity<PanelDTO> getPanel(Principal principal) throws ExceptionsHandler {
        return new ResponseEntity<>(panelService.panel(principal.getName()), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<PanelDTO>  addToPanel(@QueryParam("productId") Integer productId,@QueryParam("number") Integer number, Principal principal) throws ExceptionsHandler {
        return new ResponseEntity<>(panelService.addToPanel(productId,number,principal.getName()), HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<PanelDTO> removeFromPanelList(@QueryParam("productId") Integer productId,@QueryParam("number") Integer number, Principal principal) throws ExceptionsHandler {
        return new ResponseEntity<>(panelService.removePanel(productId,number,principal.getName()), HttpStatus.OK);

    }
    @DeleteMapping("/drop")
    public void dropPanelList(Principal principal) throws ExceptionsHandler {
         panelService.dropPanel(principal.getName());
    }
}

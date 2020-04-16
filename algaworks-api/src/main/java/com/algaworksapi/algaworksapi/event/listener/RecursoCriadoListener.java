package com.algaworksapi.algaworksapi.event.listener;

import com.algaworksapi.algaworksapi.event.RecursoCriadoEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

//escucha un evento
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

    @Override
    public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {
        HttpServletResponse response = recursoCriadoEvent.getResponse();
        Long codigo = recursoCriadoEvent.getCodigo();
        adicionarHeaderLocation(response, codigo);
    }

    private void adicionarHeaderLocation(HttpServletResponse response, Long codigo) {
        URI url = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(codigo).toUri();
        response.setHeader("Location", url.toASCIIString());
    }

}

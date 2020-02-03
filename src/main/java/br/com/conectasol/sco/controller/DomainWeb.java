package br.com.conectasol.sco.controller;

import br.com.conectasol.sco.domain.Domain;
import br.com.conectasol.sco.domain.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/domains")
public class DomainWeb {

    @Autowired
    public DomainRepository rep;

    @GetMapping
    public List<Domain> teste(){
        return rep.findAll();
    }

    @GetMapping("/salvar")
    public String salvar(){
        Domain d = new Domain();
        d.setId(System.currentTimeMillis());
        d.setDomain("COnectaSol" + d.getId());
        d.setDisplayAds(true);
        rep.save(d);
        return "Done!!";
    }
}

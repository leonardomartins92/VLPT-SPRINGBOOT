package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.model.Pacote;
import com.spring.voluptuaria.service.PacoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
public class PacoteController {
    @Autowired
    PacoteService pacoteService;

    @RequestMapping(value = "/pesquisaPacote", method = RequestMethod.GET)
    public ModelAndView preparaPesquisa(){
        ModelAndView mv = new ModelAndView("pesquisaPacote");
        List<Pacote> pacotes = pacoteService.findAll();
        mv.addObject("pacotes", pacotes);
        return mv;
    }

    @RequestMapping(value = "/manterPacote", method = RequestMethod.GET)
    public ModelAndView getView(@RequestParam String acao, @RequestParam String operacao,
                                @RequestParam(required = false) Long cod){
        ModelAndView mv;
        log.info("ação:"+acao);
        log.info("operacao:"+operacao);
        log.info("cod:"+cod);

        if(acao.equals("preparaOperacao")){

            mv = new ModelAndView("manterPacote");
            mv.addObject("operacao", operacao);

            if (!operacao.equals("Adicionar")) {
                mv.addObject("pacote", pacoteService.findById(cod));
            }
        }
        else {
            mv = new ModelAndView("pesquisaPacote");
        }

        return mv;
    }


}

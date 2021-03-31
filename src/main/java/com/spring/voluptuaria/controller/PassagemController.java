package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.model.Passagem;
import com.spring.voluptuaria.service.PassagemService;
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
public class PassagemController {
    @Autowired
    PassagemService passagemService;

    @RequestMapping(value = "/pesquisaPassagem", method = RequestMethod.GET)
    public ModelAndView preparaPesquisa(){
        ModelAndView mv = new ModelAndView("pesquisaPassagem");
        List<Passagem> passagens = passagemService.findAll();
        mv.addObject("passagens", passagens);
        return mv;
    }

    @RequestMapping(value = "/manterPassagem", method = RequestMethod.GET)
    public ModelAndView getView(@RequestParam String acao, @RequestParam String operacao,
                                @RequestParam(required = false) Long cod){
        ModelAndView mv;
        log.info("ação:"+acao);
        log.info("operacao:"+operacao);
        log.info("cod:"+cod);

        if(acao.equals("preparaOperacao")){

            mv = new ModelAndView("manterPassagem");
            mv.addObject("operacao", operacao);

            if (!operacao.equals("Adicionar")) {
                mv.addObject("passagem", passagemService.findById(cod));
            }
        }
        else {
            mv = new ModelAndView("pesquisaPassagem");
        }

        return mv;
    }



}

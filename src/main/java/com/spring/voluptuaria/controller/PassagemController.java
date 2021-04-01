package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.model.Passagem;
import com.spring.voluptuaria.model.Passagem;
import com.spring.voluptuaria.service.EmpresaService;
import com.spring.voluptuaria.service.PacoteService;
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
    @Autowired
    EmpresaService empresaService;
    @Autowired
    PacoteService pacoteService;

    @RequestMapping(value = "/pesquisaPassagem", method = RequestMethod.GET)
    public ModelAndView preparaPesquisa(){
        ModelAndView mv = new ModelAndView("pesquisaPassagem");

        mv.addObject("passagens", passagemService.findAll());

        return mv;
    }

    @RequestMapping(value = "/manterPassagem", method = RequestMethod.GET)
    public ModelAndView getView(@RequestParam String operacao,
                                @RequestParam(required = false) Long cod){
        ModelAndView mv;

        log.info("operacao:"+operacao);
        log.info("cod:"+cod);

            mv = new ModelAndView("manterPassagem");
            mv.addObject("operacao", operacao);
            mv.addObject("pacotes", pacoteService.findAll());
            mv.addObject("empresas", empresaService.findAll());

            if (!operacao.equals("Adicionar")) {
                mv.addObject("passagem", passagemService.findById(cod));
            }

        return mv;
    }

    @RequestMapping(value = "/manterPassagem", method = RequestMethod.POST)
    public ModelAndView formulario(@RequestParam String operacao, Passagem passagem) {

        if(operacao.equals("Excluir")){
            passagemService.delete(passagem);
        }
        else{
            passagem.setEmpresa(empresaService.findById(passagem.getIdEmpresa()));
            passagem.setPacote(pacoteService.findById(passagem.getIdPacote()));
            if(operacao.equals("Adicionar")){
                passagemService.save(passagem);
            }
            else if(operacao.equals("Editar")){

            }
        }

        return preparaPesquisa();
    }

}

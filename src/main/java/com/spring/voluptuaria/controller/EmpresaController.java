package com.spring.voluptuaria.controller;

import com.spring.voluptuaria.model.Empresa;
import com.spring.voluptuaria.service.EmpresaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @RequestMapping(value = "/pesquisaEmpresa", method = RequestMethod.GET)
    public ModelAndView preparaPesquisa(){
        ModelAndView mv = new ModelAndView("pesquisaEmpresa");
        List<Empresa> empresas = empresaService.findAll();
        mv.addObject("empresas", empresas);
        return mv;
    }

    @RequestMapping(value = "/manterEmpresa", method = RequestMethod.GET)
    public ModelAndView getView(@RequestParam String acao, @RequestParam String operacao,
                                @RequestParam(required = false) Long cod){
        ModelAndView mv;
        log.info("ação:"+acao);
        log.info("operacao:"+operacao);
        log.info("cod:"+cod);

        if(acao.equals("preparaOperacao")){

            mv = new ModelAndView("manterEmpresa");
            mv.addObject("operacao", operacao);

            if (!operacao.equals("Adicionar")) {
                mv.addObject("empresa", empresaService.findById(cod));
            }
        }
        else {
            mv = new ModelAndView("pesquisaEmpresa");
        }

        return mv;
    }


}

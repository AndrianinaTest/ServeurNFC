/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ratsi
 */
@RestController
public class rootpage {
    
    @RequestMapping(value = "/home")
    public ModelAndView testPage(){
        return  new ModelAndView("Home");
    }
  @RequestMapping(value = "/indexPage", method = RequestMethod.GET)
    public ModelAndView rooterPageindex() {
        return new ModelAndView("index_1");
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView rooterPageindexrtr() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "adminInfo", method = RequestMethod.GET)
    public ModelAndView rooterPageadminInfo() {
        return new ModelAndView("adminInfo");
    }

    @RequestMapping(value = "adminInfoCon", method = RequestMethod.GET)
    public ModelAndView rooterPageadminInfoCon() {
        return new ModelAndView("adminInfoCon");
    }

    @RequestMapping(value = "recMailAdm", method = RequestMethod.GET)
    public ModelAndView rooterPagerecMailAdm() {
        return new ModelAndView("recMailAdm");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView rooterPageuserInfo() {
        return new ModelAndView("userInfo");
    }

    @RequestMapping(value = "userInfoCon", method = RequestMethod.GET)
    public ModelAndView rooterPageuserInfoCon() {
        return new ModelAndView("userInfoCon");
    }

    @RequestMapping(value = "recMailUser", method = RequestMethod.GET)
    public ModelAndView rooterPagerecMailUser() {
        return new ModelAndView("recMailUser");
    }

    @RequestMapping(value = "EqimaAdmin", method = RequestMethod.GET)
    public ModelAndView rooterPageEqimaAdmin() {
        return new ModelAndView("EqimaAdmin");
    }

    @RequestMapping(value = "AccUser")
    public ModelAndView rooterPageAccUser() {
        return new ModelAndView("AccUser");
    }

    
}

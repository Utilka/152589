/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing.concurs_lab.UI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author andrii
 */
@Controller
public class appUIController {

    
    @Autowired
    public appUIController() {}
  
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showStartingPage() {
        return "index";
    }
    
}

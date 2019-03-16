package org.yundaxue.workshop.acq.label.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yundaxue.workshop.acq.label.bo.Label;
import org.yundaxue.workshop.acq.label.service.LabelService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lenovo on 2019/3/16.
 */
@Controller
public class LabelController {
    @Autowired
    LabelService labelService;

    @RequestMapping(value = "/label/{labelId}")
    public String labelDetail(@PathVariable int labelId,ModelMap model,
                             HttpServletRequest request, HttpServletResponse response) throws Exception{
        Label label = labelService.getLabel(labelId);
        model.put("label",label);
        return "label";
    }
}

package org.yundaxue.workshop.acq.label.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @ResponseBody
    public Label labelDetail(@PathVariable int labelId,
                             HttpServletRequest request, HttpServletResponse response) throws Exception{
        Label label = labelService.getLabel(labelId);
        return label;
    }
}

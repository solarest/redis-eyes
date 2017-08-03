package com.solarest.rediseyes.controller;

import com.solarest.rediseyes.service.ListOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JinJian on 17-8-2.
 * routes on redis queue operations
 */
@RestController
@RequestMapping("/list")
public class ListController extends AbstractHandleController {

    @Autowired
    private ListOpsService listOps;


}

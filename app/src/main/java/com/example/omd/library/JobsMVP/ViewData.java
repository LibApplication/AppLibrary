package com.example.omd.library.JobsMVP;

import com.example.omd.library.Models.JobsModel;

import java.util.List;

/**
 * Created by Delta on 22/01/2018.
 */

public interface ViewData {
    void onJobDataSuccess(List<JobsModel> jobsModelList);
    void onJobDataFailed(String error);
}
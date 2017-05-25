package com.tianjian.platform.routes;

import com.jfinal.config.Routes;
import com.tianjian.approval.api.ApprovalController;

public class ApprovalRoutes extends Routes {
	@Override
	public void config() {
		add("activiti", ApprovalController.class);
	}
}

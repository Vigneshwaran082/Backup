package com.app.sugarcrush.service;

import java.util.List;

import com.app.sugarcrush.model.Cake;

public interface CakeService {

	public List<Cake> getCakesByFlavour(String flavour);
}

package com.dszi.tractor;

import com.dszi.support.Constants;

public class Tractor {

	int waterLevel;
	int pesticideLevel;
	int fertilizerLevel;
	int tractorPostionX;
	int tractorPositionY;

	public Tractor() {
		this.waterLevel = Constants.tractorWaterLevel;
		this.pesticideLevel = Constants.tractorPesticideLevel;
		this.fertilizerLevel = Constants.tractorFertilizerLevel;
		this.tractorPostionX = 0;
		this.tractorPositionY = 0;
	}

	public int getWaterLevel() {
		return waterLevel;
	}

	public void setWaterLevel(int waterLevel) {
		this.waterLevel = waterLevel;
	}

	public int getPesticideLevel() {
		return pesticideLevel;
	}

	public void setPesticideLevel(int pesticideLevel) {
		this.pesticideLevel = pesticideLevel;
	}

	public int getFertilizerLevel() {
		return fertilizerLevel;
	}

	public void setFertilizerLevel(int fertilizerLevel) {
		this.fertilizerLevel = fertilizerLevel;
	}

	public int getTractorPostionX() {
		return tractorPostionX;
	}

	public void setTractorPostionX(int tractorPostionX) {
		this.tractorPostionX = tractorPostionX;
	}

	public int getTractorPositionY() {
		return tractorPositionY;
	}

	public void setTractorPositionY(int tractorPositionY) {
		this.tractorPositionY = tractorPositionY;
	}
}

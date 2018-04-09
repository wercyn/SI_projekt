package firstorderlogic;

import gui.SingleCell;

public class Properties {
	//Proste relacje jednoelemenetowe, okreœlaj¹ce stan pojedynczego pola na mapce.
	public static boolean NeedsWateringUrgently(SingleCell s) {
		boolean result=false;
		if(s.getIrrigation()<20) result = true;
		else result=false;
		return result;
	}
	public static boolean NeedsWatering(SingleCell s) {
		boolean result=false;
		if(s.getIrrigation()<50) result = true;
		else result=false;
		return result;
	}
	public static boolean CouldUseWatering(SingleCell s) {
		boolean result=false;
		if(s.getIrrigation()<70) result = true;
		else result=false;
		return result;
	}
	public static boolean WellWatered(SingleCell s) {
		boolean result=false;
		if(s.getIrrigation()>70) result = true;
		else result=false;
		return result;
	}
	public static boolean NeedsFertilisingUrgently(SingleCell s) {
		boolean result=false;
		if(s.getSoilDestruction()>70) result = true;
		else result=false;
		return result;
	}
	public static boolean NeedsFertilising(SingleCell s) {
		boolean result=false;
		if(s.getSoilDestruction()>30) result = true;
		else result=false;
		return result;
	}
	public static boolean CouldUseFertilising(SingleCell s) {
		boolean result=false;
		if(s.getSoilDestruction()>10) result = true;
		else result=false;
		return result;
	}
	public static boolean WellFertilised(SingleCell s) {
		boolean result=false;
		if(s.getSoilDestruction()<10) result = true;
		else result=false;
		return result;
	}
	public static boolean PlaguedbyPests(SingleCell s) {
		boolean result=false;
		if(s.getNumberOfPests()>10) result = true;
		else result=false;
		return result;
	}
	public static boolean SomePestsPresent(SingleCell s) {
		boolean result=false;
		if(s.getNumberOfPests()>=1) result = true;
		else result=false;
		return result;
	}
	public static boolean ClearfromPests(SingleCell s) {
		boolean result=false;
		if(s.getNumberOfPests()==0) result = true;
		else result=false;
		return result;
	}
	public static boolean IsDead(SingleCell s) {
		boolean result=false;
		if(s.getNumberOfPests()>15 || s.getIrrigation()==0 || s.getSoilDestruction()==100) result=true;
		else result=false;
		return result;
	}
	//Z³o¿one relacje:
	public static boolean IsinPerfectCondidion(SingleCell s) {
		boolean result=false;
		if(ClearfromPests(s)==true && WellWatered(s)==true && WellFertilised(s)==true) result = true;
		else result=false;
		return result;
	}
	public static boolean SomeWorkNeeded(SingleCell s) {
		boolean result=false;
		if(ClearfromPests(s)==false && WellWatered(s)==true && WellFertilised(s)==true) result = true;
		if(ClearfromPests(s)==true && WellWatered(s)==false && WellFertilised(s)==true) result = true;
		if(ClearfromPests(s)==true && WellWatered(s)==true && WellFertilised(s)==false) result = true;
		else result=false;
		return result;
	}
	//Mo¿na definiowaæ przez logiczne || i && w Javie, albo korzystaæ z operatorów z Operators.
	//W miare potrzeb mo¿na zdefiniowaæ za pomoc¹ prostych andów relacje typu XOR, NAND nawet
	//w operatorach.
}

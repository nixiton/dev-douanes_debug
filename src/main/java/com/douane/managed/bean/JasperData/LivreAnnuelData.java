package com.douane.managed.bean.JasperData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.douane.entite.Designation;
import com.douane.entite.Materiel;
import com.douane.entite.TypeMateriel;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class LivreAnnuelData {
	List <Object[]> list;
	public LivreAnnuelData(List <Object[]> l){
		this.list = l;
	}
	public JRBeanCollectionDataSource getDataSource() {
		List<LivreAnnuel> newOne = new ArrayList<LivreAnnuel>();
		for (Object[] i : this.list ) {
			LivreAnnuel la = new LivreAnnuel(i);
			newOne.add(la);
		}
		
		return new JRBeanCollectionDataSource(newOne);
	}
	
	public class LivreAnnuel{
		private String nomenclature;
		private String row1;
		private String row2;
		private Float row3;
		private Float row4;
		private int row5;
		public String getNomenclature() {
			return nomenclature;
		}

		public void setNomenclature(String nomenclature) {
			this.nomenclature = nomenclature;
		}

		public String getRow1() {
			return row1;
		}

		public void setRow1(String row1) {
			this.row1 = row1;
		}

		public String getRow2() {
			return row2;
		}

		public void setRow2(String row2) {
			this.row2 = row2;
		}

		public Float getRow3() {
			return row3;
		}

		public void setRow3(Float row3) {
			this.row3 = row3;
		}

		public Float getRow4() {
			return row4;
		}

		public void setRow4(Float row4) {
			this.row4 = row4;
		}

		public int getRow5() {
			return row5;
		}

		public void setRow5(int row5) {
			this.row5 = row5;
		}

		public String getRow6() {
			return row6;
		}

		public void setRow6(String row6) {
			this.row6 = row6;
		}

		public String getRow7() {
			return row7;
		}

		public void setRow7(String row7) {
			this.row7 = row7;
		}

		public Integer getRow8() {
			return row8;
		}

		public void setRow8(Integer row8) {
			this.row8 = row8;
		}

		public Float getRow9() {
			return row9;
		}

		public void setRow9(Float row9) {
			this.row9 = row9;
		}

		public TypeMateriel getRow10() {
			return row10;
		}

		public void setRow10(TypeMateriel row10) {
			this.row10 = row10;
		}

		public Designation getRow11() {
			return row11;
		}

		public void setRow11(Designation row11) {
			this.row11 = row11;
		}

		public Integer getRow12() {
			return row12;
		}

		public void setRow12(Integer row12) {
			this.row12 = row12;
		}

		public Integer getRow13() {
			return row13;
		}

		public void setRow13(Integer row13) {
			this.row13 = row13;
		}

		public String getRow14() {
			return row14;
		}

		public void setRow14(String row14) {
			this.row14 = row14;
		}

		public String getRow15() {
			return row15;
		}

		public void setRow15(String row15) {
			this.row15 = row15;
		}

		private String row6;
		private String row7;
		private Integer row8;
		private Float row9;
		private TypeMateriel row10;
		private Designation row11;
		private Integer row12;
		private Integer row13;
		private String row14;///TO-DO
		private String row15;
		
		public LivreAnnuel(Object[] i) {
			DateFormat  df = new SimpleDateFormat("dd MMM yyyy", Locale.FRANCE);
			this.nomenclature= (String)i[0];
			this.row1= (String)i[1];
			this.row2= (String)i[2];
			this.row3= (Float)i[3];
			this.row4= (Float)i[4];
			this.row5= (Integer) i[5];
			this.row6= (String)i[6];
			this.row7= (String)i[7];
			this.row8= (Integer)i[8];
			this.row9= (Float)i[9];
			this.row10= (TypeMateriel)i[10];
			this.row11= (Designation)i[11];
			this.row12= (Integer)i[12];
			this.row13= (Integer)i[13];
			if(i[14].toString() == "") {
				this.row14 = i[14].toString();
			}else {
				this.row14= df.format(i[14]);
			}
			this.row15 = (String) i[15];
		}
		
		
	}

	public List<Object[]> getList() {
		return list;
	}
	public void setList(List<Object[]> list) {
		this.list = list;
	}
}

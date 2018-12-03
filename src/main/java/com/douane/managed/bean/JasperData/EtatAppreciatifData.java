package com.douane.managed.bean.JasperData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.douane.entite.Designation;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class EtatAppreciatifData {
	List <Object[]> list;
	public EtatAppreciatifData(List <Object[]> l){
		
		this.list = l;
	}
	public JRBeanCollectionDataSource getDataSource() {
		List<EtatAppreciatif> newOne = new ArrayList<EtatAppreciatif>();
		for (Object[] i : this.list ) {
			EtatAppreciatif la = new EtatAppreciatif(i);
			newOne.add(la);
		}
		
		return new JRBeanCollectionDataSource(newOne);
	}
	
	public class EtatAppreciatif{
		private Designation row0;
		private String row1;
		private String row2;
		private Float row3;
		private Float row4;
		private int row5;
		private Float row6;
		private Float row7;
		private Float row8;
		private Float row9;
		private Float row10;
		private Float row11;
		private Float row12;
		private Float row13;
		private Float row14;///TO-DO
		private String row15;
		private Float row16;
		private Float row17;
		private Float row18;
		private Float row19;
		private Float row20;
		
		public EtatAppreciatif(Object[] i) {
			//this.row0= (Designation)i[0];
			this.row1= (String)i[1];
			this.row2= (String)i[2];
			//this.row3= (Float)i[3];
			this.row4= (Float)i[4];
			//this.row5= (Integer) i[5];
			this.row6= (Float)i[6];
			this.row7= (Float)i[7];
			this.row8= (Float)i[8];
			this.row9= (Float)i[9];
			this.row10= (Float)i[10];
			this.row11= (Float)i[11];
			this.row12= (Float)i[12];
			this.row13= (Float)i[13];
			this.row14= (Float) i[14];
			this.row15 = (String) i[15];
			this.row16 = (Float) i[16];
			this.row17 = (Float) i[17];
			this.row18 = (Float) i[18];
			this.row19 = (Float) i[19];
			this.row20 = (Float) i[20];
			
		}

		public Designation getRow0() {
			return row0;
		}

		public void setRow0(Designation row0) {
			this.row0 = row0;
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

		public Float getRow6() {
			return row6;
		}

		public void setRow6(Float row6) {
			this.row6 = row6;
		}

		public Float getRow7() {
			return row7;
		}

		public void setRow7(Float row7) {
			this.row7 = row7;
		}

		public Float getRow8() {
			return row8;
		}

		public void setRow8(Float row8) {
			this.row8 = row8;
		}

		public Float getRow9() {
			return row9;
		}

		public void setRow9(Float row9) {
			this.row9 = row9;
		}

		public Float getRow10() {
			return row10;
		}

		public void setRow10(Float row10) {
			this.row10 = row10;
		}

		public Float getRow11() {
			return row11;
		}

		public void setRow11(Float row11) {
			this.row11 = row11;
		}

		public Float getRow12() {
			return row12;
		}

		public void setRow12(Float row12) {
			this.row12 = row12;
		}

		public Float getRow13() {
			return row13;
		}

		public void setRow13(Float row13) {
			this.row13 = row13;
		}

		public Float getRow14() {
			return row14;
		}

		public void setRow14(Float row14) {
			this.row14 = row14;
		}

		public String getRow15() {
			return row15;
		}

		public void setRow15(String row15) {
			this.row15 = row15;
		}

		public Float getRow16() {
			return row16;
		}

		public void setRow16(Float row16) {
			this.row16 = row16;
		}

		public Float getRow17() {
			return row17;
		}

		public void setRow17(Float row17) {
			this.row17 = row17;
		}

		public Float getRow18() {
			return row18;
		}

		public void setRow18(Float row18) {
			this.row18 = row18;
		}

		public Float getRow19() {
			return row19;
		}

		public void setRow19(Float row19) {
			this.row19 = row19;
		}

		public Float getRow20() {
			return row20;
		}

		public void setRow20(Float row20) {
			this.row20 = row20;
		}
		
	}

	public List<Object[]> getList() {
		return list;
	}
	public void setList(List<Object[]> list) {
		this.list = list;
	}
}

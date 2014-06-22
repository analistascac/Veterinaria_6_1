package Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;


public class TFecha {
	private DefaultComboBoxModel dias31 = new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
	private DefaultComboBoxModel dias30 = new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"});
	private DefaultComboBoxModel dias29 = new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29"});
	private DefaultComboBoxModel dias28 = new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"});
	
	private DefaultComboBoxModel m = new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});
	private DefaultComboBoxModel y = new DefaultComboBoxModel();
	
	private int diia,mees,annio;
	
	public TFecha(final JComboBox dia, final JComboBox mes, final JComboBox anio){
		final Calendar c1 = Calendar.getInstance();
		
		diia = c1.get(Calendar.DATE);
		mees = c1.get(Calendar.MONTH)+1;
		annio = c1.get(Calendar.YEAR);
				
		for(int i = 0; i < 150; i++){
			y.addElement(c1.get(Calendar.YEAR)-i);
		}
		
		dia.setModel(dias31);
		dias31.setSelectedItem(diia);
		mes.setModel(m);
		m.setSelectedItem(mees);
		anio.setModel(y);
		y.setSelectedItem(annio);
		
		
		dia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				diia = dia.getSelectedIndex()+1;
			}
		});
		
		anio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(esBisiesto((int)anio.getSelectedItem())){
					if(m.getIndexOf(mes.getSelectedItem()) == 1){
						dia.setModel(dias29);
						dia.setSelectedIndex(0);
					}
				}else{
					if(m.getIndexOf(mes.getSelectedItem()) == 1){
						dia.setModel(dias28);
						if(mes.getSelectedIndex()+1 > 28){
							dia.setSelectedIndex(0);
						}
					}
				}
				annio = (int)y.getElementAt(anio.getSelectedIndex());
			}
		});
		
		mes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch(m.getIndexOf(mes.getSelectedItem())+1){
					case 1:
						dia.setModel(dias31);
						break;
					case 2:
						if(esBisiesto((int)anio.getSelectedItem())){
							dia.setModel(dias29);
						}else{
							dia.setModel(dias28);
						}
						break;
					case 3:
						dia.setModel(dias31);
						break;
					case 4:
						dia.setModel(dias30);
						break;
					case 5:
						dia.setModel(dias31);
						break;
					case 6:
						dia.setModel(dias30);
						break;
					case 7:
						dia.setModel(dias31);
						break;
					case 8:
						dia.setModel(dias31);
						break;
					case 9:
						dia.setModel(dias30);
						break;
					case 10:
						dia.setModel(dias31);
						break;
					case 11:
						dia.setModel(dias30);
						break;
					case 12:
						dia.setModel(dias31);
						break;
				}
				dia.setSelectedIndex(0);
				mees = mes.getSelectedIndex() + 1;
			}
		});
	}
	
	private boolean esBisiesto(int anio){
		if(anio % 4 == 0){
			if(anio % 100 == 0 && anio % 400 != 0){
				return false;
			}else{
				return true;
			}
		}else{
			return false;
		}
	}
	
	public String getFechaString(){
		return diia+"/"+mees+"/"+annio;
	}
	
	public int getDia(){
		return diia;
	}
	public int getMes(){
		return mees;
	}
	public int getAnio(){
		return annio;
	}
	
	public boolean fechaValida(){
		return true;
	}
	
}

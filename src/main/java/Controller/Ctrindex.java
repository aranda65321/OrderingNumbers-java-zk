package Controller;

import java.util.Random;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

public class Ctrindex extends SelectorComposer<Component> {
	
	private static final long serialVersionUID = -4606061626140696666L;
	private int [][] numbers = new int[5][20];
	private int aux;
	private int auxI;
	@Wire
	private Grid listNumber;
	@Wire
	private Grid orderedList;
	@Wire
	private Button generator;
	
	public Ctrindex(int[][] numbers, int aux, int auxI) {
		this.numbers = numbers;
		this.aux = aux;
		this.auxI = auxI = 0;
	}
	
	public Ctrindex() {
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		int [][] num = numberGenerator();
		showNumber(num, listNumber);	
		orderNumber(num);
		showNumber(num, orderedList);
	}
	
	@Listen("onClick = #generator")
	public void generator() {
		Executions.sendRedirect("/index.zul");
		
	}

	public int [][] numberGenerator() {
		Random random = new Random();
		for(int i = 0; i<= 4;i++) {
			for(int j = 0; j<= 19;j++) {
			numbers[i][j] = random.nextInt(1000);
			}
		}
		return numbers;
	}

	public void showNumber(int [][] num, Grid list) {
		Rows rows = list.getRows();
		for(int k = 0; k<= 4;k++) {
			Row row = new Row();
			for(int n = 0; n<= 19;n++) {
				Label lab0 = new Label(Integer.toString(num[k][n]));
				row.appendChild(lab0);	
				}
			rows.appendChild(row);
		}
		
	}
	
	public int [][] orderNumber(int [][] number) {
		
		int [] vAux = new int [100]; 
		for(int i = 0; i< 5;i++) {
			for(int j = 0; j< 20;j++) {
				vAux [auxI] = number[i][j];
				auxI++;
			}
		}
		for(int m = 0; m < 100;m++) {
			for(int n = 0; n< 99;n++) {
				if(vAux[n] > vAux [n+1]) {
					aux = vAux[n];
					vAux[n] = vAux[n+1];
					vAux[n+1] = aux;
					}
			}
		}
		auxI=0;
		for(int i = 0; i< 5;i++) {
			for(int j = 0; j< 20;j++) {
				number[i][j] = vAux [auxI];
				auxI++;
			}
		}
		return number;
		}
}

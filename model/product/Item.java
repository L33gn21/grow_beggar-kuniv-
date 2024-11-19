package source.model.product;

import java.util.Scanner;

public class Item extends Product{
    private int increaseMoney; // 구걸 1회당 추가로 얻는 돈
    public int getIncreaseMoney(){
        return increaseMoney;
    }
    public void read(Scanner filein) {
		this.name = filein.next();
        this.price = filein.nextInt();
        this.increaseMoney = filein.nextInt();
	}
}

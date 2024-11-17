package source.model;

import java.util.ArrayList;

import source.model.product.Colleague;
import source.model.product.Item;
import source.model.product.Product;

public class Poor {
	private int totalMoney; // 소지한 돈
	private int beggedMoney; // 구걸 1회당 얻는 돈
	private ArrayList<Item> itemList;
	private ArrayList<Colleague> colleagueList;

	public void beg() {
		// 구걸금액+아이템 increaseMoney
		totalMoney += beggedMoney + itemList.stream().mapToInt(Item::getIncreaseMoney).sum();
	}

	public void receiveMoney(int colleagueIndex) {
		if (colleagueIndex >= 0 && colleagueIndex < colleagueList.size()) {
			Colleague colleague = colleagueList.get(colleagueIndex); // 인덱스 확인 후 해당 동료 객체 가져옴
			int moneyReceived = colleague.giveMoney(); // 동료가 주는 돈을 계산
			totalMoney += moneyReceived; // 받은 돈을 totalMoney에 추가
		}
	}

	public boolean addProduct(Product product) {
		if (product instanceof Item) {
			itemList.add((Item) product);
			return true;
		}
		if (product instanceof Colleague) {
			colleagueList.add((Colleague) product);
			return true;
		}
		return false;
	}

	public int getMoney(){
		return this.totalMoney;
	}

	public boolean reduceMoney(int money){
		if(this.totalMoney >= money){
			return true;
		}
		return false;
	}
}

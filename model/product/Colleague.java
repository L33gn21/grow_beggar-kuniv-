package source.model.product;

import java.time.LocalDateTime;
import java.util.Scanner;

import source.model.Poor;

public class Colleague extends Product {
	private int earningMoney; // 초당 얻는 돈
	private LocalDateTime lastEarnTime;
	private Poor poor;

	public void read(Scanner filein) {
		this.name = filein.next();
        this.price = filein.nextInt();
        this.earningMoney = filein.nextInt();
	}

	public void init(){
		this.lastEarnTime = LocalDateTime.now();
	}

	public int giveMoney() {
		// 현재 시간 가져오기
		LocalDateTime now = LocalDateTime.now();
		// 마지막 돈을 받은 시간과 현재 시간의 차이
		long secondsElapsed = java.time.Duration.between(lastEarnTime, now).getSeconds();
		// 초 단위로 얻은 돈을 계산
		int moneyGiven = (int) (secondsElapsed * earningMoney);
		// 마지막 돈을 받은 시간을 갱신
		lastEarnTime = now;

		return moneyGiven;
	}
}

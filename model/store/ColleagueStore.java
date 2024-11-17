package source.model.store;

import java.util.ArrayList;
import java.util.List;

import source.model.Poor;
import source.model.product.Colleague;
import source.repository.Repository;

public class ColleagueStore extends Store<Colleague>{
    private ArrayList<Colleague> colleagueList = new ArrayList<>();

    public ColleagueStore(Repository<Colleague> repo){
        this.repo = repo;
        addProducts(repo.read());
    }

    @Override
    public boolean sellProduct(int productIndex, Poor poor) {
        if (productIndex < 0 || productIndex >= colleagueList.size()) {
            return false; // 잘못된 인덱스
        }
        Colleague colleague = colleagueList.get(productIndex);

        // 돈이 충분한지 확인
        if (poor.getMoney() >= colleague.getPrice()) {
            poor.reduceMoney(colleague.getPrice()); // 돈 차감
            poor.addProduct(colleague); // 동료 추가
            return true;
        }
        return false; // 돈이 부족함

    }

    @Override
    public void addProducts(List<Colleague> products) {
        colleagueList.addAll(products);//동료 목록 추가
    }
}


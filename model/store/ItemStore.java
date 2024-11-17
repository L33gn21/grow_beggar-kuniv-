
package source.model.store;

import java.util.ArrayList;
import java.util.List;

import source.model.Poor;
import source.model.product.Item;
import source.repository.Repository;

public class ItemStore extends Store<Item> {
    private ArrayList<Item> itemList = new ArrayList<>();

    public ItemStore(Repository<Item> repo) {
        this.repo = repo;
        addProducts(repo.read());
    }

    @Override
    public boolean sellProduct(int productIndex, Poor poor) {
        // productIndex 유효성 검사
        if (productIndex < 0 || productIndex >= itemList.size()) {
            return false; // 잘못된 인덱스
        }
        Item item = itemList.get(productIndex);

        // 돈이 충분한지 확인
        if (poor.getMoney() >= item.getPrice()) {
            poor.reduceMoney(item.getPrice()); // 돈 차감
            poor.addProduct(item); // 아이템 추가
            return true;
        }
        return false; // 돈이 부족함
    }

    @Override
    public void addProducts(List<Item> products) {
        // 아이템 목록 추가
        itemList.addAll(products);
    }
}

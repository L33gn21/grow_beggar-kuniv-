package source.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import source.model.product.Item;

public class ItemRepository implements Repository<Item> {

    @Override
    public ArrayList<Item> read() {
        // 파일 입력으로 아이템 정보 받아서 return
        ArrayList<Item> itemList = new ArrayList<>();
        Scanner filein = openFile("items.txt");
        Item i = null;
        while (filein.hasNext()) {
            i = new Item();
            i.read(filein);
            itemList.add(i);
        }
        filein.close();
        return itemList;
    }

    Scanner openFile(String filename) {
        Scanner filein = null;
        try {
            filein = new Scanner(new File(filename));
        } catch (IOException e) {
            System.out.println("파일 입력 오류");
            System.exit(0);
        }
        return filein;
    }
}

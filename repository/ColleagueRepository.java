package source.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import source.model.product.Colleague;

public class ColleagueRepository implements Repository<Colleague> {

    @Override
    public ArrayList<Colleague> read() {
        // 파일 입력으로 동료 정보 받아서 List 로 return
        // 파일 형식 / name price increaseMoney
        // ex. 동료1 10000 10000
        ArrayList<Colleague> colleagueList = new ArrayList<>();
        Scanner filein = openFile("colleagues.txt");
        Colleague col = null;
        while (filein.hasNext()) {
            col = new Colleague();
            col.read(filein); //Colleague에 read 추가?
            colleagueList.add(col);
        }
        filein.close();
        return colleagueList;
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

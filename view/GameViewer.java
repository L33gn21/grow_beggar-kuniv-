package source.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import source.controller.GameController;
import source.model.store.StoreName;
import source.model.product.Item;
import source.model.product.Colleague;

public class GameViewer extends JFrame {
    private GameController controller;
    private JLabel moneyLabel; // 현재 돈 표시
    private JPanel itemPanel; // 아이템 패널
    private JPanel colleaguePanel; // 동료  패널
    private JButton begButton; // 구걸하기 버튼
    private JButton getMoneyButton; // 동료로부터 돈 회수 버튼

    public GameViewer(GameController controller) {
        this.controller = controller;

        setTitle("거지 키우기");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 돈 레이블
        moneyLabel = new JLabel("돈: " + controller.getHaveMoney() + "원");
        moneyLabel.setFont(new Font("Arial", Font.BOLD, 16));
        moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(moneyLabel, BorderLayout.NORTH);

        // 중앙에 아이템과 동료 패널
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 2));

        // 아이템 패널
        itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        JScrollPane itemScrollPane = new JScrollPane(itemPanel);
        centerPanel.add(itemScrollPane);

        // 동료 패널
        colleaguePanel = new JPanel();
        colleaguePanel.setLayout(new BoxLayout(colleaguePanel, BoxLayout.Y_AXIS));
        JScrollPane colleagueScrollPane = new JScrollPane(colleaguePanel);
        centerPanel.add(colleagueScrollPane);

        add(centerPanel, BorderLayout.CENTER);

        // 버튼 패널
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        // 구걸하기 버튼
        begButton = new JButton("구걸하기");
        begButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.beg();
                updateMoneyLabel();
            }
        });
        buttonPanel.add(begButton);

        // 동료로부터 돈 회수 버튼
        getMoneyButton = new JButton("동료로부터 돈 회수");
        getMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getMoney(0);
                updateMoneyLabel();
            }
        });
        buttonPanel.add(getMoneyButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // 아이템 및 동료 정보 업데이트
        updateItemPanel();
        updateColleaguePanel();

        setVisible(true);
    }

    // 아이템 패널 업데이트
    private void updateItemPanel() {
        itemPanel.removeAll();
        int idx = 0;
        for (Item item : controller.getItemStoreItems()) {
            JPanel itemPanelItem = new JPanel();
            itemPanelItem.setLayout(new FlowLayout());
            itemPanelItem.add(new JLabel(item.getName() + " - " + item.getPrice() + "원"));
            JButton buyButton = new JButton("구매");
            final int itemIdx = idx;
            buyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (controller.purchaseProduct(StoreName.Item, itemIdx)) {
                        updateMoneyLabel();
                        JOptionPane.showMessageDialog(null, item.getName() + "아이템을 구매했습니다");
                    } else {
                        JOptionPane.showMessageDialog(null, "돈 부족!");
                    }
                }
            });
            itemPanelItem.add(buyButton);
            itemPanel.add(itemPanelItem);
            idx++;
        }
        itemPanel.revalidate();
        itemPanel.repaint();
    }

    // 동료 패널 업데이트
    private void updateColleaguePanel() {
        colleaguePanel.removeAll();
        int idx = 0;
        for (Colleague colleague : controller.getColleagueStoreItems()) {
            JPanel colleaguePanelItem = new JPanel();
            colleaguePanelItem.setLayout(new FlowLayout());
            colleaguePanelItem.add(new JLabel(colleague.getName() + " - " + colleague.getPrice() + "원"));
            JButton hireButton = new JButton("영입");
            final int colleagueIdx = idx;
            hireButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (controller.purchaseProduct(StoreName.Colleague, colleagueIdx)) {
                        updateMoneyLabel();
                        JOptionPane.showMessageDialog(null, colleague.getName() + "동료를 영입했습니다");
                    } else {
                        JOptionPane.showMessageDialog(null, "돈 부족!");
                    }
                }
            });
            colleaguePanelItem.add(hireButton);
            colleaguePanel.add(colleaguePanelItem);
            idx++;
        }
        colleaguePanel.revalidate();
        colleaguePanel.repaint();
    }

    // 돈 업데이트
    private void updateMoneyLabel() {
        moneyLabel.setText("money: " + controller.getHaveMoney() + "won");
    }
}
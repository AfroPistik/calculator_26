package com.team.calculator.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HistoryPanel extends JPanel {
    private JList<String> historyList;
    private DefaultListModel<String> listModel;

    public HistoryPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("History"));

        listModel = new DefaultListModel<>();
        historyList = new JList<>(listModel);
        historyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(historyList), BorderLayout.CENTER);
    }

    public void updateHistory(List<String> entries) {
        listModel.clear();
        if (entries != null) {
            for (String entry : entries) {
                listModel.addElement(entry);
            }
        }
    }

    public void addHistoryEntry(String entry) {
        if (entry != null && !entry.trim().isEmpty()) {
            listModel.addElement(entry);
        }
    }
}
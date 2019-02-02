package org.aio.gui.task_panels;

import org.aio.activities.quests.*;
import org.aio.tasks.QuestTask;
import org.aio.tasks.Task;
import org.aio.tasks.TaskType;
import org.json.simple.JSONObject;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class QuestTaskPanel extends TaskPanel {

    private JComboBox<Quest> questSelector;

    QuestTaskPanel(){
        super(TaskType.QUEST);

        JPanel contentPanel = new JPanel(new BorderLayout());

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));

        final JLabel label1 = new JLabel();
        label1.setText("Quest:");
        controls.add(label1);

        questSelector = new JComboBox<>();
        controls.add(questSelector);

        contentPanel.add(controls, BorderLayout.CENTER);

        questSelector.setModel(new DefaultComboBoxModel<>(Quest.values()));

        setContentPanel(contentPanel);
    }

    @Override
    public Task toTask() {
        switch ((Quest) questSelector.getSelectedItem()) {
            case SHEEP_SHEARER:
                return new QuestTask(new SheepShearer(), (Quest) questSelector.getSelectedItem());
            case RUNE_MYSTERIES:
                return new QuestTask(new RuneMysteries(), (Quest) questSelector.getSelectedItem());
	        case COOKS_ASSISTANT:
	            return new QuestTask(new CooksAssistant(), (Quest) questSelector.getSelectedItem());
            case ROMEO_AND_JULIET:
                return new QuestTask(new RomeoAndJuliet(), (Quest) questSelector.getSelectedItem());
            case THE_RESTLESS_GHOST:
                return new QuestTask(new TheRestlessGhost(), (Quest) questSelector.getSelectedItem());
        }
        return null;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", TaskType.QUEST.name());
        jsonObject.put("quest", ((Quest) questSelector.getSelectedItem()).name());
        return jsonObject;
    }

    @Override
    public void fromJSON(JSONObject jsonObject) {
        questSelector.setSelectedItem(Quest.valueOf((String) jsonObject.get("quest")));
    }
}
